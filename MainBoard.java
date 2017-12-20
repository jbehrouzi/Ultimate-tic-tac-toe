import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MainBoard extends JFrame {

    private final int NUMBER_OF_BlOCK=9;
    // array of Board (JPanel)
    private final Board[] blocks;
    //turn
    public Player current_play;

    public MainBoard() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        setSize(new Dimension(500,500));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // create array of board
        blocks=new Board[NUMBER_OF_BlOCK];

        setLayout(new GridLayout(3,NUMBER_OF_BlOCK/3));

        for (int i=0;i<NUMBER_OF_BlOCK;i++){
            blocks[i]=new Board(this);
            blocks[i].position=Position.valueOf(i);
            add(blocks[i]);
        }
        UIManager.setLookAndFeel(getLookAndFeelClassName("Windows"));
        current_play=Player.X;
        setVisible(true);

    }
    public static String getLookAndFeelClassName(String nameSnippet) {
        UIManager.LookAndFeelInfo[] plafs = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo info : plafs) {
            if (info.getName().contains(nameSnippet)) {
                return info.getClassName();
            }
        }
        return null;
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

        if (blocks[square1].winner!=null && blocks[square1].winner==blocks[square2].winner
                && blocks[square2].winner==blocks[square3].winner) {
            JOptionPane.showMessageDialog(this,String.format("Player %s wins ",blocks[square1].winner));
            return true;
        }

        return false;
    }
    public void disableAll(Position position){
        for (Board b: blocks){
            b.setBorder(new LineBorder(Color.black));
            b.disableAll();
        }
        enable(position);
    }
    public void enable(Position position){
        blocks[position.value].enableAll();
        blocks[position.value].setBorder(new LineBorder(Color.BLUE));

    }
}