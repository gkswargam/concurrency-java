package org.gks.mergesort;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MergeSortClient {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> list  = List.of(10, 9, 8 ,7, 6, 10);
        ExecutorService ec = Executors.newCachedThreadPool();
        MergeSort mergeSort = new MergeSort(list, ec);
        List<Integer> result = ec.submit(mergeSort).get();
        result.forEach(System.out::println);
        ec.shutdown();
    }
}
