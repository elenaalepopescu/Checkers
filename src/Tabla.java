import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Tabla implements ActionListener {

    public Patrat[][] A = new Patrat[9][9];
    public Patrat[][] A1 = new Patrat[9][9];
    public JButton[][] a = new JButton[9][9];
    public ImageIcon rnd = new ImageIcon("sageata.png");
    public JButton mn = new JButton(rnd);
    public JButton mc = new JButton();
    JPanel p = new JPanel();
    public int i, j, k, l, tx = 0, cnt = 0;
    public ImageIcon piesaRosu = new ImageIcon("white.png");
    public ImageIcon piesaNegru = new ImageIcon("black.png");
    public ImageIcon regeRosu = new ImageIcon("whiteKing.png");
    public ImageIcon regeNegru = new ImageIcon("blackKing.png");
    public int nr = 0, regi_a = 0, regi_n = 0, piese_a = 0, piese_n = 0;  //scor_nume=0,scor_computer=0,egal=0;
    public boolean rand = true, max = true;
    public Fereastra fereastraPrincipala = new Fereastra("Tabla de joc");
    JLabel nume, computer, punctaj, piese, regi, punctajn, punctajc, piesen, piesec, regin, regic, vid, cine_muta;
    JPanel p1 = new JPanel();
    public static Setari ex;
    Mutare x = new Mutare();
    Mutare[] muta = new Mutare[96];
    Mutare Mm = new Mutare();

    public Tabla() {
        fereastraPrincipala.setSize(700, 900);
        p.setLayout(new GridLayout(8, 8));
        fereastraPrincipala.setLocationRelativeTo(null);
        for (i = 1; i <= 8; i++)
            for (j = 1; j <= 8; j++) {
                if ((i + j) % 2 == 0) {
                    a[i][j] = new JButton();
                    A[i][j] = new Patrat();
                    A[i][j].piesa = false;
                    A[i][j].culoare = -1;
                    A[i][j].rege = false;
                    a[i][j].setBackground(new Color(236, 242, 249));
                    a[i][j].setEnabled(false);
                    p.add(a[i][j]);
                } else {
                    if ((i <= 2) || (i >= 7)) {
                        if (i <= 3) {
                            a[i][j] = new JButton(piesaRosu);
                            A[i][j] = new Patrat();
                            A[i][j].piesa = true;
                            A[i][j].culoare = 0;
                            A[i][j].rege = false;
                            a[i][j].setBackground(Color.gray);
                            p.add(a[i][j]);
                        } else {
                            a[i][j] = new JButton(piesaNegru);
                            A[i][j] = new Patrat();
                            A[i][j].piesa = true;
                            A[i][j].culoare = 1;
                            A[i][j].rege = false;
                            a[i][j].setBackground(Color.gray);
                            p.add(a[i][j]);
                        }
                    } else {
                        a[i][j] = new JButton();
                        A[i][j] = new Patrat();
                        A[i][j].piesa = false;
                        A[i][j].culoare = -1;
                        A[i][j].rege = false;
                        a[i][j].setBackground(Color.gray);
                        p.add(a[i][j]);
                    }
                }

            }

        String FondText = "Arial";
        nume = new JLabel(ex.nume.getText(), JLabel.CENTER);
        nume.setFont(new Font(FondText, Font.BOLD, 14));
        computer = new JLabel("Computer", JLabel.CENTER);
        computer.setFont(new Font(FondText, Font.BOLD, 14));
        //punctaj = new JLabel("Scor",JLabel.CENTER);
        //punctaj.setFont(new Font(FondText,Font.BOLD,14));
        piese = new JLabel("Numar piese", JLabel.CENTER);
        piese.setFont(new Font(FondText, Font.BOLD, 14));
        regi = new JLabel("Numar regi", JLabel.CENTER);
        regi.setFont(new Font(FondText, Font.BOLD, 14));
        //punctajn = new JLabel("0",JLabel.CENTER);
        //punctajn.setFont(new Font(FondText,Font.BOLD,14));
        //punctajc = new JLabel("0",JLabel.CENTER);
        //punctajc.setFont(new Font(FondText,Font.BOLD,14));
        piesen = new JLabel("8", JLabel.CENTER);
        piesen.setFont(new Font(FondText, Font.BOLD, 14));
        piesec = new JLabel("8", JLabel.CENTER);
        piesec.setFont(new Font(FondText, Font.BOLD, 14));
        regin = new JLabel("0", JLabel.CENTER);
        regin.setFont(new Font(FondText, Font.BOLD, 14));
        regic = new JLabel("0", JLabel.CENTER);
        regic.setFont(new Font(FondText, Font.BOLD, 14));
        vid = new JLabel("", JLabel.CENTER);

        mn.setBackground(new Color(204, 204, 255));
        mn.setBorder(null);

        mc.setBackground(new Color(204, 204, 255));
        mc.setBorder(null);
        cine_muta = new JLabel("Jucatorul care muta", JLabel.CENTER);
        cine_muta.setFont(new Font("Arial", Font.BOLD, 14));
        p1.setLayout(new GridLayout(5, 3));
        p1.add(cine_muta);
        p1.add(mn);
        p1.add(mc);
        p1.add(vid);
        p1.add(nume);
        p1.add(computer);
        //p1.add(punctaj);
        //p1.add(punctajn);
        //p1.add(punctajc);
        p1.add(piese);
        p1.add(piesen);
        p1.add(piesec);
        p1.add(regi);
        p1.add(regin);
        p1.add(regic);
        p1.setBackground(new Color(204, 204, 255));

        Exit close = new Exit("Close");
        JPanel p2 = new JPanel();
        p2.add(close);
        p2.setBackground(new Color(204, 204, 255));
        fereastraPrincipala.setBackground(Color.BLUE);
        fereastraPrincipala.add(p, BorderLayout.NORTH);
        fereastraPrincipala.add(p1, BorderLayout.CENTER);
        fereastraPrincipala.add(p2, BorderLayout.SOUTH);
        fereastraPrincipala.show();

        for (i = 1; i <= 8; i++)
            for (j = 1; j <= 8; j++) {
                a[i][j].addActionListener(this);
            }
    }

    public void randn() {
        rand = true;
        mn.setIcon(rnd);
        mn.setBackground(new Color(204, 204, 255));
        mn.setBorder(null);
        mc.setIcon(null);
        mc.setBackground(new Color(204, 204, 255));
        mc.setBorder(null);
    }

    public void randc() {
        rand = false;
        mn.setIcon(null);
        mn.setBackground(new Color(204, 204, 255));
        mn.setBorder(null);
        mc.setIcon(rnd);
        mc.setBackground(new Color(204, 204, 255));
        mc.setBorder(null);
    }

    public int rang(int i, int j) {
        int rng = 0;
        if (ex.alb.getState()) {
            if (A1[i][j].rege == false)
                rng = i - 1;
            else
                rng = 0;
        } else {
            if (A1[i][j].rege == false)
                rng = 8 - i;
            else
                rng = 0;
        }
        return
                rng;
    }

    public int evaluare(int cul) {
        int pa, pn, ra, rn, ci, cj;
        int val;
        val = 0;
        pa = 0;
        pn = 0;
        ra = 0;
        rn = 0;
        for (ci = 1; ci <= 8; ci++)
            for (cj = 1; cj <= 8; cj++) {
                if (A1[ci][cj].piesa == true && A1[ci][cj].culoare == 0 && A1[ci][cj].rege == false)
                    pa = pa + 100 + rang(ci, cj) * rang(ci, cj);

                if (A1[ci][cj].piesa == true && A1[ci][cj].culoare == 1 && A1[ci][cj].rege == false)
                    pn = pn + 100 + rang(ci, cj) * rang(ci, cj);

                if (A1[ci][cj].rege == true && A1[ci][cj].culoare == 0 && (ci == 1 || ci == 8 || cj == 1 || cj == 8)) {
                    ra = ra + 150;
                }
                if (A1[ci][cj].rege == true && A1[ci][cj].culoare == 0 && ci != 1 && ci != 8 && cj != 1 && cj != 8) {
                    ra = ra + 175;
                }
                if (A1[ci][cj].rege == true && A1[ci][cj].culoare == 1 && (ci == 1 || ci == 8 || cj == 1 || cj == 8)) {
                    rn = rn + 150;
                }
                if (A1[ci][cj].rege == true && A1[ci][cj].culoare == 1 && ci != 1 && ci != 8 && cj != 1 && cj != 8) {
                    rn = rn + 175;
                }
            }
        if (cul == 1)
            val = pn + rn - pa - ra;
        if (cul == 0)
            val = pa + ra - pn - rn;
        return val;
    }

    public int evaluare1(Patrat[][] A1, int cul) {
        int pa, pn, ra, rn, ci, cj;
        int val;
        val = 0;
        pa = 0;
        pn = 0;
        ra = 0;
        rn = 0;
        for (ci = 1; ci <= 8; ci++)
            for (cj = 1; cj <= 8; cj++) {
                if (A1[ci][cj].piesa == true && A1[ci][cj].culoare == 0 && A1[ci][cj].rege == false)
                    pa = pa + 100 + rang(ci, cj) * rang(ci, cj);

                if (A1[ci][cj].piesa == true && A1[ci][cj].culoare == 1 && A1[ci][cj].rege == false)
                    pn = pn + 100 + rang(ci, cj) * rang(ci, cj);

                if (A1[ci][cj].rege == true && A1[ci][cj].culoare == 0 && (ci == 1 || ci == 8 || cj == 1 || cj == 8)) {
                    ra = ra + 150;
                }
                if (A1[ci][cj].rege == true && A1[ci][cj].culoare == 0 && ci != 1 && ci != 8 && cj != 1 && cj != 8) {
                    ra = ra + 175;
                }
                if (A1[ci][cj].rege == true && A1[ci][cj].culoare == 1 && (ci == 1 || ci == 8 || cj == 1 || cj == 8)) {
                    rn = rn + 150;
                }
                if (A1[ci][cj].rege == true && A1[ci][cj].culoare == 1 && ci != 1 && ci != 8 && cj != 1 && cj != 8) {
                    rn = rn + 175;
                }
            }
        if (cul == 1)
            val = pn + rn - pa - ra;
        if (cul == 0)
            val = pa + ra - pn - rn;
        return val;
    }

    public void muta_a_nume(int i1, int j1, int k1, int l1) {
        int ci, cj, ct, sw = 1, ind = 0;
        //int pct_a=0,pct_n=0;
        regi_a = 0;
        regi_n = 0;
        piese_a = 0;
        piese_n = 0;
        a[i1][j1].setIcon(null);
        a[k1][l1].setIcon(piesaRosu);
        A[i1][j1].piesa = false;
        A[k1][l1].piesa = true;
        A[i1][j1].culoare = -1;
        A[k1][l1].culoare = 0;
        if (k1 == 8 || A[i1][j1].rege == true) {
            a[k1][l1].setIcon(regeRosu);
            A[k1][l1].rege = true;
            A[i1][j1].rege = false;
        }
        for (ci = 1; ci <= 8; ci++)
            for (cj = 1; cj <= 8; cj++) {
                if (A[ci][cj].piesa == true && A[ci][cj].culoare == 0)
                    piese_a++;
                if (A[ci][cj].rege == true && A[ci][cj].culoare == 0)
                    regi_a++;
                if (A[ci][cj].piesa == true && A[ci][cj].culoare == 1)
                    piese_n++;
                if (A[ci][cj].rege == true && A[ci][cj].culoare == 1)
                    regi_n++;
            }
        //pct_a = piese_a * 10 + regi_a * 17;
        //pct_n = piese_n * 10 + regi_n * 17;
        if (ex.alb.getState()) {
            piesen.setText(Integer.toString(piese_a));
            piesec.setText(Integer.toString(piese_n));
            regin.setText(Integer.toString(regi_a));
            regic.setText(Integer.toString(regi_n));
            // punctajn.setText(Integer.toString(pct_a));
            //punctajc.setText(Integer.toString(pct_n));
        }
        if (ex.negru.getState()) {
            piesen.setText(Integer.toString(piese_n));
            piesec.setText(Integer.toString(piese_a));
            regin.setText(Integer.toString(regi_n));
            regic.setText(Integer.toString(regi_a));
            //punctajn.setText(Integer.toString(pct_n));
            //punctajc.setText(Integer.toString(pct_a));
        }

        for (ci = 1; ci <= 8; ci++)
            for (cj = 1; cj <= 8; cj++) {
                A1[ci][cj] = new Patrat();
                A1[ci][cj].piesa = A[ci][cj].piesa;
                A1[ci][cj].culoare = A[ci][cj].culoare;
                A1[ci][cj].rege = A[ci][cj].rege;
            }
        for (ci = 1; ci <= 8; ci++)
            for (cj = 1; cj <= 8; cj++) {
                if (A1[ci][cj].piesa == true && A1[ci][cj].culoare == 1) {
                    ind++;
                    ct = mutari_disponibile_n(ci, cj);
                    if (ct == 0)
                        sw = 0;
                }
            }
        if (sw == 1 && ind > 0) {
            JOptionPane.showMessageDialog(fereastraPrincipala, "Ai castigat ! Computerul a ramas fara mutari valide !");
            for (ci = 1; ci <= 8; ci++)
                for (cj = 1; cj <= 8; cj++) {
                    a[ci][cj].setEnabled(false);
                }
        } else {
            if (egal == 100) {
                JOptionPane.showMessageDialog(fereastraPrincipala, "Jocul este declarat remiza deoarece sau facut 50 de miscari fara capturare!");
                for (ci = 1; ci <= 8; ci++)
                    for (cj = 1; cj <= 8; cj++) {
                        a[ci][cj].setEnabled(false);
                    }
            }

            randc();
            muta_computer();
        }
    }

    public void muta_n_nume(int i1, int j1, int k1, int l1) {
        int ci, cj, ct, sw = 1, ind = 0;
        //int pct_a=0,pct_n=0;
        regi_a = 0;
        regi_n = 0;
        piese_a = 0;
        piese_n = 0;
        a[i1][j1].setIcon(null);
        a[k1][l1].setIcon(piesaNegru);
        A[i1][j1].piesa = false;
        A[k1][l1].piesa = true;
        A[i1][j1].culoare = -1;
        A[k1][l1].culoare = 1;
        if (k1 == 1 || A[i1][j1].rege == true) {
            A[k1][l1].rege = true;
            A[i1][j1].rege = false;
            a[k1][l1].setIcon(regeNegru);
        }
        for (ci = 1; ci <= 8; ci++)
            for (cj = 1; cj <= 8; cj++) {
                if (A[ci][cj].piesa == true && A[ci][cj].culoare == 0)
                    piese_a++;
                if (A[ci][cj].rege == true && A[ci][cj].culoare == 0)
                    regi_a++;
                if (A[ci][cj].piesa == true && A[ci][cj].culoare == 1)
                    piese_n++;
                if (A[ci][cj].rege == true && A[ci][cj].culoare == 1)
                    regi_n++;
            }
        //pct_a = piese_a * 10 + regi_a * 17;
        //pct_n = piese_n * 10 + regi_n * 17;
        if (ex.alb.getState()) {
            piesen.setText(Integer.toString(piese_a));
            piesec.setText(Integer.toString(piese_n));
            regin.setText(Integer.toString(regi_a));
            regic.setText(Integer.toString(regi_n));
            //  punctajn.setText(Integer.toString(pct_a));
            //punctajc.setText(Integer.toString(pct_n));
        }
        if (ex.negru.getState()) {
            piesen.setText(Integer.toString(piese_n));
            piesec.setText(Integer.toString(piese_a));
            regin.setText(Integer.toString(regi_n));
            regic.setText(Integer.toString(regi_a));
            //punctajn.setText(Integer.toString(pct_n));
            //punctajc.setText(Integer.toString(pct_a));
        }
        for (ci = 1; ci <= 8; ci++)
            for (cj = 1; cj <= 8; cj++) {
                A1[ci][cj] = new Patrat();
                A1[ci][cj].piesa = A[ci][cj].piesa;
                A1[ci][cj].culoare = A[ci][cj].culoare;
                A1[ci][cj].rege = A[ci][cj].rege;
                // System.out.println(A1[ci][cj].culoare+" "+A1[ci][cj].piesa);
            }
        for (ci = 1; ci <= 8; ci++)
            for (cj = 1; cj <= 8; cj++) {
                if (A1[ci][cj].piesa == true && A1[ci][cj].culoare == 0) {
                    ind++;
                    ct = mutari_disponibile_a(ci, cj);
                    if (ct == 0)
                        sw = 0;
                }
            }
        if (sw == 1 && ind > 0) {
            JOptionPane.showMessageDialog(fereastraPrincipala, "Ai castigat ! Computerul a ramas fara mutari valide !");
            for (ci = 1; ci <= 8; ci++)
                for (cj = 1; cj <= 8; cj++) {
                    a[ci][cj].setEnabled(false);
                }
        } else {
            if (egal == 100) {
                JOptionPane.showMessageDialog(fereastraPrincipala, "Jocul este declarat remiza deoarece sau facut 50 de miscari fara capturare!");
                for (ci = 1; ci <= 8; ci++)
                    for (cj = 1; cj <= 8; cj++) {
                        a[ci][cj].setEnabled(false);
                    }
            }
            randc();
            muta_computer();
        }
    }

    public void muta_n_computer(int i1, int j1, int k1, int l1) {
        int ci, cj;
        //int pct_a=0,pct_n=0;
        regi_a = 0;
        regi_n = 0;
        piese_a = 0;
        piese_n = 0;
        egal++;
        a[i1][j1].setIcon(null);
        a[k1][l1].setIcon(piesaNegru);
        A[i1][j1].piesa = false;
        A[k1][l1].piesa = true;
        A[i1][j1].culoare = -1;
        A[k1][l1].culoare = 1;
        if (k1 == 1 || A[i1][j1].rege == true) {
            A[k1][l1].rege = true;
            A[i1][j1].rege = false;
            a[k1][l1].setIcon(regeNegru);
        }
        if (A[(k1 + i1) / 2][(j1 + l1) / 2].piesa == true && A[(k1 + i1) / 2][(j1 + l1) / 2].culoare == 0) {
            egal = 0;
            A[(k1 + i1) / 2][(j1 + l1) / 2].piesa = false;
            A[(k1 + i1) / 2][(j1 + l1) / 2].culoare = -1;
            A[(k1 + i1) / 2][(j1 + l1) / 2].rege = false;
            a[(k1 + i1) / 2][(j1 + l1) / 2].setIcon(null);
        }
        for (ci = 1; ci <= 8; ci++)
            for (cj = 1; cj <= 8; cj++) {
                if (A[ci][cj].piesa == true && A[ci][cj].culoare == 0)
                    piese_a++;
                if (A[ci][cj].rege == true && A[ci][cj].culoare == 0)
                    regi_a++;
                if (A[ci][cj].piesa == true && A[ci][cj].culoare == 1)
                    piese_n++;
                if (A[ci][cj].rege == true && A[ci][cj].culoare == 1)
                    regi_n++;
            }
        //pct_a = piese_a * 10 + regi_a * 17;
        //pct_n = piese_n * 10 + regi_n * 17;
        if (ex.alb.getState()) {
            piesen.setText(Integer.toString(piese_a));
            piesec.setText(Integer.toString(piese_n));
            regin.setText(Integer.toString(regi_a));
            regic.setText(Integer.toString(regi_n));
            //  punctajn.setText(Integer.toString(pct_a));
            //punctajc.setText(Integer.toString(pct_n));
        }
        if (ex.negru.getState()) {
            piesen.setText(Integer.toString(piese_n));
            piesec.setText(Integer.toString(piese_a));
            regin.setText(Integer.toString(regi_n));
            regic.setText(Integer.toString(regi_a));
            //punctajn.setText(Integer.toString(pct_n));
            //punctajc.setText(Integer.toString(pct_a));
        }
        for (ci = 1; ci <= 8; ci++)
            for (cj = 1; cj <= 8; cj++) {
                A1[ci][cj] = new Patrat();
                A1[ci][cj].piesa = A[ci][cj].piesa;
                A1[ci][cj].culoare = A[ci][cj].culoare;
                A1[ci][cj].rege = A[ci][cj].rege;
            }
        if (egal == 100) {
            JOptionPane.showMessageDialog(fereastraPrincipala, "Jocul este declarat remiza deoarece sau facut 50 de miscari fara capturare!");
            for (ci = 1; ci <= 8; ci++)
                for (cj = 1; cj <= 8; cj++) {
                    a[ci][cj].setEnabled(false);
                }
        }
    }

    public void muta_a_computer(int i1, int j1, int k1, int l1) {
        int ci, cj;
        //int pct_a=0,pct_n=0;
        regi_a = 0;
        regi_n = 0;
        piese_a = 0;
        piese_n = 0;
        egal++;
        a[i1][j1].setIcon(null);
        a[k1][l1].setIcon(piesaRosu);
        A[i1][j1].piesa = false;
        A[k1][l1].piesa = true;
        A[i1][j1].culoare = -1;
        A[k1][l1].culoare = 0;
        if (k1 == 8 || A[i1][j1].rege == true) {
            a[k1][l1].setIcon(regeRosu);
            A[k1][l1].rege = true;
            A[i1][j1].rege = false;
        }
        if (A[(k1 + i1) / 2][(j1 + l1) / 2].piesa == true && A[(k1 + i1) / 2][(j1 + l1) / 2].culoare == 1) {
            egal = 0;
            A[(k1 + i1) / 2][(j1 + l1) / 2].piesa = false;
            A[(k1 + i1) / 2][(j1 + l1) / 2].culoare = -1;
            A[(k1 + i1) / 2][(j1 + l1) / 2].rege = false;
            a[(k1 + i1) / 2][(j1 + l1) / 2].setIcon(null);
        }
        for (ci = 1; ci <= 8; ci++)
            for (cj = 1; cj <= 8; cj++) {
                if (A[ci][cj].piesa == true && A[ci][cj].culoare == 0)
                    piese_a++;
                if (A[ci][cj].rege == true && A[ci][cj].culoare == 0)
                    regi_a++;
                if (A[ci][cj].piesa == true && A[ci][cj].culoare == 1)
                    piese_n++;
                if (A[ci][cj].rege == true && A[ci][cj].culoare == 1)
                    regi_n++;
            }
        //pct_a = piese_a * 10 + regi_a * 17;
        //pct_n = piese_n * 10 + regi_n * 17;

        if (ex.alb.getState()) {
            piesen.setText(Integer.toString(piese_a));
            piesec.setText(Integer.toString(piese_n));
            regin.setText(Integer.toString(regi_a));
            regic.setText(Integer.toString(regi_n));
            //   punctajn.setText(Integer.toString(pct_a));
            // punctajc.setText(Integer.toString(pct_n));
        }
        if (ex.negru.getState()) {
            piesen.setText(Integer.toString(piese_n));
            piesec.setText(Integer.toString(piese_a));
            regin.setText(Integer.toString(regi_n));
            regic.setText(Integer.toString(regi_a));
            //punctajn.setText(Integer.toString(pct_n));
            //    punctajc.setText(Integer.toString(pct_a));
        }
        for (ci = 1; ci <= 8; ci++)
            for (cj = 1; cj <= 8; cj++) {
                A1[ci][cj] = new Patrat();
                A1[ci][cj].piesa = A[ci][cj].piesa;
                A1[ci][cj].culoare = A[ci][cj].culoare;
                A1[ci][cj].rege = A[ci][cj].rege;
            }
        if (egal == 100) {
            JOptionPane.showMessageDialog(fereastraPrincipala, "Jocul este declarat remiza deoarece sau facut 50 de miscari fara capturare!");
            for (ci = 1; ci <= 8; ci++)
                for (cj = 1; cj <= 8; cj++) {
                    a[ci][cj].setEnabled(false);
                }
        }
    }

    public Mutare listamutari(int k1, int l1, int cul) {
        int t = 0, ci = 0, cj = 0;
        Mutare[] mt = new Mutare[96];
        Mutare xt = new Mutare();

        if (cul == 1) {
            for (ci = 1; ci <= 8; ci++)
                for (cj = 1; cj <= 8; cj++) {
                    if ((A1[k1][l1].rege == true) && ((ci == k1 + 1 && cj == l1 - 1) || (ci == k1 + 1 && cj == l1 + 1) || (ci == k1 - 1 && cj == l1 + 1) || (ci == k1 - 1 && cj == l1 - 1)) && A1[ci][cj].piesa == false && A1[k1][l1].culoare == 1) {
                        A1[k1][l1].piesa = false;
                        A1[k1][l1].rege = false;
                        A1[k1][l1].culoare = -1;
                        A1[ci][cj].piesa = true;
                        A1[ci][cj].rege = true;
                        A1[ci][cj].culoare = 1;
                        t++;
                        mt[t] = new Mutare();
                        mt[t].eval = evaluare1(A1, 1);
                        mt[t].i1 = k1;
                        mt[t].j1 = l1;
                        mt[t].i2 = ci;
                        mt[t].j2 = cj;
                        A1[k1][l1].piesa = true;
                        A1[k1][l1].rege = true;
                        A1[k1][l1].culoare = 1;
                        A1[ci][cj].piesa = false;
                        A1[ci][cj].rege = false;
                        A1[ci][cj].culoare = -1;
                    } else {
                        if ((A1[k1][l1].rege == true) && ((ci == k1 + 2 && cj == l1 - 2) || (ci == k1 + 2 && cj == l1 + 2) || (ci == k1 - 2 && cj == l1 + 2) || (ci == k1 - 2 && cj == l1 - 2)) && A1[ci][cj].piesa == false && A1[k1][l1].culoare == 1 && A1[(k1 + ci) / 2][(l1 + cj) / 2].piesa == true && A1[(k1 + ci) / 2][(l1 + cj) / 2].culoare == 0) {
                            A1[k1][l1].piesa = false;
                            A1[k1][l1].rege = false;
                            A1[k1][l1].culoare = -1;
                            A1[ci][cj].piesa = true;
                            A1[ci][cj].rege = true;
                            A1[ci][cj].culoare = 1;
                            A1[(k1 + ci) / 2][(l1 + cj) / 2].piesa = false;
                            A1[(k1 + ci) / 2][(l1 + cj) / 2].culoare = -1;
                            t++;
                            mt[t] = new Mutare();
                            mt[t].eval = evaluare1(A1, 1);
                            mt[t].i1 = k1;
                            mt[t].j1 = l1;
                            mt[t].i2 = ci;
                            mt[t].j2 = cj;
                            A1[k1][l1].piesa = true;
                            A1[k1][l1].rege = true;
                            A1[k1][l1].culoare = 1;
                            A1[ci][cj].piesa = false;
                            A1[ci][cj].rege = false;
                            A1[ci][cj].culoare = -1;
                            A1[(k1 + ci) / 2][(l1 + cj) / 2].piesa = true;
                            A1[(k1 + ci) / 2][(l1 + cj) / 2].culoare = 0;
                        } else {
                            if (A1[k1][l1].rege == false && ((ci == k1 - 1 && cj == l1 + 1) || (ci == k1 - 1 && cj == l1 - 1)) && A1[ci][cj].piesa == false && A1[k1][l1].culoare == 1) {
                                A1[k1][l1].piesa = false;
                                A1[k1][l1].culoare = -1;
                                A1[ci][cj].piesa = true;
                                A1[ci][cj].culoare = 1;
                                t++;
                                mt[t] = new Mutare();
                                mt[t].eval = evaluare1(A1, 1);
                                mt[t].i1 = k1;
                                mt[t].j1 = l1;
                                mt[t].i2 = ci;
                                mt[t].j2 = cj;
                                A1[k1][l1].piesa = true;
                                A1[k1][l1].culoare = 1;
                                A1[ci][cj].piesa = false;
                                A1[ci][cj].culoare = -1;
                            } else {
                                if (A1[k1][l1].rege == false && ((ci == k1 - 2 && cj == l1 + 2) || (ci == k1 - 2 && cj == l1 - 2)) && A1[ci][cj].piesa == false && A1[k1][l1].culoare == 1 && A1[(k1 + ci) / 2][(l1 + cj) / 2].piesa == true && A1[(k1 + ci) / 2][(l1 + cj) / 2].culoare == 0) {
                                    A1[k1][l1].piesa = false;
                                    A1[k1][l1].culoare = -1;
                                    A1[ci][cj].piesa = true;
                                    A1[ci][cj].culoare = 1;
                                    A1[(k1 + ci) / 2][(l1 + cj) / 2].piesa = false;
                                    A1[(k1 + ci) / 2][(l1 + cj) / 2].culoare = -1;
                                    t++;
                                    mt[t] = new Mutare();
                                    mt[t].eval = evaluare1(A1, 1);
                                    mt[t].i1 = k1;
                                    mt[t].j1 = l1;
                                    mt[t].i2 = ci;
                                    mt[t].j2 = cj;
                                    A1[k1][l1].piesa = true;
                                    A1[k1][l1].culoare = 1;
                                    A1[ci][cj].piesa = false;
                                    A1[ci][cj].culoare = -1;
                                    A1[(k1 + ci) / 2][(l1 + cj) / 2].piesa = true;
                                    A1[(k1 + ci) / 2][(l1 + cj) / 2].culoare = 0;
                                } else {
                                    t++;
                                    mt[t] = new Mutare();
                                    mt[t].eval = -10000;
                                }
                            }
                        }
                    }
                }
        }

        if (cul == 0) {
            for (ci = 1; ci <= 8; ci++)
                for (cj = 1; cj <= 8; cj++) {
                    if ((A1[k1][l1].rege == true) && ((ci == k1 + 1 && cj == l1 - 1) || (ci == k1 + 1 && cj == l1 + 1) || (ci == k1 - 1 && cj == l1 + 1) || (ci == k1 - 1 && cj == l1 - 1)) && A1[ci][cj].piesa == false && A1[k1][l1].culoare == 0) {
                        A1[k1][l1].piesa = false;
                        A1[k1][l1].rege = false;
                        A1[k1][l1].culoare = -1;
                        A1[ci][cj].piesa = true;
                        A1[ci][cj].rege = true;
                        A1[ci][cj].culoare = 0;
                        t++;
                        mt[t] = new Mutare();
                        mt[t].eval = evaluare1(A1, 0);
                        mt[t].i1 = k1;
                        mt[t].j1 = l1;
                        mt[t].i2 = ci;
                        mt[t].j2 = cj;
                        A1[k1][l1].piesa = true;
                        A1[k1][l1].rege = true;
                        A1[k1][l1].culoare = 0;
                        A1[ci][cj].piesa = false;
                        A1[ci][cj].rege = false;
                        A1[ci][cj].culoare = -1;
                    } else {
                        if ((A1[k1][l1].rege == true) && ((ci == k1 + 2 && cj == l1 - 2) || (ci == k1 + 2 && cj == l1 + 2) || (ci == k1 - 2 && cj == l1 + 2) || (ci == k1 - 2 && cj == l1 - 2)) && A1[ci][cj].piesa == false && A1[k1][l1].culoare == 0 && A1[(k1 + ci) / 2][(l1 + cj) / 2].piesa == true && A1[(k1 + ci) / 2][(l1 + cj) / 2].culoare == 1) {
                            A1[k1][l1].piesa = false;
                            A1[k1][l1].rege = false;
                            A1[k1][l1].culoare = -1;
                            A1[ci][cj].piesa = true;
                            A1[ci][cj].rege = true;
                            A1[ci][cj].culoare = 0;
                            A1[(k1 + ci) / 2][(l1 + cj) / 2].piesa = false;
                            A1[(k1 + ci) / 2][(l1 + cj) / 2].culoare = -1;
                            t++;
                            mt[t] = new Mutare();
                            mt[t].eval = evaluare1(A1, 0);
                            mt[t].i1 = k1;
                            mt[t].j1 = l1;
                            mt[t].i2 = ci;
                            mt[t].j2 = cj;
                            A1[k1][l1].piesa = true;
                            A1[k1][l1].rege = true;
                            A1[k1][l1].culoare = 0;
                            A1[ci][cj].piesa = false;
                            A1[ci][cj].rege = false;
                            A1[ci][cj].culoare = -1;
                            A1[(k1 + ci) / 2][(l1 + cj) / 2].piesa = true;
                            A1[(k1 + ci) / 2][(l1 + cj) / 2].culoare = 1;
                        } else {
                            if (A1[k1][l1].rege == false && ((ci == k1 + 1 && cj == l1 + 1) || (ci == k1 + 1 && cj == l1 - 1)) && A1[ci][cj].piesa == false && A1[k1][l1].culoare == 0) {
                                A1[k1][l1].piesa = false;
                                A1[k1][l1].culoare = -1;
                                A1[ci][cj].piesa = true;
                                A1[ci][cj].culoare = 0;
                                t++;
                                mt[t] = new Mutare();
                                mt[t].eval = evaluare1(A1, 0);
                                mt[t].i1 = k1;
                                mt[t].j1 = l1;
                                mt[t].i2 = ci;
                                mt[t].j2 = cj;
                                A1[k1][l1].piesa = true;
                                A1[k1][l1].culoare = 0;
                                A1[ci][cj].piesa = false;
                                A1[ci][cj].culoare = -1;
                            } else {
                                if (A1[k1][l1].rege == false && ((ci == k1 + 2 && cj == l1 + 2) || (ci == k1 + 2 && cj == l1 - 2)) && A1[ci][cj].piesa == false && A1[k1][l1].culoare == 0 && A1[(k1 + ci) / 2][(l1 + cj) / 2].piesa == true && A1[(k1 + ci) / 2][(l1 + cj) / 2].culoare == 1) {
                                    A1[k1][l1].piesa = false;
                                    A1[k1][l1].culoare = -1;
                                    A1[ci][cj].piesa = true;
                                    A1[ci][cj].culoare = 0;
                                    A1[(k1 + ci) / 2][(l1 + cj) / 2].piesa = false;
                                    A1[(k1 + ci) / 2][(l1 + cj) / 2].culoare = -1;
                                    t++;
                                    mt[t] = new Mutare();
                                    mt[t].eval = evaluare1(A1, 0);
                                    mt[t].i1 = k1;
                                    mt[t].j1 = l1;
                                    mt[t].i2 = ci;
                                    mt[t].j2 = cj;
                                    A1[k1][l1].piesa = true;
                                    A1[k1][l1].culoare = 0;
                                    A1[ci][cj].piesa = false;
                                    A1[ci][cj].culoare = -1;
                                    A1[(k1 + ci) / 2][(l1 + cj) / 2].piesa = true;
                                    A1[(k1 + ci) / 2][(l1 + cj) / 2].culoare = 1;
                                } else {
                                    t++;
                                    mt[t] = new Mutare();
                                    mt[t].eval = -10000;
                                }
                            }
                        }
                    }
                }
        }


        int m;
        m = mt[1].eval;
        for (ci = 2; ci <= t; ci++)
            if (mt[ci].eval > m)
                m = mt[ci].eval;
        for (ci = 1; ci <= t; ci++)
            if (mt[ci].eval == m) {
                xt = new Mutare();
                xt.i1 = mt[ci].i1;
                xt.i2 = mt[ci].i2;
                xt.j1 = mt[ci].j1;
                xt.j2 = mt[ci].j2;
                xt.eval = mt[ci].eval;
            }
        return xt;
    }

    public int nr_piese_negre() {
        int no = 0, c1, c2;
        for (c1 = 1; c1 <= 8; c1++)
            for (c2 = 1; c2 <= 8; c2++) {
                if ((A1[c1][c2].culoare == 1) && (A1[c1][c2].piesa == true))
                    no++;
            }
        return no;
    }

    public int nr_piese_albe() {
        int no = 0, c1, c2;
        for (c1 = 1; c1 <= 8; c1++)
            for (c2 = 1; c2 <= 8; c2++) {
                if ((A1[c1][c2].culoare == 0) && (A1[c1][c2].piesa == true))
                    no++;
            }
        return no;
    }

    public void minmax() {
        Mutare[] x1 = new Mutare[96];
        int t1 = 0, mx = 0, ci, cj, id;

        if (ex.rosu.getState()) {
            for (ci = 1; ci <= 8; ci++)
                for (cj = 1; cj <= 8; cj++) {
                    if ((A1[ci][cj].culoare == 1) && (A1[ci][cj].piesa == true)) {
                        //  System.out.println(ci+" "+cj);
                        t1++;
                        x1[t1] = new Mutare();
                        x1[t1] = listamutari(ci, cj, 1);
                        // System.out.println(x1[t1].i1+" "+x1[t1].j1+" "+x1[t1].i2+" "+x1[t1].j2);
                    }
                }
        }
        if (ex.negru.getState()) {
            for (ci = 1; ci <= 8; ci++)
                for (cj = 1; cj <= 8; cj++) {
                    if (A1[ci][cj].culoare == 0 && A1[ci][cj].piesa == true) {
                        t1++;

                        x1[t1] = new Mutare();
                        x1[t1] = listamutari(ci, cj, 0);
                    }
                }
        }
        mx = x1[1].eval;
        for (id = 2; id <= t1; id++)
            if (x1[id].eval > mx)
                mx = x1[id].eval;
        for (id = 1; id <= t1; id++)
            if (mx == x1[id].eval) {

                x = new Mutare();
                x.i1 = x1[id].i1;
                x.i2 = x1[id].i2;
                x.j1 = x1[id].j1;
                x.j2 = x1[id].j2;
                x.eval = x1[id].eval;
            }

    }

    public int mutari_disponibile_n(int c1, int c2) {
        int sw = 1, ci, cj;

        for (ci = 1; ci <= 8; ci++)
            for (cj = 1; cj <= 8; cj++) {
                if (A[c1][c2].piesa == true && A[c1][c2].rege == true && A[c1][c2].culoare == 1) {
                    if (((ci == c1 - 1 && cj == c2 - 1) || (ci == c1 - 1 && cj == c2 + 1) || (ci == c1 + 1 && cj == c2 - 1) || (ci == c1 + 1 && cj == c2 + 1)) && A[ci][cj].piesa == false) {
                        sw = 0;

                    }
                    if (((ci == c1 - 2 && cj == c2 - 2) || (ci == c1 - 2 && cj == c2 + 2) || (ci == c1 + 2 && cj == c2 + 2) || (ci == c1 + 2 && cj == c2 - 2)) && (A[ci][cj].piesa == false && A[(ci + c1) / 2][(cj + c2) / 2].piesa == true && A[(ci + c1) / 2][(cj + c2) / 2].culoare == 0)) {
                        sw = 0;

                    }
                } else {
                    if (A[c1][c2].piesa == true && A[c1][c2].culoare == 1) {
                        if (((ci == c1 - 1 && cj == c2 - 1) || (ci == c1 - 1 && cj == c2 + 1)) && A[ci][cj].piesa == false) {
                            sw = 0;
                        }
                        if (((ci == c1 - 2 && cj == c2 - 2) || (ci == c1 - 2 && cj == c2 + 2)) && (A[ci][cj].piesa == false && A[(ci + c1) / 2][(cj + c2) / 2].piesa == true && A[(ci + c1) / 2][(cj + c2) / 2].culoare == 0))
                            sw = 0;
                    }
                }
            }

        return sw;
    }

    public int mutari_disponibile_a(int c1, int c2) {
        int sw = 1, ci, cj;

        for (ci = 1; ci <= 8; ci++)
            for (cj = 1; cj <= 8; cj++) {
                if (A[c1][c2].piesa == true && A[c1][c2].rege == true && A[c1][c2].culoare == 0) {
                    if (((ci == c1 - 1 && cj == c2 - 1) || (ci == c1 - 1 && cj == c2 + 1) || (ci == c1 + 1 && cj == c2 - 1) || (ci == c1 + 1 && cj == c2 + 1)) && A[ci][cj].piesa == false) {
                        sw = 0;

                    }
                    if (((ci == c1 - 2 && cj == c2 - 2) || (ci == c1 - 2 && cj == c2 + 2) || (ci == c1 + 2 && cj == c2 + 2) || (ci == c1 + 2 && cj == c2 - 2)) && (A[ci][cj].piesa == false && A[(ci + c1) / 2][(cj + c2) / 2].piesa == true && A[(ci + c1) / 2][(cj + c2) / 2].culoare == 1)) {
                        sw = 0;

                    }
                } else {
                    if (A[c1][c2].piesa == true && A[c1][c2].culoare == 0) {
                        if (((ci == c1 + 1 && cj == c2 - 1) || (ci == c1 + 1 && cj == c2 + 1)) && A[ci][cj].piesa == false) {
                            sw = 0;
                        }
                        if (((ci == c1 + 2 && cj == c2 - 2) || (ci == c1 + 2 && cj == c2 + 2)) && (A[ci][cj].piesa == false && A[(ci + c1) / 2][(cj + c2) / 2].piesa == true && A[(ci + c1) / 2][(cj + c2) / 2].culoare == 1))
                            sw = 0;
                    }
                }
            }

        return sw;
    }

    public void toate_mutarile_n(Patrat[][] P) {
        int ci, cj;
        cnt = 0;
        for (ci = 1; ci <= 8; ci++)
            for (cj = 1; cj <= 8; cj++) {
                if (P[ci][cj].piesa == true && P[ci][cj].rege == true && P[ci][cj].culoare == 1) {
                    if ((ci - 1) > 0 && (cj - 1) > 0 && P[ci - 1][cj - 1].piesa == false) {
                        cnt++;
                        muta[cnt] = new Mutare();
                        muta[cnt].i1 = ci;
                        muta[cnt].j1 = cj;
                        muta[cnt].i2 = ci - 1;
                        muta[cnt].j2 = cj - 1;
                    }
                    if ((ci - 1) > 0 && (cj + 1) < 9 && P[ci - 1][cj + 1].piesa == false) {
                        cnt++;
                        muta[cnt] = new Mutare();
                        muta[cnt].i1 = ci;
                        muta[cnt].j1 = cj;
                        muta[cnt].i2 = ci - 1;
                        muta[cnt].j2 = cj + 1;
                    }
                    if ((ci + 1) < 9 && (cj + 1) < 9 && P[ci + 1][cj + 1].piesa == false) {
                        cnt++;
                        muta[cnt] = new Mutare();
                        muta[cnt].i1 = ci;
                        muta[cnt].j1 = cj;
                        muta[cnt].i2 = ci + 1;
                        muta[cnt].j2 = cj + 1;
                    }
                    if ((ci + 1) < 9 && (cj - 1) > 0 && P[ci + 1][cj - 1].piesa == false) {
                        cnt++;
                        muta[cnt] = new Mutare();
                        muta[cnt].i1 = ci;
                        muta[cnt].j1 = cj;
                        muta[cnt].i2 = ci + 1;
                        muta[cnt].j2 = cj - 1;
                    }
                    if ((ci - 2) > 0 && (cj - 2) > 0 && (P[ci - 2][cj - 2].piesa == false && P[ci - 1][cj - 1].piesa == true && P[ci - 1][cj - 1].culoare == 0)) {
                        cnt++;
                        muta[cnt] = new Mutare();
                        muta[cnt].i1 = ci;
                        muta[cnt].j1 = cj;
                        muta[cnt].i2 = ci - 2;
                        muta[cnt].j2 = cj - 2;
                    }
                    if ((ci - 2) > 0 && (cj + 2) < 9 && (P[ci - 2][cj + 2].piesa == false && P[ci - 1][cj + 1].piesa == true && P[ci - 1][cj + 1].culoare == 0)) {
                        cnt++;
                        muta[cnt] = new Mutare();
                        muta[cnt].i1 = ci;
                        muta[cnt].j1 = cj;
                        muta[cnt].i2 = ci - 2;
                        muta[cnt].j2 = cj + 2;
                    }
                    if ((ci + 2) < 9 && (cj + 2) < 9 && (P[ci + 2][cj + 2].piesa == false && P[ci + 1][cj + 1].piesa == true && P[ci + 1][cj + 1].culoare == 0)) {
                        cnt++;
                        muta[cnt] = new Mutare();
                        muta[cnt].i1 = ci;
                        muta[cnt].j1 = cj;
                        muta[cnt].i2 = ci + 2;
                        muta[cnt].j2 = cj + 2;
                    }
                    if ((ci + 2) < 9 && (cj - 2) > 0 && (P[ci + 2][cj - 2].piesa == false && P[ci + 1][cj - 1].piesa == true && P[ci + 1][cj - 1].culoare == 0)) {
                        cnt++;
                        muta[cnt] = new Mutare();
                        muta[cnt].i1 = ci;
                        muta[cnt].j1 = cj;
                        muta[cnt].i2 = ci + 2;
                        muta[cnt].j2 = cj - 2;
                    }
                } else {
                    if (P[ci][cj].piesa == true && P[ci][cj].culoare == 1) {
                        if ((ci - 1) > 0 && (cj - 1) > 0 && P[ci - 1][cj - 1].piesa == false) {
                            cnt++;
                            muta[cnt] = new Mutare();
                            muta[cnt].i1 = ci;
                            muta[cnt].j1 = cj;
                            muta[cnt].i2 = ci - 1;
                            muta[cnt].j2 = cj - 1;
                        }
                        if ((ci - 1) > 0 && (cj + 1) < 9 && P[ci - 1][cj + 1].piesa == false) {
                            cnt++;
                            muta[cnt] = new Mutare();
                            muta[cnt].i1 = ci;
                            muta[cnt].j1 = cj;
                            muta[cnt].i2 = ci - 1;
                            muta[cnt].j2 = cj + 1;
                        }
                        if ((ci - 2) > 0 && (cj - 2) > 0 && (P[ci - 2][cj - 2].piesa == false && P[ci - 1][cj - 1].piesa == true && P[ci - 1][cj - 1].culoare == 0)) {
                            cnt++;
                            muta[cnt] = new Mutare();
                            muta[cnt].i1 = ci;
                            muta[cnt].j1 = cj;
                            muta[cnt].i2 = ci - 2;
                            muta[cnt].j2 = cj - 2;
                        }
                        if ((ci - 2) > 0 && (cj + 2) < 9 && (P[ci - 2][cj + 2].piesa == false && P[ci - 1][cj + 1].piesa == true && P[ci - 1][cj + 1].culoare == 0)) {
                            cnt++;
                            muta[cnt] = new Mutare();
                            muta[cnt].i1 = ci;
                            muta[cnt].j1 = cj;
                            muta[cnt].i2 = ci - 2;
                            muta[cnt].j2 = cj + 2;
                        }
                    }
                }
            }
        // for (ci=1;ci<=cnt;ci++)
        //   System.out.println(muta[ci].i1+" "+muta[ci].j1+"  "+muta[ci].i2+" "+muta[ci].j2);
    }

    public void toate_mutarile_a(Patrat[][] P) {
        int ci, cj;
        cnt = 0;
        for (ci = 1; ci <= 8; ci++)
            for (cj = 1; cj <= 8; cj++) {
                if (P[ci][cj].piesa == true && P[ci][cj].rege == true && P[ci][cj].culoare == 0) {
                    if ((ci - 1) > 0 && (cj - 1) > 0 && P[ci - 1][cj - 1].piesa == false) {
                        cnt++;
                        muta[cnt] = new Mutare();
                        muta[cnt].i1 = ci;
                        muta[cnt].j1 = cj;
                        muta[cnt].i2 = ci - 1;
                        muta[cnt].j2 = cj - 1;
                    }
                    if ((ci - 1) > 0 && (cj + 1) < 9 && P[ci - 1][cj + 1].piesa == false) {
                        cnt++;
                        muta[cnt] = new Mutare();
                        muta[cnt].i1 = ci;
                        muta[cnt].j1 = cj;
                        muta[cnt].i2 = ci - 1;
                        muta[cnt].j2 = cj + 1;
                    }
                    if ((ci + 1) < 9 && (cj + 1) < 9 && P[ci + 1][cj + 1].piesa == false) {
                        cnt++;
                        muta[cnt] = new Mutare();
                        muta[cnt].i1 = ci;
                        muta[cnt].j1 = cj;
                        muta[cnt].i2 = ci + 1;
                        muta[cnt].j2 = cj + 1;
                    }
                    if ((ci + 1) < 9 && (cj - 1) > 0 && P[ci + 1][cj - 1].piesa == false) {
                        cnt++;
                        muta[cnt] = new Mutare();
                        muta[cnt].i1 = ci;
                        muta[cnt].j1 = cj;
                        muta[cnt].i2 = ci + 1;
                        muta[cnt].j2 = cj - 1;
                    }
                    if ((ci - 2) > 0 && (cj - 2) > 0 && (P[ci - 2][cj - 2].piesa == false && P[ci - 1][cj - 1].piesa == true && P[ci - 1][cj - 1].culoare == 1)) {
                        cnt++;
                        muta[cnt] = new Mutare();
                        muta[cnt].i1 = ci;
                        muta[cnt].j1 = cj;
                        muta[cnt].i2 = ci - 2;
                        muta[cnt].j2 = cj - 2;
                    }
                    if ((ci - 2) > 0 && (cj + 2) < 9 && (P[ci - 2][cj + 2].piesa == false && P[ci - 1][cj + 1].piesa == true && P[ci - 1][cj + 1].culoare == 1)) {
                        cnt++;
                        muta[cnt] = new Mutare();
                        muta[cnt].i1 = ci;
                        muta[cnt].j1 = cj;
                        muta[cnt].i2 = ci - 2;
                        muta[cnt].j2 = cj + 2;
                    }
                    if ((ci + 2) < 9 && (cj + 2) < 9 && (P[ci + 2][cj + 2].piesa == false && P[ci + 1][cj + 1].piesa == true && P[ci + 1][cj + 1].culoare == 1)) {
                        cnt++;
                        muta[cnt] = new Mutare();
                        muta[cnt].i1 = ci;
                        muta[cnt].j1 = cj;
                        muta[cnt].i2 = ci + 2;
                        muta[cnt].j2 = cj + 2;
                    }
                    if ((ci + 2) < 9 && (cj - 2) > 0 && (P[ci + 2][cj - 2].piesa == false && P[ci + 1][cj - 1].piesa == true && P[ci + 1][cj - 1].culoare == 1)) {
                        cnt++;
                        muta[cnt] = new Mutare();
                        muta[cnt].i1 = ci;
                        muta[cnt].j1 = cj;
                        muta[cnt].i2 = ci + 2;
                        muta[cnt].j2 = cj - 2;
                    }
                } else {
                    if (P[ci][cj].piesa == true && P[ci][cj].culoare == 0) {
                        if ((ci + 1) < 9 && (cj - 1) > 0 && P[ci + 1][cj - 1].piesa == false) {
                            cnt++;
                            muta[cnt] = new Mutare();
                            muta[cnt].i1 = ci;
                            muta[cnt].j1 = cj;
                            muta[cnt].i2 = ci + 1;
                            muta[cnt].j2 = cj - 1;
                        }
                        if ((ci + 1) < 9 && (cj + 1) < 9 && P[ci + 1][cj + 1].piesa == false) {
                            cnt++;
                            muta[cnt] = new Mutare();
                            muta[cnt].i1 = ci;
                            muta[cnt].j1 = cj;
                            muta[cnt].i2 = ci + 1;
                            muta[cnt].j2 = cj + 1;
                        }
                        if ((ci + 2) < 9 && (cj - 2) > 0 && (P[ci + 2][cj - 2].piesa == false && P[ci + 1][cj - 1].piesa == true && P[ci + 1][cj - 1].culoare == 1)) {
                            cnt++;
                            muta[cnt] = new Mutare();
                            muta[cnt].i1 = ci;
                            muta[cnt].j1 = cj;
                            muta[cnt].i2 = ci + 2;
                            muta[cnt].j2 = cj - 2;
                        }
                        if ((ci + 2) < 9 && (cj + 2) < 9 && (P[ci + 2][cj + 2].piesa == false && P[ci + 1][cj + 1].piesa == true && P[ci + 1][cj + 1].culoare == 1)) {
                            cnt++;
                            muta[cnt] = new Mutare();
                            muta[cnt].i1 = ci;
                            muta[cnt].j1 = cj;
                            muta[cnt].i2 = ci + 2;
                            muta[cnt].j2 = cj + 2;
                        }
                    }
                }
            }
        // for (ci=1;ci<=cnt;ci++)
        //   System.out.println(muta[ci].i1+" "+muta[ci].j1+"  "+muta[ci].i2+" "+muta[ci].j2);
    }

    public int minimax(Patrat[][] P, int ad) {
        Mutare[] m1 = new Mutare[96];
        int ccc, il, mxm = 0, mnm = 0, id = 0, r = 0;
        if (ad > 0) {
            if (max == true) {//computerul este la mutare
                max = false;
                if (ex.rosu.getState()) {  //computerul joaca cu piesele negre
                    toate_mutarile_n(P);
                    for (ccc = 1; ccc <= cnt; ccc++) {
                        r++;
                        m1[r] = new Mutare();
                        m1[r].i1 = muta[ccc].i1;
                        m1[r].j1 = muta[ccc].j1;
                        m1[r].i2 = muta[ccc].i2;
                        m1[r].j2 = muta[ccc].j2;
                    }
                    for (ccc = 1; ccc <= r; ccc++) {
                        P[m1[ccc].i1][m1[ccc].j1].piesa = false;
                        P[m1[ccc].i1][m1[ccc].j1].culoare = -1;
                        P[m1[ccc].i2][m1[ccc].j2].piesa = true;
                        P[m1[ccc].i2][m1[ccc].j2].culoare = 1;
                        if (P[m1[ccc].i1][m1[ccc].j1].rege == true) {
                            P[m1[ccc].i1][m1[ccc].j1].rege = false;
                            P[m1[ccc].i2][m1[ccc].j2].rege = true;
                        }
                        if ((m1[ccc].i1 - m1[ccc].i2 == 2) || (m1[ccc].i2 - m1[ccc].i1 == 2)) {
                            P[(m1[ccc].i1 + m1[ccc].j1) / 2][(m1[ccc].i2 + m1[ccc].j2) / 2].piesa = false;
                            P[(m1[ccc].i1 + m1[ccc].j1) / 2][(m1[ccc].i2 + m1[ccc].j2) / 2].culoare = -1;
                        }
                        m1[ccc].eval = minimax(P, ad - 1);
                        P[m1[ccc].i1][m1[ccc].j1].piesa = true;
                        P[m1[ccc].i1][m1[ccc].j1].culoare = 1;
                        P[m1[ccc].i2][m1[ccc].j2].piesa = false;
                        P[m1[ccc].i2][m1[ccc].j2].culoare = -1;
                        if (P[m1[ccc].i2][m1[ccc].j2].rege == true) {
                            P[m1[ccc].i1][m1[ccc].j1].rege = true;
                            P[m1[ccc].i2][m1[ccc].j2].rege = false;
                        }
                        if ((m1[ccc].i1 - m1[ccc].i2 == 2) || (m1[ccc].i2 - m1[ccc].i1 == 2)) {
                            P[(m1[ccc].i1 + m1[ccc].j1) / 2][(m1[ccc].i2 + m1[ccc].j2) / 2].piesa = true;
                            P[(m1[ccc].i1 + m1[ccc].j1) / 2][(m1[ccc].i2 + m1[ccc].j2) / 2].culoare = 0;
                        }
                    }
                    if (r == 0) {
                        mxm = -10000;
                        id = 1;
                        m1[id] = new Mutare();
                        m1[id].eval = mxm;
                    } else {
                        mxm = m1[1].eval;
                        id = 1;
                    }
                    for (ccc = 2; ccc <= r; ccc++)
                        if (m1[ccc].eval > mxm) {
                            mxm = m1[ccc].eval;
                            id = ccc;
                        }
                }
                if (ex.negru.getState()) {  //computerul joaca cu piesele albe
                    toate_mutarile_a(P);
                    for (ccc = 1; ccc <= cnt; ccc++) {
                        r++;
                        m1[r] = new Mutare();
                        m1[r].i1 = muta[ccc].i1;
                        m1[r].j1 = muta[ccc].j1;
                        m1[r].i2 = muta[ccc].i2;
                        m1[r].j2 = muta[ccc].j2;
                    }
                    for (ccc = 1; ccc <= r; ccc++) {
                        P[m1[ccc].i1][m1[ccc].j1].piesa = false;
                        P[m1[ccc].i1][m1[ccc].j1].culoare = -1;
                        P[m1[ccc].i2][m1[ccc].j2].piesa = true;
                        P[m1[ccc].i2][m1[ccc].j2].culoare = 0;
                        if (P[m1[ccc].i1][m1[ccc].j1].rege == true) {
                            P[m1[ccc].i1][m1[ccc].j1].rege = false;
                            P[m1[ccc].i2][m1[ccc].j2].rege = true;
                        }
                        if ((m1[ccc].i1 - m1[ccc].i2 == 2) || (m1[ccc].i2 - m1[ccc].i1 == 2)) {
                            P[(m1[ccc].i1 + m1[ccc].j1) / 2][(m1[ccc].i2 + m1[ccc].j2) / 2].piesa = false;
                            P[(m1[ccc].i1 + m1[ccc].j1) / 2][(m1[ccc].i2 + m1[ccc].j2) / 2].culoare = -1;
                        }
                        m1[ccc].eval = minimax(P, ad - 1);
                        P[m1[ccc].i1][m1[ccc].j1].piesa = true;
                        P[m1[ccc].i1][m1[ccc].j1].culoare = 0;
                        P[m1[ccc].i2][m1[ccc].j2].piesa = false;
                        P[m1[ccc].i2][m1[ccc].j2].culoare = -1;
                        if (P[m1[ccc].i2][m1[ccc].j2].rege == true) {
                            P[m1[ccc].i1][m1[ccc].j1].rege = true;
                            P[m1[ccc].i2][m1[ccc].j2].rege = false;
                        }
                        if ((m1[ccc].i1 - m1[ccc].i2 == 2) || (m1[ccc].i2 - m1[ccc].i1 == 2)) {
                            P[(m1[ccc].i1 + m1[ccc].j1) / 2][(m1[ccc].i2 + m1[ccc].j2) / 2].piesa = true;
                            P[(m1[ccc].i1 + m1[ccc].j1) / 2][(m1[ccc].i2 + m1[ccc].j2) / 2].culoare = 1;
                        }
                    }
                    if (r == 0) {
                        mxm = -10000;
                        id = 1;
                        m1[id] = new Mutare();
                        m1[id].eval = mxm;
                    } else {
                        mxm = m1[1].eval;
                        id = 1;
                    }
                    for (ccc = 2; ccc <= r; ccc++)
                        if (m1[ccc].eval > mxm) {
                            mxm = m1[ccc].eval;
                            id = ccc;
                        }
                }
            } else {
                max = true;
                if (ex.rosu.getState()) {  //nume joaca cu piesele albe
                    toate_mutarile_a(P);
                    for (ccc = 1; ccc <= cnt; ccc++) {
                        r++;
                        m1[r] = new Mutare();
                        m1[r].i1 = muta[ccc].i1;
                        m1[r].j1 = muta[ccc].j1;
                        m1[r].i2 = muta[ccc].i2;
                        m1[r].j2 = muta[ccc].j2;
                    }
                    for (ccc = 1; ccc <= r; ccc++) {
                        P[m1[ccc].i1][m1[ccc].j1].piesa = false;
                        P[m1[ccc].i1][m1[ccc].j1].culoare = -1;
                        P[m1[ccc].i2][m1[ccc].j2].piesa = true;
                        P[m1[ccc].i2][m1[ccc].j2].culoare = 0;
                        if (P[m1[ccc].i1][m1[ccc].j1].rege == true) {
                            P[m1[ccc].i1][m1[ccc].j1].rege = false;
                            P[m1[ccc].i2][m1[ccc].j2].rege = true;
                        }
                        if ((m1[ccc].i1 - m1[ccc].i2) == 2 || (m1[ccc].i2 - m1[ccc].i1) == 2) {
                            P[(m1[ccc].i1 + m1[ccc].j1) / 2][(m1[ccc].i2 + m1[ccc].j2) / 2].piesa = false;
                            P[(m1[ccc].i1 + m1[ccc].j1) / 2][(m1[ccc].i2 + m1[ccc].j2) / 2].culoare = -1;
                        }
                        m1[ccc].eval = minimax(P, ad - 1);
                        P[m1[ccc].i1][m1[ccc].j1].piesa = true;
                        P[m1[ccc].i1][m1[ccc].j1].culoare = 0;
                        P[m1[ccc].i2][m1[ccc].j2].piesa = false;
                        P[m1[ccc].i2][m1[ccc].j2].culoare = -1;
                        if (P[m1[ccc].i2][m1[ccc].j2].rege == true) {
                            P[m1[ccc].i1][m1[ccc].j1].rege = true;
                            P[m1[ccc].i2][m1[ccc].j2].rege = false;
                        }
                        if ((m1[ccc].i1 - m1[ccc].i2) == 2 || (m1[ccc].i2 - m1[ccc].i1) == 2) {
                            P[(m1[ccc].i1 + m1[ccc].j1) / 2][(m1[ccc].i2 + m1[ccc].j2) / 2].piesa = true;
                            P[(m1[ccc].i1 + m1[ccc].j1) / 2][(m1[ccc].i2 + m1[ccc].j2) / 2].culoare = 1;
                        }
                    }
                    if (r == 0) {
                        mxm = -10000;
                        id = 1;
                        m1[id] = new Mutare();
                        m1[id].eval = mxm;
                    } else {
                        mnm = m1[1].eval;
                        id = 1;
                    }
                    for (ccc = 2; ccc <= r; ccc++)
                        if (m1[ccc].eval < mnm) {
                            mnm = m1[ccc].eval;
                            id = ccc;
                        }
                }
                if (ex.negru.getState()) {  //nume joaca cu piesele negre
                    toate_mutarile_n(P);
                    for (ccc = 1; ccc <= cnt; ccc++) {
                        r++;
                        m1[r] = new Mutare();
                        m1[r].i1 = muta[ccc].i1;
                        m1[r].j1 = muta[ccc].j1;
                        m1[r].i2 = muta[ccc].i2;
                        m1[r].j2 = muta[ccc].j2;
                    }
                    for (ccc = 1; ccc <= r; ccc++) {
                        P[m1[ccc].i1][m1[ccc].j1].piesa = false;
                        P[m1[ccc].i1][m1[ccc].j1].culoare = -1;
                        P[m1[ccc].i2][m1[ccc].j2].piesa = true;
                        P[m1[ccc].i2][m1[ccc].j2].culoare = 1;
                        if (P[m1[ccc].i1][m1[ccc].j1].rege == true) {
                            P[m1[ccc].i1][m1[ccc].j1].rege = false;
                            P[m1[ccc].i2][m1[ccc].j2].rege = true;
                        }
                        if ((m1[ccc].i1 - m1[ccc].i2) == 2 || (m1[ccc].i2 - m1[ccc].i1) == 2) {
                            P[(m1[ccc].i1 + m1[ccc].j1) / 2][(m1[ccc].i2 + m1[ccc].j2) / 2].piesa = false;
                            P[(m1[ccc].i1 + m1[ccc].j1) / 2][(m1[ccc].i2 + m1[ccc].j2) / 2].culoare = -1;
                        }
                        m1[ccc].eval = minimax(P, ad - 1);
                        P[m1[ccc].i1][m1[ccc].j1].piesa = true;
                        P[m1[ccc].i1][m1[ccc].j1].culoare = 1;
                        P[m1[ccc].i2][m1[ccc].j2].piesa = false;
                        P[m1[ccc].i2][m1[ccc].j2].culoare = -1;
                        if (P[m1[ccc].i2][m1[ccc].j2].rege == true) {
                            P[m1[ccc].i1][m1[ccc].j1].rege = true;
                            P[m1[ccc].i2][m1[ccc].j2].rege = false;
                        }
                        if ((m1[ccc].i1 - m1[ccc].i2) == 2 || (m1[ccc].i2 - m1[ccc].i1) == 2) {
                            P[(m1[ccc].i1 + m1[ccc].j1) / 2][(m1[ccc].i2 + m1[ccc].j2) / 2].piesa = true;
                            P[(m1[ccc].i1 + m1[ccc].j1) / 2][(m1[ccc].i2 + m1[ccc].j2) / 2].culoare = 0;
                        }
                    }
                    if (r == 0) {
                        mxm = 10000;
                        id = 1;
                        m1[id] = new Mutare();
                        m1[id].eval = mxm;
                    } else {
                        mnm = m1[1].eval;
                        id = 1;
                    }
                    for (ccc = 2; ccc <= r; ccc++)
                        if (m1[ccc].eval < mnm) {
                            mnm = m1[ccc].eval;
                            id = ccc;
                        }
                }

            }
        }
        if (ad == 0) {
            if (ex.rosu.getState()) {  //com joaca cu piesele negre
                id = 1;
                m1[id] = new Mutare();
                m1[id].eval = evaluare1(P, 1);
            }
            if (ex.negru.getState()) {  //com joaca cu piesele negre
                id = 1;
                m1[id] = new Mutare();
                m1[id].eval = evaluare1(P, 0);
            }

        }

        Mm = new Mutare();
        Mm.i1 = m1[id].i1;
        Mm.j1 = m1[id].j1;
        Mm.i2 = m1[id].i2;
        Mm.j2 = m1[id].j2;

        return m1[id].eval;

    }

    public void muta_computer() {
        int nmrn, nmra, ci, cj, sw = 1, ct, ind = 0;
        Mutare xx;
        nmrn = nr_piese_negre();
        nmra = nr_piese_albe();
        if (ex.usor.getState()) {
            if (ex.rosu.getState()) {
                if (nmrn == 0) {
                    JOptionPane.showMessageDialog(fereastraPrincipala, ex.nume.getText() + " a castigat");
                    for (ci = 1; ci <= 8; ci++)
                        for (cj = 1; cj <= 8; cj++) {
                            a[ci][cj].setEnabled(false);
                        }
                } else {
                    if (nmra == 0) {
                        JOptionPane.showMessageDialog(fereastraPrincipala, "A castigat computerul");
                        for (ci = 1; ci <= 8; ci++)
                            for (cj = 1; cj <= 8; cj++) {
                                a[ci][cj].setEnabled(false);
                            }
                    } else {
                        minmax();
                        muta_n_computer(x.i1, x.j1, x.i2, x.j2);
                    }

                }
                nmra = nr_piese_albe();
                if (nmra == 0) {
                    JOptionPane.showMessageDialog(fereastraPrincipala, "A castigat computerul");
                    for (ci = 1; ci <= 8; ci++)
                        for (cj = 1; cj <= 8; cj++) {
                            a[ci][cj].setEnabled(false);
                        }
                }
                for (ci = 1; ci <= 8; ci++)
                    for (cj = 1; cj <= 8; cj++) {
                        if (A1[ci][cj].piesa == true && A1[ci][cj].culoare == 0) {
                            ind++;
                            ct = mutari_disponibile_a(ci, cj);
                            if (ct == 0)
                                sw = 0;
                        }
                    }
                if (sw == 1 && ind > 0) {
                    JOptionPane.showMessageDialog(fereastraPrincipala, "Ai ramas fara mutari valide ! Computerul a castigat !");
                    for (ci = 1; ci <= 8; ci++)
                        for (cj = 1; cj <= 8; cj++) {
                            a[ci][cj].setEnabled(false);
                        }
                }
            }
            if (ex.negru.getState()) {
                if (nmra == 0) {
                    JOptionPane.showMessageDialog(fereastraPrincipala, ex.nume.getText() + " a castigat");
                    for (ci = 1; ci <= 8; ci++)
                        for (cj = 1; cj <= 8; cj++) {
                            a[ci][cj].setEnabled(false);
                        }
                } else {
                    if (nmrn == 0) {
                        JOptionPane.showMessageDialog(fereastraPrincipala, "A castigat computerul");
                        for (ci = 1; ci <= 8; ci++)
                            for (cj = 1; cj <= 8; cj++) {
                                a[ci][cj].setEnabled(false);
                            }
                    } else {
                        minmax();
                        muta_a_computer(x.i1, x.j1, x.i2, x.j2);
                    }

                }
                nmrn = nr_piese_negre();
                if (nmrn == 0) {
                    JOptionPane.showMessageDialog(fereastraPrincipala, "A castigat computerul");
                    for (ci = 1; ci <= 8; ci++)
                        for (cj = 1; cj <= 8; cj++) {
                            a[ci][cj].setEnabled(false);
                        }
                }
                for (ci = 1; ci <= 8; ci++)
                    for (cj = 1; cj <= 8; cj++) {
                        if (A1[ci][cj].piesa == true && A1[ci][cj].culoare == 1) {
                            ind++;
                            ct = mutari_disponibile_n(ci, cj);
                            if (ct == 0)
                                sw = 0;
                        }
                    }
                if (sw == 1 && ind > 0) {
                    JOptionPane.showMessageDialog(fereastraPrincipala, "Ai ramas fara mutari valide ! Computerul a castigat !");
                    for (ci = 1; ci <= 8; ci++)
                        for (cj = 1; cj <= 8; cj++) {
                            a[ci][cj].setEnabled(false);
                        }
                }
            }
        }
        if (ex.mediu.getState()) {
            if (ex.rosu.getState()) {
                if (nmrn == 0) {
                    JOptionPane.showMessageDialog(fereastraPrincipala, ex.nume.getText() + " a castigat");
                    for (ci = 1; ci <= 8; ci++)
                        for (cj = 1; cj <= 8; cj++) {
                            a[ci][cj].setEnabled(false);
                        }
                } else {
                    if (nmra == 0) {
                        JOptionPane.showMessageDialog(fereastraPrincipala, "A castigat computerul");
                        for (ci = 1; ci <= 8; ci++)
                            for (cj = 1; cj <= 8; cj++) {
                                a[ci][cj].setEnabled(false);
                            }
                    } else {
                        max = true;
                        minimax(A1, 4);
                        muta_n_computer(Mm.i1, Mm.j1, Mm.i2, Mm.j2);
                    }

                }
                nmra = nr_piese_albe();
                if (nmra == 0) {
                    JOptionPane.showMessageDialog(fereastraPrincipala, "A castigat computerul");
                    for (ci = 1; ci <= 8; ci++)
                        for (cj = 1; cj <= 8; cj++) {
                            a[ci][cj].setEnabled(false);
                        }
                }
                for (ci = 1; ci <= 8; ci++)
                    for (cj = 1; cj <= 8; cj++) {
                        if (A1[ci][cj].piesa == true && A1[ci][cj].culoare == 0) {
                            ind++;
                            ct = mutari_disponibile_a(ci, cj);
                            if (ct == 0)
                                sw = 0;
                        }
                    }
                if (sw == 1 && ind > 0) {
                    JOptionPane.showMessageDialog(fereastraPrincipala, "Ai ramas fara mutari valide ! Computerul a castigat !");
                    for (ci = 1; ci <= 8; ci++)
                        for (cj = 1; cj <= 8; cj++) {
                            a[ci][cj].setEnabled(false);
                        }
                }
            }
            if (ex.negru.getState()) {
                if (nmra == 0) {
                    JOptionPane.showMessageDialog(fereastraPrincipala, ex.nume.getText() + " a castigat");
                    for (ci = 1; ci <= 8; ci++)
                        for (cj = 1; cj <= 8; cj++) {
                            a[ci][cj].setEnabled(false);
                        }
                } else {
                    if (nmrn == 0) {
                        JOptionPane.showMessageDialog(fereastraPrincipala, "A castigat computerul");
                        for (ci = 1; ci <= 8; ci++)
                            for (cj = 1; cj <= 8; cj++) {
                                a[ci][cj].setEnabled(false);
                            }
                    } else {
                        max = true;
                        minimax(A1, 4);
                        muta_a_computer(Mm.i1, Mm.j1, Mm.i2, Mm.j2);
                    }

                }
                nmrn = nr_piese_negre();
                if (nmrn == 0) {
                    JOptionPane.showMessageDialog(fereastraPrincipala, "A castigat computerul");
                    for (ci = 1; ci <= 8; ci++)
                        for (cj = 1; cj <= 8; cj++) {
                            a[ci][cj].setEnabled(false);
                        }
                }
                for (ci = 1; ci <= 8; ci++)
                    for (cj = 1; cj <= 8; cj++) {
                        if (A1[ci][cj].piesa == true && A1[ci][cj].culoare == 1) {
                            ind++;
                            ct = mutari_disponibile_n(ci, cj);
                            if (ct == 0)
                                sw = 0;
                        }
                    }
                if (sw == 1 && ind > 0) {
                    JOptionPane.showMessageDialog(fereastraPrincipala, "Ai ramas fara mutari valide ! Computerul a castigat !");
                    for (ci = 1; ci <= 8; ci++)
                        for (cj = 1; cj <= 8; cj++) {
                            a[ci][cj].setEnabled(false);
                        }
                }
            }
        }

        if (ex.avansat.getState()) {
            if (ex.rosu.getState()) {
                if (nmrn == 0) {
                    JOptionPane.showMessageDialog(fereastraPrincipala, ex.nume.getText() + " a castigat");
                    for (ci = 1; ci <= 8; ci++)
                        for (cj = 1; cj <= 8; cj++) {
                            a[ci][cj].setEnabled(false);
                        }
                } else {
                    if (nmra == 0) {
                        JOptionPane.showMessageDialog(fereastraPrincipala, "A castigat computerul");
                        for (ci = 1; ci <= 8; ci++)
                            for (cj = 1; cj <= 8; cj++) {
                                a[ci][cj].setEnabled(false);
                            }
                    } else {
                        max = true;
                        minimax(A1, 6);
                        muta_n_computer(Mm.i1, Mm.j1, Mm.i2, Mm.j2);
                    }

                }
                nmra = nr_piese_albe();
                if (nmra == 0) {
                    JOptionPane.showMessageDialog(fereastraPrincipala, "A castigat computerul");
                    for (ci = 1; ci <= 8; ci++)
                        for (cj = 1; cj <= 8; cj++) {
                            a[ci][cj].setEnabled(false);
                        }
                }
                for (ci = 1; ci <= 8; ci++)
                    for (cj = 1; cj <= 8; cj++) {
                        if (A1[ci][cj].piesa == true && A1[ci][cj].culoare == 0) {
                            ind++;
                            ct = mutari_disponibile_a(ci, cj);
                            if (ct == 0)
                                sw = 0;
                        }
                    }
                if (sw == 1 && ind > 0) {
                    JOptionPane.showMessageDialog(fereastraPrincipala, "Ai ramas fara mutari valide ! Computerul a castigat !");
                    for (ci = 1; ci <= 8; ci++)
                        for (cj = 1; cj <= 8; cj++) {
                            a[ci][cj].setEnabled(false);
                        }
                }
            }
            if (ex.negru.getState()) {
                if (nmra == 0) {
                    JOptionPane.showMessageDialog(fereastraPrincipala, ex.nume.getText() + " a castigat");
                    for (ci = 1; ci <= 8; ci++)
                        for (cj = 1; cj <= 8; cj++) {
                            a[ci][cj].setEnabled(false);
                        }
                } else {
                    if (nmrn == 0) {
                        JOptionPane.showMessageDialog(fereastraPrincipala, "A castigat computerul");
                        for (ci = 1; ci <= 8; ci++)
                            for (cj = 1; cj <= 8; cj++) {
                                a[ci][cj].setEnabled(false);
                            }
                    } else {
                        max = true;
                        minimax(A1, 6);
                        muta_a_computer(Mm.i1, Mm.j1, Mm.i2, Mm.j2);
                    }

                }
                nmrn = nr_piese_negre();
                if (nmrn == 0) {
                    JOptionPane.showMessageDialog(fereastraPrincipala, "A castigat computerul");
                    for (ci = 1; ci <= 8; ci++)
                        for (cj = 1; cj <= 8; cj++) {
                            a[ci][cj].setEnabled(false);
                        }
                }
                for (ci = 1; ci <= 8; ci++)
                    for (cj = 1; cj <= 8; cj++) {
                        if (A1[ci][cj].piesa == true && A1[ci][cj].culoare == 1) {
                            ind++;
                            ct = mutari_disponibile_n(ci, cj);
                            if (ct == 0)
                                sw = 0;
                        }
                    }
                if (sw == 1 && ind > 0) {
                    JOptionPane.showMessageDialog(fereastraPrincipala, "Ai ramas fara mutari valide ! Computerul a castigat !");
                    for (ci = 1; ci <= 8; ci++)
                        for (cj = 1; cj <= 8; cj++) {
                            a[ci][cj].setEnabled(false);
                        }
                }
            }
        }

/*    System.out.println("Mutarile negre:");
    toate_mutarile_n(A1);
    System.out.println("Mutarile albe:");
    toate_mutarile_a(A1); */
        randn();
    }

    public static void main(String args[]) {

        ex = new Setari();

    }

    public void actionPerformed(ActionEvent e) {
        if (rand == true) {

            if (ex.rosu.getState()) {
                for (i = 1; i <= 8; i++)
                    for (j = 1; j <= 8; j++) {
                        if ((a[i][j] == e.getSource()) && (nr % 2 != 0)) {
                            nr++;
                            if (A[k][l].rege == true) {
                                if (((i == k + 1 && j == l - 1) || (i == k + 1 && j == l + 1) || (i == k - 1 && j == l + 1) || (i == k - 1 && j == l - 1)) && A[i][j].piesa == false) {
                                    egal++;
                                    muta_a_nume(k, l, i, j);
                                } else {
                                    if (((i == k + 2 && j == l - 2) || (i == k + 2 && j == l + 2) || (i == k - 2 && j == l + 2) || (i == k - 2 && j == l - 2)) && A[i][j].piesa == false && A[(i + k) / 2][(j + l) / 2].piesa == true && A[(i + k) / 2][(j + l) / 2].culoare == 1) {
                                        A[(i + k) / 2][(j + l) / 2].piesa = false;
                                        A[(i + k) / 2][(j + l) / 2].rege = false;
                                        a[(i + k) / 2][(j + l) / 2].setIcon(null);
                                        egal = 0;
                                        muta_a_nume(k, l, i, j);
                                    } else
                                        JOptionPane.showMessageDialog(fereastraPrincipala, "Mutare nepermisa!");
                                }
                            } else {
                                if (((i == k + 1 && j == l - 1) || (i == k + 1 && j == l + 1)) && A[i][j].piesa == false) {
                                    egal++;
                                    muta_a_nume(k, l, i, j);
                                } else {
                                    if (((i == k + 2 && j == l - 2) || (i == k + 2 && j == l + 2)) && A[i][j].piesa == false && A[(i + k) / 2][(j + l) / 2].piesa == true && A[(i + k) / 2][(j + l) / 2].culoare == 1) {
                                        A[(i + k) / 2][(j + l) / 2].piesa = false;
                                        A[(i + k) / 2][(j + l) / 2].rege = false;
                                        a[(i + k) / 2][(j + l) / 2].setIcon(null);
                                        egal = 0;
                                        muta_a_nume(k, l, i, j);
                                    } else
                                        JOptionPane.showMessageDialog(fereastraPrincipala, "Mutare nepermisa!");
                                }
                            }
                            break;
                        }

                        if ((a[i][j] == e.getSource()) && (nr % 2 == 0) && A[i][j].culoare == 0 && A[i][j].piesa == true) {
                            k = i;
                            l = j;
                            nr++;
                        }
                    }
            }

            if (ex.negru.getState()) {
                for (i = 1; i <= 8; i++)
                    for (j = 1; j <= 8; j++) {
                        if ((a[i][j] == e.getSource()) && (nr % 2 != 0)) {
                            nr++;
                            if (A[k][l].rege == true) {
                                if (((i == k + 1 && j == l - 1) || (i == k + 1 && j == l + 1) || (i == k - 1 && j == l + 1) || (i == k - 1 && j == l - 1)) && A[i][j].piesa == false) {
                                    egal++;
                                    muta_n_nume(k, l, i, j);
                                } else {
                                    if (((i == k + 2 && j == l - 2) || (i == k + 2 && j == l + 2) || (i == k - 2 && j == l + 2) || (i == k - 2 && j == l - 2)) && A[i][j].piesa == false && A[(i + k) / 2][(j + l) / 2].piesa == true && A[(i + k) / 2][(j + l) / 2].culoare == 0) {
                                        A[(i + k) / 2][(j + l) / 2].piesa = false;
                                        A[(i + k) / 2][(j + l) / 2].rege = false;
                                        a[(i + k) / 2][(j + l) / 2].setIcon(null);
                                        egal = 0;
                                        muta_n_nume(k, l, i, j);
                                    } else
                                        JOptionPane.showMessageDialog(fereastraPrincipala, "Mutare nepermisa!");
                                }
                            } else {
                                if (((i == k - 1 && j == l - 1) || (i == k - 1 && j == l + 1)) && A[i][j].piesa == false) {
                                    egal++;
                                    muta_n_nume(k, l, i, j);
                                } else {
                                    if (((i == k - 2 && j == l - 2) || (i == k - 2 && j == l + 2)) && A[i][j].piesa == false && A[(i + k) / 2][(j + l) / 2].piesa == true && A[(i + k) / 2][(j + l) / 2].culoare == 0) {
                                        A[(i + k) / 2][(j + l) / 2].piesa = false;
                                        A[(i + k) / 2][(j + l) / 2].rege = false;
                                        a[(i + k) / 2][(j + l) / 2].setIcon(null);
                                        egal = 0;
                                        muta_n_nume(k, l, i, j);
                                    } else
                                        JOptionPane.showMessageDialog(fereastraPrincipala, "Mutare nepermisa!");
                                }
                            }
                            break;
                        }

                        if ((a[i][j] == e.getSource()) && (nr % 2 == 0) && A[i][j].culoare == 1 && A[i][j].piesa == true) {
                            k = i;
                            l = j;
                            nr++;
                        }
                    }
            }
        }


    }

}



