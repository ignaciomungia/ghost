 package foto.fantasma.broma;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.InputStream;

import android.app.Activity;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.database.Cursor;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;

import android.graphics.BitmapFactory;

import android.media.ExifInterface;
import android.media.FaceDetector;
import android.media.FaceDetector.Face;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.net.Uri;

import android.os.Bundle;
import android.os.Environment;
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
import android.view.GestureDetector;

import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;

import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import android.widget.Toast;

import androidx.core.content.FileProvider;


 //cambiatu
public class SquaresActivityh extends Activity {

	private CanvasView canvasView;
	private String gg = "m";
	private String ww = "t";
	private String vv = "o";
	private String dd = "a";
	private String ee = "n";
	private String jj = "f";
	private String hh = "b";
	private String ff = "s";
	private String ii = "r";
	private String pp = ".";
	private int brillo;
	private int defineEfecto;
	private int imagenCentrado;
	private Bitmap bmp;
	private Context contexto;
	private int auxa = 0;
	private int auxb = 0;
	private int imagentocada = 0;
	private int tamanoInicial = 50;
	private float tamanioInicialFijo = 50.f;

	private float tamanioMaximo = 150.f;
	private float tamanioMinimo = 10.f;


	private int visibilidad = 100;
	private int rotation = 0;
	private int w = 0;
	private int h = 0;
	private int xx = 0;
	private int yy = 0;
	private int wPantalla = 0;
	private int hPantalla = 0;
	private int wPantalla2 = 0;
	private int hPantalla2 = 0;
	private int sonido = 0;
	private int borrar = 0;
	//aammdd
	private String fechaDia = "0001";
	private String ssaammdd = "0002";
	
	private Integer tipo = 1;
	private Integer plantilla = 0;
	private LinearLayout linearLayout;
	private ScaleGestureDetector mScaleDetector;   
	
	private float mScaleFactor = 1.f; 
	
	private Toast toast1;  

	private Uri selectedImage;
	
