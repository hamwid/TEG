//IHD

public class IHD {
  private int read1;         //input from sensor, "original output"
  private int read2;         //input from sensor, "safety output"
  private int mean;          //mean of read1 and read2
  public int outputToSensor; //output to sensor
  private Sensor sensor;     //the connected sensor

  final private int TO_SENSOR_3V = 3; //3V = "reset sensor"
  final private int TO_SENSOR_0V = 0; //0V = "listen to sensor-values"

  //Initialize IHD, connect with 1 Sensor
  //Code that runs the first cycle, or if external request of initialize()
  public void initialize(Sensor sensor_1){
    read1 = 0;
    read2 = 0;
    mean = 0;
    outputToSensor = TO_SENSOR_3V;
    sensor = sensor_1;
  }

  //Execute IHD
  //Code that runs every cycle, except if external request of initialize()
  public void execute(){
    this.listenToSensor();
    this.readFromSensor();
    this.mean();
  }

  //Put 3V on "start/restart"-pin of sensor
  public void startSensor(){
    outputToSensor = TO_SENSOR_3V;
  }

  //Put 0V on "start/restart"-pin of sensor
  public void listenToSensor(){
    outputToSensor = TO_SENSOR_0V;
  }

  //Calculate mean between two integers
  private void mean(){
    mean = (read1+read2)/2;
  }

  //Get mean of read1 and read2
  public int getMean(){
    return mean;
  }

  //Collect data from connected sensor
  //In sensor, data is stored as two integers read during the same cycle
  private void readFromSensor(){
    read1 = sensor.originalOutput;
    read2 = sensor.safetyOutput;
  }

  //Get-methods for testers
  public int getRead1(){
    return read1;
  }
  public int getRead2(){
    return read2;
  }
  //Call-methods for testers
  public int callMean(int i1, int i2) {
    return mean(i1, i2);
  }
  public void callReadFromSensor(){
    this.readFromSensor();
  }
}
