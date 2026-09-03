package foto.fantasma.broma;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;

import android.graphics.Paint;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;
/*
 * Para modificar las plantillas ir al final superponerplantilla
 */
public class CanvasView extends SurfaceView implements SurfaceHolder.Callback {

	private Bitmap bmp;

	private int ancho;
	private int alto;
	private int tipoFondo;
	private Context contexto;
	private int conta = 0;
	private int plantilla = 0;


	public class CanvasThread extends Thread {

		private SurfaceHolder mSurfaceHolder;

		private boolean mRun;

		public CanvasThread(SurfaceHolder surfaceHolder) {
			mSurfaceHolder = surfaceHolder;
		}

		public void run() {
			long now = System.currentTimeMillis();
			long lastTime = now;
			conta = 0;
			while (mRun) {
				conta = conta + 1;
				Canvas c = null;
				now = System.currentTimeMillis();
				update(now - lastTime);
							
				lastTime = now;
				try {
					if (conta==1){
						c = mSurfaceHolder.lockCanvas(null);
						if (bmp != null){
							try {
								c.drawBitmap(bmp, 0, 0, null);
							} catch (Exception e) {
								mRun = false;
							}
						}
						if (c != null){
                            doDraw(c);
						}
					}					
					
				} finally {
					conta = 0;
					if (c != null) {
						//if (tipoFondo == 0){
						//c.drawBitmap(bmp, 0, 0, null);
												
						//}
						mSurfaceHolder.unlockCanvasAndPost(c);
					}
				}
			}
		}
		public void setRunning(boolean running) {
			mRun = running;
		}

	}

	private CanvasThread thread;

	private List<Renderable> renderables;
	private List<Updateable> updateables;

	public CanvasView(Context context, Bitmap bmp1, int h, int w, int tipo, int plant) {
		super(context);
		this.bmp = bmp1;
		this.alto = h;
		this.ancho = w;
		this.tipoFondo = tipo;
		this.contexto = context;
		this.plantilla = plant; 

	    bmp = Bitmap.createScaledBitmap(bmp, ancho, alto, true);	


		SurfaceHolder holder = getHolder();
		holder.addCallback(this);
		
//		bmp = BitmapFactory.decodeResource(getResources(),
//		          R.drawable.image1);

		
		renderables = new ArrayList<Renderable>();
		updateables = new ArrayList<Updateable>();
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
	}

	public void surfaceCreated(SurfaceHolder holder) {
		
		//if (thread.isAlive() == true){
		//	thread.setRunning(true);
	
			
		//} else {
			thread = new CanvasThread(holder);

			thread.setRunning(true);
			thread.start();
		//}

	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		if (thread.isAlive()) {
	        thread.setRunning(false);
	    }
		
		boolean retry = true;
		//thread.setRunning(false);
		
		while (retry) {
			try {
				thread.join();
				retry = false;

			} catch (InterruptedException e) {
			}
		}

	}

