package com.example.ongteckwu.iotproj.modules;

/**
 * Created by ongteckwu on 13/12/16.
 */
public class DataModType extends ModType {
    // renderMap<door_module_component_<name>, value>
    public DataModType(String name) {
        super(name);
    }

    public DataModType(String name, String symbol, SymbolPosition symPos) {
        super(name, symbol, symPos);
    }




    // *** A Data Type Mods ***
    public static class WaterLevelModType extends DataModType {
        public WaterLevelModType() {
            super("Water Level", "", ModType.SymbolPosition.RIGHT);
        }
    }

    public static class RainModType extends DataModType {
        public RainModType() {
            super("Rain", "", ModType.SymbolPosition.RIGHT);
        }
    }

    public static class SoilHumidityModType extends DataModType {
        public SoilHumidityModType() {
            super("Solid Humidity", "", ModType.SymbolPosition.RIGHT);
        }
    }


    public static class InfraRedModType extends DataModType {
        public InfraRedModType() {
            super("Infra-Red", "", ModType.SymbolPosition.RIGHT);
        }
    }

    public static class UltraSonicModType extends DataModType {
        public UltraSonicModType() {
            super("Ultrasonic", "", ModType.SymbolPosition.RIGHT);
        }
    }

    public static class HumidityModType extends DataModType {
        public HumidityModType() {
            super("Humidity", "%", ModType.SymbolPosition.RIGHT);
        }
    }

    public static class TemperatureModType extends DataModType {
        public TemperatureModType() {
            super("Temperature", "°C", ModType.SymbolPosition.RIGHT);
        }
    }

    public static class PIRModType extends DataModType {
        public PIRModType() {
            super("PIR", "", ModType.SymbolPosition.RIGHT);
        }
    }

    public static class FlameModType extends DataModType {
        public FlameModType() {
            super("Flame", "", ModType.SymbolPosition.RIGHT);
        }
    }
}
