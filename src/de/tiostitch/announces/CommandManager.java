package de.tiostitch.announces;

import de.tiostitch.announces.eventos.AnuncioCMD;
import org.bukkit.Bukkit;

public class CommandManager {

    public static void registerCommands() {

        Bukkit.getPluginCommand("anunciar").setExecutor(new AnuncioCMD());
    }
}
