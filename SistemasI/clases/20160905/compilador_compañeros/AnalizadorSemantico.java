package compilar;

public class AnalizadorSemantico {

    private final IndicadorDeErrores indicadorDeErrores;
    private final IdentificadorBean[] tabla;

    public AnalizadorSemantico(IndicadorDeErrores indicadorDeErrores) {
        this.indicadorDeErrores = indicadorDeErrores;
        this.tabla = new IdentificadorBean[Constantes.MAX_CANT_IDENT];
    }

}
