package org.bukkit.event;

public interface Cancellable {

    boolean isCancelled();

    void setCancelled(boolean flag);
}
