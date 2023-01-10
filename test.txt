
  SELECT maMon, tenMon, maLoaiMon, donVi, giaTien, soLuong FROM MonAn 

  SELECT maMon, tenMon, maLoaiMon, donVi, giaTien, soLuong FROM MonAn, DanhMucMon WHERE maLoaiMon = maLoai and tenLoai != N'Nước uống'

  SELECT maMon, tenMon, maLoaiMon, donVi, giaTien, soLuong FROM MonAn, DanhMucMon WHERE maLoaiMon = maLoai and tenLoai = N'Đồ ăn vặt'


  --Menu
  SELECT f.tenMon, bi.soLuong, f.giaTien, f.giaTien*bi.soLuong AS totalPrice 
  FROM dbo.ChiTietHD AS bi, dbo.HoaDonTT AS b, dbo.MonAn AS f 
  WHERE bi.maHoaDon = b.maHD AND bi.maMonAn = f.maMon AND b.trangThai = 0 AND bi.maBan = 1

  SELECT MAX(maHD) FROM dbo.HoaDonTT


  --GetUncheckBillIDByTableID(int id)
  SELECT * FROM ChiTietHD ct, HoaDonTT hd WHERE ct.maBan = 1 AND trangThai = 0