"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.PageController = void 0;
const Contorller_1 = require("../interfaces/Contorller");
const log_1 = require("../middlewares/log");
const path = "E:/demo/FinalReport/src/dist/index.html";
class PageController extends Contorller_1.Contorller {
    test(Request, Response) {
        log_1.logger.info(`${Request.ip} sendFile`);
        Response.sendFile("E:/demo/FinalReport/src/assets/test2.mkv");
    }
    test2(Request, Response) {
        log_1.logger.info(`${Request.ip} sendFile`);
        Response.sendFile("E:/demo/FinalReport/src/assets/test2.mkv");
    }
    sendPage(Request, Response) {
        Response.sendFile(path, (err => {
            log_1.logger.error(`${Request.ip} used sendPage(): ${err}`);
        }));
    }
}
exports.PageController = PageController;
