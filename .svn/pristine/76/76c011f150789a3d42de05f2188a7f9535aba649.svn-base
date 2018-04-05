package com.qbao.store.job;

import com.bqiong.usercenter.constants.RedisContant;
import com.bqiong.usercenter.constants.UserCenterConstants;
import com.bqiong.usercenter.util.MailUtil;
import com.bqiong.usercenter.util.PropertiesUtil;
import com.bqiong.usercenter.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.*;
import java.util.Enumeration;

/**
 * Created by admin on 2017/8/4.
 */
public class EmailByUserIdTask {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PropertiesUtil propertiesUtil;

    public void execute() {
        Integer length = RedisUtil.llen(RedisContant.DB_TBL_ID_IDX).intValue();
        String ip = getLinuxLocalIp();
        log.info("UC服务器IP为:{},库表剩余数量:{},不足值:{}", ip, length, propertiesUtil.getString("mail.uc.idx.length"));
        if (length != null
                && length <= Integer.parseInt(propertiesUtil.getString("mail.uc.idx.length"))
                && StringUtils.equals(ip, propertiesUtil.getString("mail.uc.server.ip"))) {
            log.info("邮件预警email发送开始...");
            MailUtil.sendMailByTask(propertiesUtil.getString(UserCenterConstants.EMAIL_RECIPIENT), length.toString(), propertiesUtil.getString("mail.uc.idx.length"));
            log.info("邮件预警email发送完成...");
        }
    }


    public static String getLinuxLocalIp() {
        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        String netip = null;// 外网IP
        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            boolean finded = false;// 是否找到外网IP
            while (netInterfaces.hasMoreElements() && !finded) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();
                while (address.hasMoreElements()) {
                    ip = address.nextElement();
//                    if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
//                            && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
//                        netip = ip.getHostAddress();
//                        finded = true;
//                        break;
//                    }
                    if (ip.isSiteLocalAddress()
                            && !ip.isLoopbackAddress()
                            && ip.getHostAddress().indexOf(":") == -1) {// 内网IP
                        localip = ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
//        if (netip != null && !"".equals(netip)) {
//            return netip;
//        } else {
        return localip;
//        }
    }

    public static void main(String[] args) throws SocketException {
        System.out.println(getLinuxLocalIp());
    }
}
