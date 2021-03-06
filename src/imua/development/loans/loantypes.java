/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development.loans;

import imua.development.loantypesholder;
import imua.development.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author kimani kogi
 */
public class loantypes extends javax.swing.JFrame {

    /**
     * Creates new form loantypes
     */
    public loantypes() {
        initComponents();
        setTilteImage();
        findUsers();
        btnGroup.add(jRadioButtonA);
        btnGroup.add(jRadioButtonN);
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
            //jPanel1.setBackground(c);
              Container cont=this.getContentPane();
            cont.setBackground(c);
        } catch (Exception ex) {
            Logger.getLogger(loantypes.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
  public ArrayList<loantypesholder> ListUsers()
  {
    ArrayList<loantypesholder> usersList = new ArrayList();
    try
    {
        Methods m=new Methods();
        Connection con = m.getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
      String searchQuery = "SELECT * FROM `accounttypes`";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
        loantypesholder user = new loantypesholder(rs.getString("id"), rs.getString("name"),
                rs.getString("rate"),rs.getString("appfee"),rs.getString("penalt"),rs.getString("balance"),rs.getString("rateTime"),rs.getString("ratePer"),rs.getString("applicable")
        );
        
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
  public String getAbbr(String name,String id){
      String abbr=name;
       try
    {
        Methods m=new Methods();
        Connection con = m.getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
     // String searchQuery = "SELECT * FROM `accounttypes`";
      String searchQuery = "SELECT sname FROM `accounttypes` WHERE `id` = '" + id + "'";
      ResultSet rs = st.executeQuery(searchQuery);
      if (rs.next())
      {
        abbr=rs.getString(1);
      }
      else{
           
      }
      st.close();
      rs.close();
      con.close();
    }
    catch (Exception ex)
    {
        ex.printStackTrace();
      System.out.println(ex.getMessage());
    }
   
      return abbr;
  }
  public void findUsers()
  {
    ArrayList<loantypesholder> users = ListUsers();
    DefaultTableModel model = new DefaultTableModel();
    
    model.setColumnIdentifiers(new Object[] { "ID", "Name","Abbr","Rate","In","Aplication","Penalty","Balance" });
    Object[] row = new Object[8];
    for (int i = 0; i < users.size(); i++)
    {
      row[0] = ((loantypesholder)users.get(i)).getId();
      row[1] = ((loantypesholder)users.get(i)).getName();
      
       row[2] = getAbbr(((loantypesholder)users.get(i)).getName(),((loantypesholder)users.get(i)).getId());
        row[3] = ((loantypesholder) users.get(i)).getRate();
        String rateper = ((loantypesholder) users.get(i)).getRatePer();
        String ratetime = ((loantypesholder) users.get(i)).getRateTime();
        row[4] = ratetime+" "+rateper;
        row[5] = ((loantypesholder) users.get(i)).getAppFee();
        double as=Double.valueOf(((loantypesholder) users.get(i)).getPenalty())*7;
        row[6] = as;
        row[7] = ((loantypesholder) users.get(i)).getBalance();
       
      
     
      
      model.addRow(row);
    }
    this.table.setModel(model);
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
        DefaultTableModel model = (DefaultTableModel)this.table.getModel();
 
        model.setRowCount(0);
        clear();
        findUsers();
        
        JOptionPane.showMessageDialog(null,  message + " Succefully");
      }
      else
      {
        JOptionPane.showMessageDialog(null, " Not " + message);
       
      }
      st.close();
      con.close();
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
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

        jTextField14 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tid = new javax.swing.JTextField();
        trate = new javax.swing.JTextField();
        tappfee = new javax.swing.JTextField();
        tpenalty = new javax.swing.JTextField();
        tbalance = new javax.swing.JTextField();
        tname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        txtIntrestPer = new javax.swing.JLabel();
        jRadioButtonA = new javax.swing.JRadioButton();
        jRadioButtonN = new javax.swing.JRadioButton();
        txtAbbreviation = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        jTextField14.setText("jTextField14");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

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
        table.setRowHeight(40);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("INFORMATION");

        jLabel2.setText("NAME");

        jLabel3.setText("ID");

        jLabel4.setText(" INTREST RATE");

        jLabel5.setText("APPLICATION FEE");

        jLabel6.setText("DEFAULTERS PENALTY");

        jLabel7.setText("REQUIRED BALANCE");

        jButton1.setText("ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("UPDATE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("DELETE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CHOOSE", "Monthly", "Weekly", "Yearly", "Daily" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel8.setText("PERIOD TYPE");

        jLabel9.setText("NO OF SUCH PERIODS");

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        jRadioButtonA.setText("APPLICABLE");
        jRadioButtonA.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonAItemStateChanged(evt);
            }
        });

        jRadioButtonN.setText("NOT APPLICABLE");
        jRadioButtonN.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonNItemStateChanged(evt);
            }
        });

