import Admin from "@/app/admin/Admin";
import UserListComponent from "@/app/admin/shared/components/UserListComponent";
import UserProfile from "@/app/shared/components/UserProfile";



const adminRoutes = [
    {
        path: '/admin/',
        component: Admin,
        redirect:'/admin/employee-list',
        children:[
            {
                path:'employee-list',
            component:UserListComponent
            },
            {
                path:'profile',
                component: UserProfile
            }
        ]

    }
];

export default adminRoutes;