export interface ApiResult<T = Object> {
  code: number,
  data: T,
  msg: string
}

export interface LoginData {
  uId: string,
  email: string,
  username: string
}

export interface Role {
  rId: number,
  name: string
}

export interface Topic {
  index: number,
  tId: number,
  uId: number,
  typeId: number,
  typeName: string,
  tagId: number,
  tagName: string,
  difficultyId: number,
  difficultyName: string,
  question: string,
  answer: string,
  status: string,
  isExist: string,
  createTime: string
}
