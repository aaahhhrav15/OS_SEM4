package processScheduling;
import java.util.Scanner;

public class SRTFPreemptive 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the number of Processes: ");
        int n = scanner.nextInt();

        int[] a = new int[n]; 
        int[] b = new int[n]; 
        int[] x = new int[n]; 
        int[] waiting = new int[n];
        int[] turnaround = new int[n];
        int[] completion = new int[n];

        for (int i = 0; i < n; i++) 
        {
            System.out.print("\nEnter arrival time of process: ");
            a[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; i++) 
        {
            System.out.print("\nEnter burst time of process: ");
            b[i] = scanner.nextInt();
            x[i] = b[i]; 
        }

        int count = 0, time = 0, smallest;
        double avg = 0, tt = 0, end;

        for (time = 0; count != n; time++) 
        {
            smallest = Integer.MAX_VALUE; 
            for (int i = 0; i < n; i++) 
            {
                if (a[i] <= time && b[i] < smallest && b[i] > 0) 
                {
                    smallest = b[i];
                }
            }

            for (int i = 0; i < n; i++) 
            {
                if (b[i] == smallest && smallest != Integer.MAX_VALUE) 
                {
                    b[i]--;
                    if (b[i] == 0) 
                    {
                        count++;
                        end = time + 1;
                        completion[i] = (int) end;
                        waiting[i] = (int) (end - a[i] - x[i]);
                        turnaround[i] = (int) (end - a[i]);
                    }
                    break;
                }
            }
        }

        System.out.println("Process\tBurst-Time\tArrival-Time\tWaiting-Time\tTurnaround-Time\tCompletion-Time");
        for (int i = 0; i < n; i++) 
        {
            System.out.println("p" + (i + 1) + "\t\t" + x[i] + "\t\t" + a[i] + "\t\t" + waiting[i] + "\t\t" + turnaround[i] + "\t\t" + completion[i]);
            avg += waiting[i];
            tt += turnaround[i];
        }

        System.out.println("\n\nAverage waiting time =" + (avg / n));
        System.out.println("Average Turnaround time =" + (tt / n));
        scanner.close();
    }
}