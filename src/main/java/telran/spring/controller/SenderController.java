package telran.spring.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import telran.spring.model.Message;
import telran.spring.service.Sender;

@RestController
@RequestMapping("sender")
@RequiredArgsConstructor
@Slf4j
public class SenderController {
	final Map<String, Sender> senders;
	@PostMapping
	String send(@RequestBody @Valid Message message) {
		log.debug("controller received message {}", message);
		Sender sender = senders.get(message.type);
		String res = "Wrong message type " + message.type;
		if (sender != null) {
			res = sender.send(message);
		}
		return res;
	}
}
