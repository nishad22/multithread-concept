package forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.stream.Stream;

public class RecursiveActionExercise extends RecursiveAction {

    private List<Integer> finalList = new ArrayList<>();

    public RecursiveActionExercise(List<Integer> list) {
        this.finalList = list;
    }

    public static void main(String[] args) {
        Stream<Integer> infiniteNumbers = Stream.iterate(0, n -> n + 1);
        List<Integer> list = infiniteNumbers.limit(10).toList();
        RecursiveActionExercise recursiveActionExercise = new RecursiveActionExercise(list);
        recursiveActionExercise.compute();

    }

    @Override
    protected void compute() {

        if (finalList.size() > 2) {
            printList(finalList);
        } else {
            System.out.println("list size is less than 10: "+finalList);
        }

    }

    private void printList(List<Integer> list) {

        List<Integer> left = list.subList(0,list.size()/2);
        List<Integer> right = list.subList(list.size()/2,list.size());

        RecursiveActionExercise recursiveActionExercise1 = new RecursiveActionExercise(left);
        RecursiveActionExercise recursiveActionExercise2 = new RecursiveActionExercise(right);

        invokeAll(recursiveActionExercise1,recursiveActionExercise2);
    }
}
