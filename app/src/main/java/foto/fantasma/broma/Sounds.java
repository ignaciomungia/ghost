package foto.fantasma.broma;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.InputStream;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import android.graphics.BitmapFactory;

import android.media.ExifInterface;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.net.Uri;

import android.os.Bundle;
import android.provider.MediaStore;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ScaleGestureDetector;

import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;

import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import android.widget.Toast; 


//cambiatu
public class Sounds extends Activity {

	private int sonido = 0;
	
	private ImageButton botonRisas1;
	private ImageButton botonRisas2;
	//private ImageButton botonRisas3;
	private ImageButton botonRisas4;
	//private ImageButton botonRisas5;
	private ImageButton botonRisas6;
	private ImageButton botonRisas7;
	private ImageButton botonRisas8;
	//private ImageButton botonRisas9;
	private ImageButton botonRisas10;
	private ImageButton botonRisas11;
	private ImageButton botonRisas12;
	private ImageButton botonRisas13;
	private ImageButton botonRisas14;
	private ImageButton botonRisas15;
	private ImageButton botonRisas16;
	private ImageButton botonRisas17;
	private ImageButton botonRisas18;
	private MediaPlayer mPlayer;
	private LayoutInflater layoutInflater;
	private int pause = 0;
	AdView vista;
	private FrameLayout adContainerView;
/*
 * Aadir plantillas en onCreate
 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		
		//display = this.getWindowManager().getDefaultDisplay();
		 setContentView(R.layout.sounds);
		 Bundle extra = this.getIntent().getExtras();
		MobileAds.initialize(this, new OnInitializationCompleteListener() {
			@Override
			public void onInitializationComplete(InitializationStatus initializationStatus) { }
		});
		try {
			vista = new AdView(this);
			adContainerView = findViewById(R.id.adView);
			adContainerView.addView(vista);
			loadBanner();
			//	AdRequest solicitud = new AdRequest.Builder().build();
			//	vista.loadAd(solicitud);
		} catch (Throwable t) {
			int i = 0;
		}
	/*	 try {
				vista = (AdView)this.findViewById(R.id.adView);
				AdRequest solicitud = new AdRequest.Builder()
					 .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
					 //.addTestDevice("SomeString")
					 .build();
				vista.loadAd(solicitud);
				} catch (Throwable t) {
	        // No revenue from this guy for today... :(
				
				}          */
	         // Watch for button clicks
			botonRisas1=(ImageButton)findViewById(R.id.risasboton1);
			botonRisas2=(ImageButton)findViewById(R.id.risasboton2);
			//botonRisas3=(ImageButton)findViewById(R.id.risasboton3);
			botonRisas4=(ImageButton)findViewById(R.id.risasboton4);
			//botonRisas5=(ImageButton)findViewById(R.id.risasboton5);
			botonRisas6=(ImageButton)findViewById(R.id.risasboton6);
			botonRisas7=(ImageButton)findViewById(R.id.risasboton7);
			botonRisas8=(ImageButton)findViewById(R.id.risasboton8);
			//botonRisas9=(ImageButton)findViewById(R.id.risasboton9);
			botonRisas10=(ImageButton)findViewById(R.id.risasboton10);
			botonRisas11=(ImageButton)findViewById(R.id.risasboton11);
			botonRisas12=(ImageButton)findViewById(R.id.risasboton12);
			botonRisas13=(ImageButton)findViewById(R.id.risasboton13);
			botonRisas14=(ImageButton)findViewById(R.id.risasboton14);
			botonRisas15=(ImageButton)findViewById(R.id.risasboton15);
			botonRisas16=(ImageButton)findViewById(R.id.risasboton16);
			botonRisas17=(ImageButton)findViewById(R.id.risasboton17);
			botonRisas18=(ImageButton)findViewById(R.id.risasboton18);
			this.cargarImagenes();
			mPlayer = MediaPlayer.create(this,R.raw.sonido1);
			sonido = 0;
			layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);    
			
			}
		
	
