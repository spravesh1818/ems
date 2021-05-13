import { createWebHistory, createRouter } from "vue-router";
import PageNotFound from "./shared/components/PageNotFound";
import LandingPage from "./shared/components/LandingPage";

const routes = [
    {
        path: "/",
        name: "Landing-Page",
        component: LandingPage,
    },
    {
        path: "/:catchAll(.*)",
        name: "Page-not-found",
        component: PageNotFound,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});
export default router;