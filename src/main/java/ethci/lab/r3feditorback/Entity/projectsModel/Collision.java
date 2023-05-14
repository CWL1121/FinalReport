package ethci.lab.r3feditorback.Entity.projectsModel;

import org.springframework.data.mongodb.core.mapping.Document;

public class Collision {
    public String _id;
    public boolean clickable;
    public String name;
    public float[] scale;
    public float[] position;
    public float[] rotation;
    public String material;
    public String onClick;
    public String onPointerOver;
    public String onPointerOut;

    public Collision( String _id ,boolean clickable, String name, float[] scale, float[] position, float[] rotation, String material, String onClick, String onPointerOver, String onPointerOut) {
        this._id = _id;
        this.clickable = clickable;
        this.name = name;
        this.scale = scale;
        this.position = position;
        this.rotation = rotation;
        this.material = material;
        this.onClick = onClick;
        this.onPointerOver = onPointerOver;
        this.onPointerOut = onPointerOut;
    }

    public Collision() {
    }
}
