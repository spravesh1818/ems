import { httpClient } from '@/app/shared/services';

import {apiBaseUrl} from "@/environment/environment";

const headers = {
    'Content-Type': 'application/json',
    'Authorization': "Bearer "+localStorage.getItem("token")
}

const getAllEmployees = () => {
    const url = `${apiBaseUrl}/admin/employee`;
    console.log(headers.Authorization);
    return httpClient.get(url,{headers:headers}).then(res =>res);
};

const addAnEmployee=(data)=>{
    const url = `${apiBaseUrl}/admin/employee`;
    console.log(headers.Authorization);
    return httpClient.post(url,{firstName:data.firstName,lastName: data.lastName,email:data.email},{headers:headers}).then(res =>res);
}

const deleteEmployee=(id)=>{
    const url=`${apiBaseUrl}/admin/employee/${id}`;
    return httpClient.delete(url,{headers:headers}).then(res =>res);
}

const editEmployee=(data)=>{
    console.log(data);
    const url=`${apiBaseUrl}/admin/employee/${data.id}`;
    return httpClient.put(url,data,{headers:headers}).then(res =>res);
}

export { getAllEmployees,addAnEmployee,deleteEmployee,editEmployee};