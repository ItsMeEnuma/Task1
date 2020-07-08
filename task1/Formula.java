package task1;

import task1.tokenizer.Token;
import task1.tokenizer.TokenType;
import task1.tokenizer.Tokenizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Formula {
    private ArrayList<Token> tokens;

    public void prepare(String text) {
        Tokenizer tokenizer = new Tokenizer();
        List<Token> tokens = tokenizer.parse(text);

        //TODO
//        if(!isCorrect(tokens)){
//            throw new Error("formula is not currect");
//        }

        this.tokens = (ArrayList<Token>) tokens;
    }


    private List<Token> insertVariables(List<Token> tokens ,int[] variables){
        ArrayList<Token> mtokens = new ArrayList<>();

        HashMap<String,Integer> vars = new HashMap<>();

        //copy tokens to mtokens
        for(Token token : tokens){
            mtokens.add(new Token(token));
            if(token.getType() == TokenType.ID){
                vars.put(token.getValue(),null);
            }
        }

        if(vars.size() != variables.length){
            throw new Error("wrong number of variables");
        }

        int ind = 0;

        for(Token token : mtokens){
            if(token.getType() == TokenType.ID){
                if(vars.get(token.getValue()) == null){
                    vars.put(token.getValue(),variables[ind]);
                    token.setType(TokenType.INT);
                    token.setValue(Integer.toString(variables[ind]));
                    ind++;
                }
                else{
                    token.setType(TokenType.INT);
                    token.setValue(Integer.toString(vars.get(token.getValue())));
                }
            }
        }
        return mtokens;
    }

    //TODO
    private boolean isCorrect(List<Token> tokens){
        if(tokens == null){
            return false;
        }

        if(tokens.size() < 3 || tokens.size() % 2 == 0){
            return false;
        }

        for(int i = 0; i < tokens.size(); i++){
            if(i % 2 == 0 && tokens.get(i).getType() == TokenType.OP){
                return false;
            }
            if(i % 2 == 1 && tokens.get(i).getType() != TokenType.OP){
                return false;
            }
        }
        return true;
    }



    public int execute(int ... variables){
        List<Token> tokens = insertVariables(this.tokens,variables);

        Stack<Integer> integers = new Stack<>();
        Stack<String> operations = new Stack<>();

        for (Token token : tokens) {
            if (token.getType() == TokenType.INT) {
                integers.push(Integer.parseInt(token.getValue()));
            }
            if(token.getType() == TokenType.OP){
                String op = token.getValue();
                if (operations.size() == 0) {
                    operations.push(op);
                } else {
                    if ((op.equals("+") || op.equals("-")) && (operations.peek().equals("+")
                            || operations.peek().equals("-"))) {
                        int a = integers.pop();
                        int b = integers.pop();
                        int c = operations.pop().equals("+") ? a + b : b - a;
                        integers.push(c);
                        operations.push(op);
                        continue;
                    }
                    if ((op.equals("+") || op.equals("-")) && (operations.peek().equals("*")
                            || operations.peek().equals("/"))) {
                        int a = integers.pop();
                        int b = integers.pop();
                        int c;
                        if(operations.pop().equals("*")){
                            c =  a * b;
                        }
                        else{
                            if(a == 0){
                                throw new Error("division error by zero");
                            }
                            c = b / a;
                        }
                        integers.push(c);
                        operations.push(op);
                        continue;
                    }
                    if ((op.equals("*") || op.equals("/")) && (operations.peek().equals("+")
                            || operations.peek().equals("-"))) {
                        operations.push(op);
                        continue;
                    }
                    if ((op.equals("*") || op.equals("/")) && (operations.peek().equals("*")
                            || operations.peek().equals("/"))) {
                        int a = integers.pop();
                        int b = integers.pop();
                        int c;
                        if(operations.pop().equals("*")){
                            c =  a * b;
                        }
                        else{
                            if(a == 0){
                                throw new Error("division error by zero");
                            }
                            c = b / a;
                        }
                        integers.push(c);
                        operations.push(op);
                    }
                }
            }
        }

        while(operations.size() != 0){
            String op = operations.pop();

            switch (op){
                case "+" : {
                    int a = integers.pop();
                    int b = integers.pop();
                    integers.push(a + b);
                    break;
                }
                case "-" : {
                    int a = integers.pop();
                    int b = integers.pop();
                    integers.push(b - a);
                    break;
                }
                case "*" : {
                    int a = integers.pop();
                    int b = integers.pop();
                    integers.push(a * b);
                    break;
                }

                case "/" : {
                    int a = integers.pop();
                    int b = integers.pop();
                    if(a == 0){
                        throw new Error("division error by zero");
                    }
                    integers.push(b / a);
                    break;
                }
            }
        }

        return integers.pop();
    }
}
