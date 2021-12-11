import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        string = string.replaceAll("\\s", "");
        String[] theorem = string.split("\\|-");
        String[] hypotheses = theorem[0].split(",");
        Parser parser = new Parser();
        ArrayList<Tree> hypoTrees = (ArrayList<Tree>) Arrays.stream(hypotheses).map(parser::parse).collect(Collectors.toList());
        Tree lastHypo = hypoTrees.get(hypoTrees.size() - 1);
        lastHypo.setParentheses(true);
        ArrayList<Tree> trees = new ArrayList<>();
        int i = 0;
        while (scanner.hasNextLine()) {
            string = scanner.nextLine();
            string = string.replaceAll("\\s", "");
            trees.add(i, parser.parse(string));
            i++;
        }
        LinkedList<Tree> newTreesNotFinal = new LinkedList<>();
        for (Tree value : trees) {
            Tree newTree = new Tree(lastHypo, value, "", Operations.IMPL);
            for (int k = 0; k < hypoTrees.size() - 1; k++) {
                if (newTree.getRight().equalsSigned(hypoTrees.get(k))) {
                    newTree.getRight().setAxiomOrHypothesis(true);
                }
            }
            newTreesNotFinal.addLast(newTree);
        }

        LinkedList<Tree> newTreesFinal = new LinkedList<>();
        LinkedList<Tree> provedStarters = new LinkedList<>();
        for (Tree tree : newTreesNotFinal) {
            if (tree.getRight().isAxiomOrHypothesis()) {
                newTreesFinal.addLast(new Tree(tree.getRight(), tree, "", Operations.IMPL));
                newTreesFinal.addLast(tree.getRight());
                newTreesFinal.addLast(tree);
                provedStarters.addLast(tree);
            } else if (tree.getRight().equalsSigned(tree.getLeft())) {
                Tree first = new Tree(tree.getRight(), tree, "", Operations.IMPL);
                first.getRight().setParentheses(true);
                newTreesFinal.addLast(first);
                Tree formAxiom2Tree = new Tree(newTreesFinal.getLast(),
                        new Tree(
                                new Tree(lastHypo,
                                        new Tree(tree, lastHypo, "", Operations.IMPL),
                                        "", Operations.IMPL),
                                tree, "", Operations.IMPL
                        ),
                        "", Operations.IMPL);
                formAxiom2Tree.getLeft().setParentheses(true);
                formAxiom2Tree.getRight().getLeft().setParentheses(true);
                formAxiom2Tree.getRight().getRight().setParentheses(true);
                newTreesFinal.addLast(formAxiom2Tree);
                newTreesFinal.addLast(newTreesFinal.getLast().getRight());
                newTreesFinal.addLast(newTreesFinal.getLast().getLeft());
                newTreesFinal.addLast(tree);
                provedStarters.addLast(tree);
            } else {
                for (Tree ancestor : provedStarters) {
                    if (ancestor.getOperation() != Operations.CONST && tree.getRight().equalsSigned(ancestor.getRight().getRight()) && ancestor.getLeft().equalsSigned(lastHypo) && ancestor.getHowManyInversions() == 0) {
                        Tree secondAncestor = null;
                        for (Tree second : provedStarters) {
                            if (second.getOperation() != Operations.CONST && ancestor.getRight().getLeft().equalsSigned(second.getRight()) && second.getLeft().equalsSigned(lastHypo) && second.getHowManyInversions() == 0) {
                                secondAncestor = second;
                                break;
                            }
                        }
                        if (secondAncestor != null) {
                            Tree formAxiom2Tree = new Tree(
                                    secondAncestor,
                                    new Tree(
                                            ancestor,
                                            tree, "", Operations.IMPL
                                    ), "", Operations.IMPL
                            );
                            formAxiom2Tree.getLeft().setParentheses(true);
                            formAxiom2Tree.getRight().getLeft().setParentheses(true);
                            formAxiom2Tree.getRight().getRight().setParentheses(true);
                            newTreesFinal.addLast(formAxiom2Tree);
                            newTreesFinal.addLast(newTreesFinal.getLast().getRight());
                            newTreesFinal.addLast(tree);
                            provedStarters.addLast(tree);
                            break;
                        }
                    }
                }
            }
        }
        if (hypotheses.length > 1) {
            System.out.print(hypotheses[0]);
        }
        for (i = 1; i < hypotheses.length - 1; i++) {
            System.out.print("," + hypotheses[i]);
        }
        Tree theoremTree = parser.parse(theorem[1]);
        Tree toProve = new Tree(lastHypo, theoremTree, "", Operations.IMPL);
        System.out.print("|- " + toProve + "\n");
        for (Tree tree : newTreesFinal) {
            System.out.println(tree);
        }
    }
}
