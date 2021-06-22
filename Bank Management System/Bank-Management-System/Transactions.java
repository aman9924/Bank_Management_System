import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Transactions extends JFrame implements ActionListener{

    JLabel l1;
    JButton b1,b2,b3,b4,b5,b6,b7;
    String pin;
    Transactions(String pin){
        this.pin = pin;
        getContentPane().setBackground(new Color(248, 246, 234));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("debit-card.png"));
        Image i2 = i1.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l14 = new JLabel(i3);
        l14.setBounds(150, 0, 100, 100);
        add(l14);
        l1 = new JLabel("Please Select Your Transaction");
        l1.setFont(new Font("System", Font.BOLD, 35));


        b1 = new JButton("DEPOSIT");
        b1.setFont(new Font("Arial",Font.BOLD,20));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.white);
        b1.setBorder(BorderFactory.createBevelBorder(1,Color.BLUE,Color.BLUE));

        b2 = new JButton("CASH WITHDRAWL");
        b2.setFont(new Font("Arial",Font.BOLD,20));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.white);
        b2.setBorder(BorderFactory.createBevelBorder(1,Color.BLUE,Color.BLUE));
        b3 = new JButton("FAST CASH");
        b3.setFont(new Font("Arial",Font.BOLD,20));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.white);
        b3.setBorder(BorderFactory.createBevelBorder(1,Color.BLUE,Color.BLUE));
        b4 = new JButton("MINI STATEMENT");
        b4.setBackground(Color.BLACK);
        b4.setForeground(Color.white);
        b4.setFont(new Font("Arial",Font.BOLD,20));
        b4.setBorder(BorderFactory.createBevelBorder(1,Color.BLUE,Color.BLUE));
        b5 = new JButton("PIN CHANGE");
        b5.setBackground(Color.BLACK);
        b5.setForeground(Color.white);
        b5.setFont(new Font("Arial",Font.BOLD,20));
        b5.setBorder(BorderFactory.createBevelBorder(1,Color.BLUE,Color.BLUE));
        b6 = new JButton("BALANCE ENQUIRY");
        b6.setFont(new Font("Arial",Font.BOLD,20));
        b6.setBackground(Color.BLACK);
        b6.setForeground(Color.white);
        b6.setBorder(BorderFactory.createBevelBorder(1,Color.BLUE,Color.BLUE));
        b7 = new JButton("EXIT");
        b7.setFont(new Font("Arial",Font.BOLD,20));
        b7.setBackground(Color.BLACK);
        b7.setForeground(Color.white);
        b7.setBorder(BorderFactory.createBevelBorder(1,Color.BLUE,Color.BLUE));
        b1.setFocusable(false);
        b2.setFocusable(false);
        b3.setFocusable(false);
        b4.setFocusable(false);
        b5.setFocusable(false);
        b6.setFocusable(false);
        b7.setFocusable(false);

        setLayout(null);

        l1.setBounds(240,35,700,35);
        add(l1);

        b1.setBounds(120,150,230,45);
        add(b1);

        b2.setBounds(600,150,230,45);
        add(b2);

        b3.setBounds(120,220,230,45);
        add(b3);

        b4.setBounds(600,220,230,45);
        add(b4);

        b5.setBounds(120,290,230,45);
        add(b5);

        b6.setBounds(600,290,230,45);
        add(b6);

        b7.setBounds(360,360,230,45);
        add(b7);


        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);


        setSize(960,580);
        setLocation(500,0);
        //setUndecorated(true);
        setVisible(true);



    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==b1){
            setVisible(false);
            new Deposit(pin).setVisible(true);
        }else if(ae.getSource()==b2){
            setVisible(false);
            new Withdrawl(pin).setVisible(true);
        }else if(ae.getSource()==b3){
            setVisible(false);
            new FastCash(pin).setVisible(true);
        }else if(ae.getSource()==b4){
            new MiniStatement(pin).setVisible(true);
        }else if(ae.getSource()==b5){
            setVisible(false);
            new Pin(pin).setVisible(true);
        }else if(ae.getSource()==b6){
            this.setVisible(false);
            new BalanceEnquiry(pin).setVisible(true);
        }else if(ae.getSource()==b7){
            System.exit(0);
        }
    }

    public static void main(String[] args){
        new Transactions("").setVisible(true);
    }
}