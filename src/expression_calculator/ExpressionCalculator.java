package expression_calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import stack.DynamicStack;
import stack.Stack;
import stack.StackException;

public class ExpressionCalculator {

    public static double calculate(String s) throws StackException {

        List<Token> tokens = toPostfixed(Token.toTokenList(s));

        Stack<Double> numberStack = new DynamicStack<>();

        for (Token t : tokens) {

            if (t.isOperand()) {

                numberStack.add(t.getIfOperand());
                continue;

            }

            if (t.isOperator()) {

                char op = t.getIfOperator();
                double x = numberStack.pop();
                double y = numberStack.pop();
                numberStack.add(processOperation(op, y, x));
                continue;

            }

        }

        return numberStack.pop();

    }

    public static List<Token> toPostfixed(List<Token> tokens) throws StackException {

        if (!checkBalancedExpression(tokens))
            throw new ArithmeticException("The expression is not balanced. Please refactor it");

        List<Token> postFixedExpression = new ArrayList<>();
        Stack<Token> operatorStack = new DynamicStack<>();

        for (Token t : tokens) {

            if (t.isOperand()) {

                postFixedExpression.add(t);
                continue;

            }

            if (t.isSymbol()) {

                if (operatorStack.isEmpty()) {

                    operatorStack.add(t);
                    continue;

                }

                if (t.isOpenBracket()) {

                    operatorStack.add(t);
                    continue;

                }

                if (t.isCloseBracket()) {

                    while (!operatorStack.isEmpty()
                            && (!operatorStack.top().isCloseBracket())) {

                        postFixedExpression.add(operatorStack.pop());

                    }

                    continue;

                }

                while ((!operatorStack.isEmpty() && !operatorStack.top().isOpenBracket())
                        && (precedence(operatorStack.top().getIfOperator()) >= precedence(t.getIfOperator()))) {

                    postFixedExpression.add(operatorStack.pop());

                }

                operatorStack.add(t);

            }

        }

        while (!operatorStack.isEmpty()) {

            postFixedExpression.add(operatorStack.pop());

        }

        return postFixedExpression;

    }

    private static boolean checkBalancedExpression(List<Token> tokens) {

        Stack<Token> bracketStack = new DynamicStack<>();

        for (Token t : tokens) {

            if (t.isOpenBracket()) {

                bracketStack.add(t);
                continue;

            }

            if (t.isCloseBracket()) {

                try {

                    bracketStack.pop();
                    continue;

                } catch (StackException e) {

                    return false;

                }

            }

        }

        return bracketStack.isEmpty();

    }

    private static int precedence(char operator) {

        return switch (operator) {
            case '+' -> 1;
            case '-' -> 1;
            case '*' -> 2;
            case '/' -> 2;
            case '^' -> 3;
            default -> throw new NoSuchElementException(operator + " is not a valid operator");
        };

    }

    private static double processOperation(char operator, double x, double y) {

        return switch (operator) {
            case '+' -> x + y;
            case '-' -> x - y;
            case '*' -> x * y;
            case '/' -> x / y;
            case '^' -> Math.pow(x, y);
            default -> throw new NoSuchElementException(operator + " is not a valid operator");
        };

    }

}