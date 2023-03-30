

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package online.examination.system;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;
/**
 *
 * @author ETHOM DIGITAL
 */
public class QuizExam extends javax.swing.JFrame {
    Connection con = null;
    PreparedStatement pst = null;
    
    public String questionId ="1";
    public String answer;
    public int min =0;
    public int sec = 0;
    public int marks = 0; 
   //Check answer code 
    public void answerCheck(){
     String studentAnswer = "";
     if(jRadioButton1.isSelected()){
         studentAnswer = jRadioButton1.getText();
     }
     else if(jRadioButton2.isSelected()){
         studentAnswer = jRadioButton2.getText();
     }
     else if(jRadioButton3.isSelected()){
         studentAnswer = jRadioButton3.getText();
     }
     else {
         studentAnswer = jRadioButton4.getText();
     }
     if(studentAnswer.equals(answer)){
         marks = marks +1;
         String marks1 = String.valueOf(marks);
      //   jLabelMarks.setText(marks1);
     }
     
     // Change Question Id
     int questionId1 = Integer.parseInt(questionId);
     questionId1 = questionId1 +1;
     questionId = String.valueOf(questionId1);
     
     //Clear Radio button after next
      jRadioButton1.setSelected(false);
      jRadioButton2.setSelected(false);
      jRadioButton3.setSelected(false);
      jRadioButton4.setSelected(false);
        
     
     //hide button next after last question
     if(questionId.equals("10")){
         jButton1.setVisible(false);
     }
    }
    
    public void question(){
        
       /*  try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/online_examination", "root", "");
           ResultSet rs1 = pst.executeQuery("select * from question where question_id = '"+questionId+"'");
           while(rs1.next()){
             jLabelQuestionNo.setText(rs1.getString(1));
             jLabelQoestion.setText(rs1.getString(2));
             jRadioButton1.setText(rs1.getString(3));
             jRadioButton2.setText(rs1.getString(4));
             jRadioButton3.setText(rs1.getString(5));
             jRadioButton4.setText(rs1.getString(6));
             answer = rs1.getString(0);
           }
           
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            
        }*/
        
    }
    public void submit(){
    //    String regNo = jLabelRegNo.getText();
      //  String fname = jLabelFname.getText();
        //String lname = jLabelLName.getText();
        answerCheck();
        
        try{
           con = DriverManager.getConnection("jdbc:mysql://localhost/online_examination", "root", "");
      //     ResultSet rs1 = pst.executeQuery("update result SET reg_no = '"+regNo+"', marks = '"+marks+"', fname = '"+fname+"', lname = '"+lname+"' where reg_no = '"+regNo+"'");
           String marks1 = String.valueOf(marks);
           JOptionPane.showMessageDialog(null, marks);
           
        }catch(Exception e){
          JOptionPane.showMessageDialog(null, e);  
            
        }
    }

    /**
     * Creates new form StudentAnswerQuestion
     */
    public QuizExam() {
        initComponents();
        Timer time = null;
        showDate();
    }
    void showDate(){
       Date d = new Date();
       SimpleDateFormat s = new SimpleDateFormat("dd-MMM-yyy");
       LabelDate.setText(s.format(d));
    }
    
     public QuizExam(String reg_no) {
        initComponents();
      Timer time = null;
      //  jLabelRegNo.setText(reg_no);
        //Date
        SimpleDateFormat dFormat = new SimpleDateFormat("dd-MMM-yyyy");
        Date date = new Date();
        LabelDate.setText(dFormat.format(date));
        
        
        //First Question and Students Details
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/online_examination", "root", "");
            String query = "select * from student where reg_no = '"+reg_no+"'";
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
           while(rs.next()){
         //    jLabelFname.setText(rs.getString(3));
          //   jLabelLName.setText(rs.getString(4));
           }
           
           ResultSet rs1 = pst.executeQuery("select * from question where question_id = '"+questionId+"'");
           while(rs1.next()){
          //   jLabelQuestionNo.setText(rs1.getString(1));
            // jLabelQoestion.setText(rs1.getString(2));
             jRadioButton1.setText(rs1.getString(3));
             jRadioButton2.setText(rs1.getString(4));
             jRadioButton3.setText(rs1.getString(5));
             jRadioButton4.setText(rs1.getString(6));
             answer = rs1.getString(0);
           }
           
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            
        }
        
        //Set Time code
        setLocationRelativeTo(this);
        time = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               // jLabelSec.setText(String.valueOf(sec));
                //jLabelMin.setText(String.valueOf(min));
                
