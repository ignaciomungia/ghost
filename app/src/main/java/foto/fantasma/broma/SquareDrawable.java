package foto.fantasma.broma;





import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Matrix;
import android.graphics.Paint;

import android.graphics.Path;


import android.graphics.drawable.Drawable;
import android.widget.Toast;


/*
 * Para cambiar los pinceles ir a draw
 */
public class SquareDrawable extends Drawable implements Renderable {

	private int color;

	private int brillo;
	private int enfoque;
	
	private int rotado;
	private int w;
	private int h;
	// porcentaje de tamao:
	private int auxaa;
	private int auxbb;
	private int dd;
	private int ee;
	private int ww = 0;
	private int hh = 0;
	private int anchoinicial = 0;
	private int altoinicial = 0;


	private int defineEfectoa;
	private int defineCentradoa;
	private Bitmap bmp;
	private Context context1;
	private Path path = new Path();
	private MaskFilter mEmboss;
	private MaskFilter mBlur;
	Paint p7 = new Paint();
	private Canvas canvasRender;
	private int flag;
	private int visib;

	public SquareDrawable(int a, int b, int d, int e, int imagenCentrado, int defineEfecto, 
			Context context, int auxa, int auxb) {
		this.context1 = context;
	
		this.w = a;
		this.h = b;
		this.dd = d;
		this.ee = e;
		this.defineEfectoa = defineEfecto;
		this.defineCentradoa = imagenCentrado;
		
		this.auxaa = auxa;
		this.auxbb = auxb;
	}

