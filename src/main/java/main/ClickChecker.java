package main;

import entity.Entity;

import java.awt.*;

public class ClickChecker {
    public <T extends Entity> Boolean checkClickedOn(T entity, Point clickPosition) {
        return entity.hitbox.contains(clickPosition);
    }
}
