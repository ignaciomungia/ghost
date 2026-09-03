package foto.fantasma.broma;


import java.io.File;
import java.util.Date;


import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class MenuP extends Activity {
	
		//camara
		private static int TAKE_PICTURE = 1;
		private static int SELECT_PICTURE = 2;
		private static int CAPTURE_PICTURE_INTENT = 2;

		private int defineEfecto = 1340;
		private int camara = 0;
		
		//camara
		private static int RESULT_LOAD_IMAGE = 1;
		private Bitmap bmp;
		private Button botonCamara;
		private Button botonImagenes;
		private Button botonSinImagenes;
		Toast toast1 = null;
		private Uri outputFileUri;
		private RadioButton r1;
		private RadioButton r2;
		private PopupWindow popupWindow;
		private ImageButton botonEfecto;
		private ImageButton botonIzda;
		private ImageButton botonDcha;
		private ImageButton botonCentro;
		private Uri mCapturedImageURI; 
		private int o;
		
		private View popupView;
		private LayoutInflater layoutInflater;
		
		private int imagenCentrado = 0;
		private static final int CAMERA_REQUEST = 1888;
		private AdView vista;
		private FrameLayout adContainerView;
		// arreglo nullpointer
		AlertDialog alertDialog;
		AlertDialog.Builder builder;

		
	
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	    Bundle extra = this.getIntent().getExtras();
		o = extra.getInt("orientacion");
		if (o == 1){
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		} else if (o == 2){
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
// arreglo nullpointer
		} else if (o == 3){
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		} else if (o == 4){
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}
        setContentView(R.layout.act0pub);
        botonEfecto=(ImageButton)findViewById(R.id.imagenefecto);
        botonIzda=(ImageButton)findViewById(R.id.imagenizda);
        botonDcha=(ImageButton)findViewById(R.id.imagendcha);
        botonCentro=(ImageButton)findViewById(R.id.imagencentro);
        imagenCentrado = 0;
        botonIzda.setImageResource(R.drawable.f1000iu);
      	botonCentro.setImageResource(R.drawable.f1000cd);
      	botonDcha.setImageResource(R.drawable.f1000dd);

        botonCamara = (Button)findViewById(R.id.imagenCamara);
        botonCamara.setOnClickListener(imagenCamaraAccion);
        
        botonImagenes = (Button)findViewById(R.id.imagenGaleria);
        botonImagenes.setOnClickListener(imagenGaleriaAccion);
        
        layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        
        LinearLayout linearPenguin = (LinearLayout)findViewById(R.id.layout2);
/*
    	try {
			vista = (AdView)this.findViewById(R.id.adView);
			AdRequest solicitud = new AdRequest.Builder()
					.build();
			vista.loadAd(solicitud);
			} catch (Throwable t) {
        // No revenue from this guy for today... :(
			}*/
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
    	//arreglo nullpointer
    	if (o  == 3 || o == 4){
    		 builder = new AlertDialog.Builder(this);    		 
    		 builder.setTitle(getString(R.string.errorpictitulo));
    		 builder.setMessage(getString(R.string.errorpicmensaje));
    		 builder.setIcon(getResources().getDrawable(android.R.drawable.picture_frame));
    		 builder.setCancelable(false);
    		 builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    		 public void onClick(DialogInterface dialog, int id) {
    		         dialog.cancel();
    		        
    		 	}
    		  });
			alertDialog = builder.create();
			alertDialog.show();
    	}
    	if (o  == 3){
    		o = 1;
    	} else if (o  == 4) {
			o = 2;
		}
	}

    public void mostrarEfectos1(View v) {
    	if (popupWindow != null){
			popupWindow.dismiss();
		}
	   
	    popupView = layoutInflater.inflate(R.layout.popupefectos, null); 
		popupWindow = new PopupWindow(popupView, LayoutParams.MATCH_PARENT,
				  LayoutParams.MATCH_PARENT);
   	    	 
	    popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

   }
    
    public void imagenIzda(View v) {
      	imagenCentrado = 0; 
      	botonIzda.setImageResource(R.drawable.f1000iu);
      	botonCentro.setImageResource(R.drawable.f1000cd);
      	botonDcha.setImageResource(R.drawable.f1000dd);
    }
    public void imagenCentro(View v) {
      	imagenCentrado = 1; 	  	
      	botonIzda.setImageResource(R.drawable.f1000id);
      	botonCentro.setImageResource(R.drawable.f1000cu);
      	botonDcha.setImageResource(R.drawable.f1000dd);
    }
    public void imagenDcha(View v) {
      	imagenCentrado = 2; 	  	
      	botonIzda.setImageResource(R.drawable.f1000id);
      	botonCentro.setImageResource(R.drawable.f1000cd);
      	botonDcha.setImageResource(R.drawable.f1000du);
    }
	
    public void cambiarImagen(View v) {
      	 popupWindow.dismiss();
         defineEfecto = Integer.parseInt(v.getContentDescription().toString());
        
         switch(defineEfecto){
 		case 1000:
 		{
 			 botonEfecto.setImageResource(R.drawable.f1000p);
 		
 			break;
 		}
 		case 1010:
 		{
 			botonEfecto.setImageResource(R.drawable.f1010p);
 		
 		   break;
 		}
		case 1020:
 		{
 			botonEfecto.setImageResource(R.drawable.f1020p);
 		
 		   break;
 		}
		case 1030:
 		{
 			botonEfecto.setImageResource(R.drawable.f1030p);
 		
 		   break;
 		}
		case 1040:
 		{
 			botonEfecto.setImageResource(R.drawable.f1040p);
 		
 		   break;
 		}
		case 1050:
 		{
 			botonEfecto.setImageResource(R.drawable.f1050p);
 		
 		   break;
 		}
		case 1060:
 		{
 			botonEfecto.setImageResource(R.drawable.f1060p);
 		
 		   break;
 		}
		case 1070:
 		{
 			botonEfecto.setImageResource(R.drawable.f1070p);
 		
 		   break;
 		}
		case 1080:
 		{
 			botonEfecto.setImageResource(R.drawable.f1080p);
 		
 		   break;
 		}
		case 1090:
 		{
 			botonEfecto.setImageResource(R.drawable.f1090p);
 		
 		   break;
 		}
		case 1100:
 		{
 			botonEfecto.setImageResource(R.drawable.f1100p);
 		
 		   break;
 		}
		case 1110:
 		{
 			botonEfecto.setImageResource(R.drawable.f1110p);
 		
 		   break;
 		}
		case 1120:
 		{
 			botonEfecto.setImageResource(R.drawable.f1120p);
 		
 		   break;
 		}
		case 1130:
 		{
 			botonEfecto.setImageResource(R.drawable.f1130p);
 		
 		   break;
 		}
		case 1140:
 		{
 			botonEfecto.setImageResource(R.drawable.f1140p);
 		
 		   break;
 		}
		case 1150:
 		{
 			botonEfecto.setImageResource(R.drawable.f1150p);
 		
 		   break;
 		}
		case 1160:
 		{
 			botonEfecto.setImageResource(R.drawable.f1160p);
 		
 		   break;
 		}
		case 1170:
 		{
 			botonEfecto.setImageResource(R.drawable.f1170p);
 		
 		   break;
 		}
		case 1180:
 		{
 			botonEfecto.setImageResource(R.drawable.f1180p);
 		
 		   break;
 		}
		case 1190:
 		{
 			botonEfecto.setImageResource(R.drawable.f1190p);
 		
 		   break;
 		}
		case 1200:
 		{
 			botonEfecto.setImageResource(R.drawable.f1200p);
 		
 		   break;
 		}
		case 1210:
 		{
 			botonEfecto.setImageResource(R.drawable.f1210p);
 		
 		   break;
 		}
		case 1220:
 		{
 			botonEfecto.setImageResource(R.drawable.f1220p);
 		
 		   break;
 		}
		case 1230:
 		{
 			botonEfecto.setImageResource(R.drawable.f1230p);
 		
 		   break;
 		}
		case 1240:
 		{
 			botonEfecto.setImageResource(R.drawable.f1240p);
 		
 		   break;
 		}
		case 1250:
 		{
 			botonEfecto.setImageResource(R.drawable.f1250p);
 		
 		   break;
 		}
		case 1260:
 		{
 			botonEfecto.setImageResource(R.drawable.f1260p);
 		
 		   break;
 		}
		case 1270:
 		{
 			botonEfecto.setImageResource(R.drawable.f1270p);
 		
 		   break;
 		}
		case 1280:
 		{
 			botonEfecto.setImageResource(R.drawable.f1280p);
 		
 		   break;
 		}
		case 1290:
 		{
 			botonEfecto.setImageResource(R.drawable.f1290p);
 		
 		   break;
 		}
		case 1300:
 		{
 			botonEfecto.setImageResource(R.drawable.f1300p);
 		
 		   break;
 		}
		case 1310:
 		{
 			botonEfecto.setImageResource(R.drawable.f1310p);
 		
 		   break;
 		}
		case 1320:
 		{
 			botonEfecto.setImageResource(R.drawable.f1320p);
 		
 		   break;
 		}
		case 1330:
 		{
 			botonEfecto.setImageResource(R.drawable.f1330p);
 		
 		   break;
 		}
		case 1340:
 		{
 			botonEfecto.setImageResource(R.drawable.f1340p);
 		
 		   break;
 		}
		case 1350:
 		{
 			botonEfecto.setImageResource(R.drawable.f1350p);
 		
 		   break;
 		}
		case 1360:
 		{
 			botonEfecto.setImageResource(R.drawable.f1360p);
 		
 		   break;
 		}
		case 1370:
 		{
 			botonEfecto.setImageResource(R.drawable.f1370p);
 		
 		   break;
 		}
		case 1380:
 		{
 			botonEfecto.setImageResource(R.drawable.f1380p);
 		
 		   break;
 		}
		case 1390:
 		{
 			botonEfecto.setImageResource(R.drawable.f1390p);
 		
 		   break;
 		}
		case 1410:
 		{
 			botonEfecto.setImageResource(R.drawable.f1410p);
 		
 		   break;
 		}
		case 1420:
 		{
 			botonEfecto.setImageResource(R.drawable.f1420p);
 		
 		   break;
 		}
		case 1430:
 		{
 			botonEfecto.setImageResource(R.drawable.f1430p);
 		
 		   break;
 		}
		case 1440:
 		{
 			botonEfecto.setImageResource(R.drawable.f1440p);
 		
 		   break;
 		}
		case 1450:
 		{
 			botonEfecto.setImageResource(R.drawable.f1450p);
 		
 		   break;
 		}
		case 1460:
 		{
 			botonEfecto.setImageResource(R.drawable.f1460p);
 		
 		   break;
 		}
		case 1470:
 		{
 			botonEfecto.setImageResource(R.drawable.f1470p);
 		
 		   break;
 		}
		case 1480:
 		{
 			botonEfecto.setImageResource(R.drawable.f1480p);
 		
 		   break;
 		}
		case 1490:
 		{
 			botonEfecto.setImageResource(R.drawable.f1490p);
 		
 		   break;
 		}
		case 1500:
 		{
 			botonEfecto.setImageResource(R.drawable.f1500p);
 		
 		   break;
 		}
		case 1510:
 		{
 			botonEfecto.setImageResource(R.drawable.f1510p);
 		
 		   break;
 		}
		case 1520:
 		{
 			botonEfecto.setImageResource(R.drawable.f1520p);
 		
 		   break;
 		}
		case 1600:
 		{
 			botonEfecto.setImageResource(R.drawable.f1600p);
 		
 		   break;
 		}
		case 1610:
 		{
 			botonEfecto.setImageResource(R.drawable.f1610p);
 		
 		   break;
 		}
		case 1620:
 		{
 			botonEfecto.setImageResource(R.drawable.f1620p);
 		
 		   break;
 		}
		case 1630:
 		{
 			botonEfecto.setImageResource(R.drawable.f1630p);
 		
 		   break;
 		}
 		case 1640:
		{
			botonEfecto.setImageResource(R.drawable.f1640p);

			break;
		}case 1650:
			{
				botonEfecto.setImageResource(R.drawable.f1650p);

				 break;
			 }
 		default:
 			botonEfecto.setImageResource(R.drawable.f1340p);
 	   
 		}	
    }
 
        // vamos a la galeria de fotos
    private OnClickListener imagenGaleriaAccion = new OnClickListener() {
        public void onClick(View v) {
            //PRUEBA 2020 ERROR
          //Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
			// Intent i = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
			Intent i = new Intent(Intent.ACTION_GET_CONTENT);
			i.addCategory(Intent.CATEGORY_OPENABLE);
			i.setType("image/*");
			//i.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
            startActivityForResult(i, SELECT_PICTURE);
          }       	
        };    
        
	 private OnClickListener imagenCamaraAccion = new OnClickListener() {
		 public void onClick(View v) {
			 String state = Environment.getExternalStorageState();
			 File sd;
			 if (Environment.MEDIA_MOUNTED.equals(state)) {
				 if (Build.VERSION.SDK_INT > 28) {
					 sd = MenuP.this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
				 } else {
					 sd = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
				 }
			 } else {
				 sd = MenuP.this.getFilesDir();
			 }
			 //String sd = Environment.getExternalStorageDirectory().getAbsolutePath() + "/photoghost";
			 //File dir = new File (sd);
			 sd.mkdirs();
			 Date d = new Date();
			 long c = d.getTime();
			 String nameF = "";
			 nameF = "photoghost" + c + ".jpg";
			 //nameF = "photoghost" + ".jpg";

			 File file = new File(sd, nameF);
			 //outputFileUri = Uri.fromFile(file);
			 outputFileUri = FileProvider.getUriForFile(MenuP.this, getPackageName() + ".provider", file);

			 Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

			 intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
			 intent.putExtra("return-data", true);
			 intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			 intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

			 try {
				 startActivityForResult(intent, TAKE_PICTURE);
			 } catch (Exception e) {
				 toast1 = Toast.makeText(getApplicationContext(), "No Camera Access", Toast.LENGTH_SHORT);
				 toast1.show();
			 }
	 	}
	 };
		
        @Override    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        	super.onActivityResult(requestCode, resultCode, data); 
   
