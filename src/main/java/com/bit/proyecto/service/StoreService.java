package com.bit.proyecto.service;

import com.bit.proyecto.configuration.TokenLogin;
import com.bit.proyecto.configuration.exception.BadRequestException;
import com.bit.proyecto.configuration.exception.NotFoundException;
import com.bit.proyecto.configuration.exception.ServerErrorException;
import com.bit.proyecto.configuration.exception.UnauthorizedException;
import com.bit.proyecto.dao.*;
import com.bit.proyecto.dto.DeliverDTO;
import com.bit.proyecto.helper.Carrito;
import com.bit.proyecto.helper.Helpers;
import com.bit.proyecto.model.*;
import com.bit.proyecto.dto.ShoeDTO;
import com.bit.proyecto.rest.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StoreService {
    private static final Logger logger = LogManager.getLogger(StoreService.class);
    private Response response;
    private final ObjectMapper maper = new ObjectMapper();
    private final Double IVA = 0.12;
    @Autowired
    private ShoeRepository shoeRepository;
    @Autowired
    private TokenLogin tokens;
    @Autowired
    private Helpers helpers;
    @Autowired
    private Carrito carrito;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private DetailOrderRepository detailOrderRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DeliverRepository deliverRepository;
    public Response listProducts(Pageable pageable, String gender, String size, String color, Double price, String order){
        Shoe shoe = new Shoe();
        List<ShoeDTO> shoeDTOOrder = new ArrayList<>();
        List<ShoeDTO> dtoList = new ArrayList<>();
        try {
            if (gender != null) {
                shoe.setZapGender(gender);
            }
            if (size != null) {
                shoe.setZapSize(size);
            }
            if (color != null) {
                shoe.setZapColor(color);
            }
            if (price != null) {
                shoe.setZapPrice(price);
            }
            for (Shoe shoe1: shoeRepository.findAll(Example.of(shoe), pageable)) {
                dtoList.add(helpers.getDTO(shoe1));
            }
            if (order.toUpperCase().equals("DESC")) {
                dtoList.stream().sorted((o1, o2) -> Integer.valueOf(Integer.parseInt(o2.getCode().substring(4,o2.getCode().length()))).compareTo(Integer.parseInt(o1.getCode().substring(4,o1.getCode().length())))).forEach(zapato -> {
                    shoeDTOOrder.add(zapato);
                });
            }else {
                dtoList.stream().sorted((o1, o2) -> Integer.valueOf(Integer.parseInt(o1.getCode().substring(4,o1.getCode().length()))).compareTo(Integer.parseInt(o2.getCode().substring(4,o2.getCode().length())))).forEach(zapato -> {
                    shoeDTOOrder.add(zapato);
                });
            }
            if (!shoeDTOOrder.isEmpty()) {
                response = new Response("Data shoe successfully", "200", true, new Date(), shoeDTOOrder);
                String json = maper.writeValueAsString(response);
                logger.info("Respuesta {}", () -> json);
                return response;
            }
            throw new NotFoundException("There is no information to display.");
        } catch (ServerErrorException e) {
            throw new ServerErrorException("Error interno");
        } catch (JsonProcessingException e) {
            throw new ServerErrorException("Error al procesar el json");
        }
    }
    public Response addCarrito(String codeShoe, Integer amount){
        Shoe shoe;
        ShoeDTO shoeDTO;
        String[] validateStock;
        try {
            if (shoeRepository.existsById(codeShoe)) {
                shoe = shoeRepository.findById(codeShoe).get();
                if (amount > 0 && amount <= shoe.getZapStock()) {
                    shoeDTO = helpers.getDTO(shoe);
                    shoeDTO.setStock(amount);
                    validateStock = carrito.addCarrito(shoeDTO).split(",");
                    if (Integer.valueOf(validateStock[0]) >= 0){
                        response = new Response("Product added to cart successfully", "200", true, new Date(),carrito.getCarrito());
                        String json = maper.writeValueAsString(response);
                        logger.info("Respuesta {}", () -> json);
                        return response;
                    }else {
                        throw new BadRequestException("There are ("+validateStock[1]+") shoes in stock, for the shoe with code "+codeShoe);
                    }
                } else {
                    throw new BadRequestException("Enter a quantity greater than zero and less than the stock of the shoe("+shoe.getZapStock()+")");
                }
            } else {
                throw new NotFoundException("The product was not found.");
            }
        } catch (ServerErrorException e) {
            throw new ServerErrorException("Error interno");
        } catch (JsonProcessingException e) {
            throw new ServerErrorException("Error al procesar el json");
        }
    }

    public Response buy(Integer codePerson){
        Shoe shoe = new Shoe();
        double iva,total,subtotal,totalOrder=0.00;
        Order order = new Order();
        Integer numberInvoice;
        Person person = new Person();
        Date date = new Date();
        Deliver deliver;
        DeliverDTO deliverDTO;
        try {
            if (personRepository.existsById(codePerson)) {
                carrito.setCodePerson(codePerson);
                if (carrito.getCodePerson() != null && !carrito.getCarrito().isEmpty()) {
                    numberInvoice = orderRepository.findAll().size()+1;
                    person = new Person(carrito.getCodePerson());
                    order = orderRepository.save(new Order(new Date(),"new Order","Pendiente",0.00,"001-001-"+helpers.generarNumeroFactura(numberInvoice),person));
                    for (ShoeDTO shoeDTO: carrito.getCarrito()) {
                        shoe = new Shoe();
                        subtotal = shoeDTO.getPrice() * shoeDTO.getStock();
                        iva = (subtotal) * IVA;
                        total = subtotal + iva;
                        totalOrder+=total;
                        shoe.setZapCode(shoeDTO.getCode());
                        detailOrderRepository.save(new DetailOrder(shoeDTO.getStock(),shoeDTO.getPrice(),0.00,helpers.precision(iva,2),helpers.precision(total,2),"quality product",shoe,order));

                        shoe = shoeRepository.findById(shoeDTO.getCode()).get();
                        shoe.setZapStock(shoe.getZapStock()-shoeDTO.getStock());
                        shoeRepository.save(shoe);
                    }
                    order.setPedTotal(helpers.precision(totalOrder,2));
                    orderRepository.save(order);

                    date.setTime(date.getTime()+(259200000));
                    deliver = deliverRepository.save(new Deliver("test description","Juan Perez","Order by buy test",new Date(),date,"Bodega",order));
                    deliverDTO = new DeliverDTO(deliver.getEntCode(),deliver.getEntDescription(),deliver.getEntReceive(),deliver.getEntComment(),deliver.getEntSendDate(),deliver.getEntReceiveDate(),deliver.getEntStatus());
                    carrito.restartCarrito();
                    response = new Response("Buy successfully", "200", true, new Date(), deliverDTO);
                    String json = maper.writeValueAsString(response);
                    logger.info("Respuesta {}", () -> json);
                    return response;
                }
                throw new NotFoundException("Add elements to the cart at the link: http://localhost:8080/carrito sending shoe code, quantity and customer code as parameters");
            }else {
                throw new NotFoundException("The person was not found.");
            }
        } catch (ServerErrorException e) {
            throw new ServerErrorException("Error interno");
        } catch (JsonProcessingException e) {
            throw new ServerErrorException("Error al procesar el json");
        }
    }
    public Response sendOrder(Integer codeDeliver){
        Deliver deliver;
        DeliverDTO deliverDTO;
        try {
            if (deliverRepository.existsById(codeDeliver)){
                deliver = deliverRepository.findById(codeDeliver).get();
                deliver.setEntStatus("Enviado");
                deliverRepository.save(deliver);
                deliverDTO = new DeliverDTO(deliver.getEntCode(),deliver.getEntDescription(),deliver.getEntReceive(),deliver.getEntComment(),deliver.getEntSendDate(),deliver.getEntReceiveDate(),deliver.getEntStatus());
                response = new Response("Order sent successfully", "200", true, new Date(), deliverDTO);
                String json = maper.writeValueAsString(response);
                logger.info("Respuesta {}", () -> json);
                return response;
            }
            throw new NotFoundException("The deliver was not found.");
        } catch (ServerErrorException e) {
            throw new ServerErrorException("Error interno");
        } catch (JsonProcessingException e) {
            throw new ServerErrorException("Error al procesar el json");
        }
    }
    public Response cancelOrder(String token, Integer codeDeliver){
        Deliver deliver;
        Shoe shoe;
        List<DetailOrder> detailOrders;
        try {
            if (tokens.exist(token)) {
                if (deliverRepository.existsById(codeDeliver)) {
                    deliver = deliverRepository.findById(codeDeliver).get();
                    if (!deliver.getEntStatus().equals("Enviado")) {
                        detailOrders = detailOrderRepository.findByPedCodigo(deliver.getPedCode().getPedCode());
                        for (DetailOrder detailOrder : detailOrders) {
                            shoe = shoeRepository.findById(detailOrder.getZapCode().getZapCode()).get();
                            shoe.setZapStock(shoe.getZapStock() + detailOrder.getDepAmount());
                            shoeRepository.save(shoe);
                        }
                        orderRepository.deleteById(deliver.getPedCode().getPedCode());
                        response = new Response("Order cancel successfully", "200", true, new Date());
                        String json = maper.writeValueAsString(response);
                        logger.info("Respuesta {}", () -> json);
                        return response;
                    }
                    throw new BadRequestException("Error can not cancel the purchase the status is sent.");
                }
                throw new NotFoundException("The deliver was not found.");
            }
            throw new UnauthorizedException("Please send a valid token in the Key(token) header");
        } catch (ServerErrorException e) {
            throw new ServerErrorException("Error interno");
        } catch (JsonProcessingException e) {
            throw new ServerErrorException("Error al procesar el json");
        }
    }

    public Response getCarrito(){
        List<ShoeDTO> shoeDTO;
        try{
            if (!carrito.getCarrito().isEmpty()) {
                shoeDTO = carrito.getCarrito();
                response = new Response("Data car successfully", "200", true, new Date(), shoeDTO);
                String json = maper.writeValueAsString(response);
                logger.info("Respuesta {}", () -> json);
                return response;
            }
            throw new NotFoundException("There is no information to display.");
        } catch (ServerErrorException e) {
            throw new ServerErrorException("Error interno");
        } catch (JsonProcessingException e) {
            throw new ServerErrorException("Error al procesar el json");
        }
    }
}
