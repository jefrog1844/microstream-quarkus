package com.mfmea.systemfx.storage;

import java.time.LocalDate;
import java.util.UUID;

import com.mfmea.systemfx.business.dfmea.entity.Dfmea;
import com.mfmea.systemfx.business.dfmea.entity.FmeaStandard;
import com.mfmea.systemfx.business.hardware.entity.Hardware;
import com.mfmea.systemfx.shared.ExternalInterface;
import com.mfmea.systemfx.shared.FailureMode;
import com.mfmea.systemfx.shared.FailureModeType;
import com.mfmea.systemfx.shared.Function;
import com.mfmea.systemfx.shared.HardwareType;
import com.mfmea.systemfx.shared.InterfaceType;
import com.mfmea.systemfx.shared.InternalInterface;
import com.mfmea.systemfx.shared.NoiseFactor;
import com.mfmea.systemfx.shared.NoiseFactorType;

import jakarta.enterprise.context.ApplicationScoped;
import one.microstream.integrations.quarkus.types.config.StorageManagerInitializer;
import one.microstream.storage.restservice.types.StorageRestService;
import one.microstream.storage.restservice.types.StorageRestServiceResolver;
import one.microstream.storage.types.StorageManager;

@ApplicationScoped
public class DataInit implements StorageManagerInitializer {
    private static final System.Logger LOGGER = System.getLogger(DataInit.class.getName());

    @Override
    public void initialize(StorageManager storageManager) {
        LOGGER.log(System.Logger.Level.INFO, "(From the App) Initializing the application...");
        // Check Root available within StorageManager
        Root root = (Root) storageManager.root();

        // Init 'database' with some data
        if (root.getDfmeas().isEmpty()) {
            LOGGER.log(System.Logger.Level.INFO, "(From the App) Add basic data the first time");
            Dfmea initialDfmea = init();
            root.addDfmea(initialDfmea);
            storageManager.store(root.getDfmeas());
        }

        // create the REST service
        StorageRestService service = StorageRestServiceResolver.resolve(storageManager);

        // and start it
        service.start();
    }

    private Dfmea init() {

        Dfmea d = new Dfmea("companyName", "engineering location", "customer name", "model year platform", "subject",
                LocalDate.now(), LocalDate.now(), "FM-001", "jrogers", "", "", "", "", FmeaStandard.AIAG, "", 0);
        d.setId(UUID.randomUUID().toString());

        d.getCrossFunctionalTeam().add("jrogers");

        Hardware exhaust = new Hardware();
        exhaust.setId(UUID.randomUUID().toString());
        exhaust.setSubject("exhaust");
        exhaust.setHardwareType(HardwareType.SYSTEM);
        d.getHardware().add(exhaust);

        Hardware flange = new Hardware();
        flange.setId(UUID.randomUUID().toString());
        flange.setSubject("flange");
        flange.setHardwareType(HardwareType.COMPONENT);
        exhaust.getHardware().add(flange);
        d.getHardware().add(flange);

        Hardware elbow = new Hardware();
        elbow.setId(UUID.randomUUID().toString());
        elbow.setSubject("elbow");
        elbow.setHardwareType(HardwareType.COMPONENT);
        exhaust.getHardware().add(elbow);
        d.getHardware().add(elbow);

        Hardware muffler = new Hardware();
        muffler.setId(UUID.randomUUID().toString());
        muffler.setSubject("muffler");
        muffler.setHardwareType(HardwareType.ASSEMBLY);
        exhaust.getHardware().add(muffler);
        d.getHardware().add(muffler);

        Hardware shell = new Hardware();
        shell.setId(UUID.randomUUID().toString());
        shell.setSubject("shell");
        shell.setHardwareType(HardwareType.COMPONENT);
        muffler.getHardware().add(shell);
        d.getHardware().add(shell);

        InternalInterface connect = new InternalInterface();
        connect.setId(UUID.randomUUID().toString());
        connect.setFrom(flange);
        connect.setTo(elbow);
        connect.setSubject("flange::elbow");
        connect.setInterfaceType(InterfaceType.PHYSICAL);
        exhaust.getInternalInterfaces().add(connect);

        Function f1 = new Function();
        f1.setId(UUID.randomUUID().toString());
        f1.setSubject("Connect Flange to Elbow");
        connect.getFunctions().add(f1);

        FailureMode decay = new FailureMode();
        decay.setId(UUID.randomUUID().toString());
        decay.setSubject("Decayed over time");
        decay.setFailureModeType(FailureModeType.DECAYED);
        f1.getFailureModes().add(decay);

        NoiseFactor saltWater = new NoiseFactor();
        saltWater.setId(UUID.randomUUID().toString());
        saltWater.setSubject("Salt Water");
        saltWater.setNoiseFactorType(NoiseFactorType.ENVIRONMENTAL);
        exhaust.getNoiseFactors().add(saltWater);

        ExternalInterface saltWaterToFlange = new ExternalInterface();
        saltWaterToFlange.setId(UUID.randomUUID().toString());
        saltWaterToFlange.setSubject("salt water::flange");
        saltWaterToFlange.setInterfaceType(InterfaceType.MATERIAL);
        saltWaterToFlange.setFrom(saltWater);
        saltWaterToFlange.setTo(flange);
        exhaust.getExternalInterfaces().add(saltWaterToFlange);

        Function f2 = new Function();
        f2.setId(UUID.randomUUID().toString());
        f2.setSubject("resist corrosion");
        saltWaterToFlange.getFunctions().add(f2);

        return d;
    }
}
