package de._212th.advancementplugin.core;

import com.google.common.collect.Maps;

import java.util.Map;

public final class Advancements {

    public enum AdvancementType {
        ACHIEVEMENT,
        GOAL,
        CHALLENGE
    }

    private static Map<AdvancementType, Map<String, String>> advancementMap = Maps.newHashMap();

    public static void initialize(AdvancementType type, AdvancementLoader loader) {
        advancementMap.put(type, loader.loadAdvancements());
    }

    public static AdvancementType getAdvancementType(String key) {
        if (containsKey(AdvancementType.ACHIEVEMENT, key)) return AdvancementType.ACHIEVEMENT;
        if (containsKey(AdvancementType.GOAL, key)) return AdvancementType.GOAL;
        if (containsKey(AdvancementType.CHALLENGE, key)) return AdvancementType.CHALLENGE;
        throw new IllegalStateException("Key does not exist!");
    }

    public static String getTitle(String key) {
        return advancementMap.get(getAdvancementType(key)).get(key);
    }

    private static boolean containsKey(AdvancementType type, String key) {
        return advancementMap.get(type).containsKey(key);
    }

}