	@Override
	public void draw(Canvas canvas) {
		if (auxbb==1000){			
			canvas.drawBitmap(bmp, ww, hh, p7);
			
		} else if (auxbb==3000){			
				Matrix mat = new Matrix();
				mat.reset();
	        	mat.setRotate(rotado, ww+bmp.getWidth()/2, hh+bmp.getHeight()/2);
	        	canvas.setMatrix(mat);	       	
	        	canvas.drawBitmap(bmp, ww, hh, p7);	
	        	
	        	canvas.setMatrix(null);
	        	auxbb=1000;		
		} else if (auxbb==4000){
			if (w  > 0){
				anchoinicial = w;                             
			} else {
				anchoinicial = 100;
			}
			if (h  > 0){
				altoinicial = h;
			} else {
				altoinicial = 100;
			}
			int altoTocado = bmp.getHeight();
			int anchoTocado = bmp.getWidth();
			bmp = recuperaRecurso(defineEfectoa, anchoinicial, altoinicial, context1.getResources());
			p7.setColor(00000000);
			p7.setAlpha(visib);
			
			bmp = Bitmap.createScaledBitmap(bmp, anchoTocado, altoTocado, true);
     		bmp = ConvolutionMatrix.cambiarColor(bmp, brillo);  
     	
			canvas.drawBitmap(bmp, ww, hh, p7);
     		auxbb=1000;
		} else if (auxbb==5000){
			//				var matrices = [
//  {
//    name: 'mean removal (sharpen)',
//    data:
//     [[-1, -1, -1],
//      [-1,  9, -1],
//      [-1, -1, -1]]
//  },
//  {
//    name: 'sharpen',
//    data:
//     [[ 0, -2,  0],
//      [-2, 11, -2],
//      [ 0, -2,  0]]
//  },
//  {
//    name: 'blur',
//    data:
 //    [[ 1,  2,  1],
//      [ 2,  4,  2],
//      [ 1,  2,  1]]
//  },
//  {
//    name: 'emboss',
//    data:
//     [[ 2,  0,  0],
//      [ 0, -1,  0],
//      [ 0,  0, -1]],
//    offset: 127,
//  },
//  {
//    name: 'emboss subtle',
//    data:
//     [[ 1,  1, -1],
//      [ 1,  3, -1],
//      [ 1, -1, -1]],
//  },
//  {
//    name: 'edge detect',
//    data:
//     [[ 1,  1,  1],
//      [ 1, -7,  1],
//      [ 1,  1,  1]],
//  },
//  {
//    name: 'edge detect 2',
//    data:
//     [[-5,  0,  0],
//      [ 0,  0,  0],
//      [ 0,  0,  5]],
//  }
//];
//				 double[][] EmbossConfig = new double[][] {  
//					{ -1 ,  0, -1 },  
//					{  0 ,  4,  0 },  
//					{ -1 ,  0, -1 }  
//					};  
//gaussianBlur[3][3] = {0.045, 0.122, 0.045, 0.122, 
 // 0.332, 0.122, 0.045, 0.122, 0.045};
//gaussianBlur2[3][3] = {1, 2, 1, 2, 4, 2, 1, 2, 1};
//gaussianBlur3[3][3] = {0, 1, 0, 1, 1, 1, 0, 1, 0};
//unsharpen[3][3] = {-1, -1, -1, -1, 9, -1, -1, -1, -1};
//sharpness[3][3] = {0,-1,0,-1,5,-1,0,-1,0};
//sharpen[3][3] = {-1, -1, -1, -1, 16, -1, -1, -1, -1};
//edgeDetect[3][3] = {-0.125, -0.125, -0.125, -0.125, 
//  1, -0.125, -0.125, -0.125, -0.125};
//edgeDetect2[3][3] = {-1, -1, -1, -1, 8, -1, -1, -1, -1};
//edgeDetect3[3][3] = {-5, 0, 0, 0, 0, 0, 0, 0, 5};
//edgeDetect4[3][3] = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
//edgeDetect5[3][3] = {-1, -1, -1, 2, 2, 2, -1, -1, -1};
//edgeDetect6[3][3] = {-5, -5, -5, -5, 39, -5, -5, -5, -5};
//sobelHorizontal[3][3] = {1, 2, 1, 0, 0, 0, -1, -2, -1 };
//sobelVertical[3][3] = {1, 0, -1, 2, 0, -2, 1, 0, -1 };
//previtHorizontal[3][3] = {1, 1, 1, 0, 0, 0, -1, -1, -1 };
//previtVertical[3][3] = {1, 0, -1, 1, 0, -1, 1, 0, -1};
//boxBlur[3][3] = {0.111f, 0.111f, 0.111f, 0.111f, 
//  0.111f, 0.111f, 0.111f, 0.111f, 0.111f};
//triangleBlur[3][3] = { 0.0625, 0.125, 0.0625, 
//  0.125, 0.25, 0.125, 0.0625, 0.125, 0.0625};
//								 double[][] EmbossConfig = new double[][] {  
//					{  1 ,  2,  1 },  
//					{  2 ,  4,  2 },  
//					{  1 ,  2,  1 }  
//					};  

								 double[][] boxBlur = new double[][] {  
					{  0.111f ,  0.111f,  0.111f },  
					{  0.111f ,  0.111f,  0.111f },  
					{  0.111f ,  0.111f,  0.111f }  
					};  

				ConvolutionMatrix convMatrix = new ConvolutionMatrix(3);  
				convMatrix.applyConfig(boxBlur);
				convMatrix.Factor = 16;  
				convMatrix.Offset = 0;				
				//convMatrix.Factor = factorc;  
				//convMatrix.Offset = offsetc;
				//Toast toast1 = Toast.makeText(context1, "factor" +factorc, Toast.LENGTH_SHORT);
				//toast1.show();
				//toast1 = Toast.makeText(context1, "offset"+offsetc, Toast.LENGTH_SHORT);
				//toast1.show();
				//convMatrix.Factor = 1; 
				// enboss: convMatrix.Offset = 127;  
     			bmp = ConvolutionMatrix.computeConvolution3x3(bmp, convMatrix);  
     			canvas.drawBitmap(bmp, ww, hh, p7);
     			auxbb=1000;
		} else if (auxbb==6000){
     			bmp = ConvolutionMatrix.convertirGris(bmp);  
     			canvas.drawBitmap(bmp, ww, hh, p7);
     			auxbb=1000;
		} else if (auxbb==7000){
     			if (w  > 0){
					anchoinicial = w;                             
				} else {
					anchoinicial = 100;
				}
				if (h  > 0){
					altoinicial = h;
				} else {
					altoinicial = 100;
				}
				int altoTocado = bmp.getHeight();
				int anchoTocado = bmp.getWidth();
				bmp = recuperaRecurso(defineEfectoa, anchoinicial, altoinicial, context1.getResources());
				p7.setColor(00000000);
				p7.setAlpha(visib);	
				bmp = Bitmap.createScaledBitmap(bmp, anchoTocado, altoTocado, true);
				canvas.drawBitmap(bmp, ww, hh, p7);
				auxbb=1000;
		} else {
				if (w  > 0){
					anchoinicial = w;                             
				} else {
					anchoinicial = 100;
				}
				if (h  > 0){
					altoinicial = h;
				} else {
					altoinicial = 100;
				}			
				bmp = recuperaRecurso(defineEfectoa, anchoinicial, altoinicial, context1.getResources());
			
    	
			p7.setColor(00000000);
			if (auxbb != 2000){
				visib = 100;	
			} 
			p7.setAlpha(visib);
			
			int altoTocado = h*auxaa/100;
    	
			int anchoTocado = bmp.getWidth()*altoTocado/bmp.getHeight();
			bmp = Bitmap.createScaledBitmap(bmp, anchoTocado, altoTocado, true);
			if (auxbb == 9999){
				if (defineCentradoa == 0){		
					ww=w/6 - anchoTocado/2;
				
					hh = h/2 - altoTocado/2;
					if (hh<h/10){
						hh=h/10;
					}			
				}else if (defineCentradoa == 1){
					ww = w/2 - anchoTocado/2;
					hh = h/2 - altoTocado/2;
					if (hh<h/10){
						hh=h/10;
					}		
				
				} else if(defineCentradoa ==2){
					ww = w - w/6 - anchoTocado/2;
				
					hh = h/2 - altoTocado/2;
					if (hh<h/10){
						hh=h/10;
					}			
				}
				canvas.drawBitmap(bmp, ww, hh, p7);
				
				auxbb=1000;
			} else {
				if (auxbb != 2000){
					ww= dd-bmp.getWidth()/2;
					hh= ee-bmp.getHeight()/2;
				}
				canvas.drawBitmap(bmp, ww, hh, p7);
				
				auxbb=1000;
			}
		}
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
	public static Bitmap recuperaRecurso(int definefectos, int ancho, int alto, Resources res) { 
			final Bitmap bmpSalida;			
			switch(definefectos){
				case 1000:
			{
				
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1000, 
						ancho, alto);
				break;
			}
				case 1010:
      	  
			{
				//bmp = BitmapFactory.decodeResource(res, R.drawable.f1010);
				//bmp = decodeSampledBitmapFromResource(res, R.drawable.f1010, ww, hh); 
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1010, 
						ancho, alto);  
				
				break;
			}
				case 1020:
      	  
			{
				//bmpSalida = BitmapFactory.decodeResource(res, R.drawable.f1020);
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1020, 
						ancho, alto);
    	  
				break;
			}
				case 1030:
      	  
