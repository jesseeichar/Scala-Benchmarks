

public class Bench {
	public static void main(String[] args) throws Exception {
		Runnable[] tests = new Runnable[args.length];
		
		for (int i = 0; i < tests.length; i++) {
			String className = args[i];
			Class<?> c = Bench.class.getClassLoader().loadClass(className);
			tests[i] = (Runnable) c.newInstance();
		}
		int top = 9000000;
		// warm VM
		runSuite(tests, top, top);

		top = 90000000;
//		top = 900000;
		runWithReport(tests, top, top);
		runWithReport(tests, top, 500000);
		runWithReport(tests, top, 500);

//		results = runSuite(tests, top, 500);
//
//		for (int i = 0; i < results.length; i++) {
//			long l = results[i];
//			System.out.println(tests[i].getClass().getSimpleName()+" = "+l);
//		}
		
		
	}

	private static void runWithReport(Runnable[] tests, int top, int swap) {
		long[] results = runSuite(tests, top, swap);

		System.out.println(top+" iterations.  Swapping every "+swap+" tests");
		long longest = 0;
		for (long l : results) {
			if(longest < l) longest = l;
		}
		for (int i = 0; i < results.length; i++) {
			long l = results[i];
			System.out.println(tests[i].getClass().getSimpleName()+" = "+l+" ("+((double)l/longest)+")");
		}
	}

	private static long[] runSuite(Runnable[] tests, int top, int swap) {
		long[] results = new long[tests.length];
		for (long i : results) {
			if(i!=0) throw new Error();
		}
		for(int iter=0; iter < top; iter += swap) {
			for(int test=0; test < tests.length; test++) {
				long start = System.currentTimeMillis(); 
				for(int i=0; i<swap; i++){
					try {
						tests[test].run();
					}catch(Throwable e) {
						// ignore
					}
				}
				long end = System.currentTimeMillis();
				results[test] += end - start; 
			}
		}
		return results;
	}

}
