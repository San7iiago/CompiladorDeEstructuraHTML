package CompiladorHTML;

import java.awt.Color;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Ventana extends javax.swing.JFrame {

    NumeroLinea numeroLinea;
    private String ruta = "src\\CompiladorHTML\\fuente.txt";
    private String archivo = new File(ruta).getAbsolutePath();
    public DefaultTableModel limpiar;
    JFileChooser ventana = new JFileChooser();

    private void NewFile() {
        archivo = "";
        txtSource.setText("");
        txtSource.setText("");
        int a = limpiar.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            limpiar.removeRow(i);
        }
        archivo = new File(ruta).getAbsolutePath();

    }

    private void OpenFile() {
        try {
            FileReader lector = new FileReader(archivo);
            BufferedReader bufer = new BufferedReader(lector);
            String cad;
            txtSource.setText("");
            while ((cad = bufer.readLine()) != null) {
                txtSource.append(cad + "\n");
            }
            bufer.close();
        } catch (IOException err) {
            txtMessage.setForeground(Color.red);
            txtMessage.setText(err.getMessage());
        }

    }

    private void SaveFile() {
        try {
            FileWriter escritor = new FileWriter(archivo);
            BufferedWriter bufer = new BufferedWriter(escritor);
            escritor.write(txtSource.getText());
            txtMessage.setForeground(Color.blue);
            txtMessage.setText("File saved...");
            bufer.close();
            archivo = new File(ruta).getAbsolutePath();
        } catch (IOException err) {
            txtMessage.setForeground(Color.red);
            txtMessage.setText(err.getMessage());
        }
    }

    private void SaveAs() {
        int opcion = ventana.showSaveDialog(this);
        if (opcion == JFileChooser.APPROVE_OPTION) {

            archivo = ventana.getSelectedFile().getAbsolutePath();

            if (!new File(archivo).exists()) {
                SaveFile();
            } else {
                int seleccionar = JOptionPane.showConfirmDialog(null, "El archivo de texto ya existe.\n¿Desea sobreescribirlo?", "Alerta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (seleccionar == JOptionPane.YES_OPTION) {
                    SaveFile();
                } else {
                    SaveAs();
                }
            }
        }
    }

    private void Compiler() {
        this.SaveFile();
        try {
            Lexer lex = new Lexer(new FileReader(archivo));
            parser par = new parser(lex);
            par.parse();
            if (lex.errlex.isEmpty() && par.errsin.isEmpty()) {
                txtMessage.setForeground(Color.blue);
                txtMessage.setText("Valid compilation...");
                txtMessage.append(lex.ts.toString());

            } else {

                txtMessage.setForeground(Color.red);
                txtMessage.setText("Error in compilation...");
                txtMessage.append(lex.errlex);
                txtMessage.append(par.errsin);
                txtMessage.append(ruta);

            }

        } catch (Exception e) {
            txtMessage.setForeground(Color.red);
            txtMessage.setText("Error: " + e.getMessage());
            //System.err.println(e.getMessage());
        }
    }

    private void OpenFiles() {
        int selected = ventana.showOpenDialog(this);

        if (selected == JFileChooser.APPROVE_OPTION) {
            archivo = ventana.getSelectedFile().getAbsolutePath();
            try {
                FileReader lector = new FileReader(archivo);
                try (BufferedReader bufer = new BufferedReader(lector)) {
                    String cad;
                    txtSource.setText("");
                    while ((cad = bufer.readLine()) != null) {
                        txtSource.append(cad + "\n");
                    }
                }
            } catch (IOException err) {
                txtMessage.setForeground(Color.red);
                txtMessage.setText(err.getMessage());
            }
        }
    }

    public Ventana() {
        initComponents();
        this.setLocationRelativeTo(null);
        OpenFile();
        limpiar = (new DefaultTableModel(null, new String[]{"Descripcion", "Contenido"}));
        numeroLinea = new NumeroLinea(txtSource);
        jScrollPane1.setRowHeaderView(numeroLinea);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Dialogo = new javax.swing.JFileChooser();
        jSeparator6 = new javax.swing.JSeparator();
        jToolBar1 = new javax.swing.JToolBar();
        btnNew = new javax.swing.JButton();
        btnOpen = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnSaveAs = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btnCopy = new javax.swing.JButton();
        btnPaste = new javax.swing.JButton();
        btnCut = new javax.swing.JButton();
        btnSelectAll = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        btnCompiler = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        btnExit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtSource = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMessage = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        File = new javax.swing.JMenu();
        mnuNew = new javax.swing.JMenuItem();
        mnuOpen = new javax.swing.JMenuItem();
        mnuSave = new javax.swing.JMenuItem();
        mnuSaveAs = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnuExit = new javax.swing.JMenuItem();
        Edit = new javax.swing.JMenu();
        mnuPaste = new javax.swing.JMenuItem();
        mnuCopy = new javax.swing.JMenuItem();
        mnuCut = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        mnuSelectAll = new javax.swing.JMenuItem();
        Application = new javax.swing.JMenu();
        mnuCompiler = new javax.swing.JMenuItem();
        mnuAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventana de Compilación");
        setBackground(new java.awt.Color(0, 0, 0));
        setMinimumSize(new java.awt.Dimension(510, 600));
        setPreferredSize(new java.awt.Dimension(510, 600));
        setResizable(false);

        jToolBar1.setRollover(true);

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        btnNew.setText(" New ");
        btnNew.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnNew.setFocusable(false);
        btnNew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNew.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jToolBar1.add(btnNew);

        btnOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/open.png"))); // NOI18N
        btnOpen.setText(" Open ");
        btnOpen.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnOpen.setFocusable(false);
        btnOpen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOpen.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });
        jToolBar1.add(btnOpen);

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        btnSave.setText(" Save ");
        btnSave.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSave.setFocusable(false);
        btnSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSave);

        btnSaveAs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sa.png"))); // NOI18N
        btnSaveAs.setText(" Save As ");
        btnSaveAs.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSaveAs.setFocusable(false);
        btnSaveAs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSaveAs.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAsActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSaveAs);
        jToolBar1.add(jSeparator4);

        btnCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/copy.png"))); // NOI18N
        btnCopy.setText(" Copy ");
        btnCopy.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCopy.setFocusable(false);
        btnCopy.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCopy.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopyActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCopy);

        btnPaste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/paste.png"))); // NOI18N
        btnPaste.setText(" Paste ");
        btnPaste.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPaste.setFocusable(false);
        btnPaste.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPaste.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasteActionPerformed(evt);
            }
        });
        jToolBar1.add(btnPaste);

        btnCut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cut.png"))); // NOI18N
        btnCut.setText("  Cut  ");
        btnCut.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCut.setFocusable(false);
        btnCut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCut.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCutActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCut);

        btnSelectAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/selectAll.png"))); // NOI18N
        btnSelectAll.setText(" Select All ");
        btnSelectAll.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSelectAll.setFocusable(false);
        btnSelectAll.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSelectAll.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectAllActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSelectAll);
        jToolBar1.add(jSeparator5);

        btnCompiler.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iniciar.png"))); // NOI18N
        btnCompiler.setText(" Compiler ");
        btnCompiler.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCompiler.setFocusable(false);
        btnCompiler.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCompiler.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCompiler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilerActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCompiler);
        jToolBar1.add(jSeparator7);

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.png"))); // NOI18N
        btnExit.setText(" Exit ");
        btnExit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnExit.setFocusable(false);
        btnExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jToolBar1.add(btnExit);

        txtSource.setColumns(20);
        txtSource.setFont(new java.awt.Font("Monospaced", 3, 13)); // NOI18N
        txtSource.setRows(5);
        txtSource.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(txtSource);

        txtMessage.setEditable(false);
        txtMessage.setColumns(20);
        txtMessage.setFont(new java.awt.Font("Monospaced", 2, 13)); // NOI18N
        txtMessage.setRows(5);
        txtMessage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane2.setViewportView(txtMessage);

        jLabel1.setFont(new java.awt.Font("Tempus Sans ITC", 3, 11)); // NOI18N
        jLabel1.setText("Compilation Results:");

        File.setText("File");

        mnuNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        mnuNew.setText("New");
        mnuNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuNewActionPerformed(evt);
            }
        });
        File.add(mnuNew);

        mnuOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnuOpen.setText("Open");
        mnuOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuOpenActionPerformed(evt);
            }
        });
        File.add(mnuOpen);

        mnuSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        mnuSave.setText("Save");
        mnuSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSaveActionPerformed(evt);
            }
        });
        File.add(mnuSave);

        mnuSaveAs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        mnuSaveAs.setText("Save As...");
        mnuSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSaveAsActionPerformed(evt);
            }
        });
        File.add(mnuSaveAs);
        File.add(jSeparator1);

        mnuExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        mnuExit.setText("Exit");
        mnuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuExitActionPerformed(evt);
            }
        });
        File.add(mnuExit);

        jMenuBar1.add(File);

        Edit.setText("Edit");

        mnuPaste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        mnuPaste.setText("Paste");
        mnuPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPasteActionPerformed(evt);
            }
        });
        Edit.add(mnuPaste);

        mnuCopy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        mnuCopy.setText("Copy");
        mnuCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCopyActionPerformed(evt);
            }
        });
        Edit.add(mnuCopy);

        mnuCut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        mnuCut.setText("Cut");
        mnuCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCutActionPerformed(evt);
            }
        });
        Edit.add(mnuCut);
        Edit.add(jSeparator2);
        Edit.add(jSeparator3);

        mnuSelectAll.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        mnuSelectAll.setText("Select All");
        mnuSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSelectAllActionPerformed(evt);
            }
        });
        Edit.add(mnuSelectAll);

        jMenuBar1.add(Edit);

        Application.setText("Application");

        mnuCompiler.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        mnuCompiler.setText("Compiler");
        mnuCompiler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCompilerActionPerformed(evt);
            }
        });
        Application.add(mnuCompiler);

        mnuAbout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        mnuAbout.setText("About");
        mnuAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAboutActionPerformed(evt);
            }
        });
        Application.add(mnuAbout);

        jMenuBar1.add(Application);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mnuExitActionPerformed

    private void mnuSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSaveActionPerformed
        SaveFile();
    }//GEN-LAST:event_mnuSaveActionPerformed

    private void mnuOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuOpenActionPerformed
        OpenFiles();
    }//GEN-LAST:event_mnuOpenActionPerformed

    private void mnuCompilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCompilerActionPerformed
        SaveFile();
        Compiler();
    }//GEN-LAST:event_mnuCompilerActionPerformed

    private void mnuAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAboutActionPerformed
        JOptionPane.showMessageDialog(this, "Desarrollado por: \n Santiago Narvaez");
    }//GEN-LAST:event_mnuAboutActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        NewFile();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        SaveFile();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCompilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilerActionPerformed
        SaveFile();
        Compiler();
    }//GEN-LAST:event_btnCompilerActionPerformed

    private void mnuNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuNewActionPerformed
        NewFile();
    }//GEN-LAST:event_mnuNewActionPerformed

    private void mnuPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPasteActionPerformed
        txtSource.paste();
    }//GEN-LAST:event_mnuPasteActionPerformed

    private void mnuCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCopyActionPerformed
        txtSource.copy();
    }//GEN-LAST:event_mnuCopyActionPerformed

    private void mnuCutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCutActionPerformed
        txtSource.cut();
    }//GEN-LAST:event_mnuCutActionPerformed

    private void mnuSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSaveAsActionPerformed
        SaveAs();
    }//GEN-LAST:event_mnuSaveAsActionPerformed

    private void mnuSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSelectAllActionPerformed
        txtSource.selectAll();
    }//GEN-LAST:event_mnuSelectAllActionPerformed

    private void btnSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAsActionPerformed
        SaveAs();
    }//GEN-LAST:event_btnSaveAsActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        OpenFiles();
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopyActionPerformed
        txtSource.copy();
    }//GEN-LAST:event_btnCopyActionPerformed

    private void btnPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasteActionPerformed
        txtSource.paste();
    }//GEN-LAST:event_btnPasteActionPerformed

    private void btnCutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCutActionPerformed
        txtSource.cut();
    }//GEN-LAST:event_btnCutActionPerformed

    private void btnSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectAllActionPerformed
        txtSource.selectAll();
    }//GEN-LAST:event_btnSelectAllActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

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
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Application;
    private javax.swing.JFileChooser Dialogo;
    private javax.swing.JMenu Edit;
    private javax.swing.JMenu File;
    private javax.swing.JButton btnCompiler;
    private javax.swing.JButton btnCopy;
    private javax.swing.JButton btnCut;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnPaste;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSaveAs;
    private javax.swing.JButton btnSelectAll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem mnuAbout;
    private javax.swing.JMenuItem mnuCompiler;
    private javax.swing.JMenuItem mnuCopy;
    private javax.swing.JMenuItem mnuCut;
    private javax.swing.JMenuItem mnuExit;
    private javax.swing.JMenuItem mnuNew;
    private javax.swing.JMenuItem mnuOpen;
    private javax.swing.JMenuItem mnuPaste;
    private javax.swing.JMenuItem mnuSave;
    private javax.swing.JMenuItem mnuSaveAs;
    private javax.swing.JMenuItem mnuSelectAll;
    private javax.swing.JTextArea txtMessage;
    private javax.swing.JTextArea txtSource;
    // End of variables declaration//GEN-END:variables
}
