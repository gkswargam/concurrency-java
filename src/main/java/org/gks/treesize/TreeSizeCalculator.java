package org.gks.treesize;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class TreeSizeCalculator implements Callable<Integer> {
    Node root;
    ExecutorService ec;

    public TreeSizeCalculator(Node root, ExecutorService ec) {
        this.root = root;
        this.ec = ec;
    }

    public Integer call() throws ExecutionException, InterruptedException {
        if(root == null) {
            return 0;
        }

        Future<Integer> leftTreeFuture = ec.submit(new TreeSizeCalculator(root.left, ec));
        Future<Integer> rightTreeFuture = ec.submit(new TreeSizeCalculator(root.right, ec));
        return 1 + leftTreeFuture.get() + rightTreeFuture.get();
    }
}
