import java.awt.*;
import java.awt.image.*;

public class ShadowFilter extends RGBImageFilter
{

    int opacity = 128;


    public ShadowFilter()
    {
        canFilterIndexColorModel = true;

    }

    public int filterRGB(int x, int y, int rgb)
    {
        int alpha_channel = (rgb >> 24) & 0xFF;
        int red = (rgb >> 16) & 255;
        
        //opacity = 0 + (255 - red);
	int Xb = Math.round(Math.abs(x - 200) * Math.abs(x - 200));
	int Yb = Math.round(Math.abs(y - 200) * Math.abs(y - 200));
	int num = (int)(Math.round(Math.sqrt(Xb + Yb)));
	if (num > 255)
	    num = 255;
	//if (num > 400)
	//   num--;
	//opacity = (int)Math.round((num / 400) * 255);
	//if (num > 100)
	//    num += (int)(Math.round(((num - 100) / 10) * 5));
	opacity = num;
	//opacity = (int)Math.round(num * 255);
        alpha_channel = alpha_channel * opacity / 255;
        return ((rgb & 0x00FFFFFF) | (alpha_channel << 24));

    }
}
