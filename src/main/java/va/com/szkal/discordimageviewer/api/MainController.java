package va.com.szkal.discordimageviewer.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController
{
    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}
