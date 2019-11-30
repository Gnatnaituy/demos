package tjise.service;

import tjise.pojo.User;

/**
 * 创建一个业务接口
 */
public interface LoginService {
    User checkLoginService(String uname, String pwd);
    //校验用户uid的方法
    User checkUidService(String uid);
}
