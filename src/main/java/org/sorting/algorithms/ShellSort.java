package org.sorting.algorithms;

import javafx.animation.Timeline;
import javafx.scene.chart.BarChart;

import java.util.ArrayList;

public class ShellSort extends Algorithms {

    private int secondTemp;

    public ShellSort(ArrayList<Double> list, BarChart<String, Number> graph) {
        super(list, graph);
        setTempIterator(list.size()/2);
        secondTemp = getTempIterator();
    }

    public void sort(Timeline timeline) {

        setWhite();

        if (getTempIterator() < 0) {
            isSorted(timeline);
            return;
        }

        double temp = getList().get(secondTemp);
        int j;

        for (j = secondTemp; j >= getTempIterator() && getList().get(j - getTempIterator()) > temp; j -= getTempIterator()) {
            getList().set(j, getList().get(j - getTempIterator()));
            for (int i = 0; i < getList().size(); i++) {
                if (i == j || i == getList().get(j - getTempIterator())) {
                    getGraph().getData().get(0).getData().get(i).getNode().setStyle("-fx-bar-fill: red;");
                } else {
                    getGraph().getData().get(0).getData().get(i).getNode().setStyle("-fx-bar-fill: white;");
                }
            }

        }
        getList().set(j, temp);

        secondTemp++;

        if (secondTemp >= getList().size()) {
            setTempIterator(getTempIterator()/2);
            secondTemp = getTempIterator();
        }

    }

}
