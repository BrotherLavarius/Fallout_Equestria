package com.redsparkle.foe.commands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.redsparkle.foe.capa.spechial.SpechialFactoryProvider.SPECHIAL_CAPABILITY;
import static net.minecraft.command.CommandBase.*;

/**
 * Created by hoijima on 27.05.17.
 */
public class rpSkillCheck implements ICommand {


    private final List aliases;

    protected String fullEntityName;

    public rpSkillCheck() {
        aliases = new ArrayList();
        aliases.add("skilldice");
        aliases.add("Sdice");
        aliases.add("diceskill");
        aliases.add("ds");
    }

    @Override
    public String getName() {
        return "skilldice";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/skilldice <text> <text> <text>";
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 4) {
            throw new WrongUsageException("Thats now you use that command silly!");
        } else {

            Entity player1 = getEntity(server, sender, args[1]);
            Entity player2 = getEntity(server, sender, args[2]);


            ITextComponent youSuck = new TextComponentString("Skill is lower than yours");
            ITextComponent youRule = new TextComponentString("Skill is higher than yours");

            youSuck.getStyle().setColor(TextFormatting.RED).setItalic(Boolean.valueOf(true));
            youRule.getStyle().setColor(TextFormatting.GREEN).setItalic(Boolean.valueOf(true));

            if ("STR".equals(args[0])) {
                if (player1.getCapability(SPECHIAL_CAPABILITY, null).getStreinght() <
                        player2.getCapability(SPECHIAL_CAPABILITY, null).getStreinght()) {


                    player2.sendMessage(new TextComponentString(sender.getDisplayName() + " " + youSuck.getFormattedText()));
                    sender.sendMessage(new TextComponentString(player2.getDisplayName() + " " + youRule.getFormattedText()));

                } else if (player1.getCapability(SPECHIAL_CAPABILITY, null).getStreinght() >
                        player2.getCapability(SPECHIAL_CAPABILITY, null).getStreinght()) {

                } else if (player1.getCapability(SPECHIAL_CAPABILITY, null).getStreinght() ==
                        player2.getCapability(SPECHIAL_CAPABILITY, null).getStreinght()) {

                }
            } else if ("PER".equals(args[0])) {
                if (player1.getCapability(SPECHIAL_CAPABILITY, null).getPerception() <
                        player2.getCapability(SPECHIAL_CAPABILITY, null).getPerception()) {
                    player2.sendMessage(new TextComponentString(sender.getDisplayName() + " " + youSuck.getFormattedText()));
                    sender.sendMessage(new TextComponentString(player2.getDisplayName() + " " + youRule.getFormattedText()));
                } else if (player1.getCapability(SPECHIAL_CAPABILITY, null).getPerception() >
                        player2.getCapability(SPECHIAL_CAPABILITY, null).getPerception()) {

                } else if (player1.getCapability(SPECHIAL_CAPABILITY, null).getPerception() ==
                        player2.getCapability(SPECHIAL_CAPABILITY, null).getPerception()) {

                }
            } else if ("END".equals(args[0])) {
                if (player1.getCapability(SPECHIAL_CAPABILITY, null).getEndurance() <
                        player2.getCapability(SPECHIAL_CAPABILITY, null).getEndurance()) {
                    player2.sendMessage(new TextComponentString(sender.getDisplayName() + " " + youSuck.getFormattedText()));
                    sender.sendMessage(new TextComponentString(player2.getDisplayName() + " " + youRule.getFormattedText()));
                } else if (player1.getCapability(SPECHIAL_CAPABILITY, null).getEndurance() >
                        player2.getCapability(SPECHIAL_CAPABILITY, null).getEndurance()) {

                } else if (player1.getCapability(SPECHIAL_CAPABILITY, null).getEndurance() ==
                        player2.getCapability(SPECHIAL_CAPABILITY, null).getEndurance()) {

                }
            } else if ("CHA".equals(args[0])) {
                if (player1.getCapability(SPECHIAL_CAPABILITY, null).getCharisma() <
                        player2.getCapability(SPECHIAL_CAPABILITY, null).getCharisma()) {
                    player2.sendMessage(new TextComponentString(sender.getDisplayName() + " " + youSuck.getFormattedText()));
                    sender.sendMessage(new TextComponentString(player2.getDisplayName() + " " + youRule.getFormattedText()));
                } else if (player1.getCapability(SPECHIAL_CAPABILITY, null).getCharisma() >
                        player2.getCapability(SPECHIAL_CAPABILITY, null).getCharisma()) {

                } else if (player1.getCapability(SPECHIAL_CAPABILITY, null).getCharisma() ==
                        player2.getCapability(SPECHIAL_CAPABILITY, null).getCharisma()) {

                }
            } else if ("INT".equals(args[0])) {
                if (player1.getCapability(SPECHIAL_CAPABILITY, null).getIntelligence() <
                        player2.getCapability(SPECHIAL_CAPABILITY, null).getIntelligence()) {
                    player2.sendMessage(new TextComponentString(sender.getDisplayName() + " " + youSuck.getFormattedText()));
                    sender.sendMessage(new TextComponentString(player2.getDisplayName() + " " + youRule.getFormattedText()));
                } else if (player1.getCapability(SPECHIAL_CAPABILITY, null).getIntelligence() >
                        player2.getCapability(SPECHIAL_CAPABILITY, null).getIntelligence()) {

                } else if (player1.getCapability(SPECHIAL_CAPABILITY, null).getIntelligence() ==
                        player2.getCapability(SPECHIAL_CAPABILITY, null).getIntelligence()) {

                }
            } else if ("AGI".equals(args[0])) {
                if (player1.getCapability(SPECHIAL_CAPABILITY, null).getAgility() <
                        player2.getCapability(SPECHIAL_CAPABILITY, null).getAgility()) {
                    player2.sendMessage(new TextComponentString(sender.getDisplayName() + " " + youSuck.getFormattedText()));
                    sender.sendMessage(new TextComponentString(player2.getDisplayName() + " " + youRule.getFormattedText()));
                } else if (player1.getCapability(SPECHIAL_CAPABILITY, null).getAgility() >
                        player2.getCapability(SPECHIAL_CAPABILITY, null).getAgility()) {

                } else if (player1.getCapability(SPECHIAL_CAPABILITY, null).getAgility() ==
                        player2.getCapability(SPECHIAL_CAPABILITY, null).getAgility()) {

                }
            } else if ("LUC".equals(args[0])) {
                if (player1.getCapability(SPECHIAL_CAPABILITY, null).getLuck() <
                        player2.getCapability(SPECHIAL_CAPABILITY, null).getLuck()) {
                    player2.sendMessage(new TextComponentString(sender.getDisplayName() + " " + youSuck.getFormattedText()));
                    sender.sendMessage(new TextComponentString(player2.getDisplayName() + " " + youRule.getFormattedText()));
                } else if (player1.getCapability(SPECHIAL_CAPABILITY, null).getLuck() >
                        player2.getCapability(SPECHIAL_CAPABILITY, null).getLuck()) {

                } else if (player1.getCapability(SPECHIAL_CAPABILITY, null).getLuck() ==
                        player2.getCapability(SPECHIAL_CAPABILITY, null).getLuck()) {

                }
            }


        }


    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return false;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : (args.length > 1 && args.length <= 4 ? getTabCompletionCoordinate(args, 1, targetPos) : Collections.emptyList());
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }
}
