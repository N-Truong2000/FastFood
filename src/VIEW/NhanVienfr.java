/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package VIEW;

import DAO.ChiTietHDDAO;
import DAO.ConnectSQL;
import DAO.DanhMucDAO;
import DAO.HoaDonDAO;
import DAO.KhuyenMaiDAO;
import DAO.NhanVienDAO;
import DAO.ThucDonDAO;
import DTO.ChiTietHD;
import DTO.DanhMuc;
import DTO.HoaDon;
import DTO.KhuyenMai;
import DTO.NhanVien;
import DTO.ThucDon;
import SERVICE.DanhMucSERVICE;
import SERVICE.HoaDonSERVICE;
import SERVICE.KhuyenMaiSERVICE;
import SERVICE.NhanVienSERVICE;
import SERVICE.ThucDonSERVICE;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.awt.Color;
import java.awt.Image;
import java.time.LocalDate;
import java.awt.Rectangle;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ADMIN
 */
public class NhanVienfr extends javax.swing.JFrame {

    /**
     * Creates new form TrangChinh
     */
    String filename = null;
    byte[] image = null;
    NhanVien nhanVien;
    NhanVienDAO nhanVienDAO = new NhanVienDAO();
    NhanVienSERVICE nhanVienSERVICE = new NhanVienSERVICE();
    DefaultTableModel modelNhanVien;
    
    
   HoaDon hoaDon; 
   HoaDonDAO hoaDonDAO;
    HoaDonSERVICE hoaDonSERVICE;
    public Connection conn = ConnectSQL.getConnection();
    public PreparedStatement ps = null;
    public ResultSet rs = null;
    DefaultTableModel modelHoaDon;
   
   
    ThucDon thucDon;
    ThucDonDAO thucDonDAO;
    ThucDonSERVICE thucDonSERVICE;
    DefaultTableModel modelThucDon;
    
    
    DanhMuc danhMuc;
    DanhMucDAO danhMucDAO;
    DanhMucSERVICE danhMucSERVICE;
    DefaultTableModel modelDanhMuc;
    
    
    ChiTietHD chiTietHD;
    ChiTietHDDAO chiTietHDDAO;
    DefaultTableModel modelLSHD;
    DefaultTableModel modelCTHD;
    
    DefaultTableModel modelDoanhThuMon;
    DefaultTableModel modelDoanhthuNV;  
    DefaultTableModel modelDoanhThuTG;
    
    KhuyenMai khuyenMai;
    KhuyenMaiDAO khuyenMaiDAO;
    KhuyenMaiSERVICE khuyenMaiSERVICE;
    DefaultTableModel modelkhuyenMai;
    DefaultTableModel modelDSKhuyenMai;
    
    
    DefaultTableModel modelTatCaMon;
    DefaultTableModel modelDoAn;
    DefaultTableModel modelNuocUong;
    
