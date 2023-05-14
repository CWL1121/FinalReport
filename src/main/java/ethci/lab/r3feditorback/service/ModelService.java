package ethci.lab.r3feditorback.service;

import ethci.lab.r3feditorback.Entity.resp.Resp;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.MimeType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import ethci.lab.r3feditorback.Entity.projectsModel.ObjModel;
import ethci.lab.r3feditorback.Entity.resp.Resp;
import ethci.lab.r3feditorback.Repository.ModelRepository;

import java.io.*;
import java.util.UUID;

@Service
public class ModelService {
   private String modelPath = "/home/ethci/projects/userAssets/";
    @Autowired
    private ModelRepository repository;

    public Resp<ObjModel> upload(MultipartFile file,String name){
        if(file.isEmpty()){
            return Resp.fail("400","empty file");
        }
        String id = UUID.randomUUID().toString().replace("-","");
        String originalFilename = file.getOriginalFilename();
        String extend = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        String path =modelPath+name+"/model/";
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
        return Resp.scusess(repository.save(new ObjModel(
                id,extend, originalFilename, "new Model", "", new float[]{0f,0f,0f}, new float[]{0f,0f,0f},1f
        )));
    }

}
