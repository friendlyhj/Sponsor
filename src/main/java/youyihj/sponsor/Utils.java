package youyihj.sponsor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

public class Utils {
    public Utils() {
        throw new UnsupportedOperationException("no instance");
    }

    public static void sendMessage(EntityPlayer player, String message) {
        TextComponentString text = new TextComponentString(message);
        player.sendStatusMessage(text, false);
    }
}
