package com.example.ongteckwu.iotproj.modules;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ongteckwu on 12/12/16.
 */
public class ModulesList {
    public static List<CategoryType> cat = new ArrayList<>();

    // data names (with corresponding types)
    public static List<ModType> data = new ArrayList<>();

    public static List<ModType> door = new ArrayList<>();

    static {
        cat.add(new CategoryType("Door"));
        cat.add(new CategoryType("Data"));

        data.add(new DataModType.TemperatureModType());
        data.add(new DataModType.RainModType());
        data.add(new DataModType.SoilHumidityModType());
        data.add(new DataModType.InfraRedModType());
        data.add(new DataModType.UltraSonicModType());
        data.add(new DataModType.HumidityModType());
        data.add(new DataModType.TemperatureModType());
        data.add(new DataModType.PIRModType());
        door.add(new ModType("Door"));
    }
}




