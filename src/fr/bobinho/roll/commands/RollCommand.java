package fr.bobinho.roll.commands;

import co.aikar.commands.annotation.*;
import fr.bobinho.roll.api.command.BCommand;
import fr.bobinho.roll.api.notification.BPlaceHolder;
import fr.bobinho.roll.api.number.BNumber;
import fr.bobinho.roll.api.validate.BValidate;
import fr.bobinho.roll.util.attribute.Attribute;
import fr.bobinho.roll.util.notification.RollNotification;
import fr.bobinho.roll.util.player.AttributePlayerManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * Command of roll
 */
@CommandAlias("roll")
public final class RollCommand extends BCommand {

    /**
     * Command roll help
     */
    @Syntax("/roll help")
    @Subcommand("help")
    @CommandPermission("roll.help")
    @Description("Gets the roll command help.")
    public void onCommandRollHelp(Player sender) {
        sendCommandHelp(this.getClass(), sender, "roll");
    }

    /**
     * Command roll
     */
    @Default
    @Syntax("/roll [max]")
    @CommandPermission("roll")
    @Description("Dices a classic roll.")
    public void onCommandRoll(Player sender, @Optional Integer max) {
        max = (max == null || max < 1) ? 20 : max;
        sendMessage(sender, RollNotification.ROLL.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                new BPlaceHolder("%roll%", "" + (BNumber.random(1, max + 1))), new BPlaceHolder("%max%", "" + (int) max)));
    }

    /**
     * Command roll attack
     */
    @Syntax("/roll attack")
    @Subcommand("attack")
    @CommandPermission("roll.attack")
    @Description("Dices an attack roll.")
    public void onCommandRollAttack(Player sender) {

        //Gets max value
        int max = 20 + switch (sender.getInventory().getItemInMainHand().getType()) {
            case WOODEN_SWORD -> 1;
            case STONE_SWORD -> 2;
            case IRON_SWORD -> 3;
            case GOLDEN_SWORD -> 3;
            case DIAMOND_SWORD -> 4;
            case NETHERITE_SWORD -> 5;
            default -> 0;
        };

        //Gets rolled value
        int rolled = BNumber.random(1, max + 1);

        //Sends message
        if (rolled < 10)
            sendMessage(sender, RollNotification.ROLL_ATTACK_LESS_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                    new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
        else if (rolled == 20)
            sendMessage(sender, RollNotification.ROLL_ATTACK_20.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                    new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
        else
            sendMessage(sender, RollNotification.ROLL_ATTACK_MORE_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                    new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
    }

    /**
     * Command roll defend
     */
    @Syntax("/roll defend")
    @Subcommand("defend")
    @CommandPermission("roll.defend")
    @Description("Dices a defend roll.")
    public void onCommandRollDefend(Player sender) {

        //Gets max value
        double max = 20 + (sender.getInventory().getItemInOffHand().getType() == Material.SHIELD ? 1 : 0);
        for (ItemStack armor : sender.getInventory().getArmorContents()) {
            if (armor == null)
                continue;
            max += switch (armor.getType().name().split("_")[0]) {
                case "LEATHER" -> 0.25;
                case "CHAINMAIL" -> 0.5;
                case "IRON" -> 0.75;
                case "GOLDEN" -> 0.75;
                case "DIAMOND" -> 1;
                case "NETHERITE" -> 1.25;
                default -> 0;
            };
        }

        //Gets rolled value
        int rolled = BNumber.random(1, (int) max + 1);

        //Sends message
        if (rolled < 10)
            sendMessage(sender, RollNotification.ROLL_DEFEND_LESS_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                    new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
        else if (rolled == 20)
            sendMessage(sender, RollNotification.ROLL_DEFEND_20.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                    new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
        else
            sendMessage(sender, RollNotification.ROLL_DEFEND_MORE_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                    new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
    }

    /**
     * Command roll range
     */
    @Syntax("/roll range")
    @Subcommand("range")
    @CommandPermission("roll.range")
    @Description("Dices a range roll.")
    public void onCommandRollRange(Player sender) {

        //Gets max value
        double max = 20;
        for (ItemStack armor : sender.getInventory().getArmorContents()) {
            if (armor == null)
                continue;
            max += switch (armor.getType().name().split("_")[0]) {
                case "LEATHER" -> 0.75;
                case "CHAINMAIL" -> 0.5;
                default -> 0;
            };
        }
        max += switch (sender.getInventory().getItemInMainHand().getType()) {
            case BOW -> 2;
            case CROSSBOW -> 2;
            case WOODEN_HOE -> 2;
            case GOLDEN_HOE -> 2;
            case STICK -> 2;
            case BLAZE_ROD -> 2;
            case WRITTEN_BOOK -> 2;
            default -> 0;
        };

        //Gets rolled value
        int rolled = BNumber.random(1, (int) max + 1);

        //Sends message
        if (rolled < 10)
            sendMessage(sender, RollNotification.ROLL_RANGE_LESS_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                    new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
        else if (rolled == 20)
            sendMessage(sender, RollNotification.ROLL_RANGE_20.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                    new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
        else
            sendMessage(sender, RollNotification.ROLL_RANGE_MORE_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                    new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
    }

    /**
     * Command roll run
     */
    @Syntax("/roll run")
    @Subcommand("run")
    @CommandPermission("roll.run")
    @Description("Dices a run roll.")
    public void onCommandRollRun(Player sender) {

        //Gets max value
        double max = 20;
        for (ItemStack armor : sender.getInventory().getArmorContents()) {
            if (armor == null)
                continue;
            max -= switch (armor.getType().name().split("_")[0]) {
                case "LEATHER" -> 0.5;
                case "CHAINMAIL" -> 0.5;
                case "IRON" -> 1;
                case "GOLDEN" -> 1;
                case "DIAMOND" -> 2;
                case "NETHERITE" -> 2;
                default -> 0;
            };
        }

        //Gets rolled value
        int rolled = BNumber.random(1, (int) max + 1);

        //Sends message
        if (rolled == 20)
            sendMessage(sender, RollNotification.ROLL_RUN_20.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                    new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
        else
            sendMessage(sender, RollNotification.ROLL_RUN_NOT_20.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                    new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
    }

    /**
     * Command roll grab
     */
    @Syntax("/roll grab")
    @Subcommand("grab")
    @CommandPermission("roll.grab")
    @Description("Dices a grab roll.")
    public void onCommandRollGrab(Player sender) {

        //Gets max value
        double max = 20;
        for (ItemStack armor : sender.getInventory().getArmorContents()) {
            if (armor == null)
                continue;
            max -= switch (armor.getType().name().split("_")[0]) {
                case "LEATHER" -> 0.5;
                case "CHAINMAIL" -> 0.5;
                case "IRON" -> 1;
                case "GOLDEN" -> 1;
                case "DIAMOND" -> 2;
                case "NETHERITE" -> 2;
                default -> 0;
            };
        }

        //Gets rolled value
        int rolled = BNumber.random(1, (int) max + 1);

        //Sends message
        if (rolled == 20)
            sendMessage(sender, RollNotification.ROLL_GRAB_20.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                    new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
        else
            sendMessage(sender, RollNotification.ROLL_GRAB_NOT_20.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                    new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
    }

    /**
     * Command roll resist
     */
    @Syntax("/roll resist")
    @Subcommand("resist")
    @CommandPermission("roll.resist")
    @Description("Dices a resist roll.")
    public void onCommandRollResist(Player sender) {

        //Gets max value
        double max = 20;
        for (ItemStack armor : sender.getInventory().getArmorContents()) {
            if (armor == null)
                continue;
            max += switch (armor.getType().name().split("_")[0]) {
                case "LEATHER" -> 0.25;
                case "CHAINMAIL" -> 0.5;
                case "IRON" -> 0.75;
                case "GOLDEN" -> 0.75;
                case "DIAMOND" -> 1;
                case "NETHERITE" -> 1.25;
                default -> 0;
            };
        }

        //Gets rolled value
        int rolled = BNumber.random(1, (int) max + 1);

        //Sends message
        if (rolled == 20)
            sendMessage(sender, RollNotification.ROLL_RESIST_20.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                    new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
        else
            sendMessage(sender, RollNotification.ROLL_RESIST_NOT_20.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                    new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
    }

    /**
     * Command roll pickpocket
     */
    @Syntax("/roll pickpocket")
    @Subcommand("pickpocket")
    @CommandPermission("roll.pickpocket")
    @Description("Dices a pickpocket roll.")
    public void onCommandRollPickPocket(Player sender) {
        AttributePlayerManager.getAttributePlayer(sender.getUniqueId()).ifPresent(player -> {

            //Gets max value
            double max = 20 + player.attributePoints().get(Attribute.PICKPOCKETING.id().get());
            for (ItemStack armor : sender.getInventory().getArmorContents()) {
                if (armor == null)
                    continue;
                max -= switch (armor.getType().name().split("_")[0]) {
                    case "LEATHER" -> 0.5;
                    case "CHAINMAIL" -> 1;
                    case "IRON" -> 2;
                    case "GOLDEN" -> 2;
                    case "DIAMOND" -> 3;
                    case "NETHERITE" -> 4;
                    default -> 0;
                };
            }

            //Gets rolled value
            int rolled = BNumber.random(1, (int) max + 1);

            //Sends message
            if (rolled < 10)
                sendMessage(sender, RollNotification.ROLL_PICKPOCKET_LESS_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
            else if (rolled == 20)
                sendMessage(sender, RollNotification.ROLL_PICKPOCKET_20.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
            else
                sendMessage(sender, RollNotification.ROLL_PICKPOCKET_MORE_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
        });
    }

    /**
     * Command roll stealth
     */
    @Syntax("/roll stealth")
    @Subcommand("stealth")
    @CommandPermission("roll.stealth")
    @Description("Dices a stealth roll.")
    public void onCommandRollStealth(Player sender) {
        AttributePlayerManager.getAttributePlayer(sender.getUniqueId()).ifPresent(player -> {

            //Gets max value
            double max = 20 + player.attributePoints().get(Attribute.STEALTH.id().get());
            for (ItemStack armor : sender.getInventory().getArmorContents()) {
                if (armor == null)
                    continue;
                max -= switch (armor.getType().name().split("_")[0]) {
                    case "LEATHER" -> 0.25;
                    case "CHAINMAIL" -> 0.5;
                    case "IRON" -> 0.75;
                    case "GOLDEN" -> 0.75;
                    case "DIAMOND" -> 1;
                    case "NETHERITE" -> 1.25;
                    default -> 0;
                };
            }

            //Gets rolled value
            int rolled = BNumber.random(1, (int) max + 1);

            //Sends message
            if (rolled < 10)
                sendMessage(sender, RollNotification.ROLL_STEALTH_LESS_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
            else
                sendMessage(sender, RollNotification.ROLL_STEALTH_MORE_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
        });
    }

    /**
     * Command roll acrobatics
     */
    @Syntax("/roll acrobatics")
    @Subcommand("acrobatics")
    @CommandPermission("roll.acrobatics")
    @Description("Dices an acrobatics roll.")
    public void onCommandRollAcrobatics(Player sender) {
        AttributePlayerManager.getAttributePlayer(sender.getUniqueId()).ifPresent(player -> {

            //Gets max value
            double max = 20 + player.attributePoints().get(Attribute.ACROBATICS.id().get());

            //Gets rolled value
            int rolled = BNumber.random(1, (int) max + 1);

            //Sends message
            if (rolled < 10)
                sendMessage(sender, RollNotification.ROLL_ACROBATICS_LESS_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
            else
                sendMessage(sender, RollNotification.ROLL_ACROBATICS_MORE_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
        });
    }

    /**
     * Command roll athletics
     */
    @Syntax("/roll athletics")
    @Subcommand("athletics")
    @CommandPermission("roll.athletics")
    @Description("Dices an athletics roll.")
    public void onCommandRollAthletics(Player sender) {
        AttributePlayerManager.getAttributePlayer(sender.getUniqueId()).ifPresent(player -> {

            //Gets max value
            double max = 20 + player.attributePoints().get(Attribute.ATHLETICS.id().get());

            //Gets rolled value
            int rolled = BNumber.random(1, (int) max + 1);

            //Sends message
            if (rolled < 10)
                sendMessage(sender, RollNotification.ROLL_ATHLETICS_LESS_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
            else
                sendMessage(sender, RollNotification.ROLL_ATHLETICS_MORE_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
        });
    }

    /**
     * Command roll reflex
     */
    @Syntax("/roll reflex")
    @Subcommand("reflex")
    @CommandPermission("roll.reflex")
    @Description("Dices a reflex roll.")
    public void onCommandRollReflex(Player sender) {
        AttributePlayerManager.getAttributePlayer(sender.getUniqueId()).ifPresent(player -> {

            //Gets max value
            double max = 20 + player.attributePoints().get(Attribute.REFLEX.id().get());

            //Gets rolled value
            int rolled = BNumber.random(1, (int) max + 1);

            //Sends message
            if (rolled < 10)
                sendMessage(sender, RollNotification.ROLL_REFLEX_LESS_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
            else
                sendMessage(sender, RollNotification.ROLL_REFLEX_MORE_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
        });
    }

    /**
     * Command roll charisma
     */
    @Syntax("/roll charisma")
    @Subcommand("charisma")
    @CommandPermission("roll.charisma")
    @Description("Dices a charisma roll.")
    public void onCommandRollCharisma(Player sender) {
        AttributePlayerManager.getAttributePlayer(sender.getUniqueId()).ifPresent(player -> {

            //Gets max value
            double max = 20 + player.attributePoints().get(Attribute.CHARISMA.id().get());

            //Gets rolled value
            int rolled = BNumber.random(1, (int) max + 1);

            //Sends message
            if (rolled < 15)
                sendMessage(sender, RollNotification.ROLL_CHARISMA_LESS_15.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
            else
                sendMessage(sender, RollNotification.ROLL_CHARISMA_MORE_15.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
        });
    }

    /**
     * Command roll persuasion
     */
    @Syntax("/roll persuasion")
    @Subcommand("persuasion")
    @CommandPermission("roll.persuasion")
    @Description("Dices a persuasion roll.")
    public void onCommandRollPersuasion(Player sender) {
        AttributePlayerManager.getAttributePlayer(sender.getUniqueId()).ifPresent(player -> {

            //Gets max value
            double max = 20 + player.attributePoints().get(Attribute.PERSUASION.id().get());

            //Gets rolled value
            int rolled = BNumber.random(1, (int) max + 1);

            //Sends message
            if (rolled < 15)
                sendMessage(sender, RollNotification.ROLL_PERSUASION_LESS_15.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
            else
                sendMessage(sender, RollNotification.ROLL_PERSUASION_MORE_15.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
        });
    }

    /**
     * Command roll deception
     */
    @Syntax("/roll deception")
    @Subcommand("deception")
    @CommandPermission("roll.deception")
    @Description("Dices a deception roll.")
    public void onCommandRollDeception(Player sender) {
        AttributePlayerManager.getAttributePlayer(sender.getUniqueId()).ifPresent(player -> {

            //Gets max value
            double max = 20 + player.attributePoints().get(Attribute.DECEPTION.id().get());

            //Gets rolled value
            int rolled = BNumber.random(1, (int) max + 1);

            //Sends message
            if (rolled < 15)
                sendMessage(sender, RollNotification.ROLL_DECEPTION_LESS_15.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
            else
                sendMessage(sender, RollNotification.ROLL_DECEPTION_MORE_15.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
        });
    }

    /**
     * Command roll intimidation
     */
    @Syntax("/roll intimidation")
    @Subcommand("intimidation")
    @CommandPermission("roll.intimidation")
    @Description("Dices a intimidation roll.")
    public void onCommandRollIntimidation(Player sender) {
        AttributePlayerManager.getAttributePlayer(sender.getUniqueId()).ifPresent(player -> {

            //Gets max value
            double max = 20 + player.attributePoints().get(Attribute.INTIMIDATION.id().get());

            //Gets rolled value
            int rolled = BNumber.random(1, (int) max + 1);

            //Sends message
            if (rolled < 15)
                sendMessage(sender, RollNotification.ROLL_INTIMIDATION_LESS_15.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
            else
                sendMessage(sender, RollNotification.ROLL_INTIMIDATION_MORE_15.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
        });
    }

    /**
     * Command roll religion
     */
    @Syntax("/roll religion")
    @Subcommand("religion")
    @CommandPermission("roll.religion")
    @Description("Dices a religion roll.")
    public void onCommandRollReligion(Player sender) {
        AttributePlayerManager.getAttributePlayer(sender.getUniqueId()).ifPresent(player -> {

            //Gets max value
            double max = 20 + player.attributePoints().get(Attribute.RELIGION.id().get());

            //Gets rolled value
            int rolled = BNumber.random(1, (int) max + 1);

            //Sends message
            if (rolled < 15)
                sendMessage(sender, RollNotification.ROLL_RELIGION_LESS_15.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
            else
                sendMessage(sender, RollNotification.ROLL_RELIGION_MORE_15.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
        });
    }

    /**
     * Command roll perception
     */
    @Syntax("/roll perception")
    @Subcommand("perception")
    @CommandPermission("roll.perception")
    @Description("Dices a perception roll.")
    public void onCommandRollPerception(Player sender) {
        AttributePlayerManager.getAttributePlayer(sender.getUniqueId()).ifPresent(player -> {

            //Gets max value
            double max = 20 + player.attributePoints().get(Attribute.PERCEPTION.id().get());

            //Gets rolled value
            int rolled = BNumber.random(1, (int) max + 1);

            //Sends message
            if (rolled < 10)
                sendMessage(sender, RollNotification.ROLL_PERCEPTION_LESS_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
            else
                sendMessage(sender, RollNotification.ROLL_PERCEPTION_MORE_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
        });
    }

    /**
     * Command roll nature
     */
    @Syntax("/roll nature")
    @Subcommand("nature")
    @CommandPermission("roll.nature")
    @Description("Dices a nature roll.")
    public void onCommandRollNature(Player sender) {
        AttributePlayerManager.getAttributePlayer(sender.getUniqueId()).ifPresent(player -> {

            //Gets max value
            double max = 20 + player.attributePoints().get(Attribute.NATURE.id().get());

            //Gets rolled value
            int rolled = BNumber.random(1, (int) max + 1);

            //Sends message
            if (rolled < 10)
                sendMessage(sender, RollNotification.ROLL_NATURE_LESS_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
            else
                sendMessage(sender, RollNotification.ROLL_NATURE_MORE_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
        });
    }

    /**
     * Command roll animal
     */
    @Syntax("/roll animal")
    @Subcommand("animal")
    @CommandPermission("roll.animal")
    @Description("Dices an animal roll.")
    public void onCommandRollAnimal(Player sender) {
        AttributePlayerManager.getAttributePlayer(sender.getUniqueId()).ifPresent(player -> {

            //Gets max value
            double max = 20 + player.attributePoints().get(Attribute.ANIMAL_HANDLING.id().get());

            //Gets rolled value
            int rolled = BNumber.random(1, (int) max + 1);

            //Sends message
            if (rolled < 10)
                sendMessage(sender, RollNotification.ROLL_ANIMAL_HANDLING_LESS_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
            else
                sendMessage(sender, RollNotification.ROLL_ANIMAL_HANDLING_MORE_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
        });
    }

    /**
     * Command roll medicine
     */
    @Syntax("/roll medicine")
    @Subcommand("medicine")
    @CommandPermission("roll.medicine")
    @Description("Dices a medicine roll.")
    public void onCommandRollMedicine(Player sender) {
        AttributePlayerManager.getAttributePlayer(sender.getUniqueId()).ifPresent(player -> {

            //Gets max value
            double max = 20 + player.attributePoints().get(Attribute.MEDICINE.id().get());

            //Gets rolled value
            int rolled = BNumber.random(1, (int) max + 1);

            //Sends message
            if (rolled < 10)
                sendMessage(sender, RollNotification.ROLL_MEDICINE_LESS_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
            else
                sendMessage(sender, RollNotification.ROLL_MEDICINE_MORE_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
        });
    }


    /**
     * Command roll performance
     */
    @Syntax("/roll performance")
    @Subcommand("performance")
    @CommandPermission("roll.performance")
    @Description("Dices a performance roll.")
    public void onCommandRollPerformance(Player sender) {
        AttributePlayerManager.getAttributePlayer(sender.getUniqueId()).ifPresent(player -> {

            //Gets max value
            double max = 20 + player.attributePoints().get(Attribute.PERFORMANCE.id().get());

            //Gets rolled value
            int rolled = BNumber.random(1, (int) max + 1);

            //Sends message
            if (rolled < 10)
                sendMessage(sender, RollNotification.ROLL_PERFORMANCE_LESS_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
            else
                sendMessage(sender, RollNotification.ROLL_PERFORMANCE_MORE_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
        });
    }

    /**
     * Command roll lockpick
     */
    @Syntax("/roll lockpick")
    @Subcommand("lockpick")
    @CommandPermission("roll.lockpick")
    @Description("Dices a lockpick roll.")
    public void onCommandRollLockPick(Player sender) {
        AttributePlayerManager.getAttributePlayer(sender.getUniqueId()).ifPresent(player -> {

            //Gets max value
            double max = 20 + player.attributePoints().get(Attribute.LOCKPIKING.id().get());
            max += switch (sender.getInventory().getItemInMainHand().getType()) {
                case STICK -> 1;
                case IRON_INGOT -> 2;
                case NETHERITE_INGOT -> 3;
                default -> 0;
            };

            //Gets rolled value
            int rolled = BNumber.random(1, (int) max + 1);

            //Sends message
            if (rolled < 10)
                sendMessage(sender, RollNotification.ROLL_LOCKPICK_LESS_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
            else
                sendMessage(sender, RollNotification.ROLL_LOCKPICK_MORE_10.getNotification(new BPlaceHolder("%sender%", sender.getName()),
                        new BPlaceHolder("%roll%", "" + rolled), new BPlaceHolder("%max%", "" + (int) max)));
        });
    }

    private void sendMessage(@Nonnull Player sender, @Nonnull String message) {
        BValidate.notNull(sender);
        BValidate.notNull(message);

        sender.getLocation().getNearbyEntitiesByType(Player.class, 20).forEach(player -> player.sendMessage(message));
    }
}
