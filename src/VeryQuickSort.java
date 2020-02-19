
public class VeryQuickSort {
	static final int FETCH = 0 ;
	static final int STORE = 1 ;
	static final int COMPARE = 2 ;
	static final int M = 0 ;
	static final int Q = 1 ;
	
	public static void veryQuickSort( double[] a, int[] counts ) {
		veryQuickSort1( a, 0, a.length, counts  ) ;
	}

	private static void veryQuickSort1(double[] a, int p, int r, int[] counts) {
		while( true ) {
			Assert.check( 0 <= p ) ;
			Assert.check( p <= r ) ;
			Assert.check( r <= a.length ) ;
			if( r-p < 2 ) break ;
			int[] results = new int[2] ;
			partition( a, p, r, results, counts ) ;
			int q = results[Q] ;
			int m = results[M] ;
			// Let x = a[q].
			// Region a[p,..m] is sorted and all items are less or equal to x.
			// Region a[m,..q] contains only items less or equal to x.
			// Region a[q+1,..r] contains only items greater or equal to x.
			if( m < r-1 ) {
				if( r-q-1 > q-p ) {
					veryQuickSort1( a, p, q, counts ) ;
					p = q+1 ;
				} else {
					veryQuickSort1( a, q+1, r, counts ) ;
					r = q ;
				}
			} else {
				p = r-1 ;
			}
		} 
		
	}

	private static void partition(double[] a, int p, int r, int[] results, int[] counts) {
		Assert.check( 0 <= p ) ;
		Assert.check( r-p > 1 ) ;
		Assert.check( r <= a.length ) ;
		
		// First phase. Find the first item in a[p,..r-1]
		// that is larger than its successor if there is one
		// Call its value x and its location m.
		int m = p ;
		double x = a[m] ;
		counts[FETCH] += 1 ;
		while( true ) {
			// invariant
			Assert.check( p <= m ); Assert.check( m < r );
			Assert.check( x == a[m] ) ;
			// for all i in {p,..,m} :: a[i] <= x
			if( m+1 == r ) break ;
			double y = a[m+1] ;
			counts[FETCH] += 1 ;
			counts[COMPARE] += 1 ;
			if( x > y) break ;
			m = m+1 ;
			x = y ;
			
		}
		Assert.check( m >= p ) ;
		Assert.check( x==a[m] ) ;
		Assert.check( m+1 == r || x > a[m+1] ) ;
		
		// The reqion a[p,..m] is sorted and all items in it are less than or equal to x
		results[M] = m ;
		// Use x as the pivot and partition the region a[m,..r]
		int q = r-1 ;
		outer : while(true) {
			double y ;
			while( true ) {
				if( m==q ) break outer ;
				y = a[q] ;
				counts[FETCH] += 1 ;
				counts[COMPARE] += 1 ;
				if( y < x ) break ;
				q -= 1 ;
			}
			a[m] = y ;   counts[STORE] += 1 ;
			m += 1 ;

			while( true ) {
				if( m==q ) break outer ;
				y = a[m] ;
				counts[FETCH] += 1 ;
				counts[COMPARE] += 1 ;
				if( y > x ) break ;
				m += 1 ;
			}
			
			a[q] = y ;
			counts[STORE] += 1 ;
			q = q-1 ;
		}
		a[q] = x ;
		results[Q] = q ;
	}

}
