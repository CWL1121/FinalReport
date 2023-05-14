package ethci.lab.r3feditorback.Entity.projectsModel;

import org.springframework.data.mongodb.core.mapping.Document;

public class Light {
    public String _id;
    public String type;
    public String name;
    public String color;
    public float intensity;
    public float[] position;
    public Boolean Shadow;

    public Light( String _id,String type, String name, String color, float intensity, float[] position, Boolean Shadow) {
        this._id = _id;
        this.type = type;
        this.name = name;
        this.color = color;
        this.intensity = intensity;
        this.position = position;
        this.Shadow = Shadow;
    }

    public Light() {

    }
}
