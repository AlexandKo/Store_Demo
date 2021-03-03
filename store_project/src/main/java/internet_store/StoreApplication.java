package internet_store;

import internet_store.integration.telegram.InitTelegram;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication()
public class StoreApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(StoreApplication.class);

        InitTelegram initTelegram = context.getBean(InitTelegram.class);
        initTelegram.init();
        System.out.println("App started");
    }
}