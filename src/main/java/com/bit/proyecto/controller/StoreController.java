package com.bit.proyecto.controller;

import com.bit.proyecto.rest.Response;
import com.bit.proyecto.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @GetMapping("catalogo")
    public ResponseEntity<Response> getListShoe(Pageable pageable,
                                                @RequestParam(required = false) String genero,
                                                @RequestParam(required = false) String talla,
                                                @RequestParam(required = false) String color,
                                                @RequestParam(required = false) Double precio,
                                                @RequestParam(required = false,defaultValue = "asc") String order){
        return ResponseEntity.status(HttpStatus.OK).body(storeService.listProducts(pageable,genero,talla,color,precio,order));
    }

    @GetMapping("carrito")
    public ResponseEntity<Response> addCarrito(@RequestParam String codeShoe, @RequestParam Integer amount){
        return ResponseEntity.status(HttpStatus.OK).body(storeService.addCarrito(codeShoe,amount));
    }
    @GetMapping("carrito/list")
    public ResponseEntity<Response> getCarrito(){
        return ResponseEntity.status(HttpStatus.OK).body(storeService.getCarrito());
    }
    @GetMapping("buy")
    public ResponseEntity<Response> buyProducts(@RequestParam Integer codePerson){
        return ResponseEntity.status(HttpStatus.OK).body(storeService.buy(codePerson));
    }

    @PatchMapping("send")
    public ResponseEntity<Response> sendDeliver(@RequestParam Integer codeDeliver){
        return ResponseEntity.status(HttpStatus.OK).body(storeService.sendOrder(codeDeliver));
    }

    @DeleteMapping("cancel/{codeDeliver}")
    public ResponseEntity<Response> cancelOrder(@RequestHeader(value = "token", defaultValue = "none") String token, @PathVariable Integer codeDeliver){
        return ResponseEntity.status(HttpStatus.OK).body(storeService.cancelOrder(token,codeDeliver));
    }
}
