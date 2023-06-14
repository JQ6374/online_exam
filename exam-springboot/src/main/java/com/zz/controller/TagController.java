package com.zz.controller;

import com.alibaba.fastjson.JSONObject;
import com.zz.Service.TagService;
import com.zz.bean.Tag;
import com.zz.utils.result.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 根据教师uid查询其创建的所有tag
     *
     * @param uId 教师uid
     * @return 返回apiresult
     */
    @GetMapping("/getTags/{uId}")
    public ApiResult<List<Tag>> getAllTag(@PathVariable("uId") Integer uId) {
        return tagService.selectAll(uId);
    }

    @PostMapping("/addTag")
    public ApiResult<Integer> addTag(@RequestBody JSONObject jsonObject) {
        return tagService.addTag(jsonObject);
    }


    @PostMapping("/updateTag")
    public ApiResult<Integer> updateTag(@RequestBody JSONObject jsonObject) {
        return tagService.updateTag(jsonObject);
    }

    @DeleteMapping("/{tagId}")
    public ApiResult<Integer> deleteTag(@PathVariable("tagId") Integer tagId) {
        return tagService.deleteTag(tagId);
    }
}
