import java.awt.*;
import java.awt.image.*;

//almost reusable, just need to fix the 
public class CenterFade extends RGBImageFilter
{

    int opacity;
    boolean transFilt;



    public CenterFade(boolean doTrans)
    {
        canFilterIndexColorModel = true;
	transFilt = doTrans;
    }

    public int filterRGB(int x, int y, int rgb)
    {
        int alpha_channel = (rgb >> 24) & 0xFF;
        
	//calculate the distance from center.
	int Xb = Math.abs(x - 15) * Math.abs(x - 15);
	int Yb = Math.abs(y - 15) * Math.abs(y - 15);
	int divNum = (15 * 15) + (15 * 15);
	//int num = (int)(Math.round((Math.sqrt(Xb + Yb) / Math.sqrt(divNum)) * 255));
	int num = (int)(Math.round(((Xb + Yb) / divNum) * 255));
	//int num = (int)Math.round((Xb + Yb) / 2);

	if (num > 255)
	    num = 255;

	opacity = 255 - num;
	
	if (transFilt) {
	    int blue = rgb & 0x000000FF;
            int green = (rgb & 0x0000FF00) >> 8;
            int red = (rgb & 0x00FF0000) >> 16;
	    if ((green > 230) && (red < 30) && (blue < 30))
		opacity = 0;
        }

        alpha_channel = alpha_channel * opacity / 255;
        return ((rgb & 0x00FFFFFF) | (alpha_channel << 24));
    }
}
