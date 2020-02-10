package com.nenadm.JwtProject.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/api/users")
public class UserApiController {

    @GetMapping
    @ResponseBody
    public String userApi(){

        return "Welcome";
    }
}
