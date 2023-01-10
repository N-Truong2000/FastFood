/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.DanhMuc;
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
public class DanhMucDAO {
    
    public List<DanhMuc> getAllDanhMuc(){
        List<DanhMuc> danhMucs = new ArrayList<DanhMuc>();
        
        Connection connection = ConnectSQL.getConnection();
        
        String sql = "  SELECT maLoai, tenLoai FROM DanhMucMon";
        
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                DanhMuc danhMuc = new DanhMuc();
                
                //bien tu csdl
                danhMuc.setMaLoai(rs.getInt("maLoai"));
                danhMuc.setTenLoai(rs.getString("tenLoai"));
                
                danhMucs.add(danhMuc);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return danhMucs;
    }
    
    public void themDanhMuc(DanhMuc danhMuc) {
        Connection connection = ConnectSQL.getConnection();
            String sql = "INSERT INTO DanhMucMon VALUES (?)";
            try {           
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, danhMuc.getTenLoai());
                
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
     
    public void suaDanhMuc(DanhMuc danhMuc) {
        Connection connection = ConnectSQL.getConnection();
            String sql = "UPDATE DanhMucMon SET tenLoai = ? WHERE  maLoai = ?";
            try {           
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, danhMuc.getTenLoai());
                preparedStatement.setInt(2, danhMuc.getMaLoai());
                
                preparedStatement.executeUpdate();
                System.out.println(preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    
    public void xoaDanhMuc(String maDM) {
        Connection connection = ConnectSQL.getConnection();
            String sql = "DELETE FROM DanhMucMon WHERE maLoai = ?";
            try {           
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, maDM);
                preparedStatement.executeUpdate();
                System.out.println(preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    
}
