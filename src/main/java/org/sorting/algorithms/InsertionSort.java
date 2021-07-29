package org.sorting.algorithms;

import javafx.animation.Timeline;
import javafx.scene.chart.BarChart;

import java.util.ArrayList;

public class InsertionSort extends Algorithms {

    public InsertionSort(ArrayList<Double> list, BarChart<String, Number> graph) {
        super(list, graph);
        setTempIterator(1);
    }

    public void sort(Timeline timeline) {

        setWhite();

        if (getTempIterator() >= getList().size()) {
            isSorted(timeline);
            return;
        }

        double key = getList().get(getTempIterator());
        int j = getTempIterator() - 1;

        for (int i = 0; i < getList().size(); i++) {
            if (i == j + 1 || i == key) {
                getGraph().getData().get(0).getData().get(i).getNode().setStyle("-fx-bar-fill: red;");
            } else {
                getGraph().getData().get(0).getData().get(i).getNode().setStyle("-fx-bar-fill: white;");
            }
        }

        while (j >= 0 && getList().get(j) > key) {
            getList().set(j + 1, getList().get(j));
            j = j - 1;
        }
        getList().set(j + 1, key);

        setTempIterator(getTempIterator() + 1);
    }

}
