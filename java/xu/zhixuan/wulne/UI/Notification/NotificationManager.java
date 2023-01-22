package xu.zhixuan.wulne.UI.Notification;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public enum NotificationManager {
    INSTANCE;
    private static ArrayList<Notification> notifications = new ArrayList<>();
    public static double addY;

    public void drawNotifications() {
        try {
            ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
            double startY = res.getScaledHeight() - 25;
            final double lastY = startY;
            for (int i = 0; i < notifications.size(); i++) {
                Notification not = notifications.get(i);
                if (not.shouldDelete()) {
                    notifications.remove(i);
                }
                not.drawBlack2(startY, lastY);
                startY -= not.getHeight() + 1;
            }
        } catch (Throwable e) {

        }
    }

    public static void sendClientMessage(String message, Notification.Type type) {
        if (notifications.size() > 8) {
            notifications.remove(0);
        }
        boolean has = false;
        for (Notification n : notifications) {
            if (n.getMessage().equals(message)) {
                has = true;
            }
        }
        if (!has) {
            notifications.add(new Notification(message, type));
        }
    }
}
