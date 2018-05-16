package callback;

import callback.beans.Job;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class CallbackController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/callback")
    public Callback callback(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Callback(counter.incrementAndGet(), String.format(template, name));
    }

    @RequestMapping(value = "/callback", method = POST)
    @ResponseBody
    public void postResponse(@RequestBody Job job) {
        System.out.println(job);
    }
}
