import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itis.kpfu.interfaces.ImplWarrior;

/**
 * Created by ruslan on 17.02.2017.
 */

public class Start {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
        ImplWarrior warrior = (ImplWarrior) context.getBean("RussianWarrior");
        System.out.println("Warrior\'s weapon is " + warrior.getWeapon() + " shield is " + warrior.getShield());
    }
}
