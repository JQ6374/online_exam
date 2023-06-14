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

// @ts-ignore
export const useJoinExamStore = defineStore("joinExamStore", {
  state: () => {
    return {
      examInfo: JSON.parse(localStorage.getItem("examInfo") || ''),
    }
  },
  getters: {
    papersContent(): Object {
      return JSON.parse(this.examInfo.content);
    },
    eId(): string {
      return this.examInfo.eId;
    },
    pId(): string {
      return this.examInfo.pId;
    },
    examName(): string {
      return this.examInfo.name;
    },
    startTime(): string {
      return this.examInfo.startTime;
    },
    endTime(): string {
      return this.examInfo.endTime;
    },
    status(): string {
      return this.examInfo.status;
    },
  },
  actions: {
    async getExamInfo(eId: number) {
      const {data} = await myRequest.get<any, ApiResult<ExamInfo>>(`/exam/selectOne/student/${eId}`)
      localStorage.setItem("examInfo", JSON.stringify(data))
    }
  }
})

