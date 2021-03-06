/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imua.development.Reports;

import IDataHolders.ILoanDataHolder;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import imua.development.HeaderFooterPageEvent;
import imua.development.LoanDataHolder;
import imua.development.Methods;
import imua.development.Personalreports;
import imua.development.UserDataHolder;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.out;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
// import java.sql.Date; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author kimani kogi
 */
public class GroupSheet extends javax.swing.JFrame {
String toCombo[];
    String toAccountsTypes[];
    private String group = null;
    private String accounttype = null;
  String accounts[];
  String groups[];
   Methods m= new Methods();
    /**
     * Creates new form GroupSheet
     */
    public GroupSheet() {
        initComponents();
        setTilteImage();
        findGroups();
        findLoans();
       // JOptionPane.showMessageDialog(null, "NOT YET COMPLETE","GROUP COLLECTION SHEET",JOptionPane.INFORMATION_MESSAGE);
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
            Logger.getLogger(GroupSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
}
     public void findGroups()
  {
  
    ArrayList<String> usersList =  m.ListUsersGroups();
   groups=new String[usersList.size()];
   usersList.toArray(groups);
   groupnames();
   //JOptionPane.showMessageDialog(null, groups[0]);
  }
     private void groupnames() {
       // String namesdb[] = {"eric", "kogi"};
        toCombo = groups;
        setModel();

    }

    private void setModel() {
        jComboBoxGroup.removeAllItems();
        jComboBoxGroup.addItem("choose");
        for (int a = 0; a < toCombo.length; a++) {

            jComboBoxGroup.addItem(toCombo[a]);
        }
    }
     public void findLoans()
  {
      Methods m=new Methods();
    ArrayList<String> usersList =  m.ListLoanTypes();
   accounts=new String[usersList.size()];
   usersList.toArray(accounts);
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
        jComboBoxGroup = new javax.swing.JComboBox<>();
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
        jScrollPane1.setViewportView(table);

        jComboBoxGroup.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxGroupItemStateChanged(evt);
            }
        });
        jComboBoxGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxGroupActionPerformed(evt);
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
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 972, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBoxGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       String der[]=getNameImage();
       imgurl=der[0];
       contact=der[1];
       OrgDetails=contact;
       if(jComboBoxGroup.getSelectedItem().toString().equals("choose")){
            JOptionPane.showMessageDialog(null,"Choose Group","ERROR",JOptionPane.INFORMATION_MESSAGE);
       }
       print(OrgDetails,imgurl);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBoxGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxGroupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxGroupActionPerformed
private void refresh(){
       
        DefaultTableModel model = (DefaultTableModel)this.table.getModel();
        
        model.setRowCount(0);
        
        findUsers();
  }
  public void findUsers() 
  {
   ArrayList<UserDataHolder> users = ListUsers(jComboBoxGroup.getSelectedItem().toString());
            DefaultTableModel model = new DefaultTableModel();
  //   String ID, Date ate, String Installmentamount, String LoanAmount,String autoid,String instno
    model.setColumnIdentifiers(new Object[] {"ID", "FNAME","SAVINGS","TYPE","BALANCE","INSTALLMENT" });    
                for (int a=0;a<users.size();a++) {
                    String uid=  ((UserDataHolder) users.get(a)).getId();
                    String nid=  ((UserDataHolder) users.get(a)).getFname();
                     String nuid=  String.valueOf(  maxid("id",uid));
                     ArrayList<Personalreports> LoanSavingsusers = ListSavingsUsers(nuid);
                     
                     String savings=((Personalreports) LoanSavingsusers .get(0)).getBalance();
                    ArrayList<ILoanDataHolder> Loanusers = ListLoanUsers(uid);
    out.println(Loanusers.size());
    Object[] row = new Object[6];
  // int i=0;
   for (int i = 0; i < Loanusers.size(); i++)
        
   { //out.println(Loanusers.size());
        if(uid.equals("1")){
        //    out.println(Loanusers.size());
        }
         row[0] = uid;
         row[1] = nid;
        
         row[2] =savings;
        
        row[3] = ((ILoanDataHolder)Loanusers.get(i)).getLoantype();
        row[4] = ((ILoanDataHolder)Loanusers.get(i)).getLoanbalance();
        row[5] = ((ILoanDataHolder)Loanusers.get(i)).getInstallmentamount();
       
      
        model.addRow(row);
    }
    
   
  
  } 
                this.table.setModel(model);
  }
    private void jComboBoxGroupItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxGroupItemStateChanged
