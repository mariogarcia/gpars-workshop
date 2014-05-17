package exercises.forwarding.images

import static groovyx.gpars.actor.Actors.actor

import exercises.test.NotifiedActor
import spock.lang.Specification

class ImagesProcessingSpecification extends Specification {

    final File RESOURCES_DIR = 'src/test/resources/exercises/forwarding/images/' as File
    final File TEMPORARY_DIR = new File(System.getProperty('java.io.tmpdir'))

    def 'Processing successfully images'() {
        setup: 'Creating needed directories references'
            def monitorActor = new NotifiedActor(3).start()
            def imageActor   = new ImageProcessorActor(monitorActor).start()
            def textActor    = new TextProcessorActor(monitorActor).start()
            def proxyActor   = new FileProcessorActor(imageActor: imageActor, textActor: textActor).start()
        when: 'Sending a couple of image files'
            proxyActor << new File(RESOURCES_DIR, 'image1.jpg')
            proxyActor << new File(RESOURCES_DIR, 'image2.jpg')
            proxyActor << new File(RESOURCES_DIR, 'text.txt')
        and: 'Expecting process to finish'
            monitorActor.join()
        and: 'Looking for processed files'
            def file1 = new File(TEMPORARY_DIR, 'processed_text.txt')
            def file2 = new File(TEMPORARY_DIR, 'processed_image1.jpg')
            def file3 = new File(TEMPORARY_DIR, 'processed_image2.jpg')
        then: 'We should make sure they exist'
            [file1, file2, file3].every { it.exists() }
        cleanup: 'Deleting processed files'
            [file1, file2, file3]*.delete()
    }
}
