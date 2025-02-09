// Java Program to Illustrate Creation Of
// Service implementation class

package com.ms.vidhyalebox.emailsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

// Importing required classes

import jakarta.mail.internet.MimeMessage;

// Annotation
@Service
// Class
// Implementing EmailService interface
public class EmailServiceImpl implements EmailService {

    @Autowired private JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}") private String sender;

    // Method 1
    // To send a simple email
    public String sendSimpleMail(EmailDetails details)
    {

        // Try block to check for exceptions
        try {

            // Create a MIME message
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            // Set email properties
            helper.setTo(details.getRecipient());
            helper.setSubject(details.getSubject());
            helper.setText(details.getMsgBody());
            helper.setFrom(sender);  // Sender's email address
            // Send email
            javaMailSender.send(mimeMessage);

            return  "Email sent successfully!";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

//    // Method 2
//    // To send an email with attachment
//    public String
//    sendMailWithAttachment(EmailDetails details)
//    {
//        // Creating a mime message
//        MimeMessage mimeMessage
//            = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper;
//
//        try {
//
//            // Setting multipart as true for attachments to
//            // be send
//            mimeMessageHelper
//                = new MimeMessageHelper(mimeMessage, true);
//            mimeMessageHelper.setFrom(sender);
//            mimeMessageHelper.setTo(details.getRecipient());
//            mimeMessageHelper.setText(details.getMsgBody());
//            mimeMessageHelper.setSubject(
//                details.getSubject());
//
//            // Adding the attachment
//            FileSystemResource file
//                = new FileSystemResource(
//                    new File(details.getAttachment()));
//
//            mimeMessageHelper.addAttachment(
//                file.getFilename(), file);
//
//            // Sending the mail
//            javaMailSender.send(mimeMessage);
//            return "Mail sent Successfully";
//        }
//
//        // Catch block to handle MessagingException
//        catch (MessagingException e) {
//
//            // Display message when exception occurred
//            return "Error while sending mail!!!";
//        }
//    }
}
