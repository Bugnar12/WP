package com.example.demo.model;

import jakarta.servlet.http.HttpSessionAttributeListener;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class Board implements HttpSessionAttributeListener {
    private int playerTurn;
    String board;

}
