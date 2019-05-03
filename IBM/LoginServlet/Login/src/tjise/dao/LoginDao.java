package tjise.dao;

import tjise.pojo.User;

/**
 * 创建dao接口
 *
 */
public interface LoginDao {
	User checkLoginDao(String uname, String pwd);

	User checkUidDao(String uid);
}
