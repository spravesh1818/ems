import FinanceDept from "@/app/finance/FinanceDept";
import CreateAndListSalarySlips from "@/app/finance/shared/components/CreateAndListSalarySlips";
import SalarySlipByEmployee from "@/app/finance/shared/components/SalarySlipByEmployee";
import UserProfile from "@/app/shared/components/UserProfile";

const financeRoutes = [
    {
        path: '/finance',
        component: FinanceDept,
        redirect:'/finance/salary-slip',
        children:[
            {
                path:"salary-slip",
                component:CreateAndListSalarySlips
            },
            {
                path:"salary-slip-by-employee",
                component:SalarySlipByEmployee
            },
            {
                path:'profile',
                component: UserProfile
            }
        ]
    }
];

export default financeRoutes;