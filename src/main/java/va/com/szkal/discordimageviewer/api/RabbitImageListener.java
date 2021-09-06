package va.com.szkal.discordimageviewer.api;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import va.com.szkal.discordimageviewer.domain.ImageService;

@Component
@RequiredArgsConstructor
public class RabbitImageListener {
    private final ImageService imageService;

    @RabbitListener(queues = "${rabbitQueue}")
    public void addImage(StoreImageRequest request) {
        imageService.add(request);
    }
}
