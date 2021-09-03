package va.com.szkal.discordimageviewer.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import va.com.szkal.discordimageviewer.api.StoreImageRequest;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    @Transactional
    public Image add(StoreImageRequest storeImageRequest) {
        Image image = new Image();
        image.setUsername(storeImageRequest.getUsername());
        image.setChannel(storeImageRequest.getChannel());
        image.setServer(storeImageRequest.getServer());
        image.setImageUrl(storeImageRequest.getUrl());
        image.setSendTime(storeImageRequest.getSendTime());
        return imageRepository.saveAndFlush(image);
    }

    public Collection<Image> getAllFromServer(String serverName) {
        return imageRepository.findAllByServer(serverName);
    }
}
