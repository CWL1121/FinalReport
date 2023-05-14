package ethci.lab.r3feditorback.Entity.projectsModel;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

@Document(collection = "worlds")
public class World {
    public String _id;
    public String name;
    public String owner;
    public String publish;
    public Light[] light;
    public ObjModel[] objModel;
    public Collision[] collision;
    public Material[] material;

    public Effect effect;
    public defaultComponent[] defaultComponents;
    public float[] spawn ;
    public float[] player ;
    public Long lastUpdateTime ;

    @Override
    public String toString() {
        return "World{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", publish='" + publish + '\'' +
                ", light=" + Arrays.toString(light) +
                ", objModel=" + Arrays.toString(objModel) +
                ", collision=" + Arrays.toString(collision) +
                ", material=" + Arrays.toString(material) +
                ", effect=" + effect +
                ", defaultComponents=" + Arrays.toString(defaultComponents) +
                ", spawn=" + Arrays.toString(spawn) +
                ", player=" + Arrays.toString(player) +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }

    public World() {
    }

    public World( String name, String owner, String publish, Light[] light, ObjModel[] objModel, Collision[] collision, Material[] material, Effect effect, defaultComponent[] defaultComponents, float[] spawn, float[] player, Long lastUpdateTime) {
        this.name = name;
        this.owner = owner;
        this.publish = publish;
        this.light = light;
        this.objModel = objModel;
        this.collision = collision;
        this.material = material;
        this.effect = effect;
        this.defaultComponents = defaultComponents;
        this.spawn = spawn;
        this.player = player;
        this.lastUpdateTime = lastUpdateTime;
    }
}
