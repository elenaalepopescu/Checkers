
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JLabel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.JPanel;

/**
 *
 * @author rd
 */
public class Intro implements ActionListener{
    public Fereastra fr=new Fereastra("Intro");
    JButton btnOk = new JButton("OK");
    Exit btnCancel= new Exit("CANCEL");
    public Setari ob;
    public Intro(){
        JLabel a,b,c;
        a = new JLabel("Dame Thailandeze",JLabel.CENTER);
        a.setFont(new Font("Arial",Font.BOLD,21));
        b = new JLabel("Apasati OK pentru a intra in joc sau CANCEL pentru a iesi din joc!",JLabel.CENTER);
        b.setFont(new Font("Arial",Font.ROMAN_BASELINE,14));
        c = new JLabel("Realizatori: Stoica Christofer, Fraticiu Larisa, Petri Casian",JLabel.CENTER);
        c.setFont(new Font("Arial",Font.ROMAN_BASELINE,14));
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        p1.add(a,BorderLayout.CENTER);
        p1.setBackground(new Color(204, 204, 255));
        p2.add(b,BorderLayout.CENTER); 
        p2.setBackground(new Color(204, 204, 255));
        p3.add(btnOk,BorderLayout.EAST);
        p3.setBackground(new Color(204, 204, 255));
        p3.add(btnCancel,BorderLayout.WEST);
        p4.add(c,BorderLayout.SOUTH);
        p4.setBackground(new Color(204, 204, 255));
        fr.setSize(680,460);
        fr.setLayout(new GridLayout(4,1));
        fr.add(p1);
        fr.add(p2);
        fr.add(p3);
        fr.add(p4);
        fr.setVisible(true);
        fr.setLocationRelativeTo(null);
        btnOk.addActionListener(this);
        
    }
 
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnOk){
            fr.setVisible(false);
            ob=new Setari();
            
            
        }
    }

}
