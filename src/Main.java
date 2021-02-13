import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        BinaryTree bt;
        Set<Integer> rt;
        int[] array;
        int searchItem;
        for (int i = 1; i <= 10; i++) {
            int countNode = 10000 * i;
            array = new int[countNode];
            for (int j = 0; j < countNode; j++) {
                array[j] = (int)(Math.random() * 90000 + 10000);
            }
            searchItem = array[(int) Math.random()* countNode];
            bt = new BinaryTree();
            final long then = System.nanoTime();
            for (int j = 0; j < countNode; j++) {
                bt.add(array[j]);
            }
            bt.containsNode(searchItem);
            final long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - then);
            System.out.println("Количество элементов в тысячах: " + (i*10));
            System.out.println("Скорость бинарного дерева: " + millis);
            rt = new HashSet<>();
            final long from = System.nanoTime();
            for (int j = 0; j < countNode; j++) {
                rt.add(array[j]);
            }
            rt.contains(searchItem);
            final long millis1 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - from);
            System.out.println("Скорость хеш-таблицы: " + millis1);

        }

    }

}
