package com.zz.controller;

import com.zz.Service.TopicService;
import com.zz.bean.Topic;
import com.zz.utils.Code;
import com.zz.utils.result.ApiResult;
import com.zz.utils.result.TempResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping
    public ApiResult addTopic(@RequestBody Topic topic) {
        TempResult tempResult = topicService.addTopic(topic);
        return new ApiResult(tempResult.isFlag() ? Code.SAVA_OK : Code.SAVA_ERR,
                null, tempResult.getMsg());
    }

    @GetMapping("/{uId}")
    public ApiResult selectByUId(@PathVariable Integer uId){
        return new ApiResult(Code.GET_OK, topicService.selectByUId(uId), null);
    }
}
