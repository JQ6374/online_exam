package com.zz.Service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zz.Service.TagService;
import com.zz.bean.Tag;
import com.zz.dao.TagDao;
import com.zz.utils.Code;
import com.zz.utils.result.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagDao tagDao;

    public ApiResult updateTag(JSONObject tag) {
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
            return new ApiResult(Code.GET_ERR, s, "查询失败");
        } else
            return new ApiResult(Code.GET_OK, s, "查询成功");
    }


    @Override
    public ApiResult addTag(JSONObject json) {
//        Tag tag = new Tag();
//        tag.setuId((Integer) json.get("uid"));
//        tag.setName((String) json.get("name"));
        Integer integer = tagDao.addTag(json);
        if (integer!=0){
            return new ApiResult(Code.UPDATE_OK,integer,"添加成功");
        }else
        return new ApiResult(Code.UPDATE_ERR,integer,"添加失败");
    }

    @Override
    public ApiResult deleteTag(Integer tagId) {
        Integer integer = tagDao.deleteTag(tagId);
        if (integer>0){
            return new ApiResult(Code.UPDATE_OK,null,"删除成功");
        }else
        return new ApiResult(Code.UPDATE_ERR,null,"删除失败");
    }
}
