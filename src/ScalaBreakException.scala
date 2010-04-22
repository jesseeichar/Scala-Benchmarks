import scala.util.control.Breaks.break

class ScalaBreakException extends Runnable {

  def run(): Unit = { 
	var i = 0;
		while (i < 10) {
			if(i==1) break;
			i += 1;
		}
  }

}