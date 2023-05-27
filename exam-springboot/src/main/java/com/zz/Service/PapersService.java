package com.zz.Service;

import com.zz.bean.Papers;
import com.zz.utils.result.ApiResult;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface PapersService {

    ApiResult selectAllByUId(Integer uId);

    ApiResult deleteByPId(Integer pId);

    ApiResult updateName(Integer pId, String name);

    ApiResult searchByName(Integer uId, String name);
}
