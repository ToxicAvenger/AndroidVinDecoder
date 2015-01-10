package pl.mobilnebajery.vindecoder.ioc;

import pl.mobilnebajery.captchahacker.CaptchaHacker;
import pl.mobilnebajery.captchahacker.ICaptchaHacker;
import pl.mobilnebajery.vindecoder.IVinDataDownloader;
import pl.mobilnebajery.vindecoder.IVinDecoderPresenter;
import pl.mobilnebajery.vindecoder.IVinInfoPresenter;
import pl.mobilnebajery.vindecoder.VinDataDownloader;
import pl.mobilnebajery.vindecoder.VinDecoderPresenter;
import pl.mobilnebajery.vindecoder.VinInfoPresenter;

import com.google.inject.AbstractModule;

public class VinDecoderModule extends AbstractModule {

	@Override
	protected void configure() {
		
		bind(ICaptchaHacker.class).to(CaptchaHacker.class).asEagerSingleton();
		
		bind(IVinDataDownloader.class).to(VinDataDownloader.class).asEagerSingleton();
		
		bind(IVinDecoderPresenter.class).to(VinDecoderPresenter.class).asEagerSingleton();
		bind(IVinInfoPresenter.class).to(VinInfoPresenter.class).asEagerSingleton();
	}

}
