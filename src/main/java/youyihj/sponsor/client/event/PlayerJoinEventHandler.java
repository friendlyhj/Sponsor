package youyihj.sponsor.client.event;


import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import youyihj.sponsor.SponsorConfig;
import youyihj.sponsor.Utils;
import youyihj.sponsor.client.data.PlayerDataHandler;

public class PlayerJoinEventHandler {
    public PlayerJoinEventHandler() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onPlayerJoin(EntityJoinWorldEvent event) {
        Entity entity = event.entity;
        World world = event.world;
        if (entity instanceof EntityPlayer && world.isRemote) {
            EntityPlayer player = (EntityPlayer) entity;
            if (PlayerDataHandler.get(player)) return;
            String modpackName = SponsorConfig.modpackName;
            if (modpackName != null && modpackName.length() > 0) {
                Utils.sendMessageTranslation(player, "welcome", player.getDisplayName(), modpackName);
            }
            String listURL = SponsorConfig.sponsorListURL;
            if (listURL != null && listURL.startsWith("http")) {
                Utils.sendMessageTranslation(player, "showsponsors");
                Utils.showSponsorList(player, world, listURL, SponsorConfig.sponsorListLength, SponsorConfig.sponsorLink);
            }
            PlayerDataHandler.set(player, true);
        }
    }
}
