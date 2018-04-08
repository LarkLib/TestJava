package com.company.postgres;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestOperation {
    public void TestPostgres() {
        String url = "jdbc:postgresql://localhost:5432/test";
        String user = "testuser";
        String password = "123654";

        try {
            Class.forName("org.postgresql.Driver");
            long rowNum = 0L;
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT VERSION()");
            if (rs.next()) {
                System.out.println(rs.getString(1));
            }

            PreparedStatement pst = con.prepareStatement("select last_value from business.persion_seq_seq;");
            rs = pst.executeQuery();
            if (rs.next()) {
                rowNum = rs.getLong(1);
                System.out.println(rowNum);
            }

            Random random = new Random();
            char[] chars = new char[4];
            for (int i = 0; i < 4; i++) {
                chars[i] = (char) (random.nextInt(26) + 65);
            }
            int age = random.nextInt(10) + 16;
            String name = new String(chars) + (++rowNum);
            float wage = random.nextInt(500) + 123456.78f;
            String sql = String.format("insert into business.persion(name,age,wage) values('%s',%d,%f);", name, age, wage);
            st.addBatch(sql);

            for (int i = 0; i < 4; i++) {
                chars[i] = (char) (random.nextInt(26) + 65);
            }
            age = random.nextInt(10) + 16;
            name = new String(chars) + (++rowNum);
            wage = random.nextInt(500) + 123456.78f;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date hiredate = addDays(new Date(), 0 - random.nextInt(100) - 90);
            sql = String.format("insert into business.persion(name,age,wage,hiredate) values('%s',%d,%f,'%s');", name, age, wage, sdf.format(hiredate));
            st.addBatch(sql);
            st.executeBatch();

            pst = con.prepareStatement("select id,name from business.persion order by seq;");
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(String.format("%s,%s", rs.getString(1), rs.getString(2)));
            }
            rs.close();
            st.close();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(ex.getClass().getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void TestMyBatis() {
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
}
