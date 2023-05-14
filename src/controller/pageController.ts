import { Contorller } from "../interfaces/Contorller";
import { Request, Response } from "express";
import { logger } from "../middlewares/log";
const path  = "E:/demo/FinalReport/src/dist/index.html"


export class PageController extends Contorller {
  public test(Request: Request, Response: Response) {
    logger.info(`${Request.ip} sendFile`)
    Response.sendFile("E:/demo/FinalReport/src/assets/test2.mkv")
  }
  public test2(Request: Request, Response: Response) {
    logger.info(`${Request.ip} sendFile`)
    Response.sendFile("E:/demo/FinalReport/src/assets/test2.mkv")
  }
  public sendPage(Request: Request, Response: Response) {
    Response.sendFile(path,(err=>{
      logger.error(`${Request.ip} used sendPage(): ${err}`)
    }))
  }
}