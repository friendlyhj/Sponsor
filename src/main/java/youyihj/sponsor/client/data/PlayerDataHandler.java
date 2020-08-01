package youyihj.sponsor.client.data;

import net.minecraft.entity.player.EntityPlayer;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDataHandler {
    private static HashMap<UUID, Boolean> data = new HashMap<UUID, Boolean>();

    public static void set(EntityPlayer player, boolean value) {
        data.put(player.getUniqueID(), value);
    }

    public static boolean get(EntityPlayer player) {
        Boolean aBoolean = data.get(player.getUniqueID());
        return (aBoolean == null) ? false : aBoolean;
    }
}