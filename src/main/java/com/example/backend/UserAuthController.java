package com.example.backend;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/user")
public class UserAuthController {

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public void addProduct(@RequestBody UserAuth p, HttpServletResponse resp) {
        if (!UserAuthStorage.check(p)) {
            UserAuthStorage.addUser(p);
            System.out.println("Got User");
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            System.out.println("Already Exist");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
