package com.mfmea.systemfx.business.dfmea.entity;

import com.mfmea.systemfx.shared.BusinessObject;

public class Dfmea extends BusinessObject {
    private int revision;
    private String originated;
    private String lastUpdated;
    private String name;
    private String program;
    private String description;
    private boolean active;
    private boolean deleted;
    private String system;
    private String subSystem;
    private String component;
    private FmeaStandard fmeaStandard;

    public Dfmea(int revision, String originated, String lastUpdated, String name, String program,
                 String description, String system, String subSystem,
                 String component, FmeaStandard fmeaStandard) {

        super();
        this.revision = revision;
        this.originated = originated;
        this.lastUpdated = lastUpdated;
        this.name = name;
        this.program = program;
        this.description = description;
        this.active = active;
        this.deleted = deleted;
        this.system = system;
        this.subSystem = subSystem;
        this.component = component;
        this.fmeaStandard = fmeaStandard;
    }

    public int getRevision() {
        return revision;
    }

    public String getOriginated() {
        return originated;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public String getName() {
        return name;
    }

    public String getProgram() {
        return program;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public String getSystem() {
        return system;
    }

    public String getSubSystem() {
        return subSystem;
    }

    public String getComponent() {
        return component;
    }

    public FmeaStandard getFmeaStandard() {
        return fmeaStandard;
    }

}
