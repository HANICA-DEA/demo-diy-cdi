package nl.han.aim.oose.dea.resources;

import nl.han.aim.oose.dea.annotations.DiyGET;
import nl.han.aim.oose.dea.annotations.DiyPath;

@DiyPath("/health")
public class HealthCheckResource {

    @DiyGET
    public String healthy() {
        return "Up & Running";
    }
}
