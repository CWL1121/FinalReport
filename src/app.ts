import express from 'express'
import {router} from "./Routers"
import { DataBase } from './utils/DataBase';
import { logger } from './middlewares/log';
const http = require('http');
import cors from 'cors';
const port = 444
//http://localhost:444
require('dotenv').config()
const app: express.Application = express()
const server = http.createServer(app);
app.use(cors({
  "origin": "http://localhost:5173",
  "methods": "GET,HEAD,PUT,PATCH,POST,DELETE",
  "preflightContinue": false,
  "optionsSuccessStatus": 200
}))
const DB = new DataBase("mongodb://root:ethci2925@163.13.172.197:27018/noteDB")

app.use(express.json({limit:'50mb'}));
app.use(express.urlencoded({ extended: false }))
app.use('/assets', express.static('/home/l676/project/R3f-editor-back/ETHCI-r3f-editor/dist/assets'));

for (const route of router) {
  app.use(route.getRouter())
}

server.listen(port, () => {
  logger.info('listening on *:'+port);
});


