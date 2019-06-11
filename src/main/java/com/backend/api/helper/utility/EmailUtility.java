package com.backend.api.helper.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;


import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
public class EmailUtility {

    /**
     * Dependency injection from mail sender bean
     */

    @Autowired
    private JavaMailSender mailSender;

    private static final Logger logger = LogManager.getLogger(EmailUtility.class);



    /**
     * email utility
     *
     * @return boolean based on successful/unsuccessful operation
     */
    public boolean sendEmail(final Object body) {

        try {
            // creates a simple e-mail object
            MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
                @Override
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("shubam555i.walias@gmail.com"));
                    mimeMessage.setSubject("Backend fault");
                    mimeMessage.setContent("<h3>Stack Trace</h3> " +
                            "<br><br>" +
                            body +
                            "<br><br><hr>" +
                            "<br><br><br>" + "Regards" + "<br>" + "Team People", "text/html");
                }
            };

            // sends the e-mail
            mailSender.send(mimeMessagePreparator);
            logger.info("Mail Send");
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            e.printStackTrace();
            return false;
        }
        return true;

    }
}