if (!"choose".equals(jComboBoxGroup.getSelectedItem().toString())){
        
        refresh();
}
    }//GEN-LAST:event_jComboBoxGroupItemStateChanged
String groupname;
String OrgDetails;
String imgurl;
String date=new Date().toString();
String contact="www.inuadevelopment.co.ke\n inua@inuadevelopment.co.ke\n 0714406984\n"+date;

public String[] getNameImage(){
   String contactx="details";
   String imgurlx="image";
    try {
        Methods m=new Methods();
        Connection con = m.getConnection();
        Statement st2 = con.createStatement();
        
        ResultSet res7 = st2.executeQuery("SELECT imgurl,name,address,email,website,phone FROM prefrences  WHERE id=1");
        if (res7.next()) {
         imgurlx = res7.getString("imgurl");
        contactx = res7.getString("name")+"\n"+res7.getString("address")+"\n"+res7.getString("website")+
        "\n"+res7.getString("email")+"\n"+res7.getString("phone");
            
        } else {
            JOptionPane.showMessageDialog(null, "error loading image \n  make sure image is in images folder ");
       
        }
        st2.close();
        res7.close();   
        con.close();
    } catch (SQLException ex) {
        Logger.getLogger(GroupSheet.class.getName()).log(Level.SEVERE, null, ex);
    }
  return new String[] {imgurlx,contactx};
}
// public void getUserInGroup(){
//     String groupid=jComboBoxGroup.getSelectedItem().toString();
//     
//      try {
//        Methods m=new Methods();
//        Connection con = m.getConnection();
//        Statement st2 = con.createStatement();
//        
//        ResultSet res7 = st2.executeQuery("SELECT id,fname FROM users  WHERE group='"+groupid+"'");
//        if (res7.next()) {
//            this.imgurl = res7.getString("imgurl");
//            this.groupname = res7.getString("name");
//        } else {
//            JOptionPane.showMessageDialog(null, "error");
//        }
//        st2.close();
//        res7.close();   
//        con.close();
//    } catch (SQLException ex) {
//        Logger.getLogger(GroupSheet.class.getName()).log(Level.SEVERE, null, ex);
//    }
//     
//     
//     
// }
 public ArrayList<UserDataHolder> ListUsers(String groupid )
  {
    //  System.gc();
    ArrayList<UserDataHolder> usersList = new ArrayList();
      
         String nl="null";
    try
    {
        Methods m=new Methods();
        Connection con = m.getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
     // String searchQuery = "SELECT * FROM `accounttypes`";
      String searchQuery = "SELECT * FROM `users` WHERE `group`='"+groupid +"' ";
      ResultSet rs = st.executeQuery(searchQuery);
      while (rs.next())
      {
        UserDataHolder user = new UserDataHolder(rs.getString("id"),rs.getString("id"), rs.getString("fname"), rs.getString("sname"),
                rs.getString("email")
                ,rs.getString("phone"),rs.getString("address"),rs.getString("county"),rs.getString("town"),
                rs.getString("occupation"),
                rs.getString("maritalstatus"),rs.getString("acounttype"),rs.getString("group"),rs.getString("imgurl")
                );
        
        usersList.add(user);
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
    return usersList;
  }
 
     public ArrayList<ILoanDataHolder> ListLoanUsers(String id )
  {
    ArrayList<ILoanDataHolder> usersList = new ArrayList();
                 Calendar  c= Calendar.getInstance();
         Date today=c.getTime();
   //   Date dt=   Date.valueOf("20170101");
         java.util.Date d=(today);
  //Days d=Days.daysBeetween();
         java.sql.Date DATE=new java.sql.Date(d.getTime());
         String nl="null";
    try
    {
        Methods m=new Methods();
        Connection con = m.getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
     // String searchQuery = "SELECT * FROM `accounttypes`";
      String searchQuery = "SELECT * FROM `iloans`WHERE customerid='"+id+"' AND  datesupposed>='" + DATE + "'";
      ResultSet rs = st.executeQuery(searchQuery);
    
      
            while(rs.next()){
      ILoanDataHolder data = new ILoanDataHolder(
              rs.getString("autoid")
              ,rs.getString("customerid"),
                rs.getString("loanid")
                ,rs.getString("loantype")
                ,rs.getString("applicablestatus")
                ,rs.getString("applicationfee")
                ,rs.getString("periodtype")
                ,rs.getString("frequencyperperiod")
                ,rs.getDate("datesupposed")
                ,rs.getString("installmentamount")
                ,rs.getString("installmentsno")
                ,rs.getString("loanAmount")
                ,rs.getString("loanRequested")
                ,rs.getString("loanbalance")
                ,rs.getString("moreorlesspaid")
                ,rs.getString("todaypay")
                ,rs.getString("paymentstatus")
                ,rs.getString("defaultstatus")
                ,rs.getString("balancebf")
                ,rs.getString("extra")
                ,rs.getString("loangivenOn")
                ,rs.getString("paidon")
          
          );
    //    out.println(rs.getString("id")+"yes");
        usersList.add(data);
      }
      if (!usersList.isEmpty())
      {
      
      }
      else{
         ILoanDataHolder data = new ILoanDataHolder(
                 "-"
               ,"-",
                 "-"
                 ,"-"
                 ,"-"
                 ,"-"
                 ,"-"
                 ,"-"
                ,d
                 ,"-"
                 ,"-"
                ,"-"
                 ,"-"
                ,"-"
                 ,"-"
                 ,"-"
                 ,"-"
                 ,"-"
                ,"-"
                 ,"-"
                 ,"-"
                 ,"-"
               );
        
        usersList.add(data);  
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
    out.println(usersList.size());
    return usersList;
  }
public void print (String groupName,String imgurl){
    
    
    
   // FileInputStream fis = null;
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
             PdfWriter writer= PdfWriter.getInstance(pdfp, new FileOutputStream(new File(chooser.getSelectedFile(),"Group "+jComboBoxGroup.getSelectedItem().toString()+".pdf")));
                pdfp.open();
               // PdfWriter writer = PdfWriter.getInstance(pdfp, new FileOutputStream(new File(chooser.getSelectedFile(),"Group "+jComboBoxGroup.getSelectedItem().toString()+".pdf")));
                HeaderFooterPageEvent event = new HeaderFooterPageEvent();
                writer.setPageEvent(event);
                
                
                PdfPTable header=new PdfPTable(2);
                //  tbl.setWidthPercentage(100);
                
                header.setTotalWidth(575);
                header.setLockedWidth(true);
                
                header.setWidths(new int[]{1,4});
                if(imgurl.isEmpty()||imgurl.equals("")||imgurl.equals("image")){
                    header.addCell("no image ");
                    
                    String img=imgurl;
                }
                else{
                    header.addCell(createImageCell(imgurl));
                    
                }
                
                header.addCell(createTextCell(OrgDetails));
                 Calendar  c= Calendar.getInstance();
        Date today=c.getTime();
         
         java.util.Date d=(today);
  //Days d=Days.daysBeetween();
         java.sql.Date DATE=new java.sql.Date(d.getTime());
         //get name and img of user
                PdfPTable group=new PdfPTable(3);
                // headings.s
                  group.setSpacingBefore(10);
                  group.addCell(creatTextCellHeader(""));
                  group.addCell(creatTextCellHeader(""));
                  group.addCell(creatTextCellHeader(""));
                  group.addCell(creatTextCellHeader(""));
                group.addCell(creatTextCellHeader("Group : "+jComboBoxGroup.getSelectedItem().toString()));
                group.addCell(creatTextCellHeader(String.valueOf(DATE)));
                group.setSpacingAfter(10);
                
                PdfPTable headings=new PdfPTable(8);
                headings.setTotalWidth(575);
               headings.setWidths(new int[]{1,2,1,1,1,1,1,1});
                
                headings.setLockedWidth(true);
                
                
                    headings.addCell(creatTextCellTitles("Id"));
                 headings.addCell(creatTextCellTitles("Name"));
                headings.addCell(creatTextCellTitles("Savings"));
                 headings.addCell(creatTextCellTitles("Deposit"));
                  headings.addCell(creatTextCellTitles("Product"));
                headings.addCell(creatTextCellTitles("Loan Balance"));
               // headings.addCell("Installment");
                headings.addCell(creatTextCellTitles("Amount paid"));
                headings.addCell(creatTextCellTitles("Others"));
                
                
                
                ArrayList<UserDataHolder> users = ListUsers(jComboBoxGroup.getSelectedItem().toString());
                
                for (int a=0;a<users.size();a++) {
                    String uid=  ((UserDataHolder) users.get(a)).getId();
                    String nid=  ((UserDataHolder) users.get(a)).getFname();
                   String nuid=  String.valueOf(  maxid("id",uid));
                     ArrayList<Personalreports> LoanSavingsusers = ListSavingsUsers(nuid);
                     
                     String savings=((Personalreports) LoanSavingsusers .get(0)).getBalance();
                    ArrayList<ILoanDataHolder> Loanusers = ListLoanUsers(uid);
                    for(int i=0;i<Loanusers.size();i++){
                       
                       
                        // String name=((LoanDataHolder)Loanusers.get(i)).getId().toString();
                        headings.addCell(createTextCellcolor(uid,a));
                        headings.addCell(createTextCellcolor(nid,a));
                         headings.addCell(createTextCellcolor(savings,a));
                          headings.addCell(createTextCellcolor("",1));
                        headings.addCell(createTextCellcolor(loanSname(((ILoanDataHolder)Loanusers.get(i)).getLoantype()),a));
                        headings.addCell(createTextCellcolor(((ILoanDataHolder)Loanusers.get(i)).getLoanbalance(),a));
                       // headings.addCell(((LoanDataHolder)Loanusers.get(i)).getInstallmentAmount());
                        headings.addCell(createTextCellcolor("",1));
                        headings.addCell(createTextCellcolor("",1));
                    }
                    
                }
                 PdfPTable totals=new PdfPTable(4);
                  totals.setTotalWidth(575);
                   totals.setLockedWidth(true);
               //   totals.setSpacingBefore(TOP_ALIGNMENT);
                // headings.s
                totals.addCell(creatTextCellHeader(""));
                totals.addCell(creatTextCellHeader(""));
               totals.addCell(creatTextCellHeader(""));
               totals.addCell(creatTextCellHeader(""));
             //  totals.setSpacingAfter(TOP_ALIGNMENT);
                 totals.addCell(creatTextCellHeader("TOTAL SAVINGS COLLECTION"));
                totals.addCell(creatTextCellHeader("_________________"));
                
               totals.addCell(creatTextCellHeader("TOTAL LOANS COLLECTION"));
               totals.addCell(creatTextCellHeader("__________________"));
               
               totals.addCell(creatTextCellHeader("GRAND TOTAL COLLECTION"));
               totals.addCell(creatTextCellHeader("__________________"));
               totals.addCell(creatTextCellHeader(""));
                totals.addCell(creatTextCellHeader(""));
               
          
               
                
           
                
//            headings.addCell("Name");
//            headings.addCell("Product");
//            headings.addCell("Previous Balance");
//            headings.addCell("Installments value");
//            headings.addCell("Amount paid");
//            headings.addCell("Others");
           







pdfp.add(header);
pdfp.add(group);
pdfp.add(headings);
pdfp.add(totals);
pdfp.close();




            } catch (DocumentException ex) {
                Logger.getLogger(GroupSheet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GroupSheet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GroupSheet.class.getName()).log(Level.SEVERE, null, ex);
            }
                        
            if(Desktop.isDesktopSupported()){
                try{
                    File file=new File(chooser.getSelectedFile(),"Group "+jComboBoxGroup.getSelectedItem().toString()+".pdf");
                    Desktop.getDesktop().open(file);
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
            
            
            
//            FileInputStream psStream = null;
//        try {
//            psStream = new FileInputStream(new File(chooser.getSelectedFile(),"Group "+jComboBoxGroup.getSelectedItem().toString()+".pdf"));
//            } catch (FileNotFoundException ffne) {
//              ffne.printStackTrace();
//            }
//            if (psStream == null) {
//                return;
//            }
//        DocFlavor psInFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
//        Doc myDoc = new SimpleDoc(psStream, psInFormat, null);  
//        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
//        PrintService[] services = PrintServiceLookup.lookupPrintServices(psInFormat, aset);
//       //  out.println(services[1]);
//          
//            JComboBox jcb=new JComboBox(services);
//            JOptionPane.showMessageDialog(null, jcb,"Select mode of printing",JOptionPane.QUESTION_MESSAGE);
//           int mode=jcb.getSelectedIndex();
//        // this step is necessary because I have several printers configured
//        PrintService myPrinter = null;
//        for (int i = 0; i < services.length; i++){
//            
//          //  System.out.println("service found: "+svcName);
//            String svcName = services[i].toString();   
//            
//            
//           // JOptionPane.showInputDialog(i)
//            if (svcName.contains("Hp")){
//                myPrinter = services[i];
//                System.out.println("my printer found: "+svcName);
//                break;
//            }
//        }
//         myPrinter=services[mode];
//        if (myPrinter != null) {            
//            DocPrintJob job = myPrinter.createPrintJob();
//            try {
//            job.print(myDoc, aset);
//             
//            } catch (Exception pe) {pe.printStackTrace();}
//        } else {
//            System.out.println("no printer services found");
//        } 
//            
//            JOptionPane.showMessageDialog(null, "Done ","Success",JOptionPane.PLAIN_MESSAGE);  
            
        }  
        
}
    catch(Exception m){
        m.printStackTrace();
    }
}
      public PdfPCell createTextCellcolor(String text ,int c){
     PdfPCell cell=new PdfPCell();
          if(c%2==0){
        
     Paragraph p=new Paragraph();
    cell.setBackgroundColor(Color.CYAN);
     p.add(text);
     cell.addElement(p);
     }
          else{
            Paragraph p=new Paragraph();
    //cell.setBackgroundColor(Color.CYAN);
     p.add(text);
     cell.addElement(p);  
          }
     
   
    
    return cell;
     
 } 
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
          public PdfPCell creatTextCellTitles(String text){
        PdfPCell cell = new PdfPCell();
        Paragraph p = new Paragraph();
        p.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD));
        p.add(text);
        cell.addElement(p);
       // cell.setBorder(Rectangle.NO_BORDER);
        return cell;
}
    String j =creatTextCellHeader("tags").toString();
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
            java.util.logging.Logger.getLogger(GroupSheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GroupSheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GroupSheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GroupSheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GroupSheet().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBoxGroup;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

public int maxid(String where,String value){
    int id=0;
    Methods m = new Methods();Connection con = m.getConnection();
    try{
        String getMaxId="SELECT MAX(autoid) FROM `transactions` WHERE `" + where + "` = '" + value + "'";
         Statement st = con.createStatement();
           ResultSet rs = st.executeQuery(getMaxId);
           if(rs.next()){
               id=rs.getInt(1);
           }
       st.close();
      rs.close();
      con.close();
    }
    catch(Exception nw){
        nw.printStackTrace();
    }
    return id;
}
     public ArrayList< Personalreports> ListSavingsUsers(String id )
  {
    ArrayList< Personalreports> usersList = new ArrayList();
               
    try
    {
        Methods m=new Methods();
        Connection con = m.getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
     // String searchQuery = "SELECT * FROM `accounttypes`";
      String searchQuery = "SELECT * FROM `transactions` WHERE `autoid` = '" + id + "'";
      ResultSet rs = st.executeQuery(searchQuery);
      if (rs.next())
      {
        Personalreports user = new Personalreports(rs.getString("updated_at"), rs.getString("modeofpayment"),rs.getString("deposited")
                ,rs.getString("withdrawn")
                ,rs.getString("balance"),rs.getString("autoid"));
        
        usersList.add(user);
      }
      else{
           Personalreports user = new Personalreports("-", "-","-"
                ,"-"
                ,"-","-");
            usersList.add(user);
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
    return usersList;
  }

public String loanSname(String name){
   String sname=name;
    try
    {
        Methods m=new Methods();
        Connection con = m.getConnection();
     // Connection con = getConnection();
      Statement st = con.createStatement();
     // String searchQuery = "SELECT * FROM `accounttypes`";
      String searchQuery = "SELECT sname FROM `accounttypes` WHERE `name` = '" + name + "'";
      ResultSet rs = st.executeQuery(searchQuery);
      if (rs.next())
      {
        sname=rs.getString(1);
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
   
   
   
   return sname;
}


}
