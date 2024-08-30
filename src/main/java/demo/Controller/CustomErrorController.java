package demo.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {
    private static final Logger LOGGER= LoggerFactory.getLogger(CustomErrorController.class);

    String errorPage= "error/error";
    @GetMapping
    public String handleError(HttpServletRequest request){
        Object status=request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if(status != null){
            Integer stat=Integer.valueOf(status.toString());
            if(stat==HttpStatus.NOT_FOUND.value()){
                errorPage= "error/404";
                LOGGER.error("Error 404");
            }
        }
        return errorPage;
    }
}
