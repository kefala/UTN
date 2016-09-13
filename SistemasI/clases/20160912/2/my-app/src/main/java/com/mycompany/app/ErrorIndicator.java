package com.mycompany.app;

/**
 * Created by kefala on 05/09/16.
 */
public class ErrorIndicator {
    private LexicalAnalizer al;

    public ErrorIndicator(LexicalAnalizer al) {
        this.al = al;
    }

    public void show(int cod, String cad) {
        System.err.print("ERROR " + cod + ": (lin: " + al.getYyline() + ", col: " + al.getYycolumn() + "):  ");
        switch (cod) {
            case 1:
                System.err.println("Se esperaba un punto (.)");
                break;
            case 2:
                System.err.println("Se esperaba un identificador.");
                break;
            case 3:
                System.err.println("Se esperaba un igual (=)");
                break;
            case 4:
                System.err.println("Se esperaba un numero");
                break;
            case 5:
                System.err.println("Se esperaba un punto y coma (;)");
                break;
            case 6:
                System.err.println("Se esperaba una asignacion (:=)");
                break;
            case 16:
                System.err.println("Identificador duplicado: " + cad);
                break;
            case 17:
                System.err.println("Identificador no declarado: " + cad);
                break;
            case 21:
                System.err.println("Numero " + cad + " fuera de rango");
                break;
            case 22:
                System.err.println("Identificador " + cad + " demasiado largo");
                break;
            case 23:
                System.err.println("Cadena " + cad + " demasiado larga");
                break;
            case 25:
                System.err.println("Excepcion de E/S! (" + cad + ")");
                break;
        }
        System.exit(1);
    }
}
