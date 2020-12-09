package entities.character.enemy.ai;

import app.management.map.Direction;
import entities.character.Player;

public class AILow extends AI {
    @Override
    public Direction calculateDirection() {
        //thuat toan tim duong
        int ans = random.nextInt(4);
        if (ans == 0) return Direction.UP;
        if (ans == 1) return Direction.DOWN;
        if (ans == 2) return Direction.LEFT;
        return Direction.RIGHT;
    }

    public Direction nearbyPlayer(Player player) {
        return player.getDirection();
    }
}
