import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button;

    @FXML
    private TextField textField;

    @FXML
    private Circle circle1, circle2, circle3, circle4, circle5, circle6, circle7, circle8, circle9, circle10, circle11, circle12, circle13, circle14, circle15;

    @FXML
    private Text text1, text2, text3, text4, text5, text6, text7, text8, text9, text10, text11, text12, text13, text14, text15;

    private Circle[] circles;

    private Text[] texts;

    private RedBlackTree<Integer> tree =  new RedBlackTree<>();

    @FXML
    void initialize() {
        circles = new Circle[]{circle1, circle2, circle3, circle4, circle5, circle6, circle7, circle8, circle9, circle10, circle11, circle12, circle13, circle14, circle15};
        texts = new Text[]{text1, text2, text3, text4, text5, text6, text7, text8, text9, text10, text11, text12, text13, text14, text15};

        button.setOnAction(event -> {
            setTree();
        });

    }

    private void setTree() {
        String string = textField.getText();
        if (string.isEmpty()) {
            cleanDisplayTree();
            return;
        }
        tree.clear();
        // Розділити рядок за пробіл
        String[] stringArray = string.split(" ");

        // Створити масив для чисел
        int[] intArray = new int[stringArray.length];

        // Перетворити рядки у числа
        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }

        for (int i : intArray) {
            tree.insert(i);
        }
        displayTree();
    }

    private void displayTree() {
        cleanDisplayTree();
        displayTreeHelper(tree.getRoot(), 0, circle1, text1);
    }

    private void displayTreeHelper(Node<Integer> node, int index, Circle circle, Text text) {
        if (node != null && node.data != null) {
            circle.setFill(node.color == 1 ? Color.RED : Color.BLACK);
            text.setText(Integer.toString(node.data));

            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;

            if (leftChildIndex < circles.length) {
                displayTreeHelper(node.left, leftChildIndex, circles[leftChildIndex], texts[leftChildIndex]);
            }

            if (rightChildIndex < circles.length) {
                displayTreeHelper(node.right, rightChildIndex, circles[rightChildIndex], texts[rightChildIndex]);
            }
        } else {
            // Обробка випадку, коли node або node.data є null
            circle.setFill(Color.BLACK);
            text.setText("null");
        }
    }


    private void cleanDisplayTree() {
        for (int i = 0; i < 15; i++) {
            circles[i].setFill(Color.WHITE);
            texts[i].setText("");
        }
    }

}
