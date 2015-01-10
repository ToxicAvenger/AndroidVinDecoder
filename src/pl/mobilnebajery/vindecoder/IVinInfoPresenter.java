package pl.mobilnebajery.vindecoder;

public interface IVinInfoPresenter {
	void registerView(IVinInfoView view);
	void getVinInfoAsync();
}
