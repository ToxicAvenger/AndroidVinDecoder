package pl.mobilnebajery.vindecoder;

public interface IVinDataDownloader {

	void setVinData(String vinKey, String code);
	void getVinInfoAsync(IVinDataReceiver downloader);
	void getCaptchaAsync(IVinDataReceiver downloader);
}