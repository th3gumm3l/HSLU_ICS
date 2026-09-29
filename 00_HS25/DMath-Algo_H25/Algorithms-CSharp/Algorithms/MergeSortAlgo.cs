using System;

class MergeSortAlgo
{
    static void Main()
    {
        int[] zahlen = { 38, 27, 43, 3, 9, 82, 10 };
        Console.WriteLine("Unsortiert: " + string.Join(", ", zahlen));

        MergeSort(zahlen, 0, zahlen.Length - 1);

        Console.WriteLine("Sortiert:   " + string.Join(", ", zahlen));
    }

    static void MergeSort(int[] array, int links, int rechts)
    {
        if (links < rechts)
        {
            int mitte = (links + rechts) / 2;

            // Rekursiv die linke und rechte Hälfte sortieren
            MergeSort(array, links, mitte);
            MergeSort(array, mitte + 1, rechts);

            // Und dann zusammenführen
            Merge(array, links, mitte, rechts);
        }
    }

    static void Merge(int[] array, int links, int mitte, int rechts)
    {
        int n1 = mitte - links + 1;
        int n2 = rechts - mitte;

        // Temporäre Arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Werte kopieren
        for (int i = 0; i < n1; i++)
            L[i] = array[links + i];
        for (int j = 0; j < n2; j++)
            R[j] = array[mitte + 1 + j];

        int iIndex = 0, jIndex = 0;
        int k = links;

        // Zwei sortierte Hälften zusammenführen
        while (iIndex < n1 && jIndex < n2)
        {
            if (L[iIndex] <= R[jIndex])
            {
                array[k] = L[iIndex];
                iIndex++;
            }
            else
            {
                array[k] = R[jIndex];
                jIndex++;
            }
            k++;
        }

        // Restliche Elemente von L (falls vorhanden)
        while (iIndex < n1)
        {
            array[k] = L[iIndex];
            iIndex++;
            k++;
        }

        // Restliche Elemente von R (falls vorhanden)
        while (jIndex < n2)
        {
            array[k] = R[jIndex];
            jIndex++;
            k++;
        }
    }
}