package com.zz.controller;

import com.zz.service.impl.PaperServiceImpl;
import com.zz.bean.Papers;
import com.zz.utils.result.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/papers")
public class PapersController {

    @Autowired
    private PaperServiceImpl paperService;

    /**
     * 查询试卷列表
     *
     * @param uId 教师Id
     */
    @GetMapping("/{uId}")
    public ApiResult<List<Papers>> selectAllByUId(@PathVariable Integer uId) {
        return paperService.selectAllByUId(uId);
    }

    @GetMapping("/{uId}/{name}")
    public ApiResult<List<Papers>> searchByName(@PathVariable("uId") Integer uId,
                                  @PathVariable("name") String name) {
        return paperService.searchByName(uId, name);
    }

    /**
     * 删除试卷
     *
     * @param pId 试卷Id
     */
    @DeleteMapping("/{pId}")
    public ApiResult<Object> deleteByPId(@PathVariable Integer pId) {
        return paperService.deleteByPId(pId);
    }

    /**
     * 修改试卷名称
     *
     * @param pId  试卷Id
     * @param name 试卷名
     */
    @PutMapping("/{pId}/{name}")
    public ApiResult<Object> updateName(@PathVariable("pId") Integer pId,
                                @PathVariable("name") String name) {
        return paperService.updateName(pId, name);
    }
}
