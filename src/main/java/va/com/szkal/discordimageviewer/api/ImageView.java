package va.com.szkal.discordimageviewer.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import va.com.szkal.discordimageviewer.domain.Image;

import java.time.format.DateTimeFormatter;


@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageView {
    private String username;
    private String imageUrl;
    private String sendTime;

    public static ImageView of(Image image) {
        return new ImageView(image.getUsername(),
                image.getImageUrl(),
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
                        .format(image.getSendTime()));
    }
}
