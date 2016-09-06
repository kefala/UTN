package compilar;

import java.io.IOException;

public class AnalizadorSintactico {

    private final AnalizadorLexico aLex;
    private final AnalizadorSemantico aSem;
    private final GeneradorDeCodigo genCod;
    private final IndicadorDeErrores indicadorDeErrores;

    public AnalizadorSintactico(AnalizadorLexico aLex, AnalizadorSemantico aSem, GeneradorDeCodigo genCod, IndicadorDeErrores indicadorDeErrores) {
        this.aLex = aLex;
        this.aSem = aSem;
        this.genCod = genCod;
        this.indicadorDeErrores = indicadorDeErrores;
    }

    public void analizar() throws IOException {
        aLex.escanear();
        programa();
        genCod.volcar();
    }

    //atencion!!! No se puede llamar desde afuera, es privado!!
    //Hay que aplicar 7 reglas (pag 12 del pdf)
    private void programa() throws IOException {
        bloque();
        if (aLex.getS() == Terminal.PUNTO) {
            aLex.escanear();
        } else {
            indicadorDeErrores.mostrar(1, null);
        }
        System.out.println("Compilacion exitosa");
    }

    private void bloque() throws IOException {
        if (aLex.getS() == Terminal.CONST) {
            aLex.escanear();
            if (aLex.getS() == Terminal.IDENTIFICADOR) {
                aLex.escanear();
            } else {
                indicadorDeErrores.mostrar(2, null);
            }
            if (aLex.getS() == Terminal.IGUAL) {
                aLex.escanear();
            } else {
                indicadorDeErrores.mostrar(3, null);
            }
            if (aLex.getS() == Terminal.NUMERO) {
                aLex.escanear();
            } else {
                indicadorDeErrores.mostrar(4, null);
            }
            while (aLex.getS() == Terminal.COMA) {
                aLex.escanear();
                if (aLex.getS() == Terminal.IDENTIFICADOR) {
                    aLex.escanear();
                } else {
                    indicadorDeErrores.mostrar(2, null);
                }
                if (aLex.getS() == Terminal.IGUAL) {
                    aLex.escanear();
                } else {
                    indicadorDeErrores.mostrar(3, null);
                }
                if (aLex.getS() == Terminal.NUMERO) {
                    aLex.escanear();
                } else {
                    indicadorDeErrores.mostrar(4, null);
                }
            }
            if (aLex.getS() == Terminal.PUNTO_Y_COMA) {
                aLex.escanear();
            } else {
                indicadorDeErrores.mostrar(5, null);
            }
        }

        if (aLex.getS() == Terminal.VAR) {
            aLex.escanear();
            if (aLex.getS() == Terminal.IDENTIFICADOR){
                aLex.escanear();
            }
            else{
                indicadorDeErrores.mostrar(2, null);
            }
            while (aLex.getS() == Terminal.COMA){
                aLex.escanear();
                if (aLex.getS() == Terminal.IDENTIFICADOR){
                    aLex.escanear();
                }
                else{
                    indicadorDeErrores.mostrar(2, null);
                }
            }
            if (aLex.getS() == Terminal.PUNTO_Y_COMA){
                aLex.escanear();
            }
            else{
                indicadorDeErrores.mostrar(5, null);
            }
        }

        while (aLex.getS() == Terminal.PROCEDURE) {
            aLex.escanear();
            if (aLex.getS() == Terminal.IDENTIFICADOR){
                aLex.escanear();
            }
            else{
                indicadorDeErrores.mostrar(2, null);
            }
            if (aLex.getS() == Terminal.PUNTO_Y_COMA){
                aLex.escanear();
            }
            else{
                indicadorDeErrores.mostrar(5, null);
            }
            bloque();
            if(aLex.getS()==Terminal.PUNTO_Y_COMA){
                aLex.escanear();
            }
            else{
                indicadorDeErrores.mostrar(5, null);
            }
        }
        proposicion();

    }

    private void proposicion() throws IOException {
        if(aLex.getS()==Terminal.IDENTIFICADOR){
            aLex.escanear();
        }
        else{
            indicadorDeErrores.mostrar(2, null);
        }
        if(aLex.getS()==Terminal.ASIGNACION){
            aLex.escanear();
        }
        else{
            indicadorDeErrores.mostrar(2, null);
        }
        
    }

    private void condicion() throws IOException {
    }

    private void expresion() throws IOException {
    }

    private void termino() throws IOException {
    }

    private void factor() throws IOException {
    }

}
