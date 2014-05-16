package exercises.forwarding.images

import spock.lang.Specification

class ImagesProcessingSpecification extends Specification {

    def 'Processing successfully images'() {
        setup: 'Creating needed directories references'
            def tmpDir = new File(System.getProperty('java.io.tmpdir'))
            def imgDir = new File('src/test/resources/exercises/forwarding/images/')
            def processor = new FileProcessorActor(
                imageActor: new ImageProcessorActor(),
                textActor: new TextProcessorActor()
            )
        and: 'Starting the monitoring process'
            processor.with {
                imageActor.start()
                textActor.start()
                start()
            }
        when: 'Sending a couple of image files'
            processor << new File(imgDir, 'image1.jpg')
            processor << new File(imgDir, 'image2.jpg')
            processor << new File(imgDir, 'text.txt')
        then: 'We should make sure the have been transformed'
            new File(tmpDir, 'processed_text.txt').exists()
            new File(tmpDir, 'processed_image1.jpg').exists()
            new File(tmpDir, 'processed_image2.jpg').exists()
    }

}
