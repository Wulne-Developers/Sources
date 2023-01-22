/*
 * Decompiled with CFR 0_132.
 */
package xu.zhixuan.wulne.Util.Fonts;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import java.awt.*;
import java.io.InputStream;
import java.util.HashMap;

public class FontLoaders {

    public static FontRenderer msFont18 = getSyyh(18, true);
    public static FontRenderer msFont16 = getSyyh(16, true);
    public static FontRenderer msFont14 = getSyyh(14, true);
    public static FontRenderer msFont22 = getSyyh(22, true);
    public static FontRenderer msFont36 = getSyyh(36, true);
    public static FontRenderer msFont72 = getSyyh(72, true);
    public static FontRenderer ClickFont = getSyyh(45, true);

    public static FontRenderer getSyyh(int size, boolean antiAlias) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("Wulne/msyh.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return new FontRenderer(font,size,antiAlias);
    }
}

