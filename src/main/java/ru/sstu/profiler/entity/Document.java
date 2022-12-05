package ru.sstu.profiler.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@ToString
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String filePath;
    private String name;
    @ManyToOne
    private DocType type;
    @ManyToOne
    private DocCategory category;

}
