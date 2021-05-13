
import { httpClient } from '@/app/shared/services';

import {apiBaseUrl} from "@/environment/environment";

const fetchUsers = (username,password) => {
    const url = `${apiBaseUrl}/authenticate`;
    console.log(username);
    return httpClient.post(url,{"username":username,"password":password}).then(res => res.data);
};

export { fetchUsers };