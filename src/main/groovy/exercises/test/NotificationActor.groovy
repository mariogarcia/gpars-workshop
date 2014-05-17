package exercises.test

import groovyx.gpars.actor.DefaultActor

/**
 * This class is useful when testing because you can
 * create an actor acting as a monitor, passing it
 * to actors under test, call its join method,
 * and expect it to be notified a number of times.
 * Once the actor finishes you can check assertions
 * safely.
 *
 * An alternative could be to use a CountDownLatch
 */
class NotificationActor extends DefaultActor {

    DefaultActor notified

    NotificationActor(DefaultActor actor) {
        this.notified = actor
    }

    void notifyIfMonitor(Object object) {
        if (notified) {
            notified << object
        }
    }

}

