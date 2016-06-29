package pl.mobilnebajery.vindecoder;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

public interface IVinInfoView {
	void dataReceived(ArrayList<BasicNameValuePair> data);
}
