package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class TestValidator {

    @GetMapping("/test")
    public String showForm(User user) {
        return "form";
    }

    @GetMapping("/results")
    public String results() {
        return "results";
    }

    @PostMapping("/test")
    public String checkUser(@Valid User user, BindingResult bindingResult, RedirectAttributes attr) {
        //特别注意实体中的属性必须都验证过了，不然不会成功
        if (bindingResult.hasErrors()) {
            return "form";
        }
        /**
         * @Description:
         * 1.使用RedirectAttributes的addAttribute方法传递参数会跟随在URL后面 ，如上代码即为?name=long&age=45
         * 2.使用addFlashAttribute不会跟随在URL后面，会把该参数值暂时保存于session，待重定向url获取该参数后从session中移除，
         * 这里的redirect必须是方法映射路径。你会发现redirect后的值只会出现一次，刷新后不会出现了,对于重复提交可以使用此来完成。
         */
        attr.addFlashAttribute("user", user);
        return "redirect:/results";

    }
}
 