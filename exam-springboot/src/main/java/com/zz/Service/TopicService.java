package com.zz.Service;

import com.alibaba.fastjson.JSONObject;
import com.zz.bean.Topic;
import com.zz.utils.result.ApiResult;
import com.zz.utils.result.TempResult;
import net.sf.jsqlparser.statement.select.Top;

import java.util.ArrayList;
import java.util.List;

public interface TopicService {

    TempResult addTopic(Topic topic);

    ArrayList<Topic> selectByUId(Integer uId);

    TempResult delTopic(Integer tId);

    TempResult updateTopic(Topic topic);

    TempResult topicToPapers(Integer uId, Integer[] tIds, String papersName, JSONObject topicScore);

    TempResult topicToPapers(JSONObject params);

    ApiResult<List<Topic>> searchByInfo(Integer uId, String info);
}
