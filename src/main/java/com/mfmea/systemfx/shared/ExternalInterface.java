package com.mfmea.systemfx.shared;

import java.util.ArrayList;
import java.util.List;

import com.mfmea.systemfx.business.hardware.entity.Hardware;

public class ExternalInterface extends BusinessObject {
    private NoiseFactor from;
    private Hardware to;
    private InterfaceType interfaceType;
    private List<Function> functions = new ArrayList<>();

    public NoiseFactor getFrom() {
        return from;
    }

    public void setFrom(NoiseFactor from) {
        this.from = from;
    }

    public Hardware getTo() {
        return to;
    }

    public void setTo(Hardware to) {
        this.to = to;
    }

    public InterfaceType getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(InterfaceType interfaceType) {
        this.interfaceType = interfaceType;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }

}