	public void undoRenderable() {
		if (renderables.size() > 0){
			synchronized (renderables){
				renderables.remove(renderables.size() - 1);
			}
		}
		
	}
	public int moverImagen(int a, int b, int dx, int dy) {
		int ejex = a;
		int ejey = b;
		int imaTocada = 0;
		if (renderables.size() > 0){
			synchronized (renderables){
				//renderables.remove(renderables.size() - 1);
				//List<Renderable> renderables;
				Iterator<Renderable> ren = this.renderables.iterator();
				Paint p7 = new Paint();
//				Canvas canvasMod = new Canvas();
				int i=0;
				int j=0;
				Renderable renble = null;
				int ww = 0;
				int hh = 0;
				int f=0;
				while (ren.hasNext()) {
//				while (ren.hasNext()) {
					renble = ren.next();
					int wx = renble.getAncho();
					int hx = renble.getAlto();

					//if (wx < 0)
					
					//{
					//	wx=0;						
					
					//}
					//if (hx < 0)
					//{
					//	hx=0;						
					//}
					Bitmap bmpx = renble.getCanvas();					
					if ((ejex>=wx && ejex<=(wx+bmpx.getWidth())) &&
							(ejey>=hx && ejey<=(hx+bmpx.getHeight()))) 
					{
						//ww= a-bmpx.getWidth()/2;
						//hh= b-bmpx.getHeight()/2;
						ww = wx + dx;
						hh = hx + dy;
						//p7 = renble.getPaint();
						f=i;
						j=1;
					}
					
				    i++;
				}
 				if (j==1){
 					Renderable renble2 = renderables.get(f);
 					renble2.setAnchoAlto(ww, hh);
					imaTocada = f;
 				}
			}
		}
		return imaTocada;	
	}
	public int borrarAlfa(int a, int b, int dx, int dy) {
		int ejex = a;
		int ejey = b;
		int imaTocada = 0;
		if (renderables.size() > 0){
			synchronized (renderables){
				//renderables.remove(renderables.size() - 1);
				//List<Renderable> renderables;
				Iterator<Renderable> ren = this.renderables.iterator();
				Paint p7 = new Paint();
//				Canvas canvasMod = new Canvas();
				int i=0;
				int j=0;
				Renderable renble = null;
				int ww = 0;
				int hh = 0;
				int f=0;
				while (ren.hasNext()) {
//				while (ren.hasNext()) {
					renble = ren.next();
					int wx = renble.getAncho();
					int hx = renble.getAlto();

					//if (wx < 0)
					
					//{
					//	wx=0;						
					
					//}
					//if (hx < 0)
					//{
					//	hx=0;						
					//}
					Bitmap bmpx = renble.getCanvas();					
					if ((ejex>=wx && ejex<=(wx+bmpx.getWidth())) &&
							(ejey>=hx && ejey<=(hx+bmpx.getHeight()))) 
					{
						//ww= a-bmpx.getWidth()/2;
						//hh= b-bmpx.getHeight()/2;
						ww = wx + dx;
						hh = hx + dy;
						//p7 = renble.getPaint();
						f=i;
						j=1;
					}
					
				    i++;
				}
 				if (j==1){
 					Renderable renble2 = renderables.get(f);
 					renble2.setAnchoAlto(ww, hh);
					imaTocada = f;
 				}
			}
		}
		return imaTocada;	
	}

	public int comprobarImagenTocada(int a, int b) {
		int ejex = a;
		int ejey = b;
		int i=0;
		int j=0;
		int f=0;
		if (renderables.size() > 0){
			synchronized (renderables){
				//renderables.remove(renderables.size() - 1);
				//List<Renderable> renderables;
				Iterator<Renderable> ren = this.renderables.iterator();				
				while (ren.hasNext()) {
				//while (ren.hasNext()) {
					Renderable renble =	ren.next();
					int wx = renble.getAncho();
					int hx = renble.getAlto();
					if (wx < 0)
					{
						wx=0;						
					}
					if (hx < 0)
					{
						hx=0;						
					}
					Bitmap bmpx = renble.getCanvas();	
					if (bmpx != null){
						if ((ejex>=wx && ejex<=(wx+bmpx.getWidth())) &&
							(ejey>=hx && ejey<=(hx+bmpx.getHeight()))) 
						{						
							j=1;
							f=i;
						}
					}
				    i++;			   
				}
			}
		}
		if (j == 0)
		{
			i = 9999;
		}
		return f;
	}
	
