import {Route} from "./interfaces/Route";
import { TestRoute } from "./routers/testRoute";
import { ModelRoute } from "./routers/modelRoute";
import { DialogflowRoute } from "./routers/dialogflowRoute";
import { PageRoute } from "./routers/pageRoute";
import { TtsRoute } from "./routers/ttsRoute";

export const router: Array<Route> = [
    new TestRoute(),new ModelRoute(),new DialogflowRoute(),new PageRoute(),new TtsRoute()
];

