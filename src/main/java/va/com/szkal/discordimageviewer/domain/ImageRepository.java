package va.com.szkal.discordimageviewer.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ImageRepository extends PagingAndSortingRepository<Image, Long> {
    Page<Image> findAllByChannelIdOrderBySendTimeDesc(long channelId, Pageable pageable);
}