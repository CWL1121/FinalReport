import express from 'express'
import rateLimit from 'express-rate-limit'
import {router} from "./Routers"
import { logger } from './middlewares/log';
const http = require('http');
import cors from 'cors';
const port = 444
const app: express.Application = express()
const server = http.createServer(app);
app.use(cors({
  "origin": "http://localhost:5173",
  "methods": "GET,HEAD,PUT,PATCH,POST,DELETE",
  "preflightContinue": false,
  "optionsSuccessStatus": 200
}))

app.use(express.json({limit:'5000mb'}));
app.use(express.urlencoded({ extended: false }))
app.use('/assets', express.static('E:/demo/FinalReport/src/dist/assets'));
for (const route of router) {
  app.use(route.getRouter())
}

server.listen(port, () => {
  logger.info('listening on *:'+port);
});


