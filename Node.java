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
