package youyihj.sponsor;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class SponsorConfig {
    private static Configuration config;

    public static String modpackName;
    public static String sponsorListURL;
    public static int sponsorListLength;
    public static String sponsorLink;

    public SponsorConfig(FMLPreInitializationEvent event)
    {
        config = new Configuration(event.getSuggestedConfigurationFile());

        config.load();
        load();
    }

    public static void load()
    {
        modpackName = config.get(Configuration.CATEGORY_GENERAL,
                "modpackName",
                "example modpack",
                "The name of your modpack").getString();

        sponsorListURL = config.get(Configuration.CATEGORY_GENERAL,
                "sponsorListURL",
                "https://example.com/SPONSOR.txt",
                "URL of sponsor list").getString();

        sponsorListLength = config.get(Configuration.CATEGORY_GENERAL,
                "sponsorListLength",
                5,
                "Length of sponsor list").getInt();

        sponsorLink = config.get(Configuration.CATEGORY_GENERAL,
                "sponsorLink",
                "https://example.com/",
                "Link to sponsor").getString();

        config.save();
    }

}
