package com.zz.Service.impl;

import com.zz.Service.TagService;
import com.zz.bean.Tag;
import com.zz.dao.TagDao;
import com.zz.utils.Code;
import com.zz.utils.result.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagDao tagDao;

    public ApiResult updateTag(Tag tag) {
        Integer integer = tagDao.updateTag(tag);
        if (integer == 1) {
            return new ApiResult(Code.UPDATE_OK, null, "修改成功");
        } else
            return new ApiResult(Code.UPDATE_ERR, null, "修改失败");
    }

    @Override
    public ApiResult selectAll(Integer uId) {
        ArrayList<Tag> tags = tagDao.selectAll(uId);
        if (tags.size() > 0) {
            return new ApiResult(Code.GET_OK, tags, "查询成功");
        } else {
            return new ApiResult(Code.GET_ERR, null, "查询失败");
        }
    }

    @Override
    public ApiResult selectById(Integer tagId) {
        String s = tagDao.selectById(tagId);
        if (s.equals("") || s.length() == 0) {
            return new ApiResult(Code.GET_ERR, null, "查询失败");
        } else
            return new ApiResult(Code.GET_OK, s, "查询成功");
    }

    @Override
    public ApiResult addTag(Tag tag) {
        Integer integer = tagDao.addTag(tag);
        if (integer!=0){
            return new ApiResult(Code.UPDATE_OK,integer,"添加成功");
        }else
        return new ApiResult(Code.UPDATE_ERR,integer,"添加失败");
    }

    @Override
    public Integer deleteTag(Integer tagId) {
        return null;
    }
}
