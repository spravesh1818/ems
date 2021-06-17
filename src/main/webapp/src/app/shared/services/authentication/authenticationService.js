
import { httpClient } from '@/app/shared/services';

import {apiBaseUrl} from "@/environment/environment";

const fetchUsers = async (username,password) => {
    const url = `${apiBaseUrl}/users/signin?username=${username}&password=${password}`;
    console.log(username);
    return httpClient.post(url).then(res => res.data);
};

export { fetchUsers };