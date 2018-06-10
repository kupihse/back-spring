package com.example.backend.controllers;

import com.example.backend.models.User;
import com.example.backend.storages.dao.ProductDAO;
import com.example.backend.storages.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;



@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDAO userStorage;

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
        if (userStorage.add(p) == UserDAO.GetResp.OK) {
            System.out.println("Got User");

            // @TODO Sending email

            // @TODO Invocation of method that transfer user to confirmed

            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            System.out.println("Already Exists");
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
        }

    }



    @RequestMapping("/allconfirmed")
    public List<User> getUsers() {
        return userStorage.getAllConfirmed();
    }

    @RequestMapping("/allnotconfirmed")
    public List<User> getNotConf() {
        return userStorage.getNonConfirmed();
    }



    // авторизация
    @RequestMapping(value = "/log", method = RequestMethod.POST)
    @ResponseBody
    public String logUser(@RequestBody User p) {
        // + logging
        System.out.println("Logging: "+p);

        switch (userStorage.log(p)) {
            case USER_NOT_EXISTS:
                /*
                 @TODO **************** код, если нет юзера ******************
                 */
                System.out.println("Such user doesn't exist");
//                return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body("");
                return null;
            case WRONG_PASSWORD:
                /*
                 @TODO **************** код, если неверный пароль ******************
                 */
                System.out.println("Entry Fail");
//                return ResponseEntity.status(HttpServletResponse.SC_CONFLICT).body("");
                return null;
            case OK:
                /*
                 @TODO ***************** код входа ******************
                 */

                System.out.println("Entry Successful");
                User u = userStorage.get(p.getLogin());
                String token = u.token();
                System.out.println("Logging2: "+u);
//                return ResponseEntity.ok(token);
                return "\""+token+"\"";
        }
        
//        return ResponseEntity.ok("");
        return "";
    }

    // удаление юзера

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public void deleteUser(@RequestBody User p, HttpServletResponse resp){
        switch (userStorage.deleteUser(p)){
            case OK:
                System.out.println("User was succesfully deleted");
                resp.setStatus(HttpServletResponse.SC_OK);
                return;
            case FAIL:
                System.out.println("User was not deleted");
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
                return;
        }
    }

    @GetMapping("/get")
    public User getUser(@RequestParam("id") String id) {
        return userStorage.get(id);
    }


    @PostMapping("/wishlist/add/{id}/")
    public void addToWishlist(@RequestBody User u, @PathVariable("id") String pId) {
        userStorage.get(u.getLogin()).addToWishlist(pId);
    }

    @PostMapping("/wishlist/remove/{id}/")
    public void removeFromWishlist(@RequestBody User u, @PathVariable("id") String pId) {
        userStorage.get(u.getLogin()).removeFromWishlist(pId);
    }
}
