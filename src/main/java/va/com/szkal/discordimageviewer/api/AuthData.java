package va.com.szkal.discordimageviewer.api;

import lombok.Data;

import java.util.Set;

@Data
public class AuthData {
    private long serverId;
    private String serverName;
    private Set<Long> channelIds;
}
