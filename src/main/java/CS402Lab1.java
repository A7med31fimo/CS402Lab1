import java.awt.Color;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.xml.bind.DatatypeConverter;
/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

/**
 * @author Fimo1
 */
public class CS402Lab1 extends javax.swing.JFrame {

    static String Type = "Caesar";
    /**
     * Creates new form CS402Lab1
     */
    String mode = "enc";
    static RSA rsa = new RSA(1024);
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    public CS402Lab1() {
        initComponents();
    }

    //Caesar", "Monoalphabetic", "Playfair", "Vigenere ", "Vernam", "Autokey", "One time pad ", "Rail Fence", "Hill", "Row Transposition", "DES", "Robin Miller Test", "RSA"
    public static String encrypt(String p, String key) {
        if (Type.equals("Caesar")) {
            return ahmed.enc_Ceasr(p, key);
        }
        else if (Type.equals("Monoalphabetic")){
            return ahmed.enc_Monoalphabetic(p, key);
        }else if (Type.equals("Playfair")){
            return ahmed.enc_Playfair(p, key);
        }else if (Type.equals("Vigenere")){
            return ahmed.enc_Vigenere(p, key);
        }else if (Type.equals("Autokey")){
            return ahmed.enc_Autokey(p, key);
        }else if (Type.equals("One time pad")){
            return ahmed.enc_OneTimePad(p, key);
        }else if (Type.equals("Rail Fence")){
            return ahmed.enc_RailFence(p, key);
        }else if (Type.equals("Hill")){
            return ahmed.enc_Hill(p, key);
        }else if (Type.equals("Row Transposition")){
            return ahmed.enc_RowTransposition(p, key);
        }else if (Type.equals("DES")){
            return ahmed.enc_DES(p, key);
        }else if(Type.equals("RSA")){
            return rsa.encrypt(p);
        }else if(Type.equals("Vernam")){
            return ahmed.enc_Vernam(p, key);
        }
        //key=133457799BBCDFF1//p=0123456789abcdef
        return "Not Choose Algorithm";
    }

