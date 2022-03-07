package com.mycompany.manager_product.dao;

import com.mycompany.manager_product.helper.DataHelper;
import com.mycompany.manager_product.model.BangDiem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BangDiemDAO {

    public boolean insert(BangDiem bd) throws Exception {
        String sql = "INSERT INTO bangdiem( maSV, tiengAnh,tinHoc, GDTC)  value(?,?,?,?)";
        try (
                Connection connection = DataHelper.openConnect();
                PreparedStatement pstm = connection.prepareStatement(sql);) {
            pstm.setString(1, bd.getMaSinhVien());
            pstm.setFloat(2, bd.getTiengAnh());
            pstm.setFloat(3, bd.getTinHoc());
            pstm.setFloat(4, bd.getGDTC());

            return pstm.executeUpdate() > 0;
        }
    }

    public boolean update(BangDiem bd) throws Exception {
        String sql = "UPDATE bangdiem SET tiengAnh=?,tinHoc=?,GDTC=? WHERE maSV=? ";
        try (
                Connection connection = DataHelper.openConnect();
                PreparedStatement pstm = connection.prepareStatement(sql);) {
            pstm.setString(4, bd.getMaSinhVien());
            pstm.setFloat(1, bd.getTiengAnh());
            pstm.setFloat(2, bd.getTinHoc());
            pstm.setFloat(3, bd.getGDTC());

            return pstm.executeUpdate() > 0;
        }
    }

    public boolean deleteById(String msv) throws Exception {
        String sql = "DELETE FROM `bangdiem` WHERE maSV = ? ";
       
        try (
                Connection connection = DataHelper.openConnect();
                PreparedStatement pstm = connection.prepareStatement(sql);) {
            pstm.setString(1, msv);

            return pstm.executeUpdate() > 0;
        }
    }

    public BangDiem findByMSV(String msv) throws Exception {
        String sql = "select * from bangdiem where maSV = ?";
        try (
                Connection connection = DataHelper.openConnect();
                PreparedStatement pstm = connection.prepareStatement(sql);) {
            pstm.setString(1, msv);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    BangDiem bd = new BangDiem();
                    createBangDiem(bd, rs);

                    return bd;
                }
                return null;
            }
        }
    }

    public List<BangDiem> findAll() throws Exception {
        String sql = "select * from bangdiem ";
        try (
                Connection connection = DataHelper.openConnect();
                PreparedStatement pstm = connection.prepareStatement(sql);) {

            try (ResultSet rs = pstm.executeQuery()) {
                List<BangDiem> list = new ArrayList<>();
                while (rs.next()) {
                    BangDiem bd = new BangDiem();
                    createBangDiem(bd, rs);
                    list.add(bd);

                }
                return list;
            }
        }
    }

    public List<BangDiem> findTop(int top) throws Exception {
        String sql = "SELECT maSV,tiengAnh,tinHoc,GDTC, "
                + "(tiengAnh+tinHoc+GDTC)/3"
                + " AS DTB"
                + " FROM bangdiem ORDER BY DTB DESC LIMIT " + top;

        try (
                Connection connection = DataHelper.openConnect();
                PreparedStatement pstm = connection.prepareStatement(sql);) {

            try (ResultSet rs = pstm.executeQuery()) {
                List<BangDiem> list = new ArrayList<>();
                while (rs.next()) {
                    BangDiem bd = new BangDiem();
                    createBangDiem(bd, rs);
                    list.add(bd);

                }
                return list;
            }
        }
    }

    private void createBangDiem(BangDiem bd, final ResultSet rs) throws SQLException {
        bd.setMa(rs.getInt("maSV"));
        bd.setMaSinhVien(rs.getString("maSV"));
        bd.setTiengAnh(rs.getFloat("TiengAnh"));
        bd.setTinHoc(rs.getFloat("TinHoc"));
        bd.setGDTC(rs.getFloat("GDTC"));
    }
}
