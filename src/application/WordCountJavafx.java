package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class WordCountJavafx extends Application {
	public static final String BLANK = "";
	GridPane grid;
	Label enterWordLabel, countLabel;
	TextField wordField, countField;
	Button countButton;

	Map<String, Integer> map = new HashMap<String, Integer>();

	@Override
	public void start(Stage stage) throws Exception {
		loadWordsMap();
		stage.setTitle("Word Count Javafx");

		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		enterWordLabel = new Label("Enter Word:");
		grid.add(enterWordLabel, 0, 0);
		wordField = new TextField();
		grid.add(wordField, 1, 0);
		countButton = new Button("Get Count");
		grid.add(countButton, 1, 1);
		countLabel = new Label("Word Count:");
		grid.add(countLabel, 0, 2);
		countField = new TextField();
		countField.setEditable(false);
		grid.add(countField, 1, 2);
		countButton.setOnAction(actionEvent -> {
			countField.setText(BLANK);
			String word = wordField.getText();
			if (BLANK.equals(word)) {
				this.alert("No Input", "Please enter a word to search", AlertType.ERROR);
				return;
			}
			map.get(word);
			int count = (map.get(word) == null) ? 0 : map.get(word);
			countField.setText(String.valueOf(count));
		});
		Scene scene = new Scene(grid, 700, 275);
		stage.setScene(scene);
		stage.show();
	}
	private void loadWordsMap() throws Exception {
		String Word;
		// url reading file
		URL poem = new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm");
		BufferedReader in;
		in = new BufferedReader(new InputStreamReader(poem.openStream()));
		// Mapping
		while ((Word = in.readLine()) != null) {
			String[] word = Word.split("[ \n\t\r.,;:!?(){}]");
			for (String val : word)
				if (map.containsKey(val) == false)
					map.put(val, 1);
				else {
					int count = (int) (map.get(val));
					map.remove(val);
					map.put(val, count + 1);
				}
		}
		// sorting list
		Set<Map.Entry<String, Integer>> set = map.entrySet();
		List<Map.Entry<String, Integer>> sortedList = new ArrayList<Map.Entry<String, Integer>>(set);
		Collections.sort(sortedList, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
				return (b.getValue()).compareTo(a.getValue());
			}
		});
		// printing the list
		for (Map.Entry<String, Integer> i : sortedList) {
			System.out.println(i.getKey() + " -> " + i.getValue());
		}
		in.close();
	}
	public void alert(String title, String message, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	public static void main(String[] args) {
		launch(args);
	}
}