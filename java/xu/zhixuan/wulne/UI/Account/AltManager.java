package xu.zhixuan.wulne.UI.Account;

import java.util.ArrayList;
import java.util.List;

public class AltManager {
    public static List<Alt> alts = new ArrayList<Alt>();;
    static Alt lastAlt;

    public static void init() {
        AltManager.getAlts();
    }

    public Alt getLastAlt() {
        return lastAlt;
    }

    public void setLastAlt(Alt alt) {
        lastAlt = alt;
    }


    public static List<Alt> getAlts() {
        return alts;
    }
}

