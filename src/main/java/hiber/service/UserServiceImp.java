package hiber.service;

import hiber.config.AppConfig;
import hiber.dao.UserDao;
import hiber.dao.UserDaoImp;
import hiber.model.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

   //todo избавляемся от import-ов лишних ..codeStyle (можно начать использовать hotKeys)

   @Autowired
   private UserDao userDao;

   @Transactional//todo - Transaction выносим над классом, над методами, где это необходимо проставляем Transaction c параметром readOnly, например
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> getDrivers(int series, String model) {//todo отказываемся от примитивов..
      return userDao.getDrivers(series,model);
   }
}
