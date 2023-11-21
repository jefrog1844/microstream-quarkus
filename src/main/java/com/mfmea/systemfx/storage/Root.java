package com.mfmea.systemfx.storage;

import java.util.ArrayList;
import java.util.List;

import com.mfmea.systemfx.business.dfmea.entity.Dfmea;

import one.microstream.integrations.quarkus.types.Storage;

@Storage
public class Root {

    private final List<Dfmea> dfmeas = new ArrayList<>();

    public Root() {
    }

    public List<Dfmea> getDfmeas() {
        return new ArrayList<>(dfmeas);
    }

    public void addDfmea(Dfmea dfmea) {
        dfmeas.add(dfmea);
    }

    public void removeDfmea(Dfmea dfmea) {
        dfmeas.remove(dfmea);
    }

}
