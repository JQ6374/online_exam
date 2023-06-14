package com.zz.controller;

import com.alibaba.fastjson.JSONObject;
import com.zz.Service.TopicService;
import com.zz.bean.Topic;
import com.zz.utils.Code;
import com.zz.utils.result.ApiResult;
import com.zz.utils.result.TempResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping
    public ApiResult<Object> addTopic(@RequestBody Topic topic) {
        TempResult tempResult = topicService.addTopic(topic);
        return new ApiResult<>(tempResult.isFlag() ? Code.SAVA_OK : Code.SAVA_ERR,
                null, tempResult.getMsg());
    }

    @GetMapping("/{uId}")
    public ApiResult<List<Topic>> selectByUId(@PathVariable Integer uId) {
        return new ApiResult<>(Code.GET_OK, topicService.selectByUId(uId), null);
    }

    /**
     * 根据题型、标签、难度、题目进行搜索
     * @param uId 教师Id
     * @param info 搜索值
     * @return 接口
     */
    @GetMapping("/{uId}/{info}")
    public ApiResult<List<Topic>> searchByInfo(@PathVariable Integer uId,
                                             @PathVariable String info) {
        return topicService.searchByInfo(uId, info);
    }

    @DeleteMapping("/{tId}")
    public ApiResult<Object> delTopic(@PathVariable Integer tId) {
        TempResult tempResult = topicService.delTopic(tId);
        return new ApiResult<>(tempResult.isFlag() ? Code.DELETE_OK : Code.DELETE_ERR,
                null, tempResult.getMsg());
    }

    @PutMapping
    public ApiResult<Object> updateTop(@RequestBody Topic topic) {
        TempResult tempResult = topicService.updateTopic(topic);
        return new ApiResult<>(tempResult.isFlag() ? Code.UPDATE_OK : Code.UPDATE_ERR,
                null, tempResult.getMsg());
    }

    @PostMapping("/topicToPapers")
    public ApiResult<Object> topicToPapers(@RequestBody JSONObject params) {
        Integer uId = params.getInteger("uId");
        Integer[] tIds = params.getObject("tIds", Integer[].class);
        String name = params.getString("name");
        JSONObject topicScore = params.getJSONObject("topicScore");
        TempResult tempResult = topicService.topicToPapers(uId, tIds, name, topicScore);
        return new ApiResult<>(tempResult.isFlag() ? Code.SAVA_OK : Code.SAVA_ERR,
                null, tempResult.getMsg());
    }

    @PostMapping("/topicRandomToPapers")
    public ApiResult<Object> topicRandomToPapers(@RequestBody JSONObject params) {
        TempResult tempResult = topicService.topicToPapers(params);
        return new ApiResult<>(tempResult.isFlag() ? Code.SAVA_OK : Code.SAVA_ERR,
                null, tempResult.getMsg());
    }
}
