import java.util.Scanner;

public class FCFS 
{
    static void waitingTime(int proc[], int n, int burst_time[], int wait_time[]) 
    {
        wait_time[0] = 0;
        for (int i = 1; i < n; i++)
        {
            wait_time[i] = burst_time[i - 1] + wait_time[i - 1];
        }
    }

    static void turnaroundTime(int proc[], int n, int burst_time[], int wait_time[], int tat[]) 
    {
        for (int i = 0; i < n; i++)
        {
            tat[i] = burst_time[i] + wait_time[i];
        }
    }

    static void avgTime(int proc[], int n, int burst_time[]) 
    {
        int[] wait_time = new int[n];
        int[] tat = new int[n];
        int total_wt = 0, total_tat = 0;

        waitingTime(proc, n, burst_time, wait_time);
        turnaroundTime(proc, n, burst_time, wait_time, tat);

        System.out.println("Processes  Burst   Waiting Turn around");
        for (int i = 0; i < n; i++) 
        {
            total_wt += wait_time[i];
            total_tat += tat[i];
            System.out.printf(" %d\t  %d\t\t %d \t%d\n", i + 1, burst_time[i], wait_time[i], tat[i]);
        }
        System.out.printf("Average waiting time = %.2f\n", (float) total_wt / (float) n);
        System.out.printf("Average turn around time = %.2f\n", (float) total_tat / (float) n);
    }

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();
        int[] proc = new int[n];
        int[] burst_time = new int[n];

        for (int i = 0; i < n; i++) 
        {
            System.out.printf("Enter burst time for process %d: ", i + 1);
            burst_time[i] = scanner.nextInt();
            proc[i] = i + 1;
        }
        avgTime(proc, n, burst_time);
    }
}
