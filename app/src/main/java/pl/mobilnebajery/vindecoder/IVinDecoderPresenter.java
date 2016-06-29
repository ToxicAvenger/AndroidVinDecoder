package pl.mobilnebajery.vindecoder;

public interface IVinDecoderPresenter {
	
	void registerView(IVinDecoderView view);
	void decodeVinInfo(String vinKey, String captcha);
	void getImageData();
}
