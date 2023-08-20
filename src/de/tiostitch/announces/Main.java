package de.tiostitch.announces;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main pl;

    @Override
    public void onEnable() {
        pl = this;

        Bukkit.getConsoleSender().sendMessage("§b----------------------------------");
        Bukkit.getConsoleSender().sendMessage("   §aNowAnnounces - §ev1.0");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("  §fFeito por: §bTioStitch");
        Bukkit.getConsoleSender().sendMessage("  §fEstado: §aIniciado!");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§b----------------------------------");
        Bukkit.getConsoleSender().sendMessage("§fJogadores Online:" + Bukkit.getOnlinePlayers().size());

        CommandManager.registerCommands();

        saveDefaultConfig();
    }
}
