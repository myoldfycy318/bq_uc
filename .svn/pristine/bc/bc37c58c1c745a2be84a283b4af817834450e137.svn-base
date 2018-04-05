package com.qbao.store.huanxin.tool;

public class Global {
	
	public static int APP_PAGE_SIZE = 5;
	
	private static String appKey;
	private static String appClientId;
	private static String appClientSecret;
	
	public static String APP_KEY = "appKey";
	public static String APP_CLIENT_ID = "appClientId";
	public static String APP_CLIENT_SECRET = "appClientSecret";
	//正式环境
	/*public static String APP_KEY = "domestore#domestoreofficial";
	public static String APP_CLIENT_ID = "YXA6bztyQGtjEea2hbnas7hMBQ";
	public static String APP_CLIENT_SECRET = "YXA6Hc6yhqJRz_X-_5npioZpxW24cIc";*/
	
	//TODO 以上参数需相应修改
	public static final int HTTP_METHOD_GET = 1;
	public static final int HTTP_METHOD_POST = 2;
	public static final int HTTP_METHOD_PUT = 3;
	public static final int HTTP_METHOD_DELETE = 4;
	//public static final String URL_HOST = "http://a1.easemob.com/"+getAppKey().replace("#","/")+"/";
	//public static final String URL_TOKEN = getUrlHost()+"token";
	//public static final String URL_CHAT = getUrlHost()+"chatmessages";
	/*public static final String URL_GROUP = getUrlHost()+"chatgroups";
	public static final String URL_FILE = getUrlHost()+"chatfiles";
	public static final String URL_ROOM = getUrlHost()+"chatrooms";
	public static final String URL_MESSAGES = getUrlHost()+"messages";
	public static final String URL_USER = getUrlHost()+"users";*/
	
	public static String getUrlHost()
	{
		return "http://a1.easemob.com/"+getAppKey().replace("#","/")+"/";
	}
	
	public static String getUrlToken()
	{
		return getUrlHost()+"token";
	}
	
	public static String getUrlChat()
	{
		return getUrlHost()+"chatmessages";
	}
	
	public static String getUrlGroup()
	{
		return getUrlHost()+"chatgroups";
	}
	
	public static String getUrlFile()
	{
		return getUrlHost()+"chatfiles";
	}
	
	public static String getUrlRoom()
	{
		return getUrlHost()+"chatrooms";
	}
	
	public static String getUrlMessage()
	{
		return getUrlHost()+"messages";
	}
	
	public static String getUrlUser()
	{
		return getUrlHost()+"users";
	}
	
	public static String getAppKey() {
		return appKey;
	}
	public static void setAppKey(String appKey) {
		Global.appKey = appKey;
	}
	public static String getAppClientId() {
		return appClientId;
	}
	public static void setAppClientId(String appClientId) {
		Global.appClientId = appClientId;
	}
	public static String getAppClientSecret() {
		return appClientSecret;
	}
	public static void setAppClientSecret(String appClientSecret) {
		Global.appClientSecret = appClientSecret;
	}
	/*public static String getUrlHost() {
		return urlHost;
	}
	public static void setUrlHost(String urlHost) {
		Global.urlHost = urlHost;
	}
	public static String getUrlToken() {
		return urlToken;
	}
	public static void setUrlToken(String urlToken) {
		Global.urlToken = urlToken;
	}
	public static String getUrlChat() {
		return urlChat;
	}
	public static void setUrlChat(String urlChat) {
		Global.urlChat = urlChat;
	}
	public static String getUrlGroup() {
		return urlGroup;
	}
	public static void setUrlGroup(String urlGroup) {
		Global.urlGroup = urlGroup;
	}
	public static String getUrlFile() {
		return urlFile;
	}
	public static void setUrlFile(String urlFile) {
		Global.urlFile = urlFile;
	}
	public static String getUrlRoom() {
		return urlRoom;
	}
	public static void setUrlRoom(String urlRoom) {
		Global.urlRoom = urlRoom;
	}
	public static String getUrlMessage() {
		return urlMessage;
	}
	public static void setUrlMessage(String urlMessage) {
		Global.urlMessage = urlMessage;
	}
	public static String getUrlUser() {
		return urlUser;
	}
	public static void setUrlUser(String urlUser) {
		Global.urlUser = urlUser;
	}*/
	
	/*public static void load()
	{
		Properties prop = new Properties();
		InputStream in = Global.class.getResourceAsStream("/param.properties");
		try 
		{
			prop.load(in);
		} catch (IOException e) {
			log.error("error occured when loading huanxin configuration");
		}
		Set<Object> keyValue = prop.keySet();
		for (Iterator it = keyValue.iterator(); it.hasNext();)
		{
			String key = (String) it.next();
			if(key.equals("huanxin.appKey"))
			{
				setAppKey(prop.getProperty(key));
			}
			if(key.equals("huanxin.appClientId"))
			{
				setAppClientId(prop.getProperty(key));
			}
			if(key.equals("huanxin.appClientSecret"))
			{
				setAppClientSecret(prop.getProperty(key));
			}
		}
		
	}*/
	
	
}