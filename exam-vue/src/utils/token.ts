//封装本地存储存储数据与读取数据方法
//存储数据
import {LoginData} from "@/utils/type.ts";

export const SET_TOKEN = (token: string) => {
  localStorage.setItem('TOKEN', token)
}
//本地存储获取数据
export const GET_TOKEN = (data: LoginData) => {
  localStorage.setItem("uId", String(data.uId))
  localStorage.setItem("username", data.username)
}
//本地存储删除数据方法
export const REMOVE_TOKEN = () => {
  localStorage.removeItem("uId");
  localStorage.removeItem("username");
}
