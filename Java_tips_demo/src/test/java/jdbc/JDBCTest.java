package jdbc;

import jdbc.dao.GoddessDao;
import jdbc.model.Goddess;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.Assert;

public class JDBCTest {
    private GoddessDao goddessDao = new GoddessDao();

    @Test
    public void testAddGoddess() throws SQLException {
        Goddess goddess = new Goddess("江泽明", 0, 46,
                Calendar.getInstance().getTime(), "maozedong@aqq.com",
                "10086", null, null);

        goddessDao.addGoddess(goddess);
    }

    @Test
    public void testGetGoddess() throws SQLException {
        Goddess goddess = goddessDao.get("毛泽东");
        Assert.assertNotNull(goddess);
    }

    @Test
    public void testUpdateGoddess() throws SQLException {
        Goddess goddess = goddessDao.get("毛泽东");
        goddess.setAge(110);
        goddessDao.updateGoddess(goddess);
        goddess = goddessDao.get("毛泽东");
        Assert.assertEquals((long) 110, (long) goddess.getAge());
    }

    @Test
    public void testQuery() throws SQLException {
        List<Goddess> goddesses = goddessDao.query();
        for (Goddess goddess : goddesses) {
            Assert.assertNotNull(goddess);
        }
    }
}
