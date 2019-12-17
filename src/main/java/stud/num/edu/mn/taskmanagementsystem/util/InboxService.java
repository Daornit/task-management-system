package stud.num.edu.mn.taskmanagementsystem.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stud.num.edu.mn.taskmanagementsystem.dao.InboxDAO;
import stud.num.edu.mn.taskmanagementsystem.entity.ImsUser;
import stud.num.edu.mn.taskmanagementsystem.entity.Inbox;

import java.util.Date;

@Service
//Шуудангийн хэсэг
public class InboxService {
    @Autowired
    InboxDAO inboxDAO;

    //Шуудангийн хэсгийн объектууд болон өгөгдлийн санд хадгалах
    public void addInbox(String title, String content, ImsUser user, String link) {
        Inbox inbox = new Inbox();
        inbox.setTitle(title);
        inbox.setContent(content);
        inbox.setNotified(false);
        inbox.setCode(TaskCodeGenerator.newCode());
        inbox.setLink(link);
        inbox.setDate(new Date());
        inbox.setUser(user);
        inboxDAO.save(inbox);
    }
}
