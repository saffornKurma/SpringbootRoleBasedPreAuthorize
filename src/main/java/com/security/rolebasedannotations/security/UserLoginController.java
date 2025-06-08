package com.security.rolebasedannotations.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Permission;

@RestController
public class UserLoginController {

    @Autowired
    UserLoginEntityRepository userLoginEntityRepository;

    @Autowired
    UserLoginEntityService userLoginEntityService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/user-login")
    public ResponseEntity<String> login(@RequestBody UserLoginEntity userLoginEntity) {

        userLoginEntity.setPassword(passwordEncoder.encode(userLoginEntity.getPassword()));
        userLoginEntityService.save(userLoginEntity);
        System.out.println("Printing "+userLoginEntity.getUsername());
        System.out.println("Printing "+userLoginEntity.getRole());
        System.out.println("Printing "+userLoginEntity.getPassword());
        for(UserPermissionEntity p:userLoginEntity.getPermissions())
        {
            System.out.println(p.getName());
        }

         return ResponseEntity.ok(userLoginEntity.getUsername()+"hello logged in");
    }
}
