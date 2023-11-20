package com.mfmea.systemfx.shared;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import com.mfmea.systemfx.business.dfmea.entity.Dfmea;
import com.mfmea.systemfx.business.dfmea.entity.FmeaStandard;
import com.mfmea.systemfx.business.hardware.entity.Hardware;

import jakarta.enterprise.context.ApplicationScoped;
import one.microstream.storage.types.StorageManager;

@ApplicationScoped
public class DataInit {
    private static final System.Logger LOGGER = System.getLogger(DataInit.class.getName());

    public void init(Root root, StorageManager storageManager) {
        LOGGER.log(System.Logger.Level.INFO, "(From the App) Additional configuration on the DataInit");
        Set<String> team = Set.of("jrogers");

        Dfmea d = new Dfmea("companyName", "engineering location", "customer name", "model year platform", "subject",
                LocalDate.now(), LocalDate.now(), team, "FM-001", "jrogers", "", "", "", "", FmeaStandard.AIAG, "", 0);
        d.setId(UUID.randomUUID().toString());

        Hardware exhaust = new Hardware();
        exhaust.setId(UUID.randomUUID().toString());
        exhaust.setSubject("exhaust");
        exhaust.setParent(null);
        exhaust.setHardwareType(HardwareType.SYSTEM);
        d.getHardware().add(exhaust);

        Hardware flange = new Hardware();
        flange.setId(UUID.randomUUID().toString());
        flange.setSubject("flange");
        flange.setParent(exhaust);
        flange.setHardwareType(HardwareType.COMPONENT);
        d.getHardware().add(flange);

        Hardware elbow = new Hardware();
        elbow.setId(UUID.randomUUID().toString());
        elbow.setSubject("elbow");
        elbow.setParent(exhaust);
        elbow.setHardwareType(HardwareType.COMPONENT);
        d.getHardware().add(elbow);

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

        root.addDfmea(d);
    }

}
