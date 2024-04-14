package me.lunatic.lunaticlibfabric;

public class LunaticLib {

    public static LunaticLib INSTANCE;
    public static String MOD_ID;

    public LunaticLib(String modId) {
        INSTANCE = this;
        MOD_ID = modId;
    }
}
