package foto.fantasma.broma;


import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;


import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Ghost extends Activity {
	private RadioButton r1;
	private RadioButton r2;
	private ImageButton botonAvanza;
	private int o;
	private InterstitialAd interstitial;
	private FrameLayout adContainerView;
	AdView vista;
	AlertDialog alertDialogt;
	AlertDialog.Builder buildert;
	/*AlertDialog alertDialog;
	AlertDialog.Builder builder;*/
	AlertDialog alertDialogRate;
	AlertDialog.Builder builderRate;
	private static final int PERMISSIONS_READ = 100;
	private static final int PERMISSIONS_WRITE = 200;
	//private static final int PERMISSIONS_CAMERA = 300;
	private static final int PERMISSIONS_REQUEST_MEDIA_OR_STORAGE = 100;
	private static final int PERMISSIONS_REQUEST_CAMERA = 101;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		o = 1;

		setContentView(R.layout.actghost);
       
  /*   // Crear el intersticial.
        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId(getString(R.string.inter_ad_unit_test));

        // Crear la solicitud de anuncio.
        AdRequest adRequest = new AdRequest.Builder().build();

        // Comenzar la carga del intersticial.
        interstitial.loadAd(adRequest);*/

		AdRequest adRequestInt = new AdRequest.Builder().build();

		InterstitialAd.load(this, getString(R.string.inter_ad_unit_id), adRequestInt,
				new InterstitialAdLoadCallback() {
					@Override
					public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
						// The mInterstitialAd reference will be null until
						// an ad is loaded.
						interstitial = interstitialAd;
					}

					@Override
					public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
						// Handle the error
						interstitial = null;
					}
				});

		r1 = (RadioButton) findViewById(R.id.r1);
		r2 = (RadioButton) findViewById(R.id.r2);
		botonAvanza = (ImageButton) findViewById(R.id.avanzar);
		final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
		
		/*try {
			vista = (AdView)this.findViewById(R.id.adView);
			AdRequest solicitud = new AdRequest.Builder()
					.build();
			vista.loadAd(solicitud);
			} catch (Throwable t) {*/
		MobileAds.initialize(this, new OnInitializationCompleteListener() {
			@Override
			public void onInitializationComplete(InitializationStatus initializationStatus) {
			}
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
		// No revenue from this guy for today... :(


		// Watch for button clicks
		/*
		int permissionCheckW = ContextCompat.checkSelfPermission(Ghost.this,
				Manifest.permission.WRITE_EXTERNAL_STORAGE);

		if (permissionCheckW == PackageManager.PERMISSION_GRANTED) {

		} else {
			ActivityCompat.requestPermissions(Ghost.this,
					new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
					PERMISSIONS_WRITE);

		}
		int permissionCheckC = ContextCompat.checkSelfPermission(Ghost.this,
				Manifest.permission.CAMERA);
		*/
		//if (permissionCheckC == PackageManager.PERMISSION_GRANTED) {

		//} else {
		//	ActivityCompat.requestPermissions(Ghost.this,
		//			new String[]{Manifest.permission.CAMERA},
		//			PERMISSIONS_CAMERA);

		//}
		checkAndRequestAppPermissions();
	}

	@Override
	public void onRequestPermissionsResult(int requestCode,
										   String permissions[], int[] grantResults) {
		switch (requestCode) {
			case PERMISSIONS_READ: {
				// If request is cancelled, the result arrays are empty.
				if (grantResults.length > 0
						&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {

					// permission was granted, yay! Do the
					// contacts-related task you need to do.

				} else {
					//Toast.makeText(this, "Sorry!!! Permission Denied, Dont run this app", Toast.LENGTH_SHORT).show();
					// permission denied, boo! Disable the
					// functionality that depends on this permission.
				}
				return;
			}
			case PERMISSIONS_WRITE: {
				// If request is cancelled, the result arrays are empty.
				if (grantResults.length > 0
						&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {

					// permission was granted, yay! Do the
					// contacts-related task you need to do.

				} else {
					//Toast.makeText(this, "Sorry!!! Permission Denied, Dont run this app", Toast.LENGTH_LONG).show();
					// permission denied, boo! Disable the
					// functionality that depends on this permission.
				}
				return;
			}
			//case PERMISSIONS_CAMERA: {
			// If request is cancelled, the result arrays are empty.
			//	if (grantResults.length > 0
			//			&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {

			// permission was granted, yay! Do the
			// contacts-related task you need to do.

			//	} else {
			//		Toast.makeText(this, "Sorry!!! Permission Denied, Dont run this app, need to access the camera", Toast.LENGTH_LONG).show();
			// permission denied, boo! Disable the
			// functionality that depends on this permission.
			//	}
			//	return;

		}
	}

	public void avanzar(View v) {
		SharedPreferences settings = getSharedPreferences("perfil", MODE_PRIVATE);
		String enlace = settings.getString("valorenlaceghost", "N");
    	/*if (enlace.equalsIgnoreCase("N")){
    		alertDialog = builder.create();
        	alertDialog.show();	} else {*/
		Intent myIntent = new Intent(Ghost.this, MenuP.class);

		int o = 0;
		if (r1.isChecked()) {
			o = 1;
		} else if (r2.isChecked()) {
			o = 2;
		}
		myIntent.putExtra("orientacion", o);
		startActivity(myIntent);
		displayInterstitial();

		//}
	}

	public void avanzars(View v) {
		Intent myIntent = new Intent(Ghost.this, Sounds.class);
		int o = 0;
		if (r1.isChecked()) {
			o = 1;
		} else if (r2.isChecked()) {
			o = 2;
		}
		myIntent.putExtra("orientacion", o);
		startActivity(myIntent);
		displayInterstitial();
	}

	public void compartirapp(View v) {
		String compartoStr = getString(R.string.compartoapp) +
				" https://play.google.com/store/apps/details?id=foto.fantasma.broma";
		//		" http://www.amazon.es/s?ie=UTF8&field-keywords=NachosWare";
		Intent sharingIntent = new Intent(Intent.ACTION_SEND);
		sharingIntent.setType("text/plain");
		sharingIntent.putExtra(Intent.EXTRA_TEXT, compartoStr);
		startActivity(Intent.createChooser(sharingIntent, "Share app using:"));
	}

	@Override
	public void onBackPressed() {

		finish();
	}

	/*// Invoca displayInterstitial() cuando est preparado para mostrar un intersticial.
	   public void displayInterstitial() {
		 if (interstitial.isLoaded()) {
		   interstitial.show();
		 }
	   }*/
	// Invoca displayInterstitial() cuando est preparado para mostrar un intersticial.
	public void displayInterstitial() {
		if (interstitial != null) {
			interstitial.setFullScreenContentCallback(new FullScreenContentCallback() {
				@Override
				public void onAdDismissedFullScreenContent() {
					// Called when fullscreen content is dismissed.
					//Log.d("TAG", "The ad was dismissed.");
				}

				@Override
				public void onAdFailedToShowFullScreenContent(AdError adError) {
					// Called when fullscreen content failed to show.
					//Log.d("TAG", "The ad failed to show.");
				}

				@Override
				public void onAdShowedFullScreenContent() {
					// Called when fullscreen content is shown.
					// Make sure to set your reference to null so you don't
					// show it a second time.
					interstitial = null;
					//Log.d("TAG", "The ad was shown.");
				}
			});
			interstitial.show(this);
		}
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

	private void checkAndRequestAppPermissions() {
		// --- Manejo del permiso de Cámara ---
		int permissionCheckC = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
		if (permissionCheckC != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this,
					new String[]{Manifest.permission.CAMERA},
					PERMISSIONS_REQUEST_CAMERA);
		}

		// --- Manejo de permisos de Almacenamiento/Multimedia ---
		// Comprobamos la versión de Android
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13 (API 33) o superior
			// Para Android 13+, solicitamos los permisos de medios granulares si necesitamos leer de la galería
			// Si solo estás CREANDO archivos propios (ej. una foto que toma tu app), a menudo NO necesitas estos permisos,
			// y debes usar MediaStore API para guardar la foto directamente.
			boolean hasReadMediaImages = ContextCompat.checkSelfPermission(this,
					Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED;
			boolean hasReadMediaVideo = ContextCompat.checkSelfPermission(this,
					Manifest.permission.READ_MEDIA_VIDEO) == PackageManager.PERMISSION_GRANTED;
			boolean hasReadMediaAudio = ContextCompat.checkSelfPermission(this,
					Manifest.permission.READ_MEDIA_AUDIO) == PackageManager.PERMISSION_GRANTED;

			// Prepara una lista de permisos que realmente necesitas y que no tienes
			String[] permissionsToRequest = new String[3]; // Max 3, ajusta según lo que necesites
			int index = 0;
			if (!hasReadMediaImages) {
				permissionsToRequest[index++] = Manifest.permission.READ_MEDIA_IMAGES;
			}
			if (!hasReadMediaVideo) {
				permissionsToRequest[index++] = Manifest.permission.READ_MEDIA_VIDEO;
			}
			if (!hasReadMediaAudio) {
				permissionsToRequest[index++] = Manifest.permission.READ_MEDIA_AUDIO;
			}

			// Si hay permisos pendientes, los solicitamos
			if (index > 0) {
				String[] finalPermissions = new String[index];
				System.arraycopy(permissionsToRequest, 0, finalPermissions, 0, index);
				ActivityCompat.requestPermissions(this,
						finalPermissions,
						PERMISSIONS_REQUEST_MEDIA_OR_STORAGE);
			}

			// Nota: El permiso WRITE_EXTERNAL_STORAGE ya no se solicita ni se usa para la mayoría de los casos
			// en Android 13+. Si tu aplicación CREA sus propios archivos multimedia (fotos/videos tomados por la app),
			// usa MediaStore API directamente sin solicitar permisos de almacenamiento.
			// Si CREA archivos NO multimedia (ej. PDFs, TXT) que son propios de la app, lo ideal es usar SAF o los
			// directorios específicos de la app, sin permisos de almacenamiento.

		} else { // Para versiones anteriores a Android 13 (API < 33, ej. Android 6 a Android 12)
			// Aquí es donde tu permiso WRITE_EXTERNAL_STORAGE sigue siendo relevante
			int permissionCheckW = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
			if (permissionCheckW != PackageManager.PERMISSION_GRANTED) {
				ActivityCompat.requestPermissions(this,
						new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
						PERMISSIONS_REQUEST_MEDIA_OR_STORAGE);
			}
			// También puedes necesitar READ_EXTERNAL_STORAGE aquí si lees archivos de otras apps
			int permissionCheckR = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
			if (permissionCheckR != PackageManager.PERMISSION_GRANTED) {
				ActivityCompat.requestPermissions(this,
						new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
						PERMISSIONS_REQUEST_MEDIA_OR_STORAGE);
			}
		}
	}
}