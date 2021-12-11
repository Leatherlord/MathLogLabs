public class Tree {
    private final String value;
    private final Operations operation;
    private Tree left;
    private Tree right;
    private boolean axiomOrHypothesis = false;
    private int howManyInversions = 0;
    private boolean parentheses = false;

    public Tree(Tree left, Tree right, String value, Operations operation) {
        this.left = left;
        this.right = right;
        this.value = value;
        this.operation = operation;
    }

    public void setParentheses(boolean parentheses) {
        this.parentheses = parentheses;
    }

    public int getHowManyInversions() {
        return howManyInversions;
    }

    public void setHowManyInversions(int howManyInversions) {
        this.howManyInversions = howManyInversions;
    }

    public boolean isAxiomOrHypothesis() {
        return axiomOrHypothesis;
    }

    public void setAxiomOrHypothesis(boolean axiomOrHypothesis) {
        this.axiomOrHypothesis = axiomOrHypothesis;
    }

    public Tree getLeft() {
        return left;
    }

    public Tree getRight() {
        return right;
    }

    public Operations getOperation() {
        return operation;
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("!".repeat(Math.max(0, howManyInversions)));
        if (operation != Operations.CONST) {
            string.append("(");
        }
        switch (operation) {
            case OR -> string.append(left.toString()).append(" | ").append(right.toString());
            case AND -> string.append(left.toString()).append(" & ").append(right.toString());
            case IMPL -> string.append(left.toString()).append(" -> ").append(right.toString());
            case CONST -> string.append(value);
            default -> string.append("????");
        }
        if (operation != Operations.CONST) {
            string.append(")");
        }
        return string.toString();
    }

    public boolean equalsSigned(Tree tree) {
        return this.equals(tree) &&
                howManyInversions == tree.getHowManyInversions();
    }


    public boolean equals(Tree tree) {
        if (tree == null) return false;
        if (operation != tree.getOperation()) return false;
        if (operation == Operations.CONST) {
            return value.equals(tree.value);
        }
        return left.equalsSigned(tree.left) && right.equalsSigned(tree.right);
    }

    public void normalize() {
        if ((operation == Operations.AND || operation == Operations.OR) && operation == right.operation && !right.parentheses) {
            this.left = new Tree(this.left, right.left, "", operation);
            this.right = right.right;
            normalize();
        }
        if (operation == Operations.CONST) return;
        left.normalize();
        right.normalize();
    }
}
