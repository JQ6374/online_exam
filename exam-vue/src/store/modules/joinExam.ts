import {defineStore} from "pinia";
import myRequest from "@/utils/request.ts";
import {ApiResult} from "@/utils/type.ts";

interface ExamInfo {
  eId: string,
  uId: string,
  cId: string,
  pId: string,
  name: string,
  startTime: string,
  endTime: string,
  status: string,
  isExist: string,
  content: string,
}

export const useJoinExamStore = defineStore("joinExamStore", {
  state: () => {
    return {
      eId: '',
      pId: '',
      examName: '',
      papersContent: '',
      startTime: '',
      endTime: '',
      status: ''
    }
  },
  actions: {
    async getExamInfo(eId: number) {
      const {data} = await myRequest.get<any, ApiResult<ExamInfo>>(`/exam/selectOne/student/${eId}`)
      this.eId = data.eId;
      this.pId = data.pId
      this.examName = data.name
      this.startTime = data.startTime
      this.endTime = data.endTime;
      this.papersContent = JSON.parse(data.content)
      this.status = data.status
    }
  }
})

