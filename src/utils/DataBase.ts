import { Schema, model, connect } from 'mongoose';
import { InteractLog } from '../models/interactLog';
import { interact } from '../interfaces/interact';
import { worlds, lights, objModels, collisions, materials } from '../models/world';
import { logger } from '../middlewares/log';
import { collision, light, material, objModel, worldinfo } from '../interfaces/worldInterfaces';

export class DataBase {
    DB!: typeof import("mongoose");

    constructor(url: string) {
        this.init(url).then(() => {
            logger.info(`suscess: connet to ${url} `);
        }).catch(() => {
            logger.error(`error: cannt connet to ${url} `);
        })
    }
    async init(url: string): Promise<void> {
        this.DB = await connect(url)
    }

    static async newWorld(name: string): Promise<any> {
        const AmbientLight = new lights({
            type: "AmbientLight",
            name:"AmbientLight",
            color: "#ffffff",
            intensity: 1,
            position: [0, 0, 0],
            Shadow: false,
        })
        const world = new worlds({
            name: name,
            light: [AmbientLight],
            objModel: [],
            collision: [],
            materials:[],
            spawn:[0,0,0],
            player:[0.75,0.75]
        });
        await world.save();
        return world
    }

    static async findAllWorld(): Promise<any> {
        const ans = await worlds.find()
        return ans
    }
    static async findWorldById(id: string): Promise<any> {
        const ans = await worlds.findById(id)
        return ans
    }
    static async testDel(): Promise<any> {
        worlds.remove({}, function (err: any) { // 筛选条件为空即是表示所有
            if (!err) {
                return ("success");
            } else {
                return (err);
            }
        });
    }

    static async delbyid(id: string): Promise<any> {
        const ans = await worlds.findByIdAndDelete(id)
        return ans
    }

    static async update(id: string, info: worldinfo): Promise<any> {
        const ans = await worlds.findByIdAndUpdate(id, {
            name: info.name,
            light: info.lights,
            objModel: info.models,
            collision: info.collisions,
            material: info.materials,
            spawn:info.spawn,
            player:info.player
        })
        return ans
    }

    static async newLight(id: string): Promise<any> {
        const light = new lights({
            type: "spotLight",
            name:"new light",
            color: "#ffffff",
            intensity: 1,
            position: [0, 0, 0],
            Shadow: false,
        })
        return light
    }

    static async newModel(id: string, info: objModel): Promise<any> {
        const model = new objModels({
            type: info.type,
            url: info.url,
            scale: info.scale,
            position: info.position,
            rotation: info.rotation,
            name: info.name,
            anime: info.anime,
        });
        return model
    }
    static async newMaterial(id: string,): Promise<any> {
        const material = new materials({
            type:"MeshBasicMaterial",
            name: "newMaterial",
            color:"0xffffff",
            imgpath:"",
            displacementScale:1,
            emissive:"0x000000",
            emissiveIntensity:1,
            transparent:false,
            flatShading:false,
            fog:true,
            opacity:1,
            metalness:0,
            refractionRatio:0.98,
            roughness:1.0,
        });
        return material
    }
    static async newCollision(id: string,): Promise<any> {
        const collision = new collisions({
            name: "new collision",
            clickable: false,
            scale:[1,1,1],
            position:[0,0,0],
            rotation:[0,0,0],
            material:undefined,
            onClick:"",
            onPointerOver:"",
            onPointerOut:"",
        });
        return collision
    }
    static async PutInteract(info: { ID: any, User: any, Agent: any, Time: any }): Promise<void> {
        let ans = new InteractLog({
            ID: info.ID,
            User: info.User,
            Agent: info.Agent,
            Time: info.Time,
        })
        await ans.save();
    }
    static async findAllInteract(): Promise<any> {
        const ans = await InteractLog.find()
        return ans
    }

}

