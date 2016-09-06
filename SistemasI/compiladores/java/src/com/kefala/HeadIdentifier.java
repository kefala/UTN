package com.kefala;

import java.io.Serializable;

/**
 * Created by kefala on 05/09/16.
 */
public class HeadIdentifier implements Serializable{

    private String name;
    private Terminal type;
    private int value;

    public HeadIdentifier() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Terminal getType() {
        return type;
    }

    public void setType(Terminal type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
