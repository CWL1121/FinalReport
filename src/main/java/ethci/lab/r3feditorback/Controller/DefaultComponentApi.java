package ethci.lab.r3feditorback.Controller;

import ethci.lab.r3feditorback.Entity.projectsModel.defaultComponent;
import ethci.lab.r3feditorback.Entity.resp.Resp;
import ethci.lab.r3feditorback.service.DefaultComponentService;
import ethci.lab.r3feditorback.utils.IdData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/DefaultComponent")
public class DefaultComponentApi {

    private final DefaultComponentService Service;

    @Autowired
    public DefaultComponentApi(DefaultComponentService Service) {
        this.Service = Service;
    }

    @PatchMapping("/upload")
    private Resp<defaultComponent> uploadPicture(@RequestParam("file") MultipartFile file, @CurrentSecurityContext(expression="authentication") Authentication authentication){
        if(authentication.getName().equals("anonymousUser")){
            return Resp.fail("403","Authenticated");
        }else{
            return Service.upload(file,authentication.getName());
        }
    }


    @PatchMapping("/add")
    private Resp<defaultComponent> addPicture(@RequestParam("file") MultipartFile file, @RequestParam("id") String id, @CurrentSecurityContext(expression="authentication") Authentication authentication){
        if(authentication.getName().equals("anonymousUser")){
            return Resp.fail("403","Authenticated");
        }else{
            return Service.add(file,authentication.getName(),id);
        }
    }

    @PostMapping("/setVideo")
    public Resp<defaultComponent> setVideo(@RequestParam("id") String id,@RequestParam("url") String url){
        return Service.setVideo(url,id);
    }
}
