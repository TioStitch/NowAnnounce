package de.tiostitch.announces.eventos;

import de.tiostitch.announces.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class AnuncioCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String texto, String[] argumentos) {

        if (sender instanceof Player) {
            if (sender.hasPermission(Main.pl.getConfig().getString("settings.anunciar-permissão"))) {

                Player player = (Player) sender;
                if (argumentos.length >= 1) {
                    // Concatenar todos os argumentos da mensagem
                    String mensagem = String.join(" ", argumentos);

                    // Substituir as variáveis na mensagem configurada
                    String mensagemAnunciada = Main.pl.getConfig().getString("settings.anunciar-mensagem")
                            .replace("%player%", sender.getName())
                            .replace("%anuncio%", mensagem)
                            .replace("&", "§");

                    if (!player.hasPermission(Main.pl.getConfig().getString("settings.censura-bypass"))) {
                        List<String> censuras = Main.pl.getConfig().getStringList("palavras-proibidas");
                        if (censuras.contains(mensagemAnunciada)) {
                            String command = Main.pl.getConfig().getString("settings.anuncio-censura-evento").replace("%player%", player.getName());

                            TextComponent component = new TextComponent(Main.pl.getConfig().getString("settings.anuncio-censura-click"));
                            component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
                            player.sendMessage(Main.pl.getConfig().getString("settings.anunciar-individuo"));
                            player.spigot().sendMessage(component);

                            for (Player pall : Bukkit.getOnlinePlayers()) {
                                if (pall.hasPermission(Main.pl.getConfig().getString("settings.censura-staff-perm"))) {
                                    if (Main.pl.getConfig().getBoolean("toggles.anunciar-staff-titulo")) {
                                        String titulo = Main.pl.getConfig().getString("settings.anunciar-staff-título");
                                        String subtitulo = Main.pl.getConfig().getString("settings.anunciar-staff-subtitulo");


                                        if (Main.pl.getConfig().getBoolean("toggles.anunciar-staff-subtitulo")) {
                                            pall.sendTitle(titulo, subtitulo);
                                        } else {
                                            pall.sendTitle(titulo, subtitulo);
                                        }
                                    }
                                }
                            }
                            return false;
                        }
                    }

                        if (Main.pl.getConfig().getBoolean("toggles.anunciar-com-titulo")) {
                        String titulo = Main.pl.getConfig().getString("settings.anunciar-título");
                        String subtitulo = Main.pl.getConfig().getString("settings.anunciar-subtitulo");

                        if (Main.pl.getConfig().getBoolean("toggles.anunciar-com-subtitulo")) {
                            player.sendTitle(titulo, subtitulo);
                        } else {
                            player.sendTitle(titulo, "");
                        }
                    }

                    // Transmitir a mensagem para todos os jogadores online
                    Bukkit.broadcastMessage(mensagemAnunciada);

                    return true;
                } else {
                    sender.sendMessage("§cPor favor, use /anunciar <Mensagem>");
                    return false;
                }
            } else {
                sender.sendMessage("§cVocê não tem permissão para usar este comando!");
            }
        } else {
            sender.sendMessage("§cApenas jogadores podem executar este comando!");
        }

        return false;
    }
}
