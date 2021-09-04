package va.com.szkal.discordimageviewer.api;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class StoreImageRequest {
    long messageId;
    long serverId;
    String username;
    String channel;
    String url;
    LocalDateTime sendTime;
}
