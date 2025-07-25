package com.example.False.Alarm.websocket;

import com.example.False.Alarm.service.ChatMonitorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.cors.CorsConfiguration;
import java.util.Collections;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final ChatMonitorService chatMonitorService;

    public WebSocketConfig(ChatMonitorService chatMonitorService) {
        this.chatMonitorService = chatMonitorService;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("*");
        corsConfig.addAllowedHeader("*");
        corsConfig.addAllowedMethod("*");
        
        registry.addHandler(alarmWebSocketHandler(), "/ws/alarm")
                .setAllowedOrigins("*");
    }

    @Bean
    public AlarmWebSocketHandler alarmWebSocketHandler() {
        return new AlarmWebSocketHandler(chatMonitorService);
    }
}
