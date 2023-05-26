package com.zz.Service;

import com.zz.bean.Difficulty;
import com.zz.bean.Role;
import com.zz.bean.Tag;
import com.zz.bean.TopicType;

import java.util.ArrayList;

public interface GeneralService {

    ArrayList<Tag> getTagList(Integer uId);

    ArrayList<TopicType> getTypeList();

    ArrayList<Difficulty> getDifficultyList();

    ArrayList<Role> getRoleList();
}
