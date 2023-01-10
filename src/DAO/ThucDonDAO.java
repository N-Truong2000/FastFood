/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ThucDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ThucDonDAO {
    
    public List<ThucDon> getAllThucDon(){
        List<ThucDon> thucDons = new ArrayList<ThucDon>();
        
        Connection connection = ConnectSQL.getConnection();
        
        String sql = "  SELECT maMon, tenMon, maLoaiMon, donVi,  giaTien, soLuong,  imageMonAn FROM MonAn";
        
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                ThucDon thucDon = new ThucDon();
                
                //bien tu csdl
                thucDon.setMaMonAn(rs.getInt("maMon"));
                thucDon.setTenMonAn(rs.getString("tenMon"));
                thucDon.setMaLoaiMon(rs.getInt("maLoaiMon"));
                thucDon.setDonVi(rs.getString("donVi"));
                thucDon.setGiaTien(rs.getFloat("giaTien"));
                thucDon.setSoLuong(rs.getInt("soLuong"));
                thucDon.setImageMonAn(rs.getString("imageMonAn"));
                
                thucDons.add(thucDon);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return thucDons;
    }
    
    public void themThucDon(ThucDon thucDon) {
        Connection connection = ConnectSQL.getConnection();
            String sql = "INSERT INTO MonAn VALUES (?, ?, ?, ?, ?,?)";
            try {           
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, thucDon.getTenMonAn());
                preparedStatement.setInt(2, thucDon.getMaLoaiMon());
                preparedStatement.setString(3, thucDon.getDonVi());
                preparedStatement.setFloat(4, thucDon.getGiaTien());
                preparedStatement.setInt(5, thucDon.getSoLuong());
                preparedStatement.setString(6, thucDon.getImageMonAn());
                
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
     
    public void suaThucDon(ThucDon thucDon) {
        Connection connection = ConnectSQL.getConnection();
            String sql = "UPDATE MonAn SET tenMon = ?, maLoaiMon = ? , donVi = ? , giaTien = ? , soLuong = ?, imageMonAn = ?  WHERE  maMon = ?";
            try {           
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, thucDon.getTenMonAn());
                preparedStatement.setInt(2, thucDon.getMaLoaiMon());
                preparedStatement.setString(3, thucDon.getDonVi());
                preparedStatement.setFloat(4, thucDon.getGiaTien());
                preparedStatement.setInt(5, thucDon.getSoLuong());
                preparedStatement.setString(6, thucDon.getImageMonAn());
                preparedStatement.setInt(7, thucDon.getMaMonAn());
                
                preparedStatement.executeUpdate();
                System.out.println(preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    
    public void xoaThucDon(String maMonAn) {
        Connection connection = ConnectSQL.getConnection();
            String sql = "DELETE FROM MonAn WHERE maMon = ?";
            try {           
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, maMonAn);
                preparedStatement.executeUpdate();
                System.out.println(preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    
    public static ThucDon layMonAnTuMaMon(String maMon) 
    {
        Connection connection = ConnectSQL.getConnection();
        String sql = "SELECT * FROM MonAn WHERE maMon = ?";
        try 
        {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, maMon);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                ThucDon thucDon = new ThucDon();
                
                thucDon.setMaMonAn(rs.getInt("maMon"));
                thucDon.setTenMonAn(rs.getString("tenMon"));
                thucDon.setDonVi(rs.getString("donVi"));
                thucDon.setGiaTien(rs.getFloat("giaTien"));
                thucDon.setSoLuong(rs.getInt("soLuong"));
                
                return thucDon; 
              }
        } 
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

}
