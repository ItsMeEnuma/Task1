package task1.tokenizer;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private int pos;
    private String text;

    private Token parseInt(){
        String value = "";
        while(pos < text.length() && text.charAt(pos) >= '0' && text.charAt(pos) <= '9'){
            value += text.charAt(pos);
            pos++;
        }

        return new Token(TokenType.INT, value);
    }

    private Token parseId(){
        String value = "";
        while(pos < text.length() && text.charAt(pos) >= 'a' && text.charAt(pos) <= 'z'){
            value += text.charAt(pos);
            pos++;
        }

        return new Token(TokenType.ID, value);
    }

    public List<Token> parse(String text){

        ArrayList<Token> tokens = new ArrayList<>();

        this.pos = 0;
        this.text = text;

        while (pos < text.length()){
            char c = text.charAt(pos);

            if(c >= '0' && c <= '9'){
                tokens.add(parseInt());
            }

            else if(c >= 'a' && c <= 'z'){
                tokens.add(parseId());
            }

            else if(c == '+' || c == '-' || c == '*' || c == '/'){
                tokens.add(new Token(TokenType.OP, Character.toString(c)));
            }

            else if(c == '(' || c == ')'){
                tokens.add(new Token(c == '(' ? TokenType.LB : TokenType.RB, Character.toString(c)));
            }

            else{
                pos++;
            }
        }

        return tokens;
    }
}
