import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class InfijaToSufija {

    public static void main(String[] args) {
        System.out.println("Ejemplos de expresiones infijas:");
        System.out.println("(A+B)*C-(D-E)*(F+G): \t"+infASuf("(A+B)*C-(D-E)*(F+G)"));

        Scanner input = new Scanner(System.in);
        System.out.print("Escribe una expresión infija:");
        String text = input.nextLine();
        System.out.println(infASuf(text));
    }
    public static String infASuf(String exInfija){
        Map<Character, Integer> expresion = new HashMap<>();
        expresion.put('*',3);
        expresion.put('/',3);
        expresion.put('+',2);
        expresion.put('-',2);
        expresion.put('(',1);

        Stack<Character> pilaOperadores = new Stack<>();
        List<Character> postfixList = new ArrayList<>();
        char[] symbols = exInfija.toCharArray();

        for(char symbol: symbols){
            if(Character.isLetterOrDigit(symbol)){
                postfixList.add(symbol);
            }else if (symbol=='('){
                pilaOperadores.push(symbol);
            }else if (symbol==')'){
                char topSymbol = pilaOperadores.pop();
                while(topSymbol!='('){
                    postfixList.add(topSymbol);
                    topSymbol = pilaOperadores.pop();
                }
            }else{
                while(!pilaOperadores.isEmpty() && expresion.get(pilaOperadores.peek()) >= expresion.get(symbol)){
                    postfixList.add(pilaOperadores.pop());
                }
                pilaOperadores.push(symbol);
            }
        }
        while(!pilaOperadores.isEmpty()){
            postfixList.add(pilaOperadores.pop());
        }

        StringBuilder postExpresion = new StringBuilder();
        for(char c: postfixList){
            postExpresion.append(c).append(" ");
        }
        return postExpresion.toString().trim();
    }
}
