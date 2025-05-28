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
    private String lang;

    public enum NotificationType {
        INFORM, WARNING, ERROR
    }
}
