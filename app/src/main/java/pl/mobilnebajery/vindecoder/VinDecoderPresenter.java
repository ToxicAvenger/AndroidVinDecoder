package pl.mobilnebajery.vindecoder;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.inject.Inject;

public class VinDecoderPresenter implements IVinDecoderPresenter, IVinDataReceiver {

	@Inject
	private IVinDataDownloader model;
	
	private IVinDecoderView view;	
	
	public void registerView(IVinDecoderView view) {
		this.view = view;
	}

	public void decodeVinInfo(String vinKey, String captcha) {
		model.setVinData(vinKey, captcha);
		
		view.showVinInfo();
	}

	public void getImageData() {
		
		model.getCaptchaAsync(this);
	}

	public void dataReceived(ArrayList<BasicNameValuePair> data) {
		// Initially left blank		
	}

	public void imageReceived(byte[] data) {
		if(data != null && data.length > 0) {

			Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
			view.setBitmap(bmp.copy(Bitmap.Config.ARGB_8888, true));
		}
	}

}
