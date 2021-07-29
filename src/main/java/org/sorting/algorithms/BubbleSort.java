package org.sorting.algorithms;

import javafx.animation.Timeline;
import javafx.scene.chart.BarChart;

import java.util.ArrayList;

public class BubbleSort extends Algorithms {

    private int secondTemp;

    public BubbleSort(ArrayList<Double> list, BarChart<String, Number> graph) {
        super(list, graph);
        setTempIterator(0);
        secondTemp = 0;
    }

    public void sort(Timeline timeline) {

        setWhite();
        isSorted(timeline);

        if (secondTemp >= getList().size() - getTempIterator() - 1) {
            setTempIterator(getTempIterator() + 1);
            secondTemp = 0;
        }

        for (int i = 0; i < getList().size(); i++) {
            if (i == secondTemp || i == secondTemp + 1) {
                getGraph().getData().get(0).getData().get(i).getNode().setStyle("-fx-bar-fill: red;");
            } else {
                getGraph().getData().get(0).getData().get(i).getNode().setStyle("-fx-bar-fill: white;");
            }
        }

        if (getList().get(secondTemp) > getList().get(secondTemp + 1)) {
            double temp = getList().get(secondTemp);
            getList().set(secondTemp, getList().get(secondTemp + 1));
            getList().set(secondTemp + 1, temp);
        }

        secondTemp++;

    }


}




