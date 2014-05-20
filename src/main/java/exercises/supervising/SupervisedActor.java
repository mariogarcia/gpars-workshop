package exercises.supervising;

import groovyx.gpars.actor.DefaultActor;

public class SupervisedActor extends DefaultActor {

    private final SupervisorBus supervisorBus;

    public void setSupervisorBus(SupervisorBus bus) {
        this.supervisorBus = bus;
    }

}