        jLabel10.setText("Abbr");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(tname))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(85, 85, 85)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(57, 57, 57)
                            .addComponent(jButton3))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addComponent(txtIntrestPer))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jRadioButtonA)
                                    .addGap(18, 18, 18)
                                    .addComponent(jRadioButtonN, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 639, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tid, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tpenalty, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                            .addComponent(trate, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                            .addComponent(jLabel4)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(tappfee, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tbalance))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAbbreviation, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAbbreviation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tappfee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(trate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tpenalty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonA)
                    .addComponent(jRadioButtonN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtIntrestPer, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(96, 96, 96)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(26, 26, 26)
                .addComponent(jButton3)
                .addContainerGap(17, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int i = this.table.getSelectedRow();

        TableModel model = this.table.getModel();

        this.tid.setText(model.getValueAt(i, 0).toString());

        this.tname.setText(model.getValueAt(i, 1).toString());
       this.txtAbbreviation.setText(model.getValueAt(i, 2).toString());
        this.trate.setText(model.getValueAt(i, 3).toString());
        this.tappfee.setText(model.getValueAt(i, 5).toString());
        if(model.getValueAt(i, 4).toString().equals("0 0")){
            this.txtIntrestPer.setText("NOT APPLICABLE");
        }else{
           this.txtIntrestPer.setText(model.getValueAt(i, 4).toString()); 
        }
        
        this.tpenalty.setText(model.getValueAt(i, 6).toString());
        this.tbalance.setText(model.getValueAt(i, 7).toString());
        
      
    }//GEN-LAST:event_tableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
check(1) ;      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
check(2);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
check(3);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
       Main.a = 1;
    }//GEN-LAST:event_formWindowClosed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
       
        ratePer=jComboBox1.getSelectedItem().toString(); 
        rateTime=jSpinner1.getValue().toString();
        updateIntrestPerTxt();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
         //frequency=(Integer)jSpinnerFrequency.getValue();
         
      rateTime=jSpinner1.getValue().toString();
       updateIntrestPerTxt();
    }//GEN-LAST:event_jSpinner1StateChanged
String ratePer="0";
String rateTime="0";
    private void jRadioButtonAItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonAItemStateChanged
        applicableStatus="A";
        //ratePer=jComboBox1.getSelectedItem().toString();  
       // rateTime=jSpinner1.getValue().toString();
    }//GEN-LAST:event_jRadioButtonAItemStateChanged

    private void jRadioButtonNItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonNItemStateChanged
applicableStatus="NA"    ;  
        ratePer="0";  
        rateTime="0";
    }//GEN-LAST:event_jRadioButtonNItemStateChanged
