import {defineStore} from "pinia";

export const useMainHeight = defineStore('mainHeightStore',
    {
      state: () => {
        return {
          mainHeight: 460
        }
      }
    }
)