package com.qbao.store.init;

import com.bqiong.usercenter.constants.RequestDataHelper;
import com.bqiong.usercenter.constants.UserCenterConstants;
import com.bqiong.usercenter.util.RedisUtil;
import com.bqiong.usercenter.util.ThreadPoolUtil;
import com.qbao.store.entity.BUEntity;
import com.qbao.store.service.ClientService;
import com.qbao.store.util.BUParser;
import com.qbao.store.util.InitConstants;
import com.qbao.store.util.NeedBuildErrorDataList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.InputSource;

import java.io.InputStreamReader;
import java.util.List;

public class InitBean {
    protected Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ClientService clientService;

    @Autowired
    InitConstants initConstants;

    @Autowired
    RedisUtil redisUtil;

    /**
     * 函数名称 : init
     * 功能描述 :
     * 参数及返回值说明：
     * <p/>
     * 修改记录：
     * 日期 ：2016年4月28日 下午6:07:23	修改人：  nz
     * 描述	：
     */
    public void init() {
        log.info("初始化开始");
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(BUParser.class.getResourceAsStream("/config/buId.xml"), "UTF-8");
            InputSource is = new InputSource(inputStreamReader);
            List<BUEntity> buInfos = BUParser.getNodes(is);
            for (int i = 0; i < buInfos.size(); i++) {
                RedisUtil.set(UserCenterConstants.REDIS_BUINFO_KEY_PREFIX + buInfos.get(i).getBuId(),
                        buInfos.get(i).getBuName());
            }
            RequestDataHelper.init();
            NeedBuildErrorDataList.init();
            ThreadPoolUtil.load();
            initConstants.load();
        } catch (Exception e) {
            log.error("初始化出错！", e);
        }
        log.info("初始化完成");
    }
}
