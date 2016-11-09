/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import object.BorrowingManagement;
import data.BorrowingManagementData;
import data.ReaderData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import object.Next10Day;
import object.NextYear;
import object.Reader;
import data.BookData;
import object.Book;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import data.AuthorData;
import object.Author;
import object.Category;
import data.CategoryData;
import data.DataAccess;
import data.PublisherData;
import data.ReturnManagementData;
import data.SupplierData;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import object.Publisher;
import object.Supplier;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import net.proteanit.sql.DbUtils;
import object.ButtonRenderer;
import object.ReturnManagement;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import data.DataAccess;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Locale;
import java.util.Random;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ProgressBar;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import library.SendingEmail.Task;
import object.Gmail;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 *
 * @author PhiLong
 */
public class Form_Library extends javax.swing.JFrame {

    /**
     * Creates new form Form_Library
     */
    JFileChooser jChooserExcel = new JFileChooser();
    JPopupMenu jPopupMenuBook = new javax.swing.JPopupMenu();
    JMenuItem jMenuItemBook = new javax.swing.JMenuItem();
    JPopupMenu jPopupMenuAuthor = new javax.swing.JPopupMenu();
    JMenuItem jMenuItemAuthor = new javax.swing.JMenuItem();
    JPopupMenu jPopupMenuPub = new javax.swing.JPopupMenu();
    JMenuItem jMenuItemPub = new javax.swing.JMenuItem();
    JPopupMenu jPopupMenuSup = new javax.swing.JPopupMenu();
    JMenuItem jMenuItemSup = new javax.swing.JMenuItem();
    JPopupMenu jPopupMenuCate = new javax.swing.JPopupMenu();
    JMenuItem jMenuItemCate = new javax.swing.JMenuItem();
    JPopupMenu jPopupMenuBo = new javax.swing.JPopupMenu();
    JMenuItem jMenuItemBo = new javax.swing.JMenuItem();
//    JPopupMenu jPopupMenuRe = new javax.swing.JPopupMenu();
//    JMenuItem jMenuItemRe = new javax.swing.JMenuItem();
    JPopupMenu jPopupMenuReader = new javax.swing.JPopupMenu();
    JMenuItem jMenuItemReader = new javax.swing.JMenuItem();
    private String sql = "SELECT * FROM borrowingmanagement where ( ReturnDate < CURDATE())";
    public DataAccess da = new DataAccess();
    private CategoryData CategoryList = new CategoryData();
    private AuthorData AuthorList = new AuthorData();
    private BookData BookList = new BookData();
    private ReaderData ReaderList = new ReaderData();
    private CategoryData clist = new CategoryData();
    public BorrowingManagementData BorrowingList = new BorrowingManagementData();
    private PublisherData PublisherList = new PublisherData();
    private SupplierData SupplierList = new SupplierData();
    private ReturnManagementData ReturnList = new ReturnManagementData();
    private String status = "";
    DefaultTableModel dmReader = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int col) {
            if (col == 10) {
                return true;
            } else {
                return false;
            }
        }
    };

    DefaultTableModel dmBorrowing = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int col) {
            if (col == 5) {
                return true;
            } else {
                return false;
            }
        }
    };
    DefaultTableModel dmBook = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    };
    DefaultTableModel dmAuthor = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    };
    DefaultTableModel dmCategory = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    };
    DefaultTableModel dmSupplier = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    };
    DefaultTableModel dmPublisher = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    };
    DefaultTableModel dmReturn = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    };

    private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    private DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
    Next10Day nd = new Next10Day();
    Date nextday = nd.getNext10Day();
    Date today2 = nd.getToday();

    NextYear ny = new NextYear();
    Date nextyear = ny.getNextYearDay();
    Date today = ny.getToday();

    private String[] tenCotBorrowing = {
        "Borrow ID", "Reader ID", "Book ID", "Borrow Date", "Return Date", "Add 10 Days"
    };
    private String[] tenCotReader = {
        "ID", "Name", "ID Card Number", "Sex", "Birthday", "Address", "Phone", "Email", "Activation Date", "Expired Date", "Add 1 Year"
    };
    private String[] tenCotBook = {
        "Book ID", "Book Name", "Author ID", "Publisher ID", "Supplier ID", "CategoryID", "Price", "Quantity", "Shelf", "Row_No.", "Column_No.", "Image",};
    private String[] tenCotAuthor = {"Author ID", "Author Name"};
    private String[] tenCotCategory = {
        "ID", "Name"
    };
    private String[] tenCotPublisher = {
        "ID", "Name"
    };
    private String[] tenCotSupplier = {
        "ID", "Name", "Address", "Phone"
    };
    private String[] tenCotReturn = {
        "Borrow ID", "Book ID", "Reader Name", "Book Name", "Author Name", "Publisher Name", "Price", "Borrow Date", "Return Date", "Overdue Days", "Penalty"
    };

    public Form_Library() {
        super("Library Management");
        initComponents();
        setSize(1100, 700);
        jPanel1.setSize(900, 600);
        jPanel2.setSize(900, 600);
        jPanel3.setSize(900, 600);
        jPanel4.setSize(900, 600);
        jPanel5.setSize(900, 600);
        jPanel6.setSize(900, 600);

//        BufferedImage img = null;
//        try {
//            img = ImageIO.read(new File("D:\\CODE\\00. SOFTTECH\\9. JAVA 1\\Project\\Library_Minh\\src\\icon\\BG.jpg"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(jLabel20.getWidth(), jLabel20.getHeight(), Image.SCALE_SMOOTH));
//        jLabel20.setIcon(imageIcon);
        AutoSuggestor autoSuggestor = new Form_Library.AutoSuggestor(tfSearchBook, this, null, Color.BLACK.brighter(), Color.WHITE, Color.RED, 1.00f) {
            @Override
            boolean wordTyped(String typedWord) {
                //create list for dictionary this in your case might be done via calling a method which queries db and returns results as arraylist
                ArrayList<String> BookNameList = new ArrayList<String>();
                BookNameList = da.loadBookName("select BookName from book", "BookName");
                setDictionary(BookNameList);
                return super.wordTyped(typedWord);//now call super to check for any matches against newest dictionary
            }
        };
        da.LoadData(sql, tbPhieuQuaHan);
        try {
            String sql1 = "SELECT SUM(Quantity) as BookSum from book";
            String sql2 = "SELECT COUNT(RdID) as ReaderSum FROM reader";
            String sql3 = "SELECT COUNT(BorrowID) as BorrowSum FROM borrowingmanagement";
            String sql4 = "SELECT COUNT(DISTINCT RdID) as ReaderBorrowSum FROM borrowingmanagement";
            String sql5 = "SELECT COUNT(BorrowID) as DatedBorrowSum FROM borrowingmanagement "
                    + "where (ReturnDate < CURDATE())";
            ResultSet rs1 = da.getData(sql1);
            ResultSet rs2 = da.getData(sql2);
            ResultSet rs3 = da.getData(sql3);
            ResultSet rs4 = da.getData(sql4);
            ResultSet rs5 = da.getData(sql5);
            if (rs1.next()) {
                this.lbTongSach.setText("Total books : " + Integer.toString(rs1.getInt("BookSum")));
            }
            if (rs2.next()) {
                this.lbTongKhach.setText("Total Readers: " + Integer.toString(rs2.getInt("ReaderSum")));
            }
            if (rs3.next()) {
                this.lbTongPhieu.setText("Total Borrowings: " + Integer.toString(rs3.getInt("BorrowSum")));
            }
            if (rs4.next()) {
                this.lbTongKhachMuon.setText("Total Readers who borrowing books: " + Integer.toString(rs4.getInt("ReaderBorrowSum")));
            }
            if (rs5.next()) {
                this.lbTongPhieuQuaHan.setText("Total overdue Borrowings: " + Integer.toString(rs5.getInt("DatedBorrowSum")));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        ResultSet rs = null;
        rs = da.getData(sql);
        List l;
        l = DbUtils.resultSetToNestedList(rs);
        this.taBaoCao.append(this.lbTongSach.getText() + "\n");
        this.taBaoCao.append(this.lbTongKhach.getText() + "\n");
        this.taBaoCao.append(this.lbTongPhieu.getText() + "\n");
        this.taBaoCao.append(this.lbTongKhachMuon.getText() + "\n");
        this.taBaoCao.append(this.lbTongPhieuQuaHan.getText() + "\n\n");
        this.taBaoCao.append("|  BorrowID  |  RdID  |  BookID  |  BorrowDate  |  ReturnDate  |\n");
        for (int i = 0; i < l.size(); i++) {
            this.taBaoCao.append(l.get(i).toString() + "\n");
        }

        //Reader
        this.tbReader.setModel(dmReader);
        dmReader.setColumnIdentifiers(tenCotReader);
        ReaderList.load("Select * from reader");
        for (Reader r : ReaderList.getList()) {
            dmReader.addRow(r.toVector());
        }

        //Borrowing Management
        this.tbBorrowingManagement.setModel(dmBorrowing);
        dmBorrowing.setColumnIdentifiers(tenCotBorrowing);

        //Gia hạn thẻ đọc
        tbReader.getColumn("Add 1 Year").setCellRenderer(new ButtonRenderer());
        ButtonEditor be = new ButtonEditor(new JCheckBox());
        tbReader.getColumn("Add 1 Year").setCellEditor(be);
        be.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int donghh = tbReader.getSelectedRow();
                String RdIDhh = tbReader.getValueAt(donghh, 0).toString();
                String RdNamehh = tbReader.getValueAt(donghh, 1).toString();
                String IDcardhh = tbReader.getValueAt(donghh, 2).toString();
                String Sexhh = tbReader.getValueAt(donghh, 3).toString();
                Date Birthdayhh = carBirthday.getDate();
                String Addresshh = tbReader.getValueAt(donghh, 5).toString();
                String Phonehh = tbReader.getValueAt(donghh, 6).toString();
                String Emailhh = tbReader.getValueAt(donghh, 7).toString();
                Date ActiveDatehh = carActivationDate.getDate();

                Reader r = new Reader(RdIDhh, RdNamehh, IDcardhh, Sexhh, Birthdayhh, Addresshh, Phonehh, Emailhh, ActiveDatehh, nextyear);
                Vector vt = r.toVector();

                try {
                    ReaderList.suaExpiredDate(donghh, r);
                } catch (Exception ex) {
                    Logger.getLogger(Form_Library.class.getName()).log(Level.SEVERE, null, ex);
                }
                dmReader.removeRow(donghh);
                dmReader.insertRow(donghh, vt);
            }
        });

        //Gia hạn mượn sách
        tbBorrowingManagement.getColumn("Add 10 Days").setCellRenderer(new ButtonRenderer());
        ButtonEditor buttonEditor = new ButtonEditor(new JCheckBox());
        tbBorrowingManagement.getColumn("Add 10 Days").setCellEditor(buttonEditor);
        buttonEditor.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int donghh = tbBorrowingManagement.getSelectedRow();
                try {
                    System.out.println(truNgayThang(df.parse(tbBorrowingManagement.getValueAt(donghh, 4).toString())));
                } catch (ParseException ex) {
                    Logger.getLogger(Form_Library.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    if (truNgayThang(df2.parse(tbBorrowingManagement.getValueAt(donghh, 4).toString())) <= 0) {

                        String BrIDhh = tbBorrowingManagement.getValueAt(donghh, 0).toString();
                        String RdIDhh = tbBorrowingManagement.getValueAt(donghh, 1).toString();
                        String BookIDhh = tbBorrowingManagement.getValueAt(donghh, 2).toString();
                        Date BorrowDatehh = carBorrowDate.getDate();

                        BorrowingManagement r = new BorrowingManagement(BrIDhh, RdIDhh, BookIDhh, BorrowDatehh, nextday);
                        Vector vt = r.toVector();

                        try {
                            BorrowingList.suaReturnDate(donghh, r);
                        } catch (Exception ex) {
                            Logger.getLogger(Form_Library.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        dmBorrowing.removeRow(donghh);
                        dmBorrowing.insertRow(donghh, vt);
//                disableCarlendarBorrow();
                    } else {
                        JOptionPane.showMessageDialog(null, "Can't renew");
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(Form_Library.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        BorrowingList.load("Select * from borrowingmanagement");

        for (BorrowingManagement r : BorrowingList.getList()) {
            dmBorrowing.addRow(r.toVector());

        }

        //Book
        this.tbBookAdmin.setModel(dmBook);
        dmBook.setColumnIdentifiers(tenCotBook);
        BookList.load("Select * from book");
        for (Book b : BookList.getList()) {
            dmBook.addRow(b.toVector());
        }
        clist.loadCategory();
        for (Category c : clist.getCategoryList()) {
            this.cbCategoryID.addItem(c.getCategoryID());
        }

        //Author
        this.tbAuthorAdmin.setModel(dmAuthor);
        dmAuthor.setColumnIdentifiers(tenCotAuthor);
        AuthorList.load("Select * from author");
        for (Author b : AuthorList.getList()) {
            dmAuthor.addRow(b.toVector());
        }

        //Category
        this.tbcategory.setModel(dmCategory);
        dmCategory.setColumnIdentifiers(tenCotCategory);
        CategoryList.load("Select * from category");
        for (Category c : CategoryList.getCategoryList()) {
            dmCategory.addRow(c.toVector());
        }

        //Publisher
        this.tbPublisher.setModel(dmPublisher);
        dmPublisher.setColumnIdentifiers(tenCotPublisher);
        PublisherList.load("Select * from publisher");
        for (Publisher c : PublisherList.getPublisherList()) {
            dmPublisher.addRow(c.toVector());
        }

        //Supplier
        this.tbSupplierAdmin.setModel(dmSupplier);
        dmSupplier.setColumnIdentifiers(tenCotSupplier);
        SupplierList.load("Select * from supplier");
        for (Supplier c : SupplierList.getSupplierList()) {
            dmSupplier.addRow(c.toVector());
        }

        //Return
        this.tbReturn.setModel(dmReturn);
        dmReturn.setColumnIdentifiers(tenCotReturn);
        ReturnList.load("select borrowingmanagement.BorrowID, borrowingmanagement.BookID, reader.RdName,book.BookName, author.AuthorName, publisher.PublisherName, book.Price, borrowingmanagement.BorrowDate, borrowingmanagement.ReturnDate\n"
                + "from borrowingmanagement\n"
                + "inner join book on borrowingmanagement.BookID = book.BookID\n"
                + "inner join author on book.AuthorID = author.AuthorID\n"
                + "inner join reader on borrowingmanagement.RdID = reader.RdID\n"
                + "inner join publisher on book.PublisherID = publisher.PublisherID\n");
        for (ReturnManagement c : ReturnList.getReturnList()) {
            dmReturn.addRow(c.toVector());
        }

        //Co dãn các cột
        resizeColumnWidth(tbAuthorAdmin);
        resizeColumnWidth(tbBookAdmin);
        resizeColumnWidth(tbBorrowingManagement);
        resizeColumnWidth(tbPhieuQuaHan);
        resizeColumnWidth(tbPublisher);
        resizeColumnWidth(tbReader);
        resizeColumnWidth(tbReturn);
        resizeColumnWidth(tbSupplierAdmin);
        resizeColumnWidth(tbcategory);

        //Sort Jtable
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tbBookAdmin.getModel());
        tbBookAdmin.setRowSorter(sorter);
        TableRowSorter<TableModel> sorter2 = new TableRowSorter<>(tbReturn.getModel());
        tbReturn.setRowSorter(sorter2);
        sorter2.setComparator(6, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        sorter2.setComparator(9, new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return o1.intValue() - o2.intValue();
            }
        });
        sorter2.setComparator(10, new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return o1.intValue() - o2.intValue();
            }
        });

        sorter.setComparator(6, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        sorter.setComparator(7, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        sorter.setComparator(9, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        sorter.setComparator(10, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        jPopupMenuBook.setName("jPopupMenu");
        tbBookAdmin.setComponentPopupMenu(jPopupMenuBook);
        jMenuItemBook.setText("Copy"); // NOI18N
        jMenuItemBook.setName("jMenuItem"); // NOI18N
        jPopupMenuBook.add(jMenuItemBook);

        jMenuItemBo.setName("jPopupMenu");
        tbBorrowingManagement.setComponentPopupMenu(jPopupMenuBo);
        jMenuItemBo.setText("Copy"); // NOI18N
        jMenuItemBo.setName("jMenuItem"); // NOI18N
        jPopupMenuBo.add(jMenuItemBo);

        jPopupMenuAuthor.setName("jPopupMenu");
        tbAuthorAdmin.setComponentPopupMenu(jPopupMenuAuthor);
        jMenuItemAuthor.setText("Copy"); // NOI18N
        jMenuItemAuthor.setName("jMenuItem"); // NOI18N
        jPopupMenuAuthor.add(jMenuItemAuthor);

        jPopupMenuPub.setName("jPopupMenu");
        tbPublisher.setComponentPopupMenu(jPopupMenuPub);
        jMenuItemPub.setText("Copy"); // NOI18N
        jMenuItemPub.setName("jMenuItem"); // NOI18N
        jPopupMenuPub.add(jMenuItemPub);

        jPopupMenuCate.setName("jPopupMenu");
        tbcategory.setComponentPopupMenu(jPopupMenuCate);
        jMenuItemCate.setText("Copy"); // NOI18N
        jMenuItemCate.setName("jMenuItem"); // NOI18N
        jPopupMenuCate.add(jMenuItemCate);

        jPopupMenuSup.setName("jPopupMenu");
        tbSupplierAdmin.setComponentPopupMenu(jPopupMenuSup);
        jMenuItemSup.setText("Copy"); // NOI18N
        jMenuItemSup.setName("jMenuItem"); // NOI18N
        jPopupMenuSup.add(jMenuItemSup);

        jPopupMenuReader.setName("jPopupMenu");
        tbReader.setComponentPopupMenu(jPopupMenuReader);
        jMenuItemReader.setText("Copy"); // NOI18N
        jMenuItemReader.setName("jMenuItem"); // NOI18N
        jPopupMenuReader.add(jMenuItemReader);

        setCarlendarBorrow();
        setCarlendarReader();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        lbBookID = new javax.swing.JLabel();
        lbSupplierID = new javax.swing.JLabel();
        lbBookName = new javax.swing.JLabel();
        lbPrice = new javax.swing.JLabel();
        lbCategoryID = new javax.swing.JLabel();
        tfBookID = new javax.swing.JTextField();
        tfBookName = new javax.swing.JTextField();
        tfPrice = new javax.swing.JTextField();
        lbColumnNo = new javax.swing.JLabel();
        lbAuthorID = new javax.swing.JLabel();
        lbShelf = new javax.swing.JLabel();
        lbQuantity = new javax.swing.JLabel();
        lbRowNo = new javax.swing.JLabel();
        cbCategoryID = new javax.swing.JComboBox<>();
        tfColumnNo = new javax.swing.JTextField();
        tfQuantity = new javax.swing.JTextField();
        tfShelf = new javax.swing.JTextField();
        tfRowNo = new javax.swing.JTextField();
        tfAuthorID = new javax.swing.JTextField();
        tfSupplierID = new javax.swing.JTextField();
        lbImage = new javax.swing.JLabel();
        btImage = new javax.swing.JButton();
        tfImage = new javax.swing.JTextField();
        btAdd1 = new javax.swing.JButton();
        btEdit1 = new javax.swing.JButton();
        btDelete1 = new javax.swing.JButton();
        btClose = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbBookAdmin = new javax.swing.JTable();
        tfSearchBook = new javax.swing.JTextField();
        btSearchBook = new javax.swing.JButton();
        lbSupplierID2 = new javax.swing.JLabel();
        tfPublisherofBook = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        btSave = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        tfcategoryid = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tfcategoryname = new javax.swing.JTextField();
        btadd = new javax.swing.JButton();
        btupdate = new javax.swing.JButton();
        btdelete = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbcategory = new javax.swing.JTable();
        btdelete1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        btAdd2 = new javax.swing.JButton();
        btEdit2 = new javax.swing.JButton();
        btDelete2 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbAuthorAdmin = new javax.swing.JTable();
        lbAuthorID1 = new javax.swing.JLabel();
        tfAuthorID1 = new javax.swing.JTextField();
        lbAuthorName = new javax.swing.JLabel();
        tfAuthorName = new javax.swing.JTextField();
        btClose1 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        btAddPublisher = new javax.swing.JButton();
        btEditPublisher = new javax.swing.JButton();
        btDeletePublisher = new javax.swing.JButton();
        btClose2 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbPublisher = new javax.swing.JTable();
        lbAuthorID2 = new javax.swing.JLabel();
        tfPublisherID = new javax.swing.JTextField();
        lbAuthorName1 = new javax.swing.JLabel();
        tfPublisherName = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        tfName = new javax.swing.JTextField();
        tfID = new javax.swing.JTextField();
        tfIDCardNumber = new javax.swing.JTextField();
        tfEmail = new javax.swing.JTextField();
        tfAddress = new javax.swing.JTextField();
        tfPhone = new javax.swing.JTextField();
        cbSex = new javax.swing.JComboBox<>();
        carBirthday = new com.toedter.calendar.JDateChooser();
        carActivationDate = new com.toedter.calendar.JDateChooser();
        carExpiredDate = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbReader = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        tfSearch = new javax.swing.JTextField();
        btnSearch1 = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        taPrintReader = new javax.swing.JTextArea();
        btPrintReader = new javax.swing.JButton();
        lbImageLink = new javax.swing.JLabel();
        btSaveReader = new javax.swing.JButton();
        tfImageLink = new javax.swing.JTextField();
        btBrowseReader = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel15 = new javax.swing.JPanel();
        tfBorrowID = new javax.swing.JTextField();
        tfBookBMID = new javax.swing.JTextField();
        tfReaderID = new javax.swing.JTextField();
        carBorrowDate = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        carReturnDate = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbBorrowingManagement = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        btEdit = new javax.swing.JButton();
        btDelete3 = new javax.swing.JButton();
        btDelete = new javax.swing.JButton();
        btAdd = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        tfSearchReturn = new javax.swing.JTextField();
        btSearchReturn = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbReturn = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        lbSupplierID1 = new javax.swing.JLabel();
        lbSupplierName = new javax.swing.JLabel();
        tfSupplierID1 = new javax.swing.JTextField();
        tfSupplierName = new javax.swing.JTextField();
        lbPhone = new javax.swing.JLabel();
        ftfPhoneSupplier = new javax.swing.JFormattedTextField();
        lbAddress = new javax.swing.JLabel();
        tfAddressSuplier = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbSupplierAdmin = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        btEditSupplier = new javax.swing.JButton();
        btDeleteSupplier = new javax.swing.JButton();
        btClose3 = new javax.swing.JButton();
        btAddSupplier = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbPhieuQuaHan = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        lbTongSach = new javax.swing.JLabel();
        lbTongPhieu = new javax.swing.JLabel();
        lbTongKhachMuon = new javax.swing.JLabel();
        lbTongKhach = new javax.swing.JLabel();
        lbTongPhieuQuaHan = new javax.swing.JLabel();
        btPrintStas = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        taBaoCao = new javax.swing.JTextArea();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1KeyPressed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/vnflag.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("resources/Bundle"); // NOI18N
        jButton10.setText(bundle.getString("Form_Library.jButton10.text")); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/US.png"))); // NOI18N
        jButton11.setText(bundle.getString("Form_Library.jButton11.text")); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/japan-flag-icon.png"))); // NOI18N
        jButton12.setText(bundle.getString("Form_Library.jButton12.text")); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 373, Short.MAX_VALUE)
            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel19Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jButton10)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton11)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton12)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel19Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/key.png"))); // NOI18N
        jButton1.setMnemonic('C');
        jButton1.setText(bundle.getString("Form_Library.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logout.png"))); // NOI18N
        jButton2.setMnemonic('L');
        jButton2.setText(bundle.getString("Form_Library.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/home.png"))); // NOI18N
        jButton3.setMnemonic('H');
        jButton3.setText(bundle.getString("Form_Library.jButton3.text")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(bundle.getString("Form_Library.jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        lbBookID.setText(bundle.getString("Form_Library.lbBookID.text")); // NOI18N

        lbSupplierID.setText(bundle.getString("Form_Library.lbSupplierID.text")); // NOI18N

        lbBookName.setText(bundle.getString("Form_Library.lbBookName.text")); // NOI18N

        lbPrice.setText(bundle.getString("Form_Library.lbPrice.text")); // NOI18N

        lbCategoryID.setText(bundle.getString("Form_Library.lbCategoryID.text")); // NOI18N

        tfBookID.setEditable(false);

        tfBookName.setEditable(false);

        tfPrice.setEditable(false);

        lbColumnNo.setText(bundle.getString("Form_Library.lbColumnNo.text")); // NOI18N

        lbAuthorID.setText(bundle.getString("Form_Library.lbAuthorID.text")); // NOI18N

        lbShelf.setText(bundle.getString("Form_Library.lbShelf.text")); // NOI18N

        lbQuantity.setText(bundle.getString("Form_Library.lbQuantity.text")); // NOI18N

        lbRowNo.setText(bundle.getString("Form_Library.lbRowNo.text")); // NOI18N

        cbCategoryID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--CategoryID--" }));
        cbCategoryID.setEnabled(false);

        tfColumnNo.setEditable(false);

        tfQuantity.setEditable(false);

        tfShelf.setEditable(false);

        tfRowNo.setEditable(false);

        tfAuthorID.setEditable(false);

        tfSupplierID.setEditable(false);

        lbImage.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        btImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Pictures Folder-20.png"))); // NOI18N
        btImage.setText(bundle.getString("Form_Library.btImage.text")); // NOI18N
        btImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImageActionPerformed(evt);
            }
        });

        tfImage.setEditable(false);

        btAdd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_add.png"))); // NOI18N
        btAdd1.setMnemonic('A');
        btAdd1.setText(bundle.getString("Form_Library.btAdd1.text")); // NOI18N
        btAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdd1ActionPerformed(evt);
            }
        });

        btEdit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gtk-refresh.png"))); // NOI18N
        btEdit1.setMnemonic('U');
        btEdit1.setText(bundle.getString("Form_Library.btEdit1.text")); // NOI18N
        btEdit1.setMaximumSize(new java.awt.Dimension(63, 23));
        btEdit1.setMinimumSize(new java.awt.Dimension(63, 23));
        btEdit1.setPreferredSize(new java.awt.Dimension(63, 23));
        btEdit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEdit1ActionPerformed(evt);
            }
        });

        btDelete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_delete.png"))); // NOI18N
        btDelete1.setMnemonic('D');
        btDelete1.setText(bundle.getString("Form_Library.btDelete1.text")); // NOI18N
        btDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDelete1ActionPerformed(evt);
            }
        });

        btClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Redo-16.png"))); // NOI18N
        btClose.setMnemonic('C');
        btClose.setText(bundle.getString("Form_Library.btClose.text")); // NOI18N
        btClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCloseActionPerformed(evt);
            }
        });

        tbBookAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbBookAdmin.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tbBookAdmin.setName(""); // NOI18N
        tbBookAdmin.setAutoCreateRowSorter(true);
        tbBookAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBookAdminMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbBookAdmin);

        btSearchBook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_search.png"))); // NOI18N
        btSearchBook.setMnemonic('S');
        btSearchBook.setText(bundle.getString("Form_Library.btSearchBook.text")); // NOI18N
        btSearchBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSearchBookActionPerformed(evt);
            }
        });

        lbSupplierID2.setText(bundle.getString("Form_Library.lbSupplierID2.text")); // NOI18N

        tfPublisherofBook.setEditable(false);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Export-16 (1).png"))); // NOI18N
        jButton4.setText(bundle.getString("Form_Library.jButton4.text")); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Import-16 (1).png"))); // NOI18N
        jButton5.setText(bundle.getString("Form_Library.jButton5.text")); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        btSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save-16.png"))); // NOI18N
        btSave.setText(bundle.getString("Form_Library.btSave.text")); // NOI18N
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                    .addComponent(btAdd1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(btEdit1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btDelete1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                        .addComponent(btSave, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(tfSearchBook))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btSearchBook, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(tfSupplierID, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addComponent(lbSupplierID2)
                                    .addGap(18, 18, 18)
                                    .addComponent(tfPublisherofBook, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbBookName)
                                        .addComponent(lbBookID, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbAuthorID))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tfAuthorID, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                                        .addComponent(tfBookID)
                                        .addComponent(tfBookName))))
                            .addComponent(lbSupplierID, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                .addComponent(lbCategoryID)
                                .addGap(18, 18, 18)
                                .addComponent(cbCategoryID, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbRowNo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbQuantity)
                                            .addComponent(lbPrice)
                                            .addComponent(lbShelf))
                                        .addGap(28, 28, 28)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tfQuantity)
                                            .addComponent(tfShelf)
                                            .addComponent(tfPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btImage)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(lbColumnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(47, 47, 47)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfColumnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfImage, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfRowNo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(111, 111, 111)
                .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfBookID)
                            .addComponent(lbBookID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbPrice)
                            .addComponent(tfPrice))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfBookName)
                            .addComponent(lbBookName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbQuantity)
                            .addComponent(tfQuantity))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbAuthorID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfAuthorID)
                            .addComponent(lbShelf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfShelf))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbSupplierID2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfPublisherofBook)
                            .addComponent(lbRowNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfRowNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbSupplierID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfSupplierID)
                            .addComponent(lbColumnNo)
                            .addComponent(tfColumnNo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbCategoryID)
                            .addComponent(cbCategoryID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btImage)
                            .addComponent(tfImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btAdd1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                                    .addComponent(btEdit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btDelete1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfSearchBook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(btClose, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btSearchBook)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab(bundle.getString("Form_Library.jPanel7.TabConstraints.tabTitle"), jPanel7); // NOI18N

        jLabel16.setText(bundle.getString("Form_Library.jLabel16.text")); // NOI18N

        tfcategoryid.setEditable(false);

        jLabel17.setText(bundle.getString("Form_Library.jLabel17.text")); // NOI18N

        btadd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_add.png"))); // NOI18N
        btadd.setMnemonic('A');
        btadd.setText(bundle.getString("Form_Library.btadd.text")); // NOI18N
        btadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btaddActionPerformed(evt);
            }
        });

        btupdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gtk-refresh.png"))); // NOI18N
        btupdate.setMnemonic('U');
        btupdate.setText(bundle.getString("Form_Library.btupdate.text")); // NOI18N
        btupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btupdateActionPerformed(evt);
            }
        });

        btdelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_delete.png"))); // NOI18N
        btdelete.setMnemonic('D');
        btdelete.setText(bundle.getString("Form_Library.btdelete.text")); // NOI18N
        btdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btdeleteActionPerformed(evt);
            }
        });

        tbcategory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Category ID", "Category Name"
            }
        ));
        tbcategory.setAutoCreateRowSorter(true);
        tbcategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbcategoryMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbcategory);

        btdelete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Redo-16.png"))); // NOI18N
        btdelete1.setMnemonic('C');
        btdelete1.setText(bundle.getString("Form_Library.btdelete1.text")); // NOI18N
        btdelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btdelete1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(btadd, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btdelete, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(tfcategoryid, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btdelete1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(tfcategoryname, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(tfcategoryid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(tfcategoryname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btadd, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btdelete, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btdelete1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab(bundle.getString("Form_Library.jPanel9.TabConstraints.tabTitle"), jPanel9); // NOI18N

        btAdd2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_add.png"))); // NOI18N
        btAdd2.setMnemonic('A');
        btAdd2.setText(bundle.getString("Form_Library.btAdd2.text")); // NOI18N
        btAdd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdd2ActionPerformed(evt);
            }
        });

        btEdit2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gtk-refresh.png"))); // NOI18N
        btEdit2.setMnemonic('U');
        btEdit2.setText(bundle.getString("Form_Library.btEdit2.text")); // NOI18N
        btEdit2.setMaximumSize(new java.awt.Dimension(63, 23));
        btEdit2.setMinimumSize(new java.awt.Dimension(63, 23));
        btEdit2.setPreferredSize(new java.awt.Dimension(63, 23));
        btEdit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEdit2ActionPerformed(evt);
            }
        });

        btDelete2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_delete.png"))); // NOI18N
        btDelete2.setMnemonic('D');
        btDelete2.setText(bundle.getString("Form_Library.btDelete2.text")); // NOI18N
        btDelete2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDelete2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btAdd2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btEdit2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btDelete2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btAdd2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btEdit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btDelete2))
        );

        tbAuthorAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbAuthorAdmin.setColumnSelectionAllowed(true);
        tbAuthorAdmin.setName(""); // NOI18N
        tbAuthorAdmin.setAutoCreateRowSorter(true);
        tbAuthorAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAuthorAdminMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbAuthorAdmin);

        lbAuthorID1.setText(bundle.getString("Form_Library.lbAuthorID1.text")); // NOI18N

        tfAuthorID1.setEditable(false);

        lbAuthorName.setText(bundle.getString("Form_Library.lbAuthorName.text")); // NOI18N

        btClose1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Redo-16.png"))); // NOI18N
        btClose1.setMnemonic('C');
        btClose1.setText(bundle.getString("Form_Library.btClose1.text")); // NOI18N
        btClose1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClose1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(39, 39, 39)
                        .addComponent(btClose1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(137, 137, 137))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(354, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lbAuthorID1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfAuthorID1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbAuthorName)
                .addGap(18, 18, 18)
                .addComponent(tfAuthorName, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbAuthorID1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfAuthorID1)
                    .addComponent(lbAuthorName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfAuthorName))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btClose1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane2.addTab(bundle.getString("Form_Library.jPanel8.TabConstraints.tabTitle"), jPanel8); // NOI18N

        btAddPublisher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_add.png"))); // NOI18N
        btAddPublisher.setMnemonic('A');
        btAddPublisher.setText(bundle.getString("Form_Library.btAddPublisher.text")); // NOI18N
        btAddPublisher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddPublisherActionPerformed(evt);
            }
        });

        btEditPublisher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gtk-refresh.png"))); // NOI18N
        btEditPublisher.setMnemonic('U');
        btEditPublisher.setText(bundle.getString("Form_Library.btEditPublisher.text")); // NOI18N
        btEditPublisher.setMaximumSize(new java.awt.Dimension(63, 23));
        btEditPublisher.setMinimumSize(new java.awt.Dimension(63, 23));
        btEditPublisher.setPreferredSize(new java.awt.Dimension(63, 23));
        btEditPublisher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditPublisherActionPerformed(evt);
            }
        });

        btDeletePublisher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_delete.png"))); // NOI18N
        btDeletePublisher.setMnemonic('D');
        btDeletePublisher.setText(bundle.getString("Form_Library.btDeletePublisher.text")); // NOI18N
        btDeletePublisher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeletePublisherActionPerformed(evt);
            }
        });

        btClose2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Redo-16.png"))); // NOI18N
        btClose2.setMnemonic('C');
        btClose2.setText(bundle.getString("Form_Library.btClose2.text")); // NOI18N
        btClose2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClose2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(btAddPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btEditPublisher, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btDeletePublisher, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(460, 460, 460)
                .addComponent(btClose2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btAddPublisher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btEditPublisher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btDeletePublisher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btClose2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane6MouseClicked(evt);
            }
        });

        tbPublisher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbPublisher.setColumnSelectionAllowed(true);
        tbPublisher.setAutoCreateRowSorter(true);
        tbPublisher.setName(""); // NOI18N
        tbPublisher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPublisherMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbPublisher);
        tbPublisher.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        lbAuthorID2.setText(bundle.getString("Form_Library.lbAuthorID2.text")); // NOI18N

        tfPublisherID.setEditable(false);

        lbAuthorName1.setText(bundle.getString("Form_Library.lbAuthorName1.text")); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(lbAuthorID2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tfPublisherID, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbAuthorName1)
                        .addGap(29, 29, 29)
                        .addComponent(tfPublisherName, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbAuthorID2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfPublisherID)
                    .addComponent(lbAuthorName1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfPublisherName))
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane2.addTab(bundle.getString("Form_Library.jPanel10.TabConstraints.tabTitle"), jPanel10); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        jTabbedPane1.addTab(bundle.getString("Form_Library.jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jPanel3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel3KeyPressed(evt);
            }
        });

        tfName.setEditable(false);

        tfID.setEditable(false);

        tfIDCardNumber.setEditable(false);

        tfEmail.setEditable(false);

        tfAddress.setEditable(false);

        tfPhone.setEditable(false);

        cbSex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        cbSex.setEnabled(false);
        cbSex.setMaximumSize(new java.awt.Dimension(450, 450));
        cbSex.setMinimumSize(new java.awt.Dimension(450, 450));
        cbSex.setName(""); // NOI18N

        carBirthday.setDateFormatString(bundle.getString("Form_Library.carBirthday.dateFormatString")); // NOI18N
        carBirthday.setEnabled(false);

        carActivationDate.setDateFormatString(bundle.getString("Form_Library.carActivationDate.dateFormatString")); // NOI18N
        carActivationDate.setEnabled(false);

        carExpiredDate.setDateFormatString(bundle.getString("Form_Library.carExpiredDate.dateFormatString")); // NOI18N
        carExpiredDate.setEnabled(false);

        jLabel6.setText(bundle.getString("Form_Library.jLabel6.text")); // NOI18N

        jLabel7.setText(bundle.getString("Form_Library.jLabel7.text")); // NOI18N

        jLabel8.setText(bundle.getString("Form_Library.jLabel8.text")); // NOI18N

        jLabel9.setText(bundle.getString("Form_Library.jLabel9.text")); // NOI18N

        jLabel10.setText(bundle.getString("Form_Library.jLabel10.text")); // NOI18N

        jLabel11.setText(bundle.getString("Form_Library.jLabel11.text")); // NOI18N

        jLabel12.setText(bundle.getString("Form_Library.jLabel12.text")); // NOI18N

        jLabel13.setText(bundle.getString("Form_Library.jLabel13.text")); // NOI18N

        jLabel14.setText(bundle.getString("Form_Library.jLabel14.text")); // NOI18N

        jLabel15.setText(bundle.getString("Form_Library.jLabel15.text")); // NOI18N

        tbReader.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "ID Card Number", "Sex", "Birthday", "Address", "Phone", "Email", "Activation Date", "Expired Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbReader.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tbReader.setAutoCreateRowSorter(true);
        tbReader.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbReaderMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbReader);

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_add.png"))); // NOI18N
        btnAdd.setMnemonic('A');
        btnAdd.setText(bundle.getString("Form_Library.btnAdd.text")); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gtk-refresh.png"))); // NOI18N
        btnEdit.setMnemonic('U');
        btnEdit.setText(bundle.getString("Form_Library.btnEdit.text")); // NOI18N
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_delete.png"))); // NOI18N
        btnDelete.setMnemonic('D');
        btnDelete.setText(bundle.getString("Form_Library.btnDelete.text")); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_search.png"))); // NOI18N
        btnSearch.setMnemonic('S');
        btnSearch.setText(bundle.getString("Form_Library.btnSearch.text")); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnSearch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Redo-16.png"))); // NOI18N
        btnSearch1.setMnemonic('C');
        btnSearch1.setText(bundle.getString("Form_Library.btnSearch1.text")); // NOI18N
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });

        taPrintReader.setColumns(20);
        taPrintReader.setRows(5);
        jScrollPane11.setViewportView(taPrintReader);

        btPrintReader.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Print-16.png"))); // NOI18N
        btPrintReader.setText(bundle.getString("Form_Library.btPrintReader.text")); // NOI18N
        btPrintReader.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPrintReaderActionPerformed(evt);
            }
        });

        lbImageLink.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btSaveReader.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save-16.png"))); // NOI18N
        btSaveReader.setText(bundle.getString("Form_Library.btSaveReader.text")); // NOI18N
        btSaveReader.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveReaderActionPerformed(evt);
            }
        });

        btBrowseReader.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Pictures Folder-20.png"))); // NOI18N
        btBrowseReader.setText(bundle.getString("Form_Library.btBrowseReader.text")); // NOI18N
        btBrowseReader.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBrowseReaderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(btPrintReader, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9))
                                        .addGap(49, 49, 49)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tfIDCardNumber)
                                            .addComponent(tfName)
                                            .addComponent(carBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbSex, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btSaveReader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))))
                                .addGap(0, 143, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(carActivationDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel11))
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(carExpiredDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                                .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(tfPhone, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(tfAddress)))))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(tfImageLink, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                            .addComponent(tfSearch))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btBrowseReader, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                            .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(39, 39, 39)
                                .addComponent(lbImageLink, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29))
                            .addComponent(jScrollPane11))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel11)
                            .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(tfPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(tfIDCardNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(carActivationDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(jLabel9)
                                .addComponent(cbSex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(carExpiredDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(carBirthday, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEdit)
                            .addComponent(btnAdd)
                            .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfImageLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btBrowseReader, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btSaveReader)
                                    .addComponent(btnDelete))))
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbImageLink, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btPrintReader)
                    .addComponent(btnSearch1))
                .addContainerGap(167, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(bundle.getString("Form_Library.jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        tfBorrowID.setEditable(false);
        tfBorrowID.setText(bundle.getString("Form_Library.tfBorrowID.text")); // NOI18N

        carBorrowDate.setDateFormatString(bundle.getString("Form_Library.carBorrowDate.dateFormatString")); // NOI18N

        jLabel1.setText(bundle.getString("Form_Library.jLabel1.text")); // NOI18N

        carReturnDate.setDateFormatString(bundle.getString("Form_Library.carReturnDate.dateFormatString")); // NOI18N

        jLabel2.setText(bundle.getString("Form_Library.jLabel2.text")); // NOI18N

        jLabel3.setText(bundle.getString("Form_Library.jLabel3.text")); // NOI18N

        jLabel4.setText(bundle.getString("Form_Library.jLabel4.text")); // NOI18N

        jLabel5.setText(bundle.getString("Form_Library.jLabel5.text")); // NOI18N

        tbBorrowingManagement.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Borrow ID", "Reader ID", "Book ID", "Borrow Date", "Return Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbBorrowingManagement.setAutoCreateRowSorter(true);
        tbBorrowingManagement.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBorrowingManagementMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbBorrowingManagement);

        btEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gtk-refresh.png"))); // NOI18N
        btEdit.setMnemonic('U');
        btEdit.setText(bundle.getString("Form_Library.btEdit.text")); // NOI18N
        btEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditActionPerformed(evt);
            }
        });

        btDelete3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Redo-16.png"))); // NOI18N
        btDelete3.setMnemonic('C');
        btDelete3.setText(bundle.getString("Form_Library.btDelete3.text")); // NOI18N
        btDelete3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDelete3ActionPerformed(evt);
            }
        });

        btDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_delete.png"))); // NOI18N
        btDelete.setMnemonic('D');
        btDelete.setText(bundle.getString("Form_Library.btDelete.text")); // NOI18N
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });

        btAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_add.png"))); // NOI18N
        btAdd.setMnemonic('A');
        btAdd.setText(bundle.getString("Form_Library.btAdd.text")); // NOI18N
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gtk-refresh.png"))); // NOI18N
        Action buttonActionBor = new AbstractAction("",new javax.swing.ImageIcon(getClass().getResource("/icon/gtk-refresh.png"))) {

            @Override
            public void actionPerformed(ActionEvent evt) {
                BorrowingList.getList().clear();
                dmBorrowing.getDataVector().clear();
                BorrowingList.load("select * from borrowingmanagement");
                for (BorrowingManagement c : BorrowingList.getList()) {
                    dmBorrowing.addRow(c.toVector());
                }
            }
        };

        String keyBor = "";

        jButton9.setAction(buttonActionBor);

        buttonActionBor.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);

        jButton9.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), keyBor);

        jButton9.getActionMap().put(keyBor, buttonActionBor);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 423, Short.MAX_VALUE)
                .addComponent(btDelete3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btDelete3)
                        .addComponent(btDelete)
                        .addComponent(btEdit)
                        .addComponent(btAdd)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(tfBorrowID))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(carBorrowDate, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(tfReaderID, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(carReturnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(78, 78, 78)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(tfBookBMID, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfBorrowID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(tfReaderID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(tfBookBMID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(carBorrowDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(carReturnDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab(bundle.getString("Form_Library.jPanel15.TabConstraints.tabTitle"), jPanel15); // NOI18N

        btSearchReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_search.png"))); // NOI18N
        btSearchReturn.setMnemonic('S');
        btSearchReturn.setText(bundle.getString("Form_Library.btSearchReturn.text")); // NOI18N
        btSearchReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSearchReturnActionPerformed(evt);
            }
        });

        tbReturn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbReturn.setAutoCreateRowSorter(true);
        tbReturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbReturnMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tbReturn);

        jLabel18.setText(bundle.getString("Form_Library.jLabel18.text")); // NOI18N

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setText(bundle.getString("Form_Library.jLabel19.text")); // NOI18N

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gtk-refresh.png"))); // NOI18N
        Action buttonAction = new AbstractAction("",new javax.swing.ImageIcon(getClass().getResource("/icon/gtk-refresh.png"))) {

            @Override
            public void actionPerformed(ActionEvent evt) {
                ReturnList.getReturnList().clear();
                dmReturn.getDataVector().clear();
                ReturnList.load("select borrowingmanagement.BorrowID, borrowingmanagement.BookID, reader.RdName, book.BookName, author.AuthorName, publisher.PublisherName, book.Price, borrowingmanagement.BorrowDate, borrowingmanagement.ReturnDate\n"
                    + "from borrowingmanagement\n"
                    + "inner join book on borrowingmanagement.BookID = book.BookID\n"
                    + "inner join author on book.AuthorID = author.AuthorID\n"
                    + "inner join publisher on book.PublisherID = publisher.PublisherID\n"
                    + "inner join reader on borrowingmanagement.RdID = reader.RdID");
                for (ReturnManagement c : ReturnList.getReturnList()) {
                    dmReturn.addRow(c.toVector());
                }
            }
        };

        String key = "";

        jButton8.setAction(buttonAction);

        buttonAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);

        jButton8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), key);

        jButton8.getActionMap().put(key, buttonAction);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(259, 259, 259)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfSearchReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btSearchReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton8))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(271, 271, 271)
                                .addComponent(jLabel19)))
                        .addGap(0, 391, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane8)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfSearchReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btSearchReturn)
                        .addComponent(jLabel18))
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab(bundle.getString("Form_Library.jPanel16.TabConstraints.tabTitle"), jPanel16); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );

        jTabbedPane1.addTab(bundle.getString("Form_Library.jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        lbSupplierID1.setText(bundle.getString("Form_Library.lbSupplierID1.text")); // NOI18N

        lbSupplierName.setText(bundle.getString("Form_Library.lbSupplierName.text")); // NOI18N

        tfSupplierID1.setEditable(false);

        lbPhone.setText(bundle.getString("Form_Library.lbPhone.text")); // NOI18N

        lbAddress.setText(bundle.getString("Form_Library.lbAddress.text")); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbPhone)
                    .addComponent(lbSupplierID1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ftfPhoneSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                    .addComponent(tfSupplierID1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 272, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbSupplierName, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(lbAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfSupplierName, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addComponent(tfAddressSuplier))
                .addGap(49, 49, 49))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfSupplierName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbSupplierID1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfSupplierID1)
                        .addComponent(lbSupplierName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbPhone)
                        .addComponent(ftfPhoneSupplier)
                        .addComponent(tfAddressSuplier)))
                .addContainerGap())
        );

        tbSupplierAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbSupplierAdmin.setColumnSelectionAllowed(true);
        tbSupplierAdmin.setName(""); // NOI18N
        tbSupplierAdmin.setAutoCreateRowSorter(true);
        tbSupplierAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSupplierAdminMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbSupplierAdmin);

        btEditSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gtk-refresh.png"))); // NOI18N
        btEditSupplier.setMnemonic('U');
        btEditSupplier.setText(bundle.getString("Form_Library.btEditSupplier.text")); // NOI18N
        btEditSupplier.setMaximumSize(new java.awt.Dimension(63, 23));
        btEditSupplier.setMinimumSize(new java.awt.Dimension(63, 23));
        btEditSupplier.setPreferredSize(new java.awt.Dimension(63, 23));
        btEditSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditSupplierActionPerformed(evt);
            }
        });

        btDeleteSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_delete.png"))); // NOI18N
        btDeleteSupplier.setMnemonic('D');
        btDeleteSupplier.setText(bundle.getString("Form_Library.btDeleteSupplier.text")); // NOI18N
        btDeleteSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteSupplierActionPerformed(evt);
            }
        });

        btClose3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Redo-16.png"))); // NOI18N
        btClose3.setText(bundle.getString("Form_Library.btClose3.text")); // NOI18N
        btClose3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClose3ActionPerformed(evt);
            }
        });

        btAddSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_add.png"))); // NOI18N
        btAddSupplier.setMnemonic('A');
        btAddSupplier.setText(bundle.getString("Form_Library.btAddSupplier.text")); // NOI18N
        btAddSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddSupplierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel17Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btAddSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addComponent(btEditSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addComponent(btDeleteSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addComponent(btClose3, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel17Layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btAddSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btDeleteSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btEditSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btClose3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(39, 39, 39)))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                .addGap(176, 176, 176))
        );

        jTabbedPane1.addTab(bundle.getString("Form_Library.jPanel5.TabConstraints.tabTitle"), jPanel5); // NOI18N

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel21.setText(bundle.getString("Form_Library.jLabel21.text")); // NOI18N

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText(bundle.getString("Form_Library.jLabel22.text")); // NOI18N

        tbPhieuQuaHan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        tbPhieuQuaHan.setAutoCreateRowSorter(true);
        jScrollPane9.setViewportView(tbPhieuQuaHan);

        btPrintStas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Print-16.png"))); // NOI18N
        btPrintStas.setText(bundle.getString("Form_Library.btPrintStas.text")); // NOI18N
        btPrintStas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPrintStasActionPerformed(evt);
            }
        });

        taBaoCao.setColumns(20);
        taBaoCao.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        taBaoCao.setRows(5);
        taBaoCao.setText(bundle.getString("Form_Library.taBaoCao.text")); // NOI18N
        jScrollPane10.setViewportView(taBaoCao);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Bar Chart-16.png"))); // NOI18N
        jButton6.setText(bundle.getString("Form_Library.jButton6.text")); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Gmail-16.png"))); // NOI18N
        jButton7.setText(bundle.getString("Form_Library.jButton7.text")); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(945, 945, 945)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel21)
                                        .addGap(284, 284, 284))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(62, 62, 62)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lbTongPhieuQuaHan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbTongSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lbTongKhach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(lbTongPhieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(lbTongKhachMuon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel23)))
                                        .addGap(107, 107, 107)))
                                .addGap(85, 85, 85)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btPrintStas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(11, 11, 11)))
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(395, 395, 395)
                .addComponent(jLabel22)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTongKhach)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTongSach)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(lbTongPhieu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTongKhachMuon))
                    .addComponent(btPrintStas, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lbTongPhieuQuaHan)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel22))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(bundle.getString("Form_Library.jPanel6.TabConstraints.tabTitle"), jPanel6); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbReaderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbReaderMouseClicked
        carActivationDate.setEnabled(true);
        carExpiredDate.setEnabled(true);
        int donghh = this.tbReader.getSelectedRow();
        Reader t = ReaderList.getReader(donghh);
        this.viewReader(t);
        this.taPrintReader.setText("");
        this.taPrintReader.append("Receipt\n");
        this.taPrintReader.append("ReaderID: " + this.tfID.getText() + ".\n");
        this.taPrintReader.append("Name: " + this.tfName.getText() + ".\n");
        this.taPrintReader.append("IDCardNo: " + this.tfIDCardNumber.getText() + ".\n");
        this.taPrintReader.append("Gender: " + this.cbSex.getSelectedItem() + ".\n");
        this.taPrintReader.append("Birthday: " + df.format(this.carBirthday.getDate()) + ".\n");
        this.taPrintReader.append("Address: " + this.tfAddress.getText() + ".\n");
        this.taPrintReader.append("Phone: " + this.tfPhone.getText() + ".\n");
        this.taPrintReader.append("Email: " + this.tfEmail.getText() + ".\n");
        this.taPrintReader.append("Activation Date: " + df.format(this.carActivationDate.getDate()) + ".\n");
        this.taPrintReader.append("Expired Date: " + df.format(this.carExpiredDate.getDate()) + ".\n");
        this.taPrintReader.append("Paid: 80.000 VNĐ");
        this.taPrintReader.append("\r\n");
        jMenuItemReader.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection entry = new StringSelection(tfID.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(entry, entry);
            }
        });
    }//GEN-LAST:event_tbReaderMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        tfName.setEditable(true);
        tfIDCardNumber.setEditable(true);
        carActivationDate.setEnabled(true);
        carExpiredDate.setEnabled(true);
        carBirthday.setEnabled(true);
        tfAddress.setEditable(true);
        tfPhone.setEditable(true);
        tfEmail.setEditable(true);
        cbSex.setEnabled(true);
        String RdID = "RD-" + getRandomString();
        tfID.setText(RdID);
        status = evt.getActionCommand();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed

        tfName.setEditable(true);
        tfIDCardNumber.setEditable(true);
        carActivationDate.setEnabled(true);
        carExpiredDate.setEnabled(true);
        carBirthday.setEnabled(true);
        tfAddress.setEditable(true);
        tfPhone.setEditable(true);
        tfEmail.setEditable(true);
        cbSex.setEnabled(true);
        String RdID = "RD-" + getRandomString();
        tfID.setText(RdID);
        status = evt.getActionCommand();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
            String RdID = tfID.getText();
            int i = ReaderList.xoa(RdID);
            dmReader.removeRow(i);
        } catch (Exception ex) {
            Logger.getLogger(Form_Library.class.getName()).log(Level.SEVERE, null, ex);
        }
        setCarlendarReader();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        this.ReaderList.getList().clear();
        this.dmReader.getDataVector().clear();
        String sql = "Select * from reader where rdname like N'" + tfSearch.getText() + "%'";
        this.ReaderList.load(sql);
        for (Reader r : ReaderList.getList()) {
            this.dmReader.addRow(r.toVector());
        }
        if (this.dmReader.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Not found!!");
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImageActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        int n = chooser.showOpenDialog(this);
        if (n == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            this.tfImage.setText(f.getName());
            File t = null;
            if (!Files.isDirectory(Paths.get("directory"))) {
                new File("image").mkdir();
            }
            try {
                Files.copy(f.toPath(), new File("image/" + f.getName()).toPath());
            } catch (IOException ex) {
            }
//            this.lbImage.setIcon(new ImageIcon(new ImageIcon("image/" + f.getName()).getImage().getScaledInstance(lbImage.WIDTH, lbImage.HEIGHT, Image.SCALE_SMOOTH)));
            this.lbImage.setIcon(BookList.loadIcon("image/" + f.getName(), 200, 300));
        }
    }//GEN-LAST:event_btImageActionPerformed

    private void btEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEdit1ActionPerformed
        // TODO add your handling code here:
        tfBookName.setEditable(true);
        tfAuthorID.setEditable(true);
        tfPublisherofBook.setEditable(true);
        tfSupplierID.setEditable(true);
        tfPrice.setEditable(true);
        tfQuantity.setEditable(true);
        tfShelf.setEditable(true);
        tfRowNo.setEditable(true);
        tfColumnNo.setEditable(true);
        cbCategoryID.setEnabled(true);
        //Book b = new Book(bookID, bookName, authorID, pubID, supplierID, categoryID, price, quantity, shelf, rowNum, colNum, image);
        //Vector vt = b.toVector();
        //int donghh = this.tbBookAdmin.getSelectedRow();
        //BookList.update(donghh, b);
        //dmBook.removeRow(donghh);
        //dmBook.insertRow(donghh, vt);
        status = evt.getActionCommand();
    }//GEN-LAST:event_btEdit1ActionPerformed

    private void btDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDelete1ActionPerformed
        // TODO add your handling code here:
        String bookId = tfBookID.getText();
        int i = BookList.delete(bookId);
        dmBook.removeRow(i);
    }//GEN-LAST:event_btDelete1ActionPerformed

    private void tbBookAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBookAdminMouseClicked
        // TODO add your handling code here:
        int donghh = this.tbBookAdmin.getSelectedRow();
        Book b = BookList.getBook(donghh);
        this.viewBook(b);
        jMenuItemBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection entry = new StringSelection(tfBookID.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(entry, entry);
            }
        });
    }//GEN-LAST:event_tbBookAdminMouseClicked

    private void btCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btCloseActionPerformed

    private void btAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdd1ActionPerformed
        // TODO add your handling code here:
        tfBookName.setEditable(true);
        tfAuthorID.setEditable(true);
        tfPublisherofBook.setEditable(true);
        tfSupplierID.setEditable(true);
        tfPrice.setEditable(true);
        tfQuantity.setEditable(true);
        tfShelf.setEditable(true);
        tfRowNo.setEditable(true);
        tfColumnNo.setEditable(true);
        cbCategoryID.setEnabled(true);
        String bookID = "BK-" + getRandomString();
        tfBookID.setText(bookID);
        status = evt.getActionCommand();
    }//GEN-LAST:event_btAdd1ActionPerformed

    private void btEdit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEdit2ActionPerformed
        // TODO add your handling code here:
        String authorID = tfAuthorID1.getText();
        String authorName = tfAuthorName.getText();
        Author b = new Author(authorID, authorName);
        Vector vt = b.toVector();
        int donghh = this.tbAuthorAdmin.getSelectedRow();
        AuthorList.update(donghh, b);
        dmAuthor.removeRow(donghh);
        dmAuthor.insertRow(donghh, vt);
    }//GEN-LAST:event_btEdit2ActionPerformed

    private void btDelete2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDelete2ActionPerformed
        // TODO add your handling code here:
        String authorId = tfAuthorID1.getText();
        int i = AuthorList.delete(authorId);
        dmAuthor.removeRow(i);
    }//GEN-LAST:event_btDelete2ActionPerformed

    private void tbAuthorAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAuthorAdminMouseClicked
        // TODO add your handling code here:
        int donghh = this.tbAuthorAdmin.getSelectedRow();
        Author b = AuthorList.getAuthor(donghh);
        this.viewAuthor(b);
        jMenuItemAuthor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection entry = new StringSelection(tfAuthorID1.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(entry, entry);
            }
        });
    }//GEN-LAST:event_tbAuthorAdminMouseClicked

    private void btClose1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClose1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btClose1ActionPerformed

    private void btAdd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdd2ActionPerformed
        // TODO add your handling code here:
        String authorID = "AT-" + getRandomString();
        String authorName = tfAuthorName.getText();
        Author b = new Author(authorID, authorName);
        Vector vt = b.toVector();
        AuthorList.add(b);
        dmAuthor.addRow(vt);
    }//GEN-LAST:event_btAdd2ActionPerformed

    private void btaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btaddActionPerformed
        String CategoryID = "CG-" + getRandomString();
        String CategoryName = tfcategoryname.getText();
        Category c = new Category(CategoryID, CategoryName);
        Vector vt = c.toVector();
        CategoryList.them(c);
        dmCategory.addRow(vt);
    }//GEN-LAST:event_btaddActionPerformed

    private void btupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btupdateActionPerformed
        String CategoryID = tfcategoryid.getText();
        String CategoryName = tfcategoryname.getText();
        Category c = new Category(CategoryID, CategoryName);
        Vector vt = c.toVector();
        int donghh = this.tbcategory.getSelectedRow();
        CategoryList.sua(donghh, c);
        dmCategory.removeRow(donghh);
        dmCategory.insertRow(donghh, vt);
    }//GEN-LAST:event_btupdateActionPerformed

    private void btdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btdeleteActionPerformed
        try {
            String RdID = tfcategoryid.getText();
            int i = CategoryList.xoa(RdID);
            dmCategory.removeRow(i);
        } catch (Exception ex) {
            Logger.getLogger(Form_Library.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btdeleteActionPerformed

    private void tbcategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbcategoryMouseClicked
        int donghh = this.tbcategory.getSelectedRow();
        Category t = CategoryList.getCategory(donghh);
        this.viewCategory(t);
        jMenuItemCate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection entry = new StringSelection(tfcategoryid.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(entry, entry);
            }
        });
    }//GEN-LAST:event_tbcategoryMouseClicked

    private void btEditPublisherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditPublisherActionPerformed
        String PublisherID = tfPublisherID.getText();
        String PublisherName = tfPublisherName.getText();
        Publisher b = new Publisher(PublisherID, PublisherName);
        Vector vt = b.toVector();
        int donghh = this.tbPublisher.getSelectedRow();
        PublisherList.update(donghh, b);
        dmPublisher.removeRow(donghh);
        dmPublisher.insertRow(donghh, vt);
    }//GEN-LAST:event_btEditPublisherActionPerformed

    private void btDeletePublisherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeletePublisherActionPerformed
        try {
            String PublisherID = tfPublisherID.getText();
            int i = PublisherList.delete(PublisherID);
            dmPublisher.removeRow(i);
        } catch (Exception ex) {
            Logger.getLogger(Form_Library.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btDeletePublisherActionPerformed

    private void tbPublisherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPublisherMouseClicked
        int donghh = this.tbPublisher.getSelectedRow();
        Publisher b = PublisherList.getPublisher(donghh);
        this.viewSupplier(b);
        jMenuItemPub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection entry = new StringSelection(tfPublisherID.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(entry, entry);
            }
        });

    }//GEN-LAST:event_tbPublisherMouseClicked

    private void btClose2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClose2ActionPerformed
        dispose();
    }//GEN-LAST:event_btClose2ActionPerformed

    private void btAddPublisherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddPublisherActionPerformed
        String PublisherID = "PL-" + getRandomString();
        String PublisherName = tfPublisherName.getText();

        Publisher c = new Publisher(PublisherID, PublisherName);
        Vector vt = c.toVector();
        PublisherList.add(c);
        dmPublisher.addRow(vt);
    }//GEN-LAST:event_btAddPublisherActionPerformed

    private void btEditSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditSupplierActionPerformed
        // TODO add your handling code here:
        String supplierID = tfSupplierID.getText();
        String supplierName = tfSupplierName.getText();
        String s_Address = tfAddressSuplier.getText();
        String s_Phone = ftfPhoneSupplier.getText();
        if (checkPhone(s_Phone)) {
            Supplier b = new Supplier(supplierID, supplierName, s_Address, s_Phone);
            Vector vt = b.toVector();
            int donghh = this.tbSupplierAdmin.getSelectedRow();
            SupplierList.update(donghh, b);
            dmSupplier.removeRow(donghh);
            dmSupplier.insertRow(donghh, vt);
        } else {
            JOptionPane.showMessageDialog(this, "Phone Number is not Valid!!!");
        }
    }//GEN-LAST:event_btEditSupplierActionPerformed

    private void btDeleteSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteSupplierActionPerformed
        // TODO add your handling code here:
        String supplierId = tfSupplierID1.getText();
        int i = SupplierList.delete(supplierId);
        dmSupplier.removeRow(i);
    }//GEN-LAST:event_btDeleteSupplierActionPerformed

    private void tbSupplierAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSupplierAdminMouseClicked
        // TODO add your handling code here:
        int donghh = this.tbSupplierAdmin.getSelectedRow();
        Supplier b = SupplierList.getSupplier(donghh);
        this.viewSupplier(b);
        jMenuItemSup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection entry = new StringSelection(tfSupplierID1.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(entry, entry);
            }
        });
    }//GEN-LAST:event_tbSupplierAdminMouseClicked

    private void btClose3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClose3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btClose3ActionPerformed

    private void btAddSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddSupplierActionPerformed

        String supplierID = "SP-" + getRandomString();
        String supplierName = tfSupplierName.getText();
        String s_Address = tfAddressSuplier.getText();
        String s_Phone = ftfPhoneSupplier.getText();
        if (checkPhone(s_Phone)) {
            Supplier b = new Supplier(supplierID, supplierName, s_Address, s_Phone);
            Vector vt = b.toVector();
            SupplierList.add(b);
            dmSupplier.addRow(vt);
        } else {
            JOptionPane.showMessageDialog(this, "Phone Number is not Valid!!!");
        }
    }//GEN-LAST:event_btAddSupplierActionPerformed

    private void jScrollPane6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane6MouseClicked

    private void btSearchBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSearchBookActionPerformed
        // TODO add your handling code here:
        this.BookList.getList().clear();
        this.dmBook.getDataVector().clear();
        String sql = "Select * from book where bookname like N'" + tfSearchBook.getText() + "%'";
        this.BookList.load(sql);
        for (Book b : BookList.getList()) {
            this.dmBook.addRow(b.toVector());
        }
        if (this.dmBook.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Not found!!");
        }
    }//GEN-LAST:event_btSearchBookActionPerformed

    private void btdelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btdelete1ActionPerformed
        dispose();
    }//GEN-LAST:event_btdelete1ActionPerformed

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
        dispose();
    }//GEN-LAST:event_btnSearch1ActionPerformed

    private void btDelete3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDelete3ActionPerformed
        dispose();
    }//GEN-LAST:event_btDelete3ActionPerformed

    private void btEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditActionPerformed
        String BrID = tfBorrowID.getText();
        String RdID = tfReaderID.getText();
        String BookID = tfBookBMID.getText();
        Date BorrowDate = carBorrowDate.getDate();
        Date ReturnDate = carReturnDate.getDate();
        BorrowingManagement r = new BorrowingManagement(BrID, RdID, BookID, BorrowDate, ReturnDate);
        Vector vt = r.toVector();
        int donghh = this.tbBorrowingManagement.getSelectedRow();
        try {
            BorrowingList.sua(donghh, r);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        dmBorrowing.removeRow(donghh);
        dmBorrowing.insertRow(donghh, vt);
//        disableCarlendarBorrow();

    }//GEN-LAST:event_btEditActionPerformed

    private void tbBorrowingManagementMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBorrowingManagementMouseClicked
        int donghh = this.tbBorrowingManagement.getSelectedRow();
        BorrowingManagement b = BorrowingList.getBorrowingManagement(donghh);
        this.viewBorrowing(b);
