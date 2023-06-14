package com.zz.Service;

import com.zz.bean.Papers;
import com.zz.utils.result.ApiResult;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

public interface PapersService {

    ApiResult<List<Papers>> selectAllByUId(Integer uId);

    ApiResult<Object> deleteByPId(Integer pId);

    ApiResult<Object> updateName(Integer pId, String name);

    ApiResult<List<Papers>> searchByName(Integer uId, String name);
}
