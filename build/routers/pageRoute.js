"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.PageRoute = void 0;
const Route_1 = require("../interfaces/Route");
const pageController_1 = require("../controller/pageController");
const express_rate_limit_1 = __importDefault(require("express-rate-limit"));
class PageRoute extends Route_1.Route {
    constructor() {
        super();
        this.Contorller = new pageController_1.PageController();
        this.limiter = (0, express_rate_limit_1.default)({
            windowMs: 1 * 60 * 1000,
            max: 2,
            standardHeaders: true,
            legacyHeaders: false, // Disable the `X-RateLimit-*` headers
        });
        this.url = '/';
        this.setRoutes();
    }
    setRoutes() {
        this.router.get(`${this.url}test`, this.limiter, this.Contorller.test);
        this.router.get(`${this.url}test2`, this.Contorller.test);
        this.router.get(`${this.url}`, this.Contorller.sendPage);
    }
}
exports.PageRoute = PageRoute;
