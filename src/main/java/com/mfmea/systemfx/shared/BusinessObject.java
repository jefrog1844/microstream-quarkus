package com.mfmea.systemfx.shared;

import java.util.UUID;

public abstract class BusinessObject {
    private String id;

    public BusinessObject() {
        this.id = UUID.randomUUID().toString();
    }
    
    public String getId() {
        return id;
    }
}
