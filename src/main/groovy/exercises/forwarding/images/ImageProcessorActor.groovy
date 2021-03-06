package exercises.forwarding.images

import static javax.imageio.ImageIO.read
import static javax.imageio.ImageIO.write
import static org.imgscalr.Scalr.resize
import static java.io.File.createTempFile

import exercises.test.NotificationActor
import groovyx.gpars.actor.DefaultActor

class ImageProcessorActor extends NotificationActor {

    ImageProcessorActor(DefaultActor monitor) {
        super(monitor)
    }

    void act() {
        loop {
            react { File image ->
                def src = read(image)
                def img = resize(src, 300, 200)
                def tmp = new File(System.getProperty('java.io.tmpdir'))

                write(img, 'jpg', new File(tmp, "processed_${image.name}"))

                notifyIfMonitor(image.name)
            }
        }
    }

}

