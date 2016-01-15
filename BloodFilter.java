import java.awt.*;
import java.awt.image.*;

public class BloodFilter extends RGBImageFilter
{

    int opacity;


    public BloodFilter()
    {
        canFilterIndexColorModel = true;

    }

    public int filterRGB(int x, int y, int rgb) {
        int alpha_channel = (rgb >> 24) & 0xFF;
        int green = (rgb & 0x0000FF00) >> 8;
        int red = (rgb & 0x00FF0000) >> 16;
        if ((green > 60) && (red < 100))
		opacity = 0;
	else
		opacity = 153;

	alpha_channel = alpha_channel * opacity / 255;
	return ((rgb & 0x00FFFFFF) | (alpha_channel << 24));
    }

}

		
