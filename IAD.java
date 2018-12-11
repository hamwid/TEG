//IAD

public class IAD {
  private int input1;      //input from first IHD
  private int input2;      //input from second IHD
  private int IHD_limit;   //limit for IHD data seen as TRUE or FALSE
  private boolean output1; //TRUE if input1 > IHD_limit
  private boolean output2; //TRUE if input2 > IHD_limit
  private IHD ihd1;        //first connected IHD
  private IHD ihd2;        //second connected IHD

  //Initialize IAD, connect with 2 IHD
  public void initialize(IHD ihd_1, IHD ihd_2){
    ihd1 = ihd_1;
    ihd2 = ihd_2;
    input1 = 0;
    input2 = 0;
    output1 = false;
    output2 = false;
    IHD_limit = 30;
  }

  //Initialize IAD, connect with 1 IHD
  public void initialize(IHD ihd_1){
    ihd1 = ihd_1;
    ihd2 = ihd_1;
    input1 = 0;
    input2 = 0;
    output1 = false;
    output2 = false;
    IHD_limit = 5;
  }

  //Execute IHD
  public void execute(){
    this.analyzeData();
  }

  //Analyze data from connected IHD(s) and set output-data
  private void analyzeData(){
    input1 = ihd1.getMean();
    input2 = ihd2.getMean();
    output1 = (input1 > IHD_limit);
    output2 = (input2 > IHD_limit);
  }

  //Give output-data
  public boolean[] getData() {
    boolean[] data = new boolean[2];
    data[0] = output1;
    data[1] = output2;
    return data;
  }
}
