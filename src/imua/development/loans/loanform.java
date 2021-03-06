/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development.loans;

//import static imua.development.Deposit.typeoftransaction;
import imua.development.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Kamau
 */
public class loanform extends javax.swing.JFrame {
static int y=0;
  private String filePath;
    private String fileurlp = null;
    private String balance ="0";
    private String typeOfAccount=null;
    String toLoan[];
     String accounts[];
   // static String with = "withdrawal";
   // static String typeoftransaction = null;
   // static String title = null;
   // private int p = 0;
    /**
     * Creates new form loan
     */
    public loanform() {
        initComponents();
        setTilteImage();
        findLoans();
       // accountsTypes();
        
    }
     public void findLoans()
  {
      Methods m=new Methods();
    ArrayList<String> usersList =  m.ListLoanTypes();
   accounts=new String[usersList.size()];
   usersList.toArray(accounts);
   accountsTypes();
  }
     private void accountsTypes() {
      //  = {"Regular Acc", "Projects Acc", "Table Acc"};
        toLoan = accounts;
        setAccountModel();
    }
      private void setAccountModel() {
        jComboBox1.removeAllItems();
        jComboBox1.addItem("choose");
        for (int a = 0; a < toLoan.length; a++) {

            jComboBox1.addItem(toLoan[a]);
        }
    }
    private void setTilteImage(){
        try {
            Methods n=new Methods();
            String t= n.setTitle();
            this.setTitle(t);
            String i=n.setIconImage();
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(i)));
            
