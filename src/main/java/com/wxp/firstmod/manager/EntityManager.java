package com.wxp.firstmod.manager;

import com.wxp.firstmod.entity.GoldenChickenEntity;
import net.minecraftforge.fml.common.registry.EntityEntry;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/** @author wxp */
public class EntityManager {
  private static int registerCount = 0;
  private static Map<Class, Integer> entityIdMap;
  private static Map<Class, EntityEntry> entities;

  public static void initEntity() {
    entityIdMap = new HashMap<>(5);
    entities = new LinkedHashMap<>();

    entities.put(GoldenChickenEntity.class, GoldenChickenEntity.getEntity());
  }

  public static Collection<EntityEntry> getInitializedEntity() {
    return entities.values();
  }

  public static int getIdByClass(Class entityClass) {
    if (entityIdMap.containsKey(entityClass)) {
      return entityIdMap.get(entityClass);
    }
    registerCount++;
    entityIdMap.put(entityClass, registerCount);
    return registerCount;
  }
}
