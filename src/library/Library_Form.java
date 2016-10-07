/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import object.BorrowingManagement;
import data.ReaderData;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import data.BorrowingManagementData;
import object.Next10Day;
import object.NextYear;
import object.Reader;
import data.ReaderData;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import object.Next10Day;
import object.NextYear;
import object.Reader;
import javax.swing.JFileChooser;
import data.CategoryData;
import data.BookData;
import object.Book;
import object.Category;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import data.AuthorData;
import object.Author;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import object.Category;
import data.CategoryData;
import data.ConnectDatabase;
import data.DataAccess;
import data.PublisherData;
import data.ReturnManagementData;
import data.SupplierData;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;
import object.Publisher;
import object.Supplier;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import net.proteanit.sql.DbUtils;
import object.ButtonRenderer;
import object.ReturnManagement;

/**
 *
 * @author PhiLong
 */
public class Library_Form extends javax.swing.JFrame {

    /**
     * Creates new form Library_Form
     */
    private String sql = "SELECT * FROM borrowingmanagement where ( ReturnDate < CURDATE())";
    public DataAccess da = new DataAccess();
//    private ConnectDatabase connectDatabase = new ConnectDatabase();
    private CategoryData CategoryList = new CategoryData();
    private AuthorData AuthorList = new AuthorData();
    private BookData BookList = new BookData();
    private ReaderData ReaderList = new ReaderData();
    private CategoryData clist = new CategoryData();
    public BorrowingManagementData BorrowingList = new BorrowingManagementData();
    private PublisherData PublisherList = new PublisherData();
    private SupplierData SupplierList = new SupplierData();
    private ReturnManagementData ReturnList = new ReturnManagementData();

