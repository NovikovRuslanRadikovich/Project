package rus.configuration;

import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by ruslan on 16.10.2017.
 */
@Order(1)
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     *  массив классов компонентов
     * @return CoreConfig.class
     */
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {CoreConfig.class};
    }

    /**
     * массив классов для MVC конфигурации
     * @return WebConfig.class
     */

    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }


    protected String[] getServletMappings() {return new String[] {"/"};
    }


}
