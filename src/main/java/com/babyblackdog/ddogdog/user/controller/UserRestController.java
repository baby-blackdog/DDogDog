package com.babyblackdog.ddogdog.user.controller;

import static org.springframework.http.HttpStatus.OK;

import com.babyblackdog.ddogdog.common.point.Point;
import com.babyblackdog.ddogdog.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String test() {
        return userService.test();
    }

    @PatchMapping("/{email}")
    public ResponseEntity<String> addPoint(
            @PathVariable String email,
            @RequestBody long charge
    ) {
        userService.chargePoint(email, new Point(charge));
        String message = "%d point charged to %s".formatted(charge, email);
        return ResponseEntity
                .status(OK)
                .body(message);
    }
}
