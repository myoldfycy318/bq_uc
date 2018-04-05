package com.qbao.store.base;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;

/**
 * xml configuration
 * @author yewenhai
 *
 */
@Configurable
@ContextConfiguration(locations = { "classpath:beans-redis.xml", "classpath:beans-job.xml", "classpath:beans-security.xml" })
public class XmlContext {

}
