package youyihj.sponsor.event;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import youyihj.sponsor.Sponsor;

@EventBusSubscriber
public class ConfigSyncHandler {
    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(Sponsor.MODID)) {
            ConfigManager.sync(Sponsor.MODID, Config.Type.INSTANCE);
        }
    }
}
