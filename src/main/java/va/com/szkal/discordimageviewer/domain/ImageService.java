package va.com.szkal.discordimageviewer.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        image.setImageUrl(storeImageRequest.getUrl());
        image.setUsername(storeImageRequest.getUsername());
        image.setChannel(storeImageRequest.getChannel());
        image.setServerId(storeImageRequest.getServerId());
        image.setImageUrl(storeImageRequest.getUrl());
        image.setSendTime(storeImageRequest.getSendTime());
        return imageRepository.save(image);
    }

    public Page<Image> getAllFromServer(long serverId, String channelName, int page) {
        Pageable pageable = PageRequest.of(page, 15);
        return imageRepository.findAllByServerIdAndChannelOrderBySendTimeDesc(serverId, channelName, pageable);
    }

    public Collection<String> getChannelsWithImages(long serverId) {
        return imageRepository.findServerChannels(serverId);
    }
}
