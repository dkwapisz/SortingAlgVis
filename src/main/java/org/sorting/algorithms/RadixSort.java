package org.sorting.algorithms;

import javafx.animation.Timeline;
import javafx.scene.chart.BarChart;

import java.util.ArrayList;
import java.util.Arrays;

public class RadixSort extends Algorithms {

    private final double maximum;
    private int whichLoop;
    private int iterator1;
    private int iterator2;
    private int iterator3;
    private int iterator4;
    private double[] output;
    private double[] count;

    public RadixSort(ArrayList<Double> list, BarChart<String, Number> graph) {
        super(list, graph);
        setTempIterator(1); // iterator0
        maximum = getMax(getList().size());
        whichLoop = 1; // switching between different loops
        iterator1 = 0; // loop 1 iterator
        iterator2 = 1; // loop 2 iterator
        iterator3 = getList().size() - 1; // loop 3 iterator
        iterator4 = 0; // loop 4 iterator
        output = new double[getList().size()];
        count = new double[10]; // decimal's number sorting
        Arrays.fill(count, 0);
    }

    private double getMax(int n) {
        double mx = getList().get(0);
        for (int i = 1; i < n; i++) {
            if (getList().get(i) > mx) {
                mx = getList().get(i);
            }
        }
        return mx;
    }

    private void countSort(int n, int exp, Timeline timeline) {

        isSorted(timeline);

        if (iterator1 >= n) {
            iterator1 = 0;
            whichLoop = 2;
        }
        if (iterator2 >= 10) {
            iterator2 = 1;
            whichLoop = 3;
        }
        if (iterator3 < 0) {
            iterator3 = n - 1;
            whichLoop = 4;
        }
        if (iterator4 >= n) {
            iterator4 = 0;
            whichLoop = 1;
            output = new double[getList().size()];
            count = new double[10]; // decimal's number sorting
            Arrays.fill(count, 0);
            setTempIterator(getTempIterator() * 10);
            return;
        }

        switch (whichLoop) {
            case 1:
                count[(int) ((getList().get(iterator1) / exp) % 10)]++;
                iterator1++;
                paintMarkers(iterator1, true);
                break;
            case 2:
                count[iterator2] += count[iterator2 - 1];
                iterator2++;
                break;
            case 3:
                output[(int) (count[(int) ((getList().get(iterator3) / exp) % 10)] - 1)] = getList().get(iterator3);
                count[(int) ((getList().get(iterator3) / exp) % 10)]--;
                iterator3--;
                paintMarkers(iterator3, true);
                break;
            case 4:
                getList().set(iterator4, output[iterator4]);
                iterator4++;
                paintMarkers(iterator4, false);
                break;
            default:
                break;
        }

    }

    private void paintMarkers(int iterator, boolean scanning) {
        for (int k = 0; k < getList().size(); k++) {
            if (k == iterator && scanning) {
                getGraph().getData().get(0).getData().get(k).getNode().setStyle("-fx-bar-fill: blue;");
            } else if (k == iterator) {
                getGraph().getData().get(0).getData().get(k).getNode().setStyle("-fx-bar-fill: red;");
            } else {
                getGraph().getData().get(0).getData().get(k).getNode().setStyle("-fx-bar-fill: white;");
            }
        }
    }

    public void sort(Timeline timeline) {

        if (maximum / getTempIterator() <= 0) {
            isSorted(timeline);
        }

        countSort(getList().size(), getTempIterator(), timeline);

    }

}
