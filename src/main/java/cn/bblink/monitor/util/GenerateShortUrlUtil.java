package cn.bblink.monitor.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 生成短网址并返回
 * @author: Jerri 
 * @date: 2014年3月22日下午9:58:54
 */
public class GenerateShortUrlUtil {
	public static final String DAC_URL = "http://dac.bblink.cn/goto?_key={_key}&_version={_version}&uid={_uid}&adid=564c17d60cf2bf4560331534&_gotourl=";
	public static final String PREFIX_URL = "http://192.168.0.145:8081/bblink_monitor/monitor/";
	public static DefaultHttpClient httpclient;
	static {
		httpclient = new DefaultHttpClient();
//		httpclient = (DefaultHttpClient) HttpClientConnectionManager
//				.getSSLInstance(httpclient); // 接受任何证书的浏览器客户端
	}
	
	/**
	 * 生成端连接信息
	 * 
	 * @author: Jerri 
	 * @date: 2014年3月22日下午5:31:15
	 */
	public static String  generateShortUrl(String url) {
		url = DAC_URL+url;
		try {
			HttpPost httpost = new HttpPost("http://dwz.cn/create.php");
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("url", url)); // 用户名称
			httpost.setEntity(new UrlEncodedFormEntity(params,  "utf-8"));
			HttpResponse response = httpclient.execute(httpost);
			String jsonStr = EntityUtils
					.toString(response.getEntity(), "utf-8");
			//System.out.println(jsonStr);
			JSONObject object = JSON.parseObject(jsonStr);
			//System.out.println(object.getString("tinyurl"));
			return object.getString("tinyurl");
		} catch (Exception e) {
			//e.printStackTrace();
			return "Error";
		}
		
	}
	
	/**
	 * 测试生成端连接
	 * @param args
	 * @author: Jerri 
	 * @date: 2014年3月22日下午5:34:05
	 */
	public static void main(String []args){
		String str = "http://dac.bblink.cn/goto?_key={_key}&_version={_version}&uid={_uid}&adid=564c17d60cf2bf4560331534&_gotourl=http://192.168.0.145:8081/bblink_monitor/monitor/1234567?tag=monitor";
		System.out.println(generateShortUrl(str));
	}
}