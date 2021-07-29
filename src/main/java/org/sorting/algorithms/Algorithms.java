package org.sorting.algorithms;

import javafx.animation.Timeline;
import javafx.scene.chart.BarChart;

import java.util.ArrayList;

public abstract class Algorithms {

    private final ArrayList<Double> list;
    private int tempIterator;
    private final BarChart<String, Number> graph;

    public Algorithms(ArrayList<Double> list, BarChart<String, Number> graph) {
        this.list = list;
        this.graph = graph;
    }

    public void isSorted(Timeline timeline) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i+1)) {
                return;
            }
        }
        setWhite();
        timeline.stop();
    }

    public void setWhite() {
        for (int i = 0; i < list.size(); i++) {
            graph.getData().get(0).getData().get(i).getNode().setStyle("-fx-bar-fill: white;");
        }
    }

    public ArrayList<Double> getList() {
        return list;
    }

    public BarChart<String, Number> getGraph() {
        return graph;
    }

    public int getTempIterator() {
        return tempIterator;
    }

    public void setTempIterator(int tempIterator) {
        this.tempIterator = tempIterator;
    }
}
