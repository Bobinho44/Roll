package fr.bobinho.roll.commands;

import co.aikar.commands.annotation.*;
import fr.bobinho.roll.api.command.BCommand;
import fr.bobinho.roll.api.notification.BPlaceHolder;
import fr.bobinho.roll.util.menu.AttributeMenuManager;
import fr.bobinho.roll.util.player.AttributePlayerManager;
import fr.bobinho.roll.util.notification.AdminNotification;
import org.bukkit.entity.Player;

/**
 * Command of attribute
 */
@CommandAlias("attribute|attributes")
public class AttributeCommand extends BCommand {

    /**
     * Command attribute help
     */
    @Syntax("/attribute help")
    @Subcommand("help")
    @CommandPermission("attribute.help")
    @Description("Gets the roll command help.")
    public void onCommandAttributeHelp(Player sender) {
        sendCommandHelp(this.getClass(), sender, "attribute");
    }

    /**
     * Command attribute give
     */
    @Syntax("/attribute give <player>")
    @Subcommand("give")
    @CommandPermission("attribute.give")
    @Description("Gives an attribute point to a player.")
    public void onCommandAttributeGive(Player sender, Player target) {
        AttributePlayerManager.getAttributePlayer(target.getUniqueId()).ifPresentOrElse(player -> {
            player.givePoint();
            sender.sendMessage(AdminNotification.ATTRIBUTE_GIVE_SENDER.getNotification(new BPlaceHolder("%target", target.getName())));
            target.sendMessage(AdminNotification.ATTRIBUTE_GIVE_TARGET.getNotification());
        }, () -> sender.sendMessage(AdminNotification.NO_PLAYER_FOUND.getNotification()));
    }

    /**
     * Command attribute
     */
    @Default
    @Syntax("/attribute")
    @CommandPermission("attribute")
    @Description("Open the attributes menu.")
    public void onCommandAttribute(Player sender) {
        AttributePlayerManager.getAttributePlayer(sender.getUniqueId()).ifPresent(AttributeMenuManager::openAttributeMenu);
    }

    /**
     * Command attribute remove
     */
    @Syntax("/attribute remove <player>")
    @Subcommand("remove")
    @CommandPermission("attribute.remove")
    @Description("Removes an attribute point from a player.")
    public void onCommandAttributeRemove(Player sender, Player target) {
        AttributePlayerManager.getAttributePlayer(target.getUniqueId()).ifPresentOrElse(player -> {
            player.removePoint();
            sender.sendMessage(AdminNotification.ATTRIBUTE_REMOVE_SENDER.getNotification(new BPlaceHolder("%target", target.getName())));
            target.sendMessage(AdminNotification.ATTRIBUTE_REMOVE_TARGET.getNotification());
        }, () -> sender.sendMessage(AdminNotification.NO_PLAYER_FOUND.getNotification()));
    }

    /**
     * Command attribute reset
     */
    @Syntax("/attribute reset <player>")
    @Subcommand("reset")
    @CommandPermission("attribute.reset")
    @Description("Resets the attributes of a player.")
    public void onCommandAttributeReset(Player sender, Player target) {
        AttributePlayerManager.getAttributePlayer(target.getUniqueId()).ifPresentOrElse(player -> {
            player.reset();
            sender.sendMessage(AdminNotification.ATTRIBUTE_RESET_SENDER.getNotification(new BPlaceHolder("%target", target.getName())));
            target.sendMessage(AdminNotification.ATTRIBUTE_RESET_TARGET.getNotification());
        }, () -> sender.sendMessage(AdminNotification.NO_PLAYER_FOUND.getNotification()));
    }

}
