package com.bit.proyecto.service;

import com.bit.proyecto.configuration.exception.BadRequestException;
import com.bit.proyecto.configuration.exception.NotFoundException;
import com.bit.proyecto.configuration.exception.ServerErrorException;
import com.bit.proyecto.dao.ShoeRepository;
import com.bit.proyecto.dto.ShoeDTO;
import com.bit.proyecto.helper.Helpers;
import com.bit.proyecto.model.Shoe;
import com.bit.proyecto.rest.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {
    private Response response;
    private ShoeDTO shoeDTO;
    @Autowired
    private Helpers helpers;
    @Autowired
    private ShoeRepository shoeRepository;

    public Response getProductById(String codeShoe){
        try {
            if (shoeRepository.existsById(codeShoe)){
                shoeDTO = helpers.getDTO(shoeRepository.findById(codeShoe).get());
                response = new Response("Product found successfully", "200", true, new Date(), shoeDTO);
                return response;
            }
            throw new NotFoundException("The product was not found.");
        }catch (ServerErrorException e) {
            throw new ServerErrorException("Error interno");
        }
    }
    public Response getProducts(){
        List<ShoeDTO> shoeDTOS = new ArrayList<>();
        try {
            shoeRepository.findAll().forEach(shoe -> {
                shoeDTOS.add(helpers.getDTO(shoe));
            });
            response = new Response("Products found successfully", "200", true, new Date(), shoeDTOS);
            return response;
        }catch (ServerErrorException e) {
            throw new ServerErrorException("Error interno");
        }
    }
    public Response saveProduct(ShoeDTO shoeDTO){
        Shoe shoe;
        try {
            shoe = helpers.getModelShoe(shoeDTO);
            if (!shoeRepository.existsById(shoe.getZapCode())){
                shoeDTO = helpers.getDTO(shoeRepository.save(shoe));
                response = new Response("Product saved successfully!!", "200", true, new Date(), shoeDTO);
                return response;
            }
            throw new BadRequestException("The product code already exists.");
        }catch (ServerErrorException e) {
            throw new ServerErrorException("Error interno");
        }
    }
    public Response updateProduct(ShoeDTO shoeDTO){
        Shoe shoe;
        try {
            shoe = helpers.getModelShoe(shoeDTO);
            shoeDTO = helpers.getDTO(shoeRepository.save(shoe));
            response = new Response("Product updated successfully!!", "200", true, new Date(), shoeDTO);
            return response;
        }catch (ServerErrorException e) {
            throw new ServerErrorException("Error interno");
        }
    }
    public Response deleteProductById(String codeShoe){
        try {
            if (shoeRepository.existsById(codeShoe)){
                shoeRepository.deleteById(codeShoe);
                response = new Response("Product deleted successfully!!", "200", true, new Date());
                return response;
            }
            throw new NotFoundException("The product was not found.");
        }catch (ServerErrorException e) {
            throw new ServerErrorException("Error interno");
        }
    }
}
