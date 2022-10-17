package liga.medical.medicalmonitoring.router.configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue getRouterQueue() {
        return new Queue("common_monitoring");
    }

    @Bean("dailyQueue")
    public Queue getDailyQueue() {
        return new Queue("daily_queue");
    }

    @Bean("alertQueue")
    public Queue getAlertQueue() {
        return new Queue("alert_queue");
    }

    @Bean("errorQueue")
    public Queue getErrorQueue() {
        return new Queue("erroe_queue");
    }
}
