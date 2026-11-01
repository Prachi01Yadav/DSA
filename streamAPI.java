import java.util.*;
import java.util.stream.Stream;
class streamAPI{
    public static void main(String[] args){
        List<Integer> ls=new ArrayList<>(List.of(5,12,7,14,18,16,3));
        Stream<Integer> stream=ls.stream();
        stream.filter(x->x>11)
        .filter(x->x%2==0)
        .map(x->x*=1).sorted()
        .forEach(x->System.out.println(x));;

        
    }
}