package com.mfmea.systemfx.business.dfmea.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

import com.mfmea.systemfx.business.dfmea.entity.Dfmea;
import com.mfmea.systemfx.business.dfmea.entity.DfmeaAlreadyExistsException;
import com.mfmea.systemfx.business.dfmea.entity.DfmeaDoesNotExistException;
import com.mfmea.systemfx.storage.Root;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import one.microstream.concurrency.XThreads;
import one.microstream.storage.types.StorageManager;

@ApplicationScoped
public class DfmeaStorage {

    @Inject
    Root root;

    @Inject
    StorageManager storageManager;

    public List<Dfmea> getAll() {
        return root.getDfmeas();
    }

    public Optional<Dfmea> getById(String id) {
        Objects.requireNonNull(id, "id is required");

        return XThreads.executeSynchronized(() -> root.getDfmeas()
                .stream()
                .filter(isIdEquals(id))
                .limit(1)
                .findFirst());
    }

    public Optional<Dfmea> findByName(String idNumber) {
        Objects.requireNonNull(idNumber, "name is required");

        return XThreads.executeSynchronized(() -> root.getDfmeas()
                .stream()
                .filter(d -> d.getIdNumber().equals(idNumber))
                .findFirst());
    }

    public Dfmea create(Dfmea dfmea) {
        Objects.requireNonNull(dfmea, "dfmea is required");

        return XThreads.executeSynchronized(() -> {
            Optional<Dfmea> byidNumber = findByName(dfmea.getIdNumber());

            if (byidNumber.isPresent()) {
                throw new DfmeaAlreadyExistsException();
            }
            dfmea.setId(UUID.randomUUID().toString());
            root.addDfmea(dfmea);
            storageManager.store(root.getDfmeas());
            return dfmea;
        });
    }

    public void removeById(String id) {
        Objects.requireNonNull(id, "id is required");

        XThreads.executeSynchronized(() -> {
            Optional<Dfmea> byId = getById(id);

            if (byId.isEmpty()) {
                throw new DfmeaDoesNotExistException();
            }

            byId.ifPresent(theDfmea -> {
                root.removeDfmea(theDfmea);
                storageManager.store(root.getDfmeas());
            });

            return null;
        });
    }

    private Predicate<Dfmea> isIdEquals(final String id) {
        return o -> o.getId().equals(id);
    }
}
