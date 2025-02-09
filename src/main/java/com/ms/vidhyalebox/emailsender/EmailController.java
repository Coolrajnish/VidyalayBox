package com.ms.vidhyalebox.emailsender;// Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

// Annotation
@RestController
// Class
public class EmailController {

    @Autowired private EmailService emailService;

    // Sending a simple Email


    // Sending email with attachment
//    @PostMapping("/sendMailWithAttachment")
//    public String sendMailWithAttachment(
//        @RequestBody EmailDetails details)
//    {
//        String status
//            = emailService.sendMailWithAttachment(details);
//
//        return status;
//    }
}