package com.hu.libraryManagement.controller;

import com.hu.libraryManagement.VO.UserVO;
import com.hu.libraryManagement.annotation.UserLoginToken;
import com.hu.libraryManagement.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "Library Management")
@RestController
@RequestMapping(value = "/librarymanagement")
public class LibraryManageController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "regiset",notes = "regiset")
    @PostMapping(value = "/regiset")
    public boolean regiset(@RequestBody UserVO userVO){
        return userService.insert(userVO);
    }

    @ApiOperation(value = "login", notes = "login")
    @PostMapping(value = "/login")
    public Object login(@RequestBody UserVO userVO){
        return userService.login(userVO);
    }

    @ApiOperation(value = "selct", notes = "selct user information")
    @PostMapping(value = "/selct/{string}")
    public UserVO selct(@PathVariable String string){
        return userService.selcet(string);
    }

    @ApiOperation(value = "redis", notes = "redis")
    @PostMapping(value = "/redis")
    public void redis(){
       userService.redis();
    }

    @ApiOperation(value = "redis", notes = "redis")
    @PostMapping(value = "/redis1")
    public Object redis1(){
        return userService.redis1();
    }

    @ApiOperation(value = "redis", notes = "redis")
    @PostMapping(value = "/redis3/{s}")
    public void redis3(@PathVariable String s){
        userService.redis3(s);
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage() {
        return "你已通过验证";
    }

}
