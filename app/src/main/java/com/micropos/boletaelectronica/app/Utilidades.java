package com.micropos.boletaelectronica.app;

//Clase para almacenar constantes o métodos que no son considerados necesarios de almacenar en
// una clase independiente
public class Utilidades {

    public static final float TRANSPARENCIA_25 = 0.25f;
    public static final float NO_TRANSPARENCIA = 1f;

    public static String convertirMesAPalabra(String m) {

        switch (m) {
            case "01":
                return "ENERO";

            case "02":
                return "FEBRERO";

            case "03":
                return "MARZO";

            case "04":
                return "ABRIL";

            case "05":
                return "MAYO";

            case "06":
                return "JUNIO";

            case "07":
                return "JULIO";

            case "08":
                return "AGOSTO";

            case "09":
                return "SEPTIEMBRE";

            case "10":
                return "OCTUBRE";

            case "11":
                return "NOVIEMBRE";

            case "12":
                return "DICIEMBRE";
        }
        return "";
    }
}
