package me.twoleggedcat.mobheaddisguises;

import net.kyori.adventure.text.Component;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class Main extends JavaPlugin implements Listener {
    // Store config values as variables; not necessary, but no real reason to use getConfig every time
    public boolean magicDisguises;
    public boolean zombiesAreDumb;
    public boolean skeletonsAreDumb;
    public boolean witherSkeletonsAreDumb;
    public boolean creepersAreDumb;

    @Override
    public void onEnable() {
        new Metrics(this, 11512);
        this.saveDefaultConfig();
        this.reloadConfig();
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1) return false;
        if (args[0].equalsIgnoreCase("reload")) this.reloadConfig();
        else if (args[0].equalsIgnoreCase("set")) {
            if (args.length < 3) return false;
            if (args[1].equals("magic-disguises")
                    || args[1].equals("zombies-are-dumb")
                    || args[1].equals("skeletons-are-dumb")
                    || args[1].equals("wither-skeletons-are-dumb")
                    || args[1].equals("creepers-are-dumb")) {
                boolean toSet;
                if (args[2].equalsIgnoreCase("true"))
                    toSet = true;
                else if (args[2].equalsIgnoreCase("false"))
                    toSet = false;
                else
                    return false;
                this.getConfig().set(args[1], toSet);
                sender.sendMessage(Component.text("Set " + args[1] + " to " + args[2]));
            }
        }
        return true;
    }

    @EventHandler
    public void onTarget(EntityTargetEvent e) {
        if (e.getTarget() instanceof Player player) {
            EntityType type = e.getEntityType();
            ItemStack helmet = player.getInventory().getHelmet();
            if (helmet == null)
                return;
            if (magicDisguises) {
                e.setCancelled(true);
                return;
            }
            Material neededType = switch (type) {
                case ZOMBIE -> (zombiesAreDumb ? Material.ZOMBIE_HEAD : null);
                case SKELETON -> (skeletonsAreDumb ?  Material.SKELETON_SKULL : null);
                case WITHER_SKELETON -> (witherSkeletonsAreDumb ? Material.WITHER_SKELETON_SKULL : null);
                case CREEPER -> (creepersAreDumb ? Material.CREEPER_HEAD : null);
                default -> null;
            };
            if (helmet.getType() == neededType)
                e.setCancelled(true);
        }
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();
        this.magicDisguises = this.getConfig().getBoolean("magic-disguises");
        this.zombiesAreDumb = this.getConfig().getBoolean("zombies-are-dumb");
        this.skeletonsAreDumb = this.getConfig().getBoolean("skeletons-are-dumb");
        this.witherSkeletonsAreDumb = this.getConfig().getBoolean("wither-skeletons-are-dumb");
        this.creepersAreDumb = this.getConfig().getBoolean("creepers-are-dumb");
    }
}