public void risas1(View v) {
	this.silenciar();
	this.cargarImagenes();
    if (pause==0){
    	pause = 1;
    	botonRisas1.setImageResource(R.drawable.pause);
    } else {
    	pause=0;
    }
  
    if (pause==1){		
			mPlayer.reset();
			mPlayer = MediaPlayer.create(this,R.raw.sonido1);
			mPlayer.setLooping(true);
			mPlayer.start();
			sonido = 1;	
    }     	    			
}
public void risas2(View v) {
	this.silenciar();
	this.cargarImagenes();
    if (pause==0){
    	pause = 1;
    	botonRisas2.setImageResource(R.drawable.pause);
    } else {
    	pause=0;
    }
  
    if (pause==1){		
			mPlayer.reset();
			mPlayer = MediaPlayer.create(this,R.raw.sonido2);
			mPlayer.setLooping(true);
			mPlayer.start();
			sonido = 1;	
    }     	    			
}
public void risas4(View v) {
	this.silenciar();
	this.cargarImagenes();
    if (pause==0){
    	pause = 1;
    	botonRisas4.setImageResource(R.drawable.pause);
    } else {
    	pause=0;
    }
  
    if (pause==1){		
			mPlayer.reset();
			mPlayer = MediaPlayer.create(this,R.raw.sonido4);
			mPlayer.setLooping(true);
			mPlayer.start();
			sonido = 1;	
    }     	    			
}
public void risas6(View v) {
	this.silenciar();
	this.cargarImagenes();
    if (pause==0){
    	pause = 1;
    	botonRisas6.setImageResource(R.drawable.pause);
    } else {
    	pause=0;
    }
  
    if (pause==1){		
			mPlayer.reset();
			mPlayer = MediaPlayer.create(this,R.raw.sonido6);
			mPlayer.setLooping(true);
			mPlayer.start();
			sonido = 1;	
    }     	    			
}
public void risas7(View v) {
	this.silenciar();
	this.cargarImagenes();
    if (pause==0){
    	pause = 1;
    	botonRisas7.setImageResource(R.drawable.pause);
    } else {
    	pause=0;
    }
  
    if (pause==1){		
			mPlayer.reset();
			mPlayer = MediaPlayer.create(this,R.raw.sonido7);
			mPlayer.setLooping(true);
			mPlayer.start();
			sonido = 1;	
    }     	    			
}
public void risas8(View v) {
	this.silenciar();
	this.cargarImagenes();
    if (pause==0){
    	pause = 1;
    	botonRisas8.setImageResource(R.drawable.pause);
    } else {
    	pause=0;
    }
  
    if (pause==1){		
			mPlayer.reset();
			mPlayer = MediaPlayer.create(this,R.raw.sonido8);
			mPlayer.setLooping(true);
			mPlayer.start();
			sonido = 1;	
    }     	    			
}
public void risas10(View v) {
	this.silenciar();
	this.cargarImagenes();
    if (pause==0){
    	pause = 1;
    	botonRisas10.setImageResource(R.drawable.pause);
    } else {
    	pause=0;
    }
  
    if (pause==1){		
			mPlayer.reset();
			mPlayer = MediaPlayer.create(this,R.raw.sonido10);
			mPlayer.setLooping(true);
			mPlayer.start();
			sonido = 1;	
    }     	    			
}
public void risas11(View v) {
	this.silenciar();
	this.cargarImagenes();
    if (pause==0){
    	pause = 1;
    	botonRisas11.setImageResource(R.drawable.pause);
    } else {
    	pause=0;
    }
  
    if (pause==1){		
			mPlayer.reset();
			mPlayer = MediaPlayer.create(this,R.raw.sonido11);
			mPlayer.setLooping(true);
			mPlayer.start();
			sonido = 1;	
    }     	    			
}
public void risas12(View v) {
	this.silenciar();
	this.cargarImagenes();
    if (pause==0){
    	pause = 1;
    	botonRisas12.setImageResource(R.drawable.pause);
    } else {
    	pause=0;
    }
  
    if (pause==1){		
			mPlayer.reset();
			mPlayer = MediaPlayer.create(this,R.raw.sonido12);
			mPlayer.setLooping(true);
			mPlayer.start();
			sonido = 1;	
    }     	    			
}
public void risas13(View v) {
	this.silenciar();
	this.cargarImagenes();
    if (pause==0){
    	pause = 1;
    	botonRisas13.setImageResource(R.drawable.pause);
    } else {
    	pause=0;
    }
  
    if (pause==1){		
			mPlayer.reset();
			mPlayer = MediaPlayer.create(this,R.raw.horror1);
			mPlayer.setLooping(true);
			mPlayer.start();
			sonido = 1;	
    }     	    			
}
public void risas14(View v) {
	this.silenciar();
	this.cargarImagenes();
    if (pause==0){
    	pause = 1;
    	botonRisas14.setImageResource(R.drawable.pause);
    } else {
    	pause=0;
    }
  
    if (pause==1){		
			mPlayer.reset();
			mPlayer = MediaPlayer.create(this,R.raw.horror2);
			mPlayer.setLooping(true);
			mPlayer.start();
			sonido = 1;	
    }     	    			
}
public void risas15(View v) {
	this.silenciar();
	this.cargarImagenes();
    if (pause==0){
    	pause = 1;
    	botonRisas15.setImageResource(R.drawable.pause);
    } else {
    	pause=0;
    }
  
    if (pause==1){		
			mPlayer.reset();
			mPlayer = MediaPlayer.create(this,R.raw.horror3);
			mPlayer.setLooping(true);
			mPlayer.start();
			sonido = 1;	
    }     	    			
}
public void risas16(View v) {
	this.silenciar();
	this.cargarImagenes();
    if (pause==0){
    	pause = 1;
    	botonRisas16.setImageResource(R.drawable.pause);
    } else {
    	pause=0;
    }
  
    if (pause==1){		
			mPlayer.reset();
			mPlayer = MediaPlayer.create(this,R.raw.horror4);
			mPlayer.setLooping(true);
			mPlayer.start();
			sonido = 1;	
    }     	    			
}
public void risas17(View v) {
	this.silenciar();
	this.cargarImagenes();
    if (pause==0){
    	pause = 1;
    	botonRisas17.setImageResource(R.drawable.pause);
    } else {
    	pause=0;
    }
  
    if (pause==1){		
			mPlayer.reset();
			mPlayer = MediaPlayer.create(this,R.raw.horror5);
			mPlayer.setLooping(true);
			mPlayer.start();
			sonido = 1;	
    }     	    			
}
public void risas18(View v) {
	this.silenciar();
	this.cargarImagenes();
    if (pause==0){
    	pause = 1;
    	botonRisas18.setImageResource(R.drawable.pause);
    } else {
    	pause=0;
    }
  
    if (pause==1){		
			mPlayer.reset();
			mPlayer = MediaPlayer.create(this,R.raw.horror6);
			mPlayer.setLooping(true);
			mPlayer.start();
			sonido = 1;	
    }     	    			
}
public void silenciar() {
	if (mPlayer.isPlaying()){
		mPlayer.reset();
	}
}
public void cargarImagenes() {
	botonRisas1.setImageResource(R.drawable.f1520p);
	botonRisas2.setImageResource(R.drawable.f1600p);
	//botonRisas3.setImageResource(R.drawable.f1500p);
	botonRisas4.setImageResource(R.drawable.f1490p);
	//botonRisas5.setImageResource(R.drawable.f1480p);
	botonRisas6.setImageResource(R.drawable.f1470p);
	botonRisas7.setImageResource(R.drawable.f1460p);
	botonRisas8.setImageResource(R.drawable.f1450p);
	//botonRisas9.setImageResource(R.drawable.f1440p);
	botonRisas10.setImageResource(R.drawable.f1430p);
	botonRisas11.setImageResource(R.drawable.f1420p);
	botonRisas12.setImageResource(R.drawable.f1410p);
	botonRisas13.setImageResource(R.drawable.f1390p);
	botonRisas14.setImageResource(R.drawable.f1380p);
	botonRisas15.setImageResource(R.drawable.f1370p);
	botonRisas16.setImageResource(R.drawable.f1360p);
	botonRisas17.setImageResource(R.drawable.f1350p);
	botonRisas18.setImageResource(R.drawable.f1340p);
}

