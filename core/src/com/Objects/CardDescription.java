package com.Objects;

import java.util.List;

public class CardDescription {
    private String description;
    private List<Integer> properties;

    private Effect effect;

    public CardDescription(String description, List<Integer> properties, Effect effect) {
        this.description = description;
        this.properties = properties;
        this.effect = effect;
    }

    public String getDescription() {
        return description;
    }

    public List<Integer> getProperties() {
        return properties;
    }

    public Effect getEffect() {
        return effect;
    }

    @Override
    public String toString() {
        return description;
    }
}
