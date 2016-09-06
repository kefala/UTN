package com.kefala;

import java.io.IOException;

/**
 * Created by kefala on 05/09/16.
 */
public class SyntacticAnalizer {
    private final LexicalAnalizer aLex;
    private final SemanticAnalizer aSem;
    private final CodeGenerator codGen;
    private final ErrorIndicator errInd;

    public SyntacticAnalizer(LexicalAnalizer aLex, SemanticAnalizer aSem, CodeGenerator codGen, ErrorIndicator errInd) {
        this.aLex = aLex;
        this.aSem = aSem;
        this.codGen = codGen;
        this.errInd = errInd;
    }

    public void analyze() throws IOException {
        aLex.escanear();
        programa();
        codGen.volcar();
    }

    //atencion!!! No se puede llamar desde afuera, es privado!!
    //Hay que aplicar 7 reglas (pag 12 del pdf)
    private void programa() throws IOException {
        bloque();
        if (aLex.getS() == Terminal.PUNTO) {
            aLex.escanear();
        } else {
            errInd.show(1, null);
        }
        System.out.println("Compilacion exitosa");
    }

    private void bloque() throws IOException {
        if (aLex.getS() == Terminal.CONST) {
            aLex.escanear();
            if (aLex.getS() == Terminal.IDENTIFICADOR) {
                aLex.escanear();
            } else {
                errInd.show(2, null);
            }
            if (aLex.getS() == Terminal.IGUAL) {
                aLex.escanear();
            } else {
                errInd.show(3, null);
            }
            if (aLex.getS() == Terminal.NUMERO) {
                aLex.escanear();
            } else {
                errInd.show(4, null);
            }
            while (aLex.getS() == Terminal.COMA) {
                aLex.escanear();
                if (aLex.getS() == Terminal.IDENTIFICADOR) {
                    aLex.escanear();
                } else {
                    errInd.show(2, null);
                }
                if (aLex.getS() == Terminal.IGUAL) {
                    aLex.escanear();
                } else {
                    errInd.show(3, null);
                }
                if (aLex.getS() == Terminal.NUMERO) {
                    aLex.escanear();
                } else {
                    errInd.show(4, null);
                }
            }
            if (aLex.getS() == Terminal.PUNTO_Y_COMA) {
                aLex.escanear();
            } else {
                errInd.show(5, null);
            }
        }

        if (aLex.getS() == Terminal.VAR) {
            aLex.escanear();
            if (aLex.getS() == Terminal.IDENTIFICADOR){
                aLex.escanear();
            }
            else{
                errInd.show(2, null);
            }
            while (aLex.getS() == Terminal.COMA){
                aLex.escanear();
                if (aLex.getS() == Terminal.IDENTIFICADOR){
                    aLex.escanear();
                }
                else{
                    errInd.show(2, null);
                }
            }
            if (aLex.getS() == Terminal.PUNTO_Y_COMA){
                aLex.escanear();
            }
            else{
                errInd.show(5, null);
            }
        }

        while (aLex.getS() == Terminal.PROCEDURE) {
            aLex.escanear();
            if (aLex.getS() == Terminal.IDENTIFICADOR){
                aLex.escanear();
            }
            else{
                errInd.show(2, null);
            }
            if (aLex.getS() == Terminal.PUNTO_Y_COMA){
                aLex.escanear();
            }
            else{
                errInd.show(5, null);
            }
            bloque();
            if(aLex.getS()==Terminal.PUNTO_Y_COMA){
                aLex.escanear();
            }
            else{
                errInd.show(5, null);
            }
        }
        proposicion();

    }

    private void proposicion() throws IOException {
        if(aLex.getS()==Terminal.IDENTIFICADOR){
            aLex.escanear();
        }
        else{
            errInd.show(2, null);
        }
        if(aLex.getS()==Terminal.ASIGNACION){
            aLex.escanear();
        }
        else{
            errInd.show(2, null);
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
