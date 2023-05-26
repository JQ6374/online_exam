package com.zz.controller;


import com.zz.Service.GeneralService;
import com.zz.utils.Code;
import com.zz.utils.result.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GeneralController {

    @Autowired
    private GeneralService generalService;

    @GetMapping("/tag/{uId}")
    public ApiResult getTag(@PathVariable Integer uId) {
        return new ApiResult(Code.GET_OK, generalService.getTagList(uId), null);
    }

    @GetMapping("/topicType")
    public ApiResult getTypeList() {
        return new ApiResult(Code.GET_OK, generalService.getTypeList(), null);
    }

    @GetMapping("/difficulty")
    public ApiResult getDifficultyList() {
        return new ApiResult(Code.GET_OK, generalService.getDifficultyList(), null);
    }

    @GetMapping("/role")
    public ApiResult getRoleList() {
        return new ApiResult(Code.GET_OK, generalService.getRoleList(), null);
    }
}