	private PopupWindow popupWindow;
	private LayoutInflater layoutInflater;
	private View popupView;
	private MediaPlayer mPlayer;
	private int camara = 0;
	private int pause = 0;
	private static int REQUEST_EXIT = 99999;
	private static int TAMANO_MAX = 480;
	private ImageButton botonRisas;

/*
 * Aadir plantillas en onCreate
 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		
		//display = this.getWindowManager().getDefaultDisplay();
		 setContentView(R.layout.act2h);
		 Bundle extra = this.getIntent().getExtras();
			defineEfecto   = extra.getInt("efecto");
			imagenCentrado = extra.getInt("centrado");
			camara = extra.getInt("camara");
			contexto = this.getApplicationContext();
			pause = 0;
			botonRisas=(ImageButton)findViewById(R.id.risasboton);
			botonRisas.setImageResource(R.drawable.speaker);
			mPlayer = MediaPlayer.create(this,R.raw.horror1);
			sonido = 0;
			layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);    
			
			//aammdd			
			try {
			 fechaDia = this.getPackageName();
			 } catch (Exception e) {
				// TODO: handle exception
			 fechaDia = "0001";
			}
			 
			//Display display = getWindowManager().getDefaultDisplay();
			//int rotation = display.getRotation();
			
			DisplayMetrics dm = new DisplayMetrics();

		    getWindowManager().getDefaultDisplay().getMetrics(dm);
		    int wPix = dm.widthPixels;
		    int hPix = dm.heightPixels;
			
			linearLayout = (LinearLayout)findViewById(R.id.surfaceView);
			tipo = 1;
//cambiatu			
			if (wPix < hPix){
				wPantalla = hPix;
				hPantalla = wPix;
			} else {
				wPantalla = wPix;
				hPantalla = hPix;
			}		
			wPantalla2 = wPantalla;
			hPantalla2 = hPantalla;
			
			plantilla = 0;
			ssaammdd = jj + vv + ww + vv + pp + jj + dd + ee +	ww + dd + ff + 
					gg + dd + pp + hh + ii + vv + gg + dd;
			
			if (extra == null){
				toast1 = Toast.makeText(getApplicationContext(), "Error: File not found", Toast.LENGTH_SHORT);
     			toast1.show();
//cambiatu				
				Intent myIntent = new Intent(SquaresActivityh.this, MenuP.class);
				int	o=1;
				
				myIntent.putExtra("orientacion", o);
				startActivity(myIntent);
			}
			//aammdd
			if (fechaDia.equalsIgnoreCase(ssaammdd)){
				//continue
			}else
			{
				if (fechaDia != "0001"){
					Intent myIntent = new Intent(SquaresActivityh.this, MenuP.class);
					int	o=1;					
					myIntent.putExtra("orientacion", o);
					startActivity(myIntent);
				}				
			}
	
			if ( extra != null ) {
				
	            Bitmap bmp2 = (Bitmap)extra.getParcelable("piccamara");
	            if  (bmp2==null){
	            	
	            
	            // imagen de la galeria:
	            //tipo = 1;
					selectedImage = (Uri)extra.getParcelable("picture"); 
					ExifInterface exif;
					if (selectedImage == null){
						toast1 = Toast.makeText(getApplicationContext(), "Error: File not found", Toast.LENGTH_SHORT);
		     			toast1.show();
						
							Intent myIntent = new Intent(SquaresActivityh.this, MenuP.class);
							int	o=1;
							
							myIntent.putExtra("orientacion", o);
							startActivity(myIntent);
						
					}
					try {
		     			exif = new ExifInterface(getRealPathFromURI(selectedImage));
		     			rotation = (int) exifOrientationToDegrees(exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL));

						} catch (IOException e) {
							rotation = 0;
						} catch (Exception e) {
							rotation=0;
						}			
					InputStream is; 
					InputStream is2; 

					try {     
						is = getContentResolver().openInputStream(selectedImage);         			
						is2 = getContentResolver().openInputStream(selectedImage);  
//cambiatu recalculamos las dimensiones de la foto sin alterar las de la pantalla
						if  (wPantalla2 > TAMANO_MAX){
							hPantalla2 = TAMANO_MAX * hPantalla / wPantalla;
							wPantalla2 = TAMANO_MAX;
						} 
						
						bmp = decodeSampledBitmapFromStream(is, is2, wPantalla2, hPantalla2);
						if (null == bmp){
							toast1 = Toast.makeText(getApplicationContext(), "Error: Invalid Image", Toast.LENGTH_SHORT);
			     			toast1.show();
							
								Intent myIntent = new Intent(SquaresActivityh.this, MenuP.class);
								int	o=1;
								
								myIntent.putExtra("orientacion", o);
								startActivity(myIntent);
							
						}
						is.close();
						is2.close();
						if  (rotation != 0) {
							Matrix matrix = new Matrix();							
							matrix.preRotate(rotation);
							bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
						} else {							
						//cambiatu
							if ((bmp.getWidth() < bmp.getHeight()) && (camara == 1)){
							//if (bmp.getWidth() > bmp.getHeight()){
								Matrix matrix = new Matrix();
								int orientation = 90;
								matrix.preRotate(orientation);
								bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
							}
						}
					} catch (FileNotFoundException e) {
        				toast1 = Toast.makeText(getApplicationContext(), "File not found", Toast.LENGTH_SHORT);
             			toast1.show();
        			} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            } else {
	            	bmp=bmp2;
	            }
	        }
	            
			tipo = 1;
			
			//aammdd    
			if (fechaDia.equalsIgnoreCase(ssaammdd)){
				//continue
			}else
			{
				if (fechaDia != "0001"){
					Intent myIntent = new Intent(SquaresActivityh.this, MenuP.class);
					int	o=1;					
					myIntent.putExtra("orientacion", o);
					startActivity(myIntent);
				}				
			}
	
			
		//if (w < h){
		//		h = (bmp.getHeight()*w/bmp.getWidth());
		//	}else{	
		//		w = (bmp.getWidth()*h/bmp.getHeight());
		//	}

//cambiatu		
			//arreglo nullpointer
				if (bmp == null){
					toast1 = Toast.makeText(getApplicationContext(), "Error: Invalid Image", Toast.LENGTH_SHORT);
					toast1.show();

					Intent myIntent = new Intent(SquaresActivityh.this, MenuP.class);
					int	o=3;

					myIntent.putExtra("orientacion", o);
					startActivity(myIntent);

				} else {
                    if (wPantalla < 1){
                        wPantalla = bmp.getWidth();
                    }
                    if (hPantalla < 1){
                        hPantalla = bmp.getHeight();
                    }
                    w = (bmp.getWidth() * hPantalla / bmp.getHeight());
                    h = hPantalla;
                    if (wPantalla < w) {
                        w = wPantalla;
                        h = (bmp.getHeight() * wPantalla / bmp.getWidth());
                    }
                    bmp = Bitmap.createScaledBitmap(bmp, w, h, true);
                    canvasView = new CanvasView(contexto, bmp, h, w, tipo, plantilla);
                    canvasView.setOnTouchListener(new MyTouchListener());
                    linearLayout.addView(canvasView, w, h);

                    auxa = tamanoInicial;
                    auxb = 9999;
                    SquareDrawable square = new SquareDrawable(w, h, 0, 0,
                            imagenCentrado, defineEfecto, contexto, auxa, auxb);
                    canvasView.addRenderable(square);
                    mScaleDetector = new ScaleGestureDetector(contexto, new ScaleListener());
                }
	}
		
	
	 
public void cambiarImagen(View v) {
		if (popupWindow != null){
			popupWindow.dismiss();
		 }
         defineEfecto = Integer.parseInt(v.getContentDescription().toString());
         auxb = 0;
         tamanoInicial = 50;
         visibilidad = 100;
         brillo = 0;
		 auxa = tamanoInicial;
		 SquareDrawable square = new SquareDrawable(w, h, 40, 40, 
		 imagenCentrado, defineEfecto, contexto, auxa, auxb);
		 canvasView.addRenderable(square);
	}
public static int calculateInSampleSize(             
		BitmapFactory.Options options, int reqWidth, int reqHeight) {     
	// Raw height and width of image     
	final int height = options.outHeight;     
	final int width = options.outWidth;     
	int inSampleSize = 1;      
	if (height > reqHeight || width > reqWidth) {          
		// Calculate ratios of height and width to requested height and width         
		final int heightRatio = Math.round((float) height / (float) reqHeight);         
		final int widthRatio = Math.round((float) width / (float) reqWidth);          
		// Choose the smallest ratio as inSampleSize value, this will guarantee         
		// a final image with both dimensions larger than or equal to the         
		// requested height and width.         
		inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;     
		}      
	return inSampleSize; 
	}
public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,         
		int reqWidth, int reqHeight) {      
	// First decode with inJustDecodeBounds=true to check dimensions     
	final BitmapFactory.Options options = new BitmapFactory.Options();     
	options.inJustDecodeBounds = true;     
	BitmapFactory.decodeResource(res, resId, options);      
	// Calculate inSampleSize     
	options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);      
	// Decode bitmap with inSampleSize set     
	options.inJustDecodeBounds = false;     
	return BitmapFactory.decodeResource(res, resId, options); 
	}
public static Bitmap decodeSampledBitmapFromStream(InputStream is, InputStream is2,       
		int reqWidth, int reqHeight) {      
	// First decode with inJustDecodeBounds=true to check dimensions     
	final BitmapFactory.Options options = new BitmapFactory.Options();     
	options.inJustDecodeBounds = true;   
	
	BitmapFactory.decodeStream (is, null, options);      
	// Calculate inSampleSize     
	options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);   
	// Decode bitmap with inSampleSize set     
	options.inJustDecodeBounds = false;     
	return BitmapFactory.decodeStream(is2, null, options); 
	}

    
public void undo(View v) {
     
	 canvasView.undoRenderable();
 	    			
	}
public void aumentarTamanio(View v) {
	 if (canvasView.getNumeroFantasmas() != 0){
		 tamanoInicial = tamanoInicial + 5;
		 if (tamanoInicial > 150){
			tamanoInicial = 150;
			}
		 canvasView.cambiarTamanio(imagentocada, tamanoInicial, visibilidad); 
	 }    
	}
public void disminuirTamanio(View v) {
	if (canvasView.getNumeroFantasmas() != 0){
		 tamanoInicial = tamanoInicial - 5;
		 if (tamanoInicial < 5){
			tamanoInicial = 5;
			}
		canvasView.cambiarTamanio(imagentocada, tamanoInicial, visibilidad);
		}
	}
	
public void disminuirVisibilidad(View v) {
	if (canvasView.getNumeroFantasmas() != 0){
		visibilidad = visibilidad - 10;
		 if (visibilidad < 10){
			visibilidad = 10;
			}
		canvasView.cambiarVisibilidad(imagentocada, visibilidad);
	}
	 
	}
public void aumentarVisibilidad(View v) {
	if (canvasView.getNumeroFantasmas() != 0){
		visibilidad = visibilidad + 10;
		 if (visibilidad > 255){
			visibilidad = 255;
			}
		canvasView.cambiarVisibilidad(imagentocada, visibilidad);
		}	
	}
public void blancoNegro(View v) {
	if (canvasView.getNumeroFantasmas() != 0){
		int desenfoque = 1;
		 
		canvasView.cambiarEnfoque(imagentocada, desenfoque);
		}	
	}
public void recargar(View v) {
	if (canvasView.getNumeroFantasmas() != 0){
		visibilidad = 100;
        brillo = 0; 
		canvasView.deshacer(imagentocada, visibilidad);
		}	
	}
public void aumentarBrillo(View v) {
	if (canvasView.getNumeroFantasmas() != 0){
		brillo = brillo + 10;
		canvasView.cambiarBrillo(imagentocada, brillo, visibilidad);
		}	
	}
public void disminuirBrillo(View v) {
	if (canvasView.getNumeroFantasmas() != 0){
		brillo = brillo - 10;
		canvasView.cambiarBrillo(imagentocada, brillo, visibilidad);
		
		}	
	}


public void risas(View v) {
	
    if (pause==0){
    	pause = 1;
    	botonRisas.setImageResource(R.drawable.pause);
    }
    else{
    	pause = 0;
    	this.silenciar();
    	botonRisas.setImageResource(R.drawable.speaker);
    }
    if (pause==1){
    	switch(sonido){
		case 0:
		{			
			mPlayer.reset();
			mPlayer = MediaPlayer.create(this,R.raw.horror1);
			mPlayer.start();
			sonido = 1;
		
			break;
		}
		case 1:
		{	
			mPlayer.reset();
			mPlayer = MediaPlayer.create(this,R.raw.horror2);
			mPlayer.start();
			sonido = 2;
			
		   break;
		}
		case 2:
		{
			mPlayer.reset();
			mPlayer = MediaPlayer.create(this,R.raw.horror3);
			mPlayer.start();
			 sonido = 3;
			
		   break;
		}
		case 3:
		{
			mPlayer.reset();
			mPlayer = MediaPlayer.create(this,R.raw.horror4);
			mPlayer.start();
			 sonido = 4;
			
		   break;
		}
		case 4:
		{
			mPlayer.reset();
			mPlayer = MediaPlayer.create(this,R.raw.horror5);
			mPlayer.start();
			 sonido = 5;
			
		   break;
		}
		case 5:
		{
			mPlayer.reset();
			mPlayer = MediaPlayer.create(this,R.raw.horror6);
			mPlayer.start();
			sonido = 0;
			
		   break;
		}
		
		default:
			mPlayer.reset();
			mPlayer = MediaPlayer.create(this,R.raw.horror1);
			mPlayer.start();
			 sonido = 1;
	   
		}	
    }     	    			
}
public void cambiasettings(View v) {
if (canvasView.getNumeroFantasmas() != 0){ 
	if (imagentocada != 9999) {
//		Ha tocado la imagen c
		if (popupWindow != null){
			popupWindow.dismiss();
		}
		
		popupView = layoutInflater.inflate(R.layout.popupcambios, null); 
		popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT,                       
				LayoutParams.WRAP_CONTENT);    	    	 
		popupWindow.showAtLocation(popupView, Gravity.RIGHT, 0, 0); 
				//canvasView.getSettingx(imagentocada), 
				//canvasView.getSettingy(imagentocada));	
		
	} else { 
		imagentocada = 0;
		if (popupWindow != null){
			popupWindow.dismiss();
		}
		popupView = layoutInflater.inflate(R.layout.popupcambios, null); 
		popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT,                       
				LayoutParams.WRAP_CONTENT);    	    	 
		popupWindow.showAtLocation(popupView, Gravity.RIGHT, 0, 0);				
	}
	} else {
		toast1 = Toast.makeText(getApplicationContext(), "add a ghost, press menu", Toast.LENGTH_SHORT);
		toast1.show();
	}
}
 	public void mostrarEfectos(View v) {		
		
		 if (popupWindow != null){
			popupWindow.dismiss();
			}
	   
	    popupView = layoutInflater.inflate(R.layout.popupefectos, null); 
		popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT,                       
				  LayoutParams.WRAP_CONTENT);  
   	    	 
	    popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 10);
	}
	 public void guardar(View v) {
		 Uri uri = canvasView.saveScreenshot();
		 /*
		 MediaScannerConnection.scanFile(this, new String[]{file.getPath()}, null,
                 new
                 MediaScannerConnection.OnScanCompletedListener() {

			 public void onScanCompleted(final String path, final Uri uri) {
			 }
		 });
	*/
	 }
	 public void compartir(View v) {
		 Uri uri = canvasView.saveScreenshot();

		 /*
		 Intent sharingIntent = new Intent(Intent.ACTION_SEND);
		Uri screenshotUri = FileProvider.getUriForFile(this, getPackageName() + ".provider", file);

		if (canvasView != null) {
			canvasView.surfaceDestroyed(null);
		}
	    sharingIntent.setType("image/*");
	    sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
	    startActivity(Intent.createChooser(sharingIntent, "Share image using:"));
	    finish();
		  */
		 if (uri != null) {
			 Intent shareIntent = new Intent(Intent.ACTION_SEND);
			 shareIntent.setType("image/*"); // O el tipo MIME apropiado
			 String compartoStr = getString(R.string.compartoapp) +
					 " https://play.google.com/store/apps/details?id=foto.fantasma.broma";
			 shareIntent.putExtra(Intent.EXTRA_TEXT,compartoStr);
			 shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
			 shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // Concede permisos de lectura a la app receptora

			 // Verifica si hay aplicaciones que puedan manejar este Intent
			 if (shareIntent.resolveActivity(getPackageManager()) != null) {
				 if (canvasView != null) {
					 canvasView.surfaceDestroyed(null);
				 }
				 startActivity(Intent.createChooser(shareIntent, "Compartir imagen usando..."));
				 finish();
			 } else {
				 Toast.makeText(this, "No hay aplicaciones disponibles para compartir.", Toast.LENGTH_SHORT).show();
			 }
		 } else {
			 Toast.makeText(this, "No se pudo obtener la imagen para compartir.", Toast.LENGTH_SHORT).show();
		 }
	 }

	 public class MyTouchListener implements OnTouchListener {
		public boolean onTouch(View v, MotionEvent event) {
		if (canvasView.getNumeroFantasmas() != 0){
			mScaleDetector.onTouchEvent(event);
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				int a = (int) event.getX();
				int b = (int) event.getY();
				xx = a;
				yy = b;
				imagentocada = canvasView.comprobarImagenTocada(a, b);
				return true;
			}
			if (event.getAction() == MotionEvent.ACTION_MOVE) {	
				if (popupWindow != null){
					popupWindow.dismiss();
				}
				
				int a = (int) event.getX();
				int b = (int) event.getY();
				
				int dx = a - xx;
				int dy = b - yy;
				int aa = xx;
				int bb = yy;
				xx = a;
				yy = b;
				
				if (!mScaleDetector.isInProgress()) {
					if (borrar==0){
						imagentocada = canvasView.moverImagen(a, b, dx, dy);
					}else{
						imagentocada = canvasView.borrarAlfa(a, b, aa, bb);
					}				
				}
				return true;
			}
			if (event.getAction() == MotionEvent.ACTION_UP) {
				int a = (int) event.getX();
				int b = (int) event.getY();
				xx = a;
				yy = b;
				imagentocada = canvasView.comprobarImagenTocada(a, b);
				return true;
			}
		}
			return false;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	MenuInflater inflater = getMenuInflater();
	inflater.inflate(R.menu.menuopciones, menu);
	
	return super.onCreateOptionsMenu(menu);
	}
