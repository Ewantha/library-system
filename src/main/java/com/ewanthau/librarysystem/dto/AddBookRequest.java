package com.ewanthau.librarysystem.dto;

import lombok.Data;

@Data
public class AddBookRequest {
    private String title;
    private String author;
}
