package net.threader.odinclient.manager;

import net.threader.odinclient.feature.AbstractFeature;

import java.util.HashMap;
import java.util.Map;

public class FeatureManager {
    private Map<String, AbstractFeature> loadedFeatures = new HashMap<>();

    public <T extends AbstractFeature> void loadAll(Class<T>... features) {

    }

    public <T extends AbstractFeature> T getFeature(Class<T> clazz) {
        return (T) loadedFeatures.values().stream().filter(x -> x.getClass().equals(clazz)).findFirst().get();
    }
}
