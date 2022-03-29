package callback;

import callback.beans.CookiesForRoleAndEnvironment;
import callback.repository.CookiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WebController {

  @Autowired CookiesRepository cookieRepository;

  @GetMapping("/")
  public String index() {
    return "redirect:/cookies/list";
  }

  @GetMapping("/cookies/list")
  public String cookiesList(Model model) {
    List<CookiesForRoleAndEnvironment> cookies = cookieRepository.findAll();
    model.addAttribute("cookies", cookies);
    return "cookies-list";
  }
}
