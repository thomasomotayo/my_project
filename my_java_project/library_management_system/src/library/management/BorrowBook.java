/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package library.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author ETHOM DIGITAL
 */
public class BorrowBook extends javax.swing.JFrame {

   Connection con = null;
    PreparedStatement pst = null;
    public BorrowBook() {
        initComponents();
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
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jTextFieldreg = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jTextFieldname = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldlevel = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldsemester = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jTextFieldAccerssion = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jDateChooserDate = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Library Management System");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(153, 153, 153)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 8));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldreg.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldreg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldregMouseClicked(evt);
            }
        });
        jTextFieldreg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldregActionPerformed(evt);
            }
        });
        jPanel5.add(jTextFieldreg, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 160, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Registration No   ");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 130, 30));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 190, 250));

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel6.add(jTextFieldname, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 160, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Automatically Fill Up");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 160, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Name   :");
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 130, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Year/Level   :");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 130, 30));

        jTextFieldlevel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel6.add(jTextFieldlevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 160, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Semester   :");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 130, 30));

        jTextFieldsemester.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel6.add(jTextFieldsemester, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 160, 30));

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 190, 250));

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldAccerssion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel7.add(jTextFieldAccerssion, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 160, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Date");
        jPanel7.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 130, 30));

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 153, 0));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/forward 2.jpg"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, 50, 40));
        jPanel7.add(jDateChooserDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 140, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Accerssion No");
        jPanel7.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 130, 30));

        jPanel4.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 460, 250));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 970, 290));

        jLabel4.setFont(new java.awt.Font("Aachen BT", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 0));
        jLabel4.setText("Developed by Computer Department.");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 560, 360, 30));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/collections of book2.jpg"))); // NOI18N
        jButton1.setText("Return books");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, 220, 30));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 0, 0));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/collections of book2.jpg"))); // NOI18N
        jButton2.setText("Borrow book");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 230, 30));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LOGOUT2.PNG"))); // NOI18N
        jPanel3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 30, 50, 40));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home1.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 30, 50, 40));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/question green1.png"))); // NOI18N
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 30, 50, 40));

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stu.PNG"))); // NOI18N
        jButton8.setText("Borower's list");
        jButton8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 230, 180, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/owu pics2.jpg"))); // NOI18N
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, -1, 140));

        jLabel19.setFont(new java.awt.Font("Aachen BT", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(204, 204, 0));
        jLabel19.setText("OWUTECH LIBRARY");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, 190, 30));

        jLabel18.setFont(new java.awt.Font("Aachen BT", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("School of Science & Engineering, Department of Computer Science");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 640, 30));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1030, 610));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1060, 640));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1146, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1111, 712));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ReturnBooks book = new ReturnBooks();
        this.hide();
        book.setVisible(true);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        BorowersList borrow = new BorowersList();
        this.hide();
        borrow.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextFieldregMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldregMouseClicked
        // TODO add your handling code here:
         try{
            
        Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library_managements", "root", "ethomdigital");

            String sql = "select * from students where reg_no =?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, jTextFieldreg.getText());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){         
                String reg = rs.getString("name");
                jTextFieldname.setText(reg);
                
                String name = rs.getString("level");
                jTextFieldlevel.setText(name);
                
                String level = rs.getString("semester");
                jTextFieldsemester.setText(level);        
            }
            
            else{
                JOptionPane.showMessageDialog(null, "User does not Exit");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTextFieldregMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
         if(jTextFieldreg.getText().length()==0){
            JOptionPane.showMessageDialog(this, "Please enter Registration No" );
        }
        else if(jTextFieldname.getText().length()==0){
            JOptionPane.showMessageDialog(this, "Please enter Student's Name");
        }
        else if(jTextFieldlevel.getText().length()==0){
            JOptionPane.showMessageDialog(this, "Please enter Student's Level");
        }
        else if(jTextFieldsemester.getText().length()==0){
            JOptionPane.showMessageDialog(this, "Please enter Student's Semester");
        }
        else if(jTextFieldAccerssion.getText().length()==0){
            JOptionPane.showMessageDialog(this, "Please enter Book Accerssion No");
        }
        else if(jDateChooserDate.getDateFormatString().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please Select Date");
        }

        else{

            try{

               
               
                con = DriverManager.getConnection("jdbc:mysql://localhost/library_managements", "root", "ethomdigital");
                String query = "insert into book_borrowed (reg_no, name, level, semester, accerssion_no, date) values(?,?,?,?,?,?)";
                pst = con.prepareStatement(query);
                pst.setString(1, jTextFieldreg.getText());
                pst.setString(2, jTextFieldname.getText());
                pst.setString(3, jTextFieldlevel.getText());
                pst.setString(4, jTextFieldsemester.getText());
                pst.setString(5, jTextFieldAccerssion.getText());
                pst.setString(6, jDateChooserDate.getDateFormatString());
            
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "BOOK BORROWED SUCCESSFULLY!");

                jTextFieldreg.setText(null);
                jTextFieldname.setText(null);
                jTextFieldlevel.setText(null);
                jTextFieldsemester.setText(null);
                jTextFieldAccerssion.setText(null);
                jDateChooserDate.setDate(null);
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
                
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        AdminHome home = new AdminHome();
        this.hide();
        home.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextFieldregActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldregActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldregActionPerformed

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
            java.util.logging.Logger.getLogger(BorrowBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BorrowBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BorrowBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BorrowBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BorrowBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private com.toedter.calendar.JDateChooser jDateChooserDate;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JTextField jTextFieldAccerssion;
    private javax.swing.JTextField jTextFieldlevel;
    private javax.swing.JTextField jTextFieldname;
    private javax.swing.JTextField jTextFieldreg;
    private javax.swing.JTextField jTextFieldsemester;
    // End of variables declaration//GEN-END:variables
}
