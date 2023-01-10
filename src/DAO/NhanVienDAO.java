/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.jtds.jdbc.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 *
 * @author ADMIN
 */
public class NhanVienDAO {
    
    public List<NhanVien> getAllNhanVien(){
        List<NhanVien> nhanViens = new ArrayList<NhanVien>();
        
        Connection connection = ConnectSQL.getConnection();
        
        String sql = "SELECT maNV, tenNV,ngaySinh,gioiTinh,diaChi,soDT,chucVu,imageNV,tenDangNhap,matKhau FROM NhanVien";
        
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                
                //bien tu csdl
                nhanVien.setMaNV(rs.getInt("maNV"));
                nhanVien.setTenNV(rs.getString("tenNV"));
                nhanVien.setNgaySinh(rs.getString("ngaySinh"));
                nhanVien.setGioiTinh(rs.getString("gioiTinh"));
                nhanVien.setSoDT(rs.getString("soDT"));
                nhanVien.setDiaChi(rs.getString("diaChi"));
                nhanVien.setChucVu(rs.getString("chucVu"));
                nhanVien.setImageNV(rs.getString("imageNV"));
                nhanVien.setMatKhau(rs.getString("matKhau"));
                nhanVien.setTenDangNhap(rs.getString("tenDangNhap"));
                
                nhanViens.add(nhanVien);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return nhanViens;
    }
    
    public NhanVien getNhanVienbyMaNV(int maNV) throws SQLException{
        NhanVien nhanVien = new NhanVien(); 
        
        String sql = "SELECT tenNV,ngaySinh,diaChi,soDT,imageNV,tenDangNhap,matKhau FROM NhanVien WHERE maNV = " + maNV;
        Connection connection = null;
        PreparedStatement stm = null;
        try{

            connection = ConnectSQL.getConnection();
            stm = connection.prepareStatement(sql);
//                stm.setInt(1, maNV);
                ResultSet rs = stm.executeQuery();
                rs.next();
                //bien tu csdl
                nhanVien.setTenNV(rs.getString("tenNV"));
                nhanVien.setNgaySinh(rs.getString("ngaySinh"));
                nhanVien.setSoDT(rs.getString("soDT"));
                nhanVien.setDiaChi(rs.getString("diaChi"));
                nhanVien.setImageNV(rs.getString("imageNV"));
                nhanVien.setTenDangNhap(rs.getString("tenDangNhap"));
                nhanVien.setMatKhau(rs.getString("matKhau"));
                
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            if (connection != null) connection.close();
            if (stm != null) stm.close();
        }
        return nhanVien;
    }
    
    public void themNhanVien(NhanVien nv) 
    {
        Connection connection = ConnectSQL.getConnection();
        String sql = "Insert into NhanVien(tenNV,ngaySinh,gioiTinh,soDT,diaChi,chucVu,imageNV,tenDangNhap,matKhau)"
                + "values (?,?,?,?,?,?,?,?,?)";
        try 
        {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nv.getTenNV());
            preparedStatement.setString(2, nv.getNgaySinh());
            preparedStatement.setString(3, nv.getGioiTinh());
            preparedStatement.setString(4, nv.getSoDT());
            preparedStatement.setString(5, nv.getDiaChi());
            preparedStatement.setString(6, nv.getChucVu());
            preparedStatement.setString(7, nv.getImageNV());
            preparedStatement.setString(8, nv.getSoDT());
            preparedStatement.setString(9, "newaccount");
            
            int rs = preparedStatement.executeUpdate();
           // System.out.println(rs);
            
        } 
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void suaNhanVien(NhanVien nv) 
    {
        Connection connection = ConnectSQL.getConnection();
        String sql = "UPDATE NhanVien SET tenNV = ?, ngaySinh = ?, gioiTinh = ?,soDT = ?"
                + " , diaChi = ?,  chucVu = ?, imageNV = ? WHERE maNV = ?";
        try 
        {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nv.getTenNV());
            preparedStatement.setString(2, nv.getNgaySinh());
            preparedStatement.setString(3, nv.getGioiTinh());
            preparedStatement.setString(4, nv.getSoDT());
            preparedStatement.setString(5, nv.getDiaChi());
            preparedStatement.setString(6, nv.getChucVu());
            preparedStatement.setString(7, nv.getImageNV());
            
            preparedStatement.setInt(8, nv.getMaNV());
            
            preparedStatement.executeUpdate();
        } 
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void suaNhanVien2(NhanVien nv) 
    {
        Connection connection = ConnectSQL.getConnection();
        String sql = "UPDATE NhanVien SET tenNV = ?, ngaySinh = ?,soDT = ?"
                + " , diaChi = ?, imageNV = ?, tenDangNhap = ?, matKhau = ? WHERE maNV = ?";
        try 
        {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nv.getTenNV());
            preparedStatement.setString(2, nv.getNgaySinh());
            preparedStatement.setString(3, nv.getSoDT());
            preparedStatement.setString(4, nv.getDiaChi());
            preparedStatement.setString(5, nv.getImageNV());
            preparedStatement.setString(6, nv.getTenDangNhap());
            preparedStatement.setString(7, nv.getMatKhau());
            
            preparedStatement.setInt(8, nv.getMaNV());
            
            preparedStatement.executeUpdate();
        } 
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    
    public void xoaNhanVien(String maNV) {
        Connection connection = ConnectSQL.getConnection();
            String sql = "DELETE FROM NhanVien WHERE maNV = ?";
            try {           
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, maNV);
                preparedStatement.executeUpdate();
                System.out.println(preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    
}
