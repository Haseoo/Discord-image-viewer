package va.com.szkal.discordimageviewer.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ImageRepository extends PagingAndSortingRepository<Image, Long> {
    Page<Image> findAllByChannelIdOrderBySendTimeDesc(long channelId, Pageable pageable);

    @Modifying
    @Query(value = "DELETE FROM discordimages WHERE ctid IN (SELECT ctid FROM discordimages WHERE messageId = ?1)",
            nativeQuery = true)
    void deleteByMessageId(long messageId);
}