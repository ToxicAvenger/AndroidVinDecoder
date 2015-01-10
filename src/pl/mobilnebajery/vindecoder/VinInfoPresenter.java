package pl.mobilnebajery.vindecoder;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import com.google.inject.Inject;

public class VinInfoPresenter implements IVinInfoPresenter, IVinDataReceiver {

	@Inject
	private IVinDataDownloader model;
	
	private IVinInfoView view;

	public void registerView(IVinInfoView view) {
		this.view = view;
	}

	public void getVinInfoAsync() {
		model.getVinInfoAsync(this);
	}

	public void dataReceived(ArrayList<BasicNameValuePair> data) {
		view.dataReceived(data);
	}

	@Override
	public void imageReceived(byte[] data) {
		// Initially left blank
	}

}
