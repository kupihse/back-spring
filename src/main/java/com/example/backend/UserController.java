package com.example.backend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {
    public EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }


    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    // добавление юзера при регистрации
    public void addUser(@RequestBody User p, HttpServletResponse resp) {
        String id = UserStorage.addUser(p);
        if (!id.equals("")) {
            System.out.println("Got User");
            resp.setStatus(HttpServletResponse.SC_OK);
            emailService.sendMail(p.getLogin(),id);
        } else {
            System.out.println("Already Exists");
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }

    @RequestMapping(value = "/confirm/{id}")
    public void confirmUser(@PathVariable String id) {
        UserStorage.confirmUser(id);
    }

    @RequestMapping("/all")
    public Map<String,User> getUsers() {
        return UserStorage.getStorage();
    }

    @RequestMapping("/c")
    public Map<String,String> getNotConf() {
        return UserStorage.getNotConfirmedUsers();
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
