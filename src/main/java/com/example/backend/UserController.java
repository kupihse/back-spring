package com.example.backend;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    // добавление юзера при регистрации
    public void addUser(@RequestBody User p, HttpServletResponse resp) {
        if (UserStorage.addUser(p)) {
            System.out.println("Got User");
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            System.out.println("Already Exists");
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }
    // авторизация
    @RequestMapping(value = "/log", method = RequestMethod.POST)
    @ResponseBody
    public void logUser(@RequestBody User p, HttpServletResponse resp) {
        switch (UserStorage.getUser(p)) {
            case WRONG_LOGIN:
                /*
                 **************** код, если нет юзера ******************
                 */
                System.out.println("Such user doesn't exist");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            case WRONG_PASS:
                /*
                 **************** код, если неверный пароль ******************
                 */
                System.out.println("Entry Fail");
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
                return;
            case OK:
                /*
                 ***************** код входа ******************
                 */
                System.out.println("Entry Successful");
                resp.setStatus(HttpServletResponse.SC_OK);
        }
    }

}
