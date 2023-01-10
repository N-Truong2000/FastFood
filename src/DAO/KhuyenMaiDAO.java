/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.KhuyenMai;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class KhuyenMaiDAO {
    
    public List<KhuyenMai> getAllKM(){
        List<KhuyenMai> dsBans = new ArrayList<KhuyenMai>();
        
        Connection connection = ConnectSQL.getConnection();
        
        String sql = "  SELECT maKhuyenMai, tenKhuyenMai, giaKhuyenMai FROM KhuyenMai";
        
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                KhuyenMai dsBan = new KhuyenMai();
                
                //bien tu csdl
                dsBan.setMaKhuyenMai(rs.getInt("maKhuyenMai"));
                dsBan.setTenKhuyenMai(rs.getString("tenKhuyenMai"));
                dsBan.setGiaKhuyenMai(rs.getFloat("giaKhuyenMai"));
                
                dsBans.add(dsBan);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return dsBans;
    }
    
    
    public void themKhuyenMai(KhuyenMai danhMuc) {
        Connection connection = ConnectSQL.getConnection();
            String sql = "INSERT INTO KhuyenMai VALUES (?,?)";
            try {           
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, danhMuc.getTenKhuyenMai());
                preparedStatement.setFloat(2, danhMuc.getGiaKhuyenMai());
                
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
     
    public void suaKhuyenMai(KhuyenMai danhMuc) {
        Connection connection = ConnectSQL.getConnection();
            String sql = "UPDATE KhuyenMai SET tenKhuyenMai = ?, giaKhuyenMai = ?  WHERE  maKhuyenMai = ?";
            try {           
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, danhMuc.getTenKhuyenMai());
                preparedStatement.setFloat(2, danhMuc.getGiaKhuyenMai());
                preparedStatement.setInt(3, danhMuc.getMaKhuyenMai());
                
                preparedStatement.executeUpdate();
                System.out.println(preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    
    public void xoaKhuyenMai(String maKM) {
        Connection connection = ConnectSQL.getConnection();
            String sql = "DELETE FROM KhuyenMai WHERE maKhuyenMai = ?";
            try {           
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, maKM);
                preparedStatement.executeUpdate();
                System.out.println(preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
