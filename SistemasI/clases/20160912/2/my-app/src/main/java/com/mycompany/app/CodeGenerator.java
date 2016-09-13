package com.mycompany.app;

import java.io.*;

/**
 * Created by kefala on 05/09/16.
 */
public class CodeGenerator {
    private final byte memory[]; 
    private int maxMemory;
    private final String name;
    private DataOutputStream bwe;
    private final ErrorIndicator errorIndicator;

    public CodeGenerator(String nomArch, ErrorIndicator errorIndicator) {
        this.memory = new byte[Constants.MAX_TAM_MEMORIA];
        this.errorIndicator = errorIndicator;
        name = (nomArch.indexOf('.') == -1 ? nomArch : nomArch.substring(0, nomArch.lastIndexOf('.')));
        try {
            bwe = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(name)));
        } catch (FileNotFoundException ex) {
            errorIndicator.show(25, ex.getMessage());
            System.exit(1);
        }

        cargarBloqueFijo();
        // this.topeMemoria = ;
    }

    public void volcar() {
        for (int i = 0; i < maxMemory; i++) {
            try {
                bwe.writeByte(memory[i]);
            } catch (IOException ex) {
                errorIndicator.show(25, ex.getMessage());
                System.exit(1);
            }
        }
        try {
            bwe.close();
        } catch (IOException ex) {
            errorIndicator.show(25, ex.getMessage());
            System.exit(1);
        }
    }

    public void borrar() {
        try {
            bwe.close();
            File file = new File(name);
            System.gc();
            file.delete();
        } catch (IOException ex) {
            errorIndicator.show(25, ex.getMessage());
            System.exit(1);
        }
    }

    private void cargarBloqueFijo() {
    }
}
