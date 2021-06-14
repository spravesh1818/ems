import { httpClient } from '@/app/shared/services';

import {apiBaseUrl} from "@/environment/environment";

const headers = {
    'Content-Type': 'application/json',
    'Authorization': "Bearer "+localStorage.getItem("token")
}

const getUserInformation=()=>{
    const url=`${apiBaseUrl}/users/me`;
    return httpClient.get(url,{headers:headers}).then(res=>res);
}

const changePassword=(data)=>{
    console.log(data);
    const jsonData={id:data.user.id,oldPassword: data.oldPassword,password:data.newPassword,confirmPassword:data.confirmNewPassword}
    const url=`${apiBaseUrl}/users/change-password`;
    return httpClient.post(url,jsonData,{headers:headers}).then(res=>res);
}


export { getUserInformation,changePassword };