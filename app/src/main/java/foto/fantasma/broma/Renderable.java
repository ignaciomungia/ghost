package foto.fantasma.broma;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public interface Renderable {

	void render(Canvas c);
	Bitmap getCanvas();
	Paint getPaint();
	int getEfecto();
	int getAuxaa();
	int getAncho();
	int getAlto();
	int getPosPopupx();
	int getPosPopupy();
	void setAnchoAlto(int ancho, int alto);
	void setPintado(Paint p);
	void setTamanio(int t, int v); 
	void setRotado(int r); 
	void setBrillo(int b, int v);
	void setEnfoque(int e);
	void deshacer(int v);
	
}
