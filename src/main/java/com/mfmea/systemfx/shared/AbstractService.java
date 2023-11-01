package com.mfmea.systemfx.shared;

import java.util.function.Predicate;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import one.microstream.storage.types.StorageManager;

public abstract class AbstractService<T extends BusinessObject> {

    @Inject
    protected StorageManager storageManager;

    protected Root root;

    @PostConstruct
    void init() {
        root = (Root) storageManager.root();
    }

    protected Predicate<T> isIdEquals(final String id) {
        return o -> o.getId().equals(id);
    }

}
