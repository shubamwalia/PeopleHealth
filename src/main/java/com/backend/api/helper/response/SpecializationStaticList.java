package com.backend.api.helper.response;

public enum SpecializationStaticList {
    EYE("Eye"),
    ORTHOPEDIC("Orthopedic"),
    PAEDIATRIC("Paediatric"),
    MEDICINES("Medicines"),
    IVF("Ivf"),
    MATERNITY("Maternity");

    String name;

    SpecializationStaticList(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
