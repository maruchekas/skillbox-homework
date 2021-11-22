import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

  public static void main(String[] args) {

    JFrame frame = new JFrame();
    frame.setSize(600, 400);
    frame.setTitle("Collapse & Expand");
    frame.add(new MainUiForm().getMainPanel());
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

  }

}
