package com.mysite.todolist.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.socket.EnableWebSocketSecurity;
import org.springframework.security.messaging.access.intercept.MessageMatcherDelegatingAuthorizationManager;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
// @EnableWebSocketSecurity
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${WS_ALLOWED_ORIGINS}")
    String allowedOrigins;


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/my-chat")
                .setAllowedOrigins(allowedOrigins)
                .withSockJS();
    }

    // currently doesn't work idk why

    // @Bean
    // AuthorizationManager<Message<?>> authorizationManager(
    //         MessageMatcherDelegatingAuthorizationManager.Builder messages) {
    //     messages
    //         .simpDestMatchers("/my-chat.sendAsAdmin").hasRole("ADMIN")
    //         // .simpDestMatchers("/user/queue/errors").permitAll()
    //         // .simpDestMatchers("/admin/**").hasRole("ADMIN")
    //         .anyMessage().authenticated();
    //     return messages.build();
    // }

}
