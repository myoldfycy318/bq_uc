package com.qbao.store.huanxin.service.impl;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bqiong.usercenter.util.DateUtil;
import com.qbao.store.huanxin.model.Authentic;
import com.qbao.store.huanxin.model.TalkNode;
import com.qbao.store.huanxin.service.TalkHttpService;
import com.qbao.store.huanxin.tool.JsonTool;
import com.qbao.store.huanxin.tool.Global;
public class TalkHttpServiceImplApache implements TalkHttpService {
	private static final Logger log = LoggerFactory.getLogger(TalkHttpServiceImplApache.class);
	public TalkNode request(String url, int method, Object param, Authentic auth, String[][] field) throws Exception {
		CloseableHttpClient client = this.getClient();
		try{
			HttpResponse response = null;
			log.info("begin to invoke huanxin interface!");
			Date begin = new Date();
			switch(method){
				case Global.HTTP_METHOD_GET:
					HttpGet get = new HttpGet(url);
					if(auth!=null){
						auth.applyAuthentication(get);
					}
					get.addHeader("Content-Type","application/json");
					response = client.execute(get);
					break;
				case Global.HTTP_METHOD_POST:
					HttpPost post = new HttpPost(url);
					if(auth!=null){
						auth.applyAuthentication(post);
					}
					if(param!=null){
						post.setEntity(new StringEntity(JsonTool.write(param),"UTF-8"));
					}
					post.addHeader("Content-Type","application/json");
					response = client.execute(post);
					break;
				case Global.HTTP_METHOD_PUT:
					HttpPut put = new HttpPut(url);
					if(put!=null){
						auth.applyAuthentication(put);
					}
					if(param!=null){
						put.setEntity(new StringEntity(JsonTool.write(param),"UTF-8"));
					}
					put.addHeader("Content-Type","application/json");
					response = client.execute(put);
					break;
				case Global.HTTP_METHOD_DELETE:
					HttpDelete delete = new HttpDelete(url);
					if(auth!=null){
						auth.applyAuthentication(delete);
					}
					delete.addHeader("Content-Type","application/json");
					response = client.execute(delete);
					break;
				default:throw new Exception("非法请求方式");
			}
			int code = response.getStatusLine().getStatusCode();
			int timeTaken = DateUtil.getSencondsBetweenMillis(new Date().getTime(), begin.getTime());
			if(code==HttpStatus.SC_OK){
				HttpEntity entity = response.getEntity();
				if(entity!=null){
					String json = EntityUtils.toString(entity,"UTF-8");
					if(field!=null&&field.length>0){
						for(String[]temp:field){
							json = json.replace(temp[0],temp[1]);
						}
					}
					log.info("end of invoke huanxin interface!httpStatus=" + code + ",result=" + json + ",timeTaken=" + timeTaken);
					return (TalkNode) JsonTool.read(json,TalkNode.class);
				}
			}else{
				log.error("end of invoke huanxin interface!httpStatus=" + code + ",timeTaken=" + timeTaken);
				return new TalkNode(code);
			}
		}catch(Exception e){
			log.error("error occured when invoke huanxin interface!", e);
			throw e;
		}finally{
			client.close();
		}
		return null;
	}
	public TalkNode upload(String url, File file, Authentic auth, String[][] equal) throws Exception {
		CloseableHttpClient client = this.getClient();
		try{
			HttpPost post = new HttpPost();
			post.setURI(new URI(url));
			if(auth!=null){
				auth.applyAuthentication(post);
			}
			post.addHeader("restrict-access","true");
			ContentBody body = new FileBody(file);
			MultipartEntity part = new MultipartEntity();
			part.addPart("file",body);
			post.setEntity(part);
			HttpResponse response = client.execute(post);
			int code = response.getStatusLine().getStatusCode();
			//System.out.println("request url = "+url+", statusCode = "+code+".");//TODO 此行可以删除
			if(code==HttpStatus.SC_OK){
				HttpEntity entity = response.getEntity();
				if(entity!=null){
					String json = EntityUtils.toString(entity,"UTF-8");
					if(equal!=null&&equal.length>0){
						for(String[]temp:equal){
							json = json.replace(temp[0],temp[1]);
						}
					}
					//System.out.println(json);//TODO 此行可以删除
					return (TalkNode) JsonTool.read(json,TalkNode.class);
				}
			}
		}catch(Exception e){
			throw e;
		}finally{
			client.close();
		}
		return null;
	}
	public void downLoad(String url, File file, Authentic auth, Map<String,String>header) throws Exception {
		CloseableHttpClient client = this.getClient();
		try{
			HttpGet get = new HttpGet();
			get.setURI(new URI(url));
			if(auth!=null){
				auth.applyAuthentication(get);
			}
			for(Entry<String,String>en:header.entrySet()){
				get.addHeader(en.getKey(),en.getValue());
			}
			HttpResponse response = client.execute(get);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				HttpEntity entity = response.getEntity();
				if(entity!=null){
					InputStream in = entity.getContent();
					FileOutputStream fos = new FileOutputStream(file);
					byte[]data = new byte[10*1024];
					int len = 0;
					while((len=in.read(data))!=-1){
						fos.write(data,0,len);
					}
					fos.flush();
					fos.close();
					in.close();
				}
			}
		}catch(Exception e){
			throw e;
		}finally{
			client.close();
		}
	}
	private CloseableHttpClient getClient() {
		return HttpClients.createDefault();
	}
}