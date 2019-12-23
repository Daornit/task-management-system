package stud.num.edu.mn.taskmanagementsystem.controller.user;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;
import stud.num.edu.mn.taskmanagementsystem.controller.activiti.ActivitiController;
import stud.num.edu.mn.taskmanagementsystem.dao.*;
import stud.num.edu.mn.taskmanagementsystem.dto.RegisterModel;
import stud.num.edu.mn.taskmanagementsystem.dto.UserForGui;
import stud.num.edu.mn.taskmanagementsystem.dto.UserModel;
import stud.num.edu.mn.taskmanagementsystem.entity.ConfirmationToken;
import stud.num.edu.mn.taskmanagementsystem.entity.ImsRole;
import stud.num.edu.mn.taskmanagementsystem.entity.ImsUser;
import stud.num.edu.mn.taskmanagementsystem.exceptions.UserAlreadyExistException;
import stud.num.edu.mn.taskmanagementsystem.security.SecurityConstants;
import stud.num.edu.mn.taskmanagementsystem.service.EmailSenderService;
import stud.num.edu.mn.taskmanagementsystem.util.TaskCodeGenerator;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class AccountManagementController {

    private Logger log = LoggerFactory.getLogger(ActivitiController.class);

    @Autowired
    ImsUserDAO imsUserDAO;
    @Autowired
    ImsRoleDAO imsRoleDAO;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody() ImsModel imsModel) {
        log.info(imsModel.toString());
        ImsUser user = imsUserDAO.findByEmail(imsModel.getEmail());
        log.info(user.toString());
        if(
                user != null &&
                user.getPassword().equals(imsModel.getPassword()) &&
                user.isEnabled()
        ){

            System.out.println("USER :: " + user.toString());
            String token = getJWTToken(user);

            UserModel userModel = new UserModel();
            userModel.setSuccess(true);
            UserForGui userForGui = new UserForGui();
            userForGui.setId(user.getId());
            userForGui.setAvatar(user.getAvatar());
            userForGui.setGroupCode(user.getGroupCode());
            userForGui.setUsername(user.getUsername());
            userForGui.setPermissions(new HashMap<>());
            userForGui.getPermissions().put("role", user.getRole() != null ? user.getRole().getRole(): "default");
            userModel.setToken(token);
            userModel.setUser(userForGui);

            return ResponseEntity.ok(userModel);
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

    @GetMapping("/currentUser")
    public ResponseEntity currentUser(@RequestHeader("Authorization") String authorization) {
        UserModel userModel = new UserModel();
        Claims claims = null;
        if(!authorization.equals("")){
            try {
                claims = Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET.getBytes())
                        .parseClaimsJws(authorization.substring(7))
                        .getBody();
                ImsUser user = imsUserDAO.findByEmail(claims.getSubject());

                userModel.setSuccess(true);
                UserForGui userForGui = new UserForGui();
                userForGui.setId(user.getId());
                userForGui.setAvatar(user.getAvatar());
                userForGui.setUsername(user.getUsername());
                userForGui.setPermissions(new HashMap<>());
                userForGui.setGroupCode(user.getGroupCode());
                userForGui.getPermissions().put("role", user.getRole() != null ? user.getRole().getRole(): "default");
                userModel.setToken(authorization);
                userModel.setUser(userForGui);
                log.info("userModel :: " + userModel.toString());
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        if(claims != null) return ResponseEntity.ok(userModel);
        return ResponseEntity.ok(claims);
    }

    private String getJWTToken(ImsUser user) {
        String secretKey = SecurityConstants.JWT_SECRET;
        List grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(user.getEmail())
                .claim("authorities", user.getRole().getPermissions()
                        .stream()
                        .map(s -> s.getPermission())
                        .collect(
                                Collectors.toList()
                        )
                )
                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 600000)) 30 min
                .setExpiration(new Date(System.currentTimeMillis() + 1600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

    @Autowired
    private ConfirmationTokenDAO confirmationTokenDAO;

    @Autowired
    private EmailSenderService emailSenderService;

    @GetMapping("")
    public ResponseEntity se(){
        return ResponseEntity.ok("TEST");
    }
    @GetMapping("/send")
    public ResponseEntity send(ImsUser user) throws UserAlreadyExistException, IOException, MessagingException {
        emailSenderService.sendmailD();
        return ResponseEntity.ok("Та Email хаягаараа өөрийн бүртгэлээ баталгаажуулна уу");
    }
    @GetMapping("/sendMail")
    public ResponseEntity send() throws UserAlreadyExistException, IOException, MessagingException {
        emailSenderService.sendMail();
        return ResponseEntity.ok("Та Email хаягаараа өөрийн бүртгэлээ баталгаажуулна уу");
    }


    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ResponseEntity displayRegistration(@RequestBody RegisterModel model) throws UserAlreadyExistException {
        log.info(model.toString());
        String password = "";
        ImsUser existingUser = imsUserDAO.findByEmail(model.getEmail());
        if(existingUser != null) throw new UserAlreadyExistException();
        ImsUser user = new ImsUser();
        user.setAvatar("http://localhost:4000/api/v1/downloadFile/avatar.png");
        user.setEmail(model.getEmail().toLowerCase());
        user.setPassword(model.getPassword());
        user.setFirstName(model.getFirstName());
        user.setLastName(model.getLastName());
        user.setPhone(model.getPhone());
        user.setEnabled(false);
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(user.getEmail());
        mailMessage.setFrom("orjuun2012@gmail.com");

        if(model.getIsInvitation() != null && model.getIsInvitation()){
            ImsRole role = null;
            if(model.getRole() != null) role = imsRoleDAO.findByRole(model.getRole());
            user.setRole(role);
            user.setGroupCode(model.getGroupCode());
            password = TaskCodeGenerator.newCode().substring(0, 8);
            user.setPassword(password);
        } else {
            user.setRole(imsRoleDAO.findByRole("manager"));
            user.setGroupCode(TaskCodeGenerator.newCode());
        }

        imsUserDAO.save(user);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationToken.setActive(true);
        confirmationTokenDAO.save(confirmationToken);

        if(model.getIsInvitation()){
            mailMessage.setSubject("Таныг Creativity системд урьсан байна!");
            mailMessage.setText("Таны системд нэвтрэх нууц үг: " + password +"\nЭнэхүү URL ээр орж өөрийн бүртгэлээ баталгаажуулна уу : "
                    +"http://localhost:7000/mn/confirm/"+confirmationToken.getConfirmationToken());
        } else {
            mailMessage.setSubject("Бүртгэлээ баталгаажуулна уу!");
            mailMessage.setText("Энэхүү URL ээр орж өөрийн бүртгэлээ баталгаажуулна уу : "
                    +"http://localhost:7000/mn/confirm/"+confirmationToken.getConfirmationToken());
        }
        emailSenderService.sendEmail(mailMessage);

        return ResponseEntity.ok("Та Email хаягаараа өөрийн бүртгэлээ баталгаажуулна уу");
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity confirmUserAccount(@RequestParam("token") String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenDAO.findByConfirmationTokenAndActive(confirmationToken, true);
        token.setActive(false);
        if(token != null)
        {
            ImsUser user = imsUserDAO.findByEmail(token.getUser().getEmail());
            user.setEnabled(true);
            confirmationTokenDAO.save(token);
            imsUserDAO.save(user);
            return ResponseEntity.ok("Та амжилттай бүртгүүллээ");
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }
}
