package com.zz.controller;

import com.zz.bean.Exam;
import com.zz.utils.result.ApiResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class ControllerTest {

    //    @GetMapping("/{cId}/{name}/{startTime}/{endTime}")
//    public ApiResult test(@PathVariable("cId") Integer cId,
//                          @PathVariable("name") String name,
//                          @PathVariable("startTime") String startTime) {
////        System.out.println(exam);
////        System.out.println(startTime);
//        System.out.println(cId);
//        System.out.println(name);
//        System.out.println(startTime);
//        System.out.println("===========");
//        return new ApiResult();
//    }
    @PostMapping
    public ApiResult test(@RequestBody Map<Object, Object> map) {
        System.out.println(map);
//        System.out.println(exam);
//        System.out.println(startTime);
        DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(LocalDateTime.parse((String) map.get("startTime"), ofPattern));
        System.out.println("===========");
        return new ApiResult();
    }
}
