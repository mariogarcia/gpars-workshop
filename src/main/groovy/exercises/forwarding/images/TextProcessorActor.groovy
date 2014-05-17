package exercises.forwarding.images

import static java.io.File.createTempFile

import exercises.test.NotificationActor
import groovyx.gpars.actor.DefaultActor

class TextProcessorActor extends NotificationActor {

    TextProcessorActor(DefaultActor monitor) {
        super(monitor)
    }

    void act() {
        loop {
            react { File textFile ->
                def tmpfile = new File(System.getProperty('java.io.tmpdir'))
                def newFile = new File(tmpfile, "processed_${textFile.name}")

                newFile << 'Author: me'
                newFile << textFile.text

                notifyIfMonitor(textFile.name)
            }
        }
    }

}
