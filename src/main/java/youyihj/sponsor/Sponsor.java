package youyihj.sponsor;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Sponsor.MODID, name = Sponsor.NAME, version = Sponsor.VERSION, acceptedMinecraftVersions = "1.12.2")
public class Sponsor
{
    public static final String MODID = "sponsor";
    public static final String NAME = "Sponsor";
    public static final String VERSION = "1.4";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {

    }
}
