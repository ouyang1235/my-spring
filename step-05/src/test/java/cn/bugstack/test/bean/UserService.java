package cn.bugstack.test.bean;

public class UserService {

    private String name;

    private UserDao userDao;

    public UserService(String name) {
        this.name = name;
    }

    public UserService() {
    }

    public String queryUserInfo(){
        return userDao.queryUserName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "name='" + name + '\'' +
                '}';
    }
}
