package org.gks.mergesort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class MergeSort implements Callable<List<Integer>> {
    private final List<Integer> list;
    private final ExecutorService ec;
    public MergeSort(List<Integer> list, ExecutorService ec) {
        this.list = list;
        this.ec = ec;
    }
    @Override
    public List<Integer> call() throws Exception {
        if(list.size() <= 1) {
            return list;
        }

        int mid = list.size() / 2;

        List<Integer> firstHalf = new ArrayList<>();
        for(int i = 0; i < mid; i++) {
            firstHalf.add(list.get(i));
        }

        List<Integer> secondHalf = new ArrayList<>();
        for(int i = mid; i < list.size(); i++) {
            secondHalf.add(list.get(i));
        }

        Future<List<Integer>> firstHalfFuture = ec.submit(new MergeSort(firstHalf, ec));
        Future<List<Integer>> secondHalfFuture = ec.submit(new MergeSort(secondHalf, ec));

        List<Integer> firstHalfSorted = firstHalfFuture.get();
        List<Integer> secondHalfSorted = secondHalfFuture.get();

        List<Integer> result = merge(firstHalfSorted, secondHalfSorted);
        return result;
    }

    private List<Integer> merge(List<Integer> list1, List<Integer> list2) {
        List<Integer> result = new ArrayList<>();

        int i = 0;
        int j = 0;

        while(i < list1.size() && j < list2.size()) {
            if(list1.get(i) <= list2.get(j)) {
                result.add(list1.get(i));
                ++i;
            } else {
                result.add(list2.get(j));
                ++j;
            }
        }

        while(i < list1.size()) {
            result.add(list1.get(i));
            ++i;
        }

        while(j < list2.size()) {
            result.add(list2.get(j));
            ++j;
        }

        return result;
    }
}
