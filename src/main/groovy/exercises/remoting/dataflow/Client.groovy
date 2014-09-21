package exercises.remoting.dataflow

import groovyx.gpars.dataflow.DataflowVariable
import groovyx.gpars.dataflow.remote.RemoteDataflows

class Client {

    static void main(String[] args) {
        // creates context
        def remoteDataflows = RemoteDataflows.create()
        // retrieves promise of variable with given name
        def remoteVariablePromise = remoteDataflows.getVariable("localhost", 9000, "greetings")
        // extracts remote proxy from promise
        def remoteVariable = remoteVariablePromise.get()

        println remoteVariable.value
    }
}
