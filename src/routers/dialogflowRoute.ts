import { Contorller } from "../interfaces/Contorller";
import { Route } from "../interfaces/Route"
import { DialogController } from "../controller/dialogController";
import { DilogFlow } from "../utils/DialogFlow";
import { response } from "express";

export class DialogflowRoute extends Route{

    protected url: string;
    protected Contorller:DialogController = new DialogController();
    protected ai: DilogFlow;

    constructor(){
        super()
        this.url = '/dialog'
        this.ai = new DilogFlow()
        this.setRoutes()
    }

    protected setRoutes(): void {
        this.router.get(`${this.url}/`,this.Contorller.test)
        this.router.get(`${this.url}/log`,this.Contorller.findAllLog)
        this.router.post(`${this.url}/text`,((require,response)=>{
            this.Contorller.text(require,response,this.ai)
        }))
        this.router.post(`${this.url}/audio`,((require,response)=>{
            this.Contorller.audio(require,response,this.ai)
        }))
    }
    
}