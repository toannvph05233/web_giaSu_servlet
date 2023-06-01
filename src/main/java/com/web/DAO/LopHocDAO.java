package com.web.DAO;

import com.web.Model.GiaSu;
import com.web.Model.LopHoc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Statement;

public class LopHocDAO extends DBconnect{
    public void create(LopHoc lopHoc) throws SQLException {
        String query = "INSERT INTO LopHoc (username_hoc_sinh, username_gia_su, ten_lop_hoc, gio_hoc, ngay_hoc, hoc_phi, phi_gia_su, mo_ta, hinh_anh) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, lopHoc.getUsernameHocSinh());
            statement.setString(2, lopHoc.getUsernameGiaSu());
            statement.setString(3, lopHoc.getTenLopHoc());
            statement.setString(4, lopHoc.getGioHoc());
            statement.setDate(5, new java.sql.Date(lopHoc.getNgayHoc().getTime()));
            statement.setInt(6, lopHoc.getHocPhi());
            statement.setInt(7, lopHoc.getPhiGiaSu());
            statement.setString(8, lopHoc.getMoTa());
            statement.setString(9, lopHoc.getHinhAnh());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                lopHoc.setId(generatedId);
            }
        }
    }

    public void update(LopHoc lopHoc) throws SQLException {
        String query = "UPDATE LopHoc SET  ten_lop_hoc = ?, gio_hoc = ?, " +
                "ngay_hoc = ?, hoc_phi = ?, phi_gia_su = ?, mo_ta = ?, hinh_anh = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, lopHoc.getTenLopHoc());
            statement.setString(2, lopHoc.getGioHoc());
            statement.setDate(3, new java.sql.Date(lopHoc.getNgayHoc().getTime()));
            statement.setInt(4, lopHoc.getHocPhi());
            statement.setInt(5, lopHoc.getPhiGiaSu());
            statement.setString(6, lopHoc.getMoTa());
            statement.setString(7, lopHoc.getHinhAnh());
            statement.setInt(8, lopHoc.getId());

            statement.executeUpdate();
        }
    }

    public void delete(int lopHocId) throws SQLException {
        String query = "DELETE FROM LopHoc WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, lopHocId);
            statement.executeUpdate();
        }
    }

    public LopHoc getById(int lopHocId) throws SQLException {
        String query = "SELECT * FROM LopHoc WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, lopHocId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToLopHoc(resultSet);
                }
            }
        }

        return null;
    }

    public List<LopHoc> getAll() throws SQLException {
        List<LopHoc> lopHocs = new ArrayList<>();

        String query = "SELECT * FROM LopHoc";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                LopHoc lopHoc = mapResultSetToLopHoc(resultSet);
                lopHocs.add(lopHoc);
            }
        }

        return lopHocs;
    }

    public List<LopHoc> getLopHocByGS(String usernameGS) throws SQLException {
        List<LopHoc> lopHocs = new ArrayList<>();

        String query = "SELECT * FROM LopHoc where username_gia_su = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1,usernameGS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                LopHoc lopHoc = mapResultSetToLopHoc(resultSet);
                lopHocs.add(lopHoc);
            }
        }

        return lopHocs;
    }

    public int countLopHocByGiaSuUsername(String giaSuUsername) throws SQLException {
        String query = "SELECT COUNT(*) FROM LopHoc WHERE username_gia_su = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, giaSuUsername);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        }

        return 0;
    }


    private LopHoc mapResultSetToLopHoc(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String usernameHocSinh = resultSet.getString("username_hoc_sinh");
        String usernameGiaSu = resultSet.getString("username_gia_su");
        String tenLopHoc = resultSet.getString("ten_lop_hoc");
        String gioHoc = resultSet.getString("gio_hoc");
        Date ngayHoc = resultSet.getDate("ngay_hoc");
        int hocPhi = resultSet.getInt("hoc_phi");
        int phiGiaSu = resultSet.getInt("phi_gia_su");
        String moTa = resultSet.getString("mo_ta");
        String hinhAnh = resultSet.getString("hinh_anh");

        return new LopHoc(id, usernameHocSinh, usernameGiaSu, tenLopHoc, gioHoc, ngayHoc, hocPhi, phiGiaSu, moTa, hinhAnh);
    }

}
