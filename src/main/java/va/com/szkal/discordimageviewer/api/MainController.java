package va.com.szkal.discordimageviewer.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import va.com.szkal.discordimageviewer.domain.ImageService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ImageService imageService;

    @GetMapping
    public ModelAndView index(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("serverName", principal.getName());
        modelAndView.addObject("images", imageService.getAllFromServer(principal.getName()));
        return modelAndView;
    }
}