    DefaultTableModel dmReader = new DefaultTableModel();
    DefaultTableModel dmBorrowing = new DefaultTableModel();
    DefaultTableModel dmBook = new DefaultTableModel();
    DefaultTableModel dmAuthor = new DefaultTableModel();
    DefaultTableModel dmCategory = new DefaultTableModel();
    DefaultTableModel dmSupplier = new DefaultTableModel();
    DefaultTableModel dmPublisher = new DefaultTableModel();
    DefaultTableModel dmReturn = new DefaultTableModel();

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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
        "ID", "Name", "ID Card Number", "Sex", "Birthday", "Address", "Phone", "Email", "Activation Date", "Expired Date"
    };
    private String[] tenCotBook = {
        "Book ID", "Book Name", "Author ID", "Publisher ID", "Supplier ID", "CategoryID", "Price", "Quantity", "Shelf", "Row_No.", "Column_No.", "Image"
    };
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
        "Borrow ID", "Book ID", "Book Name", "Author Name", "Publisher Name", "Price", "Borrow Date", "Return Date", "Overdue Days", "Penalty"
    };

    public Library_Form() {
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
                this.lbTongSach.setText("Tổng số sách : " + Integer.toString(rs1.getInt("BookSum")));
            }
            if (rs2.next()) {
                this.lbTongKhach.setText("Tổng số khách hàng: " + Integer.toString(rs2.getInt("ReaderSum")));
            }
            if (rs3.next()) {
                this.lbTongPhieu.setText("Tổng số phiếu mượn: " + Integer.toString(rs3.getInt("BorrowSum")));
            }
            if (rs4.next()) {
                this.lbTongKhachMuon.setText("Tổng số khách đang mượn sách: " + Integer.toString(rs4.getInt("ReaderBorrowSum")));
            }
            if (rs5.next()) {
                this.lbTongPhieuQuaHan.setText("Tổng số phiếu quá hạn là: " + Integer.toString(rs5.getInt("DatedBorrowSum")));
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
        
        
        tbBorrowingManagement.getColumn("Add 10 Days").setCellRenderer(new ButtonRenderer());
        ButtonEditor buttonEditor = new ButtonEditor(new JCheckBox());
        tbBorrowingManagement.getColumn("Add 10 Days").setCellEditor(buttonEditor);
        buttonEditor.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int donghh = tbBorrowingManagement.getSelectedRow();
                String BrIDhh = tbBorrowingManagement.getValueAt(donghh, 0).toString();
                String RdIDhh = tbBorrowingManagement.getValueAt(donghh, 1).toString();
                String BookIDhh = tbBorrowingManagement.getValueAt(donghh, 2).toString();
                Date BorrowDatehh = carBorrowDate.getDate();

                System.out.println(nextday);
                System.out.println(today);

//                da.updateDB("update borrowingmanagement set ReturnDate = '" + nextday + "' where BorrowID = '" + BrIDhh + "'");
//                System.out.println("update borrowingmanagement set ReturnDate = '" + nextday + "' where BorrowID = '" + BrIDhh + "'");
                BorrowingManagement r = new BorrowingManagement(BrIDhh, RdIDhh, BookIDhh, BorrowDatehh, nextday);
                Vector vt = r.toVector();

                try {
                    BorrowingList.suaReturnDate(donghh, r);
                } catch (Exception ex) {
                    Logger.getLogger(Library_Form.class.getName()).log(Level.SEVERE, null, ex);
                }
                dmBorrowing.removeRow(donghh);
                dmBorrowing.insertRow(donghh, vt);
//                disableCarlendarBorrow();

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

        enableCarlendarBorrow();
        disableCarlendarReader();
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
        tfBookID1 = new javax.swing.JTextField();
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
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel15 = new javax.swing.JPanel();
        tfBorrowID = new javax.swing.JTextField();
        tfBookID = new javax.swing.JTextField();
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
        jPanel16 = new javax.swing.JPanel();
        tfSearchReturn = new javax.swing.JTextField();
        btSearchReturn = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbReturn = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
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
        jButton4 = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        taBaoCao = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/key.png"))); // NOI18N
        jButton1.setMnemonic('C');
        jButton1.setText("Change Password");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logout.png"))); // NOI18N
        jButton2.setMnemonic('L');
        jButton2.setText("Logout");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/home.png"))); // NOI18N
        jButton3.setMnemonic('H');
        jButton3.setText("Help");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(609, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Home", jPanel1);

        lbBookID.setText("Book ID:");

        lbSupplierID.setText("Supplier ID:");

        lbBookName.setText("Book Name: ");

        lbPrice.setText("Price:");

        lbCategoryID.setText("Category ID:");

        tfBookID1.setEditable(false);

        lbColumnNo.setText("Column No. :");

        lbAuthorID.setText("Author ID:");

        lbShelf.setText("Shelf:");

        lbQuantity.setText("Quantity:");

        lbRowNo.setText("Row No. :");

        cbCategoryID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--CategoryID--" }));

        lbImage.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black));

        btImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Pictures Folder-20.png"))); // NOI18N
        btImage.setText("Browse");
        btImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImageActionPerformed(evt);
            }
        });

        btAdd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_add.png"))); // NOI18N
        btAdd1.setMnemonic('A');
        btAdd1.setText("Add");
        btAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdd1ActionPerformed(evt);
            }
        });

        btEdit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gtk-refresh.png"))); // NOI18N
        btEdit1.setMnemonic('U');
        btEdit1.setText("Update");
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
        btDelete1.setText("Delete");
        btDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDelete1ActionPerformed(evt);
            }
        });

        btClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Redo-16.png"))); // NOI18N
        btClose.setMnemonic('C');
        btClose.setText("Close");
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
        tbBookAdmin.setColumnSelectionAllowed(true);
        tbBookAdmin.setName(""); // NOI18N
        tbBookAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBookAdminMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbBookAdmin);

        btSearchBook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_search.png"))); // NOI18N
        btSearchBook.setMnemonic('S');
        btSearchBook.setText("Search");
        btSearchBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSearchBookActionPerformed(evt);
            }
        });

        lbSupplierID2.setText("Publisher ID:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(btAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btEdit1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btDelete1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfSearchBook, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btSearchBook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                                            .addComponent(tfBookID1)
                                            .addComponent(tfBookName))))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbQuantity)
                                            .addComponent(lbPrice))
                                        .addGap(28, 28, 28)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbShelf)
                                            .addComponent(lbRowNo))
                                        .addGap(26, 26, 26)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(tfShelf, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfRowNo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbSupplierID)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(lbCategoryID)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbCategoryID, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(btImage))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(lbColumnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(47, 47, 47)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfColumnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfImage, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(4, 4, 4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(lbImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(26, 26, 26))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfBookID1)
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
                        .addGap(34, 34, 34)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btEdit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btDelete1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btClose, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfSearchBook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btSearchBook, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(33, 33, 33)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
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
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 625, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Book", jPanel7);

        jLabel16.setText("Category ID");

        tfcategoryid.setEditable(false);

        jLabel17.setText("Category Name");

        btadd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_add.png"))); // NOI18N
        btadd.setMnemonic('A');
        btadd.setText("Add");
        btadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btaddActionPerformed(evt);
            }
        });

        btupdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gtk-refresh.png"))); // NOI18N
        btupdate.setMnemonic('U');
        btupdate.setText("Update");
        btupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btupdateActionPerformed(evt);
            }
        });

        btdelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_delete.png"))); // NOI18N
        btdelete.setMnemonic('D');
        btdelete.setText("Delete");
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
        tbcategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbcategoryMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbcategory);

        btdelete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Redo-16.png"))); // NOI18N
        btdelete1.setMnemonic('C');
        btdelete1.setText("Close");
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
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(tfcategoryid, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 151, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(tfcategoryname, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(btadd, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btdelete, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btdelete1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btadd)
                    .addComponent(btupdate)
                    .addComponent(btdelete)
                    .addComponent(btdelete1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Category", jPanel9);

        btAdd2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_add.png"))); // NOI18N
        btAdd2.setMnemonic('A');
        btAdd2.setText("Add");
        btAdd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdd2ActionPerformed(evt);
            }
        });

        btEdit2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gtk-refresh.png"))); // NOI18N
        btEdit2.setMnemonic('U');
        btEdit2.setText("Update");
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
        btDelete2.setText("Delete");
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
                .addComponent(btAdd2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btEdit2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btDelete2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(206, Short.MAX_VALUE))
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
        tbAuthorAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAuthorAdminMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbAuthorAdmin);

        lbAuthorID1.setText("Author ID:");

        tfAuthorID1.setEditable(false);

        lbAuthorName.setText("Author Name: ");

        btClose1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Redo-16.png"))); // NOI18N
        btClose1.setMnemonic('C');
        btClose1.setText("Close");
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(lbAuthorID1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfAuthorID1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btClose1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane2.addTab("Author", jPanel8);

        btAddPublisher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_add.png"))); // NOI18N
        btAddPublisher.setMnemonic('A');
        btAddPublisher.setText("Add");
        btAddPublisher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddPublisherActionPerformed(evt);
            }
        });

        btEditPublisher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gtk-refresh.png"))); // NOI18N
        btEditPublisher.setMnemonic('U');
        btEditPublisher.setText("Update");
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
        btDeletePublisher.setText("Delete");
        btDeletePublisher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeletePublisherActionPerformed(evt);
            }
        });

        btClose2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Redo-16.png"))); // NOI18N
        btClose2.setMnemonic('C');
        btClose2.setText("Close");
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
                .addComponent(btAddPublisher, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btEditPublisher, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btDeletePublisher, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                .addGap(137, 137, 137)
                .addComponent(btClose2, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addGap(51, 51, 51))
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
        tbPublisher.setName(""); // NOI18N
        tbPublisher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPublisherMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbPublisher);
        tbPublisher.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        lbAuthorID2.setText("Publisher ID:");

        tfPublisherID.setEditable(false);

        lbAuthorName1.setText("Publisher Name: ");

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
                        .addGap(38, 38, 38)
                        .addComponent(lbAuthorID2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tfPublisherID, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbAuthorName1)
                        .addGap(29, 29, 29)
                        .addComponent(tfPublisherName, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbAuthorID2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfPublisherID)
                    .addComponent(lbAuthorName1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfPublisherName))
                .addGap(32, 32, 32)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane2.addTab("Publisher", jPanel10);

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

        jTabbedPane1.addTab("Book Management", jPanel2);

        tfID.setEditable(false);

        cbSex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        carBirthday.setDateFormatString("yyyy-MM-dd");

        carActivationDate.setDateFormatString("yyyy-MM-dd");
        carActivationDate.setMaxSelectableDate(new java.util.Date(253370743283000L));
        carActivationDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                carActivationDateMouseClicked(evt);
            }
        });

        carExpiredDate.setDateFormatString("yyyy-MM-dd");
        carExpiredDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                carExpiredDateMouseClicked(evt);
            }
        });

        jLabel6.setText("ID");

        jLabel7.setText("Name");

        jLabel8.setText("ID Card");

        jLabel9.setText("Sex");

        jLabel10.setText("Birthday");

        jLabel11.setText("Address");

        jLabel12.setText("Phone");

        jLabel13.setText("Email");

        jLabel14.setText("Expired Date");

        jLabel15.setText("Activation Date");

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
        tbReader.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbReaderMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbReader);

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_add.png"))); // NOI18N
        btnAdd.setMnemonic('A');
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gtk-refresh.png"))); // NOI18N
        btnEdit.setMnemonic('U');
        btnEdit.setText("Update");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_delete.png"))); // NOI18N
        btnDelete.setMnemonic('D');
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_search.png"))); // NOI18N
        btnSearch.setMnemonic('S');
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnSearch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Redo-16.png"))); // NOI18N
        btnSearch1.setMnemonic('C');
        btnSearch1.setText("Close");
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel6)
                                                    .addComponent(jLabel7))
                                                .addGap(10, 10, 10))
                                            .addComponent(jLabel8)))
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(cbSex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(240, 240, 240))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfIDCardNumber)
                                            .addComponent(tfName)
                                            .addComponent(tfID, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(carBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(9, 9, 9))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(92, 92, 92)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel12)
                                                    .addComponent(jLabel13)
                                                    .addComponent(jLabel14))
                                                .addGap(0, 19, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(tfPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(tfEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(carActivationDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(carExpiredDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(107, 107, 107))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSearch)
                                .addGap(18, 18, 18)
                                .addComponent(btnSearch1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfIDCardNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbSex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(carBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(tfPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(carActivationDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(carExpiredDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete)
                    .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(btnSearch1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Reader Management", jPanel3);

        tfBorrowID.setEditable(false);
        tfBorrowID.setText(" ");

        carBorrowDate.setDateFormatString("yyyy-MM-dd");

        jLabel1.setText("Borrow ID");

        carReturnDate.setDateFormatString("yyyy-MM-dd");

        jLabel2.setText("Book ID");

        jLabel3.setText("Return Date");

        jLabel4.setText("Reader ID");

        jLabel5.setText("Borrow Date");

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
        tbBorrowingManagement.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBorrowingManagementMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbBorrowingManagement);

        btEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gtk-refresh.png"))); // NOI18N
        btEdit.setMnemonic('U');
        btEdit.setText("Update");
        btEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditActionPerformed(evt);
            }
        });

        btDelete3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Redo-16.png"))); // NOI18N
        btDelete3.setMnemonic('C');
        btDelete3.setText("Close");
        btDelete3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDelete3ActionPerformed(evt);
            }
        });

        btDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_delete.png"))); // NOI18N
        btDelete.setMnemonic('D');
        btDelete.setText("Delete");
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });

        btAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_add.png"))); // NOI18N
        btAdd.setMnemonic('A');
        btAdd.setText("Add");
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btDelete3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btDelete3)
                    .addComponent(btDelete)
                    .addComponent(btEdit)
                    .addComponent(btAdd))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(tfBorrowID, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(carBorrowDate, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(carReturnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(tfReaderID, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(tfBookID, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(tfBookID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(carBorrowDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(carReturnDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Borrowing", jPanel15);

        btSearchReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_search.png"))); // NOI18N
        btSearchReturn.setMnemonic('S');
        btSearchReturn.setText("Search");
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
        jScrollPane8.setViewportView(tbReturn);

        jLabel18.setText("Reader ID");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 51, 255));
        jLabel19.setText("Search all books which someone borrowed");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfSearchReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btSearchReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(271, 271, 271)
                        .addComponent(jLabel19)))
                .addContainerGap(295, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSearchReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSearchReturn)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Return", jPanel16);

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

        jTabbedPane1.addTab("Borrowing & Return Management", jPanel4);

        lbSupplierID1.setText("Supplier ID:");

        lbSupplierName.setText("Supplier Name: ");

        tfSupplierID1.setEditable(false);

        lbPhone.setText("Phone: ");

        lbAddress.setText("Address: ");

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
                    .addComponent(ftfPhoneSupplier)
                    .addComponent(tfSupplierID1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbSupplierName, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(lbAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfSupplierName)
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
        tbSupplierAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSupplierAdminMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbSupplierAdmin);

        btEditSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gtk-refresh.png"))); // NOI18N
        btEditSupplier.setMnemonic('U');
        btEditSupplier.setText("Update");
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
        btDeleteSupplier.setText("Delete");
        btDeleteSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteSupplierActionPerformed(evt);
            }
        });

        btClose3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Redo-16.png"))); // NOI18N
        btClose3.setText("Close");
        btClose3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClose3ActionPerformed(evt);
            }
        });

        btAddSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/btn_add.png"))); // NOI18N
        btAddSupplier.setMnemonic('A');
        btAddSupplier.setText("Add");
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
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE)
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
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addGap(176, 176, 176))
        );

        jTabbedPane1.addTab("Supplier Management", jPanel5);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel21.setText("Thống kê");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Danh sách phiếu mượn quá hạn");

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
        ));
        jScrollPane9.setViewportView(tbPhieuQuaHan);

        jButton4.setText("In báo cáo");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        taBaoCao.setColumns(20);
        taBaoCao.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        taBaoCao.setRows(5);
        taBaoCao.setText("\t\t\t       \t       THỐNG KÊ\n");
        jScrollPane10.setViewportView(taBaoCao);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(261, 261, 261)
                .addComponent(jLabel22)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
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
                        .addGap(253, 253, 253))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel21)
                        .addGap(316, 316, 316)
                        .addComponent(jButton4)
                        .addGap(11, 11, 11)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTongKhach)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTongSach)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTongPhieu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTongKhachMuon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTongPhieuQuaHan)
                .addGap(30, 30, 30)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Statistics", jPanel6);

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

    private void carActivationDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carActivationDateMouseClicked

    }//GEN-LAST:event_carActivationDateMouseClicked

    private void carExpiredDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carExpiredDateMouseClicked

    }//GEN-LAST:event_carExpiredDateMouseClicked

    private void tbReaderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbReaderMouseClicked
        carActivationDate.setEnabled(true);
        carExpiredDate.setEnabled(true);
        int donghh = this.tbReader.getSelectedRow();
        Reader t = ReaderList.getReader(donghh);
        this.viewReader(t);
    }//GEN-LAST:event_tbReaderMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        String RdID = "RD-" + getRandomString();
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
        try {
            ReaderList.them(r);
        } catch (Exception ex) {
            Logger.getLogger(Library_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        dmReader.addRow(vt);
        disableCarlendarReader();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed

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
        int donghh = this.tbReader.getSelectedRow();
        try {
            ReaderList.sua(donghh, r);
        } catch (Exception ex) {
            Logger.getLogger(Library_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        dmReader.removeRow(donghh);
        dmReader.insertRow(donghh, vt);
        disableCarlendarReader();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
            String RdID = tfID.getText();
            int i = ReaderList.xoa(RdID);
            dmReader.removeRow(i);
        } catch (Exception ex) {
            Logger.getLogger(Library_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        disableCarlendarReader();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

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
            this.lbImage.setIcon(BookList.loadIcon("image/" + f.getName(), 410, 600));
        }
    }//GEN-LAST:event_btImageActionPerformed

    private void btEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEdit1ActionPerformed
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
        int donghh = this.tbBookAdmin.getSelectedRow();
        BookList.update(donghh, b);
        dmBook.removeRow(donghh);
        dmBook.insertRow(donghh, vt);
    }//GEN-LAST:event_btEdit1ActionPerformed

    private void btDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDelete1ActionPerformed
        // TODO add your handling code here:
        String bookId = tfBookID1.getText();
        int i = BookList.delete(bookId);
        dmBook.removeRow(i);
    }//GEN-LAST:event_btDelete1ActionPerformed

    private void tbBookAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBookAdminMouseClicked
        // TODO add your handling code here:
        int donghh = this.tbBookAdmin.getSelectedRow();
        Book b = BookList.getBook(donghh);
        this.viewBook(b);
    }//GEN-LAST:event_tbBookAdminMouseClicked

    private void btCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btCloseActionPerformed

    private void btAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdd1ActionPerformed
        // TODO add your handling code here:

        String bookID = "BK-" + getRandomString();

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
        BookList.add(b);
        dmBook.addRow(vt);
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
            Logger.getLogger(Library_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btdeleteActionPerformed

    private void tbcategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbcategoryMouseClicked
        int donghh = this.tbcategory.getSelectedRow();
        Category t = CategoryList.getCategory(donghh);
        this.viewCategory(t);
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
            Logger.getLogger(Library_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btDeletePublisherActionPerformed

    private void tbPublisherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPublisherMouseClicked
        int donghh = this.tbPublisher.getSelectedRow();
        Publisher b = PublisherList.getPublisher(donghh);
        this.viewSupplier(b);
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
        String BookID = tfBookID.getText();
        Date BorrowDate = carBorrowDate.getDate();
        Date ReturnDate = carReturnDate.getDate();
        BorrowingManagement r = new BorrowingManagement(BrID, RdID, BookID, BorrowDate, ReturnDate);
        Vector vt = r.toVector();
        int donghh = this.tbBorrowingManagement.getSelectedRow();
        try {
            BorrowingList.sua(donghh, r);
        } catch (Exception ex) {
            Logger.getLogger(Library_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        dmBorrowing.removeRow(donghh);
        dmBorrowing.insertRow(donghh, vt);
//        disableCarlendarBorrow();
    }//GEN-LAST:event_btEditActionPerformed

    private void tbBorrowingManagementMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBorrowingManagementMouseClicked
        int donghh = this.tbBorrowingManagement.getSelectedRow();
        BorrowingManagement b = BorrowingList.getBorrowingManagement(donghh);
        this.viewBorrowing(b);
    }//GEN-LAST:event_tbBorrowingManagementMouseClicked

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        String BorrowID = "BR-" + getRandomString();
        String ReaderID = tfReaderID.getText();
        String BookID = tfBookID.getText();

        BorrowingManagement r = new BorrowingManagement(BorrowID, ReaderID, BookID, today, nextday);
        Vector vt = r.toVector();
        try {
            BorrowingList.them(r);
        } catch (Exception ex) {
            Logger.getLogger(Library_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        dmBorrowing.addRow(vt);
    }//GEN-LAST:event_btAddActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        try {
            String BrID = tfBorrowID.getText();
            int i = BorrowingList.xoa(BrID);
            dmBorrowing.removeRow(i);
        } catch (Exception ex) {
            Logger.getLogger(Library_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
//        disableCarlendarBorrow();
    }//GEN-LAST:event_btDeleteActionPerformed

    private void btSearchReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSearchReturnActionPerformed
        String RdID = tfSearchReturn.getText();
        ReturnList.load("select borrowingmanagement.BorrowID, borrowingmanagement.BookID, book.BookName, author.AuthorName, publisher.PublisherName, book.Price, borrowingmanagement.BorrowDate, borrowingmanagement.ReturnDate\n"
                + "from borrowingmanagement\n"
                + "inner join book on borrowingmanagement.BookID = book.BookID\n"
                + "inner join author on book.AuthorID = author.AuthorID\n"
                + "inner join publisher on book.PublisherID = publisher.PublisherID\n"
                + "where borrowingmanagement.RdID = '" + RdID + "'");
        for (ReturnManagement c : ReturnList.getReturnList()) {
            dmReturn.addRow(c.toVector());
        }
    }//GEN-LAST:event_btSearchReturnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new ChangePassword().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
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
                JOptionPane.showMessageDialog(null, "Lưu thành công");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton4ActionPerformed
    private void viewBook(Book b) {
        this.tfBookID1.setText(b.getBookID());
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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Library_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Library_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Library_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Library_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Library_Form().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdd;
    private javax.swing.JButton btAdd1;
    private javax.swing.JButton btAdd2;
    private javax.swing.JButton btAddPublisher;
    private javax.swing.JButton btAddSupplier;
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
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
    private javax.swing.JTextField tfBookID;
    private javax.swing.JTextField tfBookID1;
    private javax.swing.JTextField tfBookName;
    private javax.swing.JTextField tfBorrowID;
    private javax.swing.JTextField tfColumnNo;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfID;
    private javax.swing.JTextField tfIDCardNumber;
    private javax.swing.JTextField tfImage;
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

    private void enableCarlendarBorrow() {
        carBorrowDate.setDate(today2);
        carReturnDate.setDate(nextday);
    }
//    private void disableCarlendarBorrow() {
//        carBorrowDate.setDate(today2);
//        carReturnDate.setDate(nextday);
////        carBorrowDate.setEnabled(false);
////        carReturnDate.setEnabled(false);
//    }

    private void disableCarlendarReader() {
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
        this.carActivationDate.setDate(t.getActivationDate());
        this.carExpiredDate.setDate(t.getExpiredDate());

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

    private void viewBorrowing(BorrowingManagement b) {
        this.tfBorrowID.setText(String.valueOf(b.getBorrowID()));
        this.tfReaderID.setText(b.getRdID());
        this.tfBookID.setText(b.getBookID());
        this.carBorrowDate.setDate(b.getBorrowDate());
        this.carReturnDate.setDate(b.getReturnDate());
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

}
