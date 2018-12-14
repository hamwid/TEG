//IAD

public class IAD {
  private int input1;      //input from first IHD
  private int input2;      //input from second IHD
  private int IHD_limit;   //limit for IHD data seen as TRUE or FALSE
  private boolean output1; //TRUE if input1 > IHD_limit
  private boolean output2; //TRUE if input2 > IHD_limit
  private IHD ihd1;        //first connected IHD
  private IHD ihd2;        //second connected IHD

  //Initialize IAD, connect with 2 IHD - this is IAD2 or IAD3
  //Code that runs the first cycle, or if external request of initialize()
  public void initialize(IHD ihd_1, IHD ihd_2){
    ihd1 = ihd_1;
    ihd2 = ihd_2;
    input1 = 0;
    input2 = 0;
    output1 = false;
    output2 = false;
    IHD_limit = 30; //lower distance-limit to objects
  }

  //Initialize IAD, connect with 1 IHD - this is IAD1 (rear)
  //Code that runs the first cycle, or if external request of initialize()
  public void initialize(IHD ihd_1){
    ihd1 = ihd_1;
    ihd2 = ihd_1; //for simple re-use of other methods, instead of new methods
    input1 = 0;
    input2 = 0;
    output1 = false;
    output2 = false;
    IHD_limit = 5; //lower distance-limit to objects
  }

  //Execute IHD
  //Code that runs every cycle, except if external request of initialize()
  public void execute(){
    this.analyzeData();
  }

  //Analyze data from connected IHD(s) and set output-data
  private void analyzeData(){
    input1 = ihd1.getMean();
    input2 = ihd2.getMean();
    if (input1 > 200) { //If input-data is larger than 200
      input1 = 200;     //set it to 200
    }
    if (input1 < 0) {   //If input-data is smaller than 0
      input1 = 0;       //set it to 0
    }
    if (input2 > 200) { //If input-data is larger than 200
      input2 = 200;     //set it to 200
    }
    if (input2 < 0) {   //If input-data is smaller than 0
      input2 = 0;       //set it to 0
    }
    output1 = (input1 > IHD_limit); //true if INPUT > LIMIT
    output2 = (input2 > IHD_limit); //true if INPUT > LIMIT
  }

  //Give output-data upon request
  public boolean[] getData() {
    boolean[] data = new boolean[2];
    data[0] = output1;
    data[1] = output2;
    return data;
  }

  //Get-methods for testers
  public int getInput1(){
    return input1;
  }
  public int getInput2(){
    return input2;
  }
  public int getLimit(){
    return IHD_limit;
  }
  public boolean getOutput1(){
    return output1;
  }
  public boolean getOutput2(){
    return output2;
  }
  //Call-method for testers
  public void callAnalyzeData(){
    this.analyzeData();
  }
}
