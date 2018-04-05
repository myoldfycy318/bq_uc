package com.qbao.store.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * html 工具类
 * @author yewenhai
 *
 */
public final class HtmlUtil {

    public static String delHTMLTag(String htmlStr) {
        if (StringUtils.isEmpty(htmlStr)) {
            return "";
        }
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式 

        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); //过滤script标签 

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); //过滤style标签 

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); //过滤html标签 

        return htmlStr.trim(); //返回文本字符串 
    }
    
    /**
     * 去掉转义后的特殊字符
     * @param str
     * @return
     */
	public static String delHtmlOther(String str) {
		String otherTags = "\\&[a-z]*;"; // 定义 &*; 的正则表达式
		Pattern pattern = Pattern.compile(otherTags, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		str = matcher.replaceAll(""); 
		return str;
	}

	public static void main(String[] args) {
		String str = "&nbsp; &nbsp; &nbsp; &nbsp;教师节就要到了，这并不仅仅是教师们的节日，在生活里，人人都可以是其他人的老师，人人都可以过教师节！为庆贺教师节的来临，9月9日-9月11日23:59:59期间内，关注钱宝应用市场公众号的宝粉就有机会获得400宝券奖励哦！！&nbsp; &nbsp; &nbsp; 庆贺教师节，我们将从中抽取600名幸运宝粉，每人赠送400宝券奖励！！！获奖名额空前多有木有！庆贺的方式有很多种，唯有真枪实弹的宝券最能表达我们的爱~~不多说，快来关注吧！关注方式： &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 长按图片，可识别二维码轻松关注&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; （请将钱宝客户端升级至最新版本）&nbsp; &nbsp; &nbsp; &nbsp;本次关注应用市场抽奖活动中奖名单将于9月14日在应用市场公众号公布，宝券奖励将于活动结束后3-5个工作日内发放至您的钱宝账号，或请多关注应用市场公众号随时查看宝券发放动态。（如遇节假日则顺延，敬请谅解）";
		System.out.println(delHtmlOther(str));
	}
	
}
