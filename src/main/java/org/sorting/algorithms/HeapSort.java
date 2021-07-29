package org.sorting.algorithms;

import javafx.animation.Timeline;
import javafx.scene.chart.BarChart;

import java.util.ArrayList;

public class HeapSort extends Algorithms {

    private boolean swapped;
    private int secondTemp;

    public HeapSort(ArrayList<Double> list, BarChart<String, Number> graph) {
        super(list, graph);
        swapped = true;
        setTempIterator(getList().size() / 2 - 1);
        secondTemp = getList().size() - 1;
    }

    public void sort(Timeline timeline) {
        int n = getList().size();

        if (swapped && getTempIterator() < 0) {
            swapped = false;
            setTempIterator(getList().size() / 2 - 1);
        }

        if (!swapped && secondTemp <= 0) {
            swapped = true;
            secondTemp = getList().size() - 1;
        }

        if (swapped && getTempIterator() >= 0) {
            heapify(n, getTempIterator(), timeline);
            setTempIterator(getTempIterator() - 1);

            for (int k = 0; k < getList().size(); k++) {
                if (k == getTempIterator() || k == getTempIterator()*2 || k == getTempIterator()*4 || k == getTempIterator()*6 || k == getTempIterator()*8) {
                    getGraph().getData().get(0).getData().get(k).getNode().setStyle("-fx-bar-fill: blue;");
                } else {
                    getGraph().getData().get(0).getData().get(k).getNode().setStyle("-fx-bar-fill: white;");
                }
            }
        }

        if (!swapped && secondTemp > 0) {
            double temp = getList().get(0);
            getList().set(0, getList().get(secondTemp));
            getList().set(secondTemp, temp);

            for (int k = 0; k < getList().size(); k++) {
                if (k == 0 || k == getList().get(secondTemp) || k == secondTemp || k == temp) {
                    getGraph().getData().get(0).getData().get(k).getNode().setStyle("-fx-bar-fill: red;");
                } else {
                    getGraph().getData().get(0).getData().get(k).getNode().setStyle("-fx-bar-fill: white;");
                }
            }

            heapify(secondTemp, 0, timeline);
            secondTemp--;
        }
    }

     private void heapify(int n, int i, Timeline timeline) {

        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && getList().get(l) > getList().get(largest)) {
            largest = l;
        }

        if (r < n && getList().get(r) > getList().get(largest)) {
            largest = r;
        }

        if (largest != i) {
            double swap = getList().get(i);
            getList().set(i, getList().get(largest));
            getList().set(largest, swap);

            for (int k = 0; k < getList().size(); k++) {
                if (k == i || k == getList().get(largest) || k == largest || k == swap) {
                    getGraph().getData().get(0).getData().get(k).getNode().setStyle("-fx-bar-fill: red;");
                } else {
                    getGraph().getData().get(0).getData().get(k).getNode().setStyle("-fx-bar-fill: white;");
                }
            }

            heapify(n, largest, timeline);
        }

         isSorted(timeline);
    }

}
