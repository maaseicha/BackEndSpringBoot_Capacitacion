package com.bit.proyecto.helper;

import com.bit.proyecto.dao.ShoeRepository;
import com.bit.proyecto.dto.ShoeDTO;
import com.bit.proyecto.model.Shoe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Carrito {
    List<ShoeDTO> carrito;
    Integer codePerson;
    @Autowired
    private ShoeRepository shoeRepository;

    public Carrito() {
        carrito = new ArrayList<>();
    }
    public List<ShoeDTO> getCarrito() {
        return carrito;
    }
    public String addCarrito(ShoeDTO shoeDTO) {
        Boolean flag = true;
        Integer valueStock = shoeDTO.getStock();
        Shoe shoe;
        StringBuilder value = new StringBuilder();
        for (int i=0;i<carrito.size();i++){
            if (carrito.get(i).getCode().equals(shoeDTO.getCode())){
                shoe = shoeRepository.findById(shoeDTO.getCode()).get();
                valueStock = shoe.getZapStock() - (carrito.get(i).getStock() + shoeDTO.getStock());
                if (valueStock >= 0) {
                    carrito.get(i).setStock(carrito.get(i).getStock() + shoeDTO.getStock());
                }
                value.append(valueStock).append(",").append(shoe.getZapStock()-carrito.get(i).getStock());
                i = carrito.size();
                flag = false;
            }
        }
        if (flag) {
            carrito.add(shoeDTO);
            value.append(valueStock).append(",").append("add");
        }
        return value.toString();
    }

    public Integer getCodePerson() {
        return codePerson;
    }

    public void setCodePerson(Integer codePerson) {
        this.codePerson = codePerson;
    }

    public void restartCarrito(){
        codePerson = null;
        carrito = new ArrayList<>();
    }
}
