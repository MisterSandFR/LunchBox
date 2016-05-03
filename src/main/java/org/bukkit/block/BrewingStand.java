package org.bukkit.block;

import org.bukkit.inventory.BrewerInventory;

public interface BrewingStand extends BlockState, ContainerBlock {

    int getBrewingTime();

    void setBrewingTime(int i);

    BrewerInventory getInventory();
}
