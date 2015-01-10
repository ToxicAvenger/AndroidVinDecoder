package pl.mobilnebajery.vindecoder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.message.BasicNameValuePair;

import pl.mobilnebajery.captchahacker.Captcha;
import pl.mobilnebajery.captchahacker.ICaptchaHacker;

import android.annotation.SuppressLint;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class VinDataDownloader implements IVinDataDownloader {

	private static String VINDECODERURL = "http://vin-decoder.com/";
	
	private String vinKey;
	private String captchaCode;
	private Captcha captcha;
	private ICaptchaHacker hacker;
	
	@Inject
	public VinDataDownloader(ICaptchaHacker hacker) {
		this.hacker = hacker;
	}
	
	public void setVinData(String vinKey, String code) {
		
		this.vinKey = vinKey.toUpperCase();
		this.captchaCode = code;
	}
	
	public void getVinInfoAsync(final IVinDataReceiver downloader)
	{
		  new Thread(new Runnable() {
			    public void run() {
			    	ArrayList<BasicNameValuePair> tmp = null;
			    	
					try {						
						tmp = getVinInfo(captcha, captchaCode);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					downloader.dataReceived(tmp);
			    }
		  }).start();
	}
	
	public void getCaptchaAsync(final IVinDataReceiver downloader) {
		
		new Thread(new Runnable() {
		    public void run() {
		    	
		    	byte[] data = null;
		    	
		    	try {
					captcha = hacker.getCaptcha(VINDECODERURL);
					
					if(captcha != null) {
						
						data = captcha.getImageData();
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    	downloader.imageReceived(data);
		    }
		}).start();
	}
	
	private ArrayList<BasicNameValuePair> getVinInfo(Captcha captcha, String code) throws Exception {
		
		Map<String, String> params = new LinkedHashMap<String, String>(); 
		params.put("search", this.vinKey);
		params.put("searchbtn", "Check");
		
		captcha.setResponseUrl(VINDECODERURL);
		
		String data = hacker.confirmCaptcha(captcha, code, VINDECODERURL, params);
				
		return parseVinInfo(data);
	}
	
	private ArrayList<BasicNameValuePair> parseVinInfo(String output) {	
				
		ArrayList<BasicNameValuePair> list = new ArrayList<BasicNameValuePair> ();
			
			int index = output.lastIndexOf("<table ");
			output = output.substring(index);
			index = output.indexOf("</table>");
			output = output.substring(0, index);
			
			String[] tab = output.split("<tr>");
			
			for(int i = 0; i< tab.length; i++) {
				
				String tmp = tab[i];
				String key = "";
				String value = "";
				String tag = "<td bgcolor=\"#e8ebed\">";
				
				if(tmp.contains(tag)) {
					
					index = tmp.indexOf(tag) + tag.length();
					tmp = tmp.substring(index);
					index = tmp.indexOf("</td>");
					key = tmp.substring(0, index);
					
					tmp = tab[i];
					
					tag = "<td style=\"border:1px solid #e8ebed; padding-right:20px\">";
					
					if(tmp.contains(tag)) {
						
						index = tmp.indexOf(tag) + tag.length();
						tmp = tmp.substring(index);
						index = tmp.lastIndexOf("</td>");
						value = tmp.substring(0, index);
						
						list.add(new BasicNameValuePair(key, value));
					}
				}
			}
		
		return list; 
	}
}
