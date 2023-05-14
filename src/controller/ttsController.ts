import { Contorller } from "../interfaces/Contorller";
import { Request, Response } from "express";
import { textToSpeech } from "../utils/tts";
import { logger } from "../middlewares/log";
import { PassThrough } from 'stream';

export class TtsController extends Contorller {


    public test(Request: Request, Response: Response): void {
        textToSpeech("0ac956fa742042a1b3ef8da0a41b9402","eastus",Request.body.Note).then((res)=>{
            Response.send({
                status:200,
                body:res
            })
        })
    }

    constructor() {
        super();
    }

}