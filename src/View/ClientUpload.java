package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;


class ClientUpload extends JFrame implements ActionListener {

    File f1, f2;
    JFileChooser fc;
    JButton b, b2, b1;
    JTextField tf, tf2;
    FileInputStream in;
    Socket s;
    DataOutputStream dout;
    DataInputStream din;
    int i;

    ClientUpload() {
        super("client");
        setTitle("Bioinformatics Toolkit");
        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("/home/kartik/Desktop/Project-I/Bioinformatics-Toolkit/Bioinformatics-Toolkit/src/TU_LOGO")));
        setLayout(new FlowLayout());
        tf = new JTextField();
        tf.setBounds(800, 50, 270, 30);
        add(tf);

        tf2 = new JTextField();
        tf2.setBounds(800, 100, 270, 30);
        add(tf2);

        b = new JButton("FILE 1");
        b.setBounds(1100, 50, 80, 30);
        add(b);
        b2 = new JButton("FILE 2");
        b2.setBounds(1100, 100, 80, 30);
        add(b2);

        b.addActionListener(this);
        b2.addActionListener(this);
        b1 = new JButton("Calculate");
        b1.setBounds(1100, 150, 80, 30);
        add(b1);
        b1.addActionListener(this);
        fc = new JFileChooser();
        setLayout(null);
        setSize(1280, 720);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == b) {
                int x = fc.showOpenDialog(null);
                if (x == JFileChooser.APPROVE_OPTION) {
                    f1 = fc.getSelectedFile();
                    tf.setText(f1.getAbsolutePath());
                }
            } else if(e.getSource() == b2) {
                int x = fc.showOpenDialog(null);
                if (x == JFileChooser.APPROVE_OPTION) {
                    f2 = fc.getSelectedFile();
                    tf2.setText(f2.getAbsolutePath());
                }
            } else if (e.getSource() == b1) {
                if(f1 != null && f2 != null) {
                    new Home(f1, f2);
                }
                else {
                    System.out.println("x");
                }
            }
        } catch (Exception ex) {

        }
    }

    public static void main(String... d) throws IOException {
        try {
            new ClientUpload();
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }

}