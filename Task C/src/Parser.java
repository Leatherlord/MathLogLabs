public class Parser {
    private int pos;
    private String string;

    private Tree parseOrOperation() {
        Tree left = parseAndOperation();
        if (string.charAt(pos) == '|') {
            pos++;
            Tree right = parseOrOperation();
            return new Tree(left, right, "", Operations.OR);
        }
        return left;
    }

    private Tree parseAndOperation() {
        Tree left = parseTerm();
        if (string.charAt(pos) == '&') {
            pos++;
            Tree right = parseAndOperation();
            return new Tree(left, right, "", Operations.AND);
        }
        return left;

    }

    private Tree parseImplOperation() {
        Tree left = parseOrOperation();
        if (string.charAt(pos) == '-') {
            pos += 2;
            Tree right = parseImplOperation();
            return new Tree(left, right, "", Operations.IMPL);
        }
        return left;
    }

    private boolean condition() {
        return !(String.valueOf(string.charAt(pos)).equals("&") ||
                String.valueOf(string.charAt(pos)).equals("|") ||
                String.valueOf(string.charAt(pos)).equals("-") ||
                String.valueOf(string.charAt(pos)).equals("!") ||
                String.valueOf(string.charAt(pos)).equals(";") ||
                String.valueOf(string.charAt(pos)).equals("(") ||
                String.valueOf(string.charAt(pos)).equals(")"));
    }


    private String parseValue(String str) {
        if (condition()) {
            String s = String.valueOf(string.charAt(pos));
            pos++;
            return parseValue(str + s);
        } else {
            return str;
        }
    }

    private Tree parseTerm() {
        int inversions = 0;
        while (string.charAt(pos) == '!') {
            pos++;
            inversions++;
        }
        if (string.charAt(pos) == '(') {
            pos++;
            Tree res = parseImplOperation();
            if (string.charAt(pos) != ')') {
                System.out.println("???");
            }
            pos++;
            res.setHowManyInversions(inversions + res.getHowManyInversions());
            res.setParentheses(true);
            return res;
        } else {
            if (condition()) {
                Tree res = new Tree(null, null, parseValue(""), Operations.CONST);
                res.setHowManyInversions(inversions);
                return res;
            } else {
                System.out.println("???");
                Tree res = new Tree(null, null, "", Operations.CONST);
                res.setHowManyInversions(inversions);
                return res;
            }
        }
    }

    Tree parse(String string) {
        this.string = string + ";";
        pos = 0;
        Tree tree = parseImplOperation();
        tree.normalize();
        tree.setAxiomOrHypothesis(AxiomContainer.isAxiom(tree));
        tree.setParentheses(true);
        return tree;
    }
}
