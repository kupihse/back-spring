package com.example.backend;
import com.example.backend.AuthProc;
import org.springframework.web.bind.annotation.*;
import com.example.backend.AuthProcStorage;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@RestController
@RequestMapping("/User")
public class AuthProcController {

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public void addProduct(@RequestBody AuthProc p, HttpServletResponse resp) {
        if (!AuthProcStorage.check(p)) {
            AuthProcStorage.addUser(p);
            System.out.println("Got User");
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            System.out.println("Already Exist");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