/*	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	  switch (item.getItemId()) {
	  case R.id.guardar:
		 String path = canvasView.saveScreenshot();
		 MediaScannerConnection.scanFile(this, new String[]{path}, null,
                 new
                 MediaScannerConnection.OnScanCompletedListener() {

			 public void onScanCompleted(final String path, final Uri uri) {
			 }
		 });
     	 //sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, 
			//		 Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
     	 break;
	   case R.id.mostrarefectos:
	   if (popupWindow != null){
			popupWindow.dismiss();
		}
	   
	    popupView = layoutInflater.inflate(R.layout.popupefectos, null); 
		popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT,                       
				  LayoutParams.WRAP_CONTENT);  
   	    	 
	    popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 10);
		break;
	  case R.id.sharei:
			 String path2 = canvasView.saveScreenshot();
			 MediaScannerConnection.scanFile(this, new String[]{path2}, null,
	                 new
	                 MediaScannerConnection.OnScanCompletedListener() {

				 public void onScanCompleted(final String path, final Uri uri) {
				 }
			 });
	    	 //sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, 
			 //			 Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
	    	 
	    	 Intent sharingIntent = new Intent(Intent.ACTION_SEND);

	     	Uri screenshotUri = Uri.parse("file://"+ path2);
	     	canvasView.surfaceDestroyed(null);
	     	sharingIntent.setType("image/*");
	     	sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
	     	startActivity(Intent.createChooser(sharingIntent, "Share image using:"));
	     	finish();
	     	break;


	  case R.id.exit:
	   // Acciones
		  canvasView.surfaceDestroyed(null);
		  finish();
		  //Intent myIntent = new Intent(SquaresActivityh.this, Puntualo.class);
		  //startActivityForResult(myIntent, REQUEST_EXIT);
		  
		  break;	 
	  }
	 
	  return true;
	 }
*/	public void volver(View v) {
        if (canvasView != null) {
            canvasView.surfaceDestroyed(null);
        }
		finish();
		//Intent myIntent = new Intent(SquaresActivityh.this, Puntualo.class);
		//startActivityForResult(myIntent, REQUEST_EXIT);		
		//canvasView.cambiaTamanio();
		}
	
	public boolean onKeyDown(int keyCode, KeyEvent event)
{
    if ((keyCode == KeyEvent.KEYCODE_BACK))
    {
    	//if (popupWindow != null){
		//	popupWindow.dismiss();
		 //} else {
            if (canvasView != null) {
                canvasView.surfaceDestroyed(null);
            }
			finish();
		    //Intent myIntent = new Intent(SquaresActivityh.this, Puntualo.class);
			//startActivityForResult(myIntent, REQUEST_EXIT);
			
		 //}
    }
   
    return super.onKeyDown(keyCode, event);
}
	

