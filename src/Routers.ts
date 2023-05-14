import {Route} from "./interfaces/Route";
import { PageRoute } from "./routers/pageRoute";
//將新的路由插進來
export const router: Array<Route> = [
    new PageRoute()
    //要new一個路由路徑
];

