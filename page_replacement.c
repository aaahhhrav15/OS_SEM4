#include <stdio.h>
#include <stdbool.h>

void lru(int sequence[], int n, int frames) 
{
    int pages[frames];
    bool hit[frames];
    for (int i = 0; i < frames; i++) 
    {
        pages[i] = -1;
        hit[i] = false;
    }

    int faults = 0;
    int pos = 0;

    for (int i = 0; i < n; i++) 
    {
        bool found = false;
        for (int j = 0; j < frames; j++) 
        {
            if (pages[j] == sequence[i]) 
            {
                hit[j] = true;
                found = true;
                break;
            }
            hit[j] = false;
        }

        if (!found) 
        {
            faults++;
            pages[pos] = sequence[i];
            pos = (pos + 1) % frames;
        }

        printf("%d: ", sequence[i]);
        for (int j = 0; j < frames; j++) 
        {
            if (hit[j]) 
            {
                printf("%d(H) ", pages[j]);
            } 
            else 
            {
                printf("%d ", pages[j]);
            }
        }
        printf("\n");
    }

    printf("Total Page Faults: %d\n\n", faults);
}

void fifo(int sequence[], int n, int frames) {
    int pages[frames];
    for (int i = 0; i < frames; i++) {
        pages[i] = -1;
    }

    int faults = 0;
    int pos = 0;

    for (int i = 0; i < n; i++) 
    {
        bool found = false;
        for (int j = 0; j < frames; j++) 
        {
            if (pages[j] == sequence[i]) 
            {
                found = true;
                break;
            }
        }

        if (!found) 
        {
            faults++;
            pages[pos] = sequence[i];
            pos = (pos + 1) % frames;
        }

        printf("%d: ", sequence[i]);
        for (int j = 0; j < frames; j++) 
        {
            printf("%d ", pages[j]);
        }
        printf("\n");
    }

    printf("Total Page Faults: %d\n\n", faults);
}

void optimal(int sequence[], int n, int frames) 
{
    int pages[frames];
    for (int i = 0; i < frames; i++) 
    {
        pages[i] = -1;
    }

    int faults = 0;
    int pos = 0;

    for (int i = 0; i < n; i++) 
    {
        bool found = false;
        for (int j = 0; j < frames; j++) 
        {
            if (pages[j] == sequence[i]) 
            {
                found = true;
                break;
            }
        }

        if (!found) 
        {
            faults++;
            int max_pos = -1;
            int max_val = -1;
            for (int j = 0; j < frames; j++) 
            {
                int k;
                for (k = i + 1; k < n; k++) 
                {
                    if (sequence[k] == pages[j]) 
                    {
                        break;
                    }
                }
                if (k == n) 
                {
                    max_pos = j;
                    break;
                }
                if (k > max_val) 
                {
                    max_val = k;
                    max_pos = j;
                }
            }
            pages[max_pos] = sequence[i];
        }

        printf("%d: ", sequence[i]);
        for (int j = 0; j < frames; j++) 
        {
            printf("%d ", pages[j]);
        }
        printf("\n");
    }

    printf("Total Page Faults: %d\n\n", faults);
}

int main() 
{
    int n;
    printf("Enter the length of page reference sequence: ");
    scanf("%d", &n);

    printf("Enter the page reference sequence: ");
    int sequence[n];
    for (int i = 0; i < n; i++) 
    {
        scanf("%d", &sequence[i]);
    }

    int frames;
    printf("Enter the number of frames: ");
    scanf("%d", &frames);

    printf("\nLRU Page Replacement Algorithm:\n");
    lru(sequence, n, frames);

    printf("FIFO Page Replacement Algorithm:\n");
    fifo(sequence, n, frames);

    printf("Optimal Page Replacement Algorithm:\n");
    optimal(sequence, n, frames);

    return 0;
}