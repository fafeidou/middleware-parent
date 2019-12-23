package com.batman.redislikes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LikedCountDTO {
    private Integer id;
    private String key;
    private Integer count;

    public LikedCountDTO(String key, Integer count) {
        this.key = key;
        this.count = count;
    }
}
