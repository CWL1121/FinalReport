import { Route } from "../interfaces/Route"
import { PageController } from '../controller/pageController'
import rateLimit from "express-rate-limit";

export class PageRoute extends Route{
    
    protected url: string;
    protected Contorller = new PageController();

    constructor(){
        super()
        this.url = '/'
        this.setRoutes()
    }

    private limiter = rateLimit({
        windowMs: 1 * 60 * 1000, // 15 minutes
        max: 2, // Limit each IP to 100 requests per `window` (here, per 15 minutes)
        standardHeaders: true, // Return rate limit info in the `RateLimit-*` headers
        legacyHeaders: false, // Disable the `X-RateLimit-*` headers
    })
    
    
    protected setRoutes(): void {
        this.router.get(`${this.url}test`,this.limiter,this.Contorller.test)
        this.router.get(`${this.url}test2`,this.Contorller.test)
        this.router.get(`${this.url}`,this.Contorller.sendPage)
    }

}