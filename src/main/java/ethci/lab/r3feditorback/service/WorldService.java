package ethci.lab.r3feditorback.service;

import ethci.lab.r3feditorback.Entity.projectsModel.*;
import ethci.lab.r3feditorback.Entity.resp.Resp;
import ethci.lab.r3feditorback.Repository.CollisionRepository;
import ethci.lab.r3feditorback.Repository.LightRepository;
import ethci.lab.r3feditorback.Repository.MaterialRepository;
import ethci.lab.r3feditorback.Repository.WorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorldService {
    final HttpHeaders httpHeaders= new HttpHeaders();

   private String assetsPath = "/home/ethci/projects/userAssets/";

    @Autowired
    private WorldRepository repository;

    @Autowired
    private LightRepository lightRepository;

    @Autowired
    private CollisionRepository collisionRepository;

    @Autowired
    private MaterialRepository MaterialRepository;

    public Resp<List<World>> getWorldRepository(String user) {
        return Resp.scusess(repository.findWorldByOwner(user));
    }
    public Optional<World> findWorldByID(String id) {
        return repository.findById(id);
    }
    public Resp<Optional<World>> findWorldByID(String id,String owner) {
        Optional<World> world = repository.findById(id);
        if(world.isEmpty()){
            return Resp.fail("400" , "no project: "+id);
        }else{
            if(world.get().publish.equals("public")){
                return Resp.scusess(world);
            }else{
                if (!owner.equals(world.get().owner)){
                    return Resp.fail("400" , "not project owner");
                }else{
                    return Resp.scusess(world);
                }
            }
        }
    }
    public Resp<String> delWorld(String id) {
        repository.deleteById(id);
        return Resp.scusess("success deleted "+id);
    }
    public World newWorld(String name) {
        World world = new World("untitled",name,"private",new Light[]{},new ObjModel[]{},new Collision[]{},new Material[]{},new Effect(new SSR( true, true, true, true, true, true, true, 0.9f, 0.4f,  0f, 1f, 0.2f, 10f, 1f, 0.5f, 1f, 1f, 0.3f, 0.25f, 0.1f, 1f, 0f, 20f, 6f, 10f, 1f, 10f, 1.45f), new Bloom(0.5f, true, 0, 1.5f), new Noise(0.1f)), new defaultComponent[]{},new float[]{},new float[]{},System.currentTimeMillis());
        return repository.save(world);
    }
    public Resp<String> save(World world, String user){
        Optional<World> target = this.findWorldByID(world._id);
        if(target.isPresent()){
            if(user.equals(target.get().owner)){
                world.owner = user;
                world.lastUpdateTime = System.currentTimeMillis();
                repository.save(world);
                return Resp.scusess("saved");
            }else{
                return Resp.fail("400","not owner");
            }
        }else{
            return Resp.fail("400","cannot find project");
        }
    }
    public Light newLight() {
        String id = UUID.randomUUID().toString().replace("-","");
        return new Light(id,"AmbientLight","new Light","#FCFCFC",0.2f,new float[]{0f,0f,0f},false);
    }
    public Collision newCollision() {
        String id = UUID.randomUUID().toString().replace("-","");
        return new Collision(id,false,"new Collision",new float[]{1f,1f,1f},new float[]{0f,0f,0f},new float[]{0f,0f,0f},"","","","" );
    }
    public Material newMaterial() {
        String id = UUID.randomUUID().toString().replace("-","");
        return new Material(id,"MeshStandardMaterial", "new material", "", "", 1, "0x000000", 1f, false, false, false, 0f, 0f, 1, 1f);
    }

    public Resp<String> savePic (String user,String id,String img){
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String path =assetsPath+user+"/snapshot/";
        File dest = new File(path+id+".txt");
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        if(!dest.getParentFile().getParentFile().exists()){
            dest.getParentFile().getParentFile().mkdirs();
        }
        if(!dest.exists()){
            try {
                dest.createNewFile();
            }catch ( java.io.IOException err){
                return Resp.fail("500",err.getMessage());
            }
        }
        try {
            PrintWriter pw = new PrintWriter(dest);
            pw.println(img);
            pw.close();
        }catch (Exception e){
            e.printStackTrace();
            return Resp.fail("500",e.getMessage());
        }
        return Resp.scusess("sucess");
    }
}
