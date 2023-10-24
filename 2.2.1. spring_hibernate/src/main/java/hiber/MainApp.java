package hiber;

import hiber.config.AppConfig;
import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      user1.setUserCar(new Car("Urus", 123));
      user2.setUserCar(new Car("Corolla", 124));

      System.out.println("Добавление пользователей в таблицу\n");
      userService.add(user1);
      userService.add(user2);
      userService.add(new User());

      System.out.println("Вывод всех пользователей таблицы \"users\"\n");
      userService.listUsers().forEach(System.out::println);

      System.out.println("Вывод всех пользователей таблицы \"users\" по условию наличия переданного автомобиля\n");
      System.out.println(userService.getDrivers(123, "Urus"));

      context.close();
   }
}
