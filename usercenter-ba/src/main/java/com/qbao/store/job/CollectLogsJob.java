package com.qbao.store.job;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qbao.store.util.DateUtil;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;

/**
 * 每天加载有效期内的会员到缓存
 * @ClassName: MemberInfoLoadJob
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhang chen
 * @date 2016年1月27日 上午11:04:15
 *
 */
public class CollectLogsJob {

	private static final Logger LOGGER = LoggerFactory.getLogger(CollectLogsJob.class);

	/** 属性文件 */
	protected static final Properties properties = new Properties();

	/** 加载配置的参数 */
	static {
		InputStream inputStream = null;
		try {
			System.out.println(CollectLogsJob.class.getResource("/").getPath());
			inputStream = CollectLogsJob.class.getResourceAsStream("/collect.properties");
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != inputStream) {
				try {
					inputStream.close();
					inputStream = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// @Scheduled(cron = "0 30 0 * * ?")//每天00点30分 与加载有效期内的会员信息定时任务时间错开
	// @Scheduled(cron = "0 0/5 * * * ?") //每5分钟（测试）
	public void execute() {
		LOGGER.info("收集日志-定时任务开始执行......");
		String localtmpurl = properties.get("collector.tmpurl").toString();
		File file = new File(localtmpurl);
		if (!file.exists()) {
			file.mkdirs();
			LOGGER.info("创建本地临时目录" + localtmpurl);
		}

		String localFlumeUrl = properties.get("collector.url").toString();
		File file2 = new File(localFlumeUrl);
		if (!file2.exists()) {
			file2.mkdirs();
			LOGGER.info("创建本地flume spoolDir目录" + localFlumeUrl);
		}
		
		Connection conn = null;

		String[] agents = properties.get("collector.agents").toString().split("#");
		for (String agent : agents) {
			String agentServer = properties.get(agent + ".server").toString();
			LOGGER.info("正在连接" + agentServer + "...");
			try {
				conn = new Connection(agentServer);
				conn.connect();
				LOGGER.info("连接" + agentServer + "成功!");
				String user = properties.get(agent + ".user").toString();
				String password = properties.get(agent + ".password").toString();
				boolean isAuthenticated = conn.authenticateWithPassword(user, password);
				if (isAuthenticated == true) {
					SCPClient client = new SCPClient(conn);
					String[] targets = properties.get(agent + ".target").toString().split("#");
					for (String target : targets) {
						try {
							String src = properties.get(target + ".src").toString();
							String filename = properties.get(target + ".filename").toString();
							String suffix = properties.get(target + ".suffix").toString();
							String today = DateUtil.getToday_yyyy_MM_dd();// yyyy-MM-dd
							String fileAllName=filename + "_" + today + suffix;
							String targetfile = src +fileAllName;
							LOGGER.info("下载中:正在从" + agentServer + "下载文件:" + targetfile);
							client.get(targetfile, localtmpurl);
							LOGGER.info("从" + agentServer + "下载成功,文件储存在:" + localtmpurl);
							String localFile=localtmpurl+fileAllName;
							File logFlie=new File(localFile);
							if(logFlie.exists()){
								logFlie.renameTo(new File(localFlumeUrl+fileAllName));
								LOGGER.info("移动成功:"+targetfile+"已移动到" + localFlumeUrl);
							}else{
								LOGGER.info("移动失败:"+logFlie+"不存在!");
							}
							
						} catch (Exception e) {
							LOGGER.info("从" + agentServer + "下载失败!");
							e.printStackTrace();
						}
					}
					conn.close();
					LOGGER.info("已关闭" + agentServer + "的连接...");
				}
			} catch (Exception e) {
				LOGGER.info("连接" + agentServer + "失败!");
				e.printStackTrace();
			} finally {
				conn.close();
			}
		}

		LOGGER.info("收集日志-定时任务开始执行完成");
	}

	public static void main(String[] args) {

	}
}
