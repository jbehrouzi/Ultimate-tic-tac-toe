import java.awt.*;

public enum Player {
    X(66,244,155),
    O(244,122,66);
    private Color color;
    Player(int r,int g,int b){
        color=new Color(r, g, b);
    }

    public Color getColor() {
        return color;
    }
}
