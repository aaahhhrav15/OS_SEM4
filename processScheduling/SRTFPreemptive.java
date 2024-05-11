package processScheduling;
import java.util.Scanner;

public class SRTFPreemptive 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the number of Processes: ");
        int n = scanner.nextInt();

        int[] at = new int[n]; 
        int[] bt = new int[n]; 
        int[] x = new int[n]; 
        int[] waiting = new int[n];
        int[] turnaround = new int[n];
        int[] completion = new int[n];

        for (int i = 0; i < n; i++) 
        {
            System.out.print("\nEnter arrival time of process: ");
            at[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; i++) 
        {
            System.out.print("\nEnter burst time of process: ");
            bt[i] = scanner.nextInt();
            x[i] = bt[i]; 
        }

        int count = 0, time = 0, smallest;
        double avg = 0, tt = 0, end;
        int index = -1;

        for (time = 0; count != n; time++) 
        {
            smallest = Integer.MAX_VALUE; 
            for (int i = 0; i < n; i++) 
            {
                if (at[i] <= time && bt[i] < smallest && bt[i] > 0) 
                {
                    smallest = bt[i];
                    index=i;
                }
            }

            bt[index]--;
            if (bt[index] == 0) 
            {
                count++;
                end = time + 1;
                completion[index] = (int) end;
                turnaround[index] = (int) (end - at[index]);
                waiting[index] = (int) (turnaround[index] - x[index]);
            }

        }

        System.out.println("Process\tBurst-Time\tArrival-Time\tWaiting-Time\tTurnaround-Time\tCompletion-Time");
        for (int i = 0; i < n; i++) 
        {
            System.out.println("p" + (i + 1) + "\t\t" + x[i] + "\t\t" + at[i] + "\t\t" + waiting[i] + "\t\t" + turnaround[i] + "\t\t" + completion[i]);
            avg += waiting[i];
            tt += turnaround[i];
        }

        System.out.println("\n\nAverage waiting time =" + (avg / n));
        System.out.println("Average Turnaround time =" + (tt / n));
        scanner.close();
    }
}