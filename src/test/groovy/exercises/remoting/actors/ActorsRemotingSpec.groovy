package exercises.remoting.actors

import spock.lang.Specification

// tag::remoteactors_imports[]
import static groovyx.gpars.actor.Actors.actor
import groovyx.gpars.actor.remote.RemoteActors
// end::remoteactors_imports[]

class ActorsRemotingSpec extends Specification {

    // tag::remoteactors_1[]
    void 'simple remote actors test'() {
        given: 'a remote actor server'
            // <1>
            def remoteActorServer = RemoteActors.create()
            remoteActorServer.startServer('localhost',9000)
        and: 'a simple DefaultActor instance'
            // <2>
            def simpleActor = actor {
                loop {
                    react { String msg ->
                        reply "Thanks for saying: $msg"
                    }
                }
            }
        when: 'publishing the actor remotely'
            // <3>
            remoteActorServer.publish(simpleActor, 'greetingsActor')
        and: 'getting the actor reference'
            // <4>
            def remoteActorsClient = RemoteActors.create()
            def actorPromise =
                remoteActorsClient
                    .get('localhost', 9000, 'greetingsActor')
            // <5>
            def remoteActor = actorPromise.get()
        and: 'sending a message to actor'
            // <6>
            def result = remoteActor.sendAndWait('hello')
        then: 'the reply should be the expected'
             result == 'Thanks for saying: hello'
        cleanup: 'shutting down the actor server'
            remoteActorServer.stopServer()
    }
    // end::remoteactors_1[]

}
