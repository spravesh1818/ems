import { httpClient } from '@/app/shared/services';

import {apiBaseUrl} from "@/environment/environment";

const headers = {
    'Content-Type': 'application/json',
    'Authorization': "Bearer "+localStorage.getItem("token")
}



const getAllAttendances=()=>{
    const url=`${apiBaseUrl}/attendence`;
    return httpClient.get(url,{headers:headers}).then(res =>res);
}

const addAttendance=(data)=>{
    console.log(data);
    const url=`${apiBaseUrl}/attendence`;
    return httpClient.post(url,{id:0,date:data.date,timeArrived:data.timeArrived+" "+data.timeArrivedSuffix,timeLeft:data.timeLeft+" "+data.timeLeftSuffix,totalHours:data.totalHours,employee_id:data.employee_id},{headers:headers}).then(res =>res);
}

const getAllAttendanceOfAnEmployee=(id)=>{
    const url=`${apiBaseUrl}/attendence/employee/${id}`;
    return httpClient.get(url,{headers:headers}).then(res=>res);
}

const deleteAttendance=(id)=>{
    const url=`${apiBaseUrl}/attendence/${id}`;
    return httpClient.delete(url,{headers:headers}).then(res =>res);
}

const attendanceEdit=(data)=>{
    const id=data.id;
    const url=`${apiBaseUrl}/attendence/${id}`;
    console.log("Edit data info");
    console.log(data);
    return httpClient.put(url,data,{headers:headers}).then(res =>res);
}

export { getAllAttendances,getAllAttendanceOfAnEmployee,deleteAttendance,attendanceEdit,addAttendance };