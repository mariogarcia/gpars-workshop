package exercises.supervising;

import groovyx.gpars.actor.DefaultActor;

public class SupervisedActor extends DefaultActor {

    private SupervisorBus supervisorBus;

    public void setSupervisorBus(SupervisorBus bus) {
        this.supervisorBus = bus;
    }

}
