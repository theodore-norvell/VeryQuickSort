
public class Assert {
	public static void check( boolean p ) {
		if( !p ) throw new AssertionError() ;
	}

}