public void cerrarPopup(View v) {		
		
	popupWindow.dismiss();
}
public void uploadMenu(View v) {
    openOptionsMenu(); 
}	
public void silenciar() {
	if (mPlayer.isPlaying()){
		mPlayer.reset();
	}
}

private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {          
@Override          
	public boolean onScale(ScaleGestureDetector mScaleDetector) {              
	mScaleFactor *= mScaleDetector.getScaleFactor();                            
// Don't let the object get too small or too large.              
	float limiteMaximo = tamanioMaximo/tamanioInicialFijo;
	float limiteMinimo = tamanioMinimo/tamanioInicialFijo;
	mScaleFactor = Math.max(limiteMinimo, Math.min(mScaleFactor, limiteMaximo));         

	if (mScaleFactor != 1.f) {
		if (mScaleFactor > 1.f) {
			float tamanoInicialf = tamanoInicial * mScaleFactor;
			if (tamanoInicialf > 150){
				tamanoInicialf = 150;
			}
			int tamanoInicialint = (int)tamanoInicialf;	
			if (canvasView.getNumeroFantasmas() != 0){
				canvasView.cambiarTamanio(imagentocada, tamanoInicialint, visibilidad);	
			}
		} else {
			float tamanoInicialf = tamanoInicial * mScaleFactor;
			if (tamanoInicialf < 5){
				tamanoInicialf = 5;
			}
			int tamanoInicialint = (int)tamanoInicialf;
			if (canvasView.getNumeroFantasmas() != 0){
				canvasView.cambiarTamanio(imagentocada, tamanoInicialint, visibilidad);
			}
		}
	}
//?	invalidate();              
	return true;          
	}      
}  	

