package expression_calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Token {

    private String value;
    private TokenType type;

    public Token() {

        value = "";
        type = TokenType.UNDEFINED;

    }

    public Token(String value, TokenType type) {

        this.value = value;
        this.type = type;

    }

    public Token(char value, TokenType type) {

        this.value = String.valueOf(value);
        this.type = type;

    }

    public static List<Token> toTokenList(String s) {

        s = s.replaceAll("\\s", "");

        List<Token> tokens = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (c >= '0' && c <= '9') {

                int j = i;

                while (j < s.length() && (s.charAt(j) >= '0' && s.charAt(j) <= '9'))
                    j++;

                tokens.add(new Token(s.substring(i, j), TokenType.OPERAND));

                i = j - 1;

                continue;

            }

            if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {

                tokens.add(new Token(c, TokenType.OPERATOR));
                continue;

            }

            if (c == '(') {

                tokens.add(new Token(c, TokenType.OPEN_BRACKET));
                continue;

            }

            if (c == ')') {

                tokens.add(new Token(c, TokenType.CLOSE_BRACKET));
                continue;

            }

            throw new ArithmeticException("this is not a valid arithmetic element");

        }

        return tokens;

    }

    public boolean isSymbol() {
        return type == TokenType.OPERATOR || type == TokenType.OPEN_BRACKET || type == TokenType.CLOSE_BRACKET;
    }

    public boolean isOperand() {
        return type == TokenType.OPERAND;
    }

    public boolean isOperator() {
        return type == TokenType.OPERATOR;
    }

    public boolean isOpenBracket() {
        return type == TokenType.OPEN_BRACKET;
    }

    public boolean isCloseBracket() {
        return type == TokenType.CLOSE_BRACKET;
    }

    public char getIfOperator() {

        if (!isOperator())
            throw new NoSuchElementException(value + " is not an operator!!");

        return value.charAt(0);

    }

    public double getIfOperand() {

        if (!isOperand())
            throw new NoSuchElementException(value + " is not an operand!!");

        return Double.parseDouble(value);

    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "{" + value + ", " + type + " }";
    }
}
