package be.jordiperreman.coalconverter.util;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

public class ChatUtil {

    /**
     * Translates a string using an alternate color code character into a
     * string that uses the internal ChatColor.COLOR_CODE color code
     * character.
     *
     * @param string String with alternate color codes to be translated.
     * @return String with translated color codes.
     */
    @NotNull
    public static String color(String string) {
        string += " &r";
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    /**
     * Get the prefix or debug prefix from the configuration file.
     *
     * @param debug Optional parameter for using debug prefix.
     * @return String prefix.
     */
    public static String getPrefix(boolean... debug) {
        return color(debug.length == 0 ? color(Files.MESSAGES.getString("messages.general.prefix"))
                : color(Files.MESSAGES.getString("messages.general.prefix_debug")));
    }

    /**
     * Get a string from the configuration file which is already translated
     * using alternate color code character.
     *
     * @param file   Given file enum.
     * @param string Given string to get config string.
     * @param debug  Optional parameter for using debug prefix.
     * @return String chat.
     */
    public static String getString(Files file, String string, boolean... debug) {
        return getPrefix(debug) + color(file.getString(string));
    }
}