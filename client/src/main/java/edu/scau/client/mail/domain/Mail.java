package edu.scau.client.mail.domain;

import lombok.Data;

@Data
public class Mail {
    private Long userId;
    private String to;
    private String subject;
    private String content;
}
