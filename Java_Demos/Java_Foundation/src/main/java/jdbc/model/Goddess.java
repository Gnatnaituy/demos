package jdbc.model;

import java.util.Date;


/**
 * CREATE TABLE imooc_goddess (
 * id      int          NOT NULL PRIMARY KEY AUTO_INCREMENT ,
 * name    varchar(10)  NOT NULL ,
 * sex     int          NOT NULL DEFAULT 0,
 * age     int          DEFAULT 0 ,
 * birthday date        DEFAULT NULL,
 * email varchar(20)    DEFAULT NULL,
 * phone varchar(11)    DEFAULT NULL,
 * create_date datetime DEFAULT NULL,
 * update_date datetime DEFAULT NULL
 * ) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
 */
public class Goddess {
    private Integer id = null;
    private String name = null;
    private Integer sex = null;
    private Integer age = null;
    private Date birthday = null;
    private String email = null;
    private String phone = null;
    private Date createDate = null;
    private Date updateDate = null;

    public Goddess() {
    }

    public Goddess(String name, Integer sex, Integer age, Date birthday, String email,
                   String phone, Date createDate, Date updateDate) {
        if (name != null) this.name = name;
        if (sex != null) this.sex = sex;
        if (age != null) this.age = age;
        if (birthday != null) this.birthday = birthday;
        if (email != null) this.email = email;
        if (phone != null) this.phone = phone;
        if (createDate != null) this.createDate = createDate;
        if (updateDate != null) this.updateDate = updateDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
