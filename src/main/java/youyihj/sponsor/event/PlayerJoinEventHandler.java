package youyihj.sponsor.event;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import org.apache.commons.io.IOUtils;
import youyihj.sponsor.Sponsor;
import youyihj.sponsor.SponsorConfig;
import youyihj.sponsor.Utils;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@EventBusSubscriber
public class PlayerJoinEventHandler {
    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof EntityPlayer && !event.getWorld().isRemote) {
            EntityPlayer player = (EntityPlayer) entity;
            String modpackName = SponsorConfig.modpackName;
            if (modpackName != null && modpackName.length() > 0) {
                Utils.sendMessage(player, I18n.format("message." + Sponsor.MODID + ".welcome", player.getName(), modpackName));
            }
            String listURL = SponsorConfig.sponsorListURL;
            if (listURL != null && listURL.startsWith("http")) {
                Utils.sendMessage(player, I18n.format("message." + Sponsor.MODID + ".showsponsors"));
                try {
                    URL url = new URL(listURL);
                    List<String> strings = IOUtils.readLines(url.openStream(), StandardCharsets.UTF_8);
                    ArrayList<Integer> hadGetIndex = new ArrayList<>();
                    if (strings.size() <= SponsorConfig.sponsorListLength) {
                        for (int i = 0; i < strings.size(); i++) {
                            Utils.sendMessage(player, strings.get(i));
                        }
                    } else {
                        Random random = new Random(event.getWorld().getWorldTime());
                        for (int i = 0; i < SponsorConfig.sponsorListLength; i++) {
                            int index = random.nextInt(strings.size());
                            while (hadGetIndex.contains(index)) {
                                index = random.nextInt(strings.size());
                            }
                            hadGetIndex.add(index);
                            Utils.sendMessage(player, strings.get(index));
                        }
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                    Utils.sendMessage(player, I18n.format("message." + Sponsor.MODID + ".fail"));
                }
            }
            String link = SponsorConfig.sponsorLink;
            if (link != null && link.length() > 0) {
                Utils.sendMessage(player, I18n.format("message."+ Sponsor.MODID + ".linktosponsor", link));
            }
        }
    }
}
