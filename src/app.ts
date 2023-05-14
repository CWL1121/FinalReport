import express from 'express'
import {router} from "./Routers"
import { DataBase } from './utils/DataBase';
import { logger } from './middlewares/log';
const http = require('http');
import cors from 'cors';
const port = 80

require('dotenv').config()
const app: express.Application = express()
const server = http.createServer(app);
// app.use(cors({
//   "origin": "http://localhost:5173",
//   "methods": "GET,HEAD,PUT,PATCH,POST,DELETE",
//   "preflightContinue": false,
//   "optionsSuccessStatus": 200
// }))
app.use(cors({credentials: true, origin: 'http://localhost:5173'}));
const DB = new DataBase(<string>process.env.DB)

app.use(express.json({limit:'50mb'}));
app.use(express.urlencoded({ extended: false }))
app.use('/assets', express.static('D:\_R3f-editor-back\assets'));

for (const route of router) {
  app.use(route.getRouter())
}

server.listen(port, () => {
  logger.info('listening on *:'+port);
});


