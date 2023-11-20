package com.mfmea.systemfx.shared;

import java.util.ArrayList;
import java.util.List;

public class Function extends BusinessObject {

    private List<FailureMode> failureModes = new ArrayList<>();

    public List<FailureMode> getFailureModes() {
        return failureModes;
    }

    public void setFailureModes(List<FailureMode> failureModes) {
        this.failureModes = failureModes;
    }

}
