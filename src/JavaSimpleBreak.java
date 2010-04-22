
public class JavaSimpleBreak implements Runnable {

	@Override
	public void run() {
		int i = 0;
		while (i < 10) {
			if(i==1) break;
			i += 1;
		}
	}

}
