//IHD

public class IHD {
  private int read1 = 0;      //input from sensor, "original output"
  private int read2 = 0;      //input from sensor, "safety output"
  private int mean  = 0;      //mean of read1 and read2
  public int outputToSensor;  //output to sensor
  private Sensor sensor;      //

  private final int TO_SENSOR_3V = 3;
  private final int TO_SENSOR_0V = 0;

  public void initialize(){

  }

  public void execute(){

  }

  private int mean(int i1, int i2){
    mean = (i1+i2)/2;
  }

  public int getOutput(){
    return mean;
  }

  //Put 3V on "start/restart"-pin of sensor
  public void startSensor(){
    outputToSensor = TO_SENSOR_3V;
  }

  //Put 0V on "start/restart"-pin of sensor
  public void readSensor(){
    outputToSensor = TO_SENSOR_0V;
  }

  public static void getInputFromSensor(){
    this.readSensor();
    read1 = sensor.originalOutput;
    read2 = sensor.safetyOutput;
  }

}
