package liga.medical.medicalmonitoring.router.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitRouterListener {

    @RabbitListener(queues = "common_monitoring")
    public void receiveAndRedirectMessage(String message) {

    }

}