private int getImageOrientation(){
    final String[] imageColumns = { MediaStore.Images.Media._ID, MediaStore.Images.ImageColumns.ORIENTATION };
    final String imageOrderBy = MediaStore.Images.Media._ID+" DESC";
    Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            imageColumns, null, null, imageOrderBy);

    if(cursor.moveToFirst()){
        int orientation = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.ImageColumns.ORIENTATION));
        cursor.close();
        return orientation;
    } else {
        return 0;
    }
}
	public void onActivityResult(int requestCode, int resultCode, Intent intent){ 
	
		if (requestCode == REQUEST_EXIT) {        
			
			finish();
		}
	}
private static float exifOrientationToDegrees(int exifOrientation) {

    if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {

        return 90;

    } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {

        return 180;

    } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {

        return 270;

    }

    return 0;

}
public String getRealPathFromURI(Uri contentUri) {
    String[] proj = { MediaStore.Images.Media.DATA };
    Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
    cursor.moveToFirst();
    return cursor.getString(column_index);
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
//	}
//	public void onActivityResult(int requestCode, int resultCode, Intent intent){ 
//	  finish();
//	  break;
//		if (requestCode == 101) {        
//			if (resultCode == RESULT_OK) {            
//				        
//				// Handle successful scan        
//				} else if (resultCode == RESULT_CANCELED) {            
//					// Handle cancel        
//				}    
//			}
				
//	}
}
