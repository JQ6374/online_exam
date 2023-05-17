package com.zz.Service;

import com.zz.bean.Topic;
import com.zz.utils.result.TempResult;
import net.sf.jsqlparser.statement.select.Top;

import java.util.ArrayList;

public interface TopicService {

    TempResult addTopic(Topic topic);

    ArrayList<Topic> selectByUId(Integer uId);

    TempResult delTopic(Integer tId);

    TempResult updateTopic(Topic topic);

    TempResult topicToPapers(Integer[] tIds);
}
