package com.zz.controller;

import com.alibaba.fastjson.JSONObject;
import com.zz.Service.TagService;
import com.zz.utils.result.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 根据教师uid查询其创建的所有tag
     * @param uId 教师uid
     * @return 返回apiresult
     */
    @GetMapping("/gettags/{uId}")
    public ApiResult getAllTag(@PathVariable("uId") Integer uId){
    return  tagService.selectAll(uId);
}

    /**
     * 根据tagId获取该标签
     * @param tagId
     * @return
     */
    @GetMapping("/gettag/{tagId}")
    public ApiResult getOneById(@PathVariable("tagId") Integer tagId){
        return tagService.selectById(tagId);
    }

    @PostMapping("/addtag")
    public ApiResult addTag(@RequestBody JSONObject jsonObject){
        return tagService.addTag(jsonObject);
    }


    @PostMapping("/updateTag")
    public ApiResult updateTag(@RequestBody JSONObject jsonObject){
    return tagService.updateTag(jsonObject);
    }
@GetMapping("/deleteTag/{tagId}")
    public ApiResult deleteTag(@PathVariable("tagId") Integer tagId){
        return tagService.deleteTag(tagId);
    }
}
