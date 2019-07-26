package com.tiutiunnyk.cuteanimals.data.enums;

public enum AnimalTypes {

    TYPE_DOG("dog"),
    TYPE_CAT("cat");

    private final String name;

    AnimalTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
