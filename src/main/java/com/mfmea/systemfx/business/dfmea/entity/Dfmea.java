package com.mfmea.systemfx.business.dfmea.entity;

import java.time.LocalDate;

import com.mfmea.systemfx.shared.BusinessObject;

public class Dfmea extends BusinessObject {
    private String companyName;
    private String engineeringLocation;
    private String customerName;
    private String modelYearPlatform;
    private String subject;
    private LocalDate startDate;
    private LocalDate revisionDate;
    private String crossFunctionalTeam;
    private String idNumber;
    private String designResponsibility;
    private String confidentialityLevel;
    private String system;
    private String subSystem;
    private String component;
    private FmeaStandard fmeaStandard;
    private String partNumber;
    private int revision;

    public Dfmea() {
    }

    public Dfmea(String companyName, String engineeringLocation, String customerName, String modelYearPlatform,
            String subject, LocalDate startDate, LocalDate revisionDate, String crossFunctionalTeam, String idNumber,
            String designResponsibility, String confidentialityLevel, String system, String subSystem, String component,
            FmeaStandard fmeaStandard, String partNumber, int revision) {
        this.companyName = companyName;
        this.engineeringLocation = engineeringLocation;
        this.customerName = customerName;
        this.modelYearPlatform = modelYearPlatform;
        this.subject = subject;
        this.startDate = startDate;
        this.revisionDate = revisionDate;
        this.crossFunctionalTeam = crossFunctionalTeam;
        this.idNumber = idNumber;
        this.designResponsibility = designResponsibility;
        this.confidentialityLevel = confidentialityLevel;
        this.system = system;
        this.subSystem = subSystem;
        this.component = component;
        this.fmeaStandard = fmeaStandard;
        this.partNumber = partNumber;
        this.revision = revision;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEngineeringLocation() {
        return engineeringLocation;
    }

    public void setEngineeringLocation(String engineeringLocation) {
        this.engineeringLocation = engineeringLocation;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getModelYearPlatform() {
        return modelYearPlatform;
    }

    public void setModelYearPlatform(String modelYearPlatform) {
        this.modelYearPlatform = modelYearPlatform;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(LocalDate revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getCrossFunctionalTeam() {
        return crossFunctionalTeam;
    }

    public void setCrossFunctionalTeam(String crossFunctionalTeam) {
        this.crossFunctionalTeam = crossFunctionalTeam;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getDesignResponsibility() {
        return designResponsibility;
    }

    public void setDesignResponsibility(String designResponsibility) {
        this.designResponsibility = designResponsibility;
    }

    public String getConfidentialityLevel() {
        return confidentialityLevel;
    }

    public void setConfidentialityLevel(String confidentialityLevel) {
        this.confidentialityLevel = confidentialityLevel;
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

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

}
