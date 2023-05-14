import { Contorller } from "../interfaces/Contorller";
import { Request, Response } from "express";
import { DilogFlow } from "../utils/DialogFlow";
import { DataBase } from "../utils/DataBase";
import { logger } from "../middlewares/log";

export class DialogController extends Contorller {

  constructor() {
    super();
  }

  public test(Request: Request, Response: Response) {
    Response.send("DialogController: nmsl")
  }
  public text(Request: Request, Response: Response, DilogAi: DilogFlow) {
    DilogAi.detectIntent(Request.body.text, Request.body.id).then(res => {
      logger.info(`${Request.ip} used DilogFlow.text() : suscess`)
      Response.send({
        input: res.response[0].queryResult.queryText,
        text: res.response[0].queryResult.fulfillmentText,
        audio: res.response[0].outputAudio
      })
    }).catch(err => {
      console.log(err)
    })

  }
  public audio(Request: Request, Response: Response, DilogAi: DilogFlow) {

    DilogAi.detectIntentAudio(Request.body.audio, Request.body.id).then(res => {
      logger.info(`${Request.ip} used DilogFlow.audio() : suscess`)
      Response.send({
        input: res.response[0].queryResult.queryText,
        text: res.response[0].queryResult.fulfillmentText,
        audio: res.response[0].outputAudio
      })
      if (res.response[0].queryResult.fulfillmentText != "") {
        logger.info(`${Request.ip} used DilogFlow.audio() : empty res`)
        let date = new Date( Date.now());
        DataBase.PutInteract({
          ID: Request.body.id,
          User: res.response[0].queryResult.queryText,
          Agent: res.response[0].queryResult.fulfillmentText,
          Time: `${date.getFullYear()}/${date.getMonth() + 1}/${date.getDate()},${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`
        })
      }
    }).catch(err => {
      console.log(err)
    })

  }
  public findAllLog(Request: Request, Response: Response){
    DataBase.findAllInteract().then(ans=>{
      Response.send(ans)
    }).catch(err=>{
      Response.send(err)
    })
  }
}