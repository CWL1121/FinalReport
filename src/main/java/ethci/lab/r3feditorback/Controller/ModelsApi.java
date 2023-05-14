package ethci.lab.r3feditorback.Controller;

import ethci.lab.r3feditorback.Entity.projectsModel.ObjModel;
import ethci.lab.r3feditorback.Entity.resp.Resp;
import ethci.lab.r3feditorback.service.DefaultComponentService;
import ethci.lab.r3feditorback.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;

@RestController
@RequestMapping("/api/model")
public class ModelsApi {
    @Autowired
    ModelService modelService;

    @PatchMapping("/upload")
    private Resp<ObjModel> uploadModel(@RequestParam("file")MultipartFile file, @CurrentSecurityContext(expression="authentication") Authentication authentication){
        if(authentication.getName() == "anonymousUser"){
            return Resp.fail("403","Authenticated");
        }else{
            return modelService.upload(file,authentication.getName());
        }
    }
}
