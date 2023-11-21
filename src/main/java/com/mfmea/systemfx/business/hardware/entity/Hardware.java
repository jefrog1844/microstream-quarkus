package com.mfmea.systemfx.business.hardware.entity;

import java.util.ArrayList;
import java.util.List;

import com.mfmea.systemfx.shared.BusinessObject;
import com.mfmea.systemfx.shared.ExternalInterface;
import com.mfmea.systemfx.shared.HardwareType;
import com.mfmea.systemfx.shared.IdealResponse;
import com.mfmea.systemfx.shared.InternalInterface;
import com.mfmea.systemfx.shared.NoiseFactor;

public class Hardware extends BusinessObject {
    private List<NoiseFactor> noiseFactors = new ArrayList<>();
    private List<InternalInterface> internalInterfaces = new ArrayList<>();
    private List<ExternalInterface> externalInterfaces = new ArrayList<>();
    private List<IdealResponse> idealResponses = new ArrayList<>();
    private HardwareType hardwareType;
    private List<Hardware> hardware = new ArrayList<>();

    public List<NoiseFactor> getNoiseFactors() {
        return noiseFactors;
    }

    public void setNoiseFactors(List<NoiseFactor> noiseFactors) {
        this.noiseFactors = noiseFactors;
    }

    public List<InternalInterface> getInternalInterfaces() {
        return internalInterfaces;
    }

    public void setInternalInterfaces(List<InternalInterface> internalInterfaces) {
        this.internalInterfaces = internalInterfaces;
    }

    public List<ExternalInterface> getExternalInterfaces() {
        return externalInterfaces;
    }

    public void setExternalInterfaces(List<ExternalInterface> externalInterfaces) {
        this.externalInterfaces = externalInterfaces;
    }

    public List<IdealResponse> getIdealResponses() {
        return idealResponses;
    }

    public void setIdealResponses(List<IdealResponse> idealResponses) {
        this.idealResponses = idealResponses;
    }

    public HardwareType getHardwareType() {
        return hardwareType;
    }

    public void setHardwareType(HardwareType hardwareType) {
        this.hardwareType = hardwareType;
    }

    public List<Hardware> getHardware() {
        return hardware;
    }

    public void setHardware(List<Hardware> hardware) {
        this.hardware = hardware;
    }

}
