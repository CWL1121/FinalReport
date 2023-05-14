import { Contorller } from "../interfaces/Contorller";
import { Request, Response } from "express";
import { DataBase } from "../utils/DataBase";
import { logger } from "../middlewares/log";
const path:string = "/home/ethci/projects/userAssets"

export class modelController extends Contorller {
  public test(Request: Request, Response: Response) {
    Response.download(path + "skyBox/scene.glb", "scene.glb")
  }
  public findModel(Request: Request, Response: Response) {
    logger.info(`${Request.ip} used findModel() : suscess download ${path}${Request.query.path}`)
    Response.download(`${path}${Request.query.path}`, "scene.glb")
  }
  public getModel(Request: Request, Response: Response) {
    Response.sendFile(`${path}/${Request.query.name}/model/${Request.query.model}`)
  }
  public getPic(Request: Request, Response: Response) {
    Response.sendFile(`${path}/${Request.body.name}/snapshot/${Request.body.id}.txt`)
  }
  // public uploadModel(Request: Request, Response: Response) {
  //   if (Request.file) {
  //     logger.info(`${Request.ip} used uploadModel() : suscess upload ${Request.file.destination}`)
  //     const path = `${Request.file.destination}/${Request.file.originalname}`
  //     let destination: string | string[] = Request.file.originalname.split(".")
  //     destination = destination[destination.length - 1]
  //     DataBase.newModel(<string>Request.query.id, {
  //       type: destination,
  //       url: path,
  //       scale: 1,
  //       position: [0, 0, 0],
  //       rotation: [0, 0, 0],
  //       name: "new model",
  //       anime: "",
  //     }).then(res => {
  //       Response.send({
  //         status: "ok",
  //         path: path,
  //         model: res
  //       })
  //     }).catch(err => {
  //       logger.error(`${Request.ip} used uploadModel(): ${err}`)
  //       Response.send(err)
  //     })

  //   } else {
  //     logger.error(`${Request.ip} used uploadModel(): no file`)
  //     Response.send("no file")
  //   }
  // }
}

