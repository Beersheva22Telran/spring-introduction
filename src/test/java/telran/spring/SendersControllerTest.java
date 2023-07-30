package telran.spring;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import telran.spring.controller.SenderController;
import telran.spring.model.Message;
import telran.spring.service.Sender;
class MockSender implements Sender {

	@Override
	public String send(Message message) {
		
		return "test";
	}

	@Override
	public String getMessageTypeString() {
		
		return "test";
	}

	@Override
	public Class<? extends Message> getMessageTypeObject() {
		
		return Message.class;
	}
	
}
@WebMvcTest(SenderController.class)
class SendersControllerTest {
@Autowired
	MockMvc mockMvc;
@Autowired
ObjectMapper mapper;
	@Test
	void test() {
		
	}

}
