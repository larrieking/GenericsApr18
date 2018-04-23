package simple;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UseAList {
  public static <E> E[] MakeArray(List<E> list, Class<E> cl) {
    E[] rv = (E[])Array.newInstance(cl, list.size()); // Right way :)
//    E[] rv = (E[])new Object[list.size()]; // "heap pollution" because things are
    // not what they think they are.
//    E[] rv = new Object[list.size()];  // "heap pollution"
//    E[] rv = new E[list.size()]; // type erasure "hides" E in the running system
     // E literally only exists at compile time.

    return rv;
  }

  public static void breakArray(Object [] oa) {
    oa[0] = LocalDate.now();
  }

  public static void breakAList(List ls) { // RAW type warning...
    ls.add(LocalDate.now());
  }

  public static void main(String[] args) {

    int [] ia = {1, 1};
    String [] sa = {null, null};

//    breakArray(sa);

    System.out.println("int array is " + ia.getClass().getName());
    System.out.println("String array is " + sa.getClass().getName());
//    List<String> ls = new ArrayList<String>();
//    List<String> ls = new ArrayList<>(); // Diamond is a nice shorthand
    List<String> l1 = new ArrayList<>(Arrays.asList("Hello", "Guten Tag"));
    List<String> ls = Collections.checkedList(l1, String.class);

    ls.add("Hello");
    ls.add("Bonjour");
    String s = ls.get(0);
    breakAList(ls);
    for (String st : ls) {
      System.out.println("> " + st);
    }

//    ls.add(LocalDate.now());
//    String s1 = (String)(ls.get(2));

//    System.out.println("item at position zero is " + ls.get(0));

  }
}
