package ethci.lab.r3feditorback.Controller;
import ethci.lab.r3feditorback.Entity.projectsModel.*;
import ethci.lab.r3feditorback.Entity.resp.Resp;
import ethci.lab.r3feditorback.service.WorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.File;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/world")
public class WorldApi {
    private final WorldService worldService;

    @Autowired
    public WorldApi(WorldService worldService) {
        this.worldService = worldService;
    }

    @GetMapping("/getOwnWorld")
    public Resp<List<World>> getWorld(@CurrentSecurityContext(expression="authentication") Authentication authentication){
        String user = authentication.getName();
        if(user != "anonymousUser"){
            System.out.println(user);
            return worldService.getWorldRepository(user);
        }
        return Resp.fail("403","UnAuthenticated");
    }
    @GetMapping("/findByID")
    public Resp<Optional<World>> findbyID(@RequestParam String id, @CurrentSecurityContext(expression="authentication") Authentication authentication){
        return worldService.findWorldByID(id,authentication.getName());
    }

    @PostMapping("/delByID")
    public Resp<String> delByID(@RequestParam String id, @CurrentSecurityContext(expression="authentication") Authentication authentication){
        String owner = authentication.getName();
        Optional<World> world = worldService.findWorldByID(id);
        if(world.isEmpty()){
          return Resp.fail("400",id+" not found");
        }else{
            if(owner.equals(world.get().owner)){
                return worldService.delWorld(id);
            }else{
                return Resp.fail("403","not Owner");
            }
        }
    }

    @GetMapping("/newWorld")
    public World newWorld(@CurrentSecurityContext(expression="authentication") Authentication authentication){
        return worldService.newWorld(authentication.getName());
    }

    @PostMapping("/save")
    public Resp<String> saveWorld(@RequestBody World world,@CurrentSecurityContext(expression="authentication") Authentication authentication){
        String user = authentication.getName();
        if(user.equals("anonymousUser")){
            return Resp.fail("403","UnAuthenticated");
        }else{
            return worldService.save(world,authentication.getName());
        }
    }

    @PostMapping(value = "/savePic")
    public Resp<String> savePic(@RequestParam("id") String id,@RequestParam("pic") String pic, @CurrentSecurityContext(expression="authentication") Authentication authentication){
        String user = authentication.getName();
        if(user.equals("anonymousUser")){
            return Resp.fail("403","UnAuthenticated");
        }else{
            return worldService.savePic(user,id,pic);
        }
    }


    @GetMapping("/newLight")
    public Resp<Light> newLight(@CurrentSecurityContext(expression="authentication") Authentication authentication){
        String user = authentication.getName();
        if(user.equals("anonymousUser")){
            return Resp.fail("403","UnAuthenticated");
        }else{
            return Resp.scusess(worldService.newLight());
        }
    }

    @GetMapping("/newCollision")
    public Resp<Collision> newCollision(@CurrentSecurityContext(expression="authentication") Authentication authentication){
        String user = authentication.getName();
        if(user.equals("anonymousUser")){
            return Resp.fail("403","UnAuthenticated");
        }else{
            return Resp.scusess(worldService.newCollision());
        }
    }

    @GetMapping("/newMaterial")
    public Resp<Material> newMaterial(@CurrentSecurityContext(expression="authentication") Authentication authentication){
        String user = authentication.getName();
        if(user.equals("anonymousUser")){
            return Resp.fail("403","UnAuthenticated");
        }else{
            return Resp.scusess(worldService.newMaterial());
        }
    }

    @PatchMapping("/updateMaterial")
    private Resp<String> upload(@RequestParam("file")MultipartFile file, @CurrentSecurityContext(expression="authentication") Authentication authentication){
        String user = authentication.getName();
        if(user.equals("anonymousUser")){
            return Resp.fail("403","UnAuthenticated");
        }else{
            return Resp.scusess(upload(file,user));
        }
    }

    public String upload(MultipartFile file,String name){
        if(file.isEmpty()){
            return "empty file";
        }
        String originalFilename = file.getOriginalFilename();
        String path ="/home/ethci/projects/userAssets/"+name+"/material/";
        File dest = new File(path+originalFilename);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        }catch (Exception e){
            e.printStackTrace();
            return "uploadFail";
        }
        return originalFilename;
    }

}
