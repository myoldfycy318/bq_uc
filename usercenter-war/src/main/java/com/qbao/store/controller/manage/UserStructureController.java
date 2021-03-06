package com.qbao.store.controller.manage;

import com.bqiong.usercenter.constants.RedisContant;
import com.bqiong.usercenter.util.RedisUtil;
import com.qbao.store.entity.user.UserInfo;
import com.qbao.store.service.structure.UserStructureService;
import com.qbao.store.util.DynamicDataSourceSelector;
import com.qbao.store.util.UserCenterContext;
import com.qbao.store.util.UserIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * UserStructureController
 *
 * @author Zhang ShanMin
 * @date 2017/7/10
 * @time 13:43
 */
@Controller
@RequestMapping("/structure")
public class UserStructureController {

    @Resource(name = "userStructureService")
    private UserStructureService userStructureService;
    @Autowired
    private UserIdGenerator userIdGenerator;

    @Resource
    private DynamicDataSourceSelector dynamicDataSourceSelector;

    //建立表结构
//    @RequestMapping("/user")
//    @ResponseBody
    public String createUserStructure(HttpServletRequest request) {
        //确定数据库是否存在
        //创建表10张，每张80万数据
        //库+表+id放入redis List
        String datasource = request.getParameter("datasource");
        System.out.println(datasource);
        UserCenterContext.setDataSource(datasource);
        UserInfo userInfo = null;
        AtomicLong atomicLong = null;
        String suffix;
        List<UserInfo> list = new ArrayList<UserInfo>(10000);
        List<String> userTableIdxs = new ArrayList<String>(10000);
        long seqId = 0;
        for (int i = 0; i <= 0; i++) {
            suffix = "0" + i;
            String tableName = "user_info_" + suffix;
            userStructureService.createUserTable(tableName);
            if ("dataSource01".equals(datasource) && "user_info_00".equals(tableName)) {
                atomicLong = new AtomicLong(230000);
            } else {
                atomicLong = new AtomicLong(0);
            }
            while (atomicLong.get() < 800000) {
                userInfo = new UserInfo();
                seqId = atomicLong.addAndGet(1);
                userInfo.setId(seqId);
                userTableIdxs.add(datasource + "-" + tableName + "-" + seqId);
                list.add(userInfo);
                if (userTableIdxs.size() % 10000 == 0) {
                    RedisUtil.rpush(RedisContant.DB_TBL_ID_IDX, userTableIdxs.toArray(new String[1]));
                    userTableIdxs.clear();
                }
                if (atomicLong.get() % 10000 == 0) {
                    userStructureService.insertUsers(tableName, list);
                    list.clear();
                }
            }
        }
        return "ok";
    }

//    @RequestMapping("/initUidSeq")
//    @ResponseBody
    public String initDomeUidStarSeq() {
        String redisKey = "uc:userid";
        RedisUtil.set(redisKey, "230000");
        return "ok";
    }

    //现有数据导入
    //1.钱宝用户 ，2.冰穹用户 3.游客登录
//    @ResponseBody
//    @RequestMapping("/import")
    public String importOldUserData(HttpServletRequest request) {
        String datasource = request.getParameter("datasource");
        System.out.println(datasource);
        UserCenterContext.setDataSource(datasource);
        userStructureService.importOldUserInfo();
        userStructureService.loadTourist2cache();
        return "ok";
    }


    /**
     * 在redis挂掉的情况下加载剩余库表索引到缓存
     *
     * @return
     */
//    @ResponseBody
//    @RequestMapping("/loadLeaveOverSeq2Cache")
    public String loadLeaveOverSeq2Cache(HttpServletRequest request) {

        String datasource = request.getParameter("datasource");
        UserCenterContext.setDataSource(datasource);
        String table = request.getParameter("table");
        Integer seqStart = Integer.valueOf(request.getParameter("seqStart"));
        Integer seqEnd = Integer.valueOf(request.getParameter("seqEnd"));
        String dbTblIdx = datasource + "-" + table + "-";
        List<String> userTableIdxs = new ArrayList<String>(1000);

        int pagesize = 2;
        Integer total = seqEnd - seqStart + 1;
        int totalCount = (total % pagesize == 0) ? (total / pagesize) : (total / pagesize + 1);
        int count = 0;
        for (Integer i = seqStart; i <= seqEnd; i++) {
            userTableIdxs.add(dbTblIdx + i);
            if (userTableIdxs.size() % pagesize == 0) {
                count++;
            }
            if (count <= totalCount) {
                RedisUtil.rpush(RedisContant.DB_TBL_ID_IDX, userTableIdxs.toArray(new String[1]));
                userTableIdxs.clear();
            }
        }
        return "ok";
    }

}
