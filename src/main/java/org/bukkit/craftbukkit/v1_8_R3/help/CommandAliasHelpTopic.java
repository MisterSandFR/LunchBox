package org.bukkit.craftbukkit.v1_8_R3.help;

import org.apache.commons.lang3.Validate;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.help.HelpMap;
import org.bukkit.help.HelpTopic;

public class CommandAliasHelpTopic extends HelpTopic {

    private final String aliasFor;
    private final HelpMap helpMap;

    public CommandAliasHelpTopic(String alias, String aliasFor, HelpMap helpMap) {
        this.aliasFor = aliasFor.startsWith("/") ? aliasFor : "/" + aliasFor;
        this.helpMap = helpMap;
        this.name = alias.startsWith("/") ? alias : "/" + alias;
        Validate.isTrue(!this.name.equals(this.aliasFor), "Command " + this.name + " cannot be alias for itself");
        this.shortText = ChatColor.YELLOW + "Alias for " + ChatColor.WHITE + this.aliasFor;
    }

    public String getFullText(CommandSender forWho) {
        StringBuilder sb = new StringBuilder(this.shortText);
        HelpTopic aliasForTopic = this.helpMap.getHelpTopic(this.aliasFor);

        if (aliasForTopic != null) {
            sb.append("\n");
            sb.append(aliasForTopic.getFullText(forWho));
        }

        return sb.toString();
    }

    public boolean canSee(CommandSender commandSender) {
        if (this.amendedPermission == null) {
            HelpTopic aliasForTopic = this.helpMap.getHelpTopic(this.aliasFor);

            return aliasForTopic != null ? aliasForTopic.canSee(commandSender) : false;
        } else {
            return commandSender.hasPermission(this.amendedPermission);
        }
    }
}
