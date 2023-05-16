package com.zz.Service;

import com.zz.bean.Topic;
import com.zz.utils.result.TempResult;

import java.util.ArrayList;

public interface TopicService {

    TempResult addTopic(Topic topic);

    ArrayList<Topic> selectByUId(Integer uId);
}
