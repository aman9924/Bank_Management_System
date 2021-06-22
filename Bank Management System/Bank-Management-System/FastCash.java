import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JLabel l1, l2;
    JButton b1, b2, b3, b4, b5, b6, b7, b8;
    JTextField t1;
    String pin;

    FastCash(String pin) {
        this.pin = pin;
        getContentPane().setBackground(new Color(248, 246, 234));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("debit-card.png"));
        Image i2 = i1.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l14 = new JLabel(i3);
        l14.setBounds(150, 0, 100, 100);
        add(l14);
        l1 = new JLabel("SELECT WITHDRAWL AMOUNT");
        l1.setFont(new Font("System", Font.BOLD, 35));

        b1 = new JButton("Rs 100");
        b1.setFont(new Font("Arial",Font.BOLD,20));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.white);
        b1.setBorder(BorderFactory.createBevelBorder(1,Color.BLUE,Color.BLUE));
        b2 = new JButton("Rs 500");
        b2.setFont(new Font("Arial",Font.BOLD,20));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.white);
        b2.setBorder(BorderFactory.createBevelBorder(1,Color.BLUE,Color.BLUE));
        b3 = new JButton("Rs 1000");
        b3.setFont(new Font("Arial",Font.BOLD,20));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.white);
        b3.setBorder(BorderFactory.createBevelBorder(1,Color.BLUE,Color.BLUE));
        b4 = new JButton("Rs 2000");
        b4.setBackground(Color.BLACK);
        b4.setForeground(Color.white);
        b4.setFont(new Font("Arial",Font.BOLD,20));
        b4.setBorder(BorderFactory.createBevelBorder(1,Color.BLUE,Color.BLUE));
        b5 = new JButton("Rs 5000");
        b5.setBackground(Color.BLACK);
        b5.setForeground(Color.white);
        b5.setFont(new Font("Arial",Font.BOLD,20));
        b5.setBorder(BorderFactory.createBevelBorder(1,Color.BLUE,Color.BLUE));
        b6 = new JButton("Rs 10000");
        b6.setFont(new Font("Arial",Font.BOLD,20));
        b6.setBackground(Color.BLACK);
        b6.setForeground(Color.white);
        b6.setBorder(BorderFactory.createBevelBorder(1,Color.BLUE,Color.BLUE));
        b7 = new JButton("BACK");
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

    public void actionPerformed(ActionEvent ae) {
        try {
            String amount = ((JButton)ae.getSource()).getText().substring(3); //k
            Conn c = new Conn();
            ResultSet rs = c.st.executeQuery("select * from bank where pin = '"+pin+"'");
            int balance = 0;
            while (rs.next()) {
                if (rs.getString("mode").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            } String num = "17";
            if (ae.getSource() != b7 && balance < Integer.parseInt(amount)) {
                JOptionPane.showMessageDialog(null, "Insuffient Balance");
                return;
            }

            if (ae.getSource() == b7) {
                this.setVisible(false);
                new Transactions(pin).setVisible(true);
            }else{
                Date date = new Date();
                c.st.executeUpdate("insert into bank values('"+pin+"', '"+date+"', 'Withdrawl', '"+amount+"')");
                JOptionPane.showMessageDialog(null, "Rs. "+amount+" Debited Successfully");

                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new FastCash("").setVisible(true);
    }
}
