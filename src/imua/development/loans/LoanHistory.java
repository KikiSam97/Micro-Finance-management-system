/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development.loans;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import imua.development.LoanDataHolder;
import imua.development.*;
import imua.development.Reports.GroupSheet;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kimani kogi
 */
public class LoanHistory extends javax.swing.JFrame {
String toLoan[];
     String accounts[];
     Methods m=new Methods();
    /**
     * Creates new form LoanHistory
     */
    public LoanHistory() {
        initComponents();
        setTilteImage();
        findLoans();
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
            Logger.getLogger(LoanHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        jComboBoxLoanType.removeAllItems();
        //jComboBoxLoanType.addItem("choose");
        for (int a = 0; a < toLoan.length; a++) {

            jComboBoxLoanType.addItem(toLoan[a]);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        txtId = new javax.swing.JTextField();
        jComboBoxLoanType = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

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
        table.setRowHeight(40);
        jScrollPane1.setViewportView(table);

        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
        });

        jComboBoxLoanType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxLoanTypeItemStateChanged(evt);
            }
        });

        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxLoanType, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 512, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxLoanType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
           String nl="null";
           String id=txtId.getText();
           String lntype=jComboBoxLoanType.getSelectedItem().toString();
          refresh(id+lntype);
  
    }//GEN-LAST:event_txtIdKeyReleased

    private void jComboBoxLoanTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxLoanTypeItemStateChanged
        String nl="null";
           String id=txtId.getText();
           String lntype=jComboBoxLoanType.getSelectedItem().toString();
           refresh(id+lntype);
    }//GEN-LAST:event_jComboBoxLoanTypeItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     
      if(txtId.getText().isEmpty()||
              table.getRowCount()==0||
              table.getColumnCount()==0
              
              
              
              ){  
          JOptionPane.showMessageDialog(null,"NO DATA","ERROR",JOptionPane.INFORMATION_MESSAGE);
      }
      else{
        print();
      }
    }//GEN-LAST:event_jButton1ActionPerformed
