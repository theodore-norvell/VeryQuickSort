# VeryQuickSort
A variation on quicksort that has linear time best case performance.

I wanted an algorithm that had different asymptotic time complexity for best, average, and worst case.  Since I couldn't think of any,
I designed VeryQuickSort.  It has worst case complexity of $\Theta(N^2)$, average case complexity of $\Theta(N\log N)$ and best case time
complexity of $\Theta(N)$.

The best case is on arrays that are already sorted from least to most.  Sorting an already sorted array requires $N$ fetches, $N-1$
comparisons and no stores.

Performance on arrays that are sorted the other way gives $\Theta(N^2)$ performance.
However, for at least some N, there are other arrays that have worse performance in terms
the actual number of stores, fetches, and comparisons.
For example, there are arrays of size 19 that require 368 (or more) of these operations to sort,
whereas a reverse sorted array of the same size requires only 315.

To illustrate the performance, we can execute the Main class and then run the plotVSQData.py python script in the main folder.

Aside from being useful in teaching, this algorithm might work better than conventional quick sort on data that is already mostly sorted.
For example on arrays of size 50, the average on random arrays is 772 fetches, compares, and stores. On arrays of the same size,
that are sorted but then have 6 randomly chosen pairs of elements swapped, the average number of these operations is about 377.

The implementation is licenced under the MIT version 2 licence.
