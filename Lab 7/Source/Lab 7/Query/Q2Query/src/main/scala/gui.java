import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by VenkatNag on 7/23/2017.
 */
public class gui extends JApplet implements ActionListener {
    JButton button;
    JTextField text;
    JLabel l;
    String ans="";
    public void init() {
        this.setSize(400, 400);
        this.add(getCustPanel());
        this.setVisible(true);
    }

    private JPanel getCustPanel() {
        JPanel panel = new JPanel();
      //  panel.setLayout ((LayoutManager) new BoxLayout(panel, BoxLayout.Y_AXIS));
        text=new JTextField();
        text.setPreferredSize( new Dimension( 400, 25 ) );
        text.setToolTipText("Please enter your question");
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
        button = new JButton("Ask");
        button.setPreferredSize(new Dimension(100, 20));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(this);
        panel.add(text);
        panel.add(button);
        l=new JLabel();
        text.setPreferredSize( new Dimension( 400, 25 ) );
        l.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(l);
        return panel;
    }

    public void actionPerformed(ActionEvent e) {
            try {
                Question2Query q = new Question2Query();
                ans = q.call(text.getText());
                l.setText("\n"+ans);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

    }
}
