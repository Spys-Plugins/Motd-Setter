package dev.cosmics.motdsetter;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class MotdSetter extends JavaPlugin implements Listener {
    private String motd;

    @Override
    public void onEnable() {
        //Load config
        saveDefaultConfig();
        //Setup motd
        motd = getConfig().getString("motd").replace("&", "§");
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("motd").setExecutor(this);
    }

    @Override
    public void onDisable() {
        //Save the motd
        getConfig().set("motd", motd.replace("§", "&"));
        saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String motd1 = String.join(" ", args);
        motd = ChatColor.translateAlternateColorCodes('&', motd1);
        sender.sendMessage( ChatColor.GREEN + "set motd to " + motd);
        return true;
    }

    @EventHandler
    public void onPing(ServerListPingEvent event) {
        event.setMotd(motd);
    }
}
