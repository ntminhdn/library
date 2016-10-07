/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import data.ConnectDatabase;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import data.SearchList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import library.ChangePassword;
import library.Login;
import object.Search;

/**
 *
 * @author Administrator
 */
public class SearchGUI extends javax.swing.JFrame {

    private SearchList list = new SearchList();
    DefaultTableModel dm = new DefaultTableModel();
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    ConnectDatabase con = new ConnectDatabase();

    //private CategoryList clist = new CategoryList();
    private String[] tenCot = {
        "Book ID", "Book Name", "Author Name", "Publisher Name", "Category Name", "Price", "Quantity"
    };
    private String sqlSearch = "Select bookid,bookname,authorname,publishername,categoryname,price,quantity"
            + " from book b join category c on b.CategoryID = c.CategoryID "
            + " join author a on b.AuthorID = a.AuthorID"
            + " join publisher s on b.PublisherID = s.PublisherID ";

    /**
     * Creates new form BookSearchGUI
     */
    public SearchGUI() {
        super("Find Book");
        initComponents();
        this.tbResult.setModel(dm);
        dm.setColumnIdentifiers(tenCot);
//        this.cbChoice.addItem("--Choices--");
        this.cbChoice.addItem("BookName");
        this.cbChoice.addItem("AuthorName");
        this.cbChoice.addItem("PublisherName");
        this.cbChoice.addItem("CategoryName");
        this.list.load(sqlSearch);
        for (Search s : list.getList()) {
            this.dm.addRow(s.toVector());
        }
        AutoSuggestor autoSuggestor = new AutoSuggestor(tfSearch, this, null, Color.WHITE.brighter(), Color.BLUE, Color.RED, 0.75f) {
            @Override
            boolean wordTyped(String typedWord) {
                String sql0 = "Select bookname,authorname,publishername,categoryname"
                        + " from book b join category c on b.CategoryID = c.CategoryID "
                        + " join author a on b.AuthorID = a.AuthorID"
                        + " join publisher s on b.PublisherID = s.PublisherID";
                //create list for dictionary this in your case might be done via calling a method which queries db and returns results as arraylist
//                ArrayList<String> words = new ArrayList<String>();
                ArrayList<String> BookNameList = new ArrayList<String>();
                ArrayList<String> AuthorNameList = new ArrayList<String>();
                ArrayList<String> PublisherNameList = new ArrayList<String>();
                ArrayList<String> CategoryNameList = new ArrayList<String>();
                BookNameList = con.loadBookName("select BookName from book","BookName");
                for (String string : BookNameList) {
                    System.out.println(string);
                }
                AuthorNameList = con.loadBookName("select AuthorName from author","AuthorName");
                for (String string : AuthorNameList) {
                    System.out.println(string);
                }
                PublisherNameList = con.loadBookName("select PublisherName from publisher","PublisherName");
                for (String string : PublisherNameList) {
                    System.out.println(string);
                }
                CategoryNameList = con.loadBookName("select CategoryName from category","CategoryName");
                for (String string : CategoryNameList) {
                    System.out.println(string);
                }
                list.load(sql0);
                if(cbChoice.getSelectedItem().toString().equals("BookName")){
                    setDictionary(BookNameList);
                } else if(cbChoice.getSelectedItem().toString().equals("AuthorName")){
                    setDictionary(AuthorNameList);
                } else if(cbChoice.getSelectedItem().toString().equals("PublisherName")){
                    setDictionary(PublisherNameList);
                } else if (cbChoice.getSelectedItem().toString().equals("CategoryName")){
                    setDictionary(CategoryNameList);
                }
                
                //addToDictionary("bye");//adds a single word

                return super.wordTyped(typedWord);//now call super to check for any matches against newest dictionary
            }
        };
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbInput = new javax.swing.JLabel();
        cbChoice = new javax.swing.JComboBox<>();
        btCancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbResult = new javax.swing.JTable();
        tfSearch = new javax.swing.JTextField();
        btSearch = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(0, 600));

        lbInput.setText("Input:");

        btCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Redo-16.png"))); // NOI18N
        btCancel.setText("Cancel");
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelActionPerformed(evt);
            }
        });

        tbResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbResult);

        btSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_search.png"))); // NOI18N
        btSearch.setText("Search");
        btSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSearchActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/key.png"))); // NOI18N
        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("WELCOME TO MAQ LIBRARY");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lbInput, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbChoice, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbChoice)
                    .addComponent(tfSearch)
                    .addComponent(lbInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btCancelActionPerformed

    private void btSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSearchActionPerformed
        // TODO add your handling code here:
        int c = this.cbChoice.getSelectedIndex() - 1;
        this.list.getList().clear();
        this.dm.getDataVector().clear();
        String sql = sqlSearch;
        String choice = this.cbChoice.getSelectedItem().toString();
        if (c >= 0) {
            sql = sqlSearch + " where " + choice + " like N'" + this.tfSearch.getText() + "%'";
        }
        this.list.load(sql);
        for (Search s : list.getList()) {
            this.dm.addRow(s.toVector());
        }
        if (this.dm.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Not found!!");
        }
    }//GEN-LAST:event_btSearchActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new Login().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(SearchGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new SearchGUI().setVisible(true);
            }
        });
    }

    class AutoSuggestor {

        private final JTextField textField;
        private final Window container;
        private JPanel suggestionsPanel;
        private JWindow autoSuggestionPopUpWindow;
        private String typedWord;
        private final ArrayList<String> dictionary = new ArrayList<>();
        private int currentIndexOfSpace, tW, tH;
        private DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                checkForAndShowSuggestions();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                checkForAndShowSuggestions();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                checkForAndShowSuggestions();
            }
        };
        private final Color suggestionsTextColor;
        private final Color suggestionFocusedColor;

        public AutoSuggestor(JTextField textField, Window mainWindow, ArrayList<String> words, Color popUpBackground, Color textColor, Color suggestionFocusedColor, float opacity) {
            this.textField = textField;
            this.suggestionsTextColor = textColor;
            this.container = mainWindow;
            this.suggestionFocusedColor = suggestionFocusedColor;
            this.textField.getDocument().addDocumentListener(documentListener);

            setDictionary(words);

            typedWord = "";
            currentIndexOfSpace = 0;
            tW = 0;
            tH = 0;

            autoSuggestionPopUpWindow = new JWindow(mainWindow);
            autoSuggestionPopUpWindow.setOpacity(opacity);

            suggestionsPanel = new JPanel();
            suggestionsPanel.setLayout(new GridLayout(0, 1));
            suggestionsPanel.setBackground(popUpBackground);

            addKeyBindingToRequestFocusInPopUpWindow();
        }

        private void addKeyBindingToRequestFocusInPopUpWindow() {
            textField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "Down released");
            textField.getActionMap().put("Down released", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent ae) {//focuses the first label on popwindow
                    for (int i = 0; i < suggestionsPanel.getComponentCount(); i++) {
                        if (suggestionsPanel.getComponent(i) instanceof SuggestionLabel) {
                            ((SuggestionLabel) suggestionsPanel.getComponent(i)).setFocused(true);
                            autoSuggestionPopUpWindow.toFront();
                            autoSuggestionPopUpWindow.requestFocusInWindow();
                            suggestionsPanel.requestFocusInWindow();
                            suggestionsPanel.getComponent(i).requestFocusInWindow();
                            break;
                        }
                    }
                }
            });
            suggestionsPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "Down released");
            suggestionsPanel.getActionMap().put("Down released", new AbstractAction() {
                int lastFocusableIndex = 0;

                @Override
                public void actionPerformed(ActionEvent ae) {//allows scrolling of labels in pop window (I know very hacky for now :))

                    ArrayList<SuggestionLabel> sls = getAddedSuggestionLabels();
                    int max = sls.size();

                    if (max > 1) {//more than 1 suggestion
                        for (int i = 0; i < max; i++) {
                            SuggestionLabel sl = sls.get(i);
                            if (sl.isFocused()) {
                                if (lastFocusableIndex == max - 1) {
                                    lastFocusableIndex = 0;
                                    sl.setFocused(false);
                                    autoSuggestionPopUpWindow.setVisible(false);
                                    setFocusToTextField();
                                    checkForAndShowSuggestions();//fire method as if document listener change occured and fired it

                                } else {
                                    sl.setFocused(false);
                                    lastFocusableIndex = i;
                                }
                            } else if (lastFocusableIndex <= i) {
                                if (i < max) {
                                    sl.setFocused(true);
                                    autoSuggestionPopUpWindow.toFront();
                                    autoSuggestionPopUpWindow.requestFocusInWindow();
                                    suggestionsPanel.requestFocusInWindow();
                                    suggestionsPanel.getComponent(i).requestFocusInWindow();
                                    lastFocusableIndex = i;
                                    break;
                                }
                            }
                        }
                    } else {//only a single suggestion was given
                        autoSuggestionPopUpWindow.setVisible(false);
                        setFocusToTextField();
                        checkForAndShowSuggestions();//fire method as if document listener change occured and fired it
                    }
                }
            });
        }

        private void setFocusToTextField() {
            container.toFront();
            container.requestFocusInWindow();
            textField.requestFocusInWindow();
        }

        public ArrayList<SuggestionLabel> getAddedSuggestionLabels() {
            ArrayList<SuggestionLabel> sls = new ArrayList<>();
            for (int i = 0; i < suggestionsPanel.getComponentCount(); i++) {
                if (suggestionsPanel.getComponent(i) instanceof SuggestionLabel) {
                    SuggestionLabel sl = (SuggestionLabel) suggestionsPanel.getComponent(i);
                    sls.add(sl);
                }
            }
            return sls;
        }

        private void checkForAndShowSuggestions() {
            typedWord = getCurrentlyTypedWord();

            suggestionsPanel.removeAll();//remove previos words/jlabels that were added

            //used to calcualte size of JWindow as new Jlabels are added
            tW = 0;
            tH = 0;

            boolean added = wordTyped(typedWord);

            if (!added) {
                if (autoSuggestionPopUpWindow.isVisible()) {
                    autoSuggestionPopUpWindow.setVisible(false);
                }
            } else {
                showPopUpWindow();
                setFocusToTextField();
            }
        }

        protected void addWordToSuggestions(String word) {
            SuggestionLabel suggestionLabel = new SuggestionLabel(word, suggestionFocusedColor, suggestionsTextColor, this);

            calculatePopUpWindowSize(suggestionLabel);

            suggestionsPanel.add(suggestionLabel);
        }

        public String getCurrentlyTypedWord() {//get newest word after last white spaceif any or the first word if no white spaces
            String text = textField.getText();
            String wordBeingTyped = "";
            if (text.contains(" ")) {
                int tmp = text.lastIndexOf(" ");
                if (tmp >= currentIndexOfSpace) {
                    currentIndexOfSpace = tmp;
                    wordBeingTyped = text.substring(text.lastIndexOf(" "));
                }
            } else {
                wordBeingTyped = text;
            }
            return wordBeingTyped.trim();
        }

        private void calculatePopUpWindowSize(JLabel label) {
            //so we can size the JWindow correctly
            if (tW < label.getPreferredSize().width) {
                tW = label.getPreferredSize().width;
            }
            tH += label.getPreferredSize().height;
        }

        private void showPopUpWindow() {
            autoSuggestionPopUpWindow.getContentPane().add(suggestionsPanel);
            autoSuggestionPopUpWindow.setMinimumSize(new Dimension(textField.getWidth(), 30));
            autoSuggestionPopUpWindow.setSize(tW, tH);
            autoSuggestionPopUpWindow.setVisible(true);

            int windowX = 0;
            int windowY = 0;

            windowX = container.getX() + textField.getX() + 5;
            if (suggestionsPanel.getHeight() > autoSuggestionPopUpWindow.getMinimumSize().height) {
                windowY = container.getY() + textField.getY() + textField.getHeight() + autoSuggestionPopUpWindow.getMinimumSize().height;
            } else {
                windowY = container.getY() + textField.getY() + textField.getHeight() + autoSuggestionPopUpWindow.getHeight();
            }

            autoSuggestionPopUpWindow.setLocation(windowX, windowY);
            autoSuggestionPopUpWindow.setMinimumSize(new Dimension(textField.getWidth(), 30));
            autoSuggestionPopUpWindow.revalidate();
            autoSuggestionPopUpWindow.repaint();

        }

        public void setDictionary(ArrayList<String> words) {
            dictionary.clear();
            if (words == null) {
                return;//so we can call constructor with null value for dictionary without exception thrown
            }
            for (String word : words) {
                dictionary.add(word);
            }
        }

        public JWindow getAutoSuggestionPopUpWindow() {
            return autoSuggestionPopUpWindow;
        }

        public Window getContainer() {
            return container;
        }

        public JTextField getTextField() {
            return textField;
        }

        public void addToDictionary(String word) {
            dictionary.add(word);
        }

        boolean wordTyped(String typedWord) {

            if (typedWord.isEmpty()) {
                return false;
            }
            //System.out.println("Typed word: " + typedWord);

            boolean suggestionAdded = false;

            for (String word : dictionary) {//get words in the dictionary which we added
                boolean fullymatches = true;
                for (int i = 0; i < typedWord.length(); i++) {//each string in the word
                    if (!typedWord.toLowerCase().startsWith(String.valueOf(word.toLowerCase().charAt(i)), i)) {//check for match
                        fullymatches = false;
                        break;
                    }
                }
                if (fullymatches) {
                    addWordToSuggestions(word);
                    suggestionAdded = true;
                }
            }
            return suggestionAdded;
        }
    }

    class SuggestionLabel extends JLabel {

        private boolean focused = false;
        private final JWindow autoSuggestionsPopUpWindow;
        private final JTextField textField;
        private final AutoSuggestor autoSuggestor;
        private Color suggestionsTextColor, suggestionBorderColor;

        public SuggestionLabel(String string, final Color borderColor, Color suggestionsTextColor, AutoSuggestor autoSuggestor) {
            super(string);

            this.suggestionsTextColor = suggestionsTextColor;
            this.autoSuggestor = autoSuggestor;
            this.textField = autoSuggestor.getTextField();
            this.suggestionBorderColor = borderColor;
            this.autoSuggestionsPopUpWindow = autoSuggestor.getAutoSuggestionPopUpWindow();

            initComponent();
        }

        private void initComponent() {
            setFocusable(true);
            setForeground(suggestionsTextColor);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    super.mouseClicked(me);

                    replaceWithSuggestedText();

                    autoSuggestionsPopUpWindow.setVisible(false);
                }
            });

            getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "Enter released");
            getActionMap().put("Enter released", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    replaceWithSuggestedText();
                    autoSuggestionsPopUpWindow.setVisible(false);
                }
            });
        }

        public void setFocused(boolean focused) {
            if (focused) {
                setBorder(new LineBorder(suggestionBorderColor));
            } else {
                setBorder(null);
            }
            repaint();
            this.focused = focused;
        }

        public boolean isFocused() {
            return focused;
        }

        private void replaceWithSuggestedText() {
            String suggestedWord = getText();
            String text = textField.getText();
            String typedWord = autoSuggestor.getCurrentlyTypedWord();
            String t = text.substring(0, text.lastIndexOf(typedWord));
            String tmp = t + text.substring(text.lastIndexOf(typedWord)).replace(typedWord, suggestedWord);
            textField.setText(tmp + "");
        }

        

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btCancel;
    javax.swing.JButton btSearch;
    javax.swing.JComboBox<String> cbChoice;
    javax.swing.JButton jButton1;
    javax.swing.JLabel jLabel1;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JLabel lbInput;
    javax.swing.JTable tbResult;
    javax.swing.JTextField tfSearch;
    // End of variables declaration//GEN-END:variables
}
