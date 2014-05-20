package exercises.supervising;

import groovyx.gpars.actor.Actor;

public class SupervisedActorMessage {
    public final String CONTENT;
    public final Actor REFERENCE;

    public SupervisedActorMessage(final Actor reference, final String content) {
        this.REFERENCE = reference;
        this.CONTENT = content;
    }

}
