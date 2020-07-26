package youyihj.sponsor.data;

import net.minecraft.entity.player.EntityPlayer;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDataHandler {
    private static HashMap<UUID, Boolean> data = new HashMap<>();

    public static void set(EntityPlayer player, boolean value) {
        data.put(player.getUniqueID(), value);
    }

    public static boolean get(EntityPlayer player) {
        return data.getOrDefault(player.getUniqueID(), false);
    }
}
