import { httpClient } from '@/app/shared/services';

import {apiBaseUrl} from "@/environment/environment";

const headers = {
    'Content-Type': 'application/json',
    'Authorization': "Bearer "+localStorage.getItem("token")
}

// const getAllEmployees = () => {
//     const url = `${apiBaseUrl}/admin/employee`;
//     console.log(headers.Authorization);
//     return httpClient.get(url,{headers:headers}).then(res =>res);
// };
//
// const addAnEmployee=(data)=>{
//     const url = `${apiBaseUrl}/admin/employee`;
//     console.log(headers.Authorization);
//     console.log("Adding employee");
//     return httpClient.post(url,{firstName:data.firstName,lastName: data.lastName,email:data.email,role:data.role},{headers:headers}).then(res =>res);
// }
//
// const deleteEmployee=(id)=>{
//     const url=`${apiBaseUrl}/admin/employee/${id}`;
//     return httpClient.delete(url,{headers:headers}).then(res =>res);
// }
//
// const editEmployee=(data)=>{
//     console.log(data);
//     const url=`${apiBaseUrl}/admin/employee/${data.id}`;
//     return httpClient.put(url,data,{headers:headers}).then(res =>res);
// }


// const getAllLeaves=()=>{
//     const url=`${apiBaseUrl}/leave`;
//
//     return httpClient.get(url,{headers:headers}).then(res =>res);
// }

const addLeaveRequest=(data)=>{
    console.log(data);
    const url=`${apiBaseUrl}/leave/employee`;
    return httpClient.post(url,data,{headers:headers}).then(res =>res);
}

const getAllLeavesOfAnEmployee=()=>{
    const url=`${apiBaseUrl}/leave/employee/all`;
    return httpClient.get(url,{headers:headers}).then(res=>res);
}

const deleteEmployeeLeave=(id)=>{
    const url=`${apiBaseUrl}/leave/${id}`;
    return httpClient.delete(url,{headers:headers}).then(res=>res);
}
//
// const deleteSalarySlip=(id)=>{
//     const url=`${apiBaseUrl}/salary-slip/${id}`;
//     return httpClient.delete(url,{headers:headers}).then(res =>res);
// }
//
// const salarySlipEdit=(data)=>{
//     console.log(data);
//     const url=`${apiBaseUrl}/salary-slip/${data.employee_id}`;
//     return httpClient.put(url,data,{headers:headers}).then(res =>res);
// }

export { addLeaveRequest,getAllLeavesOfAnEmployee,deleteEmployeeLeave };