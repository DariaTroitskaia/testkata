import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Введите арифметическое выражение, состоящее из двух целых чисел и оператора, без пробелов");
            String scannerString = scanner.nextLine();
            if (!scannerString.equalsIgnoreCase("Стоп")) {
                System.out.println(calc(scannerString));
            } else {
                System.out.println("Вы остановили калькулятор");
                break;
            }

        }
        scanner.close();
    }
    public static String calc(String input) throws Exception {
        char[] inputChars = input.toCharArray();
        String operatorForSplit = ""; // знак оператора для разделения введенной строки на первое и второе число
        String operator = ""; // знак оператора для вычисления результата
        int i;
        for (i = 0; i < input.length(); i++) { // ищу знак оператора в введенной строке, прохожу по каждому символу из массива
            switch (inputChars[i]) {
                case '+' -> {
                    operatorForSplit = "\\+";
                    operator = "+";
                }
                case '-' -> {
                    operatorForSplit = "-";
                    operator = "-";
                }
                case '*' -> {
                    operatorForSplit = "\\*";
                    operator = "*";
                }
                case '/' -> {
                    operatorForSplit = "/";
                    operator = "/";
                }
            }
        }

        if (operator == "") {throw new Exception("Нужно использовать оператор +, -, * или /");}
        String[] numbers = input.split(operatorForSplit);


        if (numbers.length != 2) {
            numbers = new String[2];
            numbers[0]="";
            numbers[1]="";
        }
        if (!(((arabNumbers(numbers[1])) | ((romanNumbers(numbers[1])>0))) & ((arabNumbers(numbers[0])) | ((romanNumbers(numbers[0])>0))))) {
            throw new Exception("Некорректное выражение, введите два числа и один арифметический оператор между ними без пробелов");
        }
        if (((arabNumbers(numbers[1]) | (arabNumbers(numbers[0]))) & (((romanNumbers(numbers[0])>0) | (romanNumbers(numbers[1])>0))))) {
            throw new Exception("Одновременно ввели римские и арабские числа");
        }
        if (((romanNumbers(numbers[1])) > 0) & ((romanNumbers(numbers[0])) > 0)) {
            int number1 = romanNumbers(numbers[0]);
            int number2 = romanNumbers(numbers[1]);
            String result = resultCalc(number1, number2, operator);
            if (Integer.parseInt(result) < 0) {
                    throw new Exception("Ошибка: результатом работы с римскими числами могут быть только положительные числа");
                } else {
                    return romanTranslate(result);
                }
        } else {
            int number1;
            int number2;
            try {
                number1 = Integer.parseInt(numbers[0]);
                number2 = Integer.parseInt(numbers[1]);
            } catch (Exception e){
                throw new Exception("Римские и арабские не могут быть введены одновременно");
            }
            if ((number1 < 1)| (number2 < 1) |(number2>10)|(number1> 10)|(number1 == 0) | (number2 == 0)) {
                throw new Exception("Калькулятор умеет работать только с числами от 1 до 10 включительно");
            }
            return resultCalc(number1, number2, operator);
        }
    }
    public static int romanNumbers (String romanNumber){
       int number = 0;
        String [] romans  = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        for (int i = 0; i < romans.length; i++){
            if (romanNumber.equals(romans[i])) {
                number = i + 1;
            }
        }
        return number;
    }
    public static boolean arabNumbers (String arabNumber) throws Exception {
        try {
            Integer.parseInt(arabNumber);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public static String resultCalc (int number1, int number2, String operator) throws Exception {
        int result = 0;
        switch (operator) {
            case "+" -> result = number1 + number2;
            case "-" -> result = number1 - number2;
            case "*" -> result = number1 * number2;
            case "/" -> result = number1 / number2;
            case "" -> throw new Exception("Ебанько");
                    }
        return String.valueOf(result);
    }
    public static String romanTranslate (String result){
        String[] romansResult = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                    "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                    "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX",
                    "*****", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII",
                    "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
                    "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                    "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                    "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX",
                    "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII",
                    "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
        int index = Integer.parseInt(result)-1;
        return romansResult[index];
        }
    }


