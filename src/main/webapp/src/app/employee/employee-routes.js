import Employee from "@/app/employee/Employee";
import EmployeeAttendenceList from "@/app/employee/shared/components/EmployeeAttendenceList";
import EmployeeSalarySlip from "@/app/employee/shared/components/EmployeeSalarySlip";
import EmployeeShiftList from "@/app/employee/shared/components/EmployeeShiftList";
import UserProfile from "@/app/shared/components/UserProfile";
import EmployeeLeaveInformation from "@/app/employee/shared/components/EmployeeLeaveInformation";

const employeeRoutes = [
    {
        path: '/employee',
        component: Employee,
        redirect:"/employee/attendance-list",
        children:[
            {
                path:'attendance-list',
                component:EmployeeAttendenceList
            },
            {
                path:'salary-slip',
                component:EmployeeSalarySlip
            },
            {
                path:'shifts',
                component:EmployeeShiftList
            },
            {
                path: 'leave',
                component: EmployeeLeaveInformation
            },
            {
                path:'profile',
                component: UserProfile
            }
        ]

    }
];

export default employeeRoutes;