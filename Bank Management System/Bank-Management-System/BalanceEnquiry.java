import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import java.util.*;

class BalanceEnquiry extends JFrame implements ActionListener {

    JTextField t1, t2;
    JButton b1, b2, b3;
    JLabel l1, l2, l3;
    String pin;

    BalanceEnquiry(String pin) {

        this.pin = pin;

        JLabel title = new JLabel("Balance Enquiry");
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("debit-card.png"));
        Image i2 = i1.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l14 = new JLabel(i3);
        l14.setBounds(150, 0, 100, 100);
        add(l14);

        title.setFont(new Font("System", Font.BOLD, 35));

        title.setBounds(240,35,700,35);
        add(title);

        l1 = new JLabel();
        l1.setForeground(Color.BLUE);
        l1.setFont(new Font("System", Font.BOLD, 25));

        b1 = new JButton("BACK");

        setLayout(null);


        l1.setBounds(60, 190, 700, 35);
        add(l1);

        b1.setBounds(220, 320, 200, 40);
        b1.setFocusable(false);
        b1.setForeground(Color.white);
        b1.setBackground(Color.BLACK);
        b1.setFont(new Font("Serif",Font.BOLD,25));
        b1.setBorder(BorderFactory.createBevelBorder(1,Color.ORANGE,Color.ORANGE));
        add(b1);
        int balance = 0;
        try{
            Conn c1 = new Conn();
            ResultSet rs = c1.st.executeQuery("select * from bank where pin = '"+pin+"'");
            while (rs.next()) {
                if (rs.getString("mode").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
        }catch(Exception e){}

        l1.setText("Your Current Account Balance is Rs "+balance);

        b1.addActionListener(this);
        getContentPane().setBackground(new Color(237, 246, 232) );
        setSize(660, 580);
        //setUndecorated(true);
        setLocation(500, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions(pin).setVisible(true);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("").setVisible(true);
    }
}