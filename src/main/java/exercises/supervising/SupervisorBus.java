package exercises.supervising;

import java.util.List;
import java.util.ArrayList;
import groovyx.gpars.actor.DefaultActor;

public class SupervisorBus extends DefaultActor {

    private List<SupervisorActor> supervisors = new ArrayList<SupervisorActor>();

    public void onMessage(SupervisedActorMessage message) {
        for(SupervisorActor supervisor : supervisors) {
            supervisor.leftShift(message);
        }
    }

    public void onMessage(SupervisorActor supervisor) {
        this.supervisors.add(supervisor);
    }

}
