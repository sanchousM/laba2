package task;

import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Preprocessor {
    private static final Pattern IDENTIFIERS_PATTERN = Pattern.compile("[A-Z]");
    private static final Pattern NUMERIC = Pattern.compile("([0-9]*[.])?[0-9]+");

    private final String input;
    private IllegalArgumentException exception;

    public Preprocessor(String input) {
        this.input = input;
    }

    public String preprocess() {
        var processedString = IDENTIFIERS_PATTERN.
                matcher(this.input).
                results().
                map(MatchResult::group).
                distinct().
                reduce(this.input, this::accumulator);
        if (this.exception != null) {
            throw this.exception;
        }
        return processedString;
    }

    private String accumulator(String all, String x) {
        System.out.printf("Define variable %s = ", x);
        var line = new Scanner(System.in).nextLine();
        if (!NUMERIC.matcher(line).matches()) {
            this.exception = new IllegalArgumentException(
                    String.format("Wrong value of variable %s = %s (not a number)", x, line)
            );
            return "";
        }
        var val = Double.parseDouble(line);
        return all.replace(x, String.valueOf(val));
    }
}
