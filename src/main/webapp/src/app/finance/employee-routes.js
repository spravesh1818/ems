import FinanceDept from "@/app/finance/FinanceDept";
import CreateAndListSalarySlips from "@/app/finance/shared/components/CreateAndListSalarySlips";
import SalarySlipByEmployee from "@/app/finance/shared/components/SalarySlipByEmployee";

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
            }
        ]
    }
];

export default financeRoutes;