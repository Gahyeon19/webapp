package org.example.springsecurityjwttest.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSignupDto {
    private String  userid;
    private String  password;
    private String  name;
    private String  address;
}
