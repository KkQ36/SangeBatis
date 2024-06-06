package dao;

/**
 * @author kq
 * 2024-06-04 17:23
 **/
public interface UserDao {

    /**
     * 获取姓名
     */
    String getName(int id);

    /**
     * 获取年龄
     */
    Integer getAge(int id);
}
