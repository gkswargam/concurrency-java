package org.gks.tables;

public class TableCreator implements Runnable {
    int num;
    public TableCreator(int n) {
        this.num = n;
    }
    @Override
    public void run() {
        printTable();
    }

    private void printTable() {
        for(int i = 1; i <= 10; i++) {
            System.out.println(num + " * " + i + " = " + num * i);
        }
    }
}
