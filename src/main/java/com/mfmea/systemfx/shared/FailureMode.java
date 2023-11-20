package com.mfmea.systemfx.shared;

public class FailureMode extends BusinessObject {
    private Cause cause;
    private Effect effect;
    private FailureModeType failureModeType;

    public Cause getCause() {
        return cause;
    }

    public void setCause(Cause cause) {
        this.cause = cause;
    }

    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public FailureModeType getFailureModeType() {
        return failureModeType;
    }

    public void setFailureModeType(FailureModeType failureModeType) {
        this.failureModeType = failureModeType;
    }

}
