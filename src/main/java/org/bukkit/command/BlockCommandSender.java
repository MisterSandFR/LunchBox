package org.bukkit.command;

import org.bukkit.block.Block;

public interface BlockCommandSender extends CommandSender {

    Block getBlock();
}
