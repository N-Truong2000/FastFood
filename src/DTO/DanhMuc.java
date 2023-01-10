/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author ADMIN
 */
public class DanhMuc {
    
    public DanhMuc() {
        
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public void setImageDM(String imageDM) {
        this.imageDM = imageDM;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public String getImageDM() {
        return imageDM;
    }

    public DanhMuc(int maLoai, String tenLoai, String imageDM) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.imageDM = imageDM;
    }

    private int maLoai;
    private String tenLoai;
    private String imageDM;

}
