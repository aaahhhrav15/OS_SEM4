import java.util.Scanner;

public class DiningPhilosophers 
{
    public static void main(String[] args) 
    {
        int n = 5;
        int[] forks = new int[n];
        for (int i = 0; i < n; i++) 
        {
            forks[i] = 1;
        }
        Scanner scanner = new Scanner(System.in);
        int philosopher;

        while (true) 
        {
            System.out.print("Enter the philosopher who wants to eat:\n");
            philosopher = scanner.nextInt();
            if (philosopher == -1) 
            {
                break;
            } 
            else 
            {
                System.out.printf("P%d is now thinking to eat\n", philosopher);
                if (forks[philosopher - 1] == 1) 
                {
                    System.out.printf("P%d gets fork %d\n", philosopher, philosopher);
                    forks[philosopher - 1] = 0;
                }
                if (forks[philosopher % n] == 1) 
                {
                    System.out.printf("P%d gets fork %d\n", philosopher, philosopher % n + 1);
                    forks[philosopher % n] = 0;
                }
                System.out.printf("P%d starts eating\n", philosopher);
                forks[philosopher - 1] = 1;
                forks[philosopher % n] = 1;
                System.out.printf("P%d stops eating\n", philosopher);
            }
        }
        scanner.close();
    }
}