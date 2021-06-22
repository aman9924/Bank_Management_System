import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Pin extends JFrame implements ActionListener{

    JPasswordField t1,t2;
    JButton b1,b2;
    JLabel l1,l2,l3;
    String pin;
    Pin(String pin){
        this.pin = pin;

        JLabel title = new JLabel("Change PIN");
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("mini.png"));
        Image i2 = i1.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l14 = new JLabel(i3);
        l14.setBounds(150, 0, 100, 100);
        add(l14);

        title.setFont(new Font("System", Font.BOLD, 35));

        title.setBounds(240,41,700,35);
        add(title);



        l2 = new JLabel("New PIN:");
        l2.setForeground(Color.BLUE);
        l2.setFont(new Font("System", Font.BOLD, 20));

        l3 = new JLabel("Re-Enter New PIN:");
        l3.setFont(new Font("System", Font.BOLD, 20));
        l3.setForeground(Color.BLUE);

        t1 = new JPasswordField();
        t1.setFont(new Font("Raleway", Font.BOLD, 25));

        t2 = new JPasswordField();
        t2.setFont(new Font("Raleway", Font.BOLD, 25));

        b1 = new JButton("CHANGE");
        b1.setFont(new Font("Serif",Font.BOLD,20));
        b1.setBackground(new Color(34, 224, 43));
        b1.setBorder(BorderFactory.createEtchedBorder(Color.BLACK,Color.black));
        b2 = new JButton("BACK");
        b2.setFont(new Font("Serif",Font.BOLD,20));
        b2.setBackground(new Color(34, 224, 43));
        b2.setBorder(BorderFactory.createBevelBorder(1,Color.black,Color.black));

        b1.addActionListener(this);
        b2.addActionListener(this);

        setLayout(null);


        l2.setBounds(72,160,150,35);
        add(l2);

        l3.setBounds(72,210,200,35);
        add(l3);

        t1.setBounds(225,160,180,25);
        add(t1);

        t2.setBounds(225,210,180,25);
        add(t2);

        b1.setBounds(160,300,330,35);
        add(b1);

        b2.setBounds(160,360,330,35);
        add(b2);
        setTitle("Change PIN ");
        getContentPane().setBackground(new Color(237, 246, 232) );
        setSize(660, 580);
        setLocation(500,250);
        //setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae){
        try{
            String npin = t1.getText();
            String rpin = t2.getText();

            if(!npin.equals(rpin)){
                JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                return;
            }

            if(ae.getSource()==b1){
                if (t1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Enter New PIN");
                }
                if (t2.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Re-Enter new PIN");
                }

                Conn c1 = new Conn();
                String q1 = "update bank set pin = '"+rpin+"' where pin = '"+pin+"' ";
                String q2 = "update login set pin = '"+rpin+"' where pin = '"+pin+"' ";
                String q3 = "update signup3 set pin = '"+rpin+"' where pin = '"+pin+"' ";

                c1.st.executeUpdate(q1);
                c1.st.executeUpdate(q2);
                c1.st.executeUpdate(q3);

                JOptionPane.showMessageDialog(null, "PIN changed successfully");
                setVisible(false);
                new Transactions(rpin).setVisible(true);

            }else if(ae.getSource()==b2){
                new Transactions(pin).setVisible(true);
                setVisible(false);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new Pin("").setVisible(true);
    }
}