			{
				//bmpSalida = BitmapFactory.decodeResource(res, R.drawable.f1030);
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1030, 
						ancho, alto);
    	  
				break;
			}
				case 1040:
      	  
			{
				//bmpSalida = BitmapFactory.decodeResource(res, R.drawable.f1040);
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1040, 
						ancho, alto);
    	  
				break;
			}
				case 1050:
      	  
			{
				//bmpSalida = BitmapFactory.decodeResource(res, R.drawable.f1050);
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1050, 
						ancho, alto);
    	  
				break;
			}
				case 1060:
      	  
			{
				//bmpSalida = BitmapFactory.decodeResource(res, R.drawable.f1060);
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1060, 
						ancho, alto);
    	  
				break;
			}
				case 1070:
      	  
			{
				//bmpSalida = BitmapFactory.decodeResource(res, R.drawable.f1070);
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1070, 
						ancho, alto);
    	  
				break;
			}
				case 1080:
      	  
			{
				//bmpSalida = BitmapFactory.decodeResource(res, R.drawable.f1080);
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1080, 
						ancho, alto);
    	   
    	  
				break;
			}
				case 1090:
      	  
			{
				//bmpSalida = BitmapFactory.decodeResource(res, R.drawable.f1090);
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1090, 
						ancho, alto);
    	   
    	  
				break;
			}
				case 1100:
      	  
			{
				//bmpSalida = BitmapFactory.decodeResource(res, R.drawable.f1100);
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1100, 
						ancho, alto);
    	   
    	  
				break;
			}
				case 1110:
      	  
			{
				//bmpSalida = BitmapFactory.decodeResource(res, R.drawable.f1110);
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1110, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1120:
      	  
			{
				//bmpSalida = BitmapFactory.decodeResource(res, R.drawable.f1120);
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1120, 
						ancho, alto);

    	  
				break;
			}
			case 1130:
      	  
			{
				//bmpSalida = BitmapFactory.decodeResource(res, R.drawable.f1130);
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1130, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1140:
      	  
			{
				//bmpSalida = BitmapFactory.decodeResource(res, R.drawable.f1140);
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1140, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1150:
      	  
			{
				//bmpSalida = BitmapFactory.decodeResource(res, R.drawable.f1150);
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1150, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1160:
		      	  
			{
				//bmpSalida = BitmapFactory.decodeResource(res, R.drawable.f1160);
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1160, 
						ancho, alto);
    	  
				break;
			}
			case 1170:
		      	  
			{
				//bmpSalida = BitmapFactory.decodeResource(res, R.drawable.f1170);
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1170, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1180:
		      	  
			{
				//bmpSalida = BitmapFactory.decodeResource(res, R.drawable.f1180);
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1180, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1190:
		      	  
			{
				//bmpSalida = BitmapFactory.decodeResource(res, R.drawable.f1190);
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1190, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1200:
		      	  
			{
				//bmpSalida = BitmapFactory.decodeResource(res, R.drawable.f1200);
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1200, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1210:
		      	  
			{
				//bmpSalida = BitmapFactory.decodeResource(res, R.drawable.f1210);
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1210, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1220:
		      	  
			{
				//bmpSalida = BitmapFactory.decodeResource(res, R.drawable.f1220);
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1220, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1230:
		      	  
			{
				//bmpSalida = BitmapFactory.decodeResource(res, R.drawable.f1230);
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1230, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1240:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1240, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1250:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1250, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1260:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1260, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1270:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1270, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1280:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1280, 
						ancho, alto);
    	   
    	  
				break;
			}
		
			case 1290:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1290, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1300:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1300, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1310:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1310, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1320:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1320, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1330:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1330, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1340:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1340, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1350:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1350, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1360:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1360, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1370:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1370, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1380:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1380, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1390:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1390, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1410:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1410, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1420:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1420, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1430:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1430, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1440:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1440, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1450:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1450, 
						ancho, alto);
    	   
    	  
				break;
			}

			case 1460:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1460, 
						ancho, alto);
    	   
    	  
				break;
			}

			case 1470:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1470, 
						ancho, alto);
    	   
    	  
				break;
			}

			case 1480:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1480, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1490:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1490, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1500:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1500, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1510:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1510, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1520:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1520, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1600:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1600, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1610:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1610, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1620:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1620, 
						ancho, alto);
    	   
    	  
				break;
			}
			case 1630:
		      	  
			{
				bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1630, 
						ancho, alto);
    	   
    	  
				break;
			}
				case 1640:

				{
					bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1640,
							ancho, alto);


					break;
				}
				case 1650:

				{
					bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1650,
							ancho, alto);


					break;
				}



				default:
        	{
        		//bmpSalida = BitmapFactory.decodeResource(res, R.drawable.f1000);
        		bmpSalida = decodeSampledBitmapFromResource(res, R.drawable.f1000, 
						ancho, alto);
    	   
        	}
			}
		return bmpSalida; 
	}

	@Override
	public int getOpacity() {
		return 0;
	}

	@Override
	public void setAlpha(int alpha) {

	}

	@Override
	public void setColorFilter(ColorFilter cf) {

	}

	public void render(Canvas c) {
		draw( c );
		
	}
	public Bitmap getCanvas() {
		return bmp;		
	}
	public Paint getPaint() {
		return p7;		
	}
		
	public int getEfecto() {
		return defineEfectoa;		
	}
	public int getAuxaa() {
		return auxaa;		
	}
	public int getAncho() {
		return ww;		
	}
	public int getAlto() {
		return hh;		
	}
	public int getPosPopupx() {
		return (ww+bmp.getWidth());		
	}
	public int getPosPopupy() {
		return (hh+bmp.getHeight());		
	}
	
	public void setAnchoAlto(int ancho, int alto) {
		auxbb = 1000;
		ww = ancho;	
		hh = alto;
		//draw( canvasRender );	
	}
	public void setPintado(Paint p) {
		p7 = p;
		auxbb = 1000;
		//draw( canvasRender );	
	}
	public void setTamanio(int t, int visibilidad) {
		auxaa = t;
		visib = visibilidad;
		// para que cree el bmp
		auxbb = 2000;
		//draw( canvasRender );	
	}
	public void setRotado(int r) {
		rotado = r;
		auxbb = 3000;
	}
	public void setBrillo(int b, int visibilidad) {
		visib = visibilidad;
		brillo = b;
		auxbb = 4000;
	}
	public void setEnfoque(int e) {
		enfoque = e;
		//auxbb = 5000;
		//TXAPU PARA PROBAR GRISES
		auxbb = 6000;
	}
	public void deshacer(int visibilidad) {
		auxbb = 7000;
	}


}
