public class AxiomContainer {

    public static boolean isAxiom(Tree tree) {
        return isAxiomOne(tree) ||
                isAxiomTwo(tree) ||
                isAxiomThree(tree) ||
                isAxiomFour(tree) ||
                isAxiomFive(tree) ||
                isAxiomSix(tree) ||
                isAxiomSeven(tree) ||
                isAxiomEight(tree) ||
                isAxiomNine(tree) ||
                isAxiomTen(tree);
    }

    private static boolean isAxiomOne(Tree tree) {
        try {
            return tree.getOperation() == Operations.IMPL &&
                    tree.getRight().getOperation() == Operations.IMPL &&
                    tree.getLeft().equalsSigned(tree.getRight().getRight()) &&
                    tree.getHowManyInversions() == 0 &&
                    tree.getRight().getHowManyInversions() == 0;
        } catch (NullPointerException e) {
            return false;
        }

    }

    private static boolean isAxiomTwo(Tree tree) {
        try {
            return tree.getOperation() == Operations.IMPL &&
                    tree.getRight().getOperation() == Operations.IMPL &&
                    tree.getLeft().getOperation() == Operations.IMPL &&
                    tree.getRight().getLeft().getOperation() == Operations.IMPL &&
                    tree.getRight().getLeft().getRight().getOperation() == Operations.IMPL &&
                    tree.getRight().getRight().getOperation() == Operations.IMPL &&
                    tree.getLeft().getLeft().equalsSigned(tree.getRight().getLeft().getLeft()) &&
                    tree.getLeft().getLeft().equalsSigned(tree.getRight().getRight().getLeft()) &&
                    tree.getLeft().getRight().equalsSigned(tree.getRight().getLeft().getRight().getLeft()) &&
                    tree.getRight().getLeft().getRight().getRight().equalsSigned(tree.getRight().getRight().getRight()) &&
                    tree.getHowManyInversions() == 0 &&
                    tree.getLeft().getHowManyInversions() == 0 &&
                    tree.getRight().getHowManyInversions() == 0 &&
                    tree.getRight().getLeft().getHowManyInversions() == 0 &&
                    tree.getRight().getRight().getHowManyInversions() == 0 &&
                    tree.getRight().getLeft().getRight().getHowManyInversions() == 0;
        } catch (NullPointerException e) {
            return false;
        }
    }

    private static boolean isAxiomThree(Tree tree) {
        try {
            return tree.getOperation() == Operations.IMPL &&
                    tree.getRight().getOperation() == Operations.IMPL &&
                    tree.getRight().getRight().getOperation() == Operations.AND &&
                    tree.getLeft().equalsSigned(tree.getRight().getRight().getLeft()) &&
                    tree.getRight().getLeft().equalsSigned(tree.getRight().getRight().getRight()) &&
                    tree.getHowManyInversions() == 0 &&
                    tree.getRight().getHowManyInversions() == 0 &&
                    tree.getRight().getRight().getHowManyInversions() == 0;
        } catch (NullPointerException e) {
            return false;
        }
    }

    private static boolean isAxiomFour(Tree tree) {
        try {
            return tree.getOperation() == Operations.IMPL &&
                    tree.getLeft().getOperation() == Operations.AND &&
                    tree.getLeft().getLeft().equalsSigned(tree.getRight()) &&
                    tree.getHowManyInversions() == 0 &&
                    tree.getLeft().getHowManyInversions() == 0;
        } catch (NullPointerException e) {
            return false;
        }
    }

    private static boolean isAxiomFive(Tree tree) {
        try {
            return tree.getOperation() == Operations.IMPL &&
                    tree.getLeft().getOperation() == Operations.AND &&
                    tree.getLeft().getRight().equalsSigned(tree.getRight()) &&
                    tree.getHowManyInversions() == 0 &&
                    tree.getLeft().getHowManyInversions() == 0;
        } catch (NullPointerException e) {
            return false;
        }
    }

