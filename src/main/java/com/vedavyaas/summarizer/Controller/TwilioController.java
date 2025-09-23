package com.vedavyaas.summarizer.Controller;

import com.vedavyaas.summarizer.Service.AIService;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.web.bind.annotation.*;
import com.twilio.Twilio;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;
import com.twilio.twiml.MessagingResponse;

@RestController
@RequestMapping("/twilio")
public class TwilioController {

    private final AIService aiService;

    public TwilioController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping(value = "/webhook", produces = "application/xml")
    public String webhook(@RequestParam("From") String from, @RequestParam("Body") String body) {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        String accountSid = dotenv.get("TWILIO_SID", System.getenv("TWILIO_SID"));
        String authToken = dotenv.get("TWILIO_AUTH_TOKEN", System.getenv("TWILIO_AUTH_TOKEN"));

        Twilio.init(accountSid, authToken);
        Body responseBody;

        try {
            Twilio.init(accountSid, authToken);
            String reply = aiService.getGroq(body);

            responseBody = new Body.Builder(reply).build();
        } catch (Exception e) {
            responseBody = new Body.Builder("Error processing your message.").build();
        }

        return new MessagingResponse.Builder()
                .message(new Message.Builder().body(responseBody).build())
                .build()
                .toXml();
    }
}