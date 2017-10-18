package rus.controllers;

import internals.FindPage;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ruslan on 16.10.2017.
 */
@Controller
public class MainController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String main(){
        return "redirect:/home";
    }


    /**
     *
     * @param city
     * @param request
     * @return main view
     * @throws Exception
     * @see internals.FindPage
     */

    @RequestMapping(value = "/findCity", method = RequestMethod.POST)
    public String findCity(@RequestParam("city") String city, HttpServletRequest request) throws Exception {



        Map<String,String> map = new HashMap<>();
        map.put("Saint-Petersburg","26063");
        map.put("Kazan","27595");
        map.put("Vladivostok","31960");
        map.put("Samara","28807");
        map.put("Nizniy Novgorod","27459");

        String str = null;
        Element element = null;
        try {
            element =  FindPage.getContentOfPage("http://weather.myday.uz/vse-strani?ctid=" + map.get(city) + "&w_type=1&to_main=0");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (element != null) {
            request.setAttribute("text",element.toString());
        }

        return "main";
    }


}
