package starter;

import controller.UsersRepository;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

@SpringBootApplication
@ComponentScan(basePackages = "controller")
public class SpringBootStarter implements CommandLineRunner {

    @Autowired
    UsersRepository repository;

    @Autowired
    DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarter.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("\n1.findAll()...");
        for (User user : repository.findAll()) {
            System.out.println(user);
        }
    }
}
