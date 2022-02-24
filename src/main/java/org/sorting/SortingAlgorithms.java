package org.sorting;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.chart.BarChart;
import javafx.util.Duration;
import org.sorting.algorithms.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SortingAlgorithms {

    private static BubbleSort bubbleSort;
    private static InsertionSort insertionSort;
    private static SelectionSort selectionSort;
    private static ShellSort shellSort;
    private static HeapSort heapSort;
    private static RadixSort radixSort;
    private static Timeline timelineUpdate;
    private static Timeline timelineSorting;
    private static boolean generated;

    public static void chooseAlgorithm(ArrayList<Double> list, BarChart<String, Number> graph, String whichAlgorithm, int sortingSpeed) throws InterruptedException {

        if (timelineUpdate == null) {
            timelineUpdate = new Timeline(new KeyFrame(Duration.millis(15), e -> updateGraph(list, graph)));
            timelineUpdate.setCycleCount(Timeline.INDEFINITE);
            timelineUpdate.play();
        }

        if (generated) {

            generated = false;
            switch (whichAlgorithm) {
                case "BubbleSort":
                    bubbleSort = new BubbleSort(list, graph);
                    int bubbleSpeed = sortingSpeed/4;
                    timelineSorting = new Timeline(new KeyFrame(Duration.millis(bubbleSpeed), e -> bubbleSort.sort(timelineSorting)));
                    timelineSorting.setCycleCount(Timeline.INDEFINITE);
                    timelineSorting.play();
                    break;
                case "InsertionSort":
                    insertionSort = new InsertionSort(list, graph);
                    timelineSorting = new Timeline(new KeyFrame(Duration.millis(sortingSpeed*5), e -> insertionSort.sort(timelineSorting)));
                    timelineSorting.setCycleCount(Timeline.INDEFINITE);
                    timelineSorting.play();
                    break;
                case "SelectionSort":
                    selectionSort = new SelectionSort(list, graph);
                    timelineSorting = new Timeline(new KeyFrame(Duration.millis(sortingSpeed*3), e -> selectionSort.sort(timelineSorting)));
                    timelineSorting.setCycleCount(Timeline.INDEFINITE);
                    timelineSorting.play();
                    break;
                case "ShellSort":
                    shellSort = new ShellSort(list, graph);
                    timelineSorting = new Timeline(new KeyFrame(Duration.millis(sortingSpeed), e -> shellSort.sort(timelineSorting)));
                    timelineSorting.setCycleCount(Timeline.INDEFINITE);
                    timelineSorting.play();
                    break;
                case "HeapSort":
                    heapSort = new HeapSort(list, graph);
                    timelineSorting = new Timeline(new KeyFrame(Duration.millis(sortingSpeed*2), e -> heapSort.sort(timelineSorting)));
                    timelineSorting.setCycleCount(Timeline.INDEFINITE);
                    timelineSorting.play();
                    break;
                case "RadixSort":
                    radixSort = new RadixSort(list, graph);
                    timelineSorting = new Timeline(new KeyFrame(Duration.millis(sortingSpeed), e -> radixSort.sort(timelineSorting)));
                    timelineSorting.setCycleCount(Timeline.INDEFINITE);
                    timelineSorting.play();
                    break;
                default:
                    break;
            }
        }

    }

    private static void updateGraph(ArrayList<Double> list, BarChart<String, Number> graph) {

        Platform.runLater(() -> {
            BarChart.Series<String, Number> series = graph.getData().get(0);
            graph.getData().remove(series);
            series.getData().clear();
            for (Double number : list) {
                series.getData().add(new BarChart.Data<>(String.valueOf(list.indexOf(number)), number));
            }
            graph.getData().add(series);
            graph.setBarGap(-5);
//            if (isSorted(list)) {
//                for (int i = 0; i < list.size(); i++) {
//                    graph.getData().get(0).getData().get(i).getNode().setStyle("-fx-bar-fill: black;");
//                }
//            }
            for (int i = 0; i < list.size(); i++) {
                graph.getData().get(0).getData().get(i).getNode().setStyle("-fx-bar-fill: white;");
            }
        });


    }

    public static void setGenerated(boolean generated) {
        SortingAlgorithms.generated = generated;
    }

    private static boolean isSorted(ArrayList<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i+1)) {
                return false;
            }
        }
        return true;
    }
}