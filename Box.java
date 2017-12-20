import javafx.geometry.Pos;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Box extends JButton {
    Position position;
    Player owner;

    public Box(String s) {
        super(s);
    }
}

enum Position {
    TOP_LEFT(0),
    TOP_MIDDLE(1),
    TOP_RIGHT(2),
    MIDDLE_LEFT(3),
    MIDDLE(4),
    MIDDLE_RIGHT(5),
    BOTTOM_LEFT(6),
    BOTTOM_MIDDLE(7),
    BOTTOM_RIGHT(8);

    int value;
    private static HashMap<Integer, Position> map = new HashMap<Integer, Position>();

    Position(int i) {
        value = i;
    }

    static {
        for (Position pos : Position.values()) {
            map.put(pos.value, pos);
        }
    }

    public static Position valueOf(int position) {
        return map.get(position);
    }
}