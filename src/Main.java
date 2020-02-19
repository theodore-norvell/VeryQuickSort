import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.Format;
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
		Random rand = new Random( 1234 ) ;
		int maxN = 50 ;
		for( int N = 2; N <= maxN ; ++N ) {
			
			double[] sorted = new double[N] ;
			double[] reversed = new double[N] ;
			for( int i=0 ; i<N ; ++i) sorted[i] = reversed[N-1-i] = i ;
			runAndReport(N, sorted, bestPS);
			runAndReport(N, reversed, worstPS) ;

			int trials = 100*N ;
			int sum = 0 ;
			for( int t = 0 ; t < trials ; ++t ) {
				double[] a = new double[N] ;
				for( int i=0 ; i<N ; ++i) a[i] = rand.nextDouble() ;
				sum += runAndReport(N, a, randomPS);
			}
			averagePS.format( "%d, %d, %d, %d, %d\n", N, 0, 0, 0, sum/trials ) ;
		}
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
