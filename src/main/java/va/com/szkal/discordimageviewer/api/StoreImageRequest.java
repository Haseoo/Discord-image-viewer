package va.com.szkal.discordimageviewer.api;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class StoreImageRequest {
    String username;
    String channel;
    String server;
    String url;
    LocalDateTime sendTime;
}
