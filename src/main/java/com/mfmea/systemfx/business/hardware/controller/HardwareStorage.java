package com.mfmea.systemfx.business.hardware.controller;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

import com.mfmea.systemfx.business.dfmea.controller.DfmeaStorage;
import com.mfmea.systemfx.business.dfmea.entity.Dfmea;
import com.mfmea.systemfx.business.hardware.entity.Hardware;
import com.mfmea.systemfx.storage.Root;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import one.microstream.concurrency.XThreads;
import one.microstream.storage.types.StorageManager;

@ApplicationScoped
public class HardwareStorage {

    @Inject
    Root root;

    @Inject
    StorageManager storageManager;

    @Inject
    DfmeaStorage dfmeaStorage;

    public List<Hardware> getHardwareByDfmeaId(String dfmeaId) {
        Objects.requireNonNull(dfmeaId, "id is required");

        Optional<Dfmea> optional = dfmeaStorage.getById(dfmeaId);

        return XThreads.executeSynchronized(() -> {
            return optional.isPresent() ? optional.get().getHardware() : Collections.emptyList();
        });

    }

    public Optional<Hardware> getById(String id, String dfmeaId) {
        Objects.requireNonNull(id, "id is required");
        Dfmea dfmea = dfmeaStorage.getById(dfmeaId).orElseThrow();
        return XThreads.executeSynchronized(() -> {

            return dfmea.getHardware().stream().filter(isIdEquals(id)).limit(1).findFirst();

        });
    }

    public Hardware create(Hardware hardware, String dfmeaId) {
        Objects.requireNonNull(hardware, "hardware is required");
        Dfmea dfmea = dfmeaStorage.getById(dfmeaId).orElseThrow();

        return XThreads.executeSynchronized(() -> {
            hardware.setId(UUID.randomUUID().toString());
            dfmea.getHardware().add(hardware);
            storageManager.store(dfmea.getHardware());
            return hardware;
        });
    }

    public void removeById(String id, String dfmeaId) {
        Hardware hardware = getById(id, dfmeaId).orElseThrow();
        Dfmea dfmea = dfmeaStorage.getById(dfmeaId).orElseThrow();
        XThreads.executeSynchronized(() -> {
            // first remove from dfmea
            dfmea.getHardware().remove(hardware);
            storageManager.store(dfmea.getHardware());

            // now remove from its parent
            List<Hardware> hardwareList = getHardwareByDfmeaId(dfmeaId);
            for (Hardware h : hardwareList) {
                boolean removed = h.getHardware().remove(hardware);
                if (removed) {
                    storageManager.store(h.getHardware());
                }
            }
            return null;
        });
    }

    private Predicate<Hardware> isIdEquals(final String id) {
        return o -> o.getId().equals(id);
    }

}
