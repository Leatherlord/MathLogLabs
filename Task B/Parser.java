import java.util.ArrayList;

public class Parser {
    private int pos;
    private String string;
    private ArrayList<Value> values = new ArrayList<>();
    private boolean falseResult;

    Tree parseExpression () {
        Tree left = parseMul();
        if (string.charAt(pos) == '|') {
            pos++;
            Tree right = parseExpression();
            return new Tree(left, right, new Value("", false), Operations.OR, false);
        }
        return left;
    }

    Tree parseMul() {
        Tree left = parseTerm();
        if (string.charAt(pos) == '&') {
            pos++;
            Tree right = parseMul();
            return new Tree(left, right, new Value("", false), Operations.AND, false);
        }
        return left;

    }

    Tree parseImpl() {
        Tree left = parseExpression();
        if (string.charAt(pos) == '-') {
            pos += 2;
            Tree right = parseImpl();
            return new Tree(left, right, new Value("", false), Operations.IMPL, false);
        }
        return left;
    }


    Value parseValue(String str) {
        if (!(String.valueOf(string.charAt(pos)).equals("&") ||
                String.valueOf(string.charAt(pos)).equals("|") ||
                String.valueOf(string.charAt(pos)).equals("-") ||
                String.valueOf(string.charAt(pos)).equals("!") ||
                String.valueOf(string.charAt(pos)).equals(";") ||
                String.valueOf(string.charAt(pos)).equals("(") ||
                String.valueOf(string.charAt(pos)).equals(")"))) {
            String s = String.valueOf(string.charAt(pos));
            pos++;
            return parseValue(str + s);
        } else {
            Value value;
            int i = 0;
            try {
                while (!str.equals(values.get(i).getLiteral())) {
                    i++;
                }
                value = values.get(i);
            } catch (IndexOutOfBoundsException e) {
                value = new Value(str, false);
                values.add(value);
            }
            return value;
        }
    }

    Tree parseTerm() {
        boolean inverted;
        if (string.charAt(pos) == '!') {
            pos++;
            inverted = true;
        } else {
            inverted = false;
        }
        while (string.charAt(pos) == '!') {
            pos++;
            inverted = !inverted;
        }
        if (string.charAt(pos) == '(') {
            pos++;
            Tree res = res = parseImpl();
            if (string.charAt(pos) != ')') {
                System.out.println("???");
            }
            pos++;
            res.setInverted(inverted);
            return res;
        } else {
            if (!(String.valueOf(string.charAt(pos)).equals("&") ||
                    String.valueOf(string.charAt(pos)).equals("|") ||
                    String.valueOf(string.charAt(pos)).equals("-") ||
                    String.valueOf(string.charAt(pos)).equals("!") ||
                    String.valueOf(string.charAt(pos)).equals(";") ||
                    String.valueOf(string.charAt(pos)).equals("(") ||
                    String.valueOf(string.charAt(pos)).equals(")"))) {
                return new Tree(null, null, parseValue(""), Operations.CONST, inverted);
            } else {
                System.out.println("???");
                return new Tree(null, null, new Value("", false), Operations.CONST, inverted);
            }
        }
    }

    Tree parse(String string) {
        this.string = string + ";";
        pos = 0;
        return parseImpl();
    }

    void compute(Tree tree) {
        falseResult = tree.compute();
        boolean both = false;
        int trues = 0;
        int falses = 0;
        if (falseResult) {
            trues++;
        } else {
            falses++;
        }
        for (int i = 1; i < Math.pow(2, values.size()); i++) {
            StringBuilder binary = new StringBuilder(Integer.toBinaryString(i));
            int diff = values.size() - binary.length();
            for (int j = 0; j < diff; j++) {
                binary.insert(0, "0");
            }
            for (int j = 0; j < values.size(); j++) {
                if (binary.charAt(j) == '0') {
                    values.get(j).setValue(false);
                } else {
                    values.get(j).setValue(true);
                }
            }
            boolean result = tree.compute();
            if (result != falseResult) {
                both = true;
            }
            if (result) {
                trues++;
            } else {
                falses++;
            }
        }
        if (both) {
            System.out.println("Satisfiable and invalid, " + trues + " true and " + falses + " false cases");
        } else if (falseResult) {
            System.out.println("Valid");
        } else {
            System.out.println("Unsatisfiable");
        }
    }


}
