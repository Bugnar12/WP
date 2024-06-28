package model;


import lombok.*;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@WebListener
public class Board implements HttpSessionAttributeListener {
    int playerTurn;
    String board;

}
