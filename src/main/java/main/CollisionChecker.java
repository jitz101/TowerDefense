package main;

import entity.Entity;

public class CollisionChecker {
    Entity entityA;
    Entity entityB;

    public CollisionChecker(Entity entityA, Entity entityB) {
        this.entityA = entityA;
        this.entityB = entityB;
    }

    public Boolean checkIntersection() {
        return entityA.hitbox.intersects(entityB.hitbox);
    }
}
