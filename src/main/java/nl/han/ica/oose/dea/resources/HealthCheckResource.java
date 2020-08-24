package nl.han.ica.oose.dea.resources;

import nl.han.ica.oose.dea.annotations.DiyGET;
import nl.han.ica.oose.dea.annotations.DiyPath;

@DiyPath("/health")
public class HealthCheckResource {

    @DiyGET
    public String healthy() {
        return "Up & Running";
    }
}
