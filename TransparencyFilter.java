import java.awt.*;
import java.awt.image.*;

public class TransparencyFilter extends RGBImageFilter
{

    int op1 = 0;
    int op2 = 255;

    public TransparencyFilter()
    {
        canFilterIndexColorModel = true;
    }

    public int filterRGB(int x, int y, int rgb)
    {
        int blue = rgb & 0x000000FF;
        int green = (rgb & 0x0000FF00) >> 8;
        int red = (rgb & 0x00FF0000) >> 16;
        
        int alpha_channel = (rgb >> 24) & 0xFF;
        if ((red > 220) && (green < 40) && (blue < 40))
		alpha_channel = alpha_channel * op1 / 255;
	else
		alpha_channel = alpha_channel * op2 / 255;
	/*
	if ((red > (green + 70)) && (red > (blue + 70)))
            alpha_channel = alpha_channel * op1 / 255;
        else
            alpha_channel = alpha_channel * op2 / 255;
	*/    
        return ((rgb & 0x00FFFFFF) | (alpha_channel << 24));

    }
}
