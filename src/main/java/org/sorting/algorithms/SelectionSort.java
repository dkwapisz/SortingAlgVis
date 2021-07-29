package org.sorting.algorithms;

import javafx.animation.Timeline;
import javafx.scene.chart.BarChart;

import java.util.ArrayList;

public class SelectionSort extends Algorithms {

    public SelectionSort(ArrayList<Double> list, BarChart<String, Number> graph) {
        super(list, graph);
        setTempIterator(0);
    }

    public void sort(Timeline timeline) {

        if (getTempIterator() >= getList().size()) {
            isSorted(timeline);
            return;
        }
        setWhite();

        int min_idx = getTempIterator();

        for (int j = getTempIterator() + 1; j < getList().size(); j++) {
            if (getList().get(j) < getList().get(min_idx)) {
                min_idx = j;
            }
        }

        double temp = getList().get(min_idx);

        for (int i = 0; i < getList().size(); i++) {
            if (i == min_idx || i == getTempIterator() || i == getList().get(getTempIterator()) || i == temp) {
                getGraph().getData().get(0).getData().get(i).getNode().setStyle("-fx-bar-fill: red;");
            } else {
                getGraph().getData().get(0).getData().get(i).getNode().setStyle("-fx-bar-fill: white;");
            }
        }

        getList().set(min_idx, getList().get(getTempIterator()));
        getList().set(getTempIterator(), temp);


        setTempIterator(getTempIterator() + 1);
    }

}