//   public String [] getNameImage(String id){
//       String  name="null";
//       String  imgurl="null";
//       try {
//            Methods m=new Methods();
//            Connection con = m.getConnection();
//            // Connection con = getConnection();
//            Statement st = con.createStatement();
//            // String searchQuery = "SELECT * FROM `accounttypes`";
//            String searchQuery = "SELECT fname,imgurl FROM `users` WHERE `id`="+id+" ";
//            ResultSet rs = st.executeQuery(searchQuery);
//            if(rs.next()){
//           name=rs.getString("fname");
//           imgurl=rs.getString("imgurl");
//            }
//            st.close();
//            rs.close();
//            con.close();
//            
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(LoanHistory.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    return new String []{name,imgurl};
//    
//    
//}
    public void print(){
      String []resl=  m.getNameImage(txtId.getText());
      String name=resl[0];
      String imgurl=resl[1];
      String []res= m.getNameImage();
      String Oimgurl=res[0];
      
        Calendar  c= Calendar.getInstance();
       Date today=c.getTime();
         
         java.util.Date d=(today);
  //Days d=Days.daysBeetween();
         java.sql.Date DATE=new java.sql.Date(d.getTime());
         String OrgDetails=res[1]+"\n"+DATE.toString();
    try {
        // jComboBoxGroup
        
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File(","));
        chooser.setDialogTitle("Save at");
        chooser.setApproveButtonText("save");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if(chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
            try {
                Document pdfp=new Document();
                PdfWriter.getInstance(pdfp, new FileOutputStream(new File(chooser.getSelectedFile(),txtId.getText()+""+jComboBoxLoanType.getSelectedItem().toString()+""+DATE+".pdf")));
                pdfp.open();
                PdfPTable header=new PdfPTable(3);
                //  tbl.setWidthPercentage(100);
                
                header.setTotalWidth(575);
                header.setLockedWidth(true);
                
                header.setWidths(new int[]{1,4,1});
                if(Oimgurl.equals("image")){
                    header.addCell("no image ");
                    
                    String img=Oimgurl;
                }
                else{
                    header.addCell(createImageCell(Oimgurl));
                    
                }
                
                header.addCell(createTextCell(OrgDetails));
                if(imgurl.equals("image")){
                    header.addCell("no image ");
                    
                    String img=imgurl;
                }
                else{
                    header.addCell(createImageCell(imgurl));
                    
                }
                
                PdfPTable group=new PdfPTable(2);
                // headings.s
                 ArrayList<LoanDataHolder> Loan = ListUsers(txtId.getText()+jComboBoxLoanType.getSelectedItem().toString());
                // for(int f=0;f<Loan.size();f++){
                int ap=Integer.valueOf(((LoanDataHolder)Loan.get(0)).getInstallmentNo())+1;
                group.addCell(creatTextCellHeader("Name        :"+name));
                group.addCell(creatTextCellHeader("ID          :"+txtId.getText()));
                group.addCell(creatTextCellHeader("Amount     :"+((LoanDataHolder)Loan.get(0)).getLoanAmount()));
                group.addCell(creatTextCellHeader("Type       :"+jComboBoxLoanType.getSelectedItem().toString()));
                group.addCell(creatTextCellHeader("Installment:"+String.valueOf(ap)));
                group.addCell(creatTextCellHeader("Value      :"+((LoanDataHolder)Loan.get(0)).getTodayPay()));
                group.addCell(creatTextCellHeader("Given On   :"+((LoanDataHolder)Loan.get(0)).getGivenOn() ));
                group.addCell(creatTextCellHeader(""));
              //   }
                
                
                PdfPTable headings=new PdfPTable(3);
                headings.setTotalWidth(575);
                
                headings.setLockedWidth(true);
                
                
                headings.addCell("Date");
              //  headings.addCell("InstallMent No");
                headings.addCell("Balance");
                headings.addCell("Installments value");
               // headings.addCell("Amount paid");
               // headings.addCell("Others");
                
                
                
                ArrayList<LoanDataHolder> users = ListUsers(txtId.getText()+jComboBoxLoanType.getSelectedItem().toString());
                
                for (int a=0;a<users.size();a++) {
                   
                    
                        
                        // String name=((LoanDataHolder)Loanusers.get(i)).getId().toString();
                        headings.addCell(((LoanDataHolder)users.get(a)).getPaidOn());
                        headings.addCell(((LoanDataHolder)users.get(a)).getLoanBalance());
                        headings.addCell(((LoanDataHolder)users.get(a)).getInstallmentAmount());
                       // headings.addCell(((LoanDataHolder)users.get(a)).getInstallmentAmount());
                       // headings.addCell("");
                       // headings.addCell("");
                    
                    
                }
                
                
                
                
//            headings.addCell("Name");
//            headings.addCell("Product");
//            headings.addCell("Previous Balance");
//            headings.addCell("Installments value");
//            headings.addCell("Amount paid");
//            headings.addCell("Others");
           







pdfp.add(header);
pdfp.add(group);
pdfp.add(headings);
pdfp.close();




            } catch (DocumentException ex) {
                Logger.getLogger(GroupSheet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GroupSheet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GroupSheet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            
             FileInputStream psStream = null;
        try {
            psStream = new FileInputStream(new File(chooser.getSelectedFile(),txtId.getText()+""+jComboBoxLoanType.getSelectedItem().toString()+""+DATE+".pdf"));
            } catch (FileNotFoundException ffne) {
              ffne.printStackTrace();
            }
            if (psStream == null) {
                return;
            }
        DocFlavor psInFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc myDoc = new SimpleDoc(psStream, psInFormat, null);  
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        PrintService[] services = PrintServiceLookup.lookupPrintServices(psInFormat, aset);
         
        // this step is necessary because I have several printers configured
        PrintService myPrinter = null;
        for (int i = 0; i < services.length; i++){
          //  System.out.println("service found: "+svcName);
            String svcName = services[i].toString();           
            if (svcName.contains("printer closest to me")){
                myPrinter = services[i];
                System.out.println("my printer found: "+svcName);
                break;
            }
        }
         
        if (myPrinter != null) {            
            DocPrintJob job = myPrinter.createPrintJob();
            try {
            job.print(myDoc, aset);
             
            } catch (Exception pe) {pe.printStackTrace();}
        } else {
            System.out.println("no printer services found");
        }
            
            
               JOptionPane.showMessageDialog(null, "Done ","Success",JOptionPane.PLAIN_MESSAGE); 
        }  
        
}
    catch(Exception m){
        m.printStackTrace();
    }
}
      public ArrayList<LoanDataHolder> First(String loanid )
  {
    ArrayList<LoanDataHolder> usersList = new ArrayList();
      
         String nl="null";
    try
    {
        Methods m=new Methods();
        Connection con = m.getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
      String xr="0";
     // String searchQuery = "SELECT * FROM `accounttypes`";
      String searchQuery = "SELECT * FROM `loans`WHERE loanid='"+loanid+"'AND `todaypay`='"+xr+" ";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
        LoanDataHolder user = new LoanDataHolder(rs.getString("autoid"),rs.getString("id"), rs.getString("loanid"), rs.getString("loantype"),
                rs.getString("applicable")
                ,rs.getString("installmentamount"),rs.getString("installmentsno"),rs.getString("periodtype"),rs.getString("frequencyperperiod"),
                rs.getDate("targetdate"),
                rs.getString("loanAmount"),rs.getString("loanRequested"),rs.getString("loanbalance"),rs.getString("defaultacc"),
                rs.getString("applicationfee"),rs.getString("todaypay")
                ,rs.getString("givenOn"),rs.getString("paidon"));
        
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
    
    public ArrayList<LoanDataHolder> ListUsers(String loanid )
  {
    ArrayList<LoanDataHolder> usersList = new ArrayList();
      
         String nl="null";
    try
    {
        Methods m=new Methods();
        Connection con = m.getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
     // String searchQuery = "SELECT * FROM `accounttypes`";
      String searchQuery = "SELECT * FROM `loans`WHERE loanid='"+loanid+"' ";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
        LoanDataHolder user = new LoanDataHolder(rs.getString("autoid"),rs.getString("id"), rs.getString("loanid"), rs.getString("loantype"),
                rs.getString("applicable")
                ,rs.getString("installmentamount"),rs.getString("installmentsno"),rs.getString("periodtype"),rs.getString("frequencyperperiod"),
                rs.getDate("targetdate"),
                rs.getString("loanAmount"),rs.getString("loanRequested"),rs.getString("loanbalance"),rs.getString("defaultacc"),
                rs.getString("applicationfee"),rs.getString("todaypay")
                ,rs.getString("givenOn"),rs.getString("paidon"));
        
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
  private void refresh(String loanid){
       
        DefaultTableModel model = (DefaultTableModel)this.table.getModel();
        
        model.setRowCount(0);
        
        findUsers(loanid);
  }
  public void findUsers(String loanid) 
  {
    ArrayList<LoanDataHolder> users = ListUsers(loanid);
    DefaultTableModel model = new DefaultTableModel();
  //   String ID, Date ate, String Installmentamount, String LoanAmount,String autoid,String instno
    model.setColumnIdentifiers(new Object[] { "DATE","ID", "AutoId","LOAN","BALANCE","TYPE","INSTALLMENTS","NO","PENALTY" });
    Object[] row = new Object[9];
    for (int i = 0; i < users.size(); i++)
    {row[0] = ((LoanDataHolder)users.get(i)).getPaidOn();
       row[1] = ((LoanDataHolder)users.get(i)).getId();
      row[2] = ((LoanDataHolder)users.get(i)).getAutoId();
      row[3] = ((LoanDataHolder)users.get(i)).getLoanAmount();
      row[4] = ((LoanDataHolder)users.get(i)).getLoanBalance();
      row[5] = ((LoanDataHolder)users.get(i)).getLoanType();
      row[6] = ((LoanDataHolder)users.get(i)).getInstallmentAmount();
      row[7] = ((LoanDataHolder)users.get(i)).getInstallmentNo();
      row[8] = ((LoanDataHolder)users.get(i)).getPenalty();
        model.addRow(row);
    }
    this.table.setModel(model);
  
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
            java.util.logging.Logger.getLogger(LoanHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoanHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoanHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoanHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoanHistory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBoxLoanType;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables

 public PdfPCell createTextCell(String text){
     
     PdfPCell cell=new PdfPCell();
     Paragraph p=new Paragraph();
     p.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD,16,java.awt.Font.BOLD));
   //  p.setFont(Font.BOLD);
     p.setAlignment(Element.ALIGN_CENTER);
     p.add(text);
     cell.addElement(p);
     cell.setBorder(Rectangle.NO_BORDER);
     cell.setVerticalAlignment(Rectangle.NO_BORDER);
    
    return cell;
     
 }
     public PdfPCell createImageCell(String path) throws IOException{
        PdfPCell cell=null;
        try {
            Image img=Image.getInstance(path);
            cell=new PdfPCell(img,true);
            cell.setFixedHeight(60);
              cell.setBorder(Rectangle.NO_BORDER);
            
        } catch (BadElementException ex) {
            Logger.getLogger(GroupSheet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(GroupSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
    
   

    return cell;
}
     public PdfPCell creatTextCellHeader(String text){
        PdfPCell cell = new PdfPCell();
        Paragraph p = new Paragraph();
        p.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD));
        p.add(text);
        cell.addElement(p);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
}

}
