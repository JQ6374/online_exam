// @ts-ignore
import {ElNotification} from "element-plus";
import {Code} from "@/utils/Code.ts";
import {ApiResult} from "@/utils/type.ts";

const myElNotification = (res: ApiResult, codeOk: number, handleName: string) => {
  const isSuccess = res.code == codeOk;
  ElNotification({
    title: isSuccess ? `${handleName}成功` : `${handleName}失败`,
    message: isSuccess ? res.msg : Code.ERROR_MSG,
    type: isSuccess ? 'success' : 'error',
  })
}

export default myElNotification;