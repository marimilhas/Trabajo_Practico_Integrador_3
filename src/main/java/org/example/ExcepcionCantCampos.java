package org.example;
public class ExcepcionCantCampos extends Exception{
    public ExcepcionCantCampos(int indice){
        super("El número de campos en la línea " + (indice + 1) + " es incorrecto! \nModífique su archivo y " +
                "reinicie el programa.\n");
    }
}
