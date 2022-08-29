package va.com.szkal.discordimageviewer.api;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import va.com.szkal.discordimageviewer.domain.ImageService;

@Component
@RequiredArgsConstructor
public class RabbitMessageDeletedListener {
    private final ImageService imageService;

    @RabbitListener(queues = "${messageDeletedQueue}")
    public void addImage(MessageDeletedRequest request) {
        imageService.deleteImagesOfMessage(request.getMessageId());
    }
}
