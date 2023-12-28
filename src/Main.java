//11. Бібліотека функцій для роботи з червоно-чорними деревами.

public class Main {
    public static void main(String[] args) {
        System.out.println("Цілі числа");
        RedBlackTree<Integer> treeInt = new RedBlackTree<>();
        treeInt.insert(10);
        treeInt.insert(20);
        treeInt.insert(30);
        treeInt.insert(15);
        treeInt.insert(25);
        pri(treeInt, 10);

        System.out.println("\n\nРядки");
        RedBlackTree<String> treeStr = new RedBlackTree<>();
        treeStr.insert("|"      );
        treeStr.insert("123 12" );
        treeStr.insert("Student");
        treeStr.insert("Tree"   );
        treeStr.insert("Ракушка");
        pri(treeStr, "Student"  );

        System.out.println("\n\nКласи");
        RedBlackTree<TestTree> tree = new RedBlackTree<>();
        tree.insert(new TestTree(12, 45));
        tree.insert(new TestTree(87, 23));
        tree.insert(new TestTree(34, 67));
        tree.insert(new TestTree(65, 18));
        tree.insert(new TestTree(76, 32));
        tree.insert(new TestTree(21, 89));
        tree.insert(new TestTree(43, 78));
        pri(tree,   new TestTree(65, 18));
    }


    public static <T extends Comparable<T>> void pri(RedBlackTree<T> tree, T data) {
        System.out.println("Префіксний обхід червоно-чорного дерева:");
        tree.preOrderTraversal();
        System.out.println("Симетричний обхід червоно-чорного дерева:");
        tree.inOrderTraversal();

        Node<T> foundNode = tree.search(data);
        System.out.println("Знайдене значення = " + foundNode.data);

        System.out.println("Мінімум - " + tree.minimum() + "\nМаксимум - " + tree.maximum());

        tree.delete(data);
        System.out.println("\nСиметричний обхід після видалення вузла з ключем " + data + ":");
        tree.inOrderTraversal();
    }
}