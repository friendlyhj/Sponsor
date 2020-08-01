package youyihj.sponsor;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import youyihj.sponsor.client.event.PlayerJoinEventHandler;

@Mod(modid = Sponsor.MODID, version = Sponsor.VERSION, name = Sponsor.NAME)
public class Sponsor
{
    public static final String MODID = "sponsor";
    public static final String VERSION = "0.1";
    public static final String NAME = "Sponsor";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        new SponsorConfig(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        new PlayerJoinEventHandler();
    }
}
