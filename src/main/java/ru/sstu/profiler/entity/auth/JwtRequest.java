package ru.sstu.profiler.entity.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class JwtRequest {
    private String login;
    private String password;
}
