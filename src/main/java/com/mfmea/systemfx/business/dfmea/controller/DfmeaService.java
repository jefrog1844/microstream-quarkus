package com.mfmea.systemfx.business.dfmea.controller;

import com.mfmea.systemfx.business.dfmea.entity.CreateDfmea;
import com.mfmea.systemfx.business.dfmea.entity.Dfmea;
import com.mfmea.systemfx.business.dfmea.entity.DfmeaAlreadyExistsException;
import com.mfmea.systemfx.business.dfmea.entity.DfmeaDoesNotExistException;
import com.mfmea.systemfx.shared.AbstractService;
import jakarta.enterprise.context.ApplicationScoped;
import one.microstream.concurrency.XThreads;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public Optional<Dfmea> findByName(String name) {
        Objects.requireNonNull(name, "name is required");

        return XThreads.executeSynchronized(() -> root.getDfmeas()
                .stream()
                .filter(d -> d.getName().equals(name))
                .findFirst());
    }
    public Dfmea create(CreateDfmea dfmea) {
        Objects.requireNonNull(dfmea, "dfmea is required");

        return XThreads.executeSynchronized(() -> {
            Optional<Dfmea> byName = findByName(dfmea.getName());

            if(byName.isPresent()) {
                throw new DfmeaAlreadyExistsException();
            }

            return root.addDfmea(new Dfmea(dfmea.getRevision(),dfmea.getOriginated(),
                    dfmea.getLastUpdated(), dfmea.getName(), dfmea.getProgram(), dfmea.getDescription(),
                    dfmea.getSystem(), dfmea.getSubSystem(), dfmea.getComponent(), dfmea.getFmeaStandard()));
        });
    }

    public void removeById(String id) {
        Objects.requireNonNull(id, "id is required");

        XThreads.executeSynchronized(() -> {
            Optional<Dfmea> byId = getById(id);

            if(byId.isEmpty()) {
                throw new DfmeaDoesNotExistException();
            }

            byId.ifPresent(theDfmea -> root.removeDfmea(theDfmea));

            return null;
        });
    }

    public Dfmea revise(String id, CreateDfmea dfmea) {

        return XThreads.executeSynchronized(() -> {
            Optional<Dfmea> byId = getById(id);

            if(byId.isEmpty()) {
                throw new DfmeaDoesNotExistException();
            }

            Dfmea existing = byId.get();

            int revision = existing.getRevision() + 1;

            // TODO kick off deep copy of parts and functions

            return root.addDfmea(new Dfmea(revision, dfmea.getOriginated(),
                    dfmea.getLastUpdated(), dfmea.getName(), dfmea.getProgram(), dfmea.getDescription(),
                    dfmea.getSystem(), dfmea.getSubSystem(), dfmea.getComponent(), dfmea.getFmeaStandard()));
        });
    }
}
