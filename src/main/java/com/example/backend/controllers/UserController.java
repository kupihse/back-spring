package com.example.backend.controllers;

import com.example.backend.models.User;
import com.example.backend.storages.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.backend.storages.ProductMemoryStorage;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Autowired
private UserDAO storager;

@RestController
@RequestMapping("/user")
public class UserController {
    if (storager.add(User u)==UserDAO.GetResp.FAIL){}

    // --- SET EMAIL SERVICE
//    public EmailService emailService;
//    @Autowired
//    public void setEmailService(EmailService emailService) {
//        this.emailService = emailService;
//    }
    // ---------


    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    // добавление юзера при регистрации
    public void addUser(@RequestBody User p, HttpServletResponse resp) {
        if ()



        //String result =
        if (!id.equals("")) {
            System.out.println("Got User");

            // Send email
//            emailService.sendMail(p.getLogin(),id);

            resp.setStatus(HttpServletResponse.SC_OK);
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