//Comprobamos el estado de la memoria externa (tarjeta SD) 
        	String estado = Environment.getExternalStorageState(); 
        
        	if (estado.equals(Environment.MEDIA_MOUNTED)) {     
        		
        		} else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {     
        			toast1 = Toast.makeText(getApplicationContext(), "I cant save in your sd", Toast.LENGTH_SHORT);
        			toast1.show();
        			} else{     
        				toast1 = Toast.makeText(getApplicationContext(), "I cant read and save in your sd", Toast.LENGTH_SHORT);
        				toast1.show();
        				} 

//   Vuelve de seleccionar una imagen:  
// Este ya no se usa lo dejo por si acaso:    	
        	if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK && null != data) {             
        		
        		
				Bitmap bmp = (Bitmap) data.getExtras().get("data"); 
            	 
        		bmp.prepareToDraw();
        		Intent myIntent;
				if (o==1){
					myIntent = new Intent(MenuP.this, SquaresActivity.class);                 				
				} else  {
					myIntent = new Intent(MenuP.this, SquaresActivityh.class);  
				}
    			
    			myIntent.putExtra("piccamara", bmp);
    			myIntent.putExtra("efecto", defineEfecto);
     			myIntent.putExtra("centrado", imagenCentrado);
     			myIntent.putExtra("orientacion", o);
                startActivity(myIntent);		                
        		}       	
        	   
       //   Vuelve de la camara de fotos:
	   // 
        	else if (requestCode == TAKE_PICTURE && resultCode == RESULT_OK){
        		//if (data != null) {  
					if (outputFileUri != null) { 
						//String nombreF = outputFileUri.toString();
//						int wPantalla = getResources().getDisplayMetrics().widthPixels;
//						int hPantalla = getResources().getDisplayMetrics().heightPixels;
//							try {  
//								InputStream is; 
//								InputStream is2; 							
//								is = getContentResolver().openInputStream(outputFileUri);  
//								is2 = getContentResolver().openInputStream(outputFileUri);  
//								Bitmap bmp = SquaresActivity.decodeSampledBitmapFromStream(is, is2, wPantalla,hPantalla);
//								if (bmp.getHeight() < bmp.getWidth()){
//									o = 2;
//								}
//								else if (bmp.getHeight() > bmp.getWidth()){
//									o = 1;
//								}
        			//bmp.prepareToDraw();
//							}
//        			
//						catch (FileNotFoundException e) {
//							toast1 = Toast.makeText(getApplicationContext(), "File not found", Toast.LENGTH_SHORT);
//							toast1.show();
//						
//						}
        		
						Intent myIntent;
						if (o==1){
							myIntent = new Intent(MenuP.this, SquaresActivity.class);                 				
						} else  {
							myIntent = new Intent(MenuP.this, SquaresActivityh.class);  
						}
						myIntent.putExtra("picture", outputFileUri);
						//myIntent.putExtra("nombref", nombreF);
						myIntent.putExtra("efecto", defineEfecto);
						myIntent.putExtra("centrado", imagenCentrado);
						myIntent.putExtra("orientacion", o);
						camara = 1;
						myIntent.putExtra("camara", camara);
						startActivity(myIntent);
						}
        		 
        	} else if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK && data != null) {
// atencion pasa por aqu�:
        		Uri selectedImage = data.getData(); 
        		
        		 //       		int wPantalla = getResources().getDisplayMetrics().widthPixels;
//				int hPantalla = getResources().getDisplayMetrics().heightPixels;
//					try {  
//						InputStream is; 
//						InputStream is2; 							
//						is = getContentResolver().openInputStream(selectedImage);  
//						is2 = getContentResolver().openInputStream(selectedImage);  
						
//						Bitmap bmp = SquaresActivity.decodeSampledBitmapFromStream(is, is2, wPantalla,hPantalla);
//						
//						if (bmp.getHeight() < bmp.getWidth()){
//							o = 2;
//						}
//						else if (bmp.getHeight() > bmp.getWidth()){
//							o = 1;
//						}
			//bmp.prepareToDraw();
//					}
			
//				catch (FileNotFoundException e) {
//					toast1 = Toast.makeText(getApplicationContext(), "File not found", Toast.LENGTH_SHORT);
//					toast1.show();
//				
//				}
        	//	String nombreF = getRealPathFromURI(
        	//		       selectedImage, MenuP.this);
        		
        		Intent myIntent;
				if (o==1){
					myIntent = new Intent(MenuP.this, SquaresActivity.class);                 				
				} else  {
					myIntent = new Intent(MenuP.this, SquaresActivityh.class);  
				}
     			myIntent.putExtra("picture", selectedImage);
     			//myIntent.putExtra("nombref", nombreF);
     			myIntent.putExtra("efecto", defineEfecto);
     			myIntent.putExtra("centrado", imagenCentrado);
     			myIntent.putExtra("orientacion", o);
     			camara = 0;
     			myIntent.putExtra("camara", camara);
     			startActivity(myIntent);
        	}
        }

 //      public static String getRealPathFromURI(Uri contentUri, Activity activity) {
        

        //    String[] proj = {
         //       MediaStore.Images.Media.DISPLAY_NAME
         //   };

           // Cursor cursor = activity.managedQuery(contentUri, proj, null, null,
            //         null);

         //   int column_index = cursor
         //            .getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);

         //   cursor.moveToFirst();
          //  return cursor.getString(column_index);
        //}
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
		//Display display = getWindowManager().getDefaultDisplay();
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
