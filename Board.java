import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {
    private final int NUMBER_OF_BlOCK = 9;
    private final Box[] blocks;
    private MainBoard mainBoard;
    public Player winner;
    Position position;

    public Board(MainBoard mainBoard) {
        blocks = new Box[NUMBER_OF_BlOCK];
        setLayout(new GridLayout(3, NUMBER_OF_BlOCK / 3));
        for (int i = 0; i < NUMBER_OF_BlOCK; i++) {
            blocks[i] = new Box("");
            blocks[i].position = Position.valueOf(i);
            blocks[i].addActionListener(this);
            add(blocks[i]);
        }
        setBorder(new LineBorder(Color.BLACK));
        this.mainBoard = mainBoard;

    }

    public boolean evaluateState() {
        for (int horizontal = 0, vertical = 0; horizontal < NUMBER_OF_BlOCK; horizontal += 3) {
            if (checkSet(vertical, vertical + 3, vertical++ + 6)
                    || checkSet(horizontal, horizontal + 1, horizontal + 2)) {
                return true;
            }
        }
        if (checkSet(0, 4, 8) || checkSet(2, 4, 6)) {
            return true;
        }
        return false;

    }

    private boolean checkSet(int square1, int square2, int square3) {

        if (!blocks[square1].getText().equals("") && blocks[square1].getText().equals(blocks[square2].getText())
                && blocks[square2].getText().equals(blocks[square3].getText())) {
            winner = blocks[square1].owner;
            return true;
        }

        return false;
    }


    public void actionPerformed(ActionEvent e) {
        Box b = (Box) e.getSource();
        if (mainBoard.current_play == Player.X) {
            b.setText("X");
            b.owner = Player.X;
            mainBoard.current_play = Player.O;

        } else {
            b.setText("O");
            mainBoard.current_play = Player.X;
            b.owner = Player.O;
        }

        b.setForeground(b.owner.getColor());
        b.removeActionListener(this);
        if (evaluateState()) {

            for (Box box : blocks) {
                box.removeActionListener(this);
                if (winner == box.owner) {
                    box.setForeground(Color.BLACK);
                }
                box.setBackground(winner.getColor());
            }
            mainBoard.evaluateState();

        }
        mainBoard.disableAll(b.position);
    }

    public void disableAll() {
        for (Box b : blocks){
            b.removeActionListener(this);
        }
    }

    public void enableAll() {
        for (Box b : blocks){
            if(b.owner==null){
                b.addActionListener(this);
            }
        }
    }

}
