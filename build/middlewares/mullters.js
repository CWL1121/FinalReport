"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.upload = void 0;
const multer_1 = __importDefault(require("multer"));
const log_1 = require("./log");
const storage = multer_1.default.diskStorage({
    destination: function (req, file, cb) {
        if (file.originalname.endsWith('.glb') || file.originalname.endsWith('.gltf')) {
            cb(null, 'assets/root/model');
        }
        else if (file.originalname.endsWith('.png') || file.originalname.endsWith('.jpg')) {
            cb(null, 'assets/root/material');
        }
        else if (file.originalname.endsWith('.mp4')) {
            cb(null, 'E:/ytNote-back/video'); //路徑問題
        }
    },
    filename: function (req, file, cb) {
        cb(null, file.originalname);
    }
});
const multerFilter = (req, file, callback) => {
    log_1.logger.info(file.originalname.endsWith('.mp4'));
    if (file.originalname.endsWith('.glb') || file.originalname.endsWith('.gltf')) {
        return callback(null, true);
    }
    else if (file.originalname.endsWith('.png') || file.originalname.endsWith('.jpg')) {
        return callback(null, true);
    }
    else if (file.originalname.endsWith('.mp4')) {
        return callback(null, true);
    }
    return callback(null, false);
};
exports.upload = (0, multer_1.default)({
    storage: storage,
    fileFilter: multerFilter
});
