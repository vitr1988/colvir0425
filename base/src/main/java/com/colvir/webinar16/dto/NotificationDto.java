package com.colvir.webinar16.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {

    private Long id;
    private String message;
    private NotificationType type;
    private Language lang;

    public NotificationDto(String message) {
        this(message, Language.RU);
    }

    public NotificationDto(String message, Language lang) {
        this.message = message;
        this.lang = lang;
    }

    public enum NotificationType {
        INFORM, WARNING, ERROR
    }

    public enum Language {
        RU, EN, DE, FR
    }
}
