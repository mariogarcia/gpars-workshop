package exercises.forwarding.images

import groovyx.gpars.actor.DefaultActor

class FileProcessorActor extends DefaultActor {

    DefaultActor imageActor
    DefaultActor textActor

    void act() {
        loop {
            react { File file2Process ->
                println "Start to processing ${file2Process.name}"
                if (isImageFile(file2Process)) {
                    sendToImageActor(file2Process)
                }
                if (isTextFile(file2Process)) {
                    sendToTextActor(file2Process)
                }
            }
        }
    }

    Boolean isImageFile(File file2Process) {
        return doesItEndWith(file2Process, 'jpg')
    }

    Boolean isTextFile(File file2Process) {
        return doesItEndWith(file2Process, 'txt')
    }

    Boolean doesItEndWith(File file, String ending) {
        return file.name.endsWith(ending)
    }

    void sendToImageActor(File file) {
        imageActor << file
    }

    void sendToTextActor(File file) {
        textActor << file
    }

}
