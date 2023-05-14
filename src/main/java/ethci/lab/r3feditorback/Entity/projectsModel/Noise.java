package ethci.lab.r3feditorback.Entity.projectsModel;

import java.util.UUID;

public class Noise {
    public String _id;
    public float value;
    public Noise() {
    }
    public Noise(float value) {
        this._id = UUID.randomUUID().toString().replace("-","");
        this.value = value;
    }
}
