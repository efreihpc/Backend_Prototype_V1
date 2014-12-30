package frontend;

import backend.Backend;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@Controller
@RestController
public class HPCController{
	
	private Backend m_backend = new Backend();

    @RequestMapping("/state")
    public String hello() {
        m_backend.stateCheck();
        return "Executed test";
    }

}
