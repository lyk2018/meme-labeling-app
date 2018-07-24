package tr.org.linux.kamp.memeapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CustomControllerAdvice {

    @ExceptionHandler(NullPointerException.class)
    String handleNullPointerException(NullPointerException e, Model model) {

        log.error(e.getMessage(), e);

        //TODO send SMS to developers

        throw e;
    }

}
