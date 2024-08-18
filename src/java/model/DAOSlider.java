package model;

import static Constant.constant.RECORD_PER_PAGE;
import entity.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import entity.*;
import java.sql.SQLException;
import java.util.List;

public class DAOSlider extends DBConnect {

    protected Connection connection;
    private ResultSet rs;
    private PreparedStatement ps;

    public DAOSlider() {
        connection = new DBConnect().getConnection();
    }

    public ArrayList<Slider> getSliderList() {
        ArrayList<Slider> listSilder = new ArrayList<>();
        try {
            String sql = "Select * from Slider;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Slider slider = new Slider();
                slider.setSilderID(rs.getInt("sliderID"));
                slider.setSilderImg(rs.getString("sliderImg"));
                slider.setTile(rs.getString("title"));
                slider.setStatus(rs.getBoolean("status"));
                listSilder.add(slider);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return listSilder;
    }

    public int findTotalRecord(String status, String find) {
        String msg = "";
        int check = 0;
        if (!find.isEmpty()) {
            msg = "where title like ?";
            check = 1;
        } else {
            if (status.equals("All")) {
                msg = "";
                check = 2;
            } else {
                msg = "where status = ?";
                check = 3;
            }
        }
        String sql = "select count(sliderID) from Slider"
                + " " + msg;
        try (Connection connection = new DBConnect().con) {
            ps = connection.prepareStatement(sql);
            switch (check) {
                case 1:
                    ps.setString(1, "%" + find + "%");
                    break;
                case 2:
                    break;
                case 3:
                    ps.setString(1, status);
                    break;
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResultSetAndStatement(rs, ps);
        }
        return -1;
    }

    private void closeResultSetAndStatement(ResultSet rs, PreparedStatement ps) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                System.out.println(e + "ham mypurchase");
            }
        }

    }

    public List<Slider> findSliderByPage(int page, String status, String find) {
        String msg = "";
        int check = 0;
        List<Slider> listSlider = new ArrayList<>();
        if (!find.isEmpty()) {
            msg = "where title like ?";
            check = 1;
        } else {
            if (status.equals("All")) {
                msg = "";
                check = 2;
            } else {
                msg = "where status = ?";
                check = 3;
            }
        }
        String sql = "SELECT * FROM Slider " + msg + " ORDER BY sliderID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        try (Connection connection = new DBConnect().con) {
            ps = connection.prepareStatement(sql);
            switch (check) {
                case 1:
                    ps.setString(1, "%" + find + "%");
                    ps.setInt(2, (page - 1) * RECORD_PER_PAGE);
                    ps.setInt(3, RECORD_PER_PAGE);
                    break;
                case 2:
                    ps.setInt(1, (page - 1) * RECORD_PER_PAGE);
                    ps.setInt(2, RECORD_PER_PAGE);
                    break;
                case 3:
                    ps.setString(1, status);
                    ps.setInt(2, (page - 1) * RECORD_PER_PAGE);
                    ps.setInt(3, RECORD_PER_PAGE);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String SliderImg = rs.getString(2);
                String Title = rs.getString(3);
                boolean Status = rs.getBoolean(4);
                Slider slider = new Slider(id, SliderImg, Title, Status);
                listSlider.add(slider);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResultSetAndStatement(rs, ps);
        }
        return listSlider;
    }

    public boolean UpdateStatus(int check, String SliderID) {
        String updateSql = "";
        if (check == 1) {
            updateSql = "UPDATE Slider set status = 0 where sliderID = ?";
        }
        if (check == 2) {
            updateSql = "UPDATE Slider set status = 1 where sliderID = ?";
        }
        try {
            ps = connection.prepareStatement(updateSql);
            ps.setString(1, SliderID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Slider Slider(String SliderID) {
        String sql = "Select * from Slider where sliderID = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, SliderID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Slider(
                            rs.getInt("SliderID"),
                            rs.getString("sliderImg"),
                            rs.getString("title"),
                            rs.getBoolean("Status"),
                            rs.getString("note")
                    );
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public boolean UpdateSliderImg(String sliderID, String title, String status, String note, String image) {
        String updateSql = "Update Slider set sliderImg = ?, title = ?, status = ?, note = ? where sliderID = ?;";
        try {
            ps = connection.prepareStatement(updateSql);
            ps.setString(1, image);
            ps.setString(2, title);
            ps.setString(3, status);
            ps.setString(4, note);
            ps.setString(5, sliderID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public boolean UpdateSlider(String sliderID, String title, String status, String note) {
        String updateSql = "Update Slider set title = ?, status = ?, note = ? where sliderID = ?;";
        try {
            ps = connection.prepareStatement(updateSql);
            ps.setString(1, title);
            ps.setString(2, status);
            ps.setString(3, note);
            ps.setString(4, sliderID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public static void main(String[] args) {
        DAOSlider dao = new DAOSlider();
//        List<Slider> list = new ArrayList<>();
//        list = dao.findSliderByPage(1, "", "th");
//        for (Slider c : list) {
//            System.out.println(c);
//        }
        dao.UpdateSliderImg("1", "hocngu", "1", "hoc thich an cut", "anh");
    }
}
