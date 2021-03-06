/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development.loginClass;

import imua.development.Accounts.Accgroups;
import imua.development.Methods;
import imua.development.UserAcc;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author kimani kogi
 */
public class AddUsers extends javax.swing.JFrame {
HashMap<String,String>hash=new HashMap<String,String>();
ButtonGroup radiog =new ButtonGroup();
Methods m=new Methods();
//String hash="";
    /**
     * Creates new form AddUsers
     */
    public AddUsers() {
        initComponents();
        setTilteImage();
        refresh();
        radiog.add(rbSellectAll);
        radiog.add(rbDiselectAll);
        
        
        
        ActionListener actionListener=new ActionHandler();
        cbAccountsGroups.addActionListener(actionListener);
     cbAddCustomers.addActionListener(actionListener);
   cbAddStaff.addActionListener(actionListener);
    cbAddSystemUsers.addActionListener(actionListener);
   cbBackup.addActionListener(actionListener);
    cbDeposit.addActionListener(actionListener);
    cbGroupCollectionSheet.addActionListener(actionListener);
    cbGroups.addActionListener(actionListener);
    cbLoanApplications.addActionListener(actionListener);
    cbLoanDefaulters.addActionListener(actionListener);
    cbLoanPayment.addActionListener(actionListener);
   cbLoanProcessing.addActionListener(actionListener);
    cbLoanSettings.addActionListener(actionListener);
    cbOrgsAccount.addActionListener(actionListener);
    cbPrefrences.addActionListener(actionListener);
    cbRestore.addActionListener(actionListener);
    cbTodaysLoan.addActionListener(actionListener);
    cbUsersPasswords.addActionListener(actionListener);
    cbViewCustomers.addActionListener(actionListener);
    cbViewLoans.addActionListener(actionListener);
    cbViewTransactions.addActionListener(actionListener);
    cbWithdraw.addActionListener(actionListener);
        
        
        
        
                rbSellectAll.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
         sellectAll();
        
        }
    });
         rbDiselectAll.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            deselect();    
        }
    });
        
        
        
        
        
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
        } catch (Exception ex) {
            Logger.getLogger(AddUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    public void sellectAll(){
        cbAccountsGroups.setSelected(true);
  cbAddCustomers.setSelected(true);
   cbAddStaff.setSelected(true);
    cbAddSystemUsers.setSelected(true);
   cbBackup.setSelected(true);
    cbDeposit.setSelected(true);
    cbGroupCollectionSheet.setSelected(true);
    cbGroups.setSelected(true);
    cbLoanApplications.setSelected(true);
    cbLoanDefaulters.setSelected(true);
    cbLoanPayment.setSelected(true);
   cbLoanProcessing.setSelected(true);
    cbLoanSettings.setSelected(true);
    cbOrgsAccount.setSelected(true);
    cbPrefrences.setSelected(true);
    cbRestore.setSelected(true);
    cbTodaysLoan.setSelected(true);
    cbUsersPasswords.setSelected(true);
    cbViewCustomers.setSelected(true);
    cbViewLoans.setSelected(true);
    cbViewTransactions.setSelected(true);
    cbWithdraw.setSelected(true);
    hash.put("a", "A");
    hash.put("b", "B");
   // hash.put("c", "C");
   // hash.put("d", "D");
    hash.put("e", "E");
    hash.put("f", "F");
    hash.put("g", "G");
    hash.put("h", "H");
    hash.put("i", "I");
    hash.put("j", "J");
    hash.put("k", "K");
    hash.put("l", "L");
    hash.put("m", "M");
    hash.put("n", "N");
    hash.put("o", "O");
    hash.put("p", "P");
    hash.put("q", "Q");
    hash.put("r", "R");
    hash.put("s", "S");
    hash.put("t", "T");
    hash.put("u", "U");
    hash.put("v", "V");
    hash.put("w", "W");
    hash.put("x", "X");
    }
    public void deselect(){
         cbAccountsGroups.setSelected(false);
     cbAddCustomers.setSelected(false);
   cbAddStaff.setSelected(false);
    cbAddSystemUsers.setSelected(false);
   cbBackup.setSelected(false);
    cbDeposit.setSelected(false);
    cbGroupCollectionSheet.setSelected(false);
    cbGroups.setSelected(false);
    cbLoanApplications.setSelected(false);
    cbLoanDefaulters.setSelected(false);
    cbLoanPayment.setSelected(false);
   cbLoanProcessing.setSelected(false);
    cbLoanSettings.setSelected(false);
    cbOrgsAccount.setSelected(false);
    cbPrefrences.setSelected(false);
    cbRestore.setSelected(false);
    cbTodaysLoan.setSelected(false);
    cbUsersPasswords.setSelected(false);
    cbViewCustomers.setSelected(false);
    cbViewLoans.setSelected(false);
    cbViewTransactions.setSelected(false);
    cbWithdraw.setSelected(false);
     hash.clear();
     val="";
     //id="";
    }
    public void clear(){
        txtPassword.setText("");
        txtUserName.setText("");
        txtHash.setText("");
        hash.clear();
        rbSellectAll.setSelected(false);
        rbDiselectAll.setSelected(false);
        val="";
        id="";
    }
    public ArrayList<UserAcc> ListUsers()
  {
    ArrayList<UserAcc> usersList = new ArrayList();
    try
    {
        Methods m=new Methods();
        Connection con = m.getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
      String searchQuery = "SELECT * FROM `system_user`";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
        UserAcc user = new UserAcc(rs.getString("user_name"), rs.getString("user_password"), rs.getString("id"));
        
        usersList.add(user);
      }
      st.close();
      rs.close();
      con.close();
    }
    catch (Exception ex)
    {
      System.out.println(ex.getMessage());
    }
    return usersList;
  }
  
    public void findUsers()
  {
    ArrayList<UserAcc> users = ListUsers();
    DefaultTableModel model = new DefaultTableModel();
    
    model.setColumnIdentifiers(new Object[] { "User_Name", "Id" });
    Object[] row = new Object[2];
    for (int i = 0; i < users.size(); i++)
    {
      row[0] = ((UserAcc)users.get(i)).getId();
   //   row[1] = ((UserAcc)users.get(i)).getAccName();
       row[1] = ((UserAcc)users.get(i)).getAccFee();
      
     
      
      model.addRow(row);
    }
    this.table.setModel(model);
  }
private void refresh(){
    
        DefaultTableModel model = (DefaultTableModel)this.table.getModel();
     model.setRowCount(0);
     
        
     findUsers();
     
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
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtHash = new javax.swing.JPasswordField();
        txtPassword = new javax.swing.JPasswordField();
        cbBackup = new javax.swing.JCheckBox();
        cbRestore = new javax.swing.JCheckBox();
        cbAddCustomers = new javax.swing.JCheckBox();
        cbViewCustomers = new javax.swing.JCheckBox();
        cbDeposit = new javax.swing.JCheckBox();
        cbWithdraw = new javax.swing.JCheckBox();
        cbViewTransactions = new javax.swing.JCheckBox();
        cbAccountsGroups = new javax.swing.JCheckBox();
        cbGroups = new javax.swing.JCheckBox();
        cbLoanApplications = new javax.swing.JCheckBox();
        cbLoanProcessing = new javax.swing.JCheckBox();
        cbViewLoans = new javax.swing.JCheckBox();
        cbLoanDefaulters = new javax.swing.JCheckBox();
        cbLoanPayment = new javax.swing.JCheckBox();
        cbTodaysLoan = new javax.swing.JCheckBox();
        cbLoanSettings = new javax.swing.JCheckBox();
        cbAddStaff = new javax.swing.JCheckBox();
        cbGroupCollectionSheet = new javax.swing.JCheckBox();
        cbOrgsAccount = new javax.swing.JCheckBox();
        cbAddSystemUsers = new javax.swing.JCheckBox();
        cbUsersPasswords = new javax.swing.JCheckBox();
        cbPrefrences = new javax.swing.JCheckBox();
        rbSellectAll = new javax.swing.JRadioButton();
        rbDiselectAll = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jLabel1.setText("USERNAME");

        jLabel2.setText("PASSWORD");

        jLabel3.setText("HASH");

        jButton1.setText("REMOVE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("ADD");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("UPDATE");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txtHash.setEditable(false);
        txtHash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHashActionPerformed(evt);
            }
        });

        cbBackup.setText("BackUp");

        cbRestore.setText("Restore");

        cbAddCustomers.setText("Add Customers");

        cbViewCustomers.setText("View Customers");

        cbDeposit.setText("Deposit");

        cbWithdraw.setText("Withdraw");

        cbViewTransactions.setText("View Transactions");

        cbAccountsGroups.setText("Accs &Groups");

        cbGroups.setText("Groups");

        cbLoanApplications.setText("Loan Applications");

        cbLoanProcessing.setText("Loan Processing");

        cbViewLoans.setText("View Loans");

        cbLoanDefaulters.setText("Loan Defaulters");

        cbLoanPayment.setText("Loan Payment");

        cbTodaysLoan.setText("Todays Loans");

        cbLoanSettings.setText("Loans Settings");

        cbAddStaff.setText("Add Staff");

        cbGroupCollectionSheet.setText("Group Collection");

        cbOrgsAccount.setText("Org's Account");

        cbAddSystemUsers.setText("Add System Users");

        cbUsersPasswords.setText("Users Passwords");

        cbPrefrences.setText("Prefrences");

        rbSellectAll.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rbSellectAll.setText("SELECT ALL");

        rbDiselectAll.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rbDiselectAll.setText("DISELECT ALL");

        jButton2.setText("CLEAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbBackup)
                            .addComponent(cbRestore)
                            .addComponent(cbViewCustomers)
                            .addComponent(cbDeposit)
                            .addComponent(cbViewTransactions)
                            .addComponent(cbAccountsGroups))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(txtHash, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbAddSystemUsers)
                                        .addComponent(cbOrgsAccount)
                                        .addComponent(cbUsersPasswords)
                                        .addComponent(cbPrefrences)
                                        .addComponent(rbSellectAll)
                                        .addComponent(rbDiselectAll)
                                        .addComponent(jButton2))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(jButton4)
                                        .addGap(92, 92, 92)
                                        .addComponent(jButton1))
                                    .addComponent(cbLoanSettings)
                                    .addComponent(cbTodaysLoan)
                                    .addComponent(cbAddStaff)
                                    .addComponent(cbGroupCollectionSheet)))))
                    .addComponent(cbAddCustomers)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbLoanApplications)
                            .addComponent(cbLoanProcessing)
                            .addComponent(cbViewLoans)
                            .addComponent(cbLoanDefaulters)
                            .addComponent(cbLoanPayment)))
                    .addComponent(cbWithdraw)
                    .addComponent(jButton3)
                    .addComponent(cbGroups))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbBackup)
                    .addComponent(cbLoanApplications)
                    .addComponent(cbOrgsAccount))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbRestore)
                    .addComponent(cbLoanProcessing)
                    .addComponent(cbAddSystemUsers))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbAddCustomers)
                    .addComponent(cbViewLoans)
                    .addComponent(cbUsersPasswords))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbViewCustomers)
                    .addComponent(cbLoanDefaulters)
                    .addComponent(cbPrefrences))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbDeposit)
                    .addComponent(cbLoanPayment))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbWithdraw)
                    .addComponent(cbTodaysLoan)
                    .addComponent(rbSellectAll))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbViewTransactions)
                    .addComponent(cbLoanSettings)
                    .addComponent(rbDiselectAll))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbAccountsGroups)
                    .addComponent(cbAddStaff)
                    .addComponent(jButton2))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbGroups)
                    .addComponent(cbGroupCollectionSheet))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton1))
                .addContainerGap(13, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtHashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHashActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHashActionPerformed
String val="";
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      val=hash.values().toString().replaceAll("\\[","").replaceAll("\\]","").replaceAll(",","").replaceAll("\\s+","");
     System.out.println(val.length()+"\n"+val);
     add(val);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        val=hash.values().toString().replaceAll("\\[","").replaceAll("\\]","").replaceAll(",","").replaceAll("\\s+","");
     System.out.println(val);
        update(val);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        delete();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
             deselect();
         //   refresh();
             //refreshTable();
             clear();
        
        
        int i = this.table.getSelectedRow();

        TableModel model = this.table.getModel();

         this.txtUserName.setText(model.getValueAt(i, 0).toString());

        // this.txtPassword.setText(model.getValueAt(i, 1).toString());
         id=model.getValueAt(i, 1).toString();
         String Ghash= getHash(model.getValueAt(i, 1).toString());
         txtHash.setText(Ghash);
         processGhash(Ghash);
        
    }//GEN-LAST:event_tableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
           deselect();
            refresh();
             //refreshTable();
             clear();
    }//GEN-LAST:event_jButton2ActionPerformed
