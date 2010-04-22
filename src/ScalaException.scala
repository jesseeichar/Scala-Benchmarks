class ScalaException extends Runnable {

  def run(): Unit = { 
	var i = 0;
		while (i < 10) {
			if(i==1) throw new Exception();
			i += 1;
		}
  }

}