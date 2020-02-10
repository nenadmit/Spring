package com.nenadm.JwtProject.Controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.nenadm.JwtProject.Model.JwtToken;
import com.nenadm.JwtProject.Model.User;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

@RestController
@RequestMapping("/login")
public class LoginController {


    @GetMapping
    @ResponseBody
    public String pt(@RequestParam String username,@RequestParam String password
    ,HttpServletResponse response) throws IOException {

        User user = new User(username,password);

        //HttpUrlConnection connects to authentication server
        URL url = new URL("http://127.0.0.1:8080//authenticate");
        HttpURLConnection request = (HttpURLConnection)url.openConnection();

        //Sends a post request to the authentication server with User in JSON format as a request body
        request.setRequestMethod("POST");
        request.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        request.setDoOutput(true);
        request.setDoInput(true);


        OutputStream os = request.getOutputStream();
        os.write(user.toJson().getBytes("UTF-8"));
        os.close();
        request.connect();
        System.out.println(request.getResponseCode());


        //Reads the response from /authenticate and retreives the JWT Token
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            result.append(line);
        }

        reader.close();
        request.disconnect();

        // if /authenticate doesnt return a token it means the user is not found
        if(result.toString().equals("User not found!")){
            return result.toString();
        }


        //if credentials are ok, 
        Cookie cookie = new Cookie("authorization",result.toString());
        response.addCookie(cookie);







          return "Sucessful";
    }

}
