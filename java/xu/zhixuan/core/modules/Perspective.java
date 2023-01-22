package xu.zhixuan.core.modules;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import xu.zhixuan.core.events.Client.EventTick;
import xu.zhixuan.wulne.Event.EventTarget;
import xu.zhixuan.wulne.Module.Module;
import xu.zhixuan.wulne.Module.ModuleType;
import xu.zhixuan.wulne.Module.Value.Mode;

public class Perspective extends Module {
    public static boolean perspectiveToggled;
    private static float cameraYaw;
    private static float cameraPitch;
    private static int previousPerspective;
    public static Minecraft mc = Minecraft.getMinecraft();

    static {
        perspectiveToggled = false;
        cameraYaw = 0.0f;
        cameraPitch = 0.0f;
        previousPerspective = 0;
    }

    enum Mode {
        Click, Hold;
    }

    public Perspective() {
        super("Perspective", 0, 0, ModuleType.Other,false);
        setKey(Keyboard.KEY_F);
    }

    @EventTarget
    public void onTick(EventTick e) {
        if (mc.thePlayer != null && mc.theWorld != null) {
            if (!Keyboard.isKeyDown(super.getKey())) {
                super.setEnabled(false);
            }
        }

    }

    @Override
    public void onEnabled() {

        perspectiveToggled = true;
        cameraYaw = mc.thePlayer.rotationYaw;
        cameraPitch = mc.thePlayer.rotationPitch;
        previousPerspective = mc.gameSettings.thirdPersonView;
        mc.gameSettings.thirdPersonView = 1;

    }

    @Override
    public void onDisable() {
        perspectiveToggled = false;
        mc.gameSettings.thirdPersonView = previousPerspective;
    }

    public static float getCameraYaw() {
        return perspectiveToggled ? cameraYaw : mc.getRenderViewEntity().rotationYaw;
    }

    public static float getCameraPitch() {
        return perspectiveToggled ? cameraPitch : mc.getRenderViewEntity().rotationPitch;
    }

    public static float getCameraPrevYaw() {
        return perspectiveToggled ? cameraYaw : mc.getRenderViewEntity().prevRotationYaw;
    }

    public static float getCameraPrevPitch() {
        return perspectiveToggled ? cameraPitch : mc.getRenderViewEntity().prevRotationPitch;
    }

    public static boolean overrideMouse() {
        if (mc.inGameHasFocus && Display.isActive()) {
            if (!perspectiveToggled) {
                return true;
            }
            mc.mouseHelper.mouseXYChange();
            float f1 = mc.gameSettings.mouseSensitivity * 0.6f + 0.2f;
            float f2 = f1 * f1 * f1 * 8.0f;
            float f3 = mc.mouseHelper.deltaX * f2;
            float f4 = mc.mouseHelper.deltaY * f2;
            cameraYaw += f3 * 0.15f;
            cameraPitch += f4 * 0.15f;
            if (cameraPitch > 90.0f) {
                cameraPitch = 90.0f;
            }
            if (cameraPitch < -90.0f) {
                cameraPitch = -90.0f;
            }
        }
        return false;
    }
}
