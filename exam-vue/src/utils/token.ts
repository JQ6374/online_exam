//封装本地存储存储数据与读取数据方法
//存储数据

export const SET_TOKEN = (token: string, uId: string, username: string) => {
  localStorage.setItem('token', token)
  localStorage.setItem('uId', uId)
  localStorage.setItem('username', username)
}

//本地存储删除数据方法
export const REMOVE_TOKEN = () => {
  localStorage.removeItem("token");
  localStorage.removeItem("uId");
  localStorage.removeItem("username");
}
