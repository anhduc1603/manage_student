
package com.mycompany.manager_product.dao;

import com.mycompany.manager_product.helper.DataHelper;
import com.mycompany.manager_product.model.SinhVien;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author saotr
 */
public class SinhVienDAO {

    public boolean insert(SinhVien st) throws Exception {
        String sql = "insert into sinhvien(maSV, hoTen, email, soDT, gioiTinh, diaChi, hinh) value(?,?,?,?,?,?,?)";
        try (
                Connection connection = DataHelper.openConnect();
                PreparedStatement pstm = connection.prepareStatement(sql);) {
            pstm.setString(1, st.getMaSinhVien());
            pstm.setString(2, st.getHoTen());
            pstm.setString(3, st.getEmail());
            pstm.setString(4, st.getSoDT());
            pstm.setInt(5, st.getGioiTinh());
            pstm.setString(6, st.getDiaChi());

            if (st.getHinh() != null) {
                Blob hinh = new SerialBlob(st.getHinh());
                pstm.setBlob(7, hinh);
            } else {
                Blob hinh = null;
                pstm.setBlob(7, hinh);
            }

            return pstm.executeUpdate() > 0;
        }
    }

    public boolean update(SinhVien st) throws Exception {
        String sql = "update sinhvien SET hoTen=?,email=?,soDT=?,gioiTinh=?,diaChi=?,hinh= ?"
                + " where maSV = ?";
        try (
                Connection connection = DataHelper.openConnect();
                PreparedStatement pstm = connection.prepareStatement(sql);) {
            pstm.setString(7, st.getMaSinhVien());
            pstm.setString(1, st.getHoTen());
            pstm.setString(2, st.getEmail());
            pstm.setString(3, st.getSoDT());
            pstm.setInt(4, st.getGioiTinh());
            pstm.setString(5, st.getDiaChi());

            if (st.getHinh() != null) {
                Blob hinh = new SerialBlob(st.getHinh());
                pstm.setBlob(6, hinh);
            } else {
                Blob hinh = null;
                pstm.setBlob(6, hinh);
            }

            return pstm.executeUpdate() > 0;
        }
    }

    public boolean delete(String msv) throws Exception {
        String sql = "DELETE FROM sinhvien WHERE maSV= ?";
        try (
                Connection connection = DataHelper.openConnect();
                PreparedStatement pstm = connection.prepareStatement(sql);) {
            pstm.setString(1, msv);

            return pstm.executeUpdate() > 0;
        }
    }

    public SinhVien findByid(String msv) throws Exception {
        String sql = "SELECT * FROM sinhvien WHERE maSV= ?";
        try (
                Connection connection = DataHelper.openConnect();
                PreparedStatement pstm = connection.prepareStatement(sql);) {
            pstm.setString(1, msv);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    SinhVien sv = createSinhVien(rs);
                    return sv;
                }
            }
            return null;
        }
    }

    public List <SinhVien> findAll() throws Exception {
        String sql = "SELECT * FROM sinhvien";
        try (
                Connection connection = DataHelper.openConnect();
                PreparedStatement pstm = connection.prepareStatement(sql);) {

            try (ResultSet rs = pstm.executeQuery()) {
                List<SinhVien> list = new ArrayList<>();
                while (rs.next()) {
                    SinhVien sv = createSinhVien(rs);
                    list.add(sv);
                }
                return list;
            }
           
        }
    }

    private SinhVien createSinhVien(final ResultSet rs) throws SQLException {
        SinhVien sv = new SinhVien();
        sv.setMaSinhVien(rs.getString("maSV"));
        sv.setHoTen(rs.getString("hoTen"));
        sv.setEmail(rs.getString("email"));
        sv.setSoDT(rs.getString("soDT"));
        sv.setGioiTinh(rs.getInt("gioiTinh"));
        sv.setDiaChi(rs.getString("diaChi"));  
        Blob blog = rs.getBlob("hinh");
        if (blog != null) {
            sv.setHinh(blog.getBytes(1, (int) blog.length()));
        }
        return sv;
    }
}
