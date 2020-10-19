package task;

import java.util.Scanner;

public class Main {
    private static final String desc = "Разбор и вычисление арифметических выражений\n" +
            "\n" +
            "Поддерживаются операции:\n" +
            "Скобки\n" +
            "Сложение\n" +
            "Вычитание\n" +
            "Умножение\n" +
            "Деление\n" +
            "Возведение в степень\n" +
            "Функция синуса\n" +
            "Функция косинуса\n" +
            "\n" +
            "Переменные: буквы латинского алфавита в верхнем регистре (т.е от A до Z)\n" +
            "Пробелы игнорируются\n" +
            "Простейшее детектирование ошибок\n" +
            "\n" +
            "Пример выражения: (2+2)*2*cos(sin(2/3))-Y^2\n" +
            "Введите выражение:";

    private static final boolean DEBUG = true;

    public static void main(String[] args) {
        System.out.println(desc);
        var expr = new Scanner(System.in).nextLine();
        expr = new Preprocessor(expr).preprocess();
        var tokens = Token.tokenize(expr);
        if (DEBUG) {
            System.out.println(Token.tokensAsString(tokens));
        }
        var group = Group.grouping(tokens, true);
        var value = group.evaluate().getValue();
        System.out.printf("Результат: %s", value);
    }
}
