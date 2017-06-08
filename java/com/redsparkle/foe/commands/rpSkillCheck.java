package com.redsparkle.foe.commands;

import com.redsparkle.foe.capa.spechial.SpechialFactoryProvider;
import net.minecraft.command.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hoijima on 27.05.17.
 */
public class rpSkillCheck extends CommandBase {


    private final List<String> aliases;

    protected String fullEntityName;

    public rpSkillCheck() {
        aliases = new ArrayList<String>();
        aliases.add("skilldice");
        aliases.add("Sdice");
        aliases.add("diceskill");
        aliases.add("ds");
    }


    public String[] skills = new String[]{
            "STR",
            "PER",
            "END",
            "CHA",
            "INT",
            "AGI",
            "LUC"
    };

    @Override
    public String getName() {
        return "skilldice";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/skilldice Skillname PLAYER1 PLAYER2";
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {


        if (args.length < 3
                ) {
            throw new WrongUsageException("Thats now you use that command silly!");
        } else {

            Entity player2 = getEntity(server, sender, args[2]);
            EntityPlayer playerE1 = getPlayer(server,sender,args[1]);
            EntityPlayer playerE2 = getPlayer(server,sender,args[2]);

            Integer[] player1Skills = new Integer[]{
                    SpechialFactoryProvider.instanceFor(playerE1).getStreinght(),
                    SpechialFactoryProvider.instanceFor(playerE1).getPerception(),
                    SpechialFactoryProvider.instanceFor(playerE1).getEndurance(),
                    SpechialFactoryProvider.instanceFor(playerE1).getCharisma(),
                    SpechialFactoryProvider.instanceFor(playerE1).getIntelligence(),
                    SpechialFactoryProvider.instanceFor(playerE1).getAgility(),
                    SpechialFactoryProvider.instanceFor(playerE1).getLuck()
            };
            Integer[] player2Skills = new Integer[]{
                    SpechialFactoryProvider.instanceFor(playerE2).getStreinght(),
                    SpechialFactoryProvider.instanceFor(playerE2).getPerception(),
                    SpechialFactoryProvider.instanceFor(playerE2).getEndurance(),
                    SpechialFactoryProvider.instanceFor(playerE2).getCharisma(),
                    SpechialFactoryProvider.instanceFor(playerE2).getIntelligence(),
                    SpechialFactoryProvider.instanceFor(playerE2).getAgility(),
                    SpechialFactoryProvider.instanceFor(playerE2).getLuck()
            };


            ITextComponent youSuck = new TextComponentString(" skill is lower than yours");
            ITextComponent youRule = new TextComponentString(" skill is higher than yours");
            ITextComponent samelvl = new TextComponentString(" skill is the same as yours");

            youSuck.getStyle().setColor(TextFormatting.RED).setItalic(Boolean.valueOf(true));
            youRule.getStyle().setColor(TextFormatting.GREEN).setItalic(Boolean.valueOf(true));
            samelvl.getStyle().setColor(TextFormatting.GRAY).setItalic(Boolean.valueOf(true));


            for(int i=0;i <= (skills.length -1);i++){

                if(skills[i].equals(args[0])){
                        if (player1Skills[i] < player2Skills[i]) {

                            player2.sendMessage(new TextComponentString(sender.getName()+"'s" + " " +skills[i]+ youSuck.getFormattedText()));
                            sender.sendMessage(new TextComponentString(player2.getName()+"'s" + " " +skills[i]+ youRule.getFormattedText()));

                        } else if (player1Skills[i] > player2Skills[i]) {

                            player2.sendMessage(new TextComponentString(sender.getName()+"'s" + " " +skills[i]+ youSuck.getFormattedText()));
                            sender.sendMessage(new TextComponentString(player2.getName()+"'s" + " " +skills[i]+ youRule.getFormattedText()));

                        } else if (player1Skills[i] == player2Skills[i]) {

                            TextComponentString toPlayer1 = new TextComponentString(sender.getName()+"'s" + " " +skills[i]+samelvl.getFormattedText());
                            TextComponentString toPlayer2 = new TextComponentString(player2.getName()+"'s" + " " +skills[i]+samelvl.getFormattedText());


                            player2.sendMessage(toPlayer1);
                            sender.sendMessage(toPlayer2);

                        }
                }
            }




        }


    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
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
