package org.bukkit.craftbukkit.v1_8_R3.metadata;

import org.bukkit.entity.Entity;
import org.bukkit.metadata.MetadataStore;
import org.bukkit.metadata.MetadataStoreBase;

public class EntityMetadataStore extends MetadataStoreBase<Entity> implements MetadataStore<Entity> {
    @Override
    protected String disambiguate(Entity entity, String metadataKey) {
        return entity.getUniqueId().toString() + ":" + metadataKey;
    }
}
