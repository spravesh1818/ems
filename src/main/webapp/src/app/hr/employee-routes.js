import HrDept from "@/app/hr/HrDept";
import AttendenceList from "@/app/hr/shared/components/AttendanceList";
import ShiftList from "@/app/hr/shared/components/ShiftList";

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
            }
        ]

    }
];

export default hrRoutes;