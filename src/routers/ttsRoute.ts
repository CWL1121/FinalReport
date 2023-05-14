import { Route } from "../interfaces/Route"
import { TtsController } from "../controller/ttsController";

export class TtsRoute extends Route{
    
    protected url: string;
    protected Contorller = new TtsController();

    constructor(){
        super()
        this.url = '/tts'
        this.setRoutes()
    }

    protected setRoutes(): void {
        this.router.post(`${this.url}/`,this.Contorller.test)
    }

}