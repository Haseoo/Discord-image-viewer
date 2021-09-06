package va.com.szkal.discordimageviewer.api;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class StoreImageRequest {
    long messageId;
    long serverId;
    String username;
    String channelName;
    long channelId;
    String url;
    LocalDateTime sendTime;
}
