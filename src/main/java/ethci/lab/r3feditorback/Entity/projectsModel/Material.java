package ethci.lab.r3feditorback.Entity.projectsModel;

import org.springframework.data.mongodb.core.mapping.Document;

public class Material {
    public String _id;
    public String type;
    public String name;
    public String color;
    public String imgpath;
    public float displacementScale;
    public String emissive;
    public float emissiveIntensity;
    public boolean transparent;
    public boolean flatShading;
    public boolean fog;
    public float opacity;
    public float metalness;
    public float refractionRatio;
    public float roughness;

    public Material( String _id, String type, String name, String color, String imgpath, float displacementScale, String emissive, float emissiveIntensity, boolean transparent, boolean flatShading, boolean fog, float opacity, float metalness, float refractionRatio, float roughness) {
        this._id = _id;
        this.type = type;
        this.name = name;
        this.color = color;
        this.imgpath = imgpath;
        this.displacementScale = displacementScale;
        this.emissive = emissive;
        this.emissiveIntensity = emissiveIntensity;
        this.transparent = transparent;
        this.flatShading = flatShading;
        this.fog = fog;
        this.opacity = opacity;
        this.metalness = metalness;
        this.refractionRatio = refractionRatio;
        this.roughness = roughness;
    }

    public Material() {
    }
}
