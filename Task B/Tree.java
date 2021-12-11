public class Tree {
    private final Tree left;
    private final Tree right;
    private final Value value;
    private final Operations operation;
    private boolean inverted;

    public Tree(Tree left, Tree right, Value value, Operations operation, boolean inverted) {
        this.left = left;
        this.right = right;
        this.value = value;
        this.operation = operation;
        this.inverted = inverted;
    }


    public String toString() {
        if (inverted) {
            switch (operation) {
                case OR:
                    return "(!" + "(|," + left.toString() + "," + right.toString() + "))";
                case AND:
                    return "(!" + "(&," + left.toString() + "," + right.toString() + "))";
                case IMPL:
                    return "(!" + "(->," + left.toString() + "," + right.toString() + "))";
                case CONST:
                    return "(!" + value.getLiteral() + ")";
                default:
                    return "????";
            }
        } else {
            switch (operation) {
                case OR:
                    return "(|," + left.toString() + "," + right.toString() + ")";
                case AND:
                    return "(&," + left.toString() + "," + right.toString() + ")";
                case IMPL:
                    return "(->," + left.toString() + "," + right.toString() + ")";
                case CONST:
                    return value.getLiteral();
                default:
                    return "????";
            }
        }
    }

    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    public boolean compute() {
        if (operation == Operations.CONST) {
            if (inverted) {
                return !value.isValue();
            }
            return value.isValue();
        } else {
            switch (operation) {
                case OR:
                    if (inverted) {
                        return !(left.compute() || right.compute());
                    }
                    return left.compute() || right.compute();
                case AND:
                    if (inverted) {
                        return !(left.compute() && right.compute());
                    }
                    return left.compute() && right.compute();
                case IMPL:
                    if (inverted) {
                        return !(!left.compute() || right.compute());
                    }
                    return !left.compute() || right.compute();
                default:
                    return false;
            }
        }
    }
}