    private static boolean isAxiomSix(Tree tree) {
        try {
            return tree.getOperation() == Operations.IMPL &&
                    tree.getRight().getOperation() == Operations.OR &&
                    tree.getLeft().equalsSigned(tree.getRight().getLeft()) &&
                    tree.getHowManyInversions() == 0 &&
                    tree.getRight().getHowManyInversions() == 0;
        } catch (NullPointerException e) {
            return false;
        }
    }

    private static boolean isAxiomSeven(Tree tree) {
        try {
            return tree.getOperation() == Operations.IMPL &&
                    tree.getRight().getOperation() == Operations.OR &&
                    tree.getLeft().equalsSigned(tree.getRight().getRight()) &&
                    tree.getHowManyInversions() == 0 &&
                    tree.getRight().getHowManyInversions() == 0;
        } catch (NullPointerException e) {
            return false;
        }
    }

    private static boolean isAxiomEight(Tree tree) {
        try {
            return tree.getOperation() == Operations.IMPL &&
                    tree.getLeft().getOperation() == Operations.IMPL &&
                    tree.getRight().getOperation() == Operations.IMPL &&
                    tree.getRight().getLeft().getOperation() == Operations.IMPL &&
                    tree.getRight().getRight().getOperation() == Operations.IMPL &&
                    tree.getRight().getRight().getLeft().getOperation() == Operations.OR &&
                    tree.getLeft().getLeft().equalsSigned(tree.getRight().getRight().getLeft().getLeft()) &&
                    tree.getLeft().getRight().equalsSigned(tree.getRight().getLeft().getRight()) &&
                    tree.getLeft().getRight().equalsSigned(tree.getRight().getRight().getRight()) &&
                    tree.getRight().getLeft().getLeft().equalsSigned(tree.getRight().getRight().getLeft().getRight()) &&
                    tree.getHowManyInversions() == 0 &&
                    tree.getLeft().getHowManyInversions() == 0 &&
                    tree.getRight().getHowManyInversions() == 0 &&
                    tree.getRight().getLeft().getHowManyInversions() == 0 &&
                    tree.getRight().getRight().getHowManyInversions() == 0 &&
                    tree.getRight().getRight().getLeft().getHowManyInversions() == 0;
        } catch (NullPointerException e) {
            return false;
        }
    }

    private static boolean isAxiomNine(Tree tree) {
        try {
            return tree.getOperation() == Operations.IMPL &&
                    tree.getLeft().getOperation() == Operations.IMPL &&
                    tree.getRight().getOperation() == Operations.IMPL &&
                    tree.getRight().getLeft().getOperation() == Operations.IMPL &&
                    tree.getLeft().getLeft().equalsSigned(tree.getRight().getLeft().getLeft()) &&
                    tree.getLeft().getLeft().equals(tree.getRight().getRight()) &&
                    (tree.getLeft().getLeft().getHowManyInversions() - tree.getRight().getRight().getHowManyInversions()) == -1 &&
                    tree.getLeft().getRight().equals(tree.getRight().getLeft().getRight()) &&
                    (tree.getLeft().getRight().getHowManyInversions() - tree.getRight().getLeft().getRight().getHowManyInversions()) == -1 &&
                    tree.getHowManyInversions() == 0 &&
                    tree.getLeft().getHowManyInversions() == 0 &&
                    tree.getRight().getHowManyInversions() == 0 &&
                    tree.getRight().getLeft().getHowManyInversions() == 0;
        } catch (NullPointerException e) {
            return false;
        }
    }

    private static boolean isAxiomTen(Tree tree) {
        try {
            return tree.getOperation() == Operations.IMPL &&
                    tree.getLeft().equals(tree.getRight()) &&
                    tree.getLeft().getHowManyInversions() - tree.getRight().getHowManyInversions() == 2 &&
                    tree.getHowManyInversions() == 0;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
