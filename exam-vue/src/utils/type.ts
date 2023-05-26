export interface ApiResult {
  code: number,
  data: object,
  msg: string
}

export interface LoginData {
  uId: number,
  email: string,
  username: string
}

export interface Role {
  rId: number,
  name: string
}