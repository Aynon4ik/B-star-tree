package Semestrovka2;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        BStarTree bStarTree = new BStarTree();

        // Generate an array of 10000 random integers
        int[] array = generateRandomArray(10000);

        // Measure and record the time and operations for adding each element
        ArrayList<OperationResult> insertResults = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            int key = array[i];
            long startTime = System.nanoTime();
            bStarTree.insert(key);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            int operationCount = bStarTree.getOperationCount();
            insertResults.add(new OperationResult(key, duration, operationCount));
        }

        // Randomly select 100 elements and measure the time and operations for searching each element
        ArrayList<OperationResult> searchResults = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int index = random.nextInt(array.length);
            int key = array[index];
            long startTime = System.nanoTime();
            boolean found = bStarTree.search(key);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            int operationCount = bStarTree.getOperationCount();
            searchResults.add(new OperationResult(key, duration, operationCount, found));
        }

        // Randomly select 1000 elements and measure the time and operations for deleting each element
        ArrayList<OperationResult> deleteResults = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int index = random.nextInt(array.length);
            int key = array[index];
            long startTime = System.nanoTime();
            bStarTree.delete(key);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            int operationCount = bStarTree.getOperationCount();
            deleteResults.add(new OperationResult(key, duration, operationCount));
        }

        // Calculate the average time and operations for insertion, deletion, and search
        double averageInsertTime = calculateAverageTime(insertResults);
        double averageDeleteTime = calculateAverageTime(deleteResults);
        double averageSearchTime = calculateAverageTime(searchResults);

        double averageInsertOperations = calculateAverageOperations(insertResults);
        double averageDeleteOperations = calculateAverageOperations(deleteResults);
        double averageSearchOperations = calculateAverageOperations(searchResults);

        // Print the results
        System.out.println("Average Insertion Time: " + averageInsertTime + " nanoseconds");
        System.out.println("Average Deletion Time: " + averageDeleteTime + " nanoseconds");
        System.out.println("Average Search Time: " + averageSearchTime + " nanoseconds");
        System.out.println("Average Insertion Operations: " + averageInsertOperations);
        System.out.println("Average Deletion Operations: " + averageDeleteOperations);
        System.out.println("Average Search Operations: " + averageSearchOperations);
    }

    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }

    private static double calculateAverageTime(ArrayList<OperationResult> results) {
        long totalDuration = 0;
        for (OperationResult result : results) {
            totalDuration += result.getDuration();
        }
        return (double) totalDuration / results.size();
    }

    private static double calculateAverageOperations(ArrayList<OperationResult> results) {
        int totalOperations = 0;
        for (OperationResult result : results) {
            totalOperations += result.getOperationCount();
        }
        return (double) totalOperations / results.size();
    }
}

