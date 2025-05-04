// Класс представления узла дерева
    private static class Node {
        int value;
        Node left, right, parent;
        int color;
        
        Node(int val) {
            this.value = val;
            this.color = RED; // Новые узлы всегда красны!
        }
    }
// Метод вставки нового элемента в дерево
    public void insert(int value) {
        if (root == null) { // Дерево пустое?
            root = new Node(value);
            root.color = BLACK; // Корень всегда чёрный
            return;
        }
        
        Node current = root;
        while (true) {
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new Node(value); // Создаем новый узел
                    current.left.parent = current;
                    balance(current.left); // Балансируем дерево
                    break;
                } else {
                    current = current.left;
                }
            } else {
                if (current.right == null) {
                    current.right = new Node(value); // Создаем новый узел
                    current.right.parent = current;
                    balance(current.right); // Балансируем дерево
                    break;
                } else {
                    current = current.right;
                }
            }
        }
    }

    // Процедура балансировки дерева
    private void balance(Node node) {
        while ((node != root && node.parent.color == RED)) {
            Node grandparent = node.parent.parent;
            
            if (grandparent.left == node.parent) { // Родитель является левым ребенком дедушки
                Node uncle = grandparent.right;
                
                if (uncle != null && uncle.color == RED) { // Оба ребенка деда красные => меняем цвета
                    changeColor(grandparent);
                } else if (node == node.parent.right) { // Требуется малый левый поворот
                    smallLeftRotate(node.parent);
                } else { // Малый правый поворот
                    smallRightRotate(grandparent);
                }
            } else { // Аналогично для случая, когда родитель — правый ребенок
                Node uncle = grandparent.left;
                
                if (uncle != null && uncle.color == RED) { // Оба ребенка деда красные => меняем цвета
                    changeColor(grandparent);
                } else if (node == node.parent.left) { // Требуется малый правый поворот
                    smallRightRotate(node.parent);
                } else { // Малый левый поворот
                    smallLeftRotate(grandparent);
                }
            }
        }
        
        // Проверяем цвет корня
        if (root.color == RED) {
            root.color = BLACK;
        }
    }

    // Малый правый поворот
    private void smallRightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        
        if (y.right != null) {
            y.right.parent = x;
        }
        
        y.parent = x.parent;
        
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        
        y.right = x;
        x.parent = y;
    }

    // Малый левый поворот
    private void smallLeftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        
        if (y.left != null) {
            y.left.parent = x;
        }
        
        y.parent = x.parent;
        
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        
        y.left = x;
        x.parent = y;
    }

    // Меняет цвета между узлом и его детьми
    private void changeColor(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    // Функция для отображения дерева (для проверки правильности)
    public void printTree() {
        System.out.println("Красно-чёрное дерево:");
        inOrderTraversal(root, "");
    }

    // Рекурсивная процедура обхода дерева
    private void inOrderTraversal(Node node, String indent) {
        if (node != null) {
            inOrderTraversal(node.right, indent + "\t");
            System.out.print(indent + "(" + node.value + ", ");
            System.out.println((node.color == RED ? "Red)" : "Black)"));
            inOrderTraversal(node.left, indent + "\t");
        }
    }
}
