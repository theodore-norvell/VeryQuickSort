//Copyright (c) 2020 Theodore S. Norvell
//
//Permission is hereby granted, free of charge, to any person obtaining a copy
//of this software and associated documentation files (the "Software"), to deal
//in the Software without restriction, including without limitation the rights
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in all
//copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//SOFTWARE.

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {

		File worstFile = new File("./reversed.csv") ;
		PrintStream worstPS = new PrintStream( worstFile ) ;
		File bestFile = new File("./sorted.csv") ;
		PrintStream bestPS = new PrintStream( bestFile ) ;
		File averageFile = new File("./average.csv") ;
		PrintStream averagePS = new PrintStream( averageFile ) ;
		File randomFile = new File("./random.csv") ;
		PrintStream randomPS = new PrintStream( randomFile ) ;
		File almostFile = new File("./almost.csv") ;
		PrintStream almostPS = new PrintStream( almostFile ) ;
		File almostAverageFile = new File("./almostAverage.csv") ;
		PrintStream almostAveragePS = new PrintStream( almostAverageFile ) ;
		Random rand = new Random( 1234 ) ;
		int maxN = 50 ;
		for( int N = 2; N <= maxN ; ++N ) {
			
			double[] sorted = new double[N] ;
			double[] reversed = new double[N] ;
			for( int i=0 ; i<N ; ++i) sorted[i] = reversed[N-1-i] = i ;
			runAndReport(N, sorted, bestPS);
			runAndReport(N, reversed, worstPS) ;
			
			// Try it on many random arrays.
			int trials = 100*N ;
			int sum = 0 ;
			for( int t = 0 ; t < trials ; ++t ) {
				double[] a = new double[N] ;
				for( int i=0 ; i<N ; ++i) a[i] = rand.nextDouble() ;
				sum += runAndReport(N, a, randomPS);
			}
			averagePS.format( "%d, %d, %d, %d, %d\n", N, 0, 0, 0, sum/trials ) ;
			


			// Try it on mostly sorted data.  We'll start with a sorted array an
			// make about N/10 random swaps.
			// Try it on many random arrays.
			trials = 10*N ;
			sum = 0 ;
			for( int t = 0 ; t < trials ; ++t ) {
				for( int i = 0 ; i < N ; i += 10 ) {
					int p = rand.nextInt(N) ;
					int q = rand.nextInt(N) ;
					double tmp = sorted[p] ; sorted[p] = sorted[q] ; sorted[q] = tmp ;
				}
				sum += runAndReport(N, sorted, almostPS);
			}
			almostAveragePS.format( "%d, %d, %d, %d, %d\n", N, 0, 0, 0, sum/trials ) ;
		}
		worstPS.close() ; 
		bestPS.close() ;
		averagePS.close() ;
		randomPS.close() ;
		almostPS.close() ;
		almostAveragePS.close() ;
	}

	private static int runAndReport(int N, double[] a, PrintStream out) {
		int[] counts = new int[3] ;
		VeryQuickSort.veryQuickSort(a, counts);
		int fetches = counts[VeryQuickSort.FETCH] ;
		int stores = counts[VeryQuickSort.STORE] ;
		int compares = counts[VeryQuickSort.COMPARE] ;
		int total = fetches + stores + compares ;
		out.format( "%d, %d, %d, %d, %d\n", N, fetches, stores, compares, total ) ;
		return total ;
	}

}
