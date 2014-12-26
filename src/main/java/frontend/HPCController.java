package frontend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HPCController {

    @RequestMapping("/greeting")
    public String hello() {
        return "Hello World";
    }

}
