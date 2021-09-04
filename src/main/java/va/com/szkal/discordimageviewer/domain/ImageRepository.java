package va.com.szkal.discordimageviewer.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

public interface ImageRepository extends PagingAndSortingRepository<Image, Long> {
    Page<Image> findAllByServerIdAndChannelOrderBySendTimeDesc(long serverId, String channel, Pageable pageable);

    @Query("select i.channel from Image i where i.serverId = ?1 group by i.channel")
    Collection<String> findServerChannels(long serverId);
}