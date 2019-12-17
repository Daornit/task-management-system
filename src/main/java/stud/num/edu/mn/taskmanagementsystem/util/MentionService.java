package stud.num.edu.mn.taskmanagementsystem.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stud.num.edu.mn.taskmanagementsystem.dao.ImsUserDAO;
import stud.num.edu.mn.taskmanagementsystem.entity.ImsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
//Сэтгэгдэл үлдээх хэсэгт хэрэглэгчийг тодорхойлох хэсэг
public class MentionService {

    @Autowired
    InboxService inboxService;

    @Autowired
    ImsUserDAO imsUserDAO;

    //Шуудангийн хэсэгт email-ээр mention хийсэн хэрэглэгчид мэдэгдэх
    public void mention(ImsUser from, String content, String link) {
        Matcher m = Pattern.compile("\\w+@\\w+.com").matcher(content);
        List<String> emailsList = new ArrayList<>();

        while (m.find()) {
            emailsList.add(m.group());
        }

        for (String email : emailsList) {
            inboxService.addInbox(
                    "Танд " + from.getUsername() + " хэрэглэгч мэдэгдэл хийсэн байна.",
                    content,
                    imsUserDAO.findByEmail(email),
                    link
            );
        }
    }

}
