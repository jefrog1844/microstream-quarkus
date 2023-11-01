package com.mfmea.systemfx.shared;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import one.microstream.reflect.ClassLoaderProvider;
import one.microstream.storage.embedded.configuration.types.EmbeddedStorageConfiguration;
import one.microstream.storage.embedded.types.EmbeddedStorageFoundation;
import one.microstream.storage.restservice.types.StorageRestService;
import one.microstream.storage.restservice.types.StorageRestServiceResolver;
import one.microstream.storage.types.StorageManager;

@ApplicationScoped
public class DataConfiguration {
    private static final System.Logger LOGGER = System.getLogger(DataConfiguration.class.getName());

    @Inject
    @ConfigProperty(name = "one.microstream.config")
    String microStreamConfiguration;

    @Inject
    DataInit dataInit;

    @Produces
    @ApplicationScoped
    public StorageManager defineStorageManager() {

        LOGGER.log(System.Logger.Level.INFO, "(From the App) Additional configuration on the DataConfiguration");

        EmbeddedStorageFoundation<?> embeddedStorageFoundation = embeddedStorageFoundation();
        // do additional configuration
        /*
         * storageFoundation.onConnectionFoundation(BinaryHandlersJDK8::
         * registerJDK8TypeHandlers);
         * storageFoundation.onConnectionFoundation(f -> f.registerCustomTypeHandler());
         * storageFoundation.onConnectionFoundation(f -> f
         * .getCustomTypeHandlerRegistry()
         * .registerLegacyTypeHandler(
         * new LegacyTypeHandlerBook()
         * )
         * );
         * 
         */

        // Required when using Quarkus
        embeddedStorageFoundation.onConnectionFoundation(cf -> cf.setClassLoaderProvider(ClassLoaderProvider.New(
                Thread.currentThread()
                        .getContextClassLoader())));

        StorageManager storageManager = embeddedStorageFoundation.start();

        // Check Root available within StorageManager
        Root root = (Root) storageManager.root();
        boolean initRequired = false;
        if (root == null) {
            root = new Root();
            initRequired = true;
        }
        // Prep Root
        root.setStorageManager(storageManager);

        // Init 'database' with some data
        if (initRequired) {
            dataInit.init(root, storageManager);
            storageManager.setRoot(root);
            storageManager.storeRoot();
        }

        // create the REST service
        StorageRestService service = StorageRestServiceResolver.resolve(storageManager);

        // and start it
        service.start();

        return storageManager;
    }

    private EmbeddedStorageFoundation<?> embeddedStorageFoundation() {
        return EmbeddedStorageConfiguration.load(microStreamConfiguration)
                .createEmbeddedStorageFoundation();

    }

    public void dispose(@Disposes StorageManager manager) {

        manager.close();
    }
}
