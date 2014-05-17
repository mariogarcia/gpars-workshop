package exercises.test

import groovyx.gpars.actor.DefaultActor

class NotifiedActor extends DefaultActor {

    final Integer noTimes

    NotifiedActor(Integer noTimes) {
       this.noTimes = noTimes
    }

    void act() {
        loop(noTimes) {
            react {
                println "NOTIFICATION: $it"
            }
        }
    }

}

