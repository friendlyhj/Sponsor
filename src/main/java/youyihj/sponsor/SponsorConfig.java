package youyihj.sponsor;

import net.minecraftforge.common.config.Config;

@Config(modid = Sponsor.MODID, name = Sponsor.NAME)
public class SponsorConfig {
    @Config.RequiresWorldRestart
    @Config.Comment("The name of your modpack")
    public static String modpackName = "example modpack";

    @Config.RequiresWorldRestart
    @Config.Comment("URL of sponsor list")
    public static String sponsorListURL = "https://example.com/SPONSOR.txt";

    @Config.RequiresWorldRestart
    @Config.Comment("Length of sponsor list")
    public static int sponsorListLength = 5;

    @Config.RequiresWorldRestart
    @Config.Comment("Link to sponsor")
    public static String sponsorLink = "https://example.com/";
}