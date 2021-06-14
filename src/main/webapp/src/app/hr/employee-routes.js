import HrDept from "@/app/hr/HrDept";
import AttendenceList from "@/app/hr/shared/components/AttendanceList";
import ShiftList from "@/app/hr/shared/components/ShiftList";
import UserProfile from "@/app/shared/components/UserProfile";

const hrRoutes = [
    {
        path: '/hr',
        component: HrDept,
        redirect:'/hr/attendance',
        children:[
            {
                path:'attendance',
                component:AttendenceList
            },
            {
                path: 'shift',
                component:ShiftList
            },
            {
                path:'profile',
                component: UserProfile
            }
        ]

    }
];

export default hrRoutes;