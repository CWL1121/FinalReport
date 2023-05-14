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
const limiter = rateLimit({
	windowMs: 15 * 60 * 1000, // 15 minutes
	max: 100, // Limit each IP to 100 requests per `window` (here, per 15 minutes)
	standardHeaders: true, // Return rate limit info in the `RateLimit-*` headers
	legacyHeaders: false, // Disable the `X-RateLimit-*` headers
})

app.use(limiter)
app.use(express.json({limit:'50mb'}));
app.use(express.urlencoded({ extended: false }))

for (const route of router) {
  app.use(route.getRouter())
}

server.listen(port, () => {
  logger.info('listening on *:'+port);
});


