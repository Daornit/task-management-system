package stud.num.edu.mn.taskmanagementsystem.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stud.num.edu.mn.taskmanagementsystem.controller.activiti.ActivitiController;
import stud.num.edu.mn.taskmanagementsystem.dao.ImsUserDAO;
import stud.num.edu.mn.taskmanagementsystem.dto.ImsModel;
import stud.num.edu.mn.taskmanagementsystem.entity.ImsUser;
import stud.num.edu.mn.taskmanagementsystem.security.SecurityConstants;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/token")
public class TokenController {

    private Logger log = LoggerFactory.getLogger(ActivitiController.class);

    @Autowired
    ImsUserDAO imsUserDAO;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody() ImsModel imsModel) {
        log.info(imsModel.toString());
        ImsUser user = imsUserDAO.findByUsername(imsModel.getUsername());
        if(user != null){
            String token = getJWTToken(user);
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Та хэрэглэгчийн нэр эсвэл нууц үгээ шалгана уу!");
        }
    }

    @PostMapping("/verify")
    public ResponseEntity verifyToken(@RequestBody() String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Throwable throwable){
        }
        return ResponseEntity.ok(claims);
    }

    private String getJWTToken(ImsUser user) {
        String secretKey = SecurityConstants.JWT_SECRET;
        List grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(user.getUsername())
                .claim("authorities", user.getRoles()
                        .stream()
                        .map(s -> s.getRole())
                        .collect(
                                Collectors.toList()
                        )
                )
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
