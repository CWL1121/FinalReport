"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const Routers_1 = require("./Routers");
const log_1 = require("./middlewares/log");
const http = require('http');
const cors_1 = __importDefault(require("cors"));
const port = 444;
const app = (0, express_1.default)();
const server = http.createServer(app);
app.use((0, cors_1.default)({
    "origin": "http://localhost:5173",
    "methods": "GET,HEAD,PUT,PATCH,POST,DELETE",
    "preflightContinue": false,
    "optionsSuccessStatus": 200
}));
app.use(express_1.default.json({ limit: '5000mb' }));
app.use(express_1.default.urlencoded({ extended: false }));
app.use('/assets', express_1.default.static('E:/demo/FinalReport/src/dist/assets'));
for (const route of Routers_1.router) {
    app.use(route.getRouter());
}
server.listen(port, () => {
    log_1.logger.info('listening on *:' + port);
});
