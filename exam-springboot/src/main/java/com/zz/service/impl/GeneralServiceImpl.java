package com.zz.service.impl;

import com.zz.service.GeneralService;
import com.zz.bean.Difficulty;
import com.zz.bean.Role;
import com.zz.bean.Tag;
import com.zz.bean.TopicType;
import com.zz.dao.DifficultyDao;
import com.zz.dao.RoleDao;
import com.zz.dao.TagDao;
import com.zz.dao.TopicTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GeneralServiceImpl implements GeneralService {

    @Autowired
    private TagDao tagDao;
    @Autowired
    private TopicTypeDao topicTypeDao;
    @Autowired
    private DifficultyDao difficultyDao;
    @Autowired
    private RoleDao roleDao;

    @Override
    public ArrayList<Tag> getTagList(Integer uId) {
        return tagDao.selectAll(uId);
    }

    @Override
    public ArrayList<TopicType> getTypeList() {
        return topicTypeDao.selectAll();
    }

    @Override
    public ArrayList<Difficulty> getDifficultyList() {
        return difficultyDao.selectAll();
    }

    @Override
    public ArrayList<Role> getRoleList() {
        return roleDao.selectAll();
    }
}
