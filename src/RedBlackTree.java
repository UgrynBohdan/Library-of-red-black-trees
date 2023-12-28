class Node<T extends Comparable<T>> {
    T data;
    Node<T> parent;
    Node<T> left;
    Node<T> right;
    int color; // 0 для чорного, 1 для червоного

    public Node(T data) {
        this.data = data;
        this.color = 1; // новий вузол завжди червоний
    }

}

public class RedBlackTree<T extends Comparable<T>> {
    private Node<T> root;
    private final Node<T> nil;

    // Конструктори
    public RedBlackTree() {
        nil = new Node<>(null);
        nil.color = 0;
        nil.left = null;
        nil.right = null;
        root = nil;
    }

    public Node<T> search(T key) {
        return searchHelper(root, key);
    }

    private Node<T> searchHelper(Node<T> node, T key) {
        if (node == nil || node.data.compareTo(key) == 0) {
            return node;
        }

        if (node.data.compareTo(key) > 0) {
            return searchHelper(node.left, key);
        } else {
            return searchHelper(node.right, key);
        }
    }

    // Метод для визначення найбільшого значення
    public T maximum() {
        Node node = root;
        while (node.right != nil) {
            node = node.right;
        }
        return (T) node.data;
    }

    // Метод для визначення найбільшого значення
    public T minimum() {
        Node node = root;
        while (node.left != nil) {
            node = node.left;
        }
        return (T) node.data;
    }

    public Node<T> getRoot() {
        return root;
    }

    // Метод для виклику префіксного обходу з кореня
    public void preOrderTraversal() {
        System.out.print("[");
        preOrderTraversalHelper(this.root);
        System.out.println("]\n");
    }

    // Префіксний обхід дерева
    private void preOrderTraversalHelper(Node node) {
        if (node != nil) {
            System.out.print(node.data + ", ");
            preOrderTraversalHelper(node.left);
            preOrderTraversalHelper(node.right);
        }
    }

    // Метод для виклику симетричного обхіду
    public void inOrderTraversal() {
        System.out.print("[");
        inOrderTraversalHelper(root);
        System.out.println("]\n");
    }

    // Симетричний обхід дерева
    private void inOrderTraversalHelper(Node node) {
        if (node != nil) {
            inOrderTraversalHelper(node.left);
            System.out.print(node.data + ", ");
            inOrderTraversalHelper(node.right);
        }
    }


    // Публічний метод для вставки вузла в червоно-чорне дерево
    public void insert(T data) {
        Node<T> node = new Node<>(data);
        node.parent = null;
        node.data = data;
        node.left = nil;
        node.right = nil;
        node.color = 1; // червоний

        insertHelper(node);
    }

    // Метод для вставки вузла
    private void insertHelper(Node node) {
        Node parentNode = null;
        Node tempNode = this.root;

        while (tempNode != nil) {
            parentNode = tempNode;
            if (node.data.compareTo(tempNode.data) < 0) {
                tempNode = tempNode.left;
            } else {
                tempNode = tempNode.right;
            }
        }

        node.parent = parentNode;
        if (parentNode == null) {
            root = node;
        } else if (node.data.compareTo(parentNode.data) < 0) {
            parentNode.left = node;
        } else {
            parentNode.right = node;
        }

        if (node.parent == null){
            node.color = 0;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node);
    }

    // Метод для вставки вузла у червоно-чорне дерево
    private void fixInsert(Node insertedNode){
        Node uncle;
        while (insertedNode.parent.color == 1) {
            if (insertedNode.parent == insertedNode.parent.parent.right) {
                uncle = insertedNode.parent.parent.left;
                if (uncle.color == 1) {
                    uncle.color = 0;
                    insertedNode.parent.color = 0;
                    insertedNode.parent.parent.color = 1;
                    insertedNode = insertedNode.parent.parent;
                } else {
                    if (insertedNode == insertedNode.parent.left) {
                        insertedNode = insertedNode.parent;
                        rightRotate(insertedNode);
                    }
                    insertedNode.parent.color = 0;
                    insertedNode.parent.parent.color = 1;
                    leftRotate(insertedNode.parent.parent);
                }
            } else {
                uncle = insertedNode.parent.parent.right;

                if (uncle.color == 1) {
                    uncle.color = 0;
                    insertedNode.parent.color = 0;
                    insertedNode.parent.parent.color = 1;
                    insertedNode = insertedNode.parent.parent;
                } else {
                    if (insertedNode == insertedNode.parent.right) {
                        insertedNode = insertedNode.parent;
                        leftRotate(insertedNode);
                    }
                    insertedNode.parent.color = 0;
                    insertedNode.parent.parent.color = 1;
                    rightRotate(insertedNode.parent.parent);
                }
            }
            if (insertedNode == root) {
                break;
            }
        }
        root.color = 0;
    }

    // Лівий поворот вузла
    private void leftRotate(Node x){
        Node y = x.right;
        x.right = y.left;
        if (y.left != nil) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    // Правий поворот вузла
    private void rightRotate(Node x){
        Node y = x.left;
        x.left = y.right;
        if (y.right != nil) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    // Видалення

    // Публічний метод для повного видалення всіх елементів
    public void clear() {
        root = nil;
    }

    // Публічний метод для видалення вузла
    public void delete(T key) {
        deleteNodeHelper(this.root, key);
    }

    // Метод для видалення вузла
    private void deleteNodeHelper(Node node, T key) {
        Node z = nil;
        Node x, y;
        while (node != nil) {
            if (node.data.compareTo(key) == 0) {
                z = node;
            }

            if (node.data.compareTo(key) <= 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (z == nil) {
            return;
        }


        y = z;
        int yOriginalColor = y.color;
        if (z.left == nil) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == nil) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == 0){
            fixDelete(x);
        }
    }

    // Метод для визначення наступного вузла в порядку сортування
    private Node minimum(Node node) {
        while (node.left != nil) {
            node = node.left;
        }
        return node;
    }

    // Метод для заміни піддерева вузлом
    private void transplant(Node u, Node v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    // Метод для виправлення червоно-чорного дерева після видалення вузла
    private void fixDelete(Node x) {
        Node s;
        while (x != root && x.color == 0) {
            if (x == x.parent.left) {
                s = x.parent.right;
                if (s.color == 1) {
                    s.color = 0;
                    x.parent.color = 1;
                    leftRotate(x.parent);
                    s = x.parent.right;
                }

                if (s.left.color == 0 && s.right.color == 0) {
                    s.color = 1;
                    x = x.parent;
                } else {
                    if (s.right.color == 0) {
                        s.left.color = 0;
                        s.color = 1;
                        rightRotate(s);
                        s = x.parent.right;
                    }

                    s.color = x.parent.color;
                    x.parent.color = 0;
                    s.right.color = 0;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                s = x.parent.left;
                if (s.color == 1) {
                    s.color = 0;
                    x.parent.color = 1;
                    rightRotate(x.parent);
                    s = x.parent.left;
                }

                if (s.right.color == 0 && s.right.color == 0) {
                    s.color = 1;
                    x = x.parent;
                } else {
                    if (s.left.color == 0) {
                        s.right.color = 0;
                        s.color = 1;
                        leftRotate(s);
                        s = x.parent.left;
                    }

                    s.color = x.parent.color;
                    x.parent.color = 0;
                    s.left.color = 0;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = 0;
    }



}