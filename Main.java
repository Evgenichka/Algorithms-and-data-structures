public class Main {
    public static void main(String[] args) {
        RBTree tree = new RBTree();
        
        // Добавляем элементы в дерево
        tree.insert(8);
        tree.insert(18);
        tree.insert(5);
        tree.insert(15);
        tree.insert(17);
        tree.insert(25);
        tree.insert(40);
        tree.insert(80);
        
        // Выводим дерево для проверки
        tree.printTree();  
    }
}
