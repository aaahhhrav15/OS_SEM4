import java.util.Scanner;

public class SJF 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = scanner.nextInt();

        int[] burstTime = new int[n];
        int[] process = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];

        System.out.println("\nEnter Burst Time:");
        for (int i = 0; i < n; i++) 
        {
            System.out.printf("P%d: ", i + 1);
            burstTime[i] = scanner.nextInt();
            process[i] = i + 1;
        }

        for (int i = 0; i < n; i++) 
        {
            int pos = i;
            for (int j = i + 1; j < n; j++) 
            {
                if (burstTime[j] < burstTime[pos])
                {
                    pos = j;
                }
            }
            int temp = burstTime[i];
            burstTime[i] = burstTime[pos];
            burstTime[pos] = temp;
            temp = process[i];
            process[i] = process[pos];
            process[pos] = temp;
        }

        waitingTime[0] = 0;
        int total = 0;
        for (int i = 1; i < n; i++) 
        {
            waitingTime[i] = 0;
            for (int j = 0; j < i; j++)
            {
                waitingTime[i] += burstTime[j];
            }
            total += waitingTime[i];
        }

        for (int i = 0; i < n; i++) 
        {
            turnaroundTime[i] = burstTime[i] + waitingTime[i];
            total += turnaroundTime[i];
        }

        float avgWaitingTime = (float) total / n;
        float avgTurnaroundTime = (float) total / n;

        System.out.println("\nProcess\tBurst Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) 
        {
            System.out.printf("P%d\t\t%d\t\t%d\t\t%d\n", process[i], burstTime[i], waitingTime[i], turnaroundTime[i]);
        }

        System.out.printf("\nAverage Waiting Time = %.2f\n", avgWaitingTime);
        System.out.printf("Average Turnaround Time = %.2f\n", avgTurnaroundTime);

        scanner.close();
    }
}
