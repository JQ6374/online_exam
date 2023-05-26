import myRequest from "@/utils/request.ts";
import {ApiResult} from "@/utils/type.ts";

const urlBase = '/user';

enum API {
    LOGIN_URL = urlBase + '/user',
    REGISTER_URL = urlBase + '/register',
    UPDATE_URL = urlBase + '/updatePassword'
}

// 登录接口
export const reqLogin = (data: object) => myRequest.post<any, ApiResult>(API.LOGIN_URL, data);
export const reqRegister = (data: object) => myRequest.post<any, ApiResult>(API.REGISTER_URL, data);
export const reqUpdatePassword = (data: object) => myRequest.put<any, ApiResult>(API.UPDATE_URL, data);