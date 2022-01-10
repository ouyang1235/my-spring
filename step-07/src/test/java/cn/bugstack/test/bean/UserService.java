package cn.bugstack.test.bean;

public class UserService {

    private String name;

    private UserDao userDao;

    private String company;

    private String location;

    public UserService(String name) {
        this.name = name;
    }

    public UserService() {
    }

    public String queryUserInfo(){
        return userDao.queryUserName(name) + "," + company + "," + location;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
