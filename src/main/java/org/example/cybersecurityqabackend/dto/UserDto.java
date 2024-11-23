package org.example.cybersecurityqabackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserDto {
    private Long id;
    private String name;
    private String username;
    private String email;
}
