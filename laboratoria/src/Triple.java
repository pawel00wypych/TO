import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.System.exit;

public class Triple<F extends Comparable<F>, S extends Comparable<S>, T extends Comparable<T>> implements Comparable<Triple<F, S, T>>
{

        private F first;
        private S second;
        private T third;
        Triple(F first, S second, T third){
            this.first = first;
            this.second = second;
            this.third = third;
        }
        @Override
        public String toString() {
            return "Class name:" + getClass().getName() + " First:" + getFirst() + " " +
                    first.getClass()
                    + ", Second:" + getSecond() + " " + second.getClass()
                    + ", Third:" + getThird() + " " + third.getClass() + "\n";
        }

        public F getFirst() {
            return first;
        }
        public S getSecond() {
            return second;
        }
        public T getThird() {
            return third;
        }

        public boolean isHomogeneous(){
            return first.getClass().equals(second.getClass()) &&
                    third.getClass().equals(second.getClass());
        }


    @Override
        public boolean equals(Object obj)
        {
          if (obj instanceof Triple)
          {
              Triple otherTriple = (Triple) obj;
              return first.equals(otherTriple.first) &&
                    second.equals(otherTriple.second) &&
                    third == otherTriple.third;
          }
            return false;
        }


    @Override
    public int compareTo(Triple<F, S, T> o) {
            try {
                if(!(o instanceof Triple))
                {
                    throw new IllegalArgumentException("Class must be of type Triple and can,t be null");
                }
                if(!first.getClass().equals(o.first.getClass()))
                {
                    throw new IllegalArgumentException("objects must be of the same type");
                }
                if(!second.getClass().equals(o.second.getClass()))
                {
                    throw new IllegalArgumentException("objects must be of the same type");
                }
                if(!third.getClass().equals(o.third.getClass()))
                {
                    throw new IllegalArgumentException("objects must be of the same type");
                }


                int res1 = (first.compareTo(o.first));
                int res2 = (second.compareTo(o.second));
                int res3 = (third.compareTo(o.third));


                if (res1 != 0) {
                    return res1;
                }
                if (res2 != 0) {
                    return res2;
                }
                if (res3 != 0) {
                    return res3;
                }

                return 0;
            }
            catch (IllegalArgumentException e)
            {
                System.err.println("The following exception was thrown:");
                e.printStackTrace();
                return 0;
            }

    }



    public static void main(String[] args) {
        Triple<Integer, Integer, Integer> a = new Triple<>(11,5 ,48);
        Triple<Integer, Integer, Integer> b = new Triple<>(10,5 ,45);
        Triple<Integer, Integer, Integer> c = new Triple<>(10,2 ,30);
        Triple<String, Integer, String> d = new Triple<>("a",22 ,"df");

        List<Triple> correctTypes = new ArrayList<>();
        List<Triple> incorrectTypes = new ArrayList<>();

        correctTypes.add(a);
        correctTypes.add(b);
        correctTypes.add(c);

        System.out.println("list of correct types a,b,c:\n");

        for (Triple item : correctTypes)
        {
            System.out.println(item);
            
        }
        correctTypes.sort(null);
        System.out.println("list of correct types a,b,c sorted:\n");
        for (Triple item : correctTypes)
        {
            System.out.println(item);
        }


        incorrectTypes.add(b);
        incorrectTypes.add(c);
        incorrectTypes.add(d);

        System.out.println("list of incorrect types b,c,d:\n");

        for (Triple item : incorrectTypes)
        {
            System.out.println(item);

        }
        incorrectTypes.sort(null);
        System.out.println("list of incorrect types b,c,d sorted:\n");
        for (Triple item : incorrectTypes)
        {
            System.out.println(item);
        }

    }
}