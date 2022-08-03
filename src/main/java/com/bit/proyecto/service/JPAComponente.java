package com.bit.proyecto.service;

import com.bit.proyecto.dao.OrderRepository;
import com.bit.proyecto.dao.PersonRepository;
import com.bit.proyecto.dao.ShoeRepository;
import com.bit.proyecto.helper.Helpers;
import com.bit.proyecto.dto.ShoeDTO;
import com.bit.proyecto.model.Shoe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JPAComponente implements CommandLineRunner {
    @Autowired
    ShoeRepository repositorio;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    OrderRepository orderRepository;
    private List<ShoeDTO> shoeDTO = new ArrayList<>();
    @Autowired
    private Helpers helpers;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Server init");
//        System.out.println("Insertar personas");
//        Date date = new Date();
//        for (int i=1;i<=30;i++) {
//            date.setTime(date.getTime()+(3600000*i));
////            System.out.println(date);
//            personaDAO.save(new Persona(i,"12345689-" + i, "Nombre " + i,"Apellido " + i, i % 3 == 0 ? "Pelileo" : "Quito", i % 4 == 0 ? "Medium" : "Small", date, i % 2 == 0 ? "F" : "M", "persona_" + i + "@pichincha.com"));
//        }
//        System.out.println("Personas:");
//        personRepository.findAll().forEach(persona -> {
//            System.out.print("{\n\"codigo\": "+persona.getPerCode()+",\n"+"\"nombre\": "+persona.getPerName()+",\n"+"\"apellido\": "+persona.getPerLastName()+",\n\"pedidos\":[\n");
//            if (orderRepository.existByPerCodigo(persona.getPerCode())>0)
//                orderRepository.findByPerCodigo(persona.getPerCode()).forEach(pedido -> System.out.print("{\n\t\"codigoPedido\": "+pedido.getPedCode()+",\n\t\"codigoPersona\": "+pedido.getPerCode()+",\n\t\"factura\": "+pedido.getPedInvoice()+",\n\t\"estado\": "+pedido.getPedStatus()+"\n},"));
//            System.out.println("]\n},");
//        });
//        System.out.println("Insertar zapatos");
//        for (int i=1;i<=10;i++)
//            repositorio.save(new Shoe("ZAP-"+i,"Nombre del zapato "+i, i%2==0 ? "Casual" : "Deportivo", i % 3 == 0 ? "F" : "M", "azul",i % 3 == 0 ? "30" : "36",i*5, i%2==0 ? 20.99 : 10.50));

//        System.out.println("Zapatos:");
//        /*List<Zapato> zapatos = repositorio.findAll();
//        zapatos.sort(Comparator.comparing(Zapato::getZapCodigo).reversed());
//        zapatos.forEach(zapato -> System.out.println(zapato));*/
//        repositorio.findAll().stream().sorted((o1, o2) -> Integer.valueOf(Integer.parseInt(o1.getZapCodigo().substring(4,o1.getZapCodigo().length()))).compareTo(Integer.parseInt(o2.getZapCodigo().substring(4,o2.getZapCodigo().length())))).forEach(zapato -> {
////           System.out.println(zapatoDTO.size());
//           zapatoDTO.add(new ZapatoDTO(zapato.getZapCodigo(),zapato.getZapNombre(),zapato.getZapModelo(),zapato.getZapGenero(),zapato.getZapColor(),zapato.getZapTalla(),zapato.getZapStock()));
//        });
//        zapatoDTO.forEach(zapatoDTO1 -> System.out.println(zapatoDTO1.getZapCodigo()+"\t"+zapatoDTO1.getZapNombre()));

//        System.out.println("Insert Pedido");
//        for (int i=2;i<=30;i++) {
//            date.setTime(date.getTime()+(3600000*i));
//            pedidoDAO.save(new Pedido(date,"This is a test for pedido "+i,i%4==0 ? "Pendiente" : "Completo",10.20+i,"001-001-"+helpers.generarNumeroFactura(i),0.12,new Persona(i-1)));
//        }

    }
}
