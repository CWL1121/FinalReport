package ethci.lab.r3feditorback.Entity.projectsModel;

import org.springframework.data.mongodb.core.mapping.Document;

public class ObjModel {
    public String _id;
    public String type;
    public String url;
    public String name;
    public String anime;
    public float[] position;
    public float[] rotation;
    public float scale;

    public ObjModel() {
    }

    public ObjModel(String _id, String type, String url, String name, String anime, float[] position, float[] rotation, float scale) {
        this._id = _id;
        this.type = type;
        this.url = url;
        this.name = name;
        this.anime = anime;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }
}
