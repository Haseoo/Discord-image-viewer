package va.com.szkal.discordimageviewer;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import va.com.szkal.discordimageviewer.domain.Image;
import va.com.szkal.discordimageviewer.domain.ImageRepository;

import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class ImageCleanupCron {
    private final ImageRepository imageRepository;

    @Scheduled(cron = "0 0 2 ? * *")
    @Transactional
    public void cleanupImages() {
        var images = imageRepository.findAll();
        for (Image iamge : images) {
            if (!isImageAvailable(iamge.getImageUrl())) {
                imageRepository.delete(iamge);
            }
        }
    }

    private boolean isImageAvailable(String memeUrl) {
        try {
            var url = new URL(memeUrl);
            var huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("HEAD");
            return huc.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (Exception ignored) {
            return false;
        }

    }
}
