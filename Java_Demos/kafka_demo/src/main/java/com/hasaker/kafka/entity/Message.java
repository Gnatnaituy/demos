package com.hasaker.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 余天堂
 * @since 2019/10/30 23:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    private String title;
    private String content;
}
