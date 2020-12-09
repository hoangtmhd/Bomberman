package entities.character.enemy.ai;

import app.management.map.Direction;
import entities.character.Player;
import entities.character.enemy.Enemy;

public class AIMedium extends AI {
    Player _player;
    Enemy _e;

    public AIMedium(Player player, Enemy e) {
        _player = player;
        _e = e;
    }

    @Override
    public Direction calculateDirection() {
        //thuat toan tim duong di
        int ans;
        if(_player == null)
            ans = random.nextInt(4);
        else {
            int vertical = random.nextInt(2);

            if(vertical == 1) {
                int v = calculateRowDirection();
                if(v != -1)
                    ans = v;
                else
                    ans = calculateColDirection();
            }
            else {
                int h = calculateColDirection();
                if(h != -1)
                    ans = h;
                else
                    ans = calculateRowDirection();
            }
        }

        if (ans == 0) return Direction.UP;
        if (ans == 1) return Direction.DOWN;
        if (ans == 2) return Direction.LEFT;
        return Direction.RIGHT;
    }

    protected int calculateColDirection() {
        if(_player.getHitBox().getX() < _e.getHitBox().getX())
            return 2;
        else if(_player.getHitBox().getX() > _e.getHitBox().getX())

            return 3;

        return -1;
    }

    protected int calculateRowDirection() {
        if(_player.getHitBox().getY() < _e.getHitBox().getY())
            return 1;
        else if(_player.getHitBox().getY() > _e.getHitBox().getY())
            return 0;
        return -1;
    }
}
