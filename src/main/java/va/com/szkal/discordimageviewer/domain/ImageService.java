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
    private final ChannelRepository channelRepository;

    @Transactional
    public Image add(StoreImageRequest storeImageRequest) {
        Image image = new Image();
        image.setImageUrl(storeImageRequest.getUrl());
        image.setUsername(storeImageRequest.getUsername());
        image.setChannel(channelRepository.findById(storeImageRequest.getChannelId())
                .orElseGet(() -> addNewChannel(storeImageRequest.getChannelId(),
                        storeImageRequest.getServerId(),
                        storeImageRequest.getChannelName())));
        image.setImageUrl(storeImageRequest.getUrl());
        image.setSendTime(storeImageRequest.getSendTime());
        return imageRepository.save(image);
    }

    public Page<Image> getAllFromServer(long channelId, int page) {
        Pageable pageable = PageRequest.of(page, 15);
        return imageRepository.findAllByChannelIdOrderBySendTimeDesc(channelId, pageable);
    }

    public Collection<Channel> getChannelsWithImages(long serverId) {
        return channelRepository.findAllByServerId(serverId);
    }

    @Transactional
    public void deleteChannel(long channelId) {
        channelRepository.deleteById(channelId);
    }

    @Transactional
    public void renameChannel(long id, String newName) {
        channelRepository.findById(id).ifPresent(channel -> {
            channel.setName(newName);
            channelRepository.save(channel);
        });
    }

    public String getChanelName(long id) {
        return channelRepository.findById(id).map(Channel::getName).orElse("no channel");
    }

    private Channel addNewChannel(long id, long serverId, String name) {
        Channel channel = new Channel();
        channel.setId(id);
        channel.setServerId(serverId);
        channel.setName(name);
        return channelRepository.save(channel);
    }
}
