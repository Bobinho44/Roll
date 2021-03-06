package fr.bobinho.roll;

import co.aikar.commands.PaperCommandManager;
import fr.bobinho.roll.api.command.BCommand;
import fr.bobinho.roll.api.logger.BLogger;
import fr.bobinho.roll.api.setting.BSetting;
import fr.bobinho.roll.util.menu.AttributeMenuManager;
import fr.bobinho.roll.util.player.AttributePlayerManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import javax.annotation.Nonnull;
import java.util.Set;

/**
 * Core of the plugin
 */
public final class RollCore extends JavaPlugin {

    /**
     * Fields
     */
    private static final BLogger bLogger = new BLogger(RollCore.class.getSimpleName());
    private static BSetting playersSetting;
    private static BSetting messagesSetting;

    /**
     * Gets the plugin
     *
     * @return the plugin
     */
    public static RollCore getInstance() {
        return JavaPlugin.getPlugin(RollCore.class);
    }

    /**
     * Gets the logger
     *
     * @return the logger
     */
    public static BLogger getBLogger() {
        return bLogger;
    }

    /**
     * Gets the players setting
     *
     * @return the players setting
     */
    public static BSetting getPlayersSetting() {
        return playersSetting;
    }

    /**
     * Gets the messages setting
     *
     * @return the messages setting
     */
    public static BSetting getMessagesSetting() {
        return messagesSetting;
    }

    /**
     * Enables and initializes the plugin
     */
    @Override
    public void onEnable() {
        bLogger.info("Loading the plugin...");

        //Registers commands
        registerCommands();

        //Registers settings
        playersSetting = new BSetting("players");
        messagesSetting = new BSetting("messages");

        //Initializes manager classes
        AttributePlayerManager.initialize();
        AttributeMenuManager.initialize();
    }

    /**
     * Disables the plugin and save data
     */
    @Override
    public void onDisable() {
        bLogger.info("Unloading the plugin...");
    }

    /**
     * Registers commands
     */
    private void registerCommands() {
        final PaperCommandManager commandManager = new PaperCommandManager(this);
        Reflections reflections = new Reflections("fr.bobinho.roll.commands");
        Set<Class<? extends BCommand>> classes = reflections.getSubTypesOf(BCommand.class);
        for (@Nonnull Class<? extends BCommand> command : classes) {
            try {
                commandManager.registerCommand(command.getDeclaredConstructor().newInstance());
            } catch (Exception exception) {
                getBLogger().error("Couldn't register command(" + command.getName() + ")!", exception);
            }
        }
        bLogger.info("Successfully loaded commands.");
    }

}
