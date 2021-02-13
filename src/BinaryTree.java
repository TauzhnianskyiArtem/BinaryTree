import java.util.LinkedList;
import java.util.Queue;

class TreeNode { // Класс для создания узлов
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value) {
        this.value = value;
        right = null;
        left = null;
    }
}

public class BinaryTree implements TreeInterface {
    private TreeNode root; // Корневой узел

    public BinaryTree() {}

    public BinaryTree(int ... array) { // Конструктор для перечисления элементов при создании
        for (int i = 0; i < array.length; i++) {
            this.add(array[i]);
        }
    }

    // Метод для запуска добавления элемента
    public void add(int value) {
        root = addRecursive(root, value);
    }

    // Добавление с помощью рекурсии
    private TreeNode addRecursive(TreeNode current, int value) {
        if (current == null) {
            return new TreeNode(value);
        }
        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        } else {
            return current;
        }
        return current;
    }

    // Метод для запуска поиска вхождения элемента
    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
    }

    // Поиск вхождения элементов с помощью рекурсии
    private boolean containsNodeRecursive(TreeNode current, int value) {
        if (current == null) {
            return false;
        }
        if (value == current.value) {
            return true;
        }
        return value < current.value
                ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }

    // Метод для запуска удаления элемента
    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    // Удаление элемента с помощью рекурсии
    private TreeNode deleteRecursive(TreeNode current, int value) {
        if (current == null) {
            return null;
        }
        if (value == current.value) {
            // У узла нету потомков
            if (current.left == null && current.right == null) {
                return null;
            }

            // Узел имеет один потомок
            if (current.right == null) {
                return current.left;
            }
            if (current.left == null) {
                return current.right;
            }

            // У узла два потомка
            int largestValue = findLargestValue(current.right);
            current.value = largestValue;
            current.left = deleteRecursive(current.left, largestValue);
            return current;

        }
        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }

    // Поиска минимального значения
    private int findLargestValue(TreeNode root) {
        return root.right == null ? root.value : findLargestValue(root.right);
    }

    // Метод для запуска поиска нужного элемента
    public TreeNode searchNode(int value) {
        return searchNodeRecursive(root, value);
    }

    // Поиска нужного элемента с помощью рекурсии
    private TreeNode searchNodeRecursive(TreeNode current, int value) {
        if (current == null)
            return null;

        if (current.value == value)
            return current;

        if (value < current.value)
            return searchNodeRecursive(current.left, value);
        else
            return searchNodeRecursive(current.right,value);

    }

    // Метод для запуска поиска минимального элемента
    public TreeNode searchMinimum() {
        return searchMinimumRecursive(root);
    }

    // Поиск минимального элемента с помощью рекурсии
    private TreeNode searchMinimumRecursive(TreeNode current) {
         return current.left == null? current: searchMinimumRecursive(current.left);
    }

    // Метод для запуска поиска максимального элемента
    public TreeNode searchMaximum() {
        return searchMaximumRecursive(root);
    }

    // Поиск максимального элемента с помощью рекурсии
    private TreeNode searchMaximumRecursive(TreeNode current) {
        return current.right == null? current: searchMaximumRecursive(current.right);
    }

    // Симетричный обход в глубину
    public void traverseInorder() {
        traverseInOrder(root);
    }

    private void traverseInOrder(TreeNode node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(" " + node.value);
            traverseInOrder(node.right);
        }
    }

    // Обход в ширину
    public void traverseLevelOrder() {
        if (root == null) {
            return;
        }

        // Очередь
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {

            TreeNode node = nodes.remove();

            System.out.print(" " + node.value);

            if (node.left != null) {
                nodes.add(node.left);
            }

            if (node.right!= null) {
                nodes.add(node.right);
            }
        }
    }

    // Метод для запуска поиска высоты
    public int treeHeight() {
        return maximumDepth(root);
    }

    // Поиска максимальной глубину
    private int maximumDepth(TreeNode root) {
        if(root == null)
            return 0;
        int deepLeft = maximumDepth(root.left);
        int deepRight = maximumDepth(root.right);
        return deepLeft > deepRight? deepLeft + 1: deepRight + 1;
    }
}
