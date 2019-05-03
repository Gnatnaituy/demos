package tjise.service.impl;

import tjise.dao.LoginDao;
import tjise.dao.impl.LoginDaoImpl;
import tjise.pojo.User;
import tjise.service.LoginService;

public class LoginServiceImpl implements LoginService {
    private LoginDao ld = new LoginDaoImpl();

    @Override
    public User checkLoginService(String uname, String pwd) {
        //调用Moder下的方法了  DAO
        return ld.checkLoginDao(uname, pwd);
    }

    @Override
    public User checkUidService(String uid) {
        return ld.checkUidDao(uid);
    }


}