public void processGhash(String Ghash){
    if(Ghash.length()==22){
        sellectAll();
    }
    else{
        for(int a=0;a<Ghash.length();a++){
            if(Ghash.charAt(a)=='A'){
               hash.put("a", "A");
               cbBackup.setSelected(true);
            }
            else if(Ghash.charAt(a)=='B'){
                hash.put("b", "B");
                cbRestore.setSelected(true);
            }
            else if(Ghash.charAt(a)=='C'){
                
            }
            else if(Ghash.charAt(a)=='D'){
                
            }
            else if(Ghash.charAt(a)=='E'){
               hash.put("e", "E"); 
               cbAddCustomers.setSelected(true);
            }
            else if(Ghash.charAt(a)=='F'){
               hash.put("f", "F"); 
                cbViewCustomers.setSelected(true);
            }
            else if(Ghash.charAt(a)=='G'){
              hash.put("g", "G"); 
              cbDeposit.setSelected(true);
            }
            else if(Ghash.charAt(a)=='H'){
               hash.put("h", "H"); 
               cbWithdraw.setSelected(true);
            }
            else if(Ghash.charAt(a)=='I'){
               hash.put("i", "I"); 
               cbViewTransactions.setSelected(true);
            }
            else if(Ghash.charAt(a)=='J'){
               hash.put("j", "J"); 
               cbAccountsGroups.setSelected(true);
            }
            else if(Ghash.charAt(a)=='K'){
                hash.put("k", "K");
                cbGroups.setSelected(true);
            }
            else if(Ghash.charAt(a)=='L'){
               hash.put("l", "L"); 
               cbLoanApplications.setSelected(true);
            }
            else if(Ghash.charAt(a)=='M'){
               hash.put("m", "M"); 
               cbLoanProcessing.setSelected(true);
            }
            else if(Ghash.charAt(a)=='N'){
               hash.put("n", "N"); 
               cbViewLoans.setSelected(true);
            }
            else if(Ghash.charAt(a)=='O'){
               hash.put("o", "O"); 
                cbLoanDefaulters.setSelected(true);
            }
            else if(Ghash.charAt(a)=='P'){
               hash.put("p", "P"); 
               cbLoanPayment.setSelected(true);
            }
            else if(Ghash.charAt(a)=='Q'){
              hash.put("q", "Q");  
              cbTodaysLoan.setSelected(true);
            }
            else if(Ghash.charAt(a)=='R'){
              hash.put("r", "R");  
               cbLoanSettings.setSelected(true);
            }
            else if(Ghash.charAt(a)=='S'){
              hash.put("s", "S");  
              cbAddStaff.setSelected(true);
            }
            else if(Ghash.charAt(a)=='T'){
              hash.put("t", "T");
              cbGroupCollectionSheet.setSelected(true);
            }
            else if(Ghash.charAt(a)=='U'){
              hash.put("u", "U"); 
              cbOrgsAccount.setSelected(true);
            }
            else if(Ghash.charAt(a)=='V'){
              hash.put("v", "V"); 
              cbAddSystemUsers.setSelected(true);
            }
            else if(Ghash.charAt(a)=='W'){
               hash.put("w", "W"); 
                cbUsersPasswords.setSelected(true);
            }
            else if(Ghash.charAt(a)=='X'){
              hash.put("x", "X");
              cbPrefrences.setSelected(true);
                
            }
        }
    }
    
    
}
    public void add(String val){
    try {
            Methods m=new Methods();
            try (Connection connection = m.getConnection()) {
                String query = "INSERT INTO `system_user`(`user_name`, `user_password`, `hash`) VALUES ('" + this.txtUserName.getText() + "','" + txtPassword.getText() + "','" + val + "')";
                
                try (PreparedStatement pst = connection.prepareStatement(query)) {
                    pst.executeUpdate(query);
                    pst.close();
                    connection.close();
                }
            }
            deselect();
            refresh();
             //refreshTable();
             clear();
        } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "Error");
            Logger.getLogger(AddUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
}
String id;
public void update(String val){
    
   if(txtPassword.getText().isEmpty()||txtUserName.getText().isEmpty()||"".equals(val)){
        JOptionPane.showMessageDialog(null, "Empty Field Detected");
    }
    else{
        
    
   try {
     //  String query = "UPDATE `books` SET `title`='" + this.btitle.getText() + 
     //"',`ediion`='" + this.Bedition.getText() + "',`status`= '" + this.bstatus.getText() + "' WHERE `id` = '" + this.bid.getText() + "'";
      
     // executeSQlQuery(query, "Updated");
            
            try (Connection connection = m.getConnection()) {
                String query = "UPDATE `system_user` SET `user_name`='" + this.txtUserName.getText() + "',`user_password`='" + this.txtPassword.getText() + "',`hash`='" + val + "' WHERE `id` = '" + this.id + "'";
                
                try (PreparedStatement pst = connection.prepareStatement(query)) {
                    pst.executeUpdate(query);
                    pst.close();
                    connection.close();
                }
            }
              deselect();
            refresh();
             //refreshTable();
             clear();
        } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "Error");
            Logger.getLogger(AddUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
     }  
    
}
private void delete(){
     if(txtUserName.getText().isEmpty()||txtPassword.getText().isEmpty()){
        JOptionPane.showMessageDialog(null, "Empty Field Detected");
    }
    else{
        
    
 //String id=  accId.getText();
                String query = "DELETE FROM `system_user`  WHERE `id` = '" +id+"'";
               if(m.executeSQlQueryN(query )>=1) {
               deselect();
            refresh();
             //refreshTable();
             clear();
               }
     }
}
public String getHash(String Gid){
    String GHash=null;
     try
    {
       
        Connection con = m.getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
      String searchQuery = "SELECT `hash`,`user_password` FROM `system_user` WHERE `id` = '" +Gid+"'";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
      
        GHash=rs.getString("hash");
        txtPassword.setText(rs.getString("user_password"));
        
      }
      st.close();
      rs.close();
      con.close();
    }
    catch (Exception ex)
    {
      System.out.println(ex.getMessage());
    }
     return GHash;
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
            java.util.logging.Logger.getLogger(AddUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddUsers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbAccountsGroups;
    private javax.swing.JCheckBox cbAddCustomers;
    private javax.swing.JCheckBox cbAddStaff;
    private javax.swing.JCheckBox cbAddSystemUsers;
    private javax.swing.JCheckBox cbBackup;
    private javax.swing.JCheckBox cbDeposit;
    private javax.swing.JCheckBox cbGroupCollectionSheet;
    private javax.swing.JCheckBox cbGroups;
    private javax.swing.JCheckBox cbLoanApplications;
    private javax.swing.JCheckBox cbLoanDefaulters;
    private javax.swing.JCheckBox cbLoanPayment;
    private javax.swing.JCheckBox cbLoanProcessing;
    private javax.swing.JCheckBox cbLoanSettings;
    private javax.swing.JCheckBox cbOrgsAccount;
    private javax.swing.JCheckBox cbPrefrences;
    private javax.swing.JCheckBox cbRestore;
    private javax.swing.JCheckBox cbTodaysLoan;
    private javax.swing.JCheckBox cbUsersPasswords;
    private javax.swing.JCheckBox cbViewCustomers;
    private javax.swing.JCheckBox cbViewLoans;
    private javax.swing.JCheckBox cbViewTransactions;
    private javax.swing.JCheckBox cbWithdraw;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbDiselectAll;
    private javax.swing.JRadioButton rbSellectAll;
    private javax.swing.JTable table;
    private javax.swing.JPasswordField txtHash;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables

    private  class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
         
        
        
        JCheckBox checkbox=(JCheckBox)ae.getSource();
        if(checkbox==cbAccountsGroups){
            if(cbAccountsGroups.isSelected()){
                //hash=hash+"J";
                hash.put("j", "J");
            }
            else{
                hash.remove("j");
            }
        }
        else if(checkbox==cbAddCustomers){
            if(cbAddCustomers.isSelected()){
                hash.put("e", "E");
            }
            else{
                hash.remove("e");
            }
        }
        else if(checkbox==cbAddStaff){
            if(cbAddStaff.isSelected()){
                 hash.put("s", "S");
            }
            else{
                hash.remove("s");
            }
        }
        else if(checkbox==cbAddSystemUsers){
            if(cbAddSystemUsers.isSelected()){
                 hash.put("v", "V");
            }
            else{
                 hash.remove("v");
            }
        }
        else if(checkbox==cbBackup){
            if(cbBackup.isSelected()){
                 hash.put("a", "A");
            }
            else{
                 hash.remove("a");
            }
        }
        else if(checkbox==cbDeposit){
            if(cbDeposit.isSelected()){
                 hash.put("g", "G");
            }
            else{
                hash.remove("g");
            }
        }
        else if(checkbox==cbGroupCollectionSheet){
            if(cbGroupCollectionSheet.isSelected()){
                hash.put("t", "T");
            }
            else{
                hash.remove("t");
            }
        }
        else if(checkbox==cbGroups){
            if(cbGroups.isSelected()){
                hash.put("k", "K");
            }
            else{
                hash.remove("k");
            }
        }
        else if(checkbox==cbLoanApplications){
            if(cbLoanApplications.isSelected()){
                 hash.put("l", "L");
            }
            else{
                hash.remove("l");
            }
        }
        else if(checkbox==cbLoanDefaulters){
            if(cbLoanDefaulters.isSelected()){
                 hash.put("o", "O");
            }
            else{
                 hash.remove("o");
            }
        }
        else if(checkbox==cbLoanPayment){
            if(cbLoanPayment.isSelected()){
                 hash.put("p", "P");
            }
            else{
                 hash.remove("p");
            }
        }
        else if(checkbox==cbLoanProcessing){
            if(cbLoanProcessing.isSelected()){
                  hash.put("m", "M");
            }
            else{
                 hash.remove("m");
            }
        }
        else if(checkbox==cbLoanSettings){
            if(cbLoanSettings.isSelected()){
                  hash.put("r", "R");
            }
            else{
                  hash.remove("r");
            }
        }
         else if(checkbox==cbOrgsAccount){
            if(cbOrgsAccount.isSelected()){
                 hash.put("u", "U");
            }
            else{
                  hash.remove("u");
            }
        }
         else if(checkbox==cbPrefrences){
            if(cbPrefrences.isSelected()){
                 hash.put("x", "X");
            }
            else{
                hash.remove("x");
            }
        }
         else if(checkbox==cbRestore){
            if(cbRestore.isSelected()){
                hash.put("b", "B");
            }
            else{
                hash.remove("b");
            }
        }
         else if(checkbox==cbTodaysLoan){
            if(cbTodaysLoan.isSelected()){
                hash.put("q", "Q");
            }
            else{
                hash.remove("q");
            }
        }
         else if(checkbox==cbUsersPasswords){
            if(cbUsersPasswords.isSelected()){
                hash.put("w", "W");
            }
            else{
                hash.remove("w");
            }
        }
         else if(checkbox==cbViewCustomers){
            if(cbViewCustomers.isSelected()){
                hash.put("f", "F");
            }
            else{
                hash.remove("f");
            }
        }
         else if(checkbox==cbViewLoans){
            if(cbViewLoans.isSelected()){
                hash.put("n", "N");
            }
            else{
                hash.remove("n");
            }
        }
         else if(checkbox== cbViewTransactions){
            if(cbViewTransactions.isSelected()){
                hash.put("i", "I");
            }
            else{
                hash.remove("i");
            }
        }
         else if(checkbox== cbWithdraw){
            if(cbWithdraw.isSelected()){
               hash.put("h", "H");
            }
            else{
               hash.remove("h");
            }
        }
        
        
        
        }

       
    }
}