    public static String decrypt(String c, String key) {
        if (Type.equals("Caesar")) {
            return ahmed.dec_Ceasr(c, key);
        }
        else if (Type.equals("Monoalphabetic")){
            return ahmed.dec_Monoalphabetic(c, key);
        }else if (Type.equals("Playfair")){
            return ahmed.dec_Playfair(c, key);
        }else if (Type.equals("Vigenere")){
            return ahmed.dec_Vigenere(c, key);
        }else if (Type.equals("Autokey")){
            return ahmed.dec_Autokey(c, key);
        }else if (Type.equals("One time pad")){
            return ahmed.dec_OneTimePad(c, key);
        }else if (Type.equals("Rail Fence")){
            return ahmed.dec_RailFence(c, key);
        }else if (Type.equals("Hill")){
            return ahmed.dec_Hill(c, key);
        }else if (Type.equals("Row Transposition")){
            return ahmed.dec_RowTransposition(c, key);
        }else if (Type.equals("DES")){
            return ahmed.dec_DES(c, key);
        }else if(Type.equals("RSA")){
            return rsa.decrypt(c);
        }else if(Type.equals("Vernam")){
            return ahmed.dec_end_Vernam(c, key);
        }
        return "Not Choose Algorithm";
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CS402Lab1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CS402Lab1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CS402Lab1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CS402Lab1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        final CS402Lab1 myForm = new CS402Lab1();
        ImageIcon img = new ImageIcon("Icon.png");
        myForm.setIconImage(img.getImage());
        myForm.getContentPane().setBackground(new Color(255, 255, 255));
        myForm.setLocationRelativeTo(null);
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                myForm.setVisible(true);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        String[] Ciphers = {"Caesar", "Monoalphabetic", "Playfair", "Vigenere ", "Vernam", "Autokey", "One time pad", "Rail Fence", "Hill", "Row Transposition", "DES", "Robin Miller Test", "RSA"};
        JComboBox comboBox = new JComboBox(Ciphers);
        comboBox.setBounds(430, 200, 140, 50);
        this.add(comboBox);
        comboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Type = (String) comboBox.getItemAt(comboBox.getSelectedIndex());
                System.out.println(Type);
            }
        });
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simple Cipher");
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 51));
        jLabel1.setText("Time");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 51));
        jLabel2.setText("Enter Text");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 51));
        jButton1.setText("Import from File");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 51, 51));
        jButton2.setText("Encrypt");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 51, 51));
        jButton3.setText("Decrypt");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 51));
        jLabel3.setText("Result");

        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 51, 51));
        jButton4.setText("Export to File");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 0, 0));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Generate and Plot");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 51));
        jLabel4.setText("Key");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(199, 199, 199)
                                                                .addComponent(jButton5))
                                                        .addComponent(jScrollPane2)
                                                        .addComponent(jScrollPane1))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(14, 14, 14)
                                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jButton4)
                                                                                .addGap(0, 12, Short.MAX_VALUE))
                                                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jButton1)))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap(29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jButton2)
                                .addGap(101, 101, 101)
                                .addComponent(jButton3)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jButton1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jButton2)
                                                        .addComponent(jButton3))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel3))
                                        .addComponent(jLabel4))
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(40, 40, 40)
                                                .addComponent(jButton4))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String p = jTextArea2.getText();
        String key = jTextField1.getText();

        long start = System.currentTimeMillis();

        String c = encrypt(p, key);

        long end = System.currentTimeMillis();

        jTextArea1.setText(c);
        jLabel5.setText((end - start) + " ms");

        mode = "enc";

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String c = jTextArea2.getText();
        String key = jTextField1.getText();

        long start = System.currentTimeMillis();

        String p = decrypt(c, key);

        long end = System.currentTimeMillis();

        jTextArea1.setText(p);
        jLabel5.setText((end - start) + " ms");

        mode = "dec";

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser jFileChooser1 = new JFileChooser();
            int choice = jFileChooser1.showOpenDialog(this);
            if (choice == JFileChooser.APPROVE_OPTION) {
                java.io.File file = jFileChooser1.getSelectedFile();
                java.util.Scanner scan = new java.util.Scanner(file);
                String s = "";


                while (scan.hasNext()) {
                    s += scan.nextLine() + "\n";
                }

                jTextArea2.setText(s.substring(0, s.length() - 1));

                scan.close();

            }


        } catch (Exception e) {
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {

            if (mode.equals("enc")) {
                FileWriter fileWriter = new FileWriter("EncRes.txt", false); //Set true for append mode
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.print(jTextArea1.getText());
                printWriter.close();

            } else {
                FileWriter fileWriter = new FileWriter("DecRes.txt", false); //Set true for append mode
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.print(jTextArea1.getText());
                printWriter.close();
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        XYSeries series = new XYSeries("Enc");
        XYSeries series2 = new XYSeries("DEc");
        for (int i = 1; i <= 120; i++) {
            int size = i * 120;
            String p = "";
            for (int j = 0; j < size; j++) {
                p += (char) (47 + (int) (Math.random() * 80));
            }
            long start1 = System.currentTimeMillis();
            String c = encrypt(p, "3");
            long end1 = System.currentTimeMillis();

            long timeenc = end1 - start1;
            series.add(size, timeenc);

            long start2 = System.currentTimeMillis();
            decrypt(c, "3");
            long end2 = System.currentTimeMillis();

            long timedec = end2 - start2;
            series2.add(size, timedec);


        }

        // Add the series to your data set
        XYSeriesCollection dataset = new XYSeriesCollection();

        dataset.addSeries(series);
        dataset.addSeries(series2);

        // Generate the graph
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Encryption", // Title
                "Size", // x-axis Label
                "Time", // y-axis Label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // add it to our application
        JFrame plotFrame = new JFrame("Plot");
        plotFrame.setContentPane(chartPanel);
        plotFrame.setSize(500, 500);
        plotFrame.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    // End of variables declaration//GEN-END:variables
}
