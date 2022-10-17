package liga.medical.medicalmonitoring.router.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.medicalmonitoring.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitSenderService {
    private final AmqpTemplate amqpTemplate;

    private final ObjectMapper objectMapper;

    public void sendMessage(MessageDto messageDto, String queue) throws JsonProcessingException {
        String messageStr = objectMapper.writeValueAsString(messageDto);
        amqpTemplate.convertAndSend(queue, messageStr);
        System.out.printf("Сообщение %s в очередь %s отправлено!\n", messageStr, queue);
    }

    public void sendError(String message) {
        amqpTemplate.convertAndSend("error_queue", message);
        System.out.printf("Сообщение %s в очередь %s отправлено!\n", message, "error_queue");
    }
}