//        System.out.println(tbBorrowingManagement.getValueAt(donghh, 4).toString());

        jMenuItemBo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection entry = new StringSelection(tfBorrowID.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(entry, entry);
            }
        });
    }//GEN-LAST:event_tbBorrowingManagementMouseClicked

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        String BorrowID = "BR-" + getRandomString();
        String ReaderID = tfReaderID.getText();
        String BookID = tfBookBMID.getText();

        BorrowingManagement r = new BorrowingManagement(BorrowID, ReaderID, BookID, today, nextday);
        Vector vt = r.toVector();
        try {
            BorrowingList.them(r);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        dmBorrowing.addRow(vt);
    }//GEN-LAST:event_btAddActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        try {
            String BrID = tfBorrowID.getText();
            int i = BorrowingList.xoa(BrID);
            System.out.println(i);
            dmBorrowing.removeRow(i);
            System.out.println(i);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
//        disableCarlendarBorrow();
    }//GEN-LAST:event_btDeleteActionPerformed

    private void btSearchReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSearchReturnActionPerformed
        this.ReturnList.getReturnList().clear();
        this.dmReturn.getDataVector().clear();
        String RdID = tfSearchReturn.getText();
        ReturnList.load("select borrowingmanagement.BorrowID, borrowingmanagement.BookID, reader.RdName, book.BookName, author.AuthorName, publisher.PublisherName, book.Price, borrowingmanagement.BorrowDate, borrowingmanagement.ReturnDate\n"
                + "from borrowingmanagement\n"
                + "inner join book on borrowingmanagement.BookID = book.BookID\n"
                + "inner join author on book.AuthorID = author.AuthorID\n"
                + "inner join publisher on book.PublisherID = publisher.PublisherID\n"
                + "inner join reader on borrowingmanagement.RdID = reader.RdID\n"
                + "where borrowingmanagement.RdID = '" + RdID + "'");
        for (ReturnManagement c : ReturnList.getReturnList()) {
            dmReturn.addRow(c.toVector());
        }
        if (this.dmReturn.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Not found!!");
        }
    }//GEN-LAST:event_btSearchReturnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new ChangePassword().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btPrintStasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPrintStasActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser jfc = new JFileChooser("Save File");
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                String content = this.taBaoCao.getText();
                jfc.setDialogTitle("Save File");
                FileOutputStream fos = new FileOutputStream(jfc.getSelectedFile());
                fos.write(content.getBytes());
                fos.flush();
                fos.close();
                JOptionPane.showMessageDialog(null, "Save Successfully");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btPrintStasActionPerformed

    private void btPrintReaderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPrintReaderActionPerformed
        try {
            // TODO add your handling code here:
            FileOutputStream fos = new FileOutputStream(tfID.getText() + " - " + tfName.getText() + ".docx");
            XWPFDocument doc = new XWPFDocument();
            XWPFParagraph paraTit = doc.createParagraph();
            paraTit.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun paraTitRun = paraTit.createRun();
            paraTitRun.setBold(true);
            paraTitRun.setText("Reader Information");
            paraTitRun.setFontFamily("Times New Roman");
            paraTitRun.setFontSize(20);
            String content = this.taPrintReader.getText();
            XWPFRun paraTitRun2 = doc.createParagraph().createRun();
            paraTitRun2.setFontFamily("Times New Roman");
            paraTitRun2.setFontSize(16);
            if (content.contains("\n")) {
                String[] lines = content.split("\n");
                paraTitRun2.setText(lines[0], 0); // set first line into XWPFRun
                for (int i = 1; i < lines.length; i++) {
                    // add break and insert new text
                    paraTitRun2.addBreak();
                    paraTitRun2.setText(lines[i]);
                }
            } else {
                paraTitRun2.setText(content, 0);
            }
            doc.write(fos);
            fos.close();
            JOptionPane.showMessageDialog(null, "The document created successfully!!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btPrintReaderActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        new ChartGUI().setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed


    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        SendingEmail se = new SendingEmail();
        se.setVisible(true);
        class MyTask extends SwingWorker<Void, Void> {

            /**
             * Main task. Executed in background thread.
             */
            @Override
            public Void doInBackground() {
                Random random = new Random();
                int progress = 0;
                // Initialize progress property.
                setProgress(0);
                while (progress < 100) {
                    // Sleep for up to one second.
                    try {
                        Thread.sleep(random.nextInt(400));
                    } catch (InterruptedException ignore) {
                    }
                    // Make random progress.
                    progress += random.nextInt(10);
                    setProgress(Math.min(progress, 100));
                }
                return null;
            }

            /**
             * Executed in event dispatching thread
             */
            @Override
            public void done() {
                Toolkit.getDefaultToolkit().beep();
                se.getStartButton().setEnabled(true);
                setCursor(null); // turn off the wait cursor
                se.getTaskOutput().append("Done!\n");
            }

        }
        MyTask task = new MyTask();
        se.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                se.getStartButton().setEnabled(false);
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                // Instances of javax.swing.SwingWorker are not reusuable, so
                // we create new instances as needed.

                for (BorrowingManagement r : BorrowingList.getList()) {
                    dmBorrowing.addRow(r.toVector());
                    if (truNgayThang(r.getReturnDate()) < 4) {
                        String a = "maqlibrary@gmail.com";
                        String b = "minhanh8";
                        String x = "[MAQ Library] Thông báo nhắc nhở thời gian trả sách";
                        String y = null;
                        try {
                            y = "Kính gửi " + r.getRdName(r.getBorrowID()) + "\n\nChúng tôi rất hân hạnh khi được phục vụ quý độc giả. Qua email này, chúng tôi muốn nhắc nhở đến quý độc giả về thời hạn mượn sách chỉ còn " + truNgayThang(r.getReturnDate()) + " ngày. Kính mong quý độc giả thu xếp thời gian để trả sách.\n\nXin chân thành cảm ơn.\n\nEmail này được gửi tự động, quý độc giả vui lòng không trả lời lại email này. Mọi thắc mắc xin liên hệ số Hotline: 19001009.";
                            se.getTaskOutput().append("Sent to " + r.getRdName(r.getBorrowID()) + ": " + r.getRdEmail(r.getBorrowID()) + "\n");
                        } catch (Exception ex) {
                            Logger.getLogger(Form_Library.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        String to = r.getRdEmail(r.getBorrowID());
                        Gmail.sendFromGMail(a, b, to, x, y);
                        task.addPropertyChangeListener(new PropertyChangeListener() {
                            @Override
                            public void propertyChange(PropertyChangeEvent evt) {
                                if ("progress" == (evt.getPropertyName())) {
                                    int progress = (Integer) evt.getNewValue();
                                    se.getProgressBar().setValue(progress);

                                }
                            }
                        });
                    }
                }

                task.execute();
            }
        });
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        JFileChooser chooser = new JFileChooser();
        int k = chooser.showSaveDialog(this);
        if (k == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                FileWriter out = new FileWriter(file + ".xls");
                BufferedWriter bu = new BufferedWriter(out);
                DefaultTableModel model = (DefaultTableModel) tbBookAdmin.getModel();
                for (int i = 0; i < model.getColumnCount(); i++) {
                    bu.write(model.getColumnName(i) + "\t");
                }
                bu.write("\n");
                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        bu.write(model.getValueAt(i, j) + "\t");
                    }
                    bu.write("\n");
                }
                bu.close();
                JOptionPane.showMessageDialog(this, "Saved");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Can't Save");
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        // TODO add your handling code here:
        String bookID = tfBookID.getText();
        String bookName = tfBookName.getText();
        String authorID = tfAuthorID.getText();
        String pubID = tfPublisherofBook.getText();
        String supplierID = tfSupplierID.getText();
        String categoryID = this.cbCategoryID.getSelectedItem().toString();
        int price = Integer.parseInt(tfPrice.getText());
        int quantity = Integer.parseInt(tfQuantity.getText());
        String shelf = tfShelf.getText();
        int rowNum = Integer.parseInt(tfRowNo.getText());
        int colNum = Integer.parseInt(tfColumnNo.getText());
        String image = tfImage.getText();
        Book b = new Book(bookID, bookName, authorID, pubID, supplierID, categoryID, price, quantity, shelf, rowNum, colNum, image);
        Vector vt = b.toVector();
        if (status.equalsIgnoreCase("add")) {
            BookList.add(b);
            dmBook.addRow(vt);
        } else {
            int donghh = this.tbBookAdmin.getSelectedRow();
            BookList.update(donghh, b);
            dmBook.removeRow(donghh);
            dmBook.insertRow(donghh, vt);
        }
        status = "";
        tfBookName.setEditable(false);
        tfAuthorID.setEditable(false);
        tfPublisherofBook.setEditable(false);
        tfSupplierID.setEditable(false);
        tfPrice.setEditable(false);
        tfQuantity.setEditable(false);
        tfShelf.setEditable(false);
        tfRowNo.setEditable(false);
        tfColumnNo.setEditable(false);
        cbCategoryID.setEnabled(false);
        tfBookID.setText("");
        tfBookName.setText("");
        tfAuthorID.setText("");
        tfPublisherofBook.setText("");
        tfSupplierID.setText("");
        tfPrice.setText("");
        tfQuantity.setText("");
        tfShelf.setText("");
        tfRowNo.setText("");
        tfColumnNo.setText("");
        cbCategoryID.setSelectedIndex(0);
    }//GEN-LAST:event_btSaveActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jChooserExcel.showOpenDialog(null);
        File file = jChooserExcel.getSelectedFile();
        if (!file.getName().endsWith("xls")) {
            JOptionPane.showMessageDialog(null, "Please select only Excel file", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            fillData(file);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btSaveReaderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveReaderActionPerformed
        // TODO add your handling code here:
        String RdID = tfID.getText();
        String RdName = tfName.getText();
        String IDCardNumber = tfIDCardNumber.getText();
        String Sex = cbSex.getSelectedItem().toString();
        Date Birthday = carBirthday.getDate();
        String Address = tfAddress.getText();
        String Phone = tfPhone.getText();
        String Email = tfEmail.getText();
        Date ActivationDate = carActivationDate.getDate();
        Date ExpiredDate = carExpiredDate.getDate();
        Reader r = new Reader(RdID, RdName, IDCardNumber, Sex, Birthday, Address, Phone, Email, ActivationDate, ExpiredDate);
        Vector vt = r.toVector();
        if (status.equalsIgnoreCase("add")) {
            try {
                ReaderList.them(r);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
            dmReader.addRow(vt);
        } else {
            int donghh = this.tbReader.getSelectedRow();
            try {
                ReaderList.sua(donghh, r);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
            dmReader.removeRow(donghh);
            dmReader.insertRow(donghh, vt);
        }
        status = "";
        tfName.setEditable(false);
        tfIDCardNumber.setEditable(false);
        carBirthday.setEnabled(false);
        tfAddress.setEditable(false);
        tfPhone.setEditable(false);
        tfEmail.setEditable(false);
        cbSex.setEnabled(false);
        carActivationDate.setEnabled(false);
        carExpiredDate.setEnabled(false);
        tfID.setText("");
        tfName.setText("");
        tfIDCardNumber.setText("");
        tfAddress.setText("");
        tfPhone.setText("");
        tfEmail.setText("");
        setCarlendarReader();
        carBirthday.setDate(today);
        cbSex.setSelectedIndex(0);
    }//GEN-LAST:event_btSaveReaderActionPerformed

    private void btBrowseReaderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBrowseReaderActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        int n = chooser.showOpenDialog(this);
        if (n == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            this.tfImageLink.setText(f.getName());
            File t = null;
            if (!Files.isDirectory(Paths.get("directory"))) {
                new File("image").mkdir();
            }
            try {
                Files.copy(f.toPath(), new File("image/" + f.getName()).toPath());
            } catch (IOException ex) {
            }
//            this.lbImage.setIcon(new ImageIcon(new ImageIcon("image/" + f.getName()).getImage().getScaledInstance(lbImage.WIDTH, lbImage.HEIGHT, Image.SCALE_SMOOTH)));
            this.lbImageLink.setIcon(ReaderList.loadIcon("image/" + f.getName(), 200, 300));
        }
    }//GEN-LAST:event_btBrowseReaderActionPerformed

    private void tbReturnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbReturnMouseClicked

    }//GEN-LAST:event_tbReturnMouseClicked

    private void jPanel3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F5) {
            System.out.println("hello f4");
        }
    }//GEN-LAST:event_jPanel3KeyPressed

    private void jPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F5) {
            System.out.println("hello f4");
        }
    }//GEN-LAST:event_jPanel1KeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F5) {
            System.out.println("hello f4");
        }
    }//GEN-LAST:event_formKeyPressed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        this.ReturnList.getReturnList().clear();
        this.dmReturn.getDataVector().clear();
        ReturnList.load("select borrowingmanagement.BorrowID, borrowingmanagement.BookID, reader.RdName, book.BookName, author.AuthorName, publisher.PublisherName, book.Price, borrowingmanagement.BorrowDate, borrowingmanagement.ReturnDate\n"
                + "from borrowingmanagement\n"
                + "inner join book on borrowingmanagement.BookID = book.BookID\n"
                + "inner join author on book.AuthorID = author.AuthorID\n"
                + "inner join publisher on book.PublisherID = publisher.PublisherID\n"
                + "inner join reader on borrowingmanagement.RdID = reader.RdID");
        for (ReturnManagement c : ReturnList.getReturnList()) {
            dmReturn.addRow(c.toVector());
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        this.BorrowingList.getList().clear();
        this.dmBorrowing.getDataVector().clear();
        BorrowingList.load("select * from borrowingmanagement");
        for (BorrowingManagement c : BorrowingList.getList()) {
            dmBorrowing.addRow(c.toVector());
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        jTabbedPane1.setTitleAt(0, "Quản Lí Sách");
        jTabbedPane1.setTitleAt(1, "Quản Lí Bạn Đọc");
        jTabbedPane1.setTitleAt(2, "Quản Lí Mượn Và Trả");
        jTabbedPane1.setTitleAt(3, "Quản Lí Nhà Cung Cấp");
        jTabbedPane1.setTitleAt(4, "Thống Kê");
        jTabbedPane1.setTitleAt(5, "Trang Chủ");
        jTabbedPane2.setTitleAt(0, "Sách");
        jTabbedPane2.setTitleAt(1, "Thể Loại");
        jTabbedPane2.setTitleAt(2, "Tác Giả");
        jTabbedPane2.setTitleAt(0, "Nhà Xuất Bản");
        jTabbedPane3.setTitleAt(0, "Đang Mượn");
        jTabbedPane3.setTitleAt(1, "Đã Trả");
        Locale i = new Locale("vi","VN");
        ResourceBundle r = ResourceBundle.getBundle("resources/Bundle", i);
        jButton1.setText(r.getString("Form_Library.jButton1.text"));
        this.jButton2.setText(r.getString("Form_Library.jButton2.text"));
        this.jButton3.setText(r.getString("Form_Library.jButton3.text"));
        this.lbBookID.setText(r.getString("Form_Library.lbBookID.text"));
        this.lbBookName.setText(r.getString("Form_Library.lbBookName.text"));
        this.lbAuthorID.setText(r.getString("Form_Library.lbAuthorID.text"));
        this.lbSupplierID2.setText(r.getString("Form_Library.lbSupplierID2.text"));
        this.lbSupplierID.setText(r.getString("Form_Library.lbSupplierID.text"));
        this.lbCategoryID.setText(r.getString("Form_Library.lbCategoryID.text"));
        this.lbPrice.setText(r.getString("Form_Library.lbPrice.text"));
        this.lbQuantity.setText(r.getString("Form_Library.lbQuantity.text"));
        this.lbShelf.setText(r.getString("Form_Library.lbShelf.text"));
        this.lbRowNo.setText(r.getString("Form_Library.lbRowNo.text"));
        this.lbColumnNo.setText(r.getString("Form_Library.lbSupplierID.text"));
        this.btImage.setText(r.getString("Form_Library.btImage.text"));
        this.jButton5.setText(r.getString("Form_Library.jButton5.text"));
        this.btAdd1.setText(r.getString("Form_Library.btAdd1.text"));
        this.jButton4.setText(r.getString("Form_Library.jButton4.text"));
        this.btEdit1.setText(r.getString("Form_Library.btEdit1.text"));
        this.btDelete1.setText(r.getString("Form_Library.btDelete1.text"));
        this.btSave.setText(r.getString("Form_Library.btSave.text"));
        this.btClose.setText(r.getString("Form_Library.btClose.text"));
        this.btSearchBook.setText(r.getString("Form_Library.btSearchBook.text"));
        this.jLabel16.setText(r.getString("Form_Library.jLabel16.text"));
        this.jLabel17.setText(r.getString("Form_Library.jLabel17.text"));
        this.btadd.setText(r.getString("Form_Library.btadd.text"));
        this.btupdate.setText(r.getString("Form_Library.btupdate.text"));
        this.btdelete.setText(r.getString("Form_Library.jLabel17.text"));
        this.btdelete1.setText(r.getString("Form_Library.btdelete1.text"));
        this.lbAuthorID1.setText(r.getString("Form_Library.lbAuthorID1.text"));
        this.lbAuthorName.setText(r.getString("Form_Library.lbAuthorName.text"));
        this.btAdd2.setText(r.getString("Form_Library.btAdd2.text"));
        this.btEdit2.setText(r.getString("Form_Library.btEdit2.text"));
        this.btClose1.setText(r.getString("Form_Library.btClose1.text"));
        this.btDelete2.setText(r.getString("Form_Library.btDelete2.text"));
        this.lbAuthorID2.setText(r.getString("Form_Library.lbAuthorID2.text"));
        this.btAddPublisher.setText(r.getString("Form_Library.btAddPublisher.text"));
        this.btEditPublisher.setText(r.getString("Form_Library.btEditPublisher.text"));
        this.btClose2.setText(r.getString("Form_Library.btClose2.text"));
        this.jLabel6.setText(r.getString("Form_Library.jLabel6.text"));
        this.btDeletePublisher.setText(r.getString("Form_Library.btDeletePublisher.text"));
        
        this.jLabel7.setText(r.getString("Form_Library.jLabel7.text"));
        this.jLabel8.setText(r.getString("Form_Library.jLabel8.text"));
        this.jLabel9.setText(r.getString("Form_Library.jLabel9.text"));
        this.jLabel10.setText(r.getString("Form_Library.jLabel10.text"));
        this.jLabel11.setText(r.getString("Form_Library.jLabel11.text"));
        this.jLabel12.setText(r.getString("Form_Library.jLabel12.text"));
        this.jLabel13.setText(r.getString("Form_Library.jLabel13.text"));
        this.jLabel14.setText(r.getString("Form_Library.jLabel14.text"));
        this.jLabel15.setText(r.getString("Form_Library.jLabel15.text"));
        this.btnAdd.setText(r.getString("Form_Library.btnAdd.text"));
        this.btnEdit.setText(r.getString("Form_Library.btnEdit.text"));
        this.btnDelete.setText(r.getString("Form_Library.btnDelete.text"));
        this.btSaveReader.setText(r.getString("Form_Library.btDeletePublisher.text"));
        this.btnSearch.setText(r.getString("Form_Library.btnSearch.text"));
        this.btBrowseReader.setText(r.getString("Form_Library.btBrowseReader.text"));
        this.jLabel1.setText(r.getString("Form_Library.jLabel1.text"));
        this.jLabel5.setText(r.getString("Form_Library.jLabel5.text"));
        this.jLabel4.setText(r.getString("Form_Library.jLabel4.text"));
        this.jLabel2.setText(r.getString("Form_Library.jLabel2.text"));
        this.jLabel3.setText(r.getString("Form_Library.jLabel3.text"));
        this.btAdd.setText(r.getString("Form_Library.btAdd.text"));
        this.btEdit.setText(r.getString("Form_Library.btEdit.text"));
        this.btDelete.setText(r.getString("Form_Library.btDelete.text"));
        this.btDelete3.setText(r.getString("Form_Library.btDelete3.text"));
        this.jLabel18.setText(r.getString("Form_Library.jLabel18.text"));
        this.btSearchReturn.setText(r.getString("Form_Library.btSearchReturn.text"));
        this.lbSupplierID1.setText(r.getString("Form_Library.lbSupplierID1.text"));
        this.lbPhone.setText(r.getString("Form_Library.lbPhone.text"));
        this.lbSupplierName.setText(r.getString("Form_Library.lbSupplierName.text"));
        this.lbAddress.setText(r.getString("Form_Library.lbAddress.text"));
        this.btAddSupplier.setText(r.getString("Form_Library.btAddSupplier.text"));
        this.btEditSupplier.setText(r.getString("Form_Library.btEditSupplier.text"));
        this.btDeleteSupplier.setText(r.getString("Form_Library.btDeleteSupplier.text"));
        this.btClose3.setText(r.getString("Form_Library.btClose3.text"));
        this.jLabel21.setText(r.getString("Form_Library.jLabel21.text"));
        this.jLabel22.setText(r.getString("Form_Library.jLabel22.text"));
        this.btPrintStas.setText(r.getString("Form_Library.btPrintStas.text"));
        this.jButton7.setText(r.getString("Form_Library.jButton7.text"));
        this.jButton6.setText(r.getString("Form_Library.jButton6.text"));
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        jTabbedPane1.setTitleAt(0, "Book Management");
        jTabbedPane1.setTitleAt(1, "Reader Management");
        jTabbedPane1.setTitleAt(2, "Borrowing&Return Management");
        jTabbedPane1.setTitleAt(3, "Supplier Management");
        jTabbedPane1.setTitleAt(4, "Statistic");
        jTabbedPane1.setTitleAt(5, "Home");
        jTabbedPane2.setTitleAt(0, "Book");
        jTabbedPane2.setTitleAt(1, "Category");
        jTabbedPane2.setTitleAt(2, "Author");
        jTabbedPane2.setTitleAt(0, "Publisher");
        jTabbedPane3.setTitleAt(0, "Borrowing");
        jTabbedPane3.setTitleAt(1, "Return");
        Locale i = new Locale("en","US");
        ResourceBundle r = ResourceBundle.getBundle("resources/Bundle", i);
        jButton1.setText(r.getString("Form_Library.jButton1.text"));
        this.jButton2.setText(r.getString("Form_Library.jButton2.text"));
        this.jButton3.setText(r.getString("Form_Library.jButton3.text"));
        this.lbBookID.setText(r.getString("Form_Library.lbBookID.text"));
        this.lbBookName.setText(r.getString("Form_Library.lbBookName.text"));
        this.lbAuthorID.setText(r.getString("Form_Library.lbAuthorID.text"));
        this.lbSupplierID2.setText(r.getString("Form_Library.lbSupplierID2.text"));
        this.lbSupplierID.setText(r.getString("Form_Library.lbSupplierID.text"));
        this.lbCategoryID.setText(r.getString("Form_Library.lbCategoryID.text"));
        this.lbPrice.setText(r.getString("Form_Library.lbPrice.text"));
        this.lbQuantity.setText(r.getString("Form_Library.lbQuantity.text"));
        this.lbShelf.setText(r.getString("Form_Library.lbShelf.text"));
        this.lbRowNo.setText(r.getString("Form_Library.lbRowNo.text"));
        this.lbColumnNo.setText(r.getString("Form_Library.lbSupplierID.text"));
        this.btImage.setText(r.getString("Form_Library.btImage.text"));
        this.jButton5.setText(r.getString("Form_Library.jButton5.text"));
        this.btAdd1.setText(r.getString("Form_Library.btAdd1.text"));
        this.jButton4.setText(r.getString("Form_Library.jButton4.text"));
        this.btEdit1.setText(r.getString("Form_Library.btEdit1.text"));
        this.btDelete1.setText(r.getString("Form_Library.btDelete1.text"));
        this.btSave.setText(r.getString("Form_Library.btSave.text"));
        this.btClose.setText(r.getString("Form_Library.btClose.text"));
        this.btSearchBook.setText(r.getString("Form_Library.btSearchBook.text"));
        this.jLabel16.setText(r.getString("Form_Library.jLabel16.text"));
        this.jLabel17.setText(r.getString("Form_Library.jLabel17.text"));
        this.btadd.setText(r.getString("Form_Library.btadd.text"));
        this.btupdate.setText(r.getString("Form_Library.btupdate.text"));
        this.btdelete.setText(r.getString("Form_Library.jLabel17.text"));
        this.btdelete1.setText(r.getString("Form_Library.btdelete1.text"));
        this.lbAuthorID1.setText(r.getString("Form_Library.lbAuthorID1.text"));
        this.lbAuthorName.setText(r.getString("Form_Library.lbAuthorName.text"));
        this.btAdd2.setText(r.getString("Form_Library.btAdd2.text"));
        this.btEdit2.setText(r.getString("Form_Library.btEdit2.text"));
        this.btClose1.setText(r.getString("Form_Library.btClose1.text"));
        this.btDelete2.setText(r.getString("Form_Library.btDelete2.text"));
        this.lbAuthorID2.setText(r.getString("Form_Library.lbAuthorID2.text"));
        this.btAddPublisher.setText(r.getString("Form_Library.btAddPublisher.text"));
        this.btEditPublisher.setText(r.getString("Form_Library.btEditPublisher.text"));
        this.btClose2.setText(r.getString("Form_Library.btClose2.text"));
        this.jLabel6.setText(r.getString("Form_Library.jLabel6.text"));
        this.btDeletePublisher.setText(r.getString("Form_Library.btDeletePublisher.text"));
        
        this.jLabel7.setText(r.getString("Form_Library.jLabel7.text"));
        this.jLabel8.setText(r.getString("Form_Library.jLabel8.text"));
        this.jLabel9.setText(r.getString("Form_Library.jLabel9.text"));
        this.jLabel10.setText(r.getString("Form_Library.jLabel10.text"));
        this.jLabel11.setText(r.getString("Form_Library.jLabel11.text"));
        this.jLabel12.setText(r.getString("Form_Library.jLabel12.text"));
        this.jLabel13.setText(r.getString("Form_Library.jLabel13.text"));
        this.jLabel14.setText(r.getString("Form_Library.jLabel14.text"));
        this.jLabel15.setText(r.getString("Form_Library.jLabel15.text"));
        this.btnAdd.setText(r.getString("Form_Library.btnAdd.text"));
        this.btnEdit.setText(r.getString("Form_Library.btnEdit.text"));
        this.btnDelete.setText(r.getString("Form_Library.btnDelete.text"));
        this.btSaveReader.setText(r.getString("Form_Library.btDeletePublisher.text"));
        this.btnSearch.setText(r.getString("Form_Library.btnSearch.text"));
        this.btBrowseReader.setText(r.getString("Form_Library.btBrowseReader.text"));
        this.jLabel1.setText(r.getString("Form_Library.jLabel1.text"));
        this.jLabel5.setText(r.getString("Form_Library.jLabel5.text"));
        this.jLabel4.setText(r.getString("Form_Library.jLabel4.text"));
        this.jLabel2.setText(r.getString("Form_Library.jLabel2.text"));
        this.jLabel3.setText(r.getString("Form_Library.jLabel3.text"));
        this.btAdd.setText(r.getString("Form_Library.btAdd.text"));
        this.btEdit.setText(r.getString("Form_Library.btEdit.text"));
        this.btDelete.setText(r.getString("Form_Library.btDelete.text"));
        this.btDelete3.setText(r.getString("Form_Library.btDelete3.text"));
        this.jLabel18.setText(r.getString("Form_Library.jLabel18.text"));
        this.btSearchReturn.setText(r.getString("Form_Library.btSearchReturn.text"));
        this.lbSupplierID1.setText(r.getString("Form_Library.lbSupplierID1.text"));
        this.lbPhone.setText(r.getString("Form_Library.lbPhone.text"));
        this.lbSupplierName.setText(r.getString("Form_Library.lbSupplierName.text"));
        this.lbAddress.setText(r.getString("Form_Library.lbAddress.text"));
        this.btAddSupplier.setText(r.getString("Form_Library.btAddSupplier.text"));
        this.btEditSupplier.setText(r.getString("Form_Library.btEditSupplier.text"));
        this.btDeleteSupplier.setText(r.getString("Form_Library.btDeleteSupplier.text"));
        this.btClose3.setText(r.getString("Form_Library.btClose3.text"));
        this.jLabel21.setText(r.getString("Form_Library.jLabel21.text"));
        this.jLabel22.setText(r.getString("Form_Library.jLabel22.text"));
        this.btPrintStas.setText(r.getString("Form_Library.btPrintStas.text"));
        this.jButton7.setText(r.getString("Form_Library.jButton7.text"));
        this.jButton6.setText(r.getString("Form_Library.jButton6.text"));
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
         jTabbedPane1.setTitleAt(0, "管理洋書");
        jTabbedPane1.setTitleAt(1, "リダの管理");
        jTabbedPane1.setTitleAt(2, "借りを返し管理");
        jTabbedPane1.setTitleAt(3, "サプライヤ管理");
        jTabbedPane1.setTitleAt(4, "統計的");
        jTabbedPane1.setTitleAt(5, "ホーム");
        jTabbedPane2.setTitleAt(0, "本");
        jTabbedPane2.setTitleAt(1, "カテゴリ");
        jTabbedPane2.setTitleAt(2, "著者");
        jTabbedPane2.setTitleAt(0, "出版社");
        jTabbedPane3.setTitleAt(0, "借り入れ");
        jTabbedPane3.setTitleAt(1, "復帰");
        Locale l = new Locale("ja","JP");
        ResourceBundle r = ResourceBundle.getBundle("resources/Bundle", l);
        jButton1.setText(r.getString("Form_Library.jButton1.text"));
        this.jButton2.setText(r.getString("Form_Library.jButton2.text"));
        this.jButton3.setText(r.getString("Form_Library.jButton3.text"));
        this.lbBookID.setText(r.getString("Form_Library.lbBookID.text"));
        this.lbBookName.setText(r.getString("Form_Library.lbBookName.text"));
        this.lbAuthorID.setText(r.getString("Form_Library.lbAuthorID.text"));
        this.lbSupplierID2.setText(r.getString("Form_Library.lbSupplierID2.text"));
        this.lbSupplierID.setText(r.getString("Form_Library.lbSupplierID.text"));
        this.lbCategoryID.setText(r.getString("Form_Library.lbCategoryID.text"));
        this.lbPrice.setText(r.getString("Form_Library.lbPrice.text"));
        this.lbQuantity.setText(r.getString("Form_Library.lbQuantity.text"));
        this.lbShelf.setText(r.getString("Form_Library.lbShelf.text"));
        this.lbRowNo.setText(r.getString("Form_Library.lbRowNo.text"));
        this.lbColumnNo.setText(r.getString("Form_Library.lbSupplierID.text"));
        this.btImage.setText(r.getString("Form_Library.btImage.text"));
        this.jButton5.setText(r.getString("Form_Library.jButton5.text"));
        this.btAdd1.setText(r.getString("Form_Library.btAdd1.text"));
        this.jButton4.setText(r.getString("Form_Library.jButton4.text"));
        this.btEdit1.setText(r.getString("Form_Library.btEdit1.text"));
        this.btDelete1.setText(r.getString("Form_Library.btDelete1.text"));
        this.btSave.setText(r.getString("Form_Library.btSave.text"));
        this.btClose.setText(r.getString("Form_Library.btClose.text"));
        this.btSearchBook.setText(r.getString("Form_Library.btSearchBook.text"));
        this.jLabel16.setText(r.getString("Form_Library.jLabel16.text"));
        this.jLabel17.setText(r.getString("Form_Library.jLabel17.text"));
        this.btadd.setText(r.getString("Form_Library.btadd.text"));
        this.btupdate.setText(r.getString("Form_Library.btupdate.text"));
        this.btdelete.setText(r.getString("Form_Library.jLabel17.text"));
        this.btdelete1.setText(r.getString("Form_Library.btdelete1.text"));
        this.lbAuthorID1.setText(r.getString("Form_Library.lbAuthorID1.text"));
        this.lbAuthorName.setText(r.getString("Form_Library.lbAuthorName.text"));
        this.btAdd2.setText(r.getString("Form_Library.btAdd2.text"));
        this.btEdit2.setText(r.getString("Form_Library.btEdit2.text"));
        this.btClose1.setText(r.getString("Form_Library.btClose1.text"));
        this.btDelete2.setText(r.getString("Form_Library.btDelete2.text"));
        this.lbAuthorID2.setText(r.getString("Form_Library.lbAuthorID2.text"));
        this.btAddPublisher.setText(r.getString("Form_Library.btAddPublisher.text"));
        this.btEditPublisher.setText(r.getString("Form_Library.btEditPublisher.text"));
        this.btClose2.setText(r.getString("Form_Library.btClose2.text"));
        this.jLabel6.setText(r.getString("Form_Library.jLabel6.text"));
        this.btDeletePublisher.setText(r.getString("Form_Library.btDeletePublisher.text"));
        
        this.jLabel7.setText(r.getString("Form_Library.jLabel7.text"));
        this.jLabel8.setText(r.getString("Form_Library.jLabel8.text"));
        this.jLabel9.setText(r.getString("Form_Library.jLabel9.text"));
        this.jLabel10.setText(r.getString("Form_Library.jLabel10.text"));
        this.jLabel11.setText(r.getString("Form_Library.jLabel11.text"));
        this.jLabel12.setText(r.getString("Form_Library.jLabel12.text"));
        this.jLabel13.setText(r.getString("Form_Library.jLabel13.text"));
        this.jLabel14.setText(r.getString("Form_Library.jLabel14.text"));
        this.jLabel15.setText(r.getString("Form_Library.jLabel15.text"));
        this.btnAdd.setText(r.getString("Form_Library.btnAdd.text"));
        this.btnEdit.setText(r.getString("Form_Library.btnEdit.text"));
        this.btnDelete.setText(r.getString("Form_Library.btnDelete.text"));
        this.btSaveReader.setText(r.getString("Form_Library.btDeletePublisher.text"));
        this.btnSearch.setText(r.getString("Form_Library.btnSearch.text"));
        this.btBrowseReader.setText(r.getString("Form_Library.btBrowseReader.text"));
        this.jLabel1.setText(r.getString("Form_Library.jLabel1.text"));
        this.jLabel5.setText(r.getString("Form_Library.jLabel5.text"));
        this.jLabel4.setText(r.getString("Form_Library.jLabel4.text"));
        this.jLabel2.setText(r.getString("Form_Library.jLabel2.text"));
        this.jLabel3.setText(r.getString("Form_Library.jLabel3.text"));
        this.btAdd.setText(r.getString("Form_Library.btAdd.text"));
        this.btEdit.setText(r.getString("Form_Library.btEdit.text"));
        this.btDelete.setText(r.getString("Form_Library.btDelete.text"));
        this.btDelete3.setText(r.getString("Form_Library.btDelete3.text"));
        this.jLabel18.setText(r.getString("Form_Library.jLabel18.text"));
        this.btSearchReturn.setText(r.getString("Form_Library.btSearchReturn.text"));
        this.lbSupplierID1.setText(r.getString("Form_Library.lbSupplierID1.text"));
        this.lbPhone.setText(r.getString("Form_Library.lbPhone.text"));
        this.lbSupplierName.setText(r.getString("Form_Library.lbSupplierName.text"));
        this.lbAddress.setText(r.getString("Form_Library.lbAddress.text"));
        this.btAddSupplier.setText(r.getString("Form_Library.btAddSupplier.text"));
        this.btEditSupplier.setText(r.getString("Form_Library.btEditSupplier.text"));
        this.btDeleteSupplier.setText(r.getString("Form_Library.btDeleteSupplier.text"));
        this.btClose3.setText(r.getString("Form_Library.btClose3.text"));
        this.jLabel21.setText(r.getString("Form_Library.jLabel21.text"));
        this.jLabel22.setText(r.getString("Form_Library.jLabel22.text"));
        this.btPrintStas.setText(r.getString("Form_Library.btPrintStas.text"));
        this.jButton7.setText(r.getString("Form_Library.jButton7.text"));
        this.jButton6.setText(r.getString("Form_Library.jButton6.text"));
    }//GEN-LAST:event_jButton12ActionPerformed
   private void loadInternational(Locale locale) {
        String NAME_RESOURCES = "MessageBundle";
        ResourceBundle r = ResourceBundle.getBundle("resources/" + NAME_RESOURCES, locale);
//        this.jButton1.setText(r.getString("jButton1"));
//        this.jButton2.setText(r.getString("jButton2"));
//        this.jButton3.setText(r.getString("jButton3"));
//        this.lbBookID.setText(r.getString("lbBookID"));
//        this.lbBookName.setText(r.getString("lbBookName"));
//        this.lbAuthorID.setText(r.getString("lbAuthorID"));
//        this.lbSupplierID2.setText(r.getString("lbSupplierID2"));
//        this.lbSupplierID.setText(r.getString("lbSupplierID"));
//        this.lbCategoryID.setText(r.getString("lbCategoryID"));
//        this.lbPrice.setText(r.getString("lbPrice"));
//        this.lbQuantity.setText(r.getString("lbQuantity"));
//        this.lbShelf.setText(r.getString("lbShelf"));
//        this.lbRowNo.setText(r.getString("lbRowNo"));
//        this.lbColumnNo.setText(r.getString("lbSupplierID"));
//        this.btImage.setText(r.getString("btImage"));
//        this.jButton5.setText(r.getString("jButton5"));
//        this.btAdd1.setText(r.getString("btAdd1"));
//        this.jButton4.setText(r.getString("jButton4"));
//        this.btEdit1.setText(r.getString("btEdit1"));
//        this.btDelete1.setText(r.getString("btDelete1"));
//        this.btSave.setText(r.getString("btSave"));
//        this.btClose.setText(r.getString("btClose"));
//        this.btSearchBook.setText(r.getString("btSearchBook"));
//        this.jLabel16.setText(r.getString("jLabel16"));
//        this.jLabel17.setText(r.getString("jLabel17"));
//        this.btadd.setText(r.getString("btadd"));
//        this.btupdate.setText(r.getString("btupdate"));
//        this.btdelete.setText(r.getString("jLabel17"));
//        this.btdelete1.setText(r.getString("btdelete1"));
//        this.lbAuthorID1.setText(r.getString("lbAuthorID1"));
//        this.lbAuthorName.setText(r.getString("lbAuthorName"));
//        this.btAdd2.setText(r.getString("btAdd2"));
//        this.btEdit2.setText(r.getString("btEdit2"));
//        this.btClose1.setText(r.getString("btClose1"));
//        this.btDelete2.setText(r.getString("btDelete2"));
//        this.lbAuthorID2.setText(r.getString("lbAuthorID2"));
//        this.btAddPublisher.setText(r.getString("btAddPublisher"));
//        this.btEditPublisher.setText(r.getString("btEditPublisher"));
//        this.btClose2.setText(r.getString("btClose2"));
//        this.jLabel6.setText(r.getString("jLabel6"));
//        this.btDeletePublisher.setText(r.getString("btDeletePublisher"));
//        this.jLabel7.setText(r.getString("jLabel7"));
//        this.jLabel8.setText(r.getString("jLabel8"));
//        this.jLabel9.setText(r.getString("jLabel9"));
//        this.jLabel10.setText(r.getString("jLabel10"));
//        this.jLabel11.setText(r.getString("jLabel11"));
//        this.jLabel12.setText(r.getString("jLabel12"));
//        this.jLabel13.setText(r.getString("jLabel13"));
//        this.jLabel14.setText(r.getString("jLabel14"));
//        this.jLabel15.setText(r.getString("jLabel15"));
//        this.btnAdd.setText(r.getString("btnAdd"));
//        this.btnEdit.setText(r.getString("btnEdit"));
//        this.btnDelete.setText(r.getString("btnDelete"));
//        this.btSaveReader.setText(r.getString("btDeletePublisher"));
//        this.btnSearch.setText(r.getString("btnSearch"));
//        this.btBrowseReader.setText(r.getString("btBrowseReader"));
//        this.jLabel1.setText(r.getString("jLabel1"));
//        this.jLabel5.setText(r.getString("jLabel5"));
//        this.jLabel4.setText(r.getString("jLabel4"));
//        this.jLabel2.setText(r.getString("jLabel2"));
//        this.jLabel3.setText(r.getString("jLabel3"));
//        this.btAdd.setText(r.getString("btAdd"));
//        this.btEdit.setText(r.getString("btEdit"));
//        this.btDelete.setText(r.getString("btDelete"));
//        this.btDelete3.setText(r.getString("btDelete3"));
//        this.jLabel18.setText(r.getString("jLabel18"));
//        this.btSearchReturn.setText(r.getString("btSearchReturn"));
//        this.lbSupplierID1.setText(r.getString("lbSupplierID1"));
//        this.lbPhone.setText(r.getString("lbPhone"));
//        this.lbSupplierName.setText(r.getString("lbSupplierName"));
//        this.lbAddress.setText(r.getString("lbAddress"));
//        this.btAddSupplier.setText(r.getString("btAddSupplier"));
//        this.btEditSupplier.setText(r.getString("btEditSupplier"));
//        this.btDeleteSupplier.setText(r.getString("btDeleteSupplier"));
//        this.btClose3.setText(r.getString("btClose3"));
//        this.jLabel21.setText(r.getString("jLabel21"));
//        this.jLabel22.setText(r.getString("jLabel22"));
//        this.btPrintStas.setText(r.getString("btPrintStas"));
//        this.jButton7.setText(r.getString("jButton7"));
//        this.jButton6.setText(r.getString("jButton6"));
//        this.lbCPass.setText(r.getString("lbCPass"));
//        this.lbUserName.setText(r.getString("lbUserName"));
//        this.lbNPass.setText(r.getString("lbNPass"));
//        this.lbCNPass.setText(r.getString("lbCNPass"));
//        this.btChangePass.setText(r.getString("btChangePass"));
//        this.btCancelChange.setText(r.getString("btCancelChange"));
//        this.btArea1.setText(r.getString("btArea1"));
//        this.btBar1.setText(r.getString("btBar1"));
//        this.btArea2.setText(r.getString("btArea2"));
//        this.btBar2.setText(r.getString("btBar2"));
//        this.btLine2.setText(r.getString("btLine2"));
//        this.lbUserName1.setText(r.getString("lbUserName1"));
//        this.lbPassword.setText(r.getString("lbPassword"));
//        this.btLogin.setText(r.getString("btLogin"));
//        this.btCancel.setText(r.getString("btCancel"));
//        this.lb1.setText(r.getString("lb1"));
//        this.startButton.setText(r.getString("startButton"));
//        this.Button2.setText(r.getString("Button2"));
        
        
        
        
        
    }
    private void viewBook(Book b) {
        this.tfBookID.setText(b.getBookID());
        this.tfBookName.setText(b.getBookName());
        this.tfAuthorID.setText(b.getAuthorID());
        this.tfPublisherofBook.setText(b.getPublisherID());
        this.tfSupplierID.setText(b.getSupplierID());
        this.cbCategoryID.setSelectedItem(b.getCategoryID());
        this.tfPrice.setText(String.valueOf(b.getPrice()));
        this.tfQuantity.setText(String.valueOf(b.getQuantity()));
        this.tfShelf.setText(b.getShelf());
        this.tfRowNo.setText(String.valueOf(b.getRowNum()));
        this.tfColumnNo.setText(String.valueOf(b.getColNum()));
        this.tfImage.setText(b.getImage());
        this.lbImage.setIcon(BookList.loadIcon("image/" + b.getImage(), 410, 600));
    }

    private boolean checkPhone(String str) {
        if (str.matches("\\d{10,20}")) {
            return true;
        }
        return false;
    }

    private void viewSupplier(Supplier b) {
        this.tfSupplierID1.setText(b.getSupplierID());
        this.tfSupplierName.setText(b.getSupplierName());
        this.ftfPhoneSupplier.setText(b.getS_Phone());
        this.tfAddressSuplier.setText(b.getS_Address());
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
            UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form_Library.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Library.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Library.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Library.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Library().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdd;
    private javax.swing.JButton btAdd1;
    private javax.swing.JButton btAdd2;
    private javax.swing.JButton btAddPublisher;
    private javax.swing.JButton btAddSupplier;
    private javax.swing.JButton btBrowseReader;
    private javax.swing.JButton btClose;
    private javax.swing.JButton btClose1;
    private javax.swing.JButton btClose2;
    private javax.swing.JButton btClose3;
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btDelete1;
    private javax.swing.JButton btDelete2;
    private javax.swing.JButton btDelete3;
    private javax.swing.JButton btDeletePublisher;
    private javax.swing.JButton btDeleteSupplier;
    private javax.swing.JButton btEdit;
    private javax.swing.JButton btEdit1;
    private javax.swing.JButton btEdit2;
    private javax.swing.JButton btEditPublisher;
    private javax.swing.JButton btEditSupplier;
    private javax.swing.JButton btImage;
    private javax.swing.JButton btPrintReader;
    private javax.swing.JButton btPrintStas;
    private javax.swing.JButton btSave;
    private javax.swing.JButton btSaveReader;
    private javax.swing.JButton btSearchBook;
    private javax.swing.JButton btSearchReturn;
    private javax.swing.JButton btadd;
    private javax.swing.JButton btdelete;
    private javax.swing.JButton btdelete1;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btupdate;
    private com.toedter.calendar.JDateChooser carActivationDate;
    private com.toedter.calendar.JDateChooser carBirthday;
    private com.toedter.calendar.JDateChooser carBorrowDate;
    private com.toedter.calendar.JDateChooser carExpiredDate;
    private com.toedter.calendar.JDateChooser carReturnDate;
    private javax.swing.JComboBox<String> cbCategoryID;
    private javax.swing.JComboBox<String> cbSex;
    private javax.swing.JFormattedTextField ftfPhoneSupplier;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel lbAddress;
    private javax.swing.JLabel lbAuthorID;
    private javax.swing.JLabel lbAuthorID1;
    private javax.swing.JLabel lbAuthorID2;
    private javax.swing.JLabel lbAuthorName;
    private javax.swing.JLabel lbAuthorName1;
    private javax.swing.JLabel lbBookID;
    private javax.swing.JLabel lbBookName;
    private javax.swing.JLabel lbCategoryID;
    private javax.swing.JLabel lbColumnNo;
    private javax.swing.JLabel lbImage;
    private javax.swing.JLabel lbImageLink;
    private javax.swing.JLabel lbPhone;
    private javax.swing.JLabel lbPrice;
    private javax.swing.JLabel lbQuantity;
    private javax.swing.JLabel lbRowNo;
    private javax.swing.JLabel lbShelf;
    private javax.swing.JLabel lbSupplierID;
    private javax.swing.JLabel lbSupplierID1;
    private javax.swing.JLabel lbSupplierID2;
    private javax.swing.JLabel lbSupplierName;
    private javax.swing.JLabel lbTongKhach;
    private javax.swing.JLabel lbTongKhachMuon;
    private javax.swing.JLabel lbTongPhieu;
    private javax.swing.JLabel lbTongPhieuQuaHan;
    private javax.swing.JLabel lbTongSach;
    private javax.swing.JTextArea taBaoCao;
    private javax.swing.JTextArea taPrintReader;
    private javax.swing.JTable tbAuthorAdmin;
    private javax.swing.JTable tbBookAdmin;
    private javax.swing.JTable tbBorrowingManagement;
    private javax.swing.JTable tbPhieuQuaHan;
    private javax.swing.JTable tbPublisher;
    private javax.swing.JTable tbReader;
    private javax.swing.JTable tbReturn;
    private javax.swing.JTable tbSupplierAdmin;
    private javax.swing.JTable tbcategory;
    private javax.swing.JTextField tfAddress;
    private javax.swing.JTextField tfAddressSuplier;
    private javax.swing.JTextField tfAuthorID;
    private javax.swing.JTextField tfAuthorID1;
    private javax.swing.JTextField tfAuthorName;
    private javax.swing.JTextField tfBookBMID;
    private javax.swing.JTextField tfBookID;
    private javax.swing.JTextField tfBookName;
    private javax.swing.JTextField tfBorrowID;
    private javax.swing.JTextField tfColumnNo;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfID;
    private javax.swing.JTextField tfIDCardNumber;
    private javax.swing.JTextField tfImage;
    private javax.swing.JTextField tfImageLink;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfPhone;
    private javax.swing.JTextField tfPrice;
    private javax.swing.JTextField tfPublisherID;
    private javax.swing.JTextField tfPublisherName;
    private javax.swing.JTextField tfPublisherofBook;
    private javax.swing.JTextField tfQuantity;
    private javax.swing.JTextField tfReaderID;
    private javax.swing.JTextField tfRowNo;
    private javax.swing.JTextField tfSearch;
    private javax.swing.JTextField tfSearchBook;
    private javax.swing.JTextField tfSearchReturn;
    private javax.swing.JTextField tfShelf;
    private javax.swing.JTextField tfSupplierID;
    private javax.swing.JTextField tfSupplierID1;
    private javax.swing.JTextField tfSupplierName;
    private javax.swing.JTextField tfcategoryid;
    private javax.swing.JTextField tfcategoryname;
    // End of variables declaration//GEN-END:variables

    private void setCarlendarBorrow() {
        carBorrowDate.setDate(today2);
        carReturnDate.setDate(nextday);
    }
//    private void disableCarlendarBorrow() {
//        carBorrowDate.setDate(today2);
//        carReturnDate.setDate(nextday);
////        carBorrowDate.setEnabled(false);
////        carReturnDate.setEnabled(false);
//    }

    private void setCarlendarReader() {
        carActivationDate.setDate(today);
        carExpiredDate.setDate(nextyear);
//        carActivationDate.setEnabled(false);
//        carExpiredDate.setEnabled(false);
    }

    private void viewAuthor(Author b) {
        this.tfAuthorID1.setText(b.getAuthorID());
        this.tfAuthorName.setText(b.getAuthorName());
    }

    private void viewCategory(Category t) {
        this.tfcategoryid.setText(t.getCategoryID());
        this.tfcategoryname.setText(t.getCategoryName());
    }

    private void viewReader(Reader t) {
        this.tfID.setText(t.getRdID());
        this.tfName.setText(t.getRdName());
        this.tfIDCardNumber.setText(t.getIDCardNumber());
        if (t.getSex().equalsIgnoreCase("Male")) {
            cbSex.setSelectedItem("Male");
        } else if (t.getSex().equalsIgnoreCase("Female")) {
            cbSex.setSelectedItem("Female".toString());
        }
        this.carBirthday.setDate(t.getBirthday());
        this.tfAddress.setText(t.getAddress());
        this.tfPhone.setText(t.getPhone());
        this.tfEmail.setText(t.getEmail());
//        this.carActivationDate.setDate(t.getActivationDate());
//        this.carExpiredDate.setDate(t.getExpiredDate());
    }

    private void viewSupplier(Publisher b) {
        this.tfPublisherID.setText(b.getPublisherID());
        this.tfPublisherName.setText(b.getPublisherName());
    }

    public static String getRandomString() {

        String ketqua = "";
        String hoa = "QWERTYUIOPASDFGHJKLZXCVBNM";
//        String thuong = hoa.toLowerCase();
        String so = "1234567890";
        String randomchuoi = hoa + so;
        for (int i = 0; i < 5; i++) {
            int temp = (int) Math.round(Math.random() * randomchuoi.length());
            ketqua += randomchuoi.charAt(temp);
        }
        return ketqua;
    }

    //lấy hôm nay trừ ngày a
    private long truNgayThang(Date a) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        Date today = new Date();

        try {
            c1.setTime(df.parse(df.format(a)));
            c2.setTime(df.parse(df.format(today)));
        } catch (ParseException ex) {
            Logger.getLogger(ReturnManagement.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Công thức tính số ngày giữa 2 mốc thời gian:
        long noDay = (c2.getTime().getTime() - c1.getTime().getTime())
                / (24 * 3600 * 1000);
        if (noDay > 0) {
            return noDay;
        } else {
            return 0;
        }
    }

    private void viewBorrowing(BorrowingManagement b) {
        this.tfBorrowID.setText(String.valueOf(b.getBorrowID()));
        this.tfReaderID.setText(b.getRdID());
        this.tfBookBMID.setText(b.getBookID());
//        this.carBorrowDate.setDate(b.getBorrowDate());
//        this.carReturnDate.setDate(b.getReturnDate());
    }

    public static void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    private void fillData(File file) {
        Workbook workbook = null;
        try {
            try {
                workbook = Workbook.getWorkbook(file);
            } catch (IOException ex) {
                Logger.getLogger(Form_Library.class.getName()).log(Level.SEVERE, null, ex);
            }
            Sheet sheet = workbook.getSheet(0);

            ArrayList<Integer> trung = new ArrayList();
            TreeSet<Integer> kotrung = new TreeSet();
            for (int j = 1; j < sheet.getRows(); j++) {
                if (!sheet.getCell(0, j).getContents().startsWith("BK-")) {
                    break;
                }
                for (int k = 0; k < dmBook.getRowCount(); k++) {
                    if (sheet.getCell(0, j).getContents().equalsIgnoreCase(dmBook.getValueAt(k, 0).toString())) {
                        dmBook.setValueAt(Integer.parseInt((Integer.parseInt(dmBook.getValueAt(k, 7).toString()) + Integer.parseInt(sheet.getCell(7, j).getContents())) + ""), k, 7);
                        BookList.updateQuantity(Integer.parseInt(dmBook.getValueAt(k, 7).toString()), dmBook.getValueAt(k, 0).toString());
                        trung.add(j);
                        System.out.println("====" + j);
                        break;
                    } else {
                        System.out.println("====" + j);
                        kotrung.add(j);
                    }
                }

            }
            kotrung.removeAll(trung);
            for (Integer integer : trung) {
                System.out.println(integer);
            }
            for (Integer integer : kotrung) {
                System.out.println(integer);
            }
            for (Integer j : kotrung) {
                if (!sheet.getCell(0, j).getContents().startsWith("BK-")) {
                    break;
                }
                String BookID = "";
                String bookName = "",
                        authorID = "", publisherID = "", supplierID = "", categoryID = "", shelf = "", image = "";
                int price = 0, quantity = 0, rowNum = 0, colNum = 0;
                Vector d = new Vector();
                for (int i = 0; i < sheet.getColumns(); i++) {
                    Cell cell = sheet.getCell(i, j);

                    if (i == 0) {
                        BookID += sheet.getCell(i, j).getContents();
                    } else if (i == 1) {
                        bookName += sheet.getCell(i, j).getContents();
                    } else if (i == 2) {
                        authorID += sheet.getCell(i, j).getContents();
                    } else if (i == 3) {
                        publisherID += sheet.getCell(i, j).getContents();
                    } else if (i == 4) {
                        supplierID += sheet.getCell(i, j).getContents();
                    } else if (i == 5) {
                        categoryID += sheet.getCell(i, j).getContents();
                    } else if (i == 6) {
                        price = Integer.parseInt(sheet.getCell(i, j).getContents());
                    } else if (i == 7) {
                        quantity = Integer.parseInt(sheet.getCell(i, j).getContents());
                    } else if (i == 8) {
                        shelf += sheet.getCell(i, j).getContents();
                    } else if (i == 9) {
                        rowNum = Integer.parseInt(sheet.getCell(i, j).getContents());
                    } else if (i == 10) {
                        colNum = Integer.parseInt(sheet.getCell(i, j).getContents());
                    } else if (i == 11) {
                        image += sheet.getCell(i, j).getContents();
                    }

                    d.add(cell.getContents());
                    System.out.println(cell.getContents());
                }
                Book book = new Book(BookID, bookName, authorID, publisherID, supplierID, categoryID, price, quantity, shelf, rowNum, colNum, image);
                BookList.add(book);
                System.out.println(d.get(0));
                d.add("\n");
                dmBook.addRow(d);
            }

        } catch (BiffException e) {
            e.printStackTrace();
        }
    }

    class ButtonEditor extends DefaultCellEditor {

        protected JButton button;
        private String label;
        private boolean isPushed;
//        DateFormat dfExtend = new SimpleDateFormat("yyyy-MM-dd");

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
//            button.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    
//                }
//            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {

            }
            isPushed = false;
            return new String(label);
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
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
                windowY = container.getY() + textField.getY() + textField.getHeight() + autoSuggestionPopUpWindow.getMinimumSize().height + 60;
            } else {
                windowY = container.getY() + textField.getY() + textField.getHeight() + autoSuggestionPopUpWindow.getHeight() + 60;
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
}
