package base.test.jdk;

import org.junit.Test;

import java.util.*;

/**
 * Created by base on 2016/4/6.
 */
public class Collection {

    @Test
    public void testDeque() {
        //当成栈使用,后进先出
        Deque<String> deque = new ArrayDeque<String>();
        deque.push("a");
        deque.push("b");
        deque.push("c");
        deque.push("d");
        String popstr1 = deque.pop();
        String popstr2 = deque.pop();
        System.out.println("deque:"+deque+",popstr1:"+popstr1+",popstr2:"+popstr2);

        //当成队列使用,先进先出
        deque.offer("1");
        deque.offer("2");
        deque.offer("3");
        String pollstr = deque.poll(); //出队时上述栈仍有数值a,b,转化为队列时b为队列首，栈的后进优先级高
        String peekstr = deque.peek();//取队列第一个元素，不出栈
        System.out.println("deque:"+deque+",pollstr:"+pollstr+",peekstr:"+peekstr);
    }

    @Test
    public void testHashSet() {
        //此处参数值若不是2^n，则自动转化为大于参数的最小2^n，即此处会长度会自动转化为4。
        HashSet<String> hashS = new HashSet<String>(3);
        //和HashSet存储机制一样，额外增加一个链表存储元素的顺序
        LinkedHashSet<String> linkedHS = new LinkedHashSet<String>();
        //HashSet(int initialCapacity, float loadFactor)初始容量，负载系数（超过则rehash重哈希）。
        //负载系数越小，内存消耗越多，越大则性能越差。
        //HashSet<String> hashI = new HashSet<>(3,0.8);
        hashS.add("a");
        hashS.add("b");
        hashS.add("c");
        hashS.add("d");
        //不会产生越界，自动生成长度为8的另一数组，先前创建长度为4的数组便成为垃圾。
        hashS.add("e");

        linkedHS.add("1");
        linkedHS.add("2");
        linkedHS.add("3");
        linkedHS.add("4");

        //遍历HashSet
        for(String s: hashS){
            System.out.println(s);
        }
        //遍历LinkedHashSet
        for(String s: linkedHS){
            System.out.println(s);
        }
    }

    @Test
    public void testLinkedList(){
        LinkedList<String> list = new LinkedList<String>();
        list.add("1");
        list.add("2");
        list.add("4");
        list.add("3");
        list.add("4");
        list.add("5");
        System.out.println(list.get(1));
        list.removeFirstOccurrence("4");//移除第一次出现的元素
        for(String s:list){
            System.out.print(s + '.');
        }
    }
}
