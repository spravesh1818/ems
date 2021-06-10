import { createWebHistory, createRouter } from "vue-router";
import PageNotFound from "./shared/components/PageNotFound";
import LandingPage from "./shared/components/LandingPage";
import employeeRoutes from "@/app/employee/employee-routes";
import adminRoutes from "@/app/admin/admin-routes";
import financeRoutes from "@/app/finance/employee-routes";
import hrRoutes from "@/app/hr/employee-routes";

const appRoutes = [
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

const routes=[...employeeRoutes,...appRoutes,...adminRoutes,...financeRoutes,...hrRoutes]
const router = createRouter({
    history: createWebHistory(),
    routes,
});
export default router;