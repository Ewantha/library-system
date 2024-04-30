package com.ewanthau.librarysystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LibraryErrorResponse {
    private String message;

}
