package youyihj.sponsor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {
    public Utils() {
        throw new UnsupportedOperationException("no instance");
    }

    public static void sendMessageTranslation(EntityPlayer player, String key, Object... args) {
        player.addChatComponentMessage(new ChatComponentTranslation(getI18nKey(key), args));
    }

    public static String getI18nKey(String key) {
        return String.format("message.%s.%s", Sponsor.MODID, key);
    }

    public static void showSponsorList(final EntityPlayer player, final World world, final String urlString, final int listLength, final String link) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlString);
                    URLConnection conn = url.openConnection();
                    conn.setConnectTimeout(10000);
                    conn.setReadTimeout(10000);
                    conn.setRequestProperty("User-Agent", Sponsor.MODID);
                    List<String> strings = IOUtils.readLines(conn.getInputStream(), Charset.forName("UTF-8"));
                    ArrayList<Integer> hadGetIndex = new ArrayList<Integer>();
                    if (strings.size() <= listLength) {
                        for (String string : strings) {
                            player.addChatComponentMessage(new ChatComponentText(string));
                        }
                    } else {
                        Random random = new Random();
                        for (int i = 0; i < listLength; i++) {
                            int index = random.nextInt(strings.size());
                            while (hadGetIndex.contains(index)) {
                                index = random.nextInt(strings.size());
                            }
                            hadGetIndex.add(index);
                            player.addChatComponentMessage(new ChatComponentText(strings.get(index)));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Utils.sendMessageTranslation(player, "fail");
                }

                if (link != null && link.length() > 0) {
                    Utils.sendMessageTranslation(player, "linktosponsor", link);
                }
            }
        }).start();
    }
}