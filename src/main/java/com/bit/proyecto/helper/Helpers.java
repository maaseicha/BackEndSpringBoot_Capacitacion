package com.bit.proyecto.helper;

import com.bit.proyecto.model.Shoe;
import com.bit.proyecto.dto.ShoeDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Helpers {

    /**
     * transdorma la tercera serie (000000001) de la factura en base al SRI, es el número de la factura en sí, son 9 dígitos.
     * @param numeroFactura
     * @return String con la cantidad de ceros a la izquierda
     */
    public String generarNumeroFactura(int numeroFactura){
        String valorFactura = "";
        if (numeroFactura>=1 && numeroFactura<=9)
            valorFactura = "00000000"+numeroFactura;
        if (numeroFactura>=10 && numeroFactura<=99)
            valorFactura = "0000000"+numeroFactura;
        if (numeroFactura>=100 && numeroFactura<=999)
            valorFactura = "000000"+numeroFactura;
        if (numeroFactura>=1000 && numeroFactura<=9999)
            valorFactura = "00000"+numeroFactura;
        if (numeroFactura>=10000 && numeroFactura<=99999)
            valorFactura = "0000"+numeroFactura;
        if (numeroFactura>=100000 && numeroFactura<=999999)
            valorFactura = "000"+numeroFactura;
        if (numeroFactura>=1000000 && numeroFactura<=9999999)
            valorFactura = "00"+numeroFactura;
        if (numeroFactura>=10000000 && numeroFactura<=99999999)
            valorFactura = "0"+numeroFactura;
        if (numeroFactura>=100000000 && numeroFactura<=999999999)
            valorFactura = ""+numeroFactura;
        return valorFactura;
    }

    /**
     * valida si la cedula ecuatoriana es correcta o incorrecta debe ingresar 10 digitos
     * @param cedula
     * @return verdadero si la cedula es correcta
     */
    public Boolean validarCedula(String cedula){
        boolean cedulaCorrecta;
        if (cedula.length() == 10){
            int tercerDigito = Integer.parseInt(cedula.substring(2,3));
            if (tercerDigito < 6){
                int[] coefValCedula = {2,1,2,1,2,1,2,1,2};
                int verificador = Integer.parseInt(cedula.substring(9,10));
                int suma = 0;
                int digito = 0;
                for (int i=0;i<(cedula.length() - 1);i++){
                    digito = Integer.parseInt(cedula.substring(i,i+1)) * coefValCedula[i];
                    suma += ((digito % 10) + (digito / 10));
                }
                if ((suma % 10 == 0) && (suma % 10 == verificador)){
                    cedulaCorrecta = true;
                }else if((10 - (suma % 10)) == verificador){
                    cedulaCorrecta = true;
                }else {
                    cedulaCorrecta = false;
                }
            }else {
                cedulaCorrecta = false;
            }
        }else {
            cedulaCorrecta = false;
        }
        return cedulaCorrecta;
    }

    /**
     * transforma un numero double en alta precision mediante decimales
     * @param valor -> numero double, a precision
     * @param numeroDecimales numeroDecimal -> entero, decimales de precision
     * @return valor con precision deacuerdo al numeroDecimales
     */
    public double precision(double valor, int numeroDecimales) {
        double parteEntera;
        parteEntera = Math.floor(valor);
        valor=(valor-parteEntera)*Math.pow(10, numeroDecimales);
        valor=Math.round(valor);
        valor=(valor/Math.pow(10, numeroDecimales))+parteEntera;
        return valor;
    }
    public ShoeDTO getDTO(Shoe shoe){
        return new ShoeDTO(shoe.getZapCode(),shoe.getZapName(),shoe.getZapModel(),shoe.getZapGender(),shoe.getZapColor(),shoe.getZapSize(),shoe.getZapStock(),shoe.getZapPrice(),shoe.getZapImage());
    }
    public Shoe getModelShoe(ShoeDTO shoeDTO){
        return new Shoe(shoeDTO.getCode(),shoeDTO.getName(),shoeDTO.getModel(),shoeDTO.getGender(),shoeDTO.getColor(),shoeDTO.getSize(),shoeDTO.getStock(),shoeDTO.getPrice(),shoeDTO.getImage());
    }
}
