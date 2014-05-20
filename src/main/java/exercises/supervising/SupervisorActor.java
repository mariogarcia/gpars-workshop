package exercises.supervising;

import java.util.List;
import groovyx.gpars.actor.Actor;
import groovyx.gpars.actor.DefaultActor;

public abstract class SupervisorActor extends DefaultActor {

    private final List<Class<? extends Actor>> supervisedTypeList;
    private final SupervisorBus supervisorBus;

    public SupervisorActor (
        final List<Class<? extends Actor>> supervisedTypeList) {
        this.supervisedTypeList = supervisedTypeList;
    }

    public void setSupervisorBus(final SupervisorBus bus) {
        this.supervisorBus = bus;
    }

    public abstract void onMessage(SupervisedActorMessage supervisedMessages);

}
