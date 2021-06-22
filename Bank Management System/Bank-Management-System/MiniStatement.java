import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class MiniStatement extends JFrame implements ActionListener{

    JButton b1, b2;
    JLabel l1;
    MiniStatement(String pin){
        super("Mini Statement");
        getContentPane().setBackground(new Color(248, 246, 234));
        setSize(900,600);
        setLocation(20,20);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        l1 = new JLabel();
        add(l1);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("mini.png"));
        Image i2 = i1.getImage().getScaledInstance(43, 43, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l14 = new JLabel(i3);
        l14.setBounds(320, 0, 100, 100);
        add(l14);


        JLabel l2 = new JLabel("Indian Bank");
        l2.setBounds(400, 40, 250, 40);
        l2.setFont(new Font("MV Boli",Font.BOLD,25));
        add(l2);

        JLabel l3 = new JLabel();
        l3.setBounds(270, 115, 350, 35);
        l3.setFont(new Font("Arial",Font.BOLD,20));
        add(l3);

        JLabel l4 = new JLabel();
        l4.setBounds(295, 400, 300, 20);
        l4.setFont(new Font("Arial",Font.BOLD,20));
        add(l4);

        try{
            Conn c = new Conn();
            ResultSet rs = c.st.executeQuery("select * from login where pin = '"+pin+"'");
            while(rs.next()){
                l3.setText("Card Number:    " + rs.getString("cardno").substring(0, 4) + "XXXXXXXX" + rs.getString("cardno").substring(12));
            }
        }catch(Exception e){}

        try{
            int balance = 0;
            Conn c1  = new Conn();
            ResultSet rs = c1.st.executeQuery("SELECT * FROM bank where pin = '"+pin+"'");
            while(rs.next()){
                l1.setText(l1.getText() + "<html>"+rs.getString("date")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("mode") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");
                if(rs.getString("mode").equals("Deposit")){
                    balance += Integer.parseInt(rs.getString("amount"));
                }else{
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
            l4.setText("Your total Balance is Rs "+balance);
        }catch(Exception e){
            e.printStackTrace();
        }

        setLayout(null);
        b1 = new JButton("Exit");
        b1.setFocusable(false);
        add(b1);

        b1.addActionListener(this);

        l1.setBounds(240, 160, 700, 200);
        l1.setFont(new Font("Arial",Font.PLAIN,18));
        b1.setBounds(340, 500, 200, 35);
        b1.setBackground(Color.black);
        b1.setForeground(Color.white);
        b1.setFont(new Font("Serif",Font.BOLD,20));
    }
    public void actionPerformed(ActionEvent ae){
        this.setVisible(false);
    }

    public static void main(String[] args){
        new MiniStatement("").setVisible(true);
    }

}
