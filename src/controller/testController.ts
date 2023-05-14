import { Contorller } from "../interfaces/Contorller";
import {Request, Response} from "express";
import { DataBase } from "../utils/DataBase";
import { logger } from "../middlewares/log";
import { pic } from "../utils/picConvert";
import { writeFileSync } from 'fs';
import { join } from 'path';

const path:string = "/home/ethci/projects/userAssets"

export class TestController extends Contorller {
  public test(Request:Request, Response:Response) {
    Response.send(Request.socket.remoteAddress)
  }
  public getPicture(Request:Request, Response:Response) {
    logger.info(`${Request.ip} used getPicture() : suscess get ${path}${Request.query.path}`)
    Response.sendFile(`${path}/${Request.query.name}/defaultComponent/${Request.query.picture}`)
  }
  public getMaterial(Request:Request, Response:Response) {
    logger.info(`${Request.ip} used getMaterial() : suscess get ${path}${Request.query.path}`)
    Response.sendFile(`${path}/${Request.query.name}/material/${Request.query.material}`)
  }
}