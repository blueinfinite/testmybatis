package com.blueinfinite;

import com.blueinfinite.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessagePrinter {
    final private MessageService service;

    @Autowired
    public MessagePrinter(MessageService messageService) {
        this.service=messageService;
    }

    public void pringMessage() {
        System.out.println(this.service.getMessage());
    }
}
