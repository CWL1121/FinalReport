import { Contorller } from "../interfaces/Contorller";
import { Route } from "../interfaces/Route"
import { modelController } from "../controller/modelController";
import { upload } from "../middlewares/mullters";

export class ModelRoute extends Route{

    protected url: string;
    protected Contorller = new modelController();

    constructor(){
        super()
        this.url = '/model'
        this.setRoutes()
    }

    protected setRoutes(): void {
        this.router.get(`${this.url}/`,this.Contorller.test)
        this.router.get(`${this.url}/getModel`,this.Contorller.getModel)
        this.router.get(`${this.url}/useModel`,this.Contorller.findModel)
        this.router.post(`${this.url}/getPic`,this.Contorller.getPic)
        // this.router.patch(`${this.url}/update`, upload.single('file'), this.Contorller.uploadModel)
    }
    
}