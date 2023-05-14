package ethci.lab.r3feditorback.Controller;;
import ethci.lab.r3feditorback.Entity.resp.Resp;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping
public class Default {
    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/check")
    public Resp<String> check(@CurrentSecurityContext(expression="authentication") Authentication authentication) {
        if(authentication.getName() != "anonymousUser"){
            return Resp.scusess(authentication.getName());
        }
        return Resp.fail("403","UnAuthenticated");
    }


}
