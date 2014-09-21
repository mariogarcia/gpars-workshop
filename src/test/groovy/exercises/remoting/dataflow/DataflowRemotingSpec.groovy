package exercises.remoting.dataflow

import spock.lang.Specification

// tag::dataflowremoting_imports[]
import groovyx.gpars.dataflow.DataflowVariable
import groovyx.gpars.dataflow.remote.RemoteDataflows
// end::dataflowremoting_imports[]

class DataflowRemotingSpec extends Specification {

    // tag::dataflowremoting_2[]
    void 'reading a simple dataflow variable'() {
        setup: 'starting server exposing dataflow variable'
            def serverDataflows = startServer() // <1>
        when: 'listening to that server'
            def clientDataflows = RemoteDataflows.create() // <2>
            def remoteVariablePromise = // <3>
                clientDataflows.getVariable(
                    "localhost",
                    9000,
                    "meaning-of-life"
                )
        and: 'asking for a given dataflow variable'
            def result = remoteVariablePromise.get() // <4>
        then: 'you should get what you expected'
            result.value == 42
        cleanup: 'stopping server instance'
            serverDataflows.stopServer() // <5>
    }
    // end::dataflowremoting_2[]

    // tag::dataflowremoting_1[]
    RemoteDataflows startServer() {
        def dataflows = RemoteDataflows.create() // <1>
        dataflows.startServer('localhost',9000) // <2>
        def variable = new DataflowVariable() // <3>
        variable << 42
        dataflows.publish(variable,"meaning-of-life") // <4>

        return dataflows
    }
    // end::dataflowremoting_1[]

}
