package va.com.szkal.discordimageviewer.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Collection<Image> findAllByServer(String server);
}
