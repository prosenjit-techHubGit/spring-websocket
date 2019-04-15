package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


public class AppWebSocketConfig implements WebSocketConfigurer {
 
    @Bean
    @CrossOrigin
    public WebSocketHandler myMessageHandler() {
        return new MyMessageHandler();
    }
 
    @Override
    @CrossOrigin
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myMessageHandler(), "/my-websocket-endpoint").setAllowedOrigins("*");
    }
 
}