package xu.zhixuan.wulne.Util.Fonts;

import java.awt.*;

public class ChromaText {
    public static int drawChroma(int delay){
        double ran = Math.ceil((System.currentTimeMillis() + delay) / 10.0);
        return Color.getHSBColor((float) (ran % 360.0 / 360.0),0.5f,1.0f).getRGB();
    }
}
