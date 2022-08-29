package va.com.szkal.discordimageviewer.api;

import lombok.Data;

@Data
public class MessageDeletedRequest {
    private Long messageId;
}
