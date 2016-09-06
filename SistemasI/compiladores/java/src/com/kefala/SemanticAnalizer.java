package com.kefala;

/**
 * Created by kefala on 05/09/16.
 */
public class SemanticAnalizer {
    private final ErrorIndicator errIndicator;
    private final HeadIdentifier[] tabla;

    public SemanticAnalizer(ErrorIndicator errIndicator) {
        this.errIndicator = errIndicator;
        this.tabla = new HeadIdentifier[Constants.MAX_CANT_IDENT];
    }

}
