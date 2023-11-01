package com.mfmea.systemfx.shared;

import com.mfmea.systemfx.business.dfmea.entity.Dfmea;
import com.mfmea.systemfx.business.hardware.entity.Hardware;

import jakarta.inject.Inject;
import one.microstream.storage.types.StorageManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Root {

    @Inject
    transient StorageManager storageManager;

    private final List<Dfmea> dfmeas = new ArrayList<>();
    private final Map<String, List<Hardware>> hardwareByDfmeaId = new HashMap<>();

    public void setStorageManager(StorageManager storageManager) {
        this.storageManager = storageManager;
    }

    public List<Dfmea> getDfmeas() {
        return new ArrayList<>(dfmeas);
    }

    public Dfmea addDfmea(Dfmea dfmea) {
        dfmeas.add(dfmea);
        storageManager.store(dfmeas);
        return dfmea;
    }

    public void updateDfmea(Dfmea dfmea) {
        storageManager.store(dfmea);
    }

    public void removeDfmea(Dfmea dfmea) {
        dfmeas.remove(dfmea);
        storageManager.store(dfmeas);
    }

    public List<Hardware> getHardwareByDfmeaId(String dfmeaId) {
        return hardwareByDfmeaId.get(dfmeaId);
    }
}
