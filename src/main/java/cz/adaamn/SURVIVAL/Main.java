package cz.adaamn.SURVIVAL;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class Main extends JavaPlugin implements Listener {
    String PREFIX = "" + ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW + "12LIFE" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY + "";

    @Override
    public void onEnable() {
        getLogger().info("SURVIVAL plugin enabled");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        e.setJoinMessage(PREFIX + ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " joined the server");
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        e.setQuitMessage(PREFIX + ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " left the server");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getPlayer();
        Location loc = player.getLocation();

        player.sendMessage(PREFIX + "Poslední lokace: " + ChatColor.YELLOW + (int) loc.getX() + " " + (int) loc.getY() + " " + (int) loc.getZ());
    }

    @EventHandler
    public void onGMChange(PlayerGameModeChangeEvent e) {
        Player player = e.getPlayer();
        Bukkit.getServer().broadcastMessage(PREFIX + ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " ted ma " + ChatColor.YELLOW + e.getNewGameMode());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (command.getName().equalsIgnoreCase("kdeje")) {
            if (args.length == 0) {
                sender.sendMessage(PREFIX + "Musíš zadat jméno");
                return true;
            }
            else if (args.length == 1){
                Player player = Bukkit.getPlayerExact(args[0]);
                if (player != null) {
                    Location lokace = player.getLocation();

                    sender.sendMessage(PREFIX + ChatColor.YELLOW + args[0] + ChatColor.GRAY + " se nachazi na " + ChatColor.YELLOW + player.getWorld().getName() + ChatColor.GRAY + ": " + ChatColor.YELLOW + (int) lokace.getX() + " " + (int) lokace.getY() + " " + (int) lokace.getZ());
                } else {
                    sender.sendMessage(PREFIX + ChatColor.YELLOW + args[0] + ChatColor.GRAY + " neni na serveru");
                }
            }
            else {
                sender.sendMessage(PREFIX + "Špatně zadané argumenty");
            }
            return true;
        }
        return false;
    }



    @Override
    public void onDisable() {
        getLogger().info("SURVIVAL plugin disabled");
    }
}
