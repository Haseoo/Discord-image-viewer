package va.com.szkal.discordimageviewer.api;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class StoreImageRequest {
    String channel;
    LocalDateTime sendTime;
    String url;
}