	public void cambiarVisibilidad(int indice, int visibilidad) {
		Renderable renble = renderables.get(indice);
		if (renble != null){
			Paint p = new Paint();
			p.setAlpha(visibilidad);
			renble.setPintado(p);	
		}
		
	}
	public void cambiarBrillo(int indice, int brillo, int vis) {
		Renderable renble = renderables.get(indice);
		if (renble != null){
			renble.setBrillo(brillo, vis);	
		}
		
	}
	public void cambiarEnfoque(int indice, int enfoque) {
		Renderable renble = renderables.get(indice);
		if (renble != null){
			renble.setEnfoque(enfoque);	
		}
		
	}
	public void deshacer(int indice, int visibilidad) {
		Renderable renble = renderables.get(indice);
		if (renble != null){
			renble.deshacer(visibilidad);	
		}
		
	}

	public void cambiarTamanio(int indice, int tamanio, int visibilidad) {
		Renderable renble = renderables.get(indice);
		renble.setTamanio(tamanio, visibilidad); 
	}
	
	public void cambiarRotado(int indice, int rotado) {
		Renderable renble = renderables.get(indice);
		renble.setRotado(rotado); 
	}
	public void getSettingx(int indice) {
		Renderable renble = renderables.get(indice);
		renble.getPosPopupx(); 
	}
	public void getSettingy(int indice) {
		Renderable renble = renderables.get(indice);
		renble.getPosPopupy(); 
	}
	public int getNumeroFantasmas() {
		int num = renderables.size();
		return num; 
	}
	public void addRenderable(Renderable r) {
		synchronized (renderables) {
			renderables.add(r);
		}
	}

	public void addUpdateable(Updateable u) {
		synchronized (updateables) {
			updateables.add(u);
		}
	}

	private void doDraw(Canvas c) {
		synchronized (renderables) {
			for (Renderable r : renderables) {
				r.render(c);
			}
		}
	}

	private void update(long elapsed) {
		synchronized (updateables) {
			for (Updateable u : updateables) {
				
				u.update(elapsed);
			}
		}
	}
    @SuppressLint("WrongThread")
	public File saveScreenshotAntiguo() {
    	    Toast toast1 = Toast.makeText(contexto, "saving", Toast.LENGTH_SHORT);
		    toast1.show();
		    Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
		    Canvas canvas = new Canvas(bitmap);
	    	    canvas.drawBitmap(bmp, 0, 0, null);
			    canvas.drawBitmap(bitmap, 0, 0, null);
	    	    doDraw(canvas);
			String state = Environment.getExternalStorageState();
			File dir;
			if (Environment.MEDIA_MOUNTED.equals(state)) {
				if (Build.VERSION.SDK_INT > 28) {
					dir = contexto.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
				} else {
					dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
				}
			} else {
				dir = contexto.getFilesDir();
			}

		    dir.mkdirs();

        	Date d = new Date();
        	long c = d.getTime();
        	String nameF = "";
        	nameF = "photoghost" + c + ".jpg";
        	File file = new File (dir, nameF);
            
            FileOutputStream fos;
            try {
                fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.close();
                toast1 = Toast.makeText(contexto, "name: " + file.getAbsolutePath() + "/" +
                		nameF, Toast.LENGTH_SHORT);
    			toast1.show();    			
                
            } catch (FileNotFoundException e) {
            	 toast1 = Toast.makeText(contexto, "I cant save, sorry", Toast.LENGTH_SHORT);
     			 toast1.show();
     //           Log.e("Panel", "FileNotFoundException", e);
            } catch (IOException e) {
      //          Log.e("Panel", "IOEception", e);
            	 toast1 = Toast.makeText(contexto, "I cant save, sorry", Toast.LENGTH_SHORT);
     			 toast1.show();
            }
            return file;
    }
	public Uri saveScreenshot() {
		Toast toast1 = Toast.makeText(contexto, "saving", Toast.LENGTH_SHORT);
		toast1.show();
		Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		canvas.drawBitmap(bmp, 0, 0, null);
		canvas.drawBitmap(bitmap, 0, 0, null);
		doDraw(canvas);
		Uri uri = ImageSaver.saveBitmapToGallery(CanvasView.this.getContext(), bitmap, "gost");

		return uri;
	}
}

