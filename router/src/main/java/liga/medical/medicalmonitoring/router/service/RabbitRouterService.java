package liga.medical.medicalmonitoring.router.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.medicalmonitoring.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitRouterService {

    private final RabbitSenderService rabbitSenderService;

    private final ObjectMapper objectMapper;

    public void routeMessage(String message) {
        try {
            MessageDto messageDto = objectMapper.readValue(message, MessageDto.class);
            String status = messageDto.getStatus();
            switch (status) {
                case "daily":
                    rabbitSenderService.sendMessage(messageDto, "daily_queue");
                    break;
                case "alert":
                    rabbitSenderService.sendMessage(messageDto, "alert_queue");
                    break;
            }
        } catch (Exception ex) {
            rabbitSenderService.sendError(ex.getMessage());
        }
    }
}
