import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.sql.*;

public class Withdrawl extends JFrame implements ActionListener{

    JTextField t1,t2;
    JButton b1,b2,b3;
    JLabel l1,l2,l3,l4;
    String pin;
    Withdrawl(String pin){
        this.pin = pin;
        JLabel title = new JLabel("Withdraw money");
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("debit-card.png"));
        Image i2 = i1.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l14 = new JLabel(i3);
        l14.setBounds(150, 0, 100, 100);
        add(l14);

        title.setFont(new Font("System", Font.BOLD, 35));

        title.setBounds(240,35,700,35);
        add(title);

        l1 = new JLabel("MAXIMUM WITHDRAWAL IS RS.10,000");
        l1.setForeground(Color.BLUE);
        l1.setFont(new Font("System", Font.BOLD, 20));

        l2 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        l2.setForeground(Color.BLUE);
        l2.setFont(new Font("System", Font.BOLD, 20));

        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 25));

        b1 = new JButton("WITHDRAW");
        b1.setFont(new Font("Serif",Font.BOLD,20));
        b1.setBackground(new Color(34, 224, 43));
        b1.setBorder(BorderFactory.createEtchedBorder(Color.BLACK,Color.black));
        b2 = new JButton("BACK");
        b2.setFont(new Font("Serif",Font.BOLD,20));
        b2.setBackground(new Color(34, 224, 43));
        b2.setBorder(BorderFactory.createBevelBorder(1,Color.black,Color.black));

        setLayout(null);

        l1.setBounds(150,130,600,30);
        add(l1);

        l2.setBounds(165,180,600,30);
        add(l2);

        t1.setBounds(160,240,330,30);
        add(t1);

        b1.setBounds(160,300,330,35);
        add(b1);

        b2.setBounds(160,360,330,35);
        add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);
        setTitle("Withdraw Money");
        getContentPane().setBackground(new Color(237, 246, 232) );
        setSize(660, 580);
        setLocation(500,250);
        //setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent ae){
        try{
            String amount = t1.getText();
            Date date = new Date();
            if(ae.getSource()==b1){
                if(t1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Withdraw");
                }else{
                    Conn c1 = new Conn();

                    ResultSet rs = c1.st.executeQuery("select * from bank where pin = '"+pin+"'");
                    int balance = 0;
                    while(rs.next()){
                        if(rs.getString("mode").equals("Deposit")){
                            balance += Integer.parseInt(rs.getString("amount"));
                        }else{
                            balance -= Integer.parseInt(rs.getString("amount"));
                        }
                    }
                    if(balance < Integer.parseInt(amount)){
                        JOptionPane.showMessageDialog(null, "Insuffient Balance");
                        return;
                    }

                    c1.st.executeUpdate("insert into bank values('"+pin+"', '"+date+"', 'Withdrawl', '"+amount+"')");
                    JOptionPane.showMessageDialog(null, "Rs. "+amount+" Debited Successfully");

                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                }
            }else if(ae.getSource()==b2){
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("error: "+e);
        }

    }

    public static void main(String[] args){
        new Withdrawl("").setVisible(true);
    }
}
