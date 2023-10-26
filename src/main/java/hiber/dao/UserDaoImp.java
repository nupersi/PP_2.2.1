package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);//todo ...sessionFactory.getCurrentSession() - повтор по коду. Стоит заинжектить и вызвать - Session
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");//todo codeStyle (hotKeys решают быстро вопросы с отступами, пробелами..)
      return query.getResultList();//todo - осмысленное именование переменных, например: query -> getUsersQuery, название метода: listUsers() -> getUsers(). Лучше привыкать сейчас к codeStyle, пока код маленький.
   }

   @Override
   public List<User> getDrivers(int series, String model) {//todo примитив не подаем, моветон..
      TypedQuery <User> query = sessionFactory.getCurrentSession().createQuery
              ("from User where userCar.model =: model and userCar.series =: series", User.class);
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.getResultList();
   }
}
