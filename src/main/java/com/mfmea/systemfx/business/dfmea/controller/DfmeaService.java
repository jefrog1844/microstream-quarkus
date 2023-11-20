package com.mfmea.systemfx.business.dfmea.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.mfmea.systemfx.business.dfmea.entity.Dfmea;
import com.mfmea.systemfx.business.dfmea.entity.DfmeaAlreadyExistsException;
import com.mfmea.systemfx.business.dfmea.entity.DfmeaDoesNotExistException;
import com.mfmea.systemfx.shared.AbstractService;

import jakarta.enterprise.context.ApplicationScoped;
import one.microstream.concurrency.XThreads;

@ApplicationScoped
public class DfmeaService extends AbstractService<Dfmea> {

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

            return root.addDfmea(dfmea);
        });
    }

    public void removeById(String id) {
        Objects.requireNonNull(id, "id is required");

        XThreads.executeSynchronized(() -> {
            Optional<Dfmea> byId = getById(id);

            if (byId.isEmpty()) {
                throw new DfmeaDoesNotExistException();
            }

            byId.ifPresent(theDfmea -> root.removeDfmea(theDfmea));

            return null;
        });
    }

}
