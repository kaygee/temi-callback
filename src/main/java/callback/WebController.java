package callback;

import callback.beans.RoleAndEnvironment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Controller
public class WebController {

  @RequestMapping(value = "/getDateAndTime")
  public ModelAndView getDateAndTime() {

    var now = LocalDateTime.now();
    var dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    var date_time = dtf.format(now);

    var params = new HashMap<String, Object>();
    params.put("date_time", date_time);

    return new ModelAndView("showMessage", params);
  }

  @GetMapping("/")
  public String index() {
    return "redirect:/form";
  }

  @GetMapping("/form")
  public String formGet() {
    return "form";
  }

  @PostMapping("/form")
  public String formPost(RoleAndEnvironment roleAndEnvironment, Model model) {
    model.addAttribute("roleAndEnvironment", roleAndEnvironment);
    return "form";
  }
}
