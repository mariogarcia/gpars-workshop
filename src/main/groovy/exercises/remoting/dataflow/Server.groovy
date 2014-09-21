package exercises.remoting.dataflow

import groovyx.gpars.dataflow.DataflowVariable
import groovyx.gpars.dataflow.remote.RemoteDataflows

class Server {

    static void main(String[] args) {

        def dataflows = RemoteDataflows.create()
        dataflows.startServer('localhost',9000)

        def variable = new DataflowVariable()

        variable << "hello"

        dataflows.publish(variable,"greetings")
    }

}