                if(sec == 60){
                    sec = 0;
                    min++;
                    if(min==10)
               {
                 //time.stop();
                 answerCheck(); 
                 submit();
               
               }
                            
                }
                sec++;
            }
        }){
            
        };
        time.start();
    }

     // NEW CODES 
   
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        LabelDate = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jPanel39 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jPanel46 = new javax.swing.JPanel();
        jPanel47 = new javax.swing.JPanel();
        jPanel48 = new javax.swing.JPanel();
        jPanel49 = new javax.swing.JPanel();
        jPanel50 = new javax.swing.JPanel();
        jPanel51 = new javax.swing.JPanel();
        jPanel52 = new javax.swing.JPanel();
        jPanel53 = new javax.swing.JPanel();
        jPanel54 = new javax.swing.JPanel();
        jPanel55 = new javax.swing.JPanel();
        jPanel56 = new javax.swing.JPanel();
        jPanel57 = new javax.swing.JPanel();
        jPanel58 = new javax.swing.JPanel();
        jPanel59 = new javax.swing.JPanel();
        jPanel60 = new javax.swing.JPanel();
        jPanel61 = new javax.swing.JPanel();
        jPanel62 = new javax.swing.JPanel();
        jPanel63 = new javax.swing.JPanel();
        jPanel64 = new javax.swing.JPanel();
        jPanel65 = new javax.swing.JPanel();
        jPanel66 = new javax.swing.JPanel();
        jPanel67 = new javax.swing.JPanel();
        jPanel68 = new javax.swing.JPanel();
        jPanel69 = new javax.swing.JPanel();
        jPanel70 = new javax.swing.JPanel();
        jPanel71 = new javax.swing.JPanel();
        jPanel72 = new javax.swing.JPanel();
        jPanel73 = new javax.swing.JPanel();
        jPanel74 = new javax.swing.JPanel();
        jPanel75 = new javax.swing.JPanel();
        jPanel76 = new javax.swing.JPanel();
        jPanel77 = new javax.swing.JPanel();
        jPanel78 = new javax.swing.JPanel();
        jPanel79 = new javax.swing.JPanel();
        jPanel80 = new javax.swing.JPanel();
        jPanel81 = new javax.swing.JPanel();
        jPanel82 = new javax.swing.JPanel();
        jPanel83 = new javax.swing.JPanel();
        jPanel84 = new javax.swing.JPanel();
        jPanel85 = new javax.swing.JPanel();
        jPanel86 = new javax.swing.JPanel();
        jPanel87 = new javax.swing.JPanel();
        jPanel88 = new javax.swing.JPanel();
        jPanel89 = new javax.swing.JPanel();
        jPanel90 = new javax.swing.JPanel();
        jPanel91 = new javax.swing.JPanel();
        jPanel92 = new javax.swing.JPanel();
        jPanel93 = new javax.swing.JPanel();
        jPanel94 = new javax.swing.JPanel();
        jPanel95 = new javax.swing.JPanel();
        jPanel96 = new javax.swing.JPanel();
        jPanel97 = new javax.swing.JPanel();
        jPanel98 = new javax.swing.JPanel();
        jPanel99 = new javax.swing.JPanel();
        jPanel100 = new javax.swing.JPanel();
        jPanel101 = new javax.swing.JPanel();
        jPanel102 = new javax.swing.JPanel();
        jPanel103 = new javax.swing.JPanel();
        jPanel104 = new javax.swing.JPanel();
        jPanel105 = new javax.swing.JPanel();
        jPanel106 = new javax.swing.JPanel();
        jPanel107 = new javax.swing.JPanel();
        jPanel108 = new javax.swing.JPanel();
        jPanel109 = new javax.swing.JPanel();
        jPanel110 = new javax.swing.JPanel();
        jPanel111 = new javax.swing.JPanel();
        jPanel112 = new javax.swing.JPanel();
        jPanel113 = new javax.swing.JPanel();
        jPanel114 = new javax.swing.JPanel();
        jPanel115 = new javax.swing.JPanel();
        jPanel116 = new javax.swing.JPanel();
        jPanel117 = new javax.swing.JPanel();
        jPanel118 = new javax.swing.JPanel();
        jPanel119 = new javax.swing.JPanel();
        jPanel120 = new javax.swing.JPanel();
        jPanel121 = new javax.swing.JPanel();
        jPanel122 = new javax.swing.JPanel();
        jPanel123 = new javax.swing.JPanel();
        jPanel124 = new javax.swing.JPanel();
        jPanel125 = new javax.swing.JPanel();
        jPanel126 = new javax.swing.JPanel();
        jPanel127 = new javax.swing.JPanel();
        jPanel128 = new javax.swing.JPanel();
        jPanel129 = new javax.swing.JPanel();
        jPanel130 = new javax.swing.JPanel();
        jPanel131 = new javax.swing.JPanel();
        jPanel132 = new javax.swing.JPanel();
        jPanel133 = new javax.swing.JPanel();
        jPanel134 = new javax.swing.JPanel();
        jPanel135 = new javax.swing.JPanel();
        jPanel136 = new javax.swing.JPanel();
        jPanel137 = new javax.swing.JPanel();
        jPanel138 = new javax.swing.JPanel();
        jPanel139 = new javax.swing.JPanel();
        jPanel140 = new javax.swing.JPanel();
        jPanel141 = new javax.swing.JPanel();
        jPanel142 = new javax.swing.JPanel();
        jPanel143 = new javax.swing.JPanel();
        jPanel144 = new javax.swing.JPanel();
        jPanel145 = new javax.swing.JPanel();
        jPanel146 = new javax.swing.JPanel();
        jPanel147 = new javax.swing.JPanel();
        jPanel148 = new javax.swing.JPanel();
        jPanel149 = new javax.swing.JPanel();
        jPanel150 = new javax.swing.JPanel();
        jPanel151 = new javax.swing.JPanel();
        jPanel152 = new javax.swing.JPanel();
        jPanel153 = new javax.swing.JPanel();
        jPanel154 = new javax.swing.JPanel();
        jPanel155 = new javax.swing.JPanel();
        jPanel156 = new javax.swing.JPanel();
        jPanel157 = new javax.swing.JPanel();
        jPanel158 = new javax.swing.JPanel();
        jPanel159 = new javax.swing.JPanel();
        jPanel160 = new javax.swing.JPanel();
        jPanel161 = new javax.swing.JPanel();
        jPanel162 = new javax.swing.JPanel();
        jPanel163 = new javax.swing.JPanel();
        jPanel164 = new javax.swing.JPanel();
        jPanel165 = new javax.swing.JPanel();
        jPanel166 = new javax.swing.JPanel();
        jPanel167 = new javax.swing.JPanel();
        jPanel168 = new javax.swing.JPanel();
        jPanel169 = new javax.swing.JPanel();
        jPanel170 = new javax.swing.JPanel();
        jPanel171 = new javax.swing.JPanel();
        jPanel172 = new javax.swing.JPanel();
        jPanel173 = new javax.swing.JPanel();
        jPanel174 = new javax.swing.JPanel();
        jPanel175 = new javax.swing.JPanel();
        jPanel176 = new javax.swing.JPanel();
        jPanel177 = new javax.swing.JPanel();
        jPanel178 = new javax.swing.JPanel();
        jPanel179 = new javax.swing.JPanel();
        jPanel180 = new javax.swing.JPanel();
        jPanel181 = new javax.swing.JPanel();
        jPanel182 = new javax.swing.JPanel();
        jPanel183 = new javax.swing.JPanel();
        jPanel184 = new javax.swing.JPanel();
        jPanel185 = new javax.swing.JPanel();
        jPanel186 = new javax.swing.JPanel();
        jPanel187 = new javax.swing.JPanel();
        jPanel188 = new javax.swing.JPanel();
        jPanel189 = new javax.swing.JPanel();
        jPanel190 = new javax.swing.JPanel();
        jPanel191 = new javax.swing.JPanel();
        jPanel192 = new javax.swing.JPanel();
        jPanel193 = new javax.swing.JPanel();
        jPanel194 = new javax.swing.JPanel();
        jPanel195 = new javax.swing.JPanel();
        jPanel196 = new javax.swing.JPanel();
        jPanel197 = new javax.swing.JPanel();
        jPanel198 = new javax.swing.JPanel();
        jPanel199 = new javax.swing.JPanel();
        jPanel200 = new javax.swing.JPanel();
        jPanel201 = new javax.swing.JPanel();
        jPanel202 = new javax.swing.JPanel();
        jPanel203 = new javax.swing.JPanel();
        jPanel204 = new javax.swing.JPanel();
        jPanel205 = new javax.swing.JPanel();
        jPanel206 = new javax.swing.JPanel();
        jPanel207 = new javax.swing.JPanel();
        jPanel208 = new javax.swing.JPanel();
        jPanel209 = new javax.swing.JPanel();
        jPanel210 = new javax.swing.JPanel();
        jPanel211 = new javax.swing.JPanel();
        jPanel212 = new javax.swing.JPanel();
        jPanel213 = new javax.swing.JPanel();
        jPanel214 = new javax.swing.JPanel();
        jPanel215 = new javax.swing.JPanel();
        jPanel216 = new javax.swing.JPanel();
        jPanel217 = new javax.swing.JPanel();
        jPanel218 = new javax.swing.JPanel();
        jPanel219 = new javax.swing.JPanel();
        jPanel220 = new javax.swing.JPanel();
        jPanel221 = new javax.swing.JPanel();
        jPanel222 = new javax.swing.JPanel();
        jPanel223 = new javax.swing.JPanel();
        jPanel224 = new javax.swing.JPanel();
        jPanel225 = new javax.swing.JPanel();
        jPanel226 = new javax.swing.JPanel();
        jPanel227 = new javax.swing.JPanel();
        jPanel228 = new javax.swing.JPanel();
        jPanel229 = new javax.swing.JPanel();
        jPanel230 = new javax.swing.JPanel();
        jPanel231 = new javax.swing.JPanel();
        jPanel232 = new javax.swing.JPanel();
        jPanel233 = new javax.swing.JPanel();
        jPanel234 = new javax.swing.JPanel();
        jPanel235 = new javax.swing.JPanel();
        jPanel236 = new javax.swing.JPanel();
        jPanel237 = new javax.swing.JPanel();
        jPanel238 = new javax.swing.JPanel();
        jPanel239 = new javax.swing.JPanel();
        jPanel240 = new javax.swing.JPanel();
        jPanel241 = new javax.swing.JPanel();
        jPanel242 = new javax.swing.JPanel();
        jPanel243 = new javax.swing.JPanel();
        jPanel244 = new javax.swing.JPanel();
        jPanel245 = new javax.swing.JPanel();
        jPanel246 = new javax.swing.JPanel();
        jPanel247 = new javax.swing.JPanel();
        jPanel248 = new javax.swing.JPanel();
        jPanel249 = new javax.swing.JPanel();
        jPanel250 = new javax.swing.JPanel();
        jPanel251 = new javax.swing.JPanel();
        jPanel252 = new javax.swing.JPanel();
        jPanel253 = new javax.swing.JPanel();
        jPanel254 = new javax.swing.JPanel();
        jPanel255 = new javax.swing.JPanel();
        jPanel256 = new javax.swing.JPanel();
        jPanel257 = new javax.swing.JPanel();
        jPanel258 = new javax.swing.JPanel();
        jPanel259 = new javax.swing.JPanel();
        jPanel260 = new javax.swing.JPanel();
        jPanel261 = new javax.swing.JPanel();
        jPanel262 = new javax.swing.JPanel();
        jPanel263 = new javax.swing.JPanel();
        jPanel264 = new javax.swing.JPanel();
        jPanel265 = new javax.swing.JPanel();
        jPanel266 = new javax.swing.JPanel();
        jPanel267 = new javax.swing.JPanel();
        jPanel268 = new javax.swing.JPanel();
        jPanel269 = new javax.swing.JPanel();
        jPanel270 = new javax.swing.JPanel();
        jPanel271 = new javax.swing.JPanel();
        jPanel272 = new javax.swing.JPanel();
        jPanel273 = new javax.swing.JPanel();
        jPanel274 = new javax.swing.JPanel();
        jPanel275 = new javax.swing.JPanel();
        jPanel276 = new javax.swing.JPanel();
        jPanel277 = new javax.swing.JPanel();
        jPanel278 = new javax.swing.JPanel();
        jPanel279 = new javax.swing.JPanel();
        jPanel280 = new javax.swing.JPanel();
        jPanel281 = new javax.swing.JPanel();
        jPanel282 = new javax.swing.JPanel();
        jPanel283 = new javax.swing.JPanel();
        jPanel284 = new javax.swing.JPanel();
        jPanel285 = new javax.swing.JPanel();
        jPanel286 = new javax.swing.JPanel();
        jPanel287 = new javax.swing.JPanel();
        jPanel288 = new javax.swing.JPanel();
        jPanel289 = new javax.swing.JPanel();
        jPanel290 = new javax.swing.JPanel();
        jPanel291 = new javax.swing.JPanel();
        jPanel292 = new javax.swing.JPanel();
        jPanel293 = new javax.swing.JPanel();
        jPanel294 = new javax.swing.JPanel();
        jPanel295 = new javax.swing.JPanel();
        jPanel296 = new javax.swing.JPanel();
        jPanel297 = new javax.swing.JPanel();
        jPanel298 = new javax.swing.JPanel();
        jPanel299 = new javax.swing.JPanel();
        jPanel300 = new javax.swing.JPanel();
        jPanel301 = new javax.swing.JPanel();
        jPanel302 = new javax.swing.JPanel();
        jPanel303 = new javax.swing.JPanel();
        jPanel304 = new javax.swing.JPanel();
        jPanel305 = new javax.swing.JPanel();
        jPanel306 = new javax.swing.JPanel();
        jPanel307 = new javax.swing.JPanel();
        jPanel308 = new javax.swing.JPanel();
        jPanel309 = new javax.swing.JPanel();
        jPanel310 = new javax.swing.JPanel();
        jPanel311 = new javax.swing.JPanel();
        jPanel312 = new javax.swing.JPanel();
        jPanel313 = new javax.swing.JPanel();
        jPanel314 = new javax.swing.JPanel();
        jPanel315 = new javax.swing.JPanel();
        jPanel316 = new javax.swing.JPanel();
        jPanel317 = new javax.swing.JPanel();
        jPanel318 = new javax.swing.JPanel();
        jPanel319 = new javax.swing.JPanel();
        jPanel320 = new javax.swing.JPanel();
        jPanel321 = new javax.swing.JPanel();
        jPanel322 = new javax.swing.JPanel();
        jPanel323 = new javax.swing.JPanel();
        jPanel324 = new javax.swing.JPanel();
        jPanel325 = new javax.swing.JPanel();
        jPanel326 = new javax.swing.JPanel();
        jPanel327 = new javax.swing.JPanel();
        jPanel328 = new javax.swing.JPanel();
        jPanel329 = new javax.swing.JPanel();
        jPanel330 = new javax.swing.JPanel();
        jPanel331 = new javax.swing.JPanel();
        jPanel332 = new javax.swing.JPanel();
        jPanel333 = new javax.swing.JPanel();
        jPanel334 = new javax.swing.JPanel();
        jPanel335 = new javax.swing.JPanel();
        jPanel336 = new javax.swing.JPanel();
        jPanel337 = new javax.swing.JPanel();
        jPanel338 = new javax.swing.JPanel();
        jPanel339 = new javax.swing.JPanel();
        jPanel340 = new javax.swing.JPanel();
        jPanel341 = new javax.swing.JPanel();
        jPanel342 = new javax.swing.JPanel();
        jPanel343 = new javax.swing.JPanel();
        jPanel344 = new javax.swing.JPanel();
        jPanel345 = new javax.swing.JPanel();
        jPanel346 = new javax.swing.JPanel();
        jPanel347 = new javax.swing.JPanel();
        jPanel348 = new javax.swing.JPanel();
        jPanel349 = new javax.swing.JPanel();
        jPanel350 = new javax.swing.JPanel();
        jPanel351 = new javax.swing.JPanel();
        jPanel352 = new javax.swing.JPanel();
        jPanel353 = new javax.swing.JPanel();
        jPanel354 = new javax.swing.JPanel();
        jPanel355 = new javax.swing.JPanel();
        jPanel356 = new javax.swing.JPanel();
        jPanel357 = new javax.swing.JPanel();
        jPanel358 = new javax.swing.JPanel();
        jPanel359 = new javax.swing.JPanel();
        jPanel360 = new javax.swing.JPanel();
        jPanel361 = new javax.swing.JPanel();
        jPanel362 = new javax.swing.JPanel();
        jPanel363 = new javax.swing.JPanel();
        jPanel364 = new javax.swing.JPanel();
        jPanel365 = new javax.swing.JPanel();
        jPanel366 = new javax.swing.JPanel();
        jPanel367 = new javax.swing.JPanel();
        jPanel368 = new javax.swing.JPanel();
        jPanel369 = new javax.swing.JPanel();
        jPanel370 = new javax.swing.JPanel();
        jPanel371 = new javax.swing.JPanel();
        jPanel372 = new javax.swing.JPanel();
        jPanel373 = new javax.swing.JPanel();
        jPanel374 = new javax.swing.JPanel();
        jPanel375 = new javax.swing.JPanel();
        jPanel376 = new javax.swing.JPanel();
        jPanel377 = new javax.swing.JPanel();
        jPanel378 = new javax.swing.JPanel();
        jPanel379 = new javax.swing.JPanel();
        jPanel380 = new javax.swing.JPanel();
        jPanel381 = new javax.swing.JPanel();
        jPanel382 = new javax.swing.JPanel();
        jPanel383 = new javax.swing.JPanel();
        jPanel384 = new javax.swing.JPanel();
        jPanel385 = new javax.swing.JPanel();
        jPanel386 = new javax.swing.JPanel();
        jPanel387 = new javax.swing.JPanel();
        jPanel388 = new javax.swing.JPanel();
        jPanel389 = new javax.swing.JPanel();
        jPanel390 = new javax.swing.JPanel();
        jPanel391 = new javax.swing.JPanel();
        jPanel392 = new javax.swing.JPanel();
        jPanel393 = new javax.swing.JPanel();
        jPanel394 = new javax.swing.JPanel();
        jPanel395 = new javax.swing.JPanel();
        jPanel396 = new javax.swing.JPanel();
        jPanel397 = new javax.swing.JPanel();
        jPanel398 = new javax.swing.JPanel();
        jPanel399 = new javax.swing.JPanel();
        jPanel400 = new javax.swing.JPanel();
        jPanel401 = new javax.swing.JPanel();
        jPanel402 = new javax.swing.JPanel();
        jPanel403 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 153, 101));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LabelDate.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        LabelDate.setText("jLabel15");
        jPanel3.add(LabelDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel8.setText("Reg No");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 140, 30));

        jLabel1.setFont(new java.awt.Font("Algerian", 0, 50)); // NOI18N
        jLabel1.setText("WELCOME");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 250, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Desktop\\ETHOM FILES\\NEW ICON\\stu.PNG")); // NOI18N
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jButton3.setBackground(new java.awt.Color(153, 204, 0));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 51, 0));
        jButton3.setText("SUBMIT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 230, 200, 100));

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 70)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("00");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 90, 100));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 70)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("00");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 100));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 70)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("00");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 80, 100));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, 280, 140));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel9.setText("Date: ");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, 30));

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jRadioButton1.setText("jRadioButton1");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, -1));

        jTextArea1.setBackground(new java.awt.Color(0, 204, 204));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 480, -1));

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jRadioButton2.setText("jRadioButton2");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, -1));

        jRadioButton3.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jRadioButton3.setText("jRadioButton3");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 520, -1));

        jRadioButton4.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton4.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jRadioButton4.setText("jRadioButton4");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jButton1.setText("NEXT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 450, 120, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel4.setText("Q No");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 80, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.add(jPanel7);

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.add(jPanel9);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.add(jPanel11);

        jPanel8.add(jPanel10);

        jPanel4.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.add(jPanel15);

        jPanel12.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.add(jPanel17);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.add(jPanel19);

        jPanel16.add(jPanel18);

        jPanel12.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel4.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 520, 40));

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.add(jPanel23);

        jPanel20.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.add(jPanel25);

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.add(jPanel27);

        jPanel24.add(jPanel26);

        jPanel20.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.add(jPanel31);

        jPanel28.add(jPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));
        jPanel32.add(jPanel33);

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
        jPanel34.add(jPanel35);

        jPanel32.add(jPanel34);

        jPanel28.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel20.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));
        jPanel36.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));
        jPanel36.add(jPanel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel38.setBackground(new java.awt.Color(255, 255, 255));

        jPanel39.setBackground(new java.awt.Color(255, 255, 255));
        jPanel38.add(jPanel39);

        jPanel36.add(jPanel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel40.setBackground(new java.awt.Color(255, 255, 255));

        jPanel41.setBackground(new java.awt.Color(255, 255, 255));
        jPanel40.add(jPanel41);

        jPanel42.setBackground(new java.awt.Color(255, 255, 255));

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));
        jPanel42.add(jPanel43);

        jPanel40.add(jPanel42);

        jPanel36.add(jPanel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel44.setBackground(new java.awt.Color(255, 255, 255));
        jPanel44.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel45.setBackground(new java.awt.Color(255, 255, 255));
        jPanel44.add(jPanel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel46.setBackground(new java.awt.Color(255, 255, 255));

        jPanel47.setBackground(new java.awt.Color(255, 255, 255));
        jPanel46.add(jPanel47);

        jPanel44.add(jPanel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel48.setBackground(new java.awt.Color(255, 255, 255));

        jPanel49.setBackground(new java.awt.Color(255, 255, 255));
        jPanel48.add(jPanel49);

        jPanel50.setBackground(new java.awt.Color(255, 255, 255));

        jPanel51.setBackground(new java.awt.Color(255, 255, 255));
        jPanel50.add(jPanel51);

        jPanel48.add(jPanel50);

        jPanel44.add(jPanel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel36.add(jPanel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel20.add(jPanel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 520, 40));

        jPanel52.setBackground(new java.awt.Color(255, 255, 255));
        jPanel52.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel53.setBackground(new java.awt.Color(255, 255, 255));
        jPanel52.add(jPanel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel54.setBackground(new java.awt.Color(255, 255, 255));

        jPanel55.setBackground(new java.awt.Color(255, 255, 255));
        jPanel54.add(jPanel55);

        jPanel52.add(jPanel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel56.setBackground(new java.awt.Color(255, 255, 255));

        jPanel57.setBackground(new java.awt.Color(255, 255, 255));
        jPanel56.add(jPanel57);

        jPanel58.setBackground(new java.awt.Color(255, 255, 255));

        jPanel59.setBackground(new java.awt.Color(255, 255, 255));
        jPanel58.add(jPanel59);

        jPanel56.add(jPanel58);

        jPanel52.add(jPanel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel60.setBackground(new java.awt.Color(255, 255, 255));
        jPanel60.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel61.setBackground(new java.awt.Color(255, 255, 255));
        jPanel60.add(jPanel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel62.setBackground(new java.awt.Color(255, 255, 255));

        jPanel63.setBackground(new java.awt.Color(255, 255, 255));
        jPanel62.add(jPanel63);

        jPanel60.add(jPanel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel64.setBackground(new java.awt.Color(255, 255, 255));

        jPanel65.setBackground(new java.awt.Color(255, 255, 255));
        jPanel64.add(jPanel65);

        jPanel66.setBackground(new java.awt.Color(255, 255, 255));

        jPanel67.setBackground(new java.awt.Color(255, 255, 255));
        jPanel66.add(jPanel67);

        jPanel64.add(jPanel66);

        jPanel60.add(jPanel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel52.add(jPanel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel68.setBackground(new java.awt.Color(255, 255, 255));
        jPanel68.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel69.setBackground(new java.awt.Color(255, 255, 255));
        jPanel68.add(jPanel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel70.setBackground(new java.awt.Color(255, 255, 255));

        jPanel71.setBackground(new java.awt.Color(255, 255, 255));
        jPanel70.add(jPanel71);

        jPanel68.add(jPanel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel72.setBackground(new java.awt.Color(255, 255, 255));

        jPanel73.setBackground(new java.awt.Color(255, 255, 255));
        jPanel72.add(jPanel73);

        jPanel74.setBackground(new java.awt.Color(255, 255, 255));

        jPanel75.setBackground(new java.awt.Color(255, 255, 255));
        jPanel74.add(jPanel75);

        jPanel72.add(jPanel74);

        jPanel68.add(jPanel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel76.setBackground(new java.awt.Color(255, 255, 255));
        jPanel76.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel77.setBackground(new java.awt.Color(255, 255, 255));
        jPanel76.add(jPanel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel78.setBackground(new java.awt.Color(255, 255, 255));

        jPanel79.setBackground(new java.awt.Color(255, 255, 255));
        jPanel78.add(jPanel79);

        jPanel76.add(jPanel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel80.setBackground(new java.awt.Color(255, 255, 255));

        jPanel81.setBackground(new java.awt.Color(255, 255, 255));
        jPanel80.add(jPanel81);

        jPanel82.setBackground(new java.awt.Color(255, 255, 255));

        jPanel83.setBackground(new java.awt.Color(255, 255, 255));
        jPanel82.add(jPanel83);

        jPanel80.add(jPanel82);

        jPanel76.add(jPanel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel68.add(jPanel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel52.add(jPanel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 520, 40));

        jPanel20.add(jPanel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 520, 40));

        jPanel84.setBackground(new java.awt.Color(255, 255, 255));
        jPanel84.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel85.setBackground(new java.awt.Color(255, 255, 255));
        jPanel84.add(jPanel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel86.setBackground(new java.awt.Color(255, 255, 255));

        jPanel87.setBackground(new java.awt.Color(255, 255, 255));
        jPanel86.add(jPanel87);

        jPanel84.add(jPanel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel88.setBackground(new java.awt.Color(255, 255, 255));

        jPanel89.setBackground(new java.awt.Color(255, 255, 255));
        jPanel88.add(jPanel89);

        jPanel90.setBackground(new java.awt.Color(255, 255, 255));

        jPanel91.setBackground(new java.awt.Color(255, 255, 255));
        jPanel90.add(jPanel91);

        jPanel88.add(jPanel90);

        jPanel84.add(jPanel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel92.setBackground(new java.awt.Color(255, 255, 255));
        jPanel92.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel93.setBackground(new java.awt.Color(255, 255, 255));
        jPanel92.add(jPanel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel94.setBackground(new java.awt.Color(255, 255, 255));

        jPanel95.setBackground(new java.awt.Color(255, 255, 255));
        jPanel94.add(jPanel95);

        jPanel92.add(jPanel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel96.setBackground(new java.awt.Color(255, 255, 255));

        jPanel97.setBackground(new java.awt.Color(255, 255, 255));
        jPanel96.add(jPanel97);

        jPanel98.setBackground(new java.awt.Color(255, 255, 255));

        jPanel99.setBackground(new java.awt.Color(255, 255, 255));
        jPanel98.add(jPanel99);

        jPanel96.add(jPanel98);

        jPanel92.add(jPanel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel84.add(jPanel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel100.setBackground(new java.awt.Color(255, 255, 255));
        jPanel100.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel101.setBackground(new java.awt.Color(255, 255, 255));
        jPanel100.add(jPanel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel102.setBackground(new java.awt.Color(255, 255, 255));

        jPanel103.setBackground(new java.awt.Color(255, 255, 255));
        jPanel102.add(jPanel103);

        jPanel100.add(jPanel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel104.setBackground(new java.awt.Color(255, 255, 255));

        jPanel105.setBackground(new java.awt.Color(255, 255, 255));
        jPanel104.add(jPanel105);

        jPanel106.setBackground(new java.awt.Color(255, 255, 255));

        jPanel107.setBackground(new java.awt.Color(255, 255, 255));
        jPanel106.add(jPanel107);

        jPanel104.add(jPanel106);

        jPanel100.add(jPanel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel108.setBackground(new java.awt.Color(255, 255, 255));
        jPanel108.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel109.setBackground(new java.awt.Color(255, 255, 255));
        jPanel108.add(jPanel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel110.setBackground(new java.awt.Color(255, 255, 255));

        jPanel111.setBackground(new java.awt.Color(255, 255, 255));
        jPanel110.add(jPanel111);

        jPanel108.add(jPanel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel112.setBackground(new java.awt.Color(255, 255, 255));

        jPanel113.setBackground(new java.awt.Color(255, 255, 255));
        jPanel112.add(jPanel113);

        jPanel114.setBackground(new java.awt.Color(255, 255, 255));

        jPanel115.setBackground(new java.awt.Color(255, 255, 255));
        jPanel114.add(jPanel115);

        jPanel112.add(jPanel114);

        jPanel108.add(jPanel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel100.add(jPanel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel84.add(jPanel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 520, 40));

        jPanel116.setBackground(new java.awt.Color(255, 255, 255));
        jPanel116.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel117.setBackground(new java.awt.Color(255, 255, 255));
        jPanel116.add(jPanel117, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel118.setBackground(new java.awt.Color(255, 255, 255));

        jPanel119.setBackground(new java.awt.Color(255, 255, 255));
        jPanel118.add(jPanel119);

        jPanel116.add(jPanel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel120.setBackground(new java.awt.Color(255, 255, 255));

        jPanel121.setBackground(new java.awt.Color(255, 255, 255));
        jPanel120.add(jPanel121);

        jPanel122.setBackground(new java.awt.Color(255, 255, 255));

        jPanel123.setBackground(new java.awt.Color(255, 255, 255));
        jPanel122.add(jPanel123);

        jPanel120.add(jPanel122);

        jPanel116.add(jPanel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel124.setBackground(new java.awt.Color(255, 255, 255));
        jPanel124.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel125.setBackground(new java.awt.Color(255, 255, 255));
        jPanel124.add(jPanel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel126.setBackground(new java.awt.Color(255, 255, 255));

        jPanel127.setBackground(new java.awt.Color(255, 255, 255));
        jPanel126.add(jPanel127);

        jPanel124.add(jPanel126, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel128.setBackground(new java.awt.Color(255, 255, 255));

        jPanel129.setBackground(new java.awt.Color(255, 255, 255));
        jPanel128.add(jPanel129);

        jPanel130.setBackground(new java.awt.Color(255, 255, 255));

        jPanel131.setBackground(new java.awt.Color(255, 255, 255));
        jPanel130.add(jPanel131);

        jPanel128.add(jPanel130);

        jPanel124.add(jPanel128, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel116.add(jPanel124, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel132.setBackground(new java.awt.Color(255, 255, 255));
        jPanel132.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel133.setBackground(new java.awt.Color(255, 255, 255));
        jPanel132.add(jPanel133, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel134.setBackground(new java.awt.Color(255, 255, 255));

        jPanel135.setBackground(new java.awt.Color(255, 255, 255));
        jPanel134.add(jPanel135);

        jPanel132.add(jPanel134, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel136.setBackground(new java.awt.Color(255, 255, 255));

        jPanel137.setBackground(new java.awt.Color(255, 255, 255));
        jPanel136.add(jPanel137);

        jPanel138.setBackground(new java.awt.Color(255, 255, 255));

        jPanel139.setBackground(new java.awt.Color(255, 255, 255));
        jPanel138.add(jPanel139);

        jPanel136.add(jPanel138);

        jPanel132.add(jPanel136, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel140.setBackground(new java.awt.Color(255, 255, 255));
        jPanel140.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel141.setBackground(new java.awt.Color(255, 255, 255));
        jPanel140.add(jPanel141, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel142.setBackground(new java.awt.Color(255, 255, 255));

        jPanel143.setBackground(new java.awt.Color(255, 255, 255));
        jPanel142.add(jPanel143);

        jPanel140.add(jPanel142, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel144.setBackground(new java.awt.Color(255, 255, 255));

        jPanel145.setBackground(new java.awt.Color(255, 255, 255));
        jPanel144.add(jPanel145);

        jPanel146.setBackground(new java.awt.Color(255, 255, 255));

        jPanel147.setBackground(new java.awt.Color(255, 255, 255));
        jPanel146.add(jPanel147);

        jPanel144.add(jPanel146);

        jPanel140.add(jPanel144, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel132.add(jPanel140, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel116.add(jPanel132, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 520, 40));

        jPanel84.add(jPanel116, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 520, 40));

        jPanel20.add(jPanel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 520, 40));

        jPanel1.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel148.setBackground(new java.awt.Color(255, 255, 255));
        jPanel148.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel149.setBackground(new java.awt.Color(255, 255, 255));
        jPanel148.add(jPanel149, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel150.setBackground(new java.awt.Color(255, 255, 255));

        jPanel151.setBackground(new java.awt.Color(255, 255, 255));
        jPanel150.add(jPanel151);

        jPanel148.add(jPanel150, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel152.setBackground(new java.awt.Color(255, 255, 255));

        jPanel153.setBackground(new java.awt.Color(255, 255, 255));
        jPanel152.add(jPanel153);

        jPanel154.setBackground(new java.awt.Color(255, 255, 255));

        jPanel155.setBackground(new java.awt.Color(255, 255, 255));
        jPanel154.add(jPanel155);

        jPanel152.add(jPanel154);

        jPanel148.add(jPanel152, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel156.setBackground(new java.awt.Color(255, 255, 255));
        jPanel156.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel157.setBackground(new java.awt.Color(255, 255, 255));
        jPanel156.add(jPanel157, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel158.setBackground(new java.awt.Color(255, 255, 255));

        jPanel159.setBackground(new java.awt.Color(255, 255, 255));
        jPanel158.add(jPanel159);

        jPanel156.add(jPanel158, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel160.setBackground(new java.awt.Color(255, 255, 255));

        jPanel161.setBackground(new java.awt.Color(255, 255, 255));
        jPanel160.add(jPanel161);

        jPanel162.setBackground(new java.awt.Color(255, 255, 255));

        jPanel163.setBackground(new java.awt.Color(255, 255, 255));
        jPanel162.add(jPanel163);

        jPanel160.add(jPanel162);

        jPanel156.add(jPanel160, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel148.add(jPanel156, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel164.setBackground(new java.awt.Color(255, 255, 255));
        jPanel164.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel165.setBackground(new java.awt.Color(255, 255, 255));
        jPanel164.add(jPanel165, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel166.setBackground(new java.awt.Color(255, 255, 255));

        jPanel167.setBackground(new java.awt.Color(255, 255, 255));
        jPanel166.add(jPanel167);

        jPanel164.add(jPanel166, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel168.setBackground(new java.awt.Color(255, 255, 255));

        jPanel169.setBackground(new java.awt.Color(255, 255, 255));
        jPanel168.add(jPanel169);

        jPanel170.setBackground(new java.awt.Color(255, 255, 255));

        jPanel171.setBackground(new java.awt.Color(255, 255, 255));
        jPanel170.add(jPanel171);

        jPanel168.add(jPanel170);

        jPanel164.add(jPanel168, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel172.setBackground(new java.awt.Color(255, 255, 255));
        jPanel172.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel173.setBackground(new java.awt.Color(255, 255, 255));
        jPanel172.add(jPanel173, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel174.setBackground(new java.awt.Color(255, 255, 255));

        jPanel175.setBackground(new java.awt.Color(255, 255, 255));
        jPanel174.add(jPanel175);

        jPanel172.add(jPanel174, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel176.setBackground(new java.awt.Color(255, 255, 255));

        jPanel177.setBackground(new java.awt.Color(255, 255, 255));
        jPanel176.add(jPanel177);

        jPanel178.setBackground(new java.awt.Color(255, 255, 255));

        jPanel179.setBackground(new java.awt.Color(255, 255, 255));
        jPanel178.add(jPanel179);

        jPanel176.add(jPanel178);

        jPanel172.add(jPanel176, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel164.add(jPanel172, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel148.add(jPanel164, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 520, 40));

        jPanel180.setBackground(new java.awt.Color(255, 255, 255));
        jPanel180.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel181.setBackground(new java.awt.Color(255, 255, 255));
        jPanel180.add(jPanel181, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel182.setBackground(new java.awt.Color(255, 255, 255));

        jPanel183.setBackground(new java.awt.Color(255, 255, 255));
        jPanel182.add(jPanel183);

        jPanel180.add(jPanel182, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel184.setBackground(new java.awt.Color(255, 255, 255));

        jPanel185.setBackground(new java.awt.Color(255, 255, 255));
        jPanel184.add(jPanel185);

        jPanel186.setBackground(new java.awt.Color(255, 255, 255));

        jPanel187.setBackground(new java.awt.Color(255, 255, 255));
        jPanel186.add(jPanel187);

        jPanel184.add(jPanel186);

        jPanel180.add(jPanel184, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel188.setBackground(new java.awt.Color(255, 255, 255));
        jPanel188.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel189.setBackground(new java.awt.Color(255, 255, 255));
        jPanel188.add(jPanel189, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel190.setBackground(new java.awt.Color(255, 255, 255));

        jPanel191.setBackground(new java.awt.Color(255, 255, 255));
        jPanel190.add(jPanel191);

        jPanel188.add(jPanel190, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel192.setBackground(new java.awt.Color(255, 255, 255));

        jPanel193.setBackground(new java.awt.Color(255, 255, 255));
        jPanel192.add(jPanel193);

        jPanel194.setBackground(new java.awt.Color(255, 255, 255));

        jPanel195.setBackground(new java.awt.Color(255, 255, 255));
        jPanel194.add(jPanel195);

        jPanel192.add(jPanel194);

        jPanel188.add(jPanel192, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel180.add(jPanel188, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel196.setBackground(new java.awt.Color(255, 255, 255));
        jPanel196.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel197.setBackground(new java.awt.Color(255, 255, 255));
        jPanel196.add(jPanel197, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel198.setBackground(new java.awt.Color(255, 255, 255));

        jPanel199.setBackground(new java.awt.Color(255, 255, 255));
        jPanel198.add(jPanel199);

        jPanel196.add(jPanel198, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel200.setBackground(new java.awt.Color(255, 255, 255));

        jPanel201.setBackground(new java.awt.Color(255, 255, 255));
        jPanel200.add(jPanel201);

        jPanel202.setBackground(new java.awt.Color(255, 255, 255));

        jPanel203.setBackground(new java.awt.Color(255, 255, 255));
        jPanel202.add(jPanel203);

        jPanel200.add(jPanel202);

        jPanel196.add(jPanel200, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel204.setBackground(new java.awt.Color(255, 255, 255));
        jPanel204.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel205.setBackground(new java.awt.Color(255, 255, 255));
        jPanel204.add(jPanel205, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel206.setBackground(new java.awt.Color(255, 255, 255));

        jPanel207.setBackground(new java.awt.Color(255, 255, 255));
        jPanel206.add(jPanel207);

        jPanel204.add(jPanel206, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel208.setBackground(new java.awt.Color(255, 255, 255));

        jPanel209.setBackground(new java.awt.Color(255, 255, 255));
        jPanel208.add(jPanel209);

        jPanel210.setBackground(new java.awt.Color(255, 255, 255));

        jPanel211.setBackground(new java.awt.Color(255, 255, 255));
        jPanel210.add(jPanel211);

        jPanel208.add(jPanel210);

        jPanel204.add(jPanel208, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel196.add(jPanel204, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel180.add(jPanel196, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 520, 40));

        jPanel148.add(jPanel180, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 520, 40));

        jPanel212.setBackground(new java.awt.Color(255, 255, 255));
        jPanel212.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel213.setBackground(new java.awt.Color(255, 255, 255));
        jPanel212.add(jPanel213, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel214.setBackground(new java.awt.Color(255, 255, 255));

        jPanel215.setBackground(new java.awt.Color(255, 255, 255));
        jPanel214.add(jPanel215);

        jPanel212.add(jPanel214, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel216.setBackground(new java.awt.Color(255, 255, 255));

        jPanel217.setBackground(new java.awt.Color(255, 255, 255));
        jPanel216.add(jPanel217);

        jPanel218.setBackground(new java.awt.Color(255, 255, 255));

        jPanel219.setBackground(new java.awt.Color(255, 255, 255));
        jPanel218.add(jPanel219);

        jPanel216.add(jPanel218);

        jPanel212.add(jPanel216, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel220.setBackground(new java.awt.Color(255, 255, 255));
        jPanel220.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel221.setBackground(new java.awt.Color(255, 255, 255));
        jPanel220.add(jPanel221, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel222.setBackground(new java.awt.Color(255, 255, 255));

        jPanel223.setBackground(new java.awt.Color(255, 255, 255));
        jPanel222.add(jPanel223);

        jPanel220.add(jPanel222, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel224.setBackground(new java.awt.Color(255, 255, 255));

        jPanel225.setBackground(new java.awt.Color(255, 255, 255));
        jPanel224.add(jPanel225);

        jPanel226.setBackground(new java.awt.Color(255, 255, 255));

        jPanel227.setBackground(new java.awt.Color(255, 255, 255));
        jPanel226.add(jPanel227);

        jPanel224.add(jPanel226);

        jPanel220.add(jPanel224, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel212.add(jPanel220, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel228.setBackground(new java.awt.Color(255, 255, 255));
        jPanel228.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel229.setBackground(new java.awt.Color(255, 255, 255));
        jPanel228.add(jPanel229, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel230.setBackground(new java.awt.Color(255, 255, 255));

        jPanel231.setBackground(new java.awt.Color(255, 255, 255));
        jPanel230.add(jPanel231);

        jPanel228.add(jPanel230, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel232.setBackground(new java.awt.Color(255, 255, 255));

        jPanel233.setBackground(new java.awt.Color(255, 255, 255));
        jPanel232.add(jPanel233);

        jPanel234.setBackground(new java.awt.Color(255, 255, 255));

        jPanel235.setBackground(new java.awt.Color(255, 255, 255));
        jPanel234.add(jPanel235);

        jPanel232.add(jPanel234);

        jPanel228.add(jPanel232, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel236.setBackground(new java.awt.Color(255, 255, 255));
        jPanel236.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel237.setBackground(new java.awt.Color(255, 255, 255));
        jPanel236.add(jPanel237, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel238.setBackground(new java.awt.Color(255, 255, 255));

        jPanel239.setBackground(new java.awt.Color(255, 255, 255));
        jPanel238.add(jPanel239);

        jPanel236.add(jPanel238, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel240.setBackground(new java.awt.Color(255, 255, 255));

        jPanel241.setBackground(new java.awt.Color(255, 255, 255));
        jPanel240.add(jPanel241);

        jPanel242.setBackground(new java.awt.Color(255, 255, 255));

        jPanel243.setBackground(new java.awt.Color(255, 255, 255));
        jPanel242.add(jPanel243);

        jPanel240.add(jPanel242);

        jPanel236.add(jPanel240, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel228.add(jPanel236, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel212.add(jPanel228, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 520, 40));

        jPanel244.setBackground(new java.awt.Color(255, 255, 255));
        jPanel244.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel245.setBackground(new java.awt.Color(255, 255, 255));
        jPanel244.add(jPanel245, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel246.setBackground(new java.awt.Color(255, 255, 255));

        jPanel247.setBackground(new java.awt.Color(255, 255, 255));
        jPanel246.add(jPanel247);

        jPanel244.add(jPanel246, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel248.setBackground(new java.awt.Color(255, 255, 255));

        jPanel249.setBackground(new java.awt.Color(255, 255, 255));
        jPanel248.add(jPanel249);

        jPanel250.setBackground(new java.awt.Color(255, 255, 255));

        jPanel251.setBackground(new java.awt.Color(255, 255, 255));
        jPanel250.add(jPanel251);

        jPanel248.add(jPanel250);

        jPanel244.add(jPanel248, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel252.setBackground(new java.awt.Color(255, 255, 255));
        jPanel252.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel253.setBackground(new java.awt.Color(255, 255, 255));
        jPanel252.add(jPanel253, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel254.setBackground(new java.awt.Color(255, 255, 255));

        jPanel255.setBackground(new java.awt.Color(255, 255, 255));
        jPanel254.add(jPanel255);

        jPanel252.add(jPanel254, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel256.setBackground(new java.awt.Color(255, 255, 255));

        jPanel257.setBackground(new java.awt.Color(255, 255, 255));
        jPanel256.add(jPanel257);

        jPanel258.setBackground(new java.awt.Color(255, 255, 255));

        jPanel259.setBackground(new java.awt.Color(255, 255, 255));
        jPanel258.add(jPanel259);

        jPanel256.add(jPanel258);

        jPanel252.add(jPanel256, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel244.add(jPanel252, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel260.setBackground(new java.awt.Color(255, 255, 255));
        jPanel260.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel261.setBackground(new java.awt.Color(255, 255, 255));
        jPanel260.add(jPanel261, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel262.setBackground(new java.awt.Color(255, 255, 255));

        jPanel263.setBackground(new java.awt.Color(255, 255, 255));
        jPanel262.add(jPanel263);

        jPanel260.add(jPanel262, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel264.setBackground(new java.awt.Color(255, 255, 255));

        jPanel265.setBackground(new java.awt.Color(255, 255, 255));
        jPanel264.add(jPanel265);

        jPanel266.setBackground(new java.awt.Color(255, 255, 255));

        jPanel267.setBackground(new java.awt.Color(255, 255, 255));
        jPanel266.add(jPanel267);

        jPanel264.add(jPanel266);

        jPanel260.add(jPanel264, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel268.setBackground(new java.awt.Color(255, 255, 255));
        jPanel268.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel269.setBackground(new java.awt.Color(255, 255, 255));
        jPanel268.add(jPanel269, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel270.setBackground(new java.awt.Color(255, 255, 255));

        jPanel271.setBackground(new java.awt.Color(255, 255, 255));
        jPanel270.add(jPanel271);

        jPanel268.add(jPanel270, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel272.setBackground(new java.awt.Color(255, 255, 255));

        jPanel273.setBackground(new java.awt.Color(255, 255, 255));
        jPanel272.add(jPanel273);

        jPanel274.setBackground(new java.awt.Color(255, 255, 255));

        jPanel275.setBackground(new java.awt.Color(255, 255, 255));
        jPanel274.add(jPanel275);

        jPanel272.add(jPanel274);

        jPanel268.add(jPanel272, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel260.add(jPanel268, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel244.add(jPanel260, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 520, 40));

        jPanel212.add(jPanel244, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 520, 40));

        jPanel148.add(jPanel212, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 520, 40));

        jPanel1.add(jPanel148, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel276.setBackground(new java.awt.Color(255, 255, 255));
        jPanel276.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel277.setBackground(new java.awt.Color(255, 255, 255));
        jPanel276.add(jPanel277, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel278.setBackground(new java.awt.Color(255, 255, 255));

        jPanel279.setBackground(new java.awt.Color(255, 255, 255));
        jPanel278.add(jPanel279);

        jPanel276.add(jPanel278, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel280.setBackground(new java.awt.Color(255, 255, 255));

        jPanel281.setBackground(new java.awt.Color(255, 255, 255));
        jPanel280.add(jPanel281);

        jPanel282.setBackground(new java.awt.Color(255, 255, 255));

        jPanel283.setBackground(new java.awt.Color(255, 255, 255));
        jPanel282.add(jPanel283);

        jPanel280.add(jPanel282);

        jPanel276.add(jPanel280, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel284.setBackground(new java.awt.Color(255, 255, 255));
        jPanel284.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel285.setBackground(new java.awt.Color(255, 255, 255));
        jPanel284.add(jPanel285, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel286.setBackground(new java.awt.Color(255, 255, 255));

        jPanel287.setBackground(new java.awt.Color(255, 255, 255));
        jPanel286.add(jPanel287);

        jPanel284.add(jPanel286, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel288.setBackground(new java.awt.Color(255, 255, 255));

        jPanel289.setBackground(new java.awt.Color(255, 255, 255));
        jPanel288.add(jPanel289);

        jPanel290.setBackground(new java.awt.Color(255, 255, 255));

        jPanel291.setBackground(new java.awt.Color(255, 255, 255));
        jPanel290.add(jPanel291);

        jPanel288.add(jPanel290);

        jPanel284.add(jPanel288, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel276.add(jPanel284, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel292.setBackground(new java.awt.Color(255, 255, 255));
        jPanel292.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel293.setBackground(new java.awt.Color(255, 255, 255));
        jPanel292.add(jPanel293, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel294.setBackground(new java.awt.Color(255, 255, 255));

        jPanel295.setBackground(new java.awt.Color(255, 255, 255));
        jPanel294.add(jPanel295);

        jPanel292.add(jPanel294, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel296.setBackground(new java.awt.Color(255, 255, 255));

        jPanel297.setBackground(new java.awt.Color(255, 255, 255));
        jPanel296.add(jPanel297);

        jPanel298.setBackground(new java.awt.Color(255, 255, 255));

        jPanel299.setBackground(new java.awt.Color(255, 255, 255));
        jPanel298.add(jPanel299);

        jPanel296.add(jPanel298);

        jPanel292.add(jPanel296, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel300.setBackground(new java.awt.Color(255, 255, 255));
        jPanel300.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel301.setBackground(new java.awt.Color(255, 255, 255));
        jPanel300.add(jPanel301, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel302.setBackground(new java.awt.Color(255, 255, 255));

        jPanel303.setBackground(new java.awt.Color(255, 255, 255));
        jPanel302.add(jPanel303);

        jPanel300.add(jPanel302, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel304.setBackground(new java.awt.Color(255, 255, 255));

        jPanel305.setBackground(new java.awt.Color(255, 255, 255));
        jPanel304.add(jPanel305);

        jPanel306.setBackground(new java.awt.Color(255, 255, 255));

        jPanel307.setBackground(new java.awt.Color(255, 255, 255));
        jPanel306.add(jPanel307);

        jPanel304.add(jPanel306);

        jPanel300.add(jPanel304, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel292.add(jPanel300, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel276.add(jPanel292, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 520, 40));

        jPanel308.setBackground(new java.awt.Color(255, 255, 255));
        jPanel308.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel309.setBackground(new java.awt.Color(255, 255, 255));
        jPanel308.add(jPanel309, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel310.setBackground(new java.awt.Color(255, 255, 255));

        jPanel311.setBackground(new java.awt.Color(255, 255, 255));
        jPanel310.add(jPanel311);

        jPanel308.add(jPanel310, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel312.setBackground(new java.awt.Color(255, 255, 255));

        jPanel313.setBackground(new java.awt.Color(255, 255, 255));
        jPanel312.add(jPanel313);

        jPanel314.setBackground(new java.awt.Color(255, 255, 255));

        jPanel315.setBackground(new java.awt.Color(255, 255, 255));
        jPanel314.add(jPanel315);

        jPanel312.add(jPanel314);

        jPanel308.add(jPanel312, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel316.setBackground(new java.awt.Color(255, 255, 255));
        jPanel316.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel317.setBackground(new java.awt.Color(255, 255, 255));
        jPanel316.add(jPanel317, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel318.setBackground(new java.awt.Color(255, 255, 255));

        jPanel319.setBackground(new java.awt.Color(255, 255, 255));
        jPanel318.add(jPanel319);

        jPanel316.add(jPanel318, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel320.setBackground(new java.awt.Color(255, 255, 255));

        jPanel321.setBackground(new java.awt.Color(255, 255, 255));
        jPanel320.add(jPanel321);

        jPanel322.setBackground(new java.awt.Color(255, 255, 255));

        jPanel323.setBackground(new java.awt.Color(255, 255, 255));
        jPanel322.add(jPanel323);

        jPanel320.add(jPanel322);

        jPanel316.add(jPanel320, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel308.add(jPanel316, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel324.setBackground(new java.awt.Color(255, 255, 255));
        jPanel324.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel325.setBackground(new java.awt.Color(255, 255, 255));
        jPanel324.add(jPanel325, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel326.setBackground(new java.awt.Color(255, 255, 255));

        jPanel327.setBackground(new java.awt.Color(255, 255, 255));
        jPanel326.add(jPanel327);

        jPanel324.add(jPanel326, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel328.setBackground(new java.awt.Color(255, 255, 255));

        jPanel329.setBackground(new java.awt.Color(255, 255, 255));
        jPanel328.add(jPanel329);

        jPanel330.setBackground(new java.awt.Color(255, 255, 255));

        jPanel331.setBackground(new java.awt.Color(255, 255, 255));
        jPanel330.add(jPanel331);

        jPanel328.add(jPanel330);

        jPanel324.add(jPanel328, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel332.setBackground(new java.awt.Color(255, 255, 255));
        jPanel332.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel333.setBackground(new java.awt.Color(255, 255, 255));
        jPanel332.add(jPanel333, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel334.setBackground(new java.awt.Color(255, 255, 255));

        jPanel335.setBackground(new java.awt.Color(255, 255, 255));
        jPanel334.add(jPanel335);

        jPanel332.add(jPanel334, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel336.setBackground(new java.awt.Color(255, 255, 255));

        jPanel337.setBackground(new java.awt.Color(255, 255, 255));
        jPanel336.add(jPanel337);

        jPanel338.setBackground(new java.awt.Color(255, 255, 255));

        jPanel339.setBackground(new java.awt.Color(255, 255, 255));
        jPanel338.add(jPanel339);

        jPanel336.add(jPanel338);

        jPanel332.add(jPanel336, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel324.add(jPanel332, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel308.add(jPanel324, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 520, 40));

        jPanel276.add(jPanel308, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 520, 40));

        jPanel340.setBackground(new java.awt.Color(255, 255, 255));
        jPanel340.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel341.setBackground(new java.awt.Color(255, 255, 255));
        jPanel340.add(jPanel341, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel342.setBackground(new java.awt.Color(255, 255, 255));

        jPanel343.setBackground(new java.awt.Color(255, 255, 255));
        jPanel342.add(jPanel343);

        jPanel340.add(jPanel342, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel344.setBackground(new java.awt.Color(255, 255, 255));

        jPanel345.setBackground(new java.awt.Color(255, 255, 255));
        jPanel344.add(jPanel345);

        jPanel346.setBackground(new java.awt.Color(255, 255, 255));

        jPanel347.setBackground(new java.awt.Color(255, 255, 255));
        jPanel346.add(jPanel347);

        jPanel344.add(jPanel346);

        jPanel340.add(jPanel344, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel348.setBackground(new java.awt.Color(255, 255, 255));
        jPanel348.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel349.setBackground(new java.awt.Color(255, 255, 255));
        jPanel348.add(jPanel349, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel350.setBackground(new java.awt.Color(255, 255, 255));

        jPanel351.setBackground(new java.awt.Color(255, 255, 255));
        jPanel350.add(jPanel351);

        jPanel348.add(jPanel350, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel352.setBackground(new java.awt.Color(255, 255, 255));

        jPanel353.setBackground(new java.awt.Color(255, 255, 255));
        jPanel352.add(jPanel353);

        jPanel354.setBackground(new java.awt.Color(255, 255, 255));

        jPanel355.setBackground(new java.awt.Color(255, 255, 255));
        jPanel354.add(jPanel355);

        jPanel352.add(jPanel354);

        jPanel348.add(jPanel352, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel340.add(jPanel348, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel356.setBackground(new java.awt.Color(255, 255, 255));
        jPanel356.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel357.setBackground(new java.awt.Color(255, 255, 255));
        jPanel356.add(jPanel357, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel358.setBackground(new java.awt.Color(255, 255, 255));

        jPanel359.setBackground(new java.awt.Color(255, 255, 255));
        jPanel358.add(jPanel359);

        jPanel356.add(jPanel358, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel360.setBackground(new java.awt.Color(255, 255, 255));

        jPanel361.setBackground(new java.awt.Color(255, 255, 255));
        jPanel360.add(jPanel361);

        jPanel362.setBackground(new java.awt.Color(255, 255, 255));

        jPanel363.setBackground(new java.awt.Color(255, 255, 255));
        jPanel362.add(jPanel363);

        jPanel360.add(jPanel362);

        jPanel356.add(jPanel360, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel364.setBackground(new java.awt.Color(255, 255, 255));
        jPanel364.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel365.setBackground(new java.awt.Color(255, 255, 255));
        jPanel364.add(jPanel365, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel366.setBackground(new java.awt.Color(255, 255, 255));

        jPanel367.setBackground(new java.awt.Color(255, 255, 255));
        jPanel366.add(jPanel367);

        jPanel364.add(jPanel366, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel368.setBackground(new java.awt.Color(255, 255, 255));

        jPanel369.setBackground(new java.awt.Color(255, 255, 255));
        jPanel368.add(jPanel369);

        jPanel370.setBackground(new java.awt.Color(255, 255, 255));

        jPanel371.setBackground(new java.awt.Color(255, 255, 255));
        jPanel370.add(jPanel371);

        jPanel368.add(jPanel370);

        jPanel364.add(jPanel368, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel356.add(jPanel364, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel340.add(jPanel356, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 520, 40));

        jPanel372.setBackground(new java.awt.Color(255, 255, 255));
        jPanel372.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel373.setBackground(new java.awt.Color(255, 255, 255));
        jPanel372.add(jPanel373, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel374.setBackground(new java.awt.Color(255, 255, 255));

        jPanel375.setBackground(new java.awt.Color(255, 255, 255));
        jPanel374.add(jPanel375);

        jPanel372.add(jPanel374, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel376.setBackground(new java.awt.Color(255, 255, 255));

        jPanel377.setBackground(new java.awt.Color(255, 255, 255));
        jPanel376.add(jPanel377);

        jPanel378.setBackground(new java.awt.Color(255, 255, 255));

        jPanel379.setBackground(new java.awt.Color(255, 255, 255));
        jPanel378.add(jPanel379);

        jPanel376.add(jPanel378);

        jPanel372.add(jPanel376, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel380.setBackground(new java.awt.Color(255, 255, 255));
        jPanel380.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel381.setBackground(new java.awt.Color(255, 255, 255));
        jPanel380.add(jPanel381, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel382.setBackground(new java.awt.Color(255, 255, 255));

        jPanel383.setBackground(new java.awt.Color(255, 255, 255));
        jPanel382.add(jPanel383);

        jPanel380.add(jPanel382, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel384.setBackground(new java.awt.Color(255, 255, 255));

        jPanel385.setBackground(new java.awt.Color(255, 255, 255));
        jPanel384.add(jPanel385);

        jPanel386.setBackground(new java.awt.Color(255, 255, 255));

        jPanel387.setBackground(new java.awt.Color(255, 255, 255));
        jPanel386.add(jPanel387);

        jPanel384.add(jPanel386);

        jPanel380.add(jPanel384, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel372.add(jPanel380, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel388.setBackground(new java.awt.Color(255, 255, 255));
        jPanel388.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel389.setBackground(new java.awt.Color(255, 255, 255));
        jPanel388.add(jPanel389, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel390.setBackground(new java.awt.Color(255, 255, 255));

        jPanel391.setBackground(new java.awt.Color(255, 255, 255));
        jPanel390.add(jPanel391);

        jPanel388.add(jPanel390, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel392.setBackground(new java.awt.Color(255, 255, 255));

        jPanel393.setBackground(new java.awt.Color(255, 255, 255));
        jPanel392.add(jPanel393);

        jPanel394.setBackground(new java.awt.Color(255, 255, 255));

        jPanel395.setBackground(new java.awt.Color(255, 255, 255));
        jPanel394.add(jPanel395);

        jPanel392.add(jPanel394);

        jPanel388.add(jPanel392, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel396.setBackground(new java.awt.Color(255, 255, 255));
        jPanel396.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel397.setBackground(new java.awt.Color(255, 255, 255));
        jPanel396.add(jPanel397, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 520, 40));

        jPanel398.setBackground(new java.awt.Color(255, 255, 255));

        jPanel399.setBackground(new java.awt.Color(255, 255, 255));
        jPanel398.add(jPanel399);

        jPanel396.add(jPanel398, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 40));

        jPanel400.setBackground(new java.awt.Color(255, 255, 255));

        jPanel401.setBackground(new java.awt.Color(255, 255, 255));
        jPanel400.add(jPanel401);

        jPanel402.setBackground(new java.awt.Color(255, 255, 255));

        jPanel403.setBackground(new java.awt.Color(255, 255, 255));
        jPanel402.add(jPanel403);

        jPanel400.add(jPanel402);

        jPanel396.add(jPanel400, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel388.add(jPanel396, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel372.add(jPanel388, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 520, 40));

        jPanel340.add(jPanel372, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 520, 40));

        jPanel276.add(jPanel340, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 520, 40));

        jPanel1.add(jPanel276, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 520, 40));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 640, 530));

        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Desktop\\ETHOM FILES\\NEW ICON\\exam2.jpg")); // NOI18N
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-140, -10, 1270, 690));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 1130, 700));

        setSize(new java.awt.Dimension(1144, 709));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Next Button
        answerCheck();
        question();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // code To select only one option
        if(jRadioButton4.isSelected()){

            jRadioButton2.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton1.setSelected(false);
        }
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // code To select only one option
        if(jRadioButton3.isSelected()){

            jRadioButton2.setSelected(false);
            jRadioButton1.setSelected(false);
            jRadioButton4.setSelected(false);
        }
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // code To select only one option
        if(jRadioButton2.isSelected()){

            jRadioButton1.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // code To select only one option
        if(jRadioButton1.isSelected()){

            jRadioButton2.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(QuizExam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuizExam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuizExam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuizExam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuizExam().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelDate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel100;
    private javax.swing.JPanel jPanel101;
    private javax.swing.JPanel jPanel102;
    private javax.swing.JPanel jPanel103;
    private javax.swing.JPanel jPanel104;
    private javax.swing.JPanel jPanel105;
    private javax.swing.JPanel jPanel106;
    private javax.swing.JPanel jPanel107;
    private javax.swing.JPanel jPanel108;
    private javax.swing.JPanel jPanel109;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel110;
    private javax.swing.JPanel jPanel111;
    private javax.swing.JPanel jPanel112;
    private javax.swing.JPanel jPanel113;
    private javax.swing.JPanel jPanel114;
    private javax.swing.JPanel jPanel115;
    private javax.swing.JPanel jPanel116;
    private javax.swing.JPanel jPanel117;
    private javax.swing.JPanel jPanel118;
    private javax.swing.JPanel jPanel119;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel120;
    private javax.swing.JPanel jPanel121;
    private javax.swing.JPanel jPanel122;
    private javax.swing.JPanel jPanel123;
    private javax.swing.JPanel jPanel124;
    private javax.swing.JPanel jPanel125;
    private javax.swing.JPanel jPanel126;
    private javax.swing.JPanel jPanel127;
    private javax.swing.JPanel jPanel128;
    private javax.swing.JPanel jPanel129;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel130;
    private javax.swing.JPanel jPanel131;
    private javax.swing.JPanel jPanel132;
    private javax.swing.JPanel jPanel133;
    private javax.swing.JPanel jPanel134;
    private javax.swing.JPanel jPanel135;
    private javax.swing.JPanel jPanel136;
    private javax.swing.JPanel jPanel137;
    private javax.swing.JPanel jPanel138;
    private javax.swing.JPanel jPanel139;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel140;
    private javax.swing.JPanel jPanel141;
    private javax.swing.JPanel jPanel142;
    private javax.swing.JPanel jPanel143;
    private javax.swing.JPanel jPanel144;
    private javax.swing.JPanel jPanel145;
    private javax.swing.JPanel jPanel146;
    private javax.swing.JPanel jPanel147;
    private javax.swing.JPanel jPanel148;
    private javax.swing.JPanel jPanel149;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel150;
    private javax.swing.JPanel jPanel151;
    private javax.swing.JPanel jPanel152;
    private javax.swing.JPanel jPanel153;
    private javax.swing.JPanel jPanel154;
    private javax.swing.JPanel jPanel155;
    private javax.swing.JPanel jPanel156;
    private javax.swing.JPanel jPanel157;
    private javax.swing.JPanel jPanel158;
    private javax.swing.JPanel jPanel159;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel160;
    private javax.swing.JPanel jPanel161;
    private javax.swing.JPanel jPanel162;
    private javax.swing.JPanel jPanel163;
    private javax.swing.JPanel jPanel164;
    private javax.swing.JPanel jPanel165;
    private javax.swing.JPanel jPanel166;
    private javax.swing.JPanel jPanel167;
    private javax.swing.JPanel jPanel168;
    private javax.swing.JPanel jPanel169;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel170;
    private javax.swing.JPanel jPanel171;
    private javax.swing.JPanel jPanel172;
    private javax.swing.JPanel jPanel173;
    private javax.swing.JPanel jPanel174;
    private javax.swing.JPanel jPanel175;
    private javax.swing.JPanel jPanel176;
    private javax.swing.JPanel jPanel177;
    private javax.swing.JPanel jPanel178;
    private javax.swing.JPanel jPanel179;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel180;
    private javax.swing.JPanel jPanel181;
    private javax.swing.JPanel jPanel182;
    private javax.swing.JPanel jPanel183;
    private javax.swing.JPanel jPanel184;
    private javax.swing.JPanel jPanel185;
    private javax.swing.JPanel jPanel186;
    private javax.swing.JPanel jPanel187;
    private javax.swing.JPanel jPanel188;
    private javax.swing.JPanel jPanel189;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel190;
    private javax.swing.JPanel jPanel191;
    private javax.swing.JPanel jPanel192;
    private javax.swing.JPanel jPanel193;
    private javax.swing.JPanel jPanel194;
    private javax.swing.JPanel jPanel195;
    private javax.swing.JPanel jPanel196;
    private javax.swing.JPanel jPanel197;
    private javax.swing.JPanel jPanel198;
    private javax.swing.JPanel jPanel199;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel200;
    private javax.swing.JPanel jPanel201;
    private javax.swing.JPanel jPanel202;
    private javax.swing.JPanel jPanel203;
    private javax.swing.JPanel jPanel204;
    private javax.swing.JPanel jPanel205;
    private javax.swing.JPanel jPanel206;
    private javax.swing.JPanel jPanel207;
    private javax.swing.JPanel jPanel208;
    private javax.swing.JPanel jPanel209;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel210;
    private javax.swing.JPanel jPanel211;
    private javax.swing.JPanel jPanel212;
    private javax.swing.JPanel jPanel213;
    private javax.swing.JPanel jPanel214;
    private javax.swing.JPanel jPanel215;
    private javax.swing.JPanel jPanel216;
    private javax.swing.JPanel jPanel217;
    private javax.swing.JPanel jPanel218;
    private javax.swing.JPanel jPanel219;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel220;
    private javax.swing.JPanel jPanel221;
    private javax.swing.JPanel jPanel222;
    private javax.swing.JPanel jPanel223;
    private javax.swing.JPanel jPanel224;
    private javax.swing.JPanel jPanel225;
    private javax.swing.JPanel jPanel226;
    private javax.swing.JPanel jPanel227;
    private javax.swing.JPanel jPanel228;
    private javax.swing.JPanel jPanel229;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel230;
    private javax.swing.JPanel jPanel231;
    private javax.swing.JPanel jPanel232;
    private javax.swing.JPanel jPanel233;
    private javax.swing.JPanel jPanel234;
    private javax.swing.JPanel jPanel235;
    private javax.swing.JPanel jPanel236;
    private javax.swing.JPanel jPanel237;
    private javax.swing.JPanel jPanel238;
    private javax.swing.JPanel jPanel239;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel240;
    private javax.swing.JPanel jPanel241;
    private javax.swing.JPanel jPanel242;
    private javax.swing.JPanel jPanel243;
    private javax.swing.JPanel jPanel244;
    private javax.swing.JPanel jPanel245;
    private javax.swing.JPanel jPanel246;
    private javax.swing.JPanel jPanel247;
    private javax.swing.JPanel jPanel248;
    private javax.swing.JPanel jPanel249;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel250;
    private javax.swing.JPanel jPanel251;
    private javax.swing.JPanel jPanel252;
    private javax.swing.JPanel jPanel253;
    private javax.swing.JPanel jPanel254;
    private javax.swing.JPanel jPanel255;
    private javax.swing.JPanel jPanel256;
    private javax.swing.JPanel jPanel257;
    private javax.swing.JPanel jPanel258;
    private javax.swing.JPanel jPanel259;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel260;
    private javax.swing.JPanel jPanel261;
    private javax.swing.JPanel jPanel262;
    private javax.swing.JPanel jPanel263;
    private javax.swing.JPanel jPanel264;
    private javax.swing.JPanel jPanel265;
    private javax.swing.JPanel jPanel266;
    private javax.swing.JPanel jPanel267;
    private javax.swing.JPanel jPanel268;
    private javax.swing.JPanel jPanel269;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel270;
    private javax.swing.JPanel jPanel271;
    private javax.swing.JPanel jPanel272;
    private javax.swing.JPanel jPanel273;
    private javax.swing.JPanel jPanel274;
    private javax.swing.JPanel jPanel275;
    private javax.swing.JPanel jPanel276;
    private javax.swing.JPanel jPanel277;
    private javax.swing.JPanel jPanel278;
    private javax.swing.JPanel jPanel279;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel280;
    private javax.swing.JPanel jPanel281;
    private javax.swing.JPanel jPanel282;
    private javax.swing.JPanel jPanel283;
    private javax.swing.JPanel jPanel284;
    private javax.swing.JPanel jPanel285;
    private javax.swing.JPanel jPanel286;
    private javax.swing.JPanel jPanel287;
    private javax.swing.JPanel jPanel288;
    private javax.swing.JPanel jPanel289;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel290;
    private javax.swing.JPanel jPanel291;
    private javax.swing.JPanel jPanel292;
    private javax.swing.JPanel jPanel293;
    private javax.swing.JPanel jPanel294;
    private javax.swing.JPanel jPanel295;
    private javax.swing.JPanel jPanel296;
    private javax.swing.JPanel jPanel297;
    private javax.swing.JPanel jPanel298;
    private javax.swing.JPanel jPanel299;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel300;
    private javax.swing.JPanel jPanel301;
    private javax.swing.JPanel jPanel302;
    private javax.swing.JPanel jPanel303;
    private javax.swing.JPanel jPanel304;
    private javax.swing.JPanel jPanel305;
    private javax.swing.JPanel jPanel306;
    private javax.swing.JPanel jPanel307;
    private javax.swing.JPanel jPanel308;
    private javax.swing.JPanel jPanel309;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel310;
    private javax.swing.JPanel jPanel311;
    private javax.swing.JPanel jPanel312;
    private javax.swing.JPanel jPanel313;
    private javax.swing.JPanel jPanel314;
    private javax.swing.JPanel jPanel315;
    private javax.swing.JPanel jPanel316;
    private javax.swing.JPanel jPanel317;
    private javax.swing.JPanel jPanel318;
    private javax.swing.JPanel jPanel319;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel320;
    private javax.swing.JPanel jPanel321;
    private javax.swing.JPanel jPanel322;
    private javax.swing.JPanel jPanel323;
    private javax.swing.JPanel jPanel324;
    private javax.swing.JPanel jPanel325;
    private javax.swing.JPanel jPanel326;
    private javax.swing.JPanel jPanel327;
    private javax.swing.JPanel jPanel328;
    private javax.swing.JPanel jPanel329;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel330;
    private javax.swing.JPanel jPanel331;
    private javax.swing.JPanel jPanel332;
    private javax.swing.JPanel jPanel333;
    private javax.swing.JPanel jPanel334;
    private javax.swing.JPanel jPanel335;
    private javax.swing.JPanel jPanel336;
    private javax.swing.JPanel jPanel337;
    private javax.swing.JPanel jPanel338;
    private javax.swing.JPanel jPanel339;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel340;
    private javax.swing.JPanel jPanel341;
    private javax.swing.JPanel jPanel342;
    private javax.swing.JPanel jPanel343;
    private javax.swing.JPanel jPanel344;
    private javax.swing.JPanel jPanel345;
    private javax.swing.JPanel jPanel346;
    private javax.swing.JPanel jPanel347;
    private javax.swing.JPanel jPanel348;
    private javax.swing.JPanel jPanel349;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel350;
    private javax.swing.JPanel jPanel351;
    private javax.swing.JPanel jPanel352;
    private javax.swing.JPanel jPanel353;
    private javax.swing.JPanel jPanel354;
    private javax.swing.JPanel jPanel355;
    private javax.swing.JPanel jPanel356;
    private javax.swing.JPanel jPanel357;
    private javax.swing.JPanel jPanel358;
    private javax.swing.JPanel jPanel359;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel360;
    private javax.swing.JPanel jPanel361;
    private javax.swing.JPanel jPanel362;
    private javax.swing.JPanel jPanel363;
    private javax.swing.JPanel jPanel364;
    private javax.swing.JPanel jPanel365;
    private javax.swing.JPanel jPanel366;
    private javax.swing.JPanel jPanel367;
    private javax.swing.JPanel jPanel368;
    private javax.swing.JPanel jPanel369;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel370;
    private javax.swing.JPanel jPanel371;
    private javax.swing.JPanel jPanel372;
    private javax.swing.JPanel jPanel373;
    private javax.swing.JPanel jPanel374;
    private javax.swing.JPanel jPanel375;
    private javax.swing.JPanel jPanel376;
    private javax.swing.JPanel jPanel377;
    private javax.swing.JPanel jPanel378;
    private javax.swing.JPanel jPanel379;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel380;
    private javax.swing.JPanel jPanel381;
    private javax.swing.JPanel jPanel382;
    private javax.swing.JPanel jPanel383;
    private javax.swing.JPanel jPanel384;
    private javax.swing.JPanel jPanel385;
    private javax.swing.JPanel jPanel386;
    private javax.swing.JPanel jPanel387;
    private javax.swing.JPanel jPanel388;
    private javax.swing.JPanel jPanel389;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel390;
    private javax.swing.JPanel jPanel391;
    private javax.swing.JPanel jPanel392;
    private javax.swing.JPanel jPanel393;
    private javax.swing.JPanel jPanel394;
    private javax.swing.JPanel jPanel395;
    private javax.swing.JPanel jPanel396;
    private javax.swing.JPanel jPanel397;
    private javax.swing.JPanel jPanel398;
    private javax.swing.JPanel jPanel399;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel400;
    private javax.swing.JPanel jPanel401;
    private javax.swing.JPanel jPanel402;
    private javax.swing.JPanel jPanel403;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel66;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel74;
    private javax.swing.JPanel jPanel75;
    private javax.swing.JPanel jPanel76;
    private javax.swing.JPanel jPanel77;
    private javax.swing.JPanel jPanel78;
    private javax.swing.JPanel jPanel79;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel80;
    private javax.swing.JPanel jPanel81;
    private javax.swing.JPanel jPanel82;
    private javax.swing.JPanel jPanel83;
    private javax.swing.JPanel jPanel84;
    private javax.swing.JPanel jPanel85;
    private javax.swing.JPanel jPanel86;
    private javax.swing.JPanel jPanel87;
    private javax.swing.JPanel jPanel88;
    private javax.swing.JPanel jPanel89;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel90;
    private javax.swing.JPanel jPanel91;
    private javax.swing.JPanel jPanel92;
    private javax.swing.JPanel jPanel93;
    private javax.swing.JPanel jPanel94;
    private javax.swing.JPanel jPanel95;
    private javax.swing.JPanel jPanel96;
    private javax.swing.JPanel jPanel97;
    private javax.swing.JPanel jPanel98;
    private javax.swing.JPanel jPanel99;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
