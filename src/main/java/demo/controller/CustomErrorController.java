package demo.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {
    private static final Logger LOGGER= LoggerFactory.getLogger(CustomErrorController.class);

    String errorPage= "error/error";
    @RequestMapping 
    public String handleError(HttpServletRequest request){
        Object status=request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object uri = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        if(status != null && Integer.parseInt(status.toString()) == HttpStatus.NOT_FOUND.value()){
            LOGGER.error("404 Not Found", uri);
            return "error/404";
        }
        LOGGER.error("Error {} on {}", status, uri);
        return errorPage;
    }
}
