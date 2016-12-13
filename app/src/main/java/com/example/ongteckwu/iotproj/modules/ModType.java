package com.example.ongteckwu.iotproj.modules;

import java.io.Serializable;

/**
 * Created by ongteckwu on 12/12/16.
 */
public class ModType implements Serializable {
    private String name;
    private String symbol;
    private SymbolPosition symPos;

    public enum SymbolPosition {
        LEFT, RIGHT
    }

    public ModType(String name) {
        this.name = name;
        this.symbol = "NIL";
        this.symPos = SymbolPosition.RIGHT;
    }

    public ModType(String name, String symbol, SymbolPosition symPos) {
        this.name = name;
        this.symbol = symbol;
        this.symPos = symPos;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public SymbolPosition getSymPos() {
        return symPos;
    }
}




