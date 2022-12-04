package ru.sstu.profiler.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ExecResponse {
    private String text;
    private double execTime;
    private int files;
    private int progress;
    private String urlResult;
}
