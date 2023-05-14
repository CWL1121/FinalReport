package ethci.lab.r3feditorback.service;

import ethci.lab.r3feditorback.Entity.projectsModel.User;
import ethci.lab.r3feditorback.Entity.projectsModel.defaultComponent;
import ethci.lab.r3feditorback.Entity.resp.Resp;
import ethci.lab.r3feditorback.Repository.DefaultComponent;
import ethci.lab.r3feditorback.utils.IdData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Service
public class DefaultComponentService {
    @Autowired
    private DefaultComponent repository;

    String modelPath = "D:\\_R3f-editor-back\\userAssets\\";

    public Optional<defaultComponent> findPictureByID(String id) {
        return repository.findById(id);
    }

    public Resp<defaultComponent> upload(MultipartFile file, String name){
        if(file.isEmpty()){
            return Resp.fail("400","empty file");
        }
        String id = UUID.randomUUID().toString().replace("-","");
        String originalFilename = file.getOriginalFilename();
        String path =modelPath+name+"\\defaultComponent\\";
        File dest = new File(path+originalFilename);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        }catch (Exception e){
            e.printStackTrace();
            return Resp.fail("500","uploadFail");
        }
        return Resp.scusess(
                repository.save(
                        new defaultComponent(id,false,originalFilename, new float[]{1f,1f,0.001f}, new float[]{0f,0f,0f}, new float[]{0f,0f,0f},"","","","",new String[]{originalFilename},"")
                )
        );
    }

    public Resp<defaultComponent> add(MultipartFile file, String name, String id){
        Optional<defaultComponent> target = this.findPictureByID(id);
        if(file.isEmpty()){
            return Resp.fail("400","empty file");
        }

        if(target.isPresent()){
            String originalFilename = file.getOriginalFilename();
            String path =modelPath+name+"\\defaultComponent\\";

            File dest = new File(path+originalFilename);
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            }catch (Exception e){
                e.printStackTrace();
                return Resp.fail("500","uploadFail");
            }
            defaultComponent pic = target.get();
            List<String> arrlist = new ArrayList<String>(Arrays.asList(pic.pictureUrl));
            arrlist.add(originalFilename);
            pic.pictureUrl = arrlist.toArray(pic.pictureUrl);
            return Resp.scusess(repository.save(pic));
        }else{;
            return Resp.fail("400","cannot find project");
        }
    }

    public Resp<defaultComponent> setVideo(String url, String id){
        Optional<defaultComponent> target = this.findPictureByID(id);

        if(target.isPresent()){
            defaultComponent pic = target.get();
            pic.videoUrl = url;
            return Resp.scusess(repository.save(pic));
        }else{;
            return Resp.fail("400","cannot find project");
        }
    }
}
