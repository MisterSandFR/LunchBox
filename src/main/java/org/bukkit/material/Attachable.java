package org.bukkit.material;

import org.bukkit.block.BlockFace;

public interface Attachable extends Directional {

    BlockFace getAttachedFace();
}
