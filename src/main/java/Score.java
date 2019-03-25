import java.util.LinkedList;
import java.util.Queue;

public class Score {


    private int count ;


    private Queue<Integer> fifo = new LinkedList<Integer>();


    public void setCount(int count) {
        this.count = count;
    }

    public void add_queue (int num){

        fifo.add(num);


    }




}
