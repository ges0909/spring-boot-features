package com.valantic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublishController.class);

    private final JmsTemplate jmsTemplate; // helper class for sending and receiving messages

    @Autowired
    public PublishController(final JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @PostMapping("/publishMessage")
    public ResponseEntity<String> publishMessage(@RequestBody final Message message) {
        try {
            jmsTemplate.convertAndSend("demo-queue", message);
            return new ResponseEntity<>("Sent", HttpStatus.OK);
        } catch (final Exception e) {
            LOGGER.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
