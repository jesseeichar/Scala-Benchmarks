import scala.util.control.Breaks._
class ScalaSimpleBreak extends Runnable {

  def run(): Unit = { 
	var i = 0;
	breakable {
		while (i < 10) {
			if(i==1) break;
			i += 1;
		}
	}
  }

}