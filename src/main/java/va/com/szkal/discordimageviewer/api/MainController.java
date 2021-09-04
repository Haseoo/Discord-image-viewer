package va.com.szkal.discordimageviewer.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import va.com.szkal.discordimageviewer.domain.Image;
import va.com.szkal.discordimageviewer.domain.ImageService;
import va.com.szkal.discordimageviewer.security.ServerAuthentication;

import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ImageService imageService;

    @GetMapping
    public ModelAndView index(@RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "") String channel,
                              ServerAuthentication authentication) {
        long serverId = authentication.getDetails();
        Page<Image> imagePage = imageService.getAllFromServer(serverId, channel, page - 1);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("serverName", authentication.getName());
        modelAndView.addObject("channels", imageService.getChannelsWithImages(serverId));
        modelAndView.addObject("channel", channel);
        if (!channel.equals("")) {
            modelAndView.addObject("totalPages", imagePage.getTotalPages());
            modelAndView.addObject("images", imagePage.stream()
                    .map(ImageView::of)
                    .collect(Collectors.toList()));
            modelAndView.addObject("pageNumber", page);
        }
        return modelAndView;
    }
}
