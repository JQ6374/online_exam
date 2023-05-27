// @ts-ignore
import {ElNotification} from "element-plus";
import {Code} from "@/utils/Code.ts";
import {ApiResult} from "@/utils/type.ts";
import setting from "@/setting.ts";

/**
 *
 * @param res 请求返回的数据
 * @param codeOk 请求的类型码
 * @param handleName 操作类型名字
 * @constructor
 */
export const MyElNotification = (res: ApiResult, codeOk: number, handleName: string) => {
  const isSuccess = res.code == codeOk;
  ElNotification({
    title: isSuccess ? `${handleName}成功!` : `${handleName}失败!`,
    message: isSuccess ? res.msg : Code.ERROR_MSG,
    type: isSuccess ? 'success' : 'warning',
    duration: setting.duration
  })
}
