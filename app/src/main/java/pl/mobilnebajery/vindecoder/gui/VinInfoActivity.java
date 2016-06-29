package pl.mobilnebajery.vindecoder.gui;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import com.google.inject.Inject;

import pl.mobilnebajery.vindecoder.IVinInfoPresenter;
import pl.mobilnebajery.vindecoder.IVinInfoView;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import pl.mobilnebajery.vindecoder.R;

@ContentView(R.layout.vin_info)
public class VinInfoActivity extends RoboActivity implements IVinInfoView {
	
    @Inject
	private IVinInfoPresenter presenter;
    
    @InjectView(R.id.tableVinDecodedInfo)
    private TableLayout tableVinDecodedInfo;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        presenter.registerView(this);
       
        tableVinDecodedInfo.setVisibility(View.INVISIBLE);
        
        presenter.getVinInfoAsync();
    }
    
    public void dataReceived(final ArrayList<BasicNameValuePair> data) {

		VinInfoActivity.this.runOnUiThread( new Runnable() {
			
			 public void run() {
				 
				 if(data != null && data.size() > 0) {
				        for(BasicNameValuePair pair:data) {
				        	
					        TableRow lytContainer = (TableRow) View.inflate(VinInfoActivity.this, R.layout.tablerow_layout, null);
							
							TextView tvProperty = (TextView) lytContainer.findViewById(R.id.textViewTableProperty);
							TextView tvValue = (TextView) lytContainer.findViewById(R.id.textViewTableValue);
							
							tvProperty.setText(pair.getName());
							tvValue.setText(pair.getValue());
							
							tableVinDecodedInfo.addView(lytContainer);
				        } 
				        
				        tableVinDecodedInfo.setVisibility(View.VISIBLE);
			        }
					 else {
						 (Toast.makeText(VinInfoActivity.this, "NO DATA!", Toast.LENGTH_LONG)).show();
					 }
				 }
			 }
		 );				
	}
    
    public void onCloseInfoClick(View view) {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        
        this.finish();
    }
}
