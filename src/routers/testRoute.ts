import { Route } from "../interfaces/Route"
import { TestController } from '../controller/testController'
import { upload } from "../middlewares/mullters";

export class TestRoute extends Route{
    
    protected url: string;
    protected Contorller = new TestController();

    constructor(){
        super()
        this.url = '/test'
        this.setRoutes()
    }

    protected setRoutes(): void {
        this.router.get(`${this.url}/`,this.Contorller.test)
        // this.router.get(`${this.url}/find`,this.Contorller.findByID)
        // this.router.get(`${this.url}/findAll`,this.Contorller.getInfo)
        // this.router.post(`${this.url}/delbyid`,this.Contorller.delebyID)
        // this.router.post(`${this.url}/newWorld`,this.Contorller.newWorld)
        // this.router.post(`${this.url}/save`,this.Contorller.saveWorld)
        // this.router.post(`${this.url}/savePic`,this.Contorller.savePic)
        // this.router.post(`${this.url}/newLight`,this.Contorller.newLight)
        // this.router.post(`${this.url}/newCollision`, this.Contorller.newCollision)
        // this.router.post(`${this.url}/newMaterial`, this.Contorller.newMaterial)
        // this.router.patch(`${this.url}/updateMaterial`, upload.single('file'), this.Contorller.uploadMaterial)
        this.router.get(`${this.url}/getPicture`, this.Contorller.getPicture)
        this.router.get(`${this.url}/getMaterial`, this.Contorller.getMaterial)
    }

}