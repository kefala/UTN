package compilar;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class GeneradorDeCodigo {

    private final byte memoria[];
    private int topeMemoria;
    private final String nombre;
    private DataOutputStream bwe;
    private final IndicadorDeErrores indicadorDeErrores;

    public GeneradorDeCodigo(String nomArch, IndicadorDeErrores indicadorDeErrores) {
        this.memoria = new byte[Constantes.MAX_TAM_MEMORIA];
        this.indicadorDeErrores = indicadorDeErrores;
        nombre = (nomArch.indexOf('.') == -1 ? nomArch : nomArch.substring(0, nomArch.lastIndexOf('.'))) + ".exe";
        try {
            bwe = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(nombre)));
        } catch (FileNotFoundException ex) {
            indicadorDeErrores.mostrar(25, ex.getMessage());
            System.exit(1);
        }

        cargarBloqueFijo();
        // this.topeMemoria = ;
    }

    public void volcar() {
        for (int i = 0; i < topeMemoria; i++) {
            try {
                bwe.writeByte(memoria[i]);
            } catch (IOException ex) {
                indicadorDeErrores.mostrar(25, ex.getMessage());
                System.exit(1);
            }
        }
        try {
            bwe.close();
        } catch (IOException ex) {
            indicadorDeErrores.mostrar(25, ex.getMessage());
            System.exit(1);
        }
    }

    public void borrar() {
        try {
            bwe.close();
            File file = new File(nombre);
            System.gc();
            file.delete();
        } catch (IOException ex) {
            indicadorDeErrores.mostrar(25, ex.getMessage());
            System.exit(1);
        }
    }

    private void cargarBloqueFijo() {
    }
}
