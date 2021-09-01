package va.com.szkal.discordimageviewer.api;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleOther(Exception e) {
        var model = new ModelAndView("error");
        model.addObject("date", LocalDateTime.now());
        model.addObject("error", e.getClass().getName());
        model.addObject("status", 500);
        model.addObject("trace", Arrays.stream(e.getStackTrace())
                .map(StackTraceElement::toString)
                .collect(Collectors.joining("\n")));
        return model;
    }
}