@Override
protected void onPause() {
    // TODO Auto-generated method stub
    super.onPause();
    this.silenciar();
}	
protected void onResume() {
    // TODO Auto-generated method stub
    super.onPause();
    this.silenciar();
}
	private void loadBanner() {
		// Create an ad request. Check your logcat output for the hashed device ID
		// to get test ads on a physical device, e.g.,
		// "Use AdRequest.Builder.addTestDevice("ABCDE0123") to get test ads on this
		// device."
		//AdRequest adRequest =
		//        new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
		//                .build();

		AdSize adSize = getAdSize();
		AdRequest solicitud = new AdRequest.Builder().build();
		// Step 4 - Set the adaptive ad size on the ad view.
		vista.setAdSize(adSize);
		vista.setAdUnitId(getResources().getString(R.string.banner_ad_unit_id));


		// Step 5 - Start loading the ad in the background.
		vista.loadAd(solicitud);
	}

	private AdSize getAdSize() {
		// Step 2 - Determine the screen width (less decorations) to use for the ad width.
		Display display = getWindowManager().getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics();
		display.getMetrics(outMetrics);

		float widthPixels = outMetrics.widthPixels;
		float density = outMetrics.density;

		int adWidth = (int) (widthPixels / density);

		// Step 3 - Get adaptive ad size and return for setting on the ad view.
		return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
	}
}
