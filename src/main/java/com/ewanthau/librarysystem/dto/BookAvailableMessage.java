package com.ewanthau.librarysystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookAvailableMessage {
    private String content;
}
