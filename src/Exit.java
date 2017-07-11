
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * 
 * @author rd
 */
public class Exit extends JButton implements ActionListener{

    public Exit(String t){
        super(t);
        this.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
            System.exit(0);
    }

}
