package org.sorting;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Collections;

public class AppController {

    @FXML
    private BarChart<String, Number> graph;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Button getRandomsButton;

    @FXML
    private Button startButton;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private RadioButton randomRadioButton;

    @FXML
    private ToggleGroup generateGroup;

    @FXML
    private RadioButton upDownRadioButton;

    @FXML
    private Slider speedSortingSlider;

    @FXML
    private TextField listSize;

    @FXML
    private RadioButton sinRadioButton;

    ArrayList<Double> listOfNumbers = new ArrayList<>();
    int sizeOfList = 100;
    int speedOfSorting = 25;

    public void initialize() {

        typeComboBox.getItems().addAll("BubbleSort", "InsertionSort", "SelectionSort", "ShellSort", "HeapSort", "RadixSort");
        graph.lookup(".chart-plot-background").setStyle("-fx-background-color: black;");

        getRandomsButton.setOnAction(event -> {
            if (!listSize.getText().equals("")) {
                try {
                    sizeOfList = Integer.parseInt(listSize.getText());
                    if (sizeOfList <= 1) {
                        listSize.setText("");
                        listSize.setPromptText("Too small value, generated default: 100");
                        sizeOfList = 100;
                    }
                    if (sizeOfList > 1000) {
                        listSize.setText("");
                        listSize.setPromptText("Too big value, generated default: 100");
                        sizeOfList = 100;
                    }
                } catch (NumberFormatException nfe) {
                    listSize.setText("");
                    listSize.setPromptText("Invalid value, generated default: 100");
                    sizeOfList = 100;
                }
            }

            listOfNumbers.clear();
            graph.getData().clear();
            BarChart.Series<String,Number> series = new XYChart.Series<>();

            if (randomRadioButton.isSelected()) {
                if (!typeComboBox.getItems().contains("RadixSort")) {
                    typeComboBox.getItems().add("RadixSort");
                }
                for (double i = 0; i < sizeOfList; i++) {
                    listOfNumbers.add(i);
                }
                Collections.shuffle(listOfNumbers);
                for (int i = 0; i < sizeOfList; i++) {
                    series.getData().add(new BarChart.Data<>(String.valueOf(i), listOfNumbers.get(i)));
                }
            } else if (upDownRadioButton.isSelected()) {
                int listIterator = 0;
                if (!typeComboBox.getItems().contains("RadixSort")) {
                    typeComboBox.getItems().add("RadixSort");
                }
                for (double i = sizeOfList; i > 0; i--) {
                    listOfNumbers.add(i);
                    series.getData().add(new BarChart.Data<>(String.valueOf(listIterator), listOfNumbers.get(listIterator)));
                    listIterator++;
                }
            } else if (sinRadioButton.isSelected()) {
                typeComboBox.getItems().remove("RadixSort");
                for (int i = 0; i < sizeOfList; i++) {
                    listOfNumbers.add(Math.sin(i));
                }
                Collections.shuffle(listOfNumbers);
                for (int i = 0; i < sizeOfList; i++) {
                    series.getData().add(new BarChart.Data<>(String.valueOf(i), listOfNumbers.get(i)));
                }
            }
            graph.getData().add(series);
            graph.setBarGap(-5);

            setWhite();

            SortingAlgorithms.setGenerated(true);
        });

        startButton.setOnAction(event -> {

            speedOfSorting = (int) speedSortingSlider.getValue();
            speedOfSorting = 50 - speedOfSorting;

            setWhite();

            if (listOfNumbers.size() > 0 && typeComboBox.getValue() != null) {
                try {
                    SortingAlgorithms.chooseAlgorithm(listOfNumbers, graph, typeComboBox.getValue(), speedOfSorting);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setWhite() {
        for (int i = 0; i < sizeOfList; i++) {
            graph.getData().get(0).getData().get(i).getNode().setStyle("-fx-bar-fill: white;");
        }
    }

}