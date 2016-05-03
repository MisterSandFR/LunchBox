package org.bukkit.event.entity;

import org.bukkit.entity.Item;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

public class ItemMergeEvent extends EntityEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final Item target;

    public ItemMergeEvent(Item item, Item target) {
        super(item);
        this.target = target;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Item getEntity() {
        return (Item) this.entity;
    }

    public Item getTarget() {
        return this.target;
    }

    public HandlerList getHandlers() {
        return ItemMergeEvent.handlers;
    }

    public static HandlerList getHandlerList() {
        return ItemMergeEvent.handlers;
    }
}