    public NhanVienfr() {
        initComponents();
        
        
        //HÓA ĐƠN
        modelHoaDon = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) { 
                return false;
            }
        };
        
        hoaDon = new HoaDon();
        hoaDonDAO  = new HoaDonDAO();
        hoaDonSERVICE  = new HoaDonSERVICE();
        
        loadTenMon();
        loadTenNV();
        modelHoaDon = (DefaultTableModel)tableHoaDon.getModel();
        
        tableHoaDon.setModel(modelHoaDon);
        modelHoaDon.addColumn("Dish code");
        modelHoaDon.addColumn("Dish name");
        modelHoaDon.addColumn("Price");
        modelHoaDon.addColumn("Amount");
        modelHoaDon.addColumn("Total");
        
        //THỰC ĐƠN
        DisableMA();
        thucDon = new ThucDon();
        thucDonSERVICE = new ThucDonSERVICE();
        
        modelThucDon = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) { 
                return false;
            }
        };
        
        tableFood.setModel(modelThucDon);
        modelThucDon.addColumn("Dish code");
        modelThucDon.addColumn("Dish name");
        modelThucDon.addColumn("Dish type");
        modelThucDon.addColumn("Unit");
        modelThucDon.addColumn("Price");
        modelThucDon.addColumn("Amount");
        modelThucDon.addColumn("Image");
        
        setDataTableTD( thucDonSERVICE.getAllThucDon()); //để dữ liệu vào bảng
        
        modelCTHD=(DefaultTableModel) tableCTHD.getModel();
        modelLSHD=(DefaultTableModel) tableLSHD.getModel();
        HienThiCboTenMon();
        HienThiCboNhanVien();
        hienThiTableHoaDon();

        //DANH MỤC
        DisableDM();
        danhMuc = new DanhMuc();
        danhMucSERVICE = new DanhMucSERVICE();
        
        modelDanhMuc = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) { 
                return false;
            }
        };
        
        tableDanhMuc.setModel(modelDanhMuc);
        modelDanhMuc.addColumn("Category ID");
        modelDanhMuc.addColumn("Category name");
        
        setDataTableDM( danhMucSERVICE.getAllDanhMuc()); //để dữ liệu vào bảng
      
        
        //KHUYẾN MÃI
        khuyenMaiSERVICE = new KhuyenMaiSERVICE();
        khuyenMai = new KhuyenMai();
        
        modelkhuyenMai=(DefaultTableModel) tableKhuyenMai.getModel();
        modelDSKhuyenMai=(DefaultTableModel) tableDSKhuyenMai.getModel();
        hienThiKhuyenMai();
        
        //THỰC ĐƠN MÓN
        
        modelTatCaMon=(DefaultTableModel) tabTatCaMon3.getModel();
        modelDoAn=(DefaultTableModel) tabDoAn.getModel();
        modelNuocUong=(DefaultTableModel) tabNuocUong.getModel();
        hienThiTatCa();
        hienThiDoAn();
        hienThiNuocUong();
        
    } //end
    
    
    private void setDataTableNV(  List<NhanVien> nhanViens ){ //lấy dữ liệu cho bảng
            for(NhanVien nhanVien : nhanViens){
            modelNhanVien.addRow(new Object[]{nhanVien.getMaNV(),nhanVien.getTenNV(),
                nhanVien.getNgaySinh(),nhanVien.getGioiTinh(),nhanVien.getSoDT(),nhanVien.getDiaChi(),nhanVien.getChucVu(),
                nhanVien.getTenDangNhap(), nhanVien.getImageNV()});
        }
    }
    
    private void setDataTableTD(  List<ThucDon> thucDons ){ //lấy dữ liệu cho bảng
                for(ThucDon thucDon : thucDons){
            modelThucDon.addRow(new Object[]{thucDon.getMaMonAn(),thucDon.getTenMonAn(),
                thucDon.getMaLoaiMon(),thucDon.getDonVi(),thucDon.getGiaTien(),thucDon.getSoLuong(),
                thucDon.getImageMonAn()});
        }
}
    
    private void setDataTableDM(  List<DanhMuc> danhMucs ){ //lấy dữ liệu cho bảng
                for(DanhMuc danhMuc : danhMucs){
            modelDanhMuc.addRow(new Object[]{danhMuc.getMaLoai(),danhMuc.getTenLoai(),
                danhMuc.getImageDM()});
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        panelMenu = new javax.swing.JPanel();
        panelMonAn = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        panelHoaDon = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        panelDanhMuc = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        panelTrangChu = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        panelCTHD = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        panelKhuyenMai = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        tfmaNV = new javax.swing.JTextField();
        tfTenDN = new javax.swing.JTextField();
        panelGiaoDien = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnTrangChu = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        btnMinimize6 = new javax.swing.JLabel();
        lbThoat8 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        btnExit = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        btnProfile = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        pnHoaDon = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnMinimize = new javax.swing.JLabel();
        lbThoat2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        tfGiaTien = new javax.swing.JTextField();
        tfMaMon = new javax.swing.JTextField();
        tfSoLuong = new javax.swing.JTextField();
        cbTenMon = new javax.swing.JComboBox<>();
        cbTenNV = new javax.swing.JComboBox<>();
        tfMaNVHD = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        tfTenLoaiMon = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        tfSoLuongTon = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        tfThanhTien = new javax.swing.JTextField();
        cbCTGiamGia = new javax.swing.JComboBox<>();
        jLabel80 = new javax.swing.JLabel();
        lbMaKM = new javax.swing.JLabel();
        tfVATHD = new javax.swing.JTextField();
        checkVATHD = new javax.swing.JCheckBox();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel62 = new javax.swing.JPanel();
        jPanel63 = new javax.swing.JPanel();
        panelThucDonMon3 = new javax.swing.JTabbedPane();
        paneThucDon3 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tabTatCaMon3 = new javax.swing.JTable();
        panelDoAn = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tabDoAn = new javax.swing.JTable();
        panelNuocUong = new javax.swing.JPanel();
        jScrollPane22 = new javax.swing.JScrollPane();
        tabNuocUong = new javax.swing.JTable();
        jPanel55 = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel56 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tableKhuyenMai = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        btnThanhToan = new javax.swing.JButton();
        jLabel65 = new javax.swing.JLabel();
        lbTienthua = new javax.swing.JLabel();
        lbTongTien = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        tfGiamGia = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHoaDon = new javax.swing.JTable();
        jPanel30 = new javax.swing.JPanel();
        btnThemMoi = new javax.swing.JButton();
        btnXoa4 = new javax.swing.JButton();
        btnLamMoi3 = new javax.swing.JButton();
        panelChiTietHD = new javax.swing.JPanel();
        jpnalLSHĐ = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableLSHD = new javax.swing.JTable();
        jpnalCTHD = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tableCTHD = new javax.swing.JTable();
        jPanel44 = new javax.swing.JPanel();
        tfThanhTienCTHD = new javax.swing.JTextField();
        cboTenMonAn = new javax.swing.JComboBox<>();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        tfGiaBanCTHD = new javax.swing.JTextField();
        tfSoLuongCTHD = new javax.swing.JTextField();
        btnInHoaDonCT = new javax.swing.JButton();
        jPanel45 = new javax.swing.JPanel();
        btnMinimize8 = new javax.swing.JLabel();
        lbThoat10 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tfMaHD = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cboTenNV = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        tfTongTienHD = new javax.swing.JTextField();
        tfTenNV = new javax.swing.JTextField();
        pnDanhMuc = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDanhMuc = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfMaDM = new javax.swing.JTextField();
        tfTenDM = new javax.swing.JTextField();
        tfLinkDM = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        btnMinimize1 = new javax.swing.JLabel();
        lbThoat = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        btnThemDM = new javax.swing.JButton();
        btnLuuDM = new javax.swing.JButton();
        pnMonAn = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfMaMonAn = new javax.swing.JTextField();
        tfTenMonAn = new javax.swing.JTextField();
        btnOpen = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        tfDonViTinh = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tfGiaTienMon = new javax.swing.JTextField();
        tfTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        lbLoai = new javax.swing.JLabel();
        cbLoaiMon = new javax.swing.JComboBox<>();
        lbGia1 = new javax.swing.JLabel();
        tfTenLoai = new javax.swing.JTextField();
        tfSoLuongMon = new javax.swing.JTextField();
        tfLinkMonAn = new javax.swing.JTextField();
        jPanel39 = new javax.swing.JPanel();
        lbImageFood = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        btnMinimize2 = new javax.swing.JLabel();
        lbThoat1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableFood = new javax.swing.JTable();
        jPanel41 = new javax.swing.JPanel();
        btnLuuMon = new javax.swing.JButton();
        btnSuaMon = new javax.swing.JButton();
        btnXoaMon = new javax.swing.JButton();
        btnThemMon = new javax.swing.JButton();
        pnKhuyenMai = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tableDSKhuyenMai = new javax.swing.JTable();
        jPanel54 = new javax.swing.JPanel();
        btnMinimize11 = new javax.swing.JLabel();
        lbThoat13 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jPanel57 = new javax.swing.JPanel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        tfMaKM = new javax.swing.JTextField();
        tfTenKM = new javax.swing.JTextField();
        jLabel98 = new javax.swing.JLabel();
        btnLuuKM = new javax.swing.JButton();
        btnThemKM = new javax.swing.JButton();
        tfGiaKM = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(1249, 654));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        panelMenu.setBackground(new java.awt.Color(51, 51, 51));
        panelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelMonAn.setBackground(new java.awt.Color(51, 51, 51));
        panelMonAn.setPreferredSize(new java.awt.Dimension(188, 72));
        panelMonAn.setRequestFocusEnabled(false);
        panelMonAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelMonAnMouseClicked(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Cascadia Code", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Dish");

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/food_23px.png"))); // NOI18N

        javax.swing.GroupLayout panelMonAnLayout = new javax.swing.GroupLayout(panelMonAn);
        panelMonAn.setLayout(panelMonAnLayout);
        panelMonAnLayout.setHorizontalGroup(
            panelMonAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMonAnLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelMonAnLayout.setVerticalGroup(
            panelMonAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMonAnLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelMonAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        panelMenu.add(panelMonAn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 397, 218, 53));

        panelHoaDon.setBackground(new java.awt.Color(51, 51, 51));
        panelHoaDon.setPreferredSize(new java.awt.Dimension(188, 72));
        panelHoaDon.setRequestFocusEnabled(false);
        panelHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelHoaDonMouseClicked(evt);
            }
        });

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/receipt_26px.png"))); // NOI18N

        jLabel20.setFont(new java.awt.Font("Cascadia Code", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Invoice");

        javax.swing.GroupLayout panelHoaDonLayout = new javax.swing.GroupLayout(panelHoaDon);
        panelHoaDon.setLayout(panelHoaDonLayout);
        panelHoaDonLayout.setHorizontalGroup(
            panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHoaDonLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelHoaDonLayout.setVerticalGroup(
            panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHoaDonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        panelMenu.add(panelHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 218, 50));

        panelDanhMuc.setBackground(new java.awt.Color(51, 51, 51));
        panelDanhMuc.setPreferredSize(new java.awt.Dimension(188, 72));
        panelDanhMuc.setRequestFocusEnabled(false);
        panelDanhMuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelDanhMucMouseClicked(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Cascadia Code", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Categories");

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/sorting_23px.png"))); // NOI18N

        javax.swing.GroupLayout panelDanhMucLayout = new javax.swing.GroupLayout(panelDanhMuc);
        panelDanhMuc.setLayout(panelDanhMucLayout);
        panelDanhMucLayout.setHorizontalGroup(
            panelDanhMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDanhMucLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelDanhMucLayout.setVerticalGroup(
            panelDanhMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDanhMucLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelDanhMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        panelMenu.add(panelDanhMuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 344, 218, 53));

        panelTrangChu.setBackground(new java.awt.Color(51, 51, 51));
        panelTrangChu.setPreferredSize(new java.awt.Dimension(188, 72));
        panelTrangChu.setRequestFocusEnabled(false);
        panelTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelTrangChuMouseClicked(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Cascadia Code", 0, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("HOME");

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/home_23px.png"))); // NOI18N

        javax.swing.GroupLayout panelTrangChuLayout = new javax.swing.GroupLayout(panelTrangChu);
        panelTrangChu.setLayout(panelTrangChuLayout);
        panelTrangChuLayout.setHorizontalGroup(
            panelTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTrangChuLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        panelTrangChuLayout.setVerticalGroup(
            panelTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTrangChuLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(panelTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );

        panelMenu.add(panelTrangChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 83, 218, 71));

        jLabel60.setBackground(new java.awt.Color(51, 51, 51));
        jLabel60.setFont(new java.awt.Font("Cascadia Code", 1, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("Minion Food");
        panelMenu.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 800, 180, 30));

        panelCTHD.setBackground(new java.awt.Color(51, 51, 51));
        panelCTHD.setPreferredSize(new java.awt.Dimension(188, 72));
        panelCTHD.setRequestFocusEnabled(false);
        panelCTHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelCTHDMouseClicked(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Cascadia Code", 0, 18)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("Invoice detail");

        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/clickcollect_28px.png"))); // NOI18N

        javax.swing.GroupLayout panelCTHDLayout = new javax.swing.GroupLayout(panelCTHD);
        panelCTHD.setLayout(panelCTHDLayout);
        panelCTHDLayout.setHorizontalGroup(
            panelCTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCTHDLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelCTHDLayout.setVerticalGroup(
            panelCTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCTHDLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelCTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        panelMenu.add(panelCTHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 218, 53));

        panelKhuyenMai.setBackground(new java.awt.Color(51, 51, 51));
        panelKhuyenMai.setPreferredSize(new java.awt.Dimension(188, 72));
        panelKhuyenMai.setRequestFocusEnabled(false);
        panelKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelKhuyenMaiMouseClicked(evt);
            }
        });

        jLabel78.setFont(new java.awt.Font("Cascadia Code", 0, 18)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setText("Promotions");

        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/cash_register_28px.png"))); // NOI18N

        javax.swing.GroupLayout panelKhuyenMaiLayout = new javax.swing.GroupLayout(panelKhuyenMai);
        panelKhuyenMai.setLayout(panelKhuyenMaiLayout);
        panelKhuyenMaiLayout.setHorizontalGroup(
            panelKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKhuyenMaiLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        panelKhuyenMaiLayout.setVerticalGroup(
            panelKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKhuyenMaiLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(panelKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelMenu.add(panelKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 218, 53));

        tfmaNV.setBackground(new java.awt.Color(51, 51, 51));
        tfmaNV.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        tfmaNV.setForeground(new java.awt.Color(255, 255, 255));
        tfmaNV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfmaNV.setBorder(null);
        tfmaNV.setEnabled(false);
        panelMenu.add(tfmaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 680, 200, 40));

        tfTenDN.setBackground(new java.awt.Color(51, 51, 51));
        tfTenDN.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        tfTenDN.setForeground(new java.awt.Color(255, 255, 255));
        tfTenDN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfTenDN.setBorder(null);
        tfTenDN.setEnabled(false);
        panelMenu.add(tfTenDN, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 730, 200, 40));

        panelGiaoDien.setBackground(new java.awt.Color(255, 255, 255));
        panelGiaoDien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnTrangChu.setBackground(new java.awt.Color(255, 255, 255));
        pnTrangChu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        btnMinimize6.setBackground(new java.awt.Color(49, 139, 130));
        btnMinimize6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/subtract_22px.png"))); // NOI18N
        btnMinimize6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinimize6MouseClicked(evt);
            }
        });

        lbThoat8.setBackground(new java.awt.Color(49, 139, 130));
        lbThoat8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/Close_22px.png"))); // NOI18N
        lbThoat8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbThoat8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(1143, Short.MAX_VALUE)
                .addComponent(btnMinimize6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbThoat8)
                .addGap(18, 18, 18))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbThoat8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMinimize6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/exit.png"))); // NOI18N
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/logout_36px.png"))); // NOI18N
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(49, 139, 130));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Log out");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(49, 139, 130));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Exit");
        jLabel26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/account_30px.png"))); // NOI18N
        btnProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfileActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(49, 139, 130));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Profile");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(374, 374, 374))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel23Layout.createSequentialGroup()
                            .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jLabel26))
                        .addGroup(jPanel23Layout.createSequentialGroup()
                            .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(jLabel25)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addComponent(btnProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel27)))
                .addGap(0, 16, Short.MAX_VALUE))
        );

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        jLabel53.setBackground(new java.awt.Color(255, 255, 255));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/hellohome.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE)
                .addGap(45, 45, 45))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        pnTrangChu.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 820));

        jTabbedPane1.addTab("tab8", pnTrangChu);

        pnHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        pnHoaDon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        btnMinimize.setBackground(new java.awt.Color(49, 139, 130));
        btnMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/subtract_22px.png"))); // NOI18N
        btnMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinimizeMouseClicked(evt);
            }
        });

        lbThoat2.setBackground(new java.awt.Color(49, 139, 130));
        lbThoat2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/Close_22px.png"))); // NOI18N
        lbThoat2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbThoat2MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Cascadia Code", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(49, 139, 130));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/receipt_48px.png"))); // NOI18N
        jLabel4.setText("Invoice");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1063, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMinimize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbThoat2)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnMinimize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbThoat2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnHoaDon.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 16, 1194, 80));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(49, 139, 130));
        jLabel54.setText("Dish name:");

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(49, 139, 130));
        jLabel55.setText("Dish code:");

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(49, 139, 130));
        jLabel56.setText("Staff:");

        jLabel57.setBackground(new java.awt.Color(49, 139, 130));
        jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(49, 139, 130));
        jLabel57.setText("Staff code:");

        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(49, 139, 130));
        jLabel58.setText("Price:");

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(49, 139, 130));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setText("Amount:");

        tfGiaTien.setEnabled(false);

        tfMaMon.setEnabled(false);

        tfSoLuong.setText("1");
        tfSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSoLuongActionPerformed(evt);
            }
        });

        cbTenMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTenMonActionPerformed(evt);
            }
        });

        cbTenNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTenNVActionPerformed(evt);
            }
        });

        tfMaNVHD.setEnabled(false);

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(49, 139, 130));
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setText("Dish type:");
        jLabel62.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tfTenLoaiMon.setEnabled(false);

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(49, 139, 130));
        jLabel63.setText("Quantity remaining:");

        tfSoLuongTon.setEnabled(false);

        jLabel73.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(49, 139, 130));
        jLabel73.setText("Toltal:");

        tfThanhTien.setEnabled(false);

        cbCTGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCTGiamGiaActionPerformed(evt);
            }
        });

        jLabel80.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(49, 139, 130));
        jLabel80.setText("Promotion name:");

        lbMaKM.setBackground(new java.awt.Color(255, 255, 255));
        lbMaKM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbMaKM.setForeground(new java.awt.Color(255, 255, 255));
        lbMaKM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMaKM.setText("Mã KM");

        tfVATHD.setForeground(new java.awt.Color(49, 139, 130));
        tfVATHD.setText("0");
        tfVATHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfVATHDActionPerformed(evt);
            }
        });

        checkVATHD.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        checkVATHD.setForeground(new java.awt.Color(49, 139, 130));
        checkVATHD.setText("VAT(%)");
        checkVATHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkVATHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tfGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tfSoLuong)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkVATHD, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(tfVATHD, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel29Layout.createSequentialGroup()
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(tfMaMon))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel29Layout.createSequentialGroup()
                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(cbTenMon, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 584, Short.MAX_VALUE))
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(jLabel63)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfSoLuongTon)
                                .addGap(315, 315, 315)))
                        .addComponent(lbMaKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfTenLoaiMon, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                                    .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbTenNV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfMaNVHD, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel73)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(jLabel80)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbCTGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(11, 11, 11))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(cbTenMon, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(tfMaMon, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfSoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfTenLoaiMon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)))
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(cbTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(tfMaNVHD, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1))
                            .addComponent(cbCTGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)))
                .addGap(10, 10, 10)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel59, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfVATHD, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(checkVATHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(15, 15, 15))
        );

        jTabbedPane4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel62.setBackground(new java.awt.Color(255, 255, 255));

        jPanel63.setBackground(new java.awt.Color(255, 255, 255));

        paneThucDon3.setBackground(new java.awt.Color(255, 255, 255));

        tabTatCaMon3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabTatCaMon3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Dish code", "Dish name", "Unit", "Price", "Amount"
            }
        ));
        tabTatCaMon3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabTatCaMon3MouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tabTatCaMon3);

        javax.swing.GroupLayout paneThucDon3Layout = new javax.swing.GroupLayout(paneThucDon3);
        paneThucDon3.setLayout(paneThucDon3Layout);
        paneThucDon3Layout.setHorizontalGroup(
            paneThucDon3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
        );
        paneThucDon3Layout.setVerticalGroup(
            paneThucDon3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneThucDon3Layout.createSequentialGroup()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelThucDonMon3.addTab("All", paneThucDon3);

        tabDoAn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabDoAn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Dish code", "Dish name", "Unit", "Price", "Amount"
            }
        ));
        tabDoAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabDoAnMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(tabDoAn);

        javax.swing.GroupLayout panelDoAnLayout = new javax.swing.GroupLayout(panelDoAn);
        panelDoAn.setLayout(panelDoAnLayout);
        panelDoAnLayout.setHorizontalGroup(
            panelDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
        );
        panelDoAnLayout.setVerticalGroup(
            panelDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDoAnLayout.createSequentialGroup()
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelThucDonMon3.addTab("Food", panelDoAn);

        tabNuocUong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabNuocUong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Dish code", "Dish name", "Unit", "Price", "Amount"
            }
        ));
        tabNuocUong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabNuocUongMouseClicked(evt);
            }
        });
        jScrollPane22.setViewportView(tabNuocUong);

        javax.swing.GroupLayout panelNuocUongLayout = new javax.swing.GroupLayout(panelNuocUong);
        panelNuocUong.setLayout(panelNuocUongLayout);
        panelNuocUongLayout.setHorizontalGroup(
            panelNuocUongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane22, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
        );
        panelNuocUongLayout.setVerticalGroup(
            panelNuocUongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuocUongLayout.createSequentialGroup()
                .addComponent(jScrollPane22, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelThucDonMon3.addTab("Drink", panelNuocUong);

        javax.swing.GroupLayout jPanel63Layout = new javax.swing.GroupLayout(jPanel63);
        jPanel63.setLayout(jPanel63Layout);
        jPanel63Layout.setHorizontalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelThucDonMon3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel63Layout.setVerticalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelThucDonMon3)
        );

        javax.swing.GroupLayout jPanel62Layout = new javax.swing.GroupLayout(jPanel62);
        jPanel62.setLayout(jPanel62Layout);
        jPanel62Layout.setHorizontalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel62Layout.setVerticalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane4.addTab("Food menu", jPanel62);

        jScrollPane11.setBackground(new java.awt.Color(255, 255, 255));

        tableKhuyenMai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "promotion code", "promotion name", "promotion (%)"
            }
        ));
        tableKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tableKhuyenMai);

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("All", jPanel56);

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5)
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5)
        );

        jTabbedPane4.addTab("Promotions", jPanel55);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnHoaDon.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 102, -1, 580));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(49, 139, 130));
        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/pos_30px.png"))); // NOI18N
        btnThanhToan.setText("Pay");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        jLabel65.setBackground(new java.awt.Color(49, 139, 130));
        jLabel65.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(49, 139, 130));
        jLabel65.setText("The money have to pay:");

        lbTienthua.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbTienthua.setForeground(new java.awt.Color(49, 139, 130));
        lbTienthua.setText("0 VNĐ");
        lbTienthua.setEnabled(false);

        lbTongTien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbTongTien.setForeground(new java.awt.Color(49, 139, 130));
        lbTongTien.setText("0 VNĐ");
        lbTongTien.setEnabled(false);

        jLabel66.setBackground(new java.awt.Color(49, 139, 130));
        jLabel66.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(49, 139, 130));
        jLabel66.setText("Toltal:");

        jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(49, 139, 130));
        jLabel61.setText("Discount:");

        jLabel74.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(49, 139, 130));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel74.setText("%");

        tfGiamGia.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tfGiamGia.setForeground(new java.awt.Color(49, 139, 130));
        tfGiamGia.setText("0.0");
        tfGiamGia.setEnabled(false);
        tfGiamGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfGiamGiaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 80, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel65, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(tfGiamGia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbTongTien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbTienthua, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnThanhToan)
                .addGap(33, 33, 33))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfGiamGia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTienthua, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(126, 126, 126))
        );

        pnHoaDon.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 700, 600, 130));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableHoaDon.setForeground(new java.awt.Color(49, 139, 130));
        tableHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableHoaDon);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 580));

        pnHoaDon.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(724, 108, 480, 580));

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));

        btnThemMoi.setForeground(new java.awt.Color(49, 139, 130));
        btnThemMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/clickcollect_28px.png"))); // NOI18N
        btnThemMoi.setText("Add ");
        btnThemMoi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThemMoi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThemMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMoiActionPerformed(evt);
            }
        });

        btnXoa4.setForeground(new java.awt.Color(49, 139, 130));
        btnXoa4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/Close_24px.png"))); // NOI18N
        btnXoa4.setText("Delete");
        btnXoa4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnXoa4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnXoa4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa4ActionPerformed(evt);
            }
        });

        btnLamMoi3.setForeground(new java.awt.Color(49, 139, 130));
        btnLamMoi3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/reset_18px.png"))); // NOI18N
        btnLamMoi3.setText("Refresh");
        btnLamMoi3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLamMoi3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLamMoi3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(btnLamMoi3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(btnXoa4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnXoa4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemMoi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                    .addComponent(btnLamMoi3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pnHoaDon.add(jPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 720, 520, 110));

        jTabbedPane1.addTab("tab1", pnHoaDon);

        panelChiTietHD.setBackground(new java.awt.Color(255, 255, 255));

        jpnalLSHĐ.setBackground(new java.awt.Color(255, 255, 255));

        tableLSHD.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        tableLSHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Invoice code", "Staff", "Date of invoice", "Total"
            }
        ));
        tableLSHD.setRowHeight(40);
        tableLSHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableLSHDMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tableLSHD);

        javax.swing.GroupLayout jpnalLSHĐLayout = new javax.swing.GroupLayout(jpnalLSHĐ);
        jpnalLSHĐ.setLayout(jpnalLSHĐLayout);
        jpnalLSHĐLayout.setHorizontalGroup(
            jpnalLSHĐLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnalLSHĐLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnalLSHĐLayout.setVerticalGroup(
            jpnalLSHĐLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnalLSHĐLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnalCTHD.setBackground(new java.awt.Color(255, 255, 255));

        tableCTHD.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        tableCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dish name", "Pricce", "Amount", "Total"
            }
        ));
        tableCTHD.setRowHeight(40);
        tableCTHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCTHDMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tableCTHD);

        javax.swing.GroupLayout jpnalCTHDLayout = new javax.swing.GroupLayout(jpnalCTHD);
        jpnalCTHD.setLayout(jpnalCTHDLayout);
        jpnalCTHDLayout.setHorizontalGroup(
            jpnalCTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnalCTHDLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE))
        );
        jpnalCTHDLayout.setVerticalGroup(
            jpnalCTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnalCTHDLayout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel44.setBackground(new java.awt.Color(255, 255, 255));

        tfThanhTienCTHD.setEditable(false);
        tfThanhTienCTHD.setBackground(new java.awt.Color(255, 255, 255));
        tfThanhTienCTHD.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        tfThanhTienCTHD.setEnabled(false);

        cboTenMonAn.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        cboTenMonAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenMonAnActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(49, 139, 130));
        jLabel41.setText("Amount");

        jLabel42.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(49, 139, 130));
        jLabel42.setText("Price");

        jLabel43.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(49, 139, 130));
        jLabel43.setText("Dish name");

        jLabel44.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(49, 139, 130));
        jLabel44.setText("Total");

        tfGiaBanCTHD.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        tfGiaBanCTHD.setEnabled(false);
        tfGiaBanCTHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfGiaBanCTHDMouseClicked(evt);
            }
        });

        tfSoLuongCTHD.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        tfSoLuongCTHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfSoLuongCTHDMouseClicked(evt);
            }
        });
        tfSoLuongCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSoLuongCTHDActionPerformed(evt);
            }
        });

        btnInHoaDonCT.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnInHoaDonCT.setForeground(new java.awt.Color(49, 139, 130));
        btnInHoaDonCT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/receipt_26px.png"))); // NOI18N
        btnInHoaDonCT.setText("invoice printing");
        btnInHoaDonCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonCTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                .addGap(20, 96, Short.MAX_VALUE)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tfSoLuongCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel43)
                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboTenMonAn, javax.swing.GroupLayout.Alignment.TRAILING, 0, 252, Short.MAX_VALUE)
                            .addComponent(tfGiaBanCTHD, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addComponent(jLabel42)
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfThanhTienCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnInHoaDonCT))
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43)
                .addGap(6, 6, 6)
                .addComponent(cboTenMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel42)
                .addGap(6, 6, 6)
                .addComponent(tfGiaBanCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel41)
                .addGap(6, 6, 6)
                .addComponent(tfSoLuongCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfThanhTienCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnInHoaDonCT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jPanel45.setBackground(new java.awt.Color(255, 255, 255));

        btnMinimize8.setBackground(new java.awt.Color(49, 139, 130));
        btnMinimize8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/subtract_22px.png"))); // NOI18N
        btnMinimize8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinimize8MouseClicked(evt);
            }
        });

        lbThoat10.setBackground(new java.awt.Color(49, 139, 130));
        lbThoat10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/Close_22px.png"))); // NOI18N
        lbThoat10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbThoat10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel45Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMinimize8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbThoat10)
                .addGap(6, 6, 6))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnMinimize8, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(lbThoat10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(49, 139, 130));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("INVOICE HISTORY");

        jLabel45.setBackground(new java.awt.Color(255, 255, 255));
        jLabel45.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(49, 139, 130));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("INVOICE DETAILS");

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(49, 139, 130));
        jLabel2.setText("Invoice code");

        tfMaHD.setEditable(false);
        tfMaHD.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(49, 139, 130));
        jLabel3.setText("Staff");

        cboTenNV.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        cboTenNV.setEnabled(false);
        cboTenNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenNVActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(49, 139, 130));
        jLabel36.setText("Total");

        tfTongTienHD.setEditable(false);
        tfTongTienHD.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N

        tfTenNV.setEnabled(false);

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel36)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(tfTongTienHD)
                    .addComponent(cboTenNV, 0, 238, Short.MAX_VALUE)
                    .addComponent(tfMaHD)
                    .addComponent(tfTenNV))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfTongTienHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelChiTietHDLayout = new javax.swing.GroupLayout(panelChiTietHD);
        panelChiTietHD.setLayout(panelChiTietHDLayout);
        panelChiTietHDLayout.setHorizontalGroup(
            panelChiTietHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChiTietHDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChiTietHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelChiTietHDLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))
                    .addGroup(panelChiTietHDLayout.createSequentialGroup()
                        .addGroup(panelChiTietHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(panelChiTietHDLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jpnalLSHĐ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(panelChiTietHDLayout.createSequentialGroup()
                        .addGroup(panelChiTietHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jpnalCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))))
        );
        panelChiTietHDLayout.setVerticalGroup(
            panelChiTietHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChiTietHDLayout.createSequentialGroup()
                .addGroup(panelChiTietHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelChiTietHDLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelChiTietHDLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelChiTietHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelChiTietHDLayout.createSequentialGroup()
                        .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4))
                    .addComponent(jpnalLSHĐ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelChiTietHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnalCTHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelChiTietHDLayout.createSequentialGroup()
                        .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab8", panelChiTietHD);

        pnDanhMuc.setBackground(new java.awt.Color(255, 255, 255));
        pnDanhMuc.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableDanhMuc.setForeground(new java.awt.Color(49, 139, 130));
        tableDanhMuc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Dish type code", "Dish type name"
            }
        ));
        tableDanhMuc.setGridColor(new java.awt.Color(255, 255, 255));
        tableDanhMuc.setSelectionBackground(new java.awt.Color(183, 221, 217));
        tableDanhMuc.setSelectionForeground(new java.awt.Color(49, 139, 130));
        tableDanhMuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDanhMucMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableDanhMuc);

        pnDanhMuc.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 142, 677, 690));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(49, 139, 130));
        jLabel5.setText("Type code");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(49, 139, 130));
        jLabel6.setText("Type name");

        tfMaDM.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tfMaDM.setEnabled(false);

        tfTenDM.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        tfLinkDM.setForeground(new java.awt.Color(255, 255, 255));
        tfLinkDM.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(tfLinkDM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(21, 21, 21)
                                .addComponent(tfMaDM, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(tfTenDM, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(83, 83, 83))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(tfMaDM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfTenDM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addComponent(tfLinkDM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnDanhMuc.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 190, 510, 170));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        btnMinimize1.setBackground(new java.awt.Color(49, 139, 130));
        btnMinimize1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/subtract_22px.png"))); // NOI18N
        btnMinimize1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinimize1MouseClicked(evt);
            }
        });

        lbThoat.setBackground(new java.awt.Color(49, 139, 130));
        lbThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/Close_22px.png"))); // NOI18N
        lbThoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbThoatMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Cascadia Code", 1, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(49, 139, 130));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/chef_60px.png"))); // NOI18N
        jLabel7.setText("MANAGEMENT LIST OF DISH ");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 939, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMinimize1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbThoat)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel7))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMinimize1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 28, Short.MAX_VALUE))
        );

        pnDanhMuc.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 1201, -1));

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pnDanhMuc.add(jPanel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(689, 142, -1, 617));

        jPanel42.setBackground(new java.awt.Color(255, 255, 255));

        btnThemDM.setForeground(new java.awt.Color(49, 139, 130));
        btnThemDM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/reset_18px.png"))); // NOI18N
        btnThemDM.setText("RESET");
        btnThemDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDMActionPerformed(evt);
            }
        });

        btnLuuDM.setForeground(new java.awt.Color(49, 139, 130));
        btnLuuDM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/reset_28px.png"))); // NOI18N
        btnLuuDM.setText("SAVE");
        btnLuuDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuDMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(btnThemDM, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(btnLuuDM, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemDM, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuuDM, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(87, 87, 87))
        );

        pnDanhMuc.add(jPanel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 380, 510, 150));

        jTabbedPane1.addTab("tab2", pnDanhMuc);

        pnMonAn.setBackground(new java.awt.Color(255, 255, 255));
        pnMonAn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(49, 139, 130));
        jLabel8.setText("Dish code:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(49, 139, 130));
        jLabel9.setText("Dish name:");

        tfMaMonAn.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tfMaMonAn.setEnabled(false);

        tfTenMonAn.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btnOpen.setForeground(new java.awt.Color(49, 139, 130));
        btnOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/openfolder_26px.png"))); // NOI18N
        btnOpen.setText("Open Image");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(49, 139, 130));
        jLabel11.setText("Unit:");

        tfDonViTinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(49, 139, 130));
        jLabel12.setText("Price:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(49, 139, 130));
        jLabel13.setText("Amount:");

        tfGiaTienMon.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        tfTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tfTimKiem.setForeground(new java.awt.Color(49, 139, 130));

        btnTimKiem.setForeground(new java.awt.Color(49, 139, 130));
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/search_18px.png"))); // NOI18N
        btnTimKiem.setText("Search");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        lbLoai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbLoai.setForeground(new java.awt.Color(49, 139, 130));
        lbLoai.setText("Dish type code:");
        lbLoai.setToolTipText("");

        cbLoaiMon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbLoaiMon.setForeground(new java.awt.Color(49, 139, 130));
        cbLoaiMon.setToolTipText("");
        cbLoaiMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLoaiMonActionPerformed(evt);
            }
        });

        lbGia1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbGia1.setForeground(new java.awt.Color(49, 139, 130));
        lbGia1.setText("Type name:");
        lbGia1.setToolTipText("");

        tfTenLoai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfTenLoai.setForeground(new java.awt.Color(49, 139, 130));
        tfTenLoai.setToolTipText("");
        tfTenLoai.setEnabled(false);
        tfTenLoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfTenLoaiKeyReleased(evt);
            }
        });

        tfLinkMonAn.setForeground(new java.awt.Color(255, 255, 255));
        tfLinkMonAn.setText("jTextField1");
        tfLinkMonAn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jPanel39.setBackground(new java.awt.Color(255, 255, 255));

        lbImageFood.setBackground(new java.awt.Color(255, 255, 255));
        lbImageFood.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbImageFood.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/no-image209X214.png"))); // NOI18N
        lbImageFood.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lbImageFood.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbImageFoodMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(lbImageFood, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbImageFood, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(tfLinkMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(89, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbLoai)
                            .addComponent(lbGia1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbLoaiMon, 0, 270, Short.MAX_VALUE)
                            .addComponent(tfTenLoai)))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(jLabel8))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tfMaMonAn, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                .addComponent(tfTenMonAn))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfDonViTinh)
                            .addComponent(tfGiaTienMon)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(tfSoLuongMon, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                                .addComponent(btnOpen)
                                .addGap(23, 23, 23))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)))
                .addGap(62, 62, 62))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tfMaMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tfTenMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbLoaiMon, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbGia1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfTenLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfDonViTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(tfGiaTienMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(tfSoLuongMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOpen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addComponent(tfLinkMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );

        pnMonAn.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(692, 136, -1, 620));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        btnMinimize2.setBackground(new java.awt.Color(49, 139, 130));
        btnMinimize2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/subtract_22px.png"))); // NOI18N
        btnMinimize2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinimize2MouseClicked(evt);
            }
        });

        lbThoat1.setBackground(new java.awt.Color(49, 139, 130));
        lbThoat1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/Close_22px.png"))); // NOI18N
        lbThoat1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbThoat1MouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Cascadia Code", 1, 48)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(49, 139, 130));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/dinner_60px.png"))); // NOI18N
        jLabel14.setText("DISH MANAGEMENT");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMinimize2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbThoat1)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbThoat1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(btnMinimize2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnMonAn.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 1204, -1));

        tableFood.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Dish code", "Dish name", "Dish type", "Unit", "Price", "Amount", "Image"
            }
        ));
        tableFood.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableFoodMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableFood);

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );

        pnMonAn.add(jPanel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 106, -1, 730));

        jPanel41.setBackground(new java.awt.Color(255, 255, 255));
        jPanel41.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLuuMon.setForeground(new java.awt.Color(49, 139, 130));
        btnLuuMon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/save_28px.png"))); // NOI18N
        btnLuuMon.setText("Save");
        btnLuuMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuMonActionPerformed(evt);
            }
        });
        jPanel41.add(btnLuuMon, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, 48));

        btnSuaMon.setForeground(new java.awt.Color(49, 139, 130));
        btnSuaMon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/edit_28px.png"))); // NOI18N
        btnSuaMon.setText("Update");
        btnSuaMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaMonActionPerformed(evt);
            }
        });
        jPanel41.add(btnSuaMon, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, -1, 48));

        btnXoaMon.setForeground(new java.awt.Color(49, 139, 130));
        btnXoaMon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/Delete_28px.png"))); // NOI18N
        btnXoaMon.setText("Delete");
        btnXoaMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaMonActionPerformed(evt);
            }
        });
        jPanel41.add(btnXoaMon, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, -1, 48));

        btnThemMon.setForeground(new java.awt.Color(49, 139, 130));
        btnThemMon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/reset_18px.png"))); // NOI18N
        btnThemMon.setText("Reset");
        btnThemMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMonActionPerformed(evt);
            }
        });
        jPanel41.add(btnThemMon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, 48));

        pnMonAn.add(jPanel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 750, 490, 70));

        jTabbedPane1.addTab("tab3", pnMonAn);

        pnKhuyenMai.setBackground(new java.awt.Color(255, 255, 255));

        tableDSKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Promotion code", "Promotion name", "Discount"
            }
        ));
        tableDSKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDSKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(tableDSKhuyenMai);

        jPanel54.setBackground(new java.awt.Color(255, 255, 255));

        btnMinimize11.setBackground(new java.awt.Color(49, 139, 130));
        btnMinimize11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/subtract_22px.png"))); // NOI18N
        btnMinimize11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinimize11MouseClicked(evt);
            }
        });

        lbThoat13.setBackground(new java.awt.Color(49, 139, 130));
        lbThoat13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/Close_22px.png"))); // NOI18N
        lbThoat13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbThoat13MouseClicked(evt);
            }
        });

        jLabel95.setFont(new java.awt.Font("Cascadia Code", 1, 48)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(49, 139, 130));
        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel95.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/sale_50px.png"))); // NOI18N
        jLabel95.setText("PROMOTION MANAGEMENT");

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel95, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMinimize11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbThoat13)
                .addContainerGap())
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                        .addGap(0, 18, Short.MAX_VALUE)
                        .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbThoat13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMinimize11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel57.setBackground(new java.awt.Color(255, 255, 255));

        jLabel96.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(49, 139, 130));
        jLabel96.setText("Promotion code:");

        jLabel97.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(49, 139, 130));
        jLabel97.setText("Promotion name:");

        tfMaKM.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tfMaKM.setEnabled(false);

        tfTenKM.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel98.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(49, 139, 130));
        jLabel98.setText("Promotion price:");

        btnLuuKM.setForeground(new java.awt.Color(49, 139, 130));
        btnLuuKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/save_28px.png"))); // NOI18N
        btnLuuKM.setText("Save");
        btnLuuKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuKMActionPerformed(evt);
            }
        });

        btnThemKM.setForeground(new java.awt.Color(49, 139, 130));
        btnThemKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/reset_18px.png"))); // NOI18N
        btnThemKM.setText("Reset");
        btnThemKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKMActionPerformed(evt);
            }
        });

        tfGiaKM.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel57Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel97)
                    .addComponent(jLabel96)
                    .addComponent(jLabel98))
                .addGap(33, 33, 33)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfGiaKM)
                    .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tfTenKM, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                        .addComponent(tfMaKM)))
                .addGap(60, 60, 60))
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(btnThemKM, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnLuuKM, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel96)
                    .addComponent(tfMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel97)
                    .addComponent(tfTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel98)
                    .addComponent(tfGiaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemKM, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuuKM, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(295, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnKhuyenMaiLayout = new javax.swing.GroupLayout(pnKhuyenMai);
        pnKhuyenMai.setLayout(pnKhuyenMaiLayout);
        pnKhuyenMaiLayout.setHorizontalGroup(
            pnKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel54, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnKhuyenMaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel57, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnKhuyenMaiLayout.setVerticalGroup(
            pnKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKhuyenMaiLayout.createSequentialGroup()
                .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane13)))
        );

        jTabbedPane1.addTab("tab9", pnKhuyenMai);

        panelGiaoDien.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, 1210, 880));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelGiaoDien, javax.swing.GroupLayout.DEFAULT_SIZE, 1221, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGiaoDien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void lbThoat1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbThoat1MouseClicked
        int click=JOptionPane.showConfirmDialog(null, "Do you want to exit the program?", "Message", 2);
        if(click==JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_lbThoat1MouseClicked

    private void btnMinimize2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimize2MouseClicked
        this.setState(this.ICONIFIED);
    }//GEN-LAST:event_btnMinimize2MouseClicked

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
    }//GEN-LAST:event_btnOpenActionPerformed

    public void DisableMA(){
        tfMaMon.setEnabled(false);
        tfTenMonAn.setEnabled(false);
        cbLoaiMon.setEnabled(false);
        tfTenLoai.setEnabled(false);
        tfDonViTinh.setEnabled(false);
        tfGiaTienMon.setEnabled(false);
        tfSoLuongMon.setEnabled(false);
    }
    
    public void EnableMA(){
        tfTenMonAn.setEnabled(true);
        cbLoaiMon.setEnabled(true);
        tfDonViTinh.setEnabled(true);
        tfGiaTienMon.setEnabled(true);
        tfSoLuongMon.setEnabled(true);
        btnThemDM.setEnabled(true);
    }
    
     private void loadLoaiMonAn() {
        String sql = " SELECT * FROM DanhMucMon WHERE maLoai = maLoai";
        try{
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                this.cbLoaiMon.addItem(rs.getString("maLoai"));
            }
            } catch(Exception e){

            }
      }
     
    public void RemoveMA(){
        tfMaMonAn.setText("");
        tfTenMonAn.setText("");
        tfTenLoai.setText("");
        tfDonViTinh.setText("");
        tfGiaTienMon.setText("");
        tfSoLuongMon.setText("");
        ImageIcon icon = new ImageIcon((getClass().getResource("/IMAGES/no-image.png")));
        Image img = icon.getImage().getScaledInstance(lbImageFood.getWidth(), lbImageFood.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaIcon = new ImageIcon(img);
        lbImageFood.setIcon(scaIcon);
        tableFood.clearSelection();
    }
    
    private void btnThemMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMonActionPerformed
        EnableMA();
        RemoveMA();
        cbLoaiMon.removeAllItems();
            loadLoaiMonAn();
        btnLuuMon.setEnabled(true);
        btnXoaMon.setEnabled(false);
        btnSuaMon.setEnabled(false);
        //lbImageFood.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/no-image.png")));
    }//GEN-LAST:event_btnThemMonActionPerformed

    private void lbThoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbThoatMouseClicked
        int click=JOptionPane.showConfirmDialog(null, "Do you want to exit the program?", "Message", 2);
        if(click==JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_lbThoatMouseClicked

    private void btnMinimize1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimize1MouseClicked
        this.setState(this.ICONIFIED);
    }//GEN-LAST:event_btnMinimize1MouseClicked

    public void RemoveDM(){
        tfMaDM.setText("");
        tfTenDM.setText("");
        tableDanhMuc.clearSelection();
    }
    
    public void DisableDM(){
        tfMaDM.setEnabled(false);
        tfTenDM.setEnabled(false);
    }
    
    public void EnableDM(){
        tfTenDM.setEnabled(true);
        btnThemDM.setEnabled(true);
        btnLuuDM.setEnabled(true);
    }
    
    private void btnThemDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDMActionPerformed
        EnableDM();
        RemoveDM();
    }//GEN-LAST:event_btnThemDMActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        int click=JOptionPane.showConfirmDialog(null, "Do you want to exit the program?", "Message", 2);
        if(click==JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        int Click = JOptionPane.showConfirmDialog(null, "Do you want to exit the program?", "Message",2);
        if(Click ==JOptionPane.YES_OPTION){
            this.setVisible(false);
            DangNhap dangnhap = new DangNhap();
            dangnhap.setVisible(true);
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    
    private void btnXoaMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaMonActionPerformed
        int row = tableFood.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(NhanVienfr.this, "Please select the table to delete!","Message",JOptionPane.ERROR_MESSAGE);
        }else{
            int xacNhan = JOptionPane.showConfirmDialog(NhanVienfr.this, "Are you sure you want to delete?","Message",JOptionPane.YES_NO_OPTION);
            if(xacNhan == JOptionPane.YES_OPTION){
                String maMonAn = String.valueOf(tableFood.getValueAt(row, 0));
                thucDonSERVICE.xoaThucDon(maMonAn);
            }
        }
        RefeshMA();
    }//GEN-LAST:event_btnXoaMonActionPerformed

    private void cbLoaiMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLoaiMonActionPerformed
        String sql = "SELECT tenLoai FROM DanhMucMon WHERE maLoai = ?";
        try{
            String maTheLoaiString = (String) cbLoaiMon.getSelectedItem();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, maTheLoaiString);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                tfTenLoai.setText(rs.getString("tenLoai"));
            }
        } catch(Exception e){

        }
    }//GEN-LAST:event_cbLoaiMonActionPerformed

    private void tfTenLoaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTenLoaiKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTenLoaiKeyReleased

    private void tableFoodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableFoodMouseClicked
        EnableMA();
        cbLoaiMon.removeAllItems();
        int click=tableFood.getSelectedRow();
        TableModel model=tableFood.getModel();

        tfMaMonAn.setText(model.getValueAt(click, 0).toString());
        tfTenMonAn.setText(model.getValueAt(click, 1).toString());
        cbLoaiMon.addItem(model.getValueAt(click, 2).toString());
        tfDonViTinh.setText(model.getValueAt(click, 3).toString());
        String []s1=model.getValueAt(click,4).toString().split("\\s");
        tfGiaTienMon.setText(s1[0]);
        tfSoLuongMon.setText(model.getValueAt(click, 5).toString());
        String imageFood = model.getValueAt(click, 6).toString();
        tfLinkMonAn.setText(model.getValueAt(click, 6).toString());
        
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/AnhMonAn/"+imageFood));
        Image image = imageIcon.getImage().getScaledInstance(lbImageFood.getWidth(), lbImageFood.getHeight(), Image.SCALE_SMOOTH);
        lbImageFood.setIcon(new ImageIcon(image)); 
        
        btnLuuMon.setEnabled(false);
        btnSuaMon.setEnabled(true);
        btnXoaMon.setEnabled(true);
        tfMaMonAn.setEnabled(false);
    }//GEN-LAST:event_tableFoodMouseClicked

    private boolean kiemtraNullMonAn(){
        if(tfTenMonAn.getText().equals("")){
            JOptionPane.showMessageDialog(NhanVienfr.this,"Please enter dish name!");
            return false;
        }
        else
        if(tfDonViTinh.getText().equals("")){
            JOptionPane.showMessageDialog(NhanVienfr.this,"Please enter unit!");
            return false;
        }
        else   
        if(tfSoLuongMon.getText().equals("")){
            JOptionPane.showMessageDialog(NhanVienfr.this,"Please enter the number of dishes!");
            return false;
        }
        else
        if(cbLoaiMon.getSelectedItem().equals("")){
           JOptionPane.showMessageDialog(NhanVienfr.this,"Please choose the type of dish!");
            return false;
        }
        else{ 
        if(tfGiaTienMon.getText().equals("")){
            JOptionPane.showMessageDialog(NhanVienfr.this,"Please enter the price!");
            return false;
        }
        else{ 
            return true;
        }
        }
    }
    
    private void btnLuuMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuMonActionPerformed
       if(kiemtraNullMonAn()){                 
        thucDon.setTenMonAn(tfTenMonAn.getText());              
        thucDon.setMaLoaiMon(Integer.parseInt(cbLoaiMon.getSelectedItem().toString()));              
        thucDon.setDonVi(tfDonViTinh.getText());         
        thucDon.setGiaTien(Float.valueOf(tfGiaTienMon.getText()));                 
        thucDon.setSoLuong(Integer.parseInt(tfSoLuongMon.getText()));   
        thucDon.setImageMonAn(tfLinkMonAn.getText()); 
        
        thucDonSERVICE.themThucDon(thucDon);
            JOptionPane.showMessageDialog(null, "Successfully added new dishes!^^");
        RefeshMA();
        hienThiTatCa();
        hienThiDoAn();
        hienThiNuocUong();
       }
    }//GEN-LAST:event_btnLuuMonActionPerformed

    private void btnSuaMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaMonActionPerformed
        tfMaMon.setEnabled(false);
        
        int row = tableFood.getSelectedRow();   
        if(row == -1){
            JOptionPane.showMessageDialog(NhanVienfr.this, "Please select the item to be repaired","Message",JOptionPane.ERROR_MESSAGE);
        }else{
            int xacNhan = JOptionPane.showConfirmDialog(NhanVienfr.this, "Are you sure you want to fix it?","Message",JOptionPane.YES_NO_OPTION);
            if(xacNhan == JOptionPane.YES_OPTION){
                
            thucDon.setMaMonAn(Integer.parseInt(tfMaMonAn.getText()));
            thucDon.setTenMonAn(tfTenMonAn.getText());
            thucDon.setMaLoaiMon(Integer.parseInt(cbLoaiMon.getItemAt(cbLoaiMon.getSelectedIndex())));
            thucDon.setDonVi(tfDonViTinh.getText());   
            thucDon.setGiaTien(Float.valueOf(tfGiaTienMon.getText()));       
            thucDon.setSoLuong(Integer.parseInt(tfSoLuongMon.getText()));   
            thucDon.setImageMonAn(tfLinkMonAn.getText()); 
            
               // String maBan = String.valueOf(tableNV.getValueAt(row, 0));
                thucDonSERVICE.suaThucDon(thucDon);
                JOptionPane.showMessageDialog(null, "Edited dish information successfully!^^");
                RefeshMA();
            }
        }
    }//GEN-LAST:event_btnSuaMonActionPerformed

    private void lbImageFoodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbImageFoodMouseClicked
            ChonAnhMA();
    }//GEN-LAST:event_lbImageFoodMouseClicked
    
    private void timkiemMonAn(String query){
           TableRowSorter<DefaultTableModel> tbl = new TableRowSorter<DefaultTableModel>(modelThucDon);
           tableFood.setRowSorter(tbl);
           tbl.setRowFilter(RowFilter.regexFilter(query));
    }
           
    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        String query = tfTimKiem.getText();
        timkiemMonAn(query);
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void tableDanhMucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDanhMucMouseClicked
        EnableDM();
        int click=tableDanhMuc.getSelectedRow();
        TableModel model=tableDanhMuc.getModel();

        tfMaDM.setText(model.getValueAt(click, 0).toString());
        tfTenDM.setText(model.getValueAt(click, 1).toString());
        tfMaDM.setEnabled(false);
    }//GEN-LAST:event_tableDanhMucMouseClicked

    private void btnLuuDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuDMActionPerformed
        if(kiemtraNullDM()){                           
        danhMuc.setTenLoai(tfTenDM.getText());            
        danhMucSERVICE.themDanhMuc(danhMuc);
            JOptionPane.showMessageDialog(null, "Successfully added new dishes!^^");
        RefeshDM();
        }
    }//GEN-LAST:event_btnLuuDMActionPerformed

    private boolean kiemtraNullDM(){
        if(tfTenDM.getText().equals("")){
            JOptionPane.showMessageDialog(NhanVienfr.this,"Please enter dish type");
            return false;
        }
        else{ 
            return true;
        }
     }
    
    private void lbThoat8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbThoat8MouseClicked
         int click=JOptionPane.showConfirmDialog(null, "Do you want to exit the program?", "Message", 2);
        if(click==JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_lbThoat8MouseClicked

    private void btnMinimize6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimize6MouseClicked
            this.setState(this.ICONIFIED);
    }//GEN-LAST:event_btnMinimize6MouseClicked

    private void congLaiSoLuong(int maMon, int soLuong){
        try{
            String sql="UPDATE MonAn SET soLuong=soLuong + ? WHERE maMon=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1, soLuong);
            ps.setInt(2, maMon);
            ps.executeUpdate(); 
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void truSoLuongSauKhiCN(String tenMon, int soLuong){
        try{
            String sql="UPDATE MonAn SET soLuong=soLuong - ? WHERE tenMon=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1, soLuong);
            ps.setString(2, tenMon);
            ps.executeUpdate(); 
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void tableLSHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLSHDMouseClicked
        dongTextBoxCTHD();
        int row=tableLSHD.getSelectedRow();
        int maHD=Integer.parseInt(tableLSHD.getValueAt(row, 0).toString());
        String tenNV=tableLSHD.getValueAt(row, 1).toString();
        float tongTien=Float.parseFloat(tableLSHD.getValueAt(row, 3).toString());
        Locale VN = new Locale("vi", "VN");
        Currency vnd = Currency.getInstance(VN);
        NumberFormat VNDFormat = NumberFormat.getCurrencyInstance(VN);
        tfTongTienHD.setText(VNDFormat.format(tongTien));
        tfMaHD.setText(tableLSHD.getValueAt(row, 0).toString());
        cboTenNV.getModel().setSelectedItem(tenNV);
        hienThitableCTHDTheoMaHD(maHD);
    }//GEN-LAST:event_tableLSHDMouseClicked

    private void tableCTHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCTHDMouseClicked
        int row=tableCTHD.getSelectedRow();
        cboTenMonAn.getModel().setSelectedItem(tableCTHD.getValueAt(row, 0));
        tfGiaBanCTHD.setText(tableCTHD.getValueAt(row, 1).toString());
        tfSoLuongCTHD.setText(tableCTHD.getValueAt(row, 2).toString());
        float thanhtien=Float.parseFloat(tableCTHD.getValueAt(row, 3).toString());
        Locale VN = new Locale("vi", "VN");
        Currency vnd = Currency.getInstance(VN);
        NumberFormat VNDFormat = NumberFormat.getCurrencyInstance(VN);
        tfThanhTienCTHD.setText(VNDFormat.format(thanhtien));
    }//GEN-LAST:event_tableCTHDMouseClicked

    private void tfGiaBanCTHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfGiaBanCTHDMouseClicked
        // TODO add your handling code here:
        tfThanhTienCTHD.setText("");
    }//GEN-LAST:event_tfGiaBanCTHDMouseClicked

    private void tfSoLuongCTHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfSoLuongCTHDMouseClicked
        // TODO add your handling code here:
        tfThanhTienCTHD.setText("");
    }//GEN-LAST:event_tfSoLuongCTHDMouseClicked

    private void btnMinimize8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimize8MouseClicked
         this.setState(this.ICONIFIED);
    }//GEN-LAST:event_btnMinimize8MouseClicked

    private void lbThoat10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbThoat10MouseClicked
         int click=JOptionPane.showConfirmDialog(null, "Do you want to exit the program or not?", "Message", 2);
        if(click==JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_lbThoat10MouseClicked

    private void cboTenNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenNVActionPerformed
       String sql = "SELECT tenNV FROM NhanVien WHERE maNV = ?";
        try{
            String maNV = (String) cboTenNV.getSelectedItem();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, maNV);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                tfTenNV.setText(rs.getString("tenNV"));
            }
        } catch(Exception e){

        }
    }//GEN-LAST:event_cboTenNVActionPerformed
    
    private void btnMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseClicked
        this.setState(this.ICONIFIED);
    }//GEN-LAST:event_btnMinimizeMouseClicked

    private void lbThoat2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbThoat2MouseClicked
        int click=JOptionPane.showConfirmDialog(null, "Do you want to exit the program?", "Message", 2);
        if(click==JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_lbThoat2MouseClicked

    private void tfSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSoLuongActionPerformed
        float thanhTienMua = Float.valueOf(tfSoLuong.getText())*Float.valueOf(tfGiaTien.getText());
        tfThanhTien.setText(""+thanhTienMua);
    }//GEN-LAST:event_tfSoLuongActionPerformed

    private void cbTenMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTenMonActionPerformed
        String sql = "  SELECT maMon, tenLoai, soLuong, giaTien FROM MonAn, DanhMucMon WHERE maLoai=maLoaiMon AND tenMon = ?";
        try{
            String maTheLoaiString = (String) cbTenMon.getSelectedItem();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, maTheLoaiString);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                tfMaMon.setText(rs.getString("maMon"));
                tfTenLoaiMon.setText(rs.getString("tenLoai"));
                tfSoLuongTon.setText(rs.getString("soLuong"));
                tfGiaTien.setText(rs.getString("giaTien"));
            }
        } catch(Exception e){

        }
    }//GEN-LAST:event_cbTenMonActionPerformed

    private void cbTenNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTenNVActionPerformed
        String sql = "SELECT maNV FROM NhanVien WHERE tenNV = ?";
        try{
            String maTheLoaiString = (String) cbTenNV.getSelectedItem();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, maTheLoaiString);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                tfMaNVHD.setText(rs.getString("maNV"));
            }
        } catch(Exception e){

        }
    }//GEN-LAST:event_cbTenNVActionPerformed

    private void cbCTGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCTGiamGiaActionPerformed
        String sql = "  SELECT maKhuyenMai, tenKhuyenMai, giaKhuyenMai FROM KhuyenMai WHERE tenKhuyenMai = ?";
        try{
            String maKM = (String) cbCTGiamGia.getSelectedItem();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, maKM);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                lbMaKM.setText(rs.getString("maKM"));
                cbCTGiamGia.setSelectedItem(rs.getString("tenKhuyenMai"));
                tfGiamGia.setText(rs.getString("giaKM"));
            }
        } catch(Exception e){

        }
    }//GEN-LAST:event_cbCTGiamGiaActionPerformed

    private void tabTatCaMon3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabTatCaMon3MouseClicked
        cbTenMon.removeAllItems();
        int row=tabTatCaMon3.getSelectedRow();
        int maMon=Integer.parseInt(tabTatCaMon3.getValueAt(row, 0).toString());
        String tenMon=tabTatCaMon3.getValueAt(row, 1).toString();
        String donVi=tabTatCaMon3.getValueAt(row, 2).toString();
        float giaBan= Float.parseFloat(tabTatCaMon3.getValueAt(row, 3).toString());
        int soLuong=Integer.parseInt(tabTatCaMon3.getValueAt(row, 4).toString());
        //float tongTien=Float.parseFloat(tableLSHD.getValueAt(row, 3).toString());
        //tfTongTienHD.setText(VNDFormat.format(tongTien));
        tfMaMon.setText(tabTatCaMon3.getValueAt(row, 0).toString());
        cbTenMon.getModel().setSelectedItem(tenMon);
    }//GEN-LAST:event_tabTatCaMon3MouseClicked

    private void tabDoAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabDoAnMouseClicked

        cbTenMon.removeAllItems();
        int row=tabDoAn.getSelectedRow();
        int maMon=Integer.parseInt(tabDoAn.getValueAt(row, 0).toString());
        String tenMon=tabDoAn.getValueAt(row, 1).toString();
        String donVi=tabDoAn.getValueAt(row, 2).toString();
        float giaBan= Float.parseFloat(tabDoAn.getValueAt(row, 3).toString());
        int soLuong=Integer.parseInt(tabDoAn.getValueAt(row, 4).toString());
        tfMaMon.setText(tabDoAn.getValueAt(row, 0).toString());
        cbTenMon.getModel().setSelectedItem(tenMon);
    }//GEN-LAST:event_tabDoAnMouseClicked

    private void tabNuocUongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabNuocUongMouseClicked

        cbTenMon.removeAllItems();
        int row=tabNuocUong.getSelectedRow();
        int maMon=Integer.parseInt(tabNuocUong.getValueAt(row, 0).toString());
        String tenMon=tabNuocUong.getValueAt(row, 1).toString();
        String donVi=tabNuocUong.getValueAt(row, 2).toString();
        float giaBan= Float.parseFloat(tabNuocUong.getValueAt(row, 3).toString());
        int soLuong=Integer.parseInt(tabNuocUong.getValueAt(row, 4).toString());
        //float tongTien=Float.parseFloat(tableLSHD.getValueAt(row, 3).toString());
        //tfTongTienHD.setText(VNDFormat.format(tongTien));
        tfMaMon.setText(tabNuocUong.getValueAt(row, 0).toString());
        cbTenMon.getModel().setSelectedItem(tenMon);
    }//GEN-LAST:event_tabNuocUongMouseClicked

    private float tinhTongTienKM() {
        float tongtien = 0;
        float tongtienk = 0;
        float giamGia = 0;
            float giamGiakm = Float.parseFloat(tfGiamGia.getText());
        
        if(tfGiamGia.getText().equals("0")){
            tinhTongTien();
            }
        else{
            tongtienk = tinhTongTien()*(giamGiakm/100);
            tongtien = tinhTongTien() - tongtienk;
            //tongtien = giamGia * giamGiakm;
             //double finalTotalPrice = totalPrice - (totalPrice / 100) * discount; //Tính tổng tiền khi có giảm giá
            }
        return tongtien;
    }    
    
    private void tableKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKhuyenMaiMouseClicked
        cbCTGiamGia.removeAllItems();       
        cbCTGiamGia.addItem("0"); 
        int row=tableKhuyenMai.getSelectedRow();
        int maBan=Integer.parseInt(tableKhuyenMai.getValueAt(row, 0).toString());
        String tenKM=tableKhuyenMai.getValueAt(row, 1).toString();
        String trangThaiKM=tableKhuyenMai.getValueAt(row, 2).toString();
        //float tongTien=Float.parseFloat(tableLSHD.getValueAt(row, 3).toString());
        //tfTongTienHD.setText(VNDFormat.format(tongTien));
        lbMaKM.setText(tableKhuyenMai.getValueAt(row, 0).toString());
        cbCTGiamGia.getModel().setSelectedItem(tenKM);
        tfGiamGia.setText(trangThaiKM);

        float tongTien = tinhTongTienKM();
        lbTienthua.setText(""+tongTien);
        return;
        //hienThiHoaDon(maBan);
        //hienThitableCTHDTheoMaBan(maBan);
    }//GEN-LAST:event_tableKhuyenMaiMouseClicked

    private float tinhTongTien() {
        float tongtien = 0;
        int row = tableHoaDon.getRowCount();
        for (int i = 0; i < row; i++) {
            float ThanhTien = Float.parseFloat(tableHoaDon.getValueAt(i, 4).toString());
            tongtien += ThanhTien;
        }
        return tongtien;
    }
    
    private void truSoLuongSauKhiBan(int maMon, int soLuong){
        try{
            String sql="UPDATE MonAn SET soLuong=soLuong - ? WHERE maMon=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1, soLuong);
            ps.setInt(2, maMon);
            ps.executeUpdate(); 
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void themHoaDon(int maNV, float tongTien, float giamGia, float VAT) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String dateNow = formatter.format(date).toString();
        try {   
            String sql = "INSERT INTO HoaDonTT(maNhanVien,ngayXuatHoaDon,tongTien,giamGia,VAT)  VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, maNV);
            ps.setString(2, dateNow);
            ps.setFloat(3, tongTien);
            ps.setFloat(4, giamGia);
            ps.setFloat(5, VAT);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void themCTHD(int maHoaDon, int maMonAn, String tenMonAn, float giaBan, int soLuong, float thanhTien) {
        try {
            String sql = "INSERT INTO ChiTietHD(maHoaDon,maMonAn,tenMonAn,giaBan,soLuong,thanhTien)  VALUES (?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, maHoaDon);
            ps.setInt(2, maMonAn);
            ps.setString(3, tenMonAn);
            ps.setFloat(4, giaBan);
            ps.setInt(5, soLuong);
            ps.setFloat(6, thanhTien);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void lamMoiPhieuHD() {
        tfMaMon.setText("");
        tfGiaTien.setText("");
        tfSoLuong.setText("1");
        tfMaMon.setText("");
        tfTenLoaiMon.setText("");
    }
    
    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        int rowCount = tableHoaDon.getRowCount();
        if (rowCount == -1) {
            JOptionPane.showMessageDialog(this, "No products added yet");
            return;
        } else {
            int maNV = LayMaNhanVienTheoTen(cbTenNV.getSelectedItem().toString());
            float giamGia= Float.parseFloat(tfGiamGia.getText());
            float VAT= Float.parseFloat(tfVATHD.getText());
            float tongTien = Float.parseFloat( lbTienthua.getText());

            for (int i = 0; i < rowCount; i++) {
                int maMonAn=Integer.parseInt(tableHoaDon.getValueAt(i, 0).toString());
                int soLuongBan=Integer.parseInt(tableHoaDon.getValueAt(i, 3).toString());
                int soLuongton= Integer.parseInt(tfSoLuongTon.getText());

                //String tenMonAn = tableHoaDon.getValueAt(i, 1).toString();
                String tenMon=tableHoaDon.getValueAt(i, 1).toString();

                if(soLuongBan>soLuongton){
                    JOptionPane.showMessageDialog(this, "Amount Dish" +tenMon+ " There's only: " +soLuongton);
                    return;
                }
            }

            themHoaDon(maNV, tongTien,giamGia,VAT);
            int maHoaDon = layMaHoaDon();
            for (int i = 0; i < rowCount; i++) {
                int maMonAn = Integer.parseInt(tableHoaDon.getValueAt(i, 0).toString());
                String tenMonAn = tableHoaDon.getValueAt(i, 1).toString();
                float giaBan = Float.parseFloat(tableHoaDon.getValueAt(i, 2).toString());
                int soLuongBan = Integer.parseInt(tableHoaDon.getValueAt(i, 3).toString());
                float thanhTien = Float.parseFloat(tableHoaDon.getValueAt(i, 4).toString());
                themCTHD(maHoaDon, maMonAn, tenMonAn, giaBan, soLuongBan, thanhTien);
                truSoLuongSauKhiBan(maMonAn, soLuongBan);
            }
            modelHoaDon.setRowCount(0);
            tfGiaTien.setText("");
            tfMaMon.setText("");
            tfSoLuong.setText("");
            tfVATHD.setText("0");
            tfGiamGia.setText("0");
            lbTienthua.setText("0 VNĐ");
            lbTongTien.setText("0 VNĐ");
            checkVATHD.setSelected(false);
            JOptionPane.showMessageDialog(rootPane, "Payment success ");
            hienThiTableHoaDon();
            hienThiTatCa();
            hienThiNuocUong();
            hienThiDoAn();
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void tableHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHoaDonMouseClicked
        cbTenMon.removeAllItems();

        int click=tableHoaDon.getSelectedRow();
        TableModel model=tableHoaDon.getModel();

        tfMaMon.setText(model.getValueAt(click,0).toString());
        cbTenMon.addItem(model.getValueAt(click, 1).toString());
        tfGiaTien.setText(model.getValueAt(click,2).toString());
        tfSoLuong.setText(model.getValueAt(click,3).toString());
        tfThanhTien.setText(model.getValueAt(click,4).toString());
    }//GEN-LAST:event_tableHoaDonMouseClicked

    private void btnThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMoiActionPerformed
        if (tfSoLuong.getText().equals("") || tfGiaTien.getText().equals("")
            || tfMaMon.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter enough information to buy!!");
            return;
        }
        try {
            float giaBan = Float.parseFloat(tfGiaTien.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Price must be a number!!");
            return;
        }

        int soLuongNhap = Integer.parseInt(tfSoLuong.getText());
        float giaBan = Float.parseFloat(tfGiaTien.getText());
        float thanhTien = soLuongNhap * giaBan;
        int row = tableHoaDon.getRowCount();

        //Kiểm tra thông tin món để thêm món
        for (int i = 0; i < row; i++) {

            if (tableHoaDon.getValueAt(i, 0).equals(tfMaMon.getText())) { //Kiểm tra mã món ăn
                int soLuongKho = Integer.parseInt(tableHoaDon.getValueAt(i, 3).toString()); //Lấy số lượng trong hóa
                int soLuongMua = Integer.parseInt(tfSoLuong.getText()); //Lấy số lượng muốn nhập thêm
                int tongSoLuong = soLuongKho + soLuongMua; //Cộng số lượng trong hóa đơn với số lượng mới vừa nhập lại

                float thanhTienHD = giaBan * tongSoLuong;

                tableHoaDon.setValueAt(giaBan + "", i, 2); //Cột giá tiền món
                tableHoaDon.setValueAt(tongSoLuong + "", i, 3); //Cột số lượng đã thêm
                tableHoaDon.setValueAt(thanhTienHD + "", i, 4); //Cột thành tiền món ăn

                tfGiaTien.setText("");
                tfSoLuong.setText("");
                tfMaMon.setText("");
                cbTenMon.removeAllItems();
                loadTenMon();

                return;
            }
        }

        //Thêm các thông tin đã chọn từ textfield vào table hóa đơn
        Vector<Object> vec = new Vector<>();
        vec.add(tfMaMon.getText());
        vec.add(cbTenMon.getSelectedItem().toString());
        vec.add(tfGiaTien.getText());
        vec.add(tfSoLuong.getText());
        vec.add(thanhTien + "");
        modelHoaDon.addRow(vec); //Thêm dòng mới

        tfGiaTien.setText("");
        tfSoLuong.setText("");
        tfMaMon.setText("");

        float tongTien = tinhTongTien();
        ////          Locale VN = new Locale("vi", "VN");
        ////          Currency dollars = Currency.getInstance(VN);
        ////          NumberFormat VNDFormat = NumberFormat.getCurrencyInstance(VN);
        //          lbTongTien.setText(VNDFormat.format(tongTien));
        lbTongTien.setText(""+tongTien);
        lbTienthua.setText(""+tongTien);
        return;
    }//GEN-LAST:event_btnThemMoiActionPerformed

    private void btnXoa4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa4ActionPerformed
        int row = tableHoaDon.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select the entry slip in the table to delete");
            return;
        } else {
            modelHoaDon.removeRow(row);
            double tongTien = tinhTongTien();
            Locale VN = new Locale("vi", "VN");
            Currency dollars = Currency.getInstance(VN);
            NumberFormat VNDFormat = NumberFormat.getCurrencyInstance(VN);
            lbTongTien.setText(VNDFormat.format(tongTien));
            lamMoiPhieuHD();
        }
    }//GEN-LAST:event_btnXoa4ActionPerformed

    private void btnLamMoi3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi3ActionPerformed
        tfGiaTien.setText("");
        tfSoLuong.setText("");
        tfMaMon.setText("");
        tfGiamGia.setText("0");
        tfVATHD.setText("0");
        cbTenMon.removeAllItems();
        loadTenMon();
    }//GEN-LAST:event_btnLamMoi3ActionPerformed

    private void cboTenMonAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenMonAnActionPerformed
        String sql = "  SELECT giaTien FROM MonAn WHERE tenMon = ?";
        try{
            String maTheLoaiString = (String) cboTenMonAn.getSelectedItem();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, maTheLoaiString);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                tfGiaBanCTHD.setText(rs.getString("giaTien"));
            }
        } catch(Exception e){

        }
    }//GEN-LAST:event_cboTenMonAnActionPerformed

    private void tfSoLuongCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSoLuongCTHDActionPerformed
        float thanhTienMua = Float.valueOf(tfSoLuongCTHD.getText())*Float.valueOf(tfGiaBanCTHD.getText());
        tfThanhTienCTHD.setText(""+thanhTienMua);
    }//GEN-LAST:event_tfSoLuongCTHDActionPerformed

    public void  XuatHoaDon(int maBanAn){
        try{
            Hashtable map = new Hashtable();
            JasperReport report = JasperCompileManager.compileReport("src/REPORT/rpHoaDon.jrxml");
            map.put("maHoaDon", maBanAn);
            JasperPrint p = JasperFillManager.fillReport(report, map, ConnectSQL.getConnection());
            JasperViewer.viewReport(p, false);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
    private void btnInHoaDonCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaDonCTActionPerformed
        int maHoaDon = Integer.parseInt(tfMaHD.getText());
        XuatHoaDon(maHoaDon);
    }//GEN-LAST:event_btnInHoaDonCTActionPerformed

    private void panelMonAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMonAnMouseClicked
        panelHoaDon.setBackground(new Color(51,51,51));
        panelDanhMuc.setBackground(new Color(51,51,51));
        panelMonAn.setBackground(new Color(49,139,130));
        panelKhuyenMai.setBackground(new Color(51,51,51));
        panelCTHD.setBackground(new Color(51,51,51));
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_panelMonAnMouseClicked

    private void panelHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHoaDonMouseClicked
        panelHoaDon.setBackground(new Color(49,139,130));
        panelDanhMuc.setBackground(new Color(51,51,51));
        panelMonAn.setBackground(new Color(51,51,51));
        panelCTHD.setBackground(new Color(51,51,51));
        panelKhuyenMai.setBackground(new Color(51,51,51));
        //panelDSBan.setBackground(new Color(51,51,51));
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_panelHoaDonMouseClicked

    private void panelDanhMucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelDanhMucMouseClicked
        panelDanhMuc.setBackground(new Color(49,139,130));
        panelHoaDon.setBackground(new Color(51,51,51));
        panelMonAn.setBackground(new Color(51,51,51));
        panelCTHD.setBackground(new Color(51,51,51));
        panelKhuyenMai.setBackground(new Color(51,51,51));
        //panelDSBan.setBackground(new Color(51,51,51));
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_panelDanhMucMouseClicked

    private void panelTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelTrangChuMouseClicked
        panelHoaDon.setBackground(new Color(51,51,51));
        panelDanhMuc.setBackground(new Color(51,51,51));
        panelMonAn.setBackground(new Color(51,51,51));
        panelCTHD.setBackground(new Color(51,51,51));
        panelKhuyenMai.setBackground(new Color(51,51,51));
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_panelTrangChuMouseClicked

    private void panelCTHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCTHDMouseClicked
        panelCTHD.setBackground(new Color(49,139,130));
        panelDanhMuc.setBackground(new Color(51,51,51));
        panelMonAn.setBackground(new Color(51,51,51));
        panelHoaDon.setBackground(new Color(51,51,51));
        panelKhuyenMai.setBackground(new Color(51,51,51));
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_panelCTHDMouseClicked

    private void panelKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelKhuyenMaiMouseClicked
        panelKhuyenMai.setBackground(new Color(49,139,130));
        panelDanhMuc.setBackground(new Color(51,51,51));
        panelMonAn.setBackground(new Color(51,51,51));
        panelHoaDon.setBackground(new Color(51,51,51));
        panelCTHD.setBackground(new Color(51,51,51));
        jTabbedPane1.setSelectedIndex(5);
    }//GEN-LAST:event_panelKhuyenMaiMouseClicked

    private void btnMinimize11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimize11MouseClicked
        this.setState(this.ICONIFIED);
    }//GEN-LAST:event_btnMinimize11MouseClicked

    private void lbThoat13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbThoat13MouseClicked
        int click=JOptionPane.showConfirmDialog(null, "Do you want to exit the program?", "Message", 2);
        if(click==JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_lbThoat13MouseClicked

    public void RemoveKM(){
        tfTenKM.setText("");
        tfMaKM.setText("");
        tfGiaKM.setText("");
    }
    
    private void btnThemKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKMActionPerformed
        RemoveKM();
    }//GEN-LAST:event_btnThemKMActionPerformed

    private boolean kiemtraNullKM(){
        if(tfTenKM.getText().equals("")){
            JOptionPane.showMessageDialog(NhanVienfr.this,"Please enter promotion name!");
            return false;
        }
        else
        if(tfGiaKM.getText().equals("")){
            JOptionPane.showMessageDialog(NhanVienfr.this,"Please enter promotional value!");
            return false;
        }
        else{ 
            return true;
        }
    }
    
    private void btnLuuKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuKMActionPerformed
        if(kiemtraNullKM()){                 
        khuyenMai.setTenKhuyenMai(tfTenKM.getText());        
        khuyenMai.setGiaKhuyenMai(Integer.valueOf(tfGiaKM.getText()));                 
        
        khuyenMaiSERVICE.themKhuyenMai(khuyenMai);
            JOptionPane.showMessageDialog(null, "Successfully added new promotion!^^");
            hienThiKhuyenMai();
        }
    }//GEN-LAST:event_btnLuuKMActionPerformed

    private float tinhTongTienVAT() {
        float tongtien = 0;
        float tongtienk = 0;
        float giamGia = 0;
            float giamGiakm = Float.parseFloat(tfGiamGia.getText());
            float vatHD = Float.parseFloat(tfVATHD.getText());
        
        if(tfGiamGia.getText().equals("0") && !checkVATHD.isSelected()){
            tinhTongTien();
         }   
        else if(tfGiamGia.getText().equals("0") && checkVATHD.isSelected()){
            tongtienk = tinhTongTien()*(vatHD/100);
            tongtien = tinhTongTien() + tongtienk;
            }
        else if(!tfGiamGia.getText().equals("0") && checkVATHD.isSelected()){           
            tongtienk = tinhTongTien()*(vatHD/100);
            tongtien = (tinhTongTien() + tongtienk) - (tinhTongTien()*(giamGiakm/100));
            //tongtien = giamGia * giamGiakm;
             //double finalTotalPrice = totalPrice - (totalPrice / 100) * discount; //Tính tổng tiền khi có giảm giá
            }
        return tongtien;
    }    
    
    private void checkVATHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkVATHDActionPerformed
        if(checkVATHD.isSelected()){
            tfVATHD.setText("5");
            tinhTongTienVAT();
            float tongTien = tinhTongTienVAT();
        lbTienthua.setText(""+tongTien);
        return;
        }else{
            //float tientong = Float.parseFloat(lbTongTien.getText());
           // lbTongTien.getText();
           tfVATHD.setText("0");
            lbTienthua.setText(lbTongTien.getText());
        }
    }//GEN-LAST:event_checkVATHDActionPerformed

    private void tableDSKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDSKhuyenMaiMouseClicked
        int row=tableDSKhuyenMai.getSelectedRow();
        int maKM=Integer.parseInt(tableDSKhuyenMai.getValueAt(row, 0).toString());
        String tenKM=tableDSKhuyenMai.getValueAt(row, 1).toString();
        float giaKM=Float.parseFloat(tableDSKhuyenMai.getValueAt(row, 2).toString());
        
        tfMaKM.setText(tableDSKhuyenMai.getValueAt(row, 0).toString());
        tfTenKM.setText(tableDSKhuyenMai.getValueAt(row, 1).toString());
        tfGiaKM.setText(tableDSKhuyenMai.getValueAt(row, 2).toString());
    }//GEN-LAST:event_tableDSKhuyenMaiMouseClicked

    private void tfVATHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfVATHDActionPerformed
        tinhTongTienVAT();
        float tongTien = tinhTongTienVAT();
        lbTienthua.setText(""+tongTien);
        return; 
    }//GEN-LAST:event_tfVATHDActionPerformed

    private void tfGiamGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfGiamGiaMouseClicked
        tfGiamGia.setText("0.0");
        cbCTGiamGia.removeAllItems();
        tinhTongTienKM();
        float tongTien = tinhTongTienKM();
        lbTienthua.setText(""+tongTien);
        return; 
    }//GEN-LAST:event_tfGiamGiaMouseClicked

    private void btnProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfileActionPerformed
        NhanVienSERVICE sERVICE = new NhanVienSERVICE();
        String maNV = tfmaNV.getText().toString();
        try {
            nhanVien = sERVICE.getNhanVienbyMaNV(Integer.parseInt(maNV));
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienfr.class.getName()).log(Level.SEVERE, null, ex);
        }
           CaiDatLaiMK pr = new CaiDatLaiMK();
           pr.tfMaNV.setText(maNV);
           pr.tfTenDangNhap.setText(nhanVien.getTenDangNhap());
           pr.tfTenNV.setText(nhanVien.getTenNV());
           pr.tfDiaChi.setText(nhanVien.getDiaChi());
           pr.tfsoDT.setText(nhanVien.getSoDT());
           ((JTextField)pr.tfNgaySinh.getDateEditor().getUiComponent()).setText(nhanVien.getNgaySinh());
           pr.tfMatKhau.setText(nhanVien.getMatKhau());
           pr.tfLinkNV.setText(nhanVien.getImageNV());
           ImageIcon imageIcon = new ImageIcon(getClass().getResource("/IMAGES/NhanVien/"+nhanVien.getImageNV()));
           Image image = imageIcon.getImage().getScaledInstance(pr.lbImageNV.getWidth(), pr.lbImageNV.getHeight(), Image.SCALE_SMOOTH);
           pr.lbImageNV.setIcon(new ImageIcon(image));
           
           pr.setVisible(true);
    }//GEN-LAST:event_btnProfileActionPerformed

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
            java.util.logging.Logger.getLogger(NhanVienfr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVienfr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVienfr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVienfr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new NhanVienfr().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnInHoaDonCT;
    private javax.swing.JButton btnLamMoi3;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnLuuDM;
    private javax.swing.JButton btnLuuKM;
    private javax.swing.JButton btnLuuMon;
    private javax.swing.JLabel btnMinimize;
    private javax.swing.JLabel btnMinimize1;
    private javax.swing.JLabel btnMinimize11;
    private javax.swing.JLabel btnMinimize2;
    private javax.swing.JLabel btnMinimize6;
    private javax.swing.JLabel btnMinimize8;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnProfile;
    private javax.swing.JButton btnSuaMon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemDM;
    private javax.swing.JButton btnThemKM;
    private javax.swing.JButton btnThemMoi;
    private javax.swing.JButton btnThemMon;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa4;
    private javax.swing.JButton btnXoaMon;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbCTGiamGia;
    private javax.swing.JComboBox<String> cbLoaiMon;
    private javax.swing.JComboBox<String> cbTenMon;
    public static javax.swing.JComboBox<String> cbTenNV;
    private javax.swing.JComboBox<String> cboTenMonAn;
    private javax.swing.JComboBox<String> cboTenNV;
    private javax.swing.JCheckBox checkVATHD;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JPanel jpnalCTHD;
    private javax.swing.JPanel jpnalLSHĐ;
    private javax.swing.JLabel lbGia1;
    private javax.swing.JLabel lbImageFood;
    private javax.swing.JLabel lbLoai;
    private javax.swing.JLabel lbMaKM;
    private javax.swing.JLabel lbThoat;
    private javax.swing.JLabel lbThoat1;
    private javax.swing.JLabel lbThoat10;
    private javax.swing.JLabel lbThoat13;
    private javax.swing.JLabel lbThoat2;
    private javax.swing.JLabel lbThoat8;
    private javax.swing.JLabel lbTienthua;
    private javax.swing.JLabel lbTongTien;
    private javax.swing.JPanel paneThucDon3;
    private javax.swing.JPanel panelCTHD;
    private javax.swing.JPanel panelChiTietHD;
    private javax.swing.JPanel panelDanhMuc;
    private javax.swing.JPanel panelDoAn;
    private javax.swing.JPanel panelGiaoDien;
    private javax.swing.JPanel panelHoaDon;
    private javax.swing.JPanel panelKhuyenMai;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelMonAn;
    private javax.swing.JPanel panelNuocUong;
    private javax.swing.JTabbedPane panelThucDonMon3;
    private javax.swing.JPanel panelTrangChu;
    private javax.swing.JPanel pnDanhMuc;
    private javax.swing.JPanel pnHoaDon;
    private javax.swing.JPanel pnKhuyenMai;
    private javax.swing.JPanel pnMonAn;
    private javax.swing.JPanel pnTrangChu;
    private javax.swing.JTable tabDoAn;
    private javax.swing.JTable tabNuocUong;
    private javax.swing.JTable tabTatCaMon3;
    private javax.swing.JTable tableCTHD;
    private javax.swing.JTable tableDSKhuyenMai;
    private javax.swing.JTable tableDanhMuc;
    private javax.swing.JTable tableFood;
    private javax.swing.JTable tableHoaDon;
    private javax.swing.JTable tableKhuyenMai;
    private javax.swing.JTable tableLSHD;
    private javax.swing.JTextField tfDonViTinh;
    private javax.swing.JTextField tfGiaBanCTHD;
    private javax.swing.JTextField tfGiaKM;
    private javax.swing.JTextField tfGiaTien;
    private javax.swing.JTextField tfGiaTienMon;
    private javax.swing.JLabel tfGiamGia;
    private javax.swing.JTextField tfLinkDM;
    private javax.swing.JTextField tfLinkMonAn;
    private javax.swing.JTextField tfMaDM;
    private javax.swing.JTextField tfMaHD;
    private javax.swing.JTextField tfMaKM;
    private javax.swing.JTextField tfMaMon;
    private javax.swing.JTextField tfMaMonAn;
    private javax.swing.JTextField tfMaNVHD;
    private javax.swing.JTextField tfSoLuong;
    private javax.swing.JTextField tfSoLuongCTHD;
    private javax.swing.JTextField tfSoLuongMon;
    private javax.swing.JTextField tfSoLuongTon;
    private javax.swing.JTextField tfTenDM;
    public static javax.swing.JTextField tfTenDN;
    private javax.swing.JTextField tfTenKM;
    private javax.swing.JTextField tfTenLoai;
    private javax.swing.JTextField tfTenLoaiMon;
    private javax.swing.JTextField tfTenMonAn;
    private javax.swing.JTextField tfTenNV;
    private javax.swing.JTextField tfThanhTien;
    private javax.swing.JTextField tfThanhTienCTHD;
    private javax.swing.JTextField tfTimKiem;
    private javax.swing.JTextField tfTongTienHD;
    private javax.swing.JTextField tfVATHD;
    public static javax.swing.JTextField tfmaNV;
    // End of variables declaration//GEN-END:variables

    //CẬP NHẬT DỮ LIỆU
    public void RefeshMA(){
       modelThucDon.setRowCount(0); //Xóa dữ liệu cũ về 0
       setDataTableTD( thucDonSERVICE.getAllThucDon()); // lấy dữ liệu mới
    }
    
    public void RefeshNV(){
       modelNhanVien.setRowCount(0); //Xóa dữ liệu cũ về 0
       setDataTableNV(nhanVienSERVICE.getAllNhanVien()); // lấy dữ liệu mới
    }
    
    public void RefeshDM(){
       modelDanhMuc.setRowCount(0); //Xóa dữ liệu cũ về 0
       setDataTableDM(danhMucSERVICE.getAllDanhMuc()); // lấy dữ liệu mới
    }
    
    //LẤY MÃ 
    private int layMaHoaDon() {
        int ma = 0;
        try {
            String sql = "SELECT top 1 maHD FROM HoaDonTT ORDER BY maHD DESC"; //DESC Lấy mã hóa đơn đầu tiên từ dưới lên || ASC từ trên xuống
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ma = rs.getInt("maHD");
                return ma;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ma;
    }
    
    private int LayMaNhanVienTheoTen(String TenNV) {
        int maNV = 0;
        try {
            String sql = "SELECT maNV FROM NhanVien WHERE tenNV=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, TenNV);
            rs = ps.executeQuery();
            while (rs.next()) {
                maNV = rs.getInt("maNV");
                return maNV;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // xong gán nó cho mã này
        return maNV;
    }
    
    private int LayMaMonTheoTen(String TenMon) {
        int maMon = 0;
        try {
            String sql = "SELECT maMon FROM MonAn WHERE tenMon= ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, TenMon);
            rs = ps.executeQuery();
            while (rs.next()) {
                maMon = rs.getInt("maMon");
                return maMon;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maMon;
    }
    
    //LẤY DỮ LIỆU VÀO COMBOBOX
     private void loadTenNV() {
        String sql = "  SELECT tenNV FROM NhanVien WHERE maNV = maNV ";
        try{
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                this.cbTenNV.addItem(rs.getString("tenNV"));
            }
            } catch(Exception e){

        }
            }
     
     private void loadTenMon() {
        String sql = "  SELECT tenMon FROM MonAn WHERE maMon = maMon  ";
        try{
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                this.cbTenMon.addItem(rs.getString("tenMon"));
            }
            } catch(Exception e){

        }
            }
     
     //CHỌN HÌNH ẢNH
        
        public void ChonAnhMA(){
        JFileChooser open = new JFileChooser();
        open.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int res = open.showOpenDialog(NhanVienfr.this);
        if(res==JFileChooser.APPROVE_OPTION){
            File file = open.getSelectedFile();
            String path = file.getAbsolutePath();
            ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
            Rectangle rerc =lbImageFood.getBounds();
            Image scaImage = imageIcon.getImage().getScaledInstance(rerc.width, rerc.height, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(scaImage);
            lbImageFood.setIcon(imageIcon);
            tfLinkMonAn.setText(file.getName());
        }else{
            JOptionPane.showMessageDialog(this, "No new photos selected!!");
        }
    }
        
     //FORM CT HÓA ĐƠN
     public void HienThiCboTenMon(){
        try{
            String sql="select tenMon from MonAn where maMon=maMon";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            cboTenMonAn.removeAllItems();
            while(rs.next()){
                cboTenMonAn.addItem(rs.getString("tenMon"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void HienThiCboNhanVien(){
        try{
            String sql="SELECT tenNV FROM NhanVien WHERE maNV=maNV";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            cboTenNV.removeAllItems();
            while(rs.next()){
                cboTenNV.addItem(rs.getString("tenNV"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void hienThiKhuyenMai(){
        try {
            modelkhuyenMai.setRowCount(0);
            modelDSKhuyenMai.setRowCount(0);
            String sql=" SELECT km.maKhuyenMai, tenKhuyenMai, giaKhuyenMai FROM KhuyenMai km WHERE maKhuyenMai=km.maKhuyenMai";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Vector<Object> vec=new Vector<>();
                vec.add(rs.getInt("maKhuyenMai"));
                vec.add(rs.getString("tenKhuyenMai"));
                vec.add(rs.getFloat("giaKhuyenMai"));
                modelkhuyenMai.addRow(vec);
                modelDSKhuyenMai.addRow(vec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void hienThiTatCa(){
        try {
            modelTatCaMon.setRowCount(0);
            String sql="SELECT maMon, tenMon, donVi, giaTien, soLuong FROM MonAn";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Vector<Object> vec=new Vector<>();
                vec.add(rs.getInt("maMon"));
                vec.add(rs.getString("tenMon"));
                vec.add(rs.getString("donVi"));
                vec.add(rs.getFloat("giaTien"));
                vec.add(rs.getInt("soLuong"));
                modelTatCaMon.addRow(vec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void hienThiDoAn(){
        try {
            modelDoAn.setRowCount(0);
            String sql="SELECT maMon, tenMon, donVi, giaTien, soLuong FROM MonAn, DanhMucMon WHERE maLoaiMon = maLoai and tenLoai != N'Nước uống' ";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Vector<Object> vec=new Vector<>();
                vec.add(rs.getInt("maMon"));
                vec.add(rs.getString("tenMon"));
                vec.add(rs.getString("donVi"));
                vec.add(rs.getFloat("giaTien"));
                vec.add(rs.getInt("soLuong"));
                modelDoAn.addRow(vec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void hienThiNuocUong(){
        try {
            modelNuocUong.setRowCount(0);
            String sql="SELECT maMon, tenMon, donVi, giaTien, soLuong FROM MonAn, DanhMucMon WHERE maLoaiMon = maLoai and tenLoai = N'Nước uống' ";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Vector<Object> vec=new Vector<>();
                vec.add(rs.getInt("maMon"));
                vec.add(rs.getString("tenMon"));
                vec.add(rs.getString("donVi"));
                vec.add(rs.getFloat("giaTien"));
                vec.add(rs.getInt("soLuong"));
                modelNuocUong.addRow(vec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private int LayMaNhanVienTheoTenCTHD(String tenNV){
        int Ma=0;
        try{
            String sql="SELECT maNV FROM NhanVien WHERE tenNV=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, tenNV);
            rs=ps.executeQuery();
            while(rs.next()){
                 Ma=rs.getInt("maNV");
                 return Ma;
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
            // xong gán nó cho thằng mã này
        return Ma;
    }
    
    private int LayMaMonAnTheoTen(String tenMon){
        int Ma=0;
        try{
            String sql="SELECT maMon FROM MonAn WHERE tenMon=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, tenMon);
            rs=ps.executeQuery();
            while(rs.next()){
                 Ma=rs.getInt("maMon");
                 return Ma;
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
            // xong gán nó cho thằng mã này
        return Ma;
    }
    
    private void hienThiTableHoaDon(){
        try {
            modelLSHD.setRowCount(0);
            String sql="SELECT a.maHD, c.tenNV, ngayXuatHoaDon, tongtien FROM HoaDonTT a, NhanVien c WHERE a.maNhanVien=c.maNV";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Vector<Object> vec=new Vector<>();
                vec.add(rs.getInt("maHD"));
                vec.add(rs.getString("tenNV"));
                vec.add(rs.getDate("ngayXuatHoaDon"));
                vec.add(rs.getFloat("tongtien"));
                modelLSHD.addRow(vec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void hienThitableCTHDTheoMaHD(int maHD){
        try {
            modelCTHD.setRowCount(0);
            String sql="SELECT tenMonAn, giaBan, soLuong, thanhTien FROM ChiTietHD WHERE maHoaDon=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1, maHD);
            rs=ps.executeQuery();
            while(rs.next()){
                Vector<Object> vec=new Vector<>();
                vec.add(rs.getString("tenMonAn"));
                vec.add(rs.getFloat("giaBan"));
                vec.add(rs.getInt("soLuong"));
                vec.add(rs.getFloat("thanhTien"));
                modelCTHD.addRow(vec);
            }
        } catch (Exception e) {
        }
    }

    private void xoaHDTheoMaHD(int maHD){
        try {
            String sql="UPDATE HoaDonTT SET DaXoa=1 WHERE maHD=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1, maHD);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void xoaCTHD(int maHD, int maMA){
        try {
            String sql="DELETE FROM ChiTietHD WHERE maHoaDon=? AND maMonAn=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1, maHD);
            ps.setInt(2, maMA);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private float layTongTienSauKhiXoaHoaDon(int maHD){
        float tongtien=0;
        try {
            String sql="SELECT thanhTien FROM ChiTietHD WHERE maHoaDon=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1, maHD);
            rs=ps.executeQuery();
            while(rs.next()){
                float thanhtien=rs.getFloat("thanhTien");
                tongtien+=thanhtien;
            }
            return tongtien;
        } catch (Exception e) {
        }
        return tongtien;
    }
    
    private void capNhatTongTienHoaDonSauKhiXoaCTHD(int maHD, float tongTien){
        try {
            String sql="UPDATE HoaDonTT SET tongTien=? WHERE maHD=?";
             ps=conn.prepareStatement(sql);
             ps.setFloat(1, tongTien);
             ps.setInt(2, maHD);
             ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    void dongTextBox(){
        tfGiaBanCTHD.setText("");
        tfMaHD.setText("");
        tfSoLuongCTHD.setText("");
        tfThanhTienCTHD.setText("");
        tfTongTienHD.setText("");
    }
    void dongTextBoxCTHD(){
        tfGiaBanCTHD.setText("");
        tfSoLuongCTHD.setText("");
        tfThanhTienCTHD.setText("");
    }
    
    private void capNhatHoaDon(int maHD, int maNV){
        try {
            String sql="UPDATE HoaDonTT SET maNhanVien=? WHERE maHD=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1, maNV);
            ps.setInt(2, maHD);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void capNhatCTHD(int maHD, int maMA, float giaBan, int soLuong, float thanhTien){
        try {
            String sql="UPDATE ChiTietHD SET giaBan=?, soLuong=?, thanhTien=? WHERE maHoaDon=? and maMonAn=?";
            ps=conn.prepareStatement(sql);
            ps.setFloat(1, giaBan);
            ps.setInt(2, soLuong);
            ps.setFloat(3, thanhTien);
            ps.setInt(4, maHD);
            ps.setInt(5, maMA);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}