/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import DTO.ChiTietHD;
import DTO.HoaDon;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ChiTietHDDAO {
    
    
    public List<ChiTietHD> getAllCTHD(){
        List<ChiTietHD> chiTietHDs = new ArrayList<ChiTietHD>();
        
        Connection connection = ConnectSQL.getConnection();
        
        String sql = "SELECT maHoaDon, maMonAn, tenMonAn, giaBan,  soLuong, thanhTien FROM ChiTietHD";
        
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                ChiTietHD chiTietHD = new ChiTietHD();
                //bien tu csdl
                chiTietHD.setMaHoaDon(rs.getInt("maHoaDon"));
                chiTietHD.setMaMonAn(rs.getInt("maMonAn"));
                chiTietHD.setTenMonAn(rs.getString("tenMonAn"));
                chiTietHD.setGiaBan(rs.getFloat("giaBan"));
                chiTietHD.setSoLuong(rs.getInt("soLuong"));
                chiTietHD.setThanhTien(rs.getFloat("thanhTien"));
                
                chiTietHDs.add(chiTietHD);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return chiTietHDs;
    }
    
    public void themCTHoaDon(ChiTietHD chiTietHD) {
        Connection connection = ConnectSQL.getConnection();
            String sql = "INSERT INTO ChiTietHD(maHoaDon,maMonAn,tenMonAn,giaBan,soLuong,thanhTien)  VALUES (?, ?, ?, ?, ?, ?)";
            try {           
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, chiTietHD.getMaHoaDon());
                preparedStatement.setInt(2, chiTietHD.getMaMonAn());
                preparedStatement.setString(3, chiTietHD.getTenMonAn());
                preparedStatement.setFloat(4, chiTietHD.getGiaBan());
                preparedStatement.setInt(5, chiTietHD.getSoLuong());
                preparedStatement.setFloat(6, chiTietHD.getThanhTien());
                
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
