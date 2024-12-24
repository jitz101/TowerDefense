package main;

import entity.Entity;
import entity.gui.Money;

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
        Iterator<T> iterator = entityListA.iterator();

        while (iterator.hasNext()) {
            T entityA = iterator.next();

            Iterator<T> iterator2 = entityListB.iterator();
            while (iterator2.hasNext()) {
                T entityB = iterator2.next();

                if (checkIntersection(entityA, entityB)) {
                    iterator.remove();
                    iterator2.remove();
                    money.addMoney(reward);
                    break;
                }
            }
        }
    }
}
