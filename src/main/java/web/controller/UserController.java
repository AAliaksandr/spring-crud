package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String showAllUsers(Model model){
       List<User> list = userService.getAllUsers();
       model.addAttribute("users", list);
       return "users";
   }

   @GetMapping(value = {"/add"})
   public String showAddForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "add";
   }

   @PostMapping("/save")
   public String saveUser(@ModelAttribute("addUser") User user){
        userService.add(user);
        return "redirect:/";
   }
    @GetMapping("/user/{id}")
    public String getUser(@PathVariable("id")Long  id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PostMapping("/user/edit")
    public String editUser(@ModelAttribute("user") User user) {
        userService.edit(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id){
        userService.delete(userService.getUser(id));
        return "redirect:/";
    }

}
