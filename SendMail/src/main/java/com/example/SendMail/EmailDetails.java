package com.example.SendMail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
    private String recipitent;
    private String msgBody;
    private String subject;
    private String attachment;
}
