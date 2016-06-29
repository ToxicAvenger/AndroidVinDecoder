package pl.mobilnebajery.vindecoder.gui;

import com.google.inject.Inject;

import pl.mobilnebajery.vindecoder.IVinDecoderPresenter;
import pl.mobilnebajery.vindecoder.IVinDecoderView;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import pl.mobilnebajery.vindecoder.R;

@ContentView(R.layout.main)
public class VinDecoderActivity extends RoboActivity implements IVinDecoderView {
    
	@Inject
	private IVinDecoderPresenter presenter;
	
	@InjectView(R.id.editTextVinKey)
	private EditText editTextVinKey;
	
	@InjectView(R.id.editTextCaptcha)
	private EditText editTextCaptcha;
	
	@InjectView(R.id.imageViewCaptcha)
	private ImageView imageViewCaptcha;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  
        
        presenter.registerView(this);
        presenter.getImageData();
    }
    
    public void onCalculateMainClick(View view) {
   	 
    	String vinKey = "";
    	String captcha = "";
    	Editable textVin = editTextVinKey.getText();
    	Editable textCaptcha = editTextCaptcha.getText();
    	
    	if(textVin != null){
    		
    		vinKey = textVin.toString();
    	}
    	
    	if(textCaptcha != null){
    		
    		captcha = textCaptcha.toString();
    	}
    	
    	presenter.decodeVinInfo(vinKey, captcha);   	
    }
    
    public void onCloseMainClick(View view) {

    	this.finish();
    }

	@Override
	public void showVinInfo() {
		Intent myIntent = new Intent(this, VinInfoActivity.class);
        startActivityForResult(myIntent, 0);
	}

	@Override
	public void setBitmap(final Bitmap bitmap) {
		this.runOnUiThread(new Runnable() {

			public void run() {
				imageViewCaptcha.setImageBitmap(bitmap);
			}
		});

	}
}