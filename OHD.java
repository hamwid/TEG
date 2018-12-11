//OHD

public class OHD{
  private boolean rear;                      //rear sensor > 5cm from object
  private boolean left;                      //left sensor > 30cm from object
  private boolean right;                     //right sensor > 30cm from object
  private boolean forwardL;                  //forwardL sensor > 30cm from object
  private boolean forwardR;                  //forwardR sensor > 30cm from object
  private boolean[] output = new boolean[5]; //output-data to external system
  private IAD iad1;                          //first connected IAD
  private IAD iad2;                          //second connected IAD
  private IAD iad3;                          //third connected IAD
  boolean[] dataIAD1 = new boolean[2];       //data from first IAD
  boolean[] dataIAD2 = new boolean[2];       //data from second IAD
  boolean[] dataIAD3 = new boolean[2];       //data from third IAD

  //Initialize OHD, connect with 3 IAD
  public void initialize(IAD iad_1, IAD iad_2, IAD iad_3){
    rear     = false;
    left     = false;
    right    = false;
    forwardL = false;
    forwardR = false;
    output[0] = rear;
    output[1] = left;
    output[2] = forwardL;
    output[3] = right;
    output[4] = forwardR;
    iad1 = iad_1;
    iad2 = iad_2;
    iad3 = iad_3;
  }

  //Execute OHD
  //Code that runs every cycle, except if external request of initialize()
  public void execute(){
    readDataFromAllIAD();
  }

  //Read input-data and set output-data
  private void readDataFromAllIAD(){
    //read data from IAD:s
    dataIAD1 = iad1.getData();
    dataIAD2 = iad2.getData();
    dataIAD3 = iad3.getData();
    //update booleans based on IAD data
    rear     = dataIAD1[0];
    left     = dataIAD2[0];
    forwardL = dataIAD2[1];
    right    = dataIAD3[0];
    forwardR = dataIAD3[1];
    //update output-data based on booleans
    output[0] = rear;
    output[1] = left;
    output[2] = forwardL;
    output[3] = right;
    output[4] = forwardR;
  }

  //Give output-data upon request (from external system)
  public boolean[] getOutput(){
    return output;
  }

  //Get-methods for testers
  public boolean[] getBools(){
    boolean[] bools = {rear, left, forwardL, right, forwardR};
    return bools;
  }
  public boolean[] getDataAID(){
    boolean[] data = { dataIAD1[0], dataIAD1[1], dataIAD2[0],
                       dataIAD2[1], dataIAD3[0], dataIAD3[1] };
    return data;
  }
  //Call-method for testers
  public void callReadDataFromAllIAD(){
    this.readDataFromAllIAD();
  }
}
