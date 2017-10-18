import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rus.controllers.MainController;

/**
 * Created by ruslan on 17.10.2017.
 */
@Configuration
public class TestContext {

    @Bean
    MainController mainController(){return new MainController();};

}
