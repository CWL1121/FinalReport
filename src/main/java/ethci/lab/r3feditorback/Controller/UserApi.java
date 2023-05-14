package ethci.lab.r3feditorback.Controller;

import ethci.lab.r3feditorback.Entity.projectsModel.Key;
import ethci.lab.r3feditorback.Entity.projectsModel.User;
import ethci.lab.r3feditorback.Entity.resp.Resp;
import ethci.lab.r3feditorback.service.UserService;
import ethci.lab.r3feditorback.utils.IdData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserApi {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registerAdmin")
    @PreAuthorize("hasAuthority('Admin')")
    public User adnin(@RequestBody User user ){
        String psd = passwordEncoder.encode(user.password);
        User admin = new User(
                user.name,
                psd,
                "Admin"
        );
        return userService.insertUser(admin);
    }

    @PostMapping("/findbyID")
    public Optional<User> findbyID(@RequestBody IdData data){
        return userService.findUserByID(data.id);
    }
    
    @PostMapping("/register")
    public User Adduser(@RequestBody User user ){
        String psd = passwordEncoder.encode(user.password);
        User admin = new User(
                user.name,
                psd,
                "User"
        );
        return userService.insertUser(admin);
    }

    @PostMapping("/setKey")
    public Resp<Key> setKey(@RequestBody Key key, @CurrentSecurityContext(expression="authentication") Authentication authentication){
        return userService.setKey(authentication.getName(),key);
    }

    @GetMapping("/getKey")
    public Resp<Key> getKey(@CurrentSecurityContext(expression="authentication") Authentication authentication){
        String user = authentication.getName();
        if(user.equals("anonymousUser")){
            return Resp.fail("403",user+" not found");
        }else{
            return userService.getKey(user);
        }
    }

}
