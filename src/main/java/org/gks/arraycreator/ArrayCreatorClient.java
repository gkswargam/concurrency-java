package org.gks.arraycreator;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArrayCreatorClient {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ArrayCreator ac = new ArrayCreator(10);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        List<Integer> list = executorService.submit(ac).get();

        for(int i = 0; i < list.size(); i++) {
            if(i == 0) {
                System.out.print(" [" + list.get(i) + ", ");
            } else if(i == list.size() - 1) {
                System.out.print(list.get(i) + "] ");
            } else {
                System.out.print(list.get(i) + ", ");
            }
        }
    }
}
