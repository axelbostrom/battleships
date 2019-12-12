package battleships;
  
public class rot{
    static long rotenUr(int n)
    {
        int x = n;
        int y = 1;
        while (x > y) {
            x = (x + y) / 2;
            y = n / x;
        }
        return (long)x;
    }
    static public void main(String[] args)
    {
        int n = 23;
        System.out.println("Kvadratroten av " + n + " är " 
+ rotenUr(n));
    }
}
