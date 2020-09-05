package youyihj.sponsor;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {
    public Utils() {
        throw new UnsupportedOperationException("no instance");
    }

    public static void sendMessage(EntityPlayer player, String message) {
        TextComponentString text = new TextComponentString(message);
        player.sendStatusMessage(text, false);
    }

    public static String getI18nKey(String key) {
        return String.format("message.%s.%s", Sponsor.MODID, key);
    }

    public static void showSponsorList(EntityPlayer player, Random random, String urlString, int listLength, String link) {
        new Thread(() -> {
            try {
                URL url = new URL(urlString);
                URLConnection conn = url.openConnection();
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(10000);
                conn.setRequestProperty("User-Agent", Sponsor.MODID);
                List<String> strings = IOUtils.readLines(conn.getInputStream(), StandardCharsets.UTF_8);
                ArrayList<Integer> hadGetIndex = new ArrayList<>();
                if (strings.size() <= listLength) {
                    for (String string : strings) {
                        Utils.sendMessage(player, string);
                    }
                } else {
                    for (int i = 0; i < listLength; i++) {
                        int index = random.nextInt(strings.size());
                        while (hadGetIndex.contains(index)) {
                            index = random.nextInt(strings.size());
                        }
                        hadGetIndex.add(index);
                        Utils.sendMessage(player, strings.get(index));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                Utils.sendMessage(player, I18n.format(getI18nKey("fail")));
            }

            if (link != null && link.length() > 0) {
                Utils.sendMessage(player, I18n.format(getI18nKey("linktosponsor"), link));
            }
        }).start();
    }
}
