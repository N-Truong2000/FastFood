/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietHD;
import DTO.DSBan;
import DTO.HoaDon;
import DTO.ThucDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class HoaDonDAO {
    
    private static HoaDonDAO instance;

    public HoaDonDAO() {
    }

    public static HoaDonDAO getInstance() {
        if (instance == null) {
            instance = new HoaDonDAO();
        }
        return instance;
    }

    public int GetUncheckInvoiceByTableId(int id) {
        Connection con = ConnectSQL.getConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM HoaDon WHERE maBanAn = ? AND trangThaiBan = N'Trống");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
//                Invoices invoices = new Invoices(rs.getInt(1), rs.getInt(2), rs.getTimestamp(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public List<HoaDon> getAllLSHD(){
        List<HoaDon> hoaDons = new ArrayList<HoaDon>();
        
        Connection connection = ConnectSQL.getConnection();
        
        String sql = "SELECT maHD, maNhanVien, ngayXuatHoaDon, tongTien FROM HoaDonTT";
        
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                //bien tu csdl
                hoaDon.setMaHD(rs.getInt("maHD"));
                hoaDon.setMaNhanVien(rs.getInt("maNhanVien"));
                hoaDon.setNgayXuatHoaDon(rs.getDate("ngayXuatHoaDon"));
                hoaDon.setTongTien(rs.getFloat("tongTien"));
                
                hoaDons.add(hoaDon);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return hoaDons;
    }
    
    public List<HoaDon> getHoaDonByMaBan(String maBanAn){
        List<HoaDon> hoaDons = new ArrayList<HoaDon>();
        
        Connection connection = ConnectSQL.getConnection();
        
        String sql = "  SELECT * FROM HoaDonTT WHERE maBanAn like '%" + maBanAn + "%' ";
        
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                
                //bien tu csdl
                hoaDon.setMaHD(rs.getInt("maHoaDon"));
                hoaDon.setMaBanAn(rs.getInt("maBanAn"));
                hoaDon.setMaNhanVien(rs.getInt("maNhanVien"));
                hoaDon.setTongTien(rs.getFloat("tongTien"));
                
                hoaDons.add(hoaDon);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return hoaDons;
    }
    
    public void themHoaDon(HoaDon hoaDon) {
        Connection connection = ConnectSQL.getConnection();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
            String date1 = formatter.format(date).toString();
            String sql = "INSERT INTO HoaDonTT(maNhanVien,maBanAn,ngayXuatHoaDon,tongTien)  VALUES (?, ?, ?, ?)";
            try {           
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, hoaDon.getMaNhanVien());
                preparedStatement.setInt(2, hoaDon.getMaBanAn());
                preparedStatement.setString(3, date1);
                preparedStatement.setFloat(4, hoaDon.getTongTien());
                
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    
    public void themTTHoaDon(HoaDon hoaDon) {
//        Connection connection = ConnectSQL.getConnection();
//            String sql = "INSERT INTO ThongTinHD VALUES (?, ?, ?, ?, ?)";
//            try {           
//                PreparedStatement preparedStatement = connection.prepareStatement(sql);
//                preparedStatement.setString(1, hoaDon.getMaBanAn());
//                preparedStatement.setString(2, hoaDon.getMaMonAn());
//                preparedStatement.setString(3, hoaDon.getTenMonAn());
//                preparedStatement.setInt(4, hoaDon.getSoLuongMua());
//                preparedStatement.setFloat(5, hoaDon.getThanhTien());
//                
//                preparedStatement.executeUpdate();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
    }
}
