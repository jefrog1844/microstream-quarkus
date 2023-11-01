package com.mfmea.systemfx.business.dfmea.entity;

import java.time.LocalDate;

public class CreateDfmea {

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

    public CreateDfmea() {}

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public String getOriginated() {
        return originated;
    }

    public void setOriginated(String originated) {
        this.originated = originated;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getSubSystem() {
        return subSystem;
    }

    public void setSubSystem(String subSystem) {
        this.subSystem = subSystem;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public FmeaStandard getFmeaStandard() {
        return fmeaStandard;
    }

    public void setFmeaStandard(FmeaStandard fmeaStandard) {
        this.fmeaStandard = fmeaStandard;
    }
}
