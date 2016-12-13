package com.example.ongteckwu.iotproj.modules;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ongteckwu on 12/12/16.
 */
public class ModulesList {
    public static List<CategoryType> cat = new ArrayList<>();

    // data names (with corresponding types)
    public static List<ModType> A = new ArrayList<>();
    public static List<ModType> B = new ArrayList<>();
    public static List<ModType> C = new ArrayList<>();

    public static List<ModType> door = new ArrayList<>();

    static {
        cat.add(new CategoryType("Door"));
        cat.add(new CategoryType("Data"));

        A.add(new DataModType.TemperatureModType());
        A.add(new DataModType.RainModType());
        A.add(new DataModType.SoilHumidityModType());
        A.add(new DataModType.InfraRedModType());
        A.add(new DataModType.UltraSonicModType());

        B.add(new DataModType.HumidityModType());
        B.add(new DataModType.TemperatureModType());

        C.add(new DataModType.PIRModType());

        door.add(new ModType("Door"));
    }
}




