package de._212th.advancementplugin.core;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.Properties;

@AllArgsConstructor
public final class AdvancementLoader {

    private Properties properties;

    public Map<String, String> loadAdvancements() {
        Map<String, String> advancementMap = Maps.newHashMap();
        for (Map.Entry<Object, Object> entry: this.properties.entrySet()) {
            advancementMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return advancementMap;
    }

}
