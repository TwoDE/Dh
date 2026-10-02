package lexer;

import lexer.tokens.ID;

public class TokenList {

    public static final TokenCompare INT_0 = new TokenCompare(
            "0",
            new ID("0", 0)
    );
    public static final TokenCompare INT_1 = new TokenCompare(
            "1",
            new ID("1", 1)
    );
    public static final TokenCompare INT_2 = new TokenCompare(
            "2",
            new ID("2", 2)
    );
    public static final TokenCompare INT_3 = new TokenCompare(
            "3",
            new ID("3", 3)
    );
    public static final TokenCompare INT_4 = new TokenCompare(
            "4",
            new ID("4", 4)
    );
    public static final TokenCompare INT_5 = new TokenCompare(
            "5",
            new ID("5", 5)
    );
    public static final TokenCompare INT_6 = new TokenCompare(
            "6",
            new ID("6", 6)
    );
    public static final TokenCompare INT_7 = new TokenCompare(
            "7",
            new ID("7", 7)
    );
    public static final TokenCompare INT_8 = new TokenCompare(
            "8",
            new ID("8", 8)
    );
    public static final TokenCompare INT9 = new TokenCompare(
            "9",
            new ID("9", 9)
    );

}
