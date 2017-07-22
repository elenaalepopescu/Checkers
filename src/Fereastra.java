
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

public class Fereastra extends JFrame implements WindowListener{

    public Fereastra(String t){
        super(t);
        this.addWindowListener(this);
    }
           

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
        
    }

    public void windowDeactivated(WindowEvent e) {
        
    }



}
