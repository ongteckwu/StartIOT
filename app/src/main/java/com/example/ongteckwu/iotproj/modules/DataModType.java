package com.example.ongteckwu.iotproj.modules;

/**
 * Created by ongteckwu on 13/12/16.
 */
public class DataModType extends ModType {
    // renderMap<door_module_component_<name>, value>
    public enum dataType {
        A, B, C
    }

    private dataType dt;

    public DataModType(String name) {
        super(name);
    }

    public DataModType(String name, String symbol, SymbolPosition symPos, dataType dt) {
        super(name, symbol, symPos);
        this.dt = dt;
    }

    public dataType getDt() {
        return dt;
    }



    // *** A Data Type Mods ***
    public static class WaterLevelModType extends DataModType {
        public WaterLevelModType() {
            super("Water Level", "°C", ModType.SymbolPosition.RIGHT, dataType.A);
        }
    }

    public static class RainModType extends DataModType {
        public RainModType() {
            super("Rain", "°C", ModType.SymbolPosition.RIGHT, dataType.A);
        }
    }

    public static class SoilHumidityModType extends DataModType {
        public SoilHumidityModType() {
            super("Solid Humidity", "°C", ModType.SymbolPosition.RIGHT, dataType.A);
        }
    }


    public static class InfraRedModType extends DataModType {
        public InfraRedModType() {
            super("Infra-Red", "°C", ModType.SymbolPosition.RIGHT, dataType.A);
        }
    }

    public static class UltraSonicModType extends DataModType {
        public UltraSonicModType() {
            super("Ultrasonic", "°C", ModType.SymbolPosition.RIGHT, dataType.A);
        }
    }

    // *** B Data Type Mods ***
    public static class HumidityModType extends DataModType {
        public HumidityModType() {
            super("Humdity", "°C", ModType.SymbolPosition.RIGHT, dataType.B);
        }
    }

    public static class TemperatureModType extends DataModType {
        public TemperatureModType() {
            super("Temperature", "°C", ModType.SymbolPosition.RIGHT, dataType.B);
        }
    }

    // *** C Data Type Mods ***
    public static class PIRModType extends DataModType {
        public PIRModType() {
            super("PIR", "°C", ModType.SymbolPosition.RIGHT, dataType.C);
        }
    }
}
