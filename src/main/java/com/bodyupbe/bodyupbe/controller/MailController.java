package com.bodyupbe.bodyupbe.controller;

import com.bodyupbe.bodyupbe.model.user.MailStructure;
import com.bodyupbe.bodyupbe.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/mail")
public class MailController {
    @Autowired
    private MailService mailService;
    @PostMapping("/send/{mail}")
    public String sendMail(@PathVariable String mail, @RequestBody MailStructure mailStructure){
        mailService.sendMail(mail, mailStructure);
        return "Successfully sent the mail !!";
    }

}
