package org.gks.tables;

public class TableCreatorClient {
    public static void main(String[] args) {
        for(int i = 1; i <= 5; i++) {
            TableCreator tableCreator = new TableCreator(i);
            Thread thread = new Thread(tableCreator);
            thread.start();
        }
    }
}
