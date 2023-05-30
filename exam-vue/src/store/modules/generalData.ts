import request from "@/utils/request.ts";
import {ApiResult} from "@/utils/type.ts";
import {defineStore} from "pinia";
import useUserStore from "@/store/modules/user.ts";

export const useGeneralDataStore = defineStore('generalDataStore',
    {
      state: () => {
        return {
          topicTypeDict: {},
          difficultyDict: {},
          tagDict: {}
        }
      },
      actions: {
        async getTopicTypeDict() {
          let {data} = await request.get<any, ApiResult>('/topicType')
          let tempData = data as [];
          this.topicTypeDict = tempData.reduce((obj, item) => {
            // @ts-ignore
            obj[item.typeId] = item.name;
            return obj;
          }, {});
        },
        async getDifficultyDict() {
          const {data} = await request.get<any, ApiResult>('/difficulty')
          let tempData = data as [];
          this.difficultyDict = tempData.reduce((obj, item) => {
            // @ts-ignore
            obj[item.typeId] = item.name;
            return obj;
          }, {});
        },
        async getTagDict() {
          const userStore = useUserStore();
          const {data} = await request.get<any, ApiResult>(`/topicType/${userStore.uId}`)
          let tempData = data as [];
          this.tagDict = tempData.reduce((obj, item) => {
            // @ts-ignore
            obj[item.typeId] = item.name;
            return obj;
          }, {});
        },
      }
    }
)
