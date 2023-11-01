package com.mfmea.systemfx.shared;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/")
@ApplicationScoped
public class ApplicationConfig extends Application {
    
}
