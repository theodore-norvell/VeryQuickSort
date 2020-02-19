import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

class TestVQS {

	@Test
	void test0() {
		double[] a = new double[0] ;
		int[] counts = new int[3] ;
		VeryQuickSort.veryQuickSort(a, counts);
	}

	@Test
	void test1() {
		double[] a = new double[1] ;
		a[0] = 3.145 ;
		int[] counts = new int[3] ;
		VeryQuickSort.veryQuickSort(a, counts);
		assertTrue( a[0] == 3.145 ); 
	}

	@Test
	void test2a() {
		double[] a = new double[2] ;
		a[0] = 3.145 ;
		a[1] = 3.145 ;
		int[] counts = new int[3] ;
		VeryQuickSort.veryQuickSort(a, counts);
		assertTrue( a[0] == 3.145 ); 
		assertTrue( a[1] == 3.145 ); 
	}

	@Test
	void test2b() {
		double[] a = new double[2] ;
		a[0] = 3.145 ;
		a[1] = 42.0 ;
		int[] counts = new int[3] ;
		VeryQuickSort.veryQuickSort(a, counts);
		assertTrue( a[0] == 3.145 ); 
		assertTrue( a[1] == 42.0 ); 
	}

	@Test
	void test2c() {
		double[] a = new double[2] ;
		a[0] = 2 ;
		a[1] = 1 ;
		int[] counts = new int[3] ;
		VeryQuickSort.veryQuickSort(a, counts);
		assertTrue( a[0] == 1 ); 
		assertTrue( a[1] == 2 ); 
	}

	@Test
	void test3() {
		double[] a = new double[3] ;
		int[] counts = new int[3] ;
		
		a[0] = 1 ;
		a[1] = 1 ;
		a[2] = 1 ;
		VeryQuickSort.veryQuickSort(a, counts);
		assertTrue( a[0] == 1 ); 
		assertTrue( a[1] == 1 ); 
		assertTrue( a[2] == 1 ); 
		
		a[0] = 1 ;
		a[1] = 1 ;
		a[2] = 2 ;
		VeryQuickSort.veryQuickSort(a, counts);
		assertTrue( a[0] == 1 ); 
		assertTrue( a[1] == 1 ); 
		assertTrue( a[2] == 2 ); 
		
		a[0] = 1 ;
		a[1] = 2 ;
		a[2] = 1 ;
		VeryQuickSort.veryQuickSort(a, counts);
		assertTrue( a[0] == 1 ); 
		assertTrue( a[1] == 1 ); 
		assertTrue( a[2] == 2 ); 
		
		a[0] = 2 ;
		a[1] = 1 ;
		a[2] = 1 ;
		VeryQuickSort.veryQuickSort(a, counts);
		assertTrue( a[0] == 1 ); 
		assertTrue( a[1] == 1 ); 
		assertTrue( a[2] == 2 ); 
		
		a[0] = 1 ;
		a[1] = 2 ;
		a[2] = 3 ;
		VeryQuickSort.veryQuickSort(a, counts);
		assertTrue( a[0] == 1 ); 
		assertTrue( a[1] == 2 ); 
		assertTrue( a[2] == 3 ); 
		
		a[0] = 1 ;
		a[1] = 3 ;
		a[2] = 2 ;
		VeryQuickSort.veryQuickSort(a, counts);
		assertTrue( a[0] == 1 ); 
		assertTrue( a[1] == 2 ); 
		assertTrue( a[2] == 3 ); 
		
		a[0] = 2 ;
		a[1] = 1 ;
		a[2] = 3 ;
		VeryQuickSort.veryQuickSort(a, counts);
		assertTrue( a[0] == 1 ); 
		assertTrue( a[1] == 2 ); 
		assertTrue( a[2] == 3 ); 
		
		a[0] = 2 ;
		a[1] = 3 ;
		a[2] = 1 ;
		VeryQuickSort.veryQuickSort(a, counts);
		assertTrue( a[0] == 1 ); 
		assertTrue( a[1] == 2 ); 
		assertTrue( a[2] == 3 ); 
		
		a[0] = 3 ;
		a[1] = 1 ;
		a[2] = 2 ;
		VeryQuickSort.veryQuickSort(a, counts);
		assertTrue( a[0] == 1 ); 
		assertTrue( a[1] == 2 ); 
		assertTrue( a[2] == 3 ); 
		
		a[0] = 3 ;
		a[1] = 2 ;
		a[2] = 1 ;
		VeryQuickSort.veryQuickSort(a, counts);
		assertTrue( a[0] == 1 ); 
		assertTrue( a[1] == 2 ); 
		assertTrue( a[2] == 3 ); 
	}
	

	@Test
	void testBig() {
		int BIG = 1000 ;
		Random rand = new Random( 1234 ) ;
		for( int N = 0 ; N < BIG ; ++N ) {
			double[] a = new double[N] ;
			for( int i=0 ; i<N ; ++i) a[i] = rand.nextDouble() ;
			double[] b = a.clone() ;
			
			int[] counts = new int[3] ;
			VeryQuickSort.veryQuickSort(a, counts);
			
			for( int i = 1 ; i < N ; ++i ) 
				assertTrue( a[i-1] <= a[i]) ;
			
			for( int i = 0 ; i < N ; ++i ) {
				assertEquals( count( a[i], b), count( a[i], a) ) ;
			}
		}
	}
	
	private int count( double x, double[] a) {
		int c = 0 ;
		for( int i=0 ; i < a.length ; ++i)
			if( a[i]==x ) ++c ;
		return c ;
	}
	

}
