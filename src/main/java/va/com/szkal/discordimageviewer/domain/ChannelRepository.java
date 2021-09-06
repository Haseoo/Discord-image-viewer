package va.com.szkal.discordimageviewer.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

interface ChannelRepository extends CrudRepository<Channel, Long> {
    Collection<Channel> findAllByServerId(long serverId);
}