private void  updateIntrestPerTxt(){
     int  noOf=(Integer)jSpinner1.getValue();
     String perItem=jComboBox1.getSelectedItem().toString();
     String text=(String.valueOf(noOf)+" "+perItem);
     txtIntrestPer.setText(text);
     
}
    private void insert(){
    try
      {
        String stru = this.tid.getText().toString();
        Methods m=new Methods();
        Connection con = m.getConnection();
        String str = "";
        
        str = "select * from accounttypes where  id =?";
        
        PreparedStatement pst = con.prepareStatement(str);
        
        pst.setString(1, stru);
        Double getpenalty=Double.valueOf(tpenalty.getText());
        // DecimalFormat df=new DecimalFormat("#.0");
        
        String processpenalty=String.valueOf(getpenalty/7);
        
        
        ResultSet rs = pst.executeQuery();
        if (rs.next())
        {
          JOptionPane.showMessageDialog(null, " SUCH  ID  (" + this.tid.getText() + ")  ALREADY EXIST \n " );
        }
        else
        {
String query = "INSERT INTO `accounttypes`(`id`, `name`,`sname`, `rate`,`appfee`,`penalt`,`balance`,`rateTime`,`ratePer`,`applicable`)"
 + " VALUES ('" + this.tid.getText() + "','" + this.tname.getText() + "','" + this.txtAbbreviation.getText() + "','" + this.trate.getText() + "','" + this.tappfee.getText() + "','" + processpenalty + "'"
        + ",'" + this.tbalance.getText() + "','"+rateTime+"','"+ratePer+"','"+applicableStatus+"')";
          
          executeSQlQuery(query, "Inserted");
        }
        con.close();
        rs.close();
        pst.close();
      }
      catch (Exception a)
      {
        System.err.println(a);
      }
}
private void update(){
   Double getpenalty=Double.valueOf(tpenalty.getText());
   DecimalFormat df=new DecimalFormat("#.0");
  // String processpenalty=df.format(getpenalty/7);
  String processpenalty=String.valueOf(getpenalty/7);
        //long processpenalty=Math.round(getpenalty/7);
    String query = "UPDATE `accounttypes` SET `name`='" + this.tname.getText() + "',`sname`='" + this.txtAbbreviation.getText() + "',`rate`= '" + this.trate.getText() + "'"
            + ",`appfee`= '" + this.tappfee.getText() + "',`penalt`= '" + processpenalty + "',`balance`= '" + this.tbalance.getText() + "',"
            + "`ratePer`= '"+ratePer+"',`rateTime`='"+rateTime+"',"
            + "`applicable`='"+applicableStatus+"'WHERE id= '" + this.tid.getText()+"'";
      
      executeSQlQuery(query, "Updated");
}
public void delete(){
    String query = "DELETE FROM `accounttypes` WHERE id = " + this.tid.getText();
    
    executeSQlQuery(query, "Deleted");
}
public void check(int a){
    
    
    if(applicableStatus.equals("A")&&txtIntrestPer.getText().isEmpty()){
          JOptionPane.showMessageDialog(null, "Empty fields Detected");
         // clear();
    }
    else{
    if(tid.getText().isEmpty()||
        tname.getText().isEmpty()||
        trate.getText().isEmpty()||
            tappfee.getText().isEmpty()||
            applicableStatus.isEmpty()||
            tpenalty.getText().isEmpty()||
            tbalance.getText().isEmpty()||
            txtAbbreviation.getText().isEmpty()
            
            
            
            
            ){
        JOptionPane.showMessageDialog(null, "Empty fields Detected");
        
    }
    else{
        switch (a){
            case 1:
                insert();
                break;
            case 2:
                update();
                break;
            case 3:
                delete();
                break;
            default:
                JOptionPane.showMessageDialog(null, "ERROR");
            
        }
    }
    }
}
public void clear(){
    tid.setText("");
    tname.setText("");
    trate.setText("");
    tappfee.setText("");
    tpenalty.setText("");
    tbalance.setText("");
    applicableStatus="";
    ratePer="0";
    rateTime="0";
    txtIntrestPer.setText("");
    btnGroup.clearSelection();
    txtAbbreviation.setText("");
    
   
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
            java.util.logging.Logger.getLogger(loantypes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loantypes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loantypes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loantypes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loantypes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButtonA;
    private javax.swing.JRadioButton jRadioButtonN;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTable table;
    private javax.swing.JTextField tappfee;
    private javax.swing.JTextField tbalance;
    private javax.swing.JTextField tid;
    private javax.swing.JTextField tname;
    private javax.swing.JTextField tpenalty;
    private javax.swing.JTextField trate;
    private javax.swing.JTextField txtAbbreviation;
    private javax.swing.JLabel txtIntrestPer;
    // End of variables declaration//GEN-END:variables

ButtonGroup btnGroup=new ButtonGroup();
String applicableStatus="";


}
