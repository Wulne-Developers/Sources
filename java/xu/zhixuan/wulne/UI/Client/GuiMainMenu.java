package xu.zhixuan.wulne.UI.Client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;
import net.optifine.reflect.Reflector;
import xu.zhixuan.wulne.UI.Account.GuiAltLogin;
import xu.zhixuan.wulne.UI.Client.Component.SingButton;
import xu.zhixuan.wulne.Util.Fonts.FontLoaders;
import xu.zhixuan.wulne.Util.Render.RenderUtil;
import xu.zhixuan.wulne.Util.Render.RenderUtils;

import java.awt.*;
import java.io.IOException;

public class GuiMainMenu extends GuiScreen {
    @Override
    public void initGui() {
        int j = this.height / 4 + 48;
        this.buttonList.add(new SingButton(0, this.width / 2 - 100, j + 72 + 0, 200, 20, 5, I18n.format("menu.options", new Object[0])));
        this.buttonList.add(new SingButton(1, this.width / 2 - 100, j, 5, I18n.format("menu.singleplayer", new Object[0])));
        this.buttonList.add(new SingButton(2, this.width / 2 - 100, j + 24, 5, I18n.format("menu.multiplayer", new Object[0])));
        this.buttonList.add(new SingButton(5, this.width / 2 - 100, j + 24 + 25, 98, 20, 5, I18n.format("AltManager", new Object[0])));
        this.buttonList.add(new SingButton(4, this.width / 2 - 100 + 98 + 4, j + 24 + 25, 98, 20, 5, I18n.format("menu.quit", new Object[0])));
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int j = this.height / 4 + 48;
        this.drawDefaultBackground();
        this.mc.getTextureManager().bindTexture(new ResourceLocation("Wulne/MainGui/back3.png"));
        Gui.drawModalRectWithCustomSizedTexture(0, 0, 0.0F, 0.0F, this.width, this.height, (float)this.width, (float)this.height);
        RenderUtil.drawCustomImage(this.width / 2 - 100 + 60, j - 75 - 27, 80, 80, new ResourceLocation("Wulne/logo.png"));
        String name = Minecraft.getMinecraft().session.getUsername();
        RenderUtils.drawRoundRect(1,1, FontLoaders.msFont16.getStringWidth("Player：" + name) + 10,20,new Color(0,0,0,100).getRGB());
        FontLoaders.msFont16.drawString("Player：" + name,1 + 4,1 + 4,-1);
        FontLoaders.msFont16.drawString("WulneClient" + " by Frish2021", 2, this.height - 10, -1);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.id == 0)
        {
            this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        }

        if (button.id == 1)
        {
            this.mc.displayGuiScreen(new GuiSelectWorld(this));
        }

        if (button.id == 2)
        {
            this.mc.displayGuiScreen(new GuiMultiplayer(this));
        }

        if (button.id == 4)
        {
            this.mc.shutdown();
        }

        if (button.id == 5)
        {
            this.mc.displayGuiScreen(new GuiAltLogin(this));
        }

        if (button.id == 6 && Reflector.GuiModList_Constructor.exists())
        {
            this.mc.displayGuiScreen((GuiScreen)Reflector.newInstance(Reflector.GuiModList_Constructor, new Object[] {this}));
        }

        if (button.id == 11)
        {
            this.mc.launchIntegratedServer("Demo_World", "Demo_World", DemoWorldServer.demoWorldSettings);
        }

        if (button.id == 12)
        {
            ISaveFormat isaveformat = this.mc.getSaveLoader();
            WorldInfo worldinfo = isaveformat.getWorldInfo("Demo_World");

            if (worldinfo != null)
            {
                GuiYesNo guiyesno = GuiSelectWorld.makeDeleteWorldYesNo(this, worldinfo.getWorldName(), 12);
                this.mc.displayGuiScreen(guiyesno);
            }
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {}
}
