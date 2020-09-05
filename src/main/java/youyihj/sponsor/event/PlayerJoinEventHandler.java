package youyihj.sponsor.event;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import youyihj.sponsor.SponsorConfig;
import youyihj.sponsor.Utils;
import youyihj.sponsor.data.PlayerDataHandler;

import java.util.Random;


@EventBusSubscriber
public class PlayerJoinEventHandler {
    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        World world = event.getWorld();
        if (entity instanceof EntityPlayer && world.isRemote) {
            EntityPlayer player = (EntityPlayer) entity;
            if (PlayerDataHandler.get(player)) return;
            String modpackName = SponsorConfig.modpackName;
            if (modpackName != null && modpackName.length() > 0) {
                Utils.sendMessage(player, I18n.format(Utils.getI18nKey("welcome"), player.getName(), modpackName));
            }
            String listURL = SponsorConfig.sponsorListURL;
            if (listURL != null && listURL.startsWith("http")) {
                Utils.sendMessage(player, I18n.format(Utils.getI18nKey("showsponsors")));
                Utils.showSponsorList(player, new Random(), listURL, SponsorConfig.sponsorListLength, SponsorConfig.sponsorLink);
            }
            PlayerDataHandler.set(player, true);
        }
    }
}
