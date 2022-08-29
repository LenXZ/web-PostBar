package dao;

import java.util.List;
import entity.User;
/**
 * �ӿ�
 * */
public interface UserDao {

	public List<User> findAll();
	public void addUser(User user);
	public User findById(String idString);
	public void deleteUser(String idString);
	public void modifyUser(User user);

}
