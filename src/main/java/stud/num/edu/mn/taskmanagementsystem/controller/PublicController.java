package stud.num.edu.mn.taskmanagementsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
@author Bat-orgil
@date 2019-12-01
*/
@RestController
@RequestMapping("/api/v1/public")
public class PublicController {

    @GetMapping("/test")
    public ResponseEntity test() {
        return ResponseEntity.ok("TEST");
    }
}
