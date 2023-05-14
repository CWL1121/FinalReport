package ethci.lab.r3feditorback.Entity.projectsModel;

import java.util.UUID;

public class Bloom {
    public String _id;
    public float luminanceThreshold;
    public boolean mipmapBlur;
    public float luminanceSmoothing;
    public float intensity;
    public Bloom() {
    }
    public Bloom(float luminanceThreshold, boolean mipmapBlur, float luminanceSmoothing, float intensity) {
        this._id = UUID.randomUUID().toString().replace("-","");
        this.luminanceThreshold = luminanceThreshold;
        this.mipmapBlur = mipmapBlur;
        this.luminanceSmoothing = luminanceSmoothing;
        this.intensity = intensity;
    }
}
