package jdbc.dao;

import jdbc.DBUtilities;
import jdbc.MySQLDataSource;
import jdbc.model.Goddess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoddessDao {
    private MySQLDataSource dataSource;

    public GoddessDao() {
        dataSource = new MySQLDataSource();
    }

    private void setData(PreparedStatement s,
                         String         name,
                         Integer        sex,
                         Integer        age,
                         java.util.Date birthday,
                         String         email,
                         String         phone,
                         Integer        id) throws SQLException {
        int index = 1;
        if (name != null) {
            s.setString(index, name);
            index++;
        }
        if (sex != null) {
            s.setInt(index, sex);
            index++;
        }
        if (age != null) {
            s.setInt(index, age);
            index++;
        }
        if (birthday != null) {
            s.setDate(index, new Date(birthday.getTime()));
            index++;
        }
        if (email != null) {
            s.setString(index, email);
            index++;
        }
        if (phone != null) {
            s.setString(index, phone);
            index++;
        }
        if (id != null) {
            s.setInt(index, id);
        }
    }

    /**
     * Add a record using given Goddess instance
     * @param g Goddess
     */
    public void addGoddess(Goddess g) throws SQLException {
        Connection connection = dataSource.getConnection();
        String insertSQL = "INSERT INTO imooc_goddess(name, sex, age, birthday, email, phone, create_date,"
                + "update_date) VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIME(), CURRENT_TIME())";
        PreparedStatement statement = connection.prepareStatement(insertSQL);

        setData(statement, g.getName(), g.getSex(), g.getAge(), g.getBirthday(), g.getEmail(),
                g.getPhone(), null);

        statement.execute();
        DBUtilities.closeConnections(null, statement, connection);
    }

    /**
     * Update a record using given Goddess instance
     * @param g Goddess
     */
    public void updateGoddess(Goddess g) throws SQLException {
        Connection connection =  dataSource.getConnection();
        String updateSQL = "UPDATE imooc_goddess set name=?, sex=?, age=?, birthday=?, " +
                "email=?, phone=?, update_date=CURRENT_TIME() WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(updateSQL);

        setData(statement, g.getName(), g.getSex(), g.getAge(), g.getBirthday(), g.getEmail(),
                g.getPhone(), g.getId());

        statement.execute();
        DBUtilities.closeConnections(null, statement, connection);
    }

    /**
     * Delete a record using given name
     * @param name String
     */
    public void deleteGoddessByName(String name) throws SQLException {
        Connection connection = dataSource.getConnection();
        String deleteSQL = "DELETE FROM imooc_goddess WHERE name = ?";
        PreparedStatement statement = connection.prepareStatement(deleteSQL);

        statement.setString(1, name);

        statement.execute();
        DBUtilities.closeConnections(null, statement, connection);
    }

    /**
     * Delete a record using given Goddess instance
     * @param goddess Goddess instance
     */
    public void deleteGoddessByObject(Goddess goddess) throws SQLException {
        Connection connection = dataSource.getConnection();
        String deleteSQL = "DELETE FROM imooc_goddess WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(deleteSQL);

        statement.setInt(1, goddess.getId());

        statement.execute();
        DBUtilities.closeConnections(null, statement, connection);
    }

    /**
     * Get all records
     * @return List<Goddess> A List of Goddess
     */
    public List<Goddess> query() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery("SELECT * FROM imooc_goddess");

        List<Goddess> goddesses = new ArrayList<>();
        while (res.next()) {
            goddesses.add(new Goddess(
                    res.getString("name"),
                    res.getInt("sex"),
                    res.getInt("age"),
                    res.getDate("birthday"),
                    res.getString("email"),
                    res.getString("phone"),
                    res.getDate("create_date"),
                    res.getDate("update_date"))
            );
        }

        DBUtilities.closeConnections(res, statement, connection);
        return goddesses;
    }

    /**
     * Get Goddess instance using given name
     * @param name String
     * @return Goddess instance
     */
    public Goddess get(String name) throws SQLException {
        Connection connection = dataSource.getConnection();
        String getSQL = "SELECT id, name, sex, age, birthday, email, phone, create_date, " +
                "update_date FROM imooc_goddess WHERE name = ?";
        PreparedStatement statement = connection.prepareStatement(getSQL);

        statement.setString(1, name);
        // 设置每次从数据库中获取的记录数n
        // 大于0则每次获取n条,n = 0则获取整个记录集
        statement.setFetchSize(1);

        ResultSet res = statement.executeQuery();
        Goddess goddess = new Goddess();
        while (res.next()) {
            goddess.setId(res.getInt("id"));
            goddess.setName(res.getString("name"));
            goddess.setSex(res.getInt("sex"));
            goddess.setAge(res.getInt("age"));
            goddess.setBirthday(res.getDate("birthday"));
            goddess.setEmail(res.getString("email"));
            goddess.setPhone(res.getString("phone"));
            goddess.setCreateDate(res.getDate("create_date"));
            goddess.setUpdateDate(res.getDate("update_date"));
        }

        DBUtilities.closeConnections(res, statement, connection);
        return goddess;
    }
}
