package ethci.lab.r3feditorback.Entity.projectsModel;

public class defaultComponent extends Collision{
    public String[] pictureUrl;

    public String videoUrl;

    public defaultComponent(){}
    public defaultComponent(String _id, boolean clickable, String name, float[] scale, float[] position, float[] rotation, String material, String onClick, String onPointerOver, String onPointerOut, String[] pictureUrl, String videoUrl) {
        super(_id, clickable, name, scale, position, rotation, material, onClick, onPointerOver, onPointerOut);
        this.pictureUrl = pictureUrl;
    }
}
