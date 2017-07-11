
import java.awt.*;
import java.awt.event.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. 
 */
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author rd
 */
public class Setari implements ActionListener {

    public Fereastra fr = new Fereastra("Setari");
    JButton startbtn = new JButton("Start joc");
    Exit cancelbtn = new Exit("CANCEL");
    JTextField nume;
    CheckboxGroup cbg1, cbg2;
    Checkbox rosu, negru, usor, mediu, avansat;
    
    public Setari() {
        JLabel a, b, c, d;
        JPanel p1, p2, p3, p4, p5, p6, p7;
        
        d = new JLabel("SETARI JOC", JLabel.CENTER);
        d.setFont(new Font("Arial", Font.BOLD, 21));
        a = new JLabel("Numele dumneavoastra", JLabel.CENTER);
        a.setFont(new Font("Arial", Font.BOLD, 14));
        b = new JLabel("Alegeti culoarea pieselor", JLabel.CENTER);
        b.setFont(new Font("Arial", Font.BOLD, 14));
        c = new JLabel("Alegeti dificultatea jocului", JLabel.CENTER);
        c.setFont(new Font("Arial", Font.BOLD, 14));
        nume = new JTextField("Nume", 10);
        cbg1 = new CheckboxGroup();
        rosu = new Checkbox("Rosu", cbg1, false);
        negru = new Checkbox("Negru", cbg1, false);
        cbg2 = new CheckboxGroup();
        usor = new Checkbox("Usor", cbg2, false);
        mediu = new Checkbox("Mediu", cbg2, false);
        avansat = new Checkbox("Avansat", cbg2, false);
        p1 = new JPanel();
        p1.add(d, BorderLayout.CENTER);
        p2 = new JPanel();
        p2.add(a, BorderLayout.WEST);
        p2.add(nume, BorderLayout.EAST);
        p3 = new JPanel();
        p3.add(b);
        p4 = new JPanel();
        p4.add(rosu, BorderLayout.WEST);
        p4.add(negru, BorderLayout.EAST);
        p5 = new JPanel();
        p5.add(c);
        p6 = new JPanel();
        p6.add(usor, BorderLayout.WEST);
        p6.add(mediu, BorderLayout.CENTER);
        p6.add(avansat, BorderLayout.EAST);
        p7 = new JPanel();
        p7.add(startbtn, BorderLayout.WEST);
        p7.add(cancelbtn, BorderLayout.EAST);
        p1.setBackground(new Color(204, 204, 255));
        p2.setBackground(new Color(204, 204, 255));
        p3.setBackground(new Color(204, 204, 255));
        p4.setBackground(new Color(204, 204, 255));
        p5.setBackground(new Color(204, 204, 255));
        p6.setBackground(new Color(204, 204, 255));
        p7.setBackground(new Color(204, 204, 255));
        fr.setSize(680, 460);
        fr.setLayout(new GridLayout(7, 1));
        fr.add(p1);
        fr.add(p2);
        fr.add(p3);
        fr.add(p4);
        fr.add(p5);
        fr.add(p6);
        fr.add(p7);
        fr.setVisible(true);
        fr.setLocationRelativeTo(null);
        startbtn.addActionListener(this);
        
    }
    
    public void actionPerformed(ActionEvent e) {
        int op = 1;
        if (e.getSource() == startbtn) {
            if (rosu.getState() == false && negru.getState() == false) {
                op = 0;
                JOptionPane.showMessageDialog(fr, "Alegeti culoarea pieselor");
                
            }
            if (usor.getState() == false && mediu.getState() == false && avansat.getState() == false) {
                op = 0;
                JOptionPane.showMessageDialog(fr, "Alegeti nivelul de dificultate al jocului");
                
            }
            if (op == 1) {
                fr.setVisible(false);
                Tabla obt = new Tabla();
                //   obt.muta_n_computer(6, 1, 4, 3);
                //   obt.muta_n_computer(7, 2, 5, 4);
                //   obt.muta_n_computer(8, 1, 5, 8);
                // ob.muta_a(3,4,4,5);
                //    ob.muta_a(2,5,4,7);
                //  ob.muta_a(1,6,5,6);
            }
            
        }
    }
    
}
