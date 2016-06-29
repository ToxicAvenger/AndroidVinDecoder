package pl.mobilnebajery.vindecoder;

import java.util.ArrayList;
import org.apache.http.message.BasicNameValuePair;


public interface IVinDataReceiver {
	
	void dataReceived(ArrayList<BasicNameValuePair> data);
	void imageReceived(byte[] data);
}