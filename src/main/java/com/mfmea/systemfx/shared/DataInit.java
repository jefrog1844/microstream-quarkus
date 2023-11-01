package com.mfmea.systemfx.shared;

import com.mfmea.systemfx.business.dfmea.entity.Dfmea;
import com.mfmea.systemfx.business.dfmea.entity.FmeaStandard;

import jakarta.enterprise.context.ApplicationScoped;
import one.microstream.storage.types.StorageManager;

@ApplicationScoped
public class DataInit {
    private static final System.Logger LOGGER = System.getLogger(DataInit.class.getName());

    public void init(Root root, StorageManager storageManager) {
        LOGGER.log(System.Logger.Level.INFO, "(From the App) Additional configuration on the DataInit");
        // Dfmea dfmea = new Dfmea(2, "2023-10-31", null, "Name 1", "Program 1",
        // "description 1", "System 1", "", "", FmeaStandard.AIAG);
        // root.addDfmea(dfmea);
    }

}
