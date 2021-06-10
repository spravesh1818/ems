import Admin from "@/app/admin/Admin";
import UserListComponent from "@/app/admin/shared/components/UserListComponent";
import TestComponent from "@/app/admin/shared/components/TestComponent";


const adminRoutes = [
    {
        path: '/admin/',
        component: Admin,
        children:[
            {
                path:'employee-list',
            component:UserListComponent
            },
            {
                path:'test',
                component: TestComponent
            }
        ]

    }
];

export default adminRoutes;