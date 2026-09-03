package foto.fantasma.broma;
import android.graphics.Bitmap;
import android.graphics.Color;

public class ConvolutionMatrix
{
    public static final int SIZE = 3;

    public double[][] Matrix;
    public double Factor = 1;
    public double Offset = 1;

    public ConvolutionMatrix(int size) {
        Matrix = new double[size][size];
    }

    public void setAll(double value) {
        for (int x = 0; x < SIZE; ++x) {
            for (int y = 0; y < SIZE; ++y) {
                Matrix[x][y] = value;
            }
        }
    }

    public void applyConfig(double[][] config) {
    	for(int x = 0; x < SIZE; ++x) {
    		for(int y = 0; y < SIZE; ++y) {
    			Matrix[x][y] = config[x][y];
    		}
    	}
    }

    public static Bitmap computeConvolution3x3(Bitmap src, ConvolutionMatrix matrix) {
    	int width = src.getWidth();
    	int height = src.getHeight();
    	Bitmap result = Bitmap.createBitmap(width, height, src.getConfig());

    	int A, R, G, B;
    	int sumR, sumG, sumB;
    	int[][] pixels = new int[SIZE][SIZE];

    	for(int y = 0; y < height - 2; ++y) {
    		for(int x = 0; x < width - 2; ++x) {

    			// get pixel matrix
    			for(int i = 0; i < SIZE; ++i) {
    				for(int j = 0; j < SIZE; ++j) {
    					pixels[i][j] = src.getPixel(x + i, y + j);
    				}
    			}

    			// get alpha of center pixel
    			A = Color.alpha(pixels[1][1]);

    			// init color sum
    			sumR = sumG = sumB = 0;

    			// get sum of RGB on matrix
    			for(int i = 0; i < SIZE; ++i) {
    				for(int j = 0; j < SIZE; ++j) {
    					sumR += (Color.red(pixels[i][j]) * matrix.Matrix[i][j]);
    					sumG += (Color.green(pixels[i][j]) * matrix.Matrix[i][j]);
    					sumB += (Color.blue(pixels[i][j]) * matrix.Matrix[i][j]);
    				}
    			}

    			// get final Red
    			R = (int)(sumR / matrix.Factor + matrix.Offset);
    			if(R < 0) { R = 0; }
    			else if(R > 255) { R = 255; }

    			// get final Green
    			G = (int)(sumG / matrix.Factor + matrix.Offset);
    			if(G < 0) { G = 0; }
    			else if(G > 255) { G = 255; }

    			// get final Blue
    			B = (int)(sumB / matrix.Factor + matrix.Offset);
    			if(B < 0) { B = 0; }
    			else if(B > 255) { B = 255; }

    			// apply new pixel
    			result.setPixel(x + 1, y + 1, Color.argb(A, R, G, B));
    		}
    	}

    	// final image
    	return result;
    }
    
    public static Bitmap convertirGris(Bitmap src) {
    	int width = src.getWidth();
    	int height = src.getHeight();
    	Bitmap result = Bitmap.createBitmap(width, height, src.getConfig());

    	int A, R, G, B;
    	int sumR, sumG, sumB;

    	for(int y = 0; y < height; ++y) {
    		for(int x = 0; x < width; ++x) {
				int pixel = src.getPixel(x, y);
				sumR = (Color.red(pixel));
				sumG = (Color.green(pixel));
    			sumB = (Color.blue(pixel));
				A =  Color.alpha(pixel); 
	
    			// get final Red 0.299*r + 0.587*g + 0.114*b)
    			R = (int)(sumR * 0.299);
    			if(R < 0) { R = 0; }
    			else if(R > 255) { R = 255; }

    			// get final Green
    			G = (int)(sumG * 0.587);
    			if(G < 0) { G = 0; }
    			else if(G > 255) { G = 255; }

    			// get final Blue
    			B = (int)(sumB * 0.114);
    			if(B < 0) { B = 0; }
    			else if(B > 255) { B = 255; }
    			int suma = R + G + B;
    			if  (suma > 255){
    				suma = 255;
    			}
    			// apply new pixel
    			result.setPixel(x, y, Color.argb(A, suma, suma, suma));
    		}
    	}

    	// final image
    	return result;
    	
    }
	    public static Bitmap cambiarColor(Bitmap src, int gradcolor) {
    	int width = src.getWidth();
    	int height = src.getHeight();
    	Bitmap result = Bitmap.createBitmap(width, height, src.getConfig());

    	int A, R, G, B;
    	int sumR, sumG, sumB;

    	for(int y = 0; y < height; ++y) {
    		for(int x = 0; x < width; ++x) {
				int pixel = src.getPixel(x, y);
				sumR = (Color.red(pixel));
				sumG = (Color.green(pixel));
    			sumB = (Color.blue(pixel));
				A =  Color.alpha(pixel); 
	
    			// get final Red 0.299*r + 0.587*g + 0.114*b)
    			R = (int)(sumR + gradcolor);
    			if(R < 0) { R = 0;}
    			else if(R > 255) { R = 255;}

    			// get final Green
    			G = (int)(sumG + gradcolor);
    			if(G < 0) { G = 0; }
    			else if(G > 255) { G = 255;}

    			// get final Blue
    			B = (int)(sumB + gradcolor);
    			if(B < 0) { B = 0;}
    			else if(B > 255) { B = 255;}
    			
    			// apply new pixel
    			result.setPixel(x, y, Color.argb(A, R, G, B));
    		}
    	}

    	// final image
    	return result;
    	
    }
}
