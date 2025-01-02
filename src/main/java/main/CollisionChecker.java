package main;

import entity.Entity;
import entity.gui.Money;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollisionChecker<T> {

    public <T extends Entity> Boolean checkIntersection(T entityA, T entityB) {
        return entityA.hitbox.intersects(entityB.hitbox);
    }

    public <T extends Entity> void checkEntityListHitEntity(List<T> entityList, T entity) {
        entityList.removeIf(entityFromList -> checkIntersection(entityFromList, entity));
    }

    public <T extends Entity> void checkEntityListHitEntityList(List<T> entityListA, List<T> entityListB, int reward, Money money) {
        List<T> toRemoveFromA = new ArrayList<>();
        List<T> toRemoveFromB = new ArrayList<>();

        for (T entityA : entityListA) {
            for (T entityB : entityListB) {
                if (checkIntersection(entityA, entityB)) {
                    toRemoveFromA.add(entityA);
                    toRemoveFromB.add(entityB);
                    money.addMoney(reward);
                    break;
                }
            }
        }

        entityListA.removeAll(toRemoveFromA);
        entityListB.removeAll(toRemoveFromB);
    }
}
