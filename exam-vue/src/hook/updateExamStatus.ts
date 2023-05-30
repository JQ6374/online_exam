import myRequest from "@/utils/request.ts";

export const loadExamStatus = async () => {
  await myRequest.get('/exam/updateAllStatus'); // 发送请求获取考试数据
}