            String col=n.selectcolor();
            Color c=new Color(Integer.parseInt(col));
            jPanel1.setBackground(c);
              Container cont=this.getContentPane();
            cont.setBackground(c);
        } catch (Exception ex) {
            Logger.getLogger(loanform.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    private String fetchBalance(String where, String value) {
        String balance = "0";
        try {
            Methods m = new Methods();
            Connection con = m.getConnection();
            Statement st = con.createStatement();
            Statement st1 = con.createStatement();
            String withi = "withdrawal";
            String depo = "deposits";
//fetches total deposits
            String searchQuery = "SELECT SUM(amount) FROM `transactions` WHERE `" + where + "` ="
                    + " '" + value + "'AND `typeoftransaction`='" + depo + "'";
//fetch total withdrawals
            String searchQuery2 = "SELECT SUM(amount) FROM `transactions` WHERE `" + where + "` ="
                    + " '" + value + "'AND `typeoftransaction`='" + withi + "'";

            ResultSet rs = st.executeQuery(searchQuery);

            ResultSet rs1 = st1.executeQuery(searchQuery2);

            if (rs.next()&& rs1.next())
            {
 
                double a = 0;
                double b = 0;

                try 
                {
//subtracts withdrawals from total deposits to get current account balance
                    if(rs1.getString(1)==null&&rs.getString(1)==null){
                          JOptionPane.showMessageDialog(null, "Has no deposit");
                    }
                    else{
                    a = Double.valueOf(rs1.getString(1));
                    b = Double.valueOf(rs.getString(1));
                    balance = String.valueOf(b - a);
                    }

                } 
                catch (Exception n) 
                {

                    balance = rs.getString(1);

                }
            } 
            else if (rs.next()) 
            {
JOptionPane.showMessageDialog(null,"rs ");
                balance = rs.getString(1);
            } 
            else 
            {
                JOptionPane.showMessageDialog(null,"no");
//if no deposits found in rs query excution
                JOptionPane.showMessageDialog(null, "Has no deposit");
            }
            st.close();
            st1.close();
            rs.close();
            rs1.close();
            con.close();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex);
            Logger.getLogger(loanform.class.getName()).log(Level.SEVERE, null, ex);
        }

        return balance;
    }
//fetches customers details from user tbl by id or account no(where,value parameters)
    public void fetchUserDetails(String value, String where) {

        try {
            Methods m = new Methods();
            Connection con = m.getConnection();
            Statement st = con.createStatement();
            String searchQuery = "SELECT * FROM `users` WHERE `" + where + "` = '" + value + "'";
            ResultSet rs = st.executeQuery(searchQuery);
            while (rs.next()) {
                txtSirname.setText(rs.getString("fname"));
                txtOtherName.setText(rs.getString("sname"));
                txtPhone.setText(rs.getString("phone"));
              //  txtAddress.setText(rs.getString("address"));
                txtTown.setText(rs.getString("town"));
                txtAccountType.setText(rs.getString("acounttype"));
                txtCounty.setText(rs.getString("county"));
                txtOccupation.setText(rs.getString("occupation"));

                try {
                   
//call fetchbalance method and give it the same parameters used to fetch user details
                        balance = fetchBalance(where, value);
                    
                    //set txtBalance to the returned value
                    txtBalance.setText(balance);
                    showimg(where,value);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                    Logger.getLogger(loanform.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            st.close();
            rs.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(loanform.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//profile photo loading done here
    public void showimg(String where,String value)
            throws Exception 
    {
        
        this.img.setIcon(null);
        this.img.setText(" no image");
        try 
        {
            Methods m = new Methods();
           
            Connection con = m.getConnection();
            Statement st2 = con.createStatement();
///
            ResultSet res7 = st2.executeQuery("SELECT imgurl FROM users  WHERE `"+where+"`=' " + value + "'");
            if (res7.next())
            {
                this.filePath = res7.getString("imgurl");

                st2.close();
                res7.close();
                con.close();
                
                String op = "image";
                if (this.filePath.equals(op))
                {
                    this.img.setIcon(null);
                    this.img.setIcon(null);
                    this.img.setText(" no image");
                } else 
                {
                    BufferedImage img = null;
                    try 
                    {
                        img = ImageIO.read(new java.io.File(this.filePath));
                        this.fileurlp = this.filePath.replace("\\", "\\\\");
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "error loading image \n  make sure image is in images folder ");

                        this.img.setIcon(null);
                        this.img.setText(" no image");
                    }
                    Image dimg = img.getScaledInstance(this.img.getWidth(), this.img.getHeight(), 4);

                    ImageIcon icon = new ImageIcon(dimg);
                    this.img.setText("");
                    this.img.setIcon(icon);
                }
                
            }
            else 
            {
                JOptionPane.showMessageDialog(null, "error loading image \n  make sure image is in images folder ");
            }
        } 
        catch (Exception ex) 
        {
            System.out.println(ex.getMessage());
        }
    }
 public void executeSQlQuery(String query, String message)
  {
   Methods m=new Methods();
        Connection con = m.getConnection();
    try
    {
      Statement st = con.createStatement();
      if (st.executeUpdate(query) == 1)
      {
       
        
        JOptionPane.showMessageDialog(null, message + " Succefully");
        clear();
        //refreshTable();
      }
      else
      {
        JOptionPane.showMessageDialog(null, "Not " + message);
        
      }
      st.close();
      con.close();
    }
    catch (SQLException  ex)
        
    {      JOptionPane.showMessageDialog(null, "Not " + message);
        Logger.getLogger(loanform.class.getName()).log(Level.SEVERE, null, ex);
       //  JOptionPane.showMessageDialog(null, "Not " + ex);
    }
  }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        img = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtSirname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtOtherName = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        txtTown = new javax.swing.JTextField();
        txtCounty = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtLoan = new javax.swing.JTextField();
        BtnConfirm = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txtOccupation = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtBalance = new javax.swing.JTextField();
        txtAccountType = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        installments = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        txtPeriod = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(300, 80));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        img.setText("Pic");

        jLabel3.setText("ID No.");

        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        jLabel4.setText("Sir Name");

        jLabel5.setText("Other Names");

        jLabel6.setText("Mobile No.");

        jLabel7.setText("Town");

        jLabel8.setText("County");

        jLabel9.setText("Occupation");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 51, 102));
        jLabel10.setText("Loan");

        txtLoan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtLoan.setForeground(new java.awt.Color(255, 0, 51));
        txtLoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLoanKeyReleased(evt);
            }
        });

        BtnConfirm.setText("Confirm");
        BtnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnConfirmActionPerformed(evt);
            }
        });

        jButton2.setText("Request");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N
        jLabel18.setText("Customer Loan Request Information Form");

        jLabel19.setText("Account Type");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 51, 153));
        jLabel20.setText("Account Balance");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Account");

        txtBalance.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtBalance.setForeground(new java.awt.Color(255, 51, 255));
        txtBalance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBalanceActionPerformed(evt);
            }
        });

        txtAccountType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAccountTypeActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Loan Info");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("User Info");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("Gatantee Info");

        jButton1.setText("Garantee Form");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Check");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel20))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel19)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtSirname, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCounty, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAccountType, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtOtherName, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTown, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtOccupation, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(jLabel21))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(27, 27, 27)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel25)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel24)
                            .addComponent(BtnConfirm)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtLoan)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(installments, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(142, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(jLabel18)
                .addContainerGap(224, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel27))
                .addGap(164, 164, 164))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(BtnConfirm))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(22, 22, 22))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(18, 18, 18))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSirname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtLoan, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOtherName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(77, 77, 77)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(installments)
                                    .addComponent(txtPeriod))
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)))))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCounty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtOccupation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addGap(30, 30, 30)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtAccountType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAccountTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAccountTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAccountTypeActionPerformed

    private void txtBalanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBalanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBalanceActionPerformed

    private void BtnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnConfirmActionPerformed
        try

        {
            if (txtId.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "FILL ONE OF THE FIELDS");
            }

           
           

            else
            {
                int v=Integer.valueOf(txtId.getText());
                threadExecute(txtId.getText(), "id");
            }

        }
        catch(Exception r)
        {
            JOptionPane.showMessageDialog(null, "Enter correct Value For ID or Acc No");
        }
    }//GEN-LAST:event_BtnConfirmActionPerformed

     public void threadExecute(String value, String where){
Thread log=new Thread(){
public void run(){
    
    try{
   
   fetchUserDetails(value,  where);
      
    }
      catch(Exception b){
         System.out.println("Error");
      }
} 
      }   ;  
      log.start();
  }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        getId();
        imua.development.loans.garanters n = new imua.development.loans.garanters();
        n.setVisible(true);
      // this.setEnabled(false);
    }//GEN-LAST:event_jButton1ActionPerformed
public  String getId(){
   String   ID=null;
    if (txtId.getText().isEmpty()){
        
    }
    else{
  garanters.id=txtId.getText()+jComboBox1.getSelectedItem().toString();
    }
    return ID;
}
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    switch (checkButton) {
        case 0:
            JOptionPane.showMessageDialog(null, "CHECK PROCEDURE NOT FOLLOWED","Warning",JOptionPane.ERROR_MESSAGE);
            break;
        case 3:
            JOptionPane.showMessageDialog(null, "LOAN LIMIT EXCEDED","ERROR",JOptionPane.ERROR_MESSAGE);
            break;
        case 2:
            check();
            break;
        case 1:
            check();
            break;
        default:
            JOptionPane.showMessageDialog(null, "ERROR","ERROR",JOptionPane.ERROR_MESSAGE);
    }
       
    }//GEN-LAST:event_jButton2ActionPerformed
String loanType;
    public void check(){
    if(loanType==null||
            txtId.getText().isEmpty()||
           // installments.getText().isEmpty()||
           // txtPeriod.getText().isEmpty()||
            txtLoan.getText().isEmpty()){
        JOptionPane.showMessageDialog(null, "SOME DATA IS MISSING");
    }
    else{
        insert();
    }
}
    
    private void insert(){
        int dialogButton=
        
        JOptionPane.showConfirmDialog(null, "Confirm This \n  LOAN OF  "+ this.txtLoan.getText() +"  \n TO  "+txtId.getText());
        if(dialogButton==JOptionPane.YES_OPTION){
        if(y>0){
            String garantersstatus="full";
            String i="Accepted";
            
            ActualInsert(garantersstatus,i);
          
          
        }
        else if (y<=1){
            
            Object []options={"GO BACK","CONTINUE ANYWAY","CANCEL"};
           int btn= JOptionPane.showOptionDialog(null, "GURANTERS FORM NOT FIELD","CONFIRM",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
            switch (btn) {
                case 1:
                    String garantersstatus="void";
                    String i="Not Accepted";
                    ActualInsert(garantersstatus,i);
                    break;
            //JOptionPane.showConfirmDialog(null, " Guarantors form Not Filled Click ye ");
            // JOptionPane.showMessageDialog(null, btn);
                case 2:
                    clear();
                    break;
            //clear();
                default:
                    break;
            }
        }
    }
}
    public void ActualInsert(String garantersstatus,String loanStatus){
        String query = "INSERT INTO applications(`id`,`type`, `amount`,`status`,`guranters`,`name`,`updated_at`)"
             + " VALUES ('" + this.txtId.getText() + "','" + this.loanType+ "','" + this.txtLoan.getText()+ "','" + loanStatus + "',"
             + "'" + garantersstatus+ "'"
             + ",'" + this.txtSirname.getText() + "',now())";
          
             executeSQlQuery(query, "Inserted");
    }
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
       Main.a=1; // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed
    private void clear(){
        txtId.setText("");
        txtLoan.setText("");
        txtSirname.setText("");
        txtBalance.setText("");
        loanType=null;
        // txtAccountNo.setText("");
  txtAccountType.setText("");
     txtBalance.setText("");
   txtCounty.setText("");
   //txtId;
   // txtLoan;
     txtOccupation.setText("");
   txtOtherName.setText("");
     txtPeriod.setText("");
 txtPhone.setText("");
     txtSirname.setText("");
     txtTown.setText("");
     jComboBox1.setSelectedIndex(0);
     this.img.setIcon(null);
     this.img.setText("");
      int y=0;
   filePath=null;
     fileurlp = null;
    balance ="0";
     typeOfAccount=null;
     ballevel="";
     checkButton=0;
        
        
    }
    int checkButton=0;
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(loanType==null){
            JOptionPane.showMessageDialog(null, "Select LoanType First");
        }
        else{
          Double borrowedBal=  getAccountBalanceNotFixed();
            String loan= txtLoan.getText();
            String accBal=txtBalance.getText();
            getLoanTypeInfo();
            //JOptionPane.showMessageDialog(null, ballevel);
            double requestedAmount=Double.valueOf(loan);
            double accountBalance=Double.valueOf(accBal);
            double balLevelRequired=Double.valueOf(ballevel);
            try{
                
                if(balLevelRequired==0||balLevelRequired==0.0){
                    JOptionPane.showMessageDialog(null,"NO LOAN LIMIT REQUIRED FOR THIS LOAN TYPE\nPROCEED","Warning",JOptionPane.INFORMATION_MESSAGE);
                    checkButton=2;
                }
                else{
                    
                  if(requestedAmount>(accountBalance-borrowedBal)*balLevelRequired){
                    JOptionPane.showMessageDialog(null, "Loan Limit Exceded",+borrowedBal+"",JOptionPane.ERROR_MESSAGE);
                    checkButton=3;
                  }
                
                
                  else{
                    JOptionPane.showMessageDialog(null, "Proceed","Accepted",JOptionPane.INFORMATION_MESSAGE);
                    checkButton=1;
                  }
                
                }

            }
            catch(Exception a){

            }

        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtLoanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoanKeyReleased

    }//GEN-LAST:event_txtLoanKeyReleased

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        //  String accounts[] = {"Regular Acc", "Projects Acc", "Table Acc"};
        if (evt.getItem() == "Loan Type"||evt.getItem() == "choose" && evt.getStateChange() == ItemEvent.SELECTED) {

        } //else if (evt.getItem().toString().contains("Business") && evt.getStateChange() == ItemEvent.SELECTED) {

            else {
                loanType=evt.getItem().toString();
            }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        try

        {
            if (txtId.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "FILL ONE OF THE FIELDS");
            }

            else
            {
                int v=Integer.valueOf(txtId.getText());
             threadExecute(txtId.getText(), "id");
            }

        }
        catch(Exception r)
        {
            JOptionPane.showMessageDialog(null, "Enter correct Value For ID or Acc No");
        }
    }//GEN-LAST:event_txtIdActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        clear();
    }//GEN-LAST:event_jButton4ActionPerformed

    String ballevel;
   private void getLoanTypeInfo(){
     try {
          Methods m = new Methods();
          Connection con = m.getConnection();
          Statement st = con.createStatement();
         
          String searchQuery = "SELECT balance FROM `accounttypes` WHERE `name` = '" +loanType+ "'";
          ResultSet rs = st.executeQuery(searchQuery);
          if(rs.next()){
            
              ballevel=rs.getString("balance");
             
            
              
              }
          else{
              JOptionPane.showMessageDialog(null, "error");
          }
          
          
      } catch (SQLException ex) {
          Logger.getLogger(loanform.class.getName()).log(Level.SEVERE, null, ex);
      }
}


public Double getAccountBalanceNotFixed(){
    Double xc=0.0;
   int a=toLoan.length;
    String tdpy="0";
   for(int b=0;b<a;b++){
       
       try {
           Methods m = new Methods();
           Connection con = m.getConnection();
           Statement st = con.createStatement();
           
           String searchQuery = "SELECT loanRequested FROM `iloans` WHERE `loantype` = '" +toLoan[b]+ "'AND `customerid`='"+txtId.getText()+"'";
           ResultSet rs = st.executeQuery(searchQuery);
           if(rs.next()){
               
               
               
            xc=xc+    Double.valueOf(rs.getString("loanRequested"))/specifBalance(toLoan[b]);
              // ballevel=rs.getString("balance");
               
               
               
           }
       } catch (SQLException ex) {
           Logger.getLogger(loanform.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       
       
   }
    
    
    
    return xc;
    
}
public Double specifBalance(String where){
    Double val=0.0;
       try {
           Methods m = new Methods();
           Connection con = m.getConnection();
           Statement st = con.createStatement();
           
           String searchQuery = "SELECT balance FROM `accounttypes` WHERE `name` = '" +where+ "'";
           ResultSet rs = st.executeQuery(searchQuery);
           if(rs.next()){
               
               val=Double.valueOf(rs.getString("balance"));
               
               
               
           }
       } catch (SQLException ex) {
           Logger.getLogger(loanform.class.getName()).log(Level.SEVERE, null, ex);
       } 
       return val;
}

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(loanform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loanform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loanform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loanform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loanform().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnConfirm;
    private javax.swing.JLabel img;
    private javax.swing.JLabel installments;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtAccountType;
    private javax.swing.JTextField txtBalance;
    private javax.swing.JTextField txtCounty;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLoan;
    private javax.swing.JTextField txtOccupation;
    private javax.swing.JTextField txtOtherName;
    private javax.swing.JLabel txtPeriod;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSirname;
    private javax.swing.JTextField txtTown;
    // End of variables declaration//GEN-END:variables
}
