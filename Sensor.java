//Sensor
import java.util.Random; //To simulate static or disturbance

public class Sensor {
  public int originalOutput  = 0; //Reads data twice; this is first read.
  public int safetyOutput    = 0; //Reads data twice; this is second read.
  public int startRestartPin = 1;//1 = undefined, 0 = send data, 3 = restart.

  //At "start" or "restart" request
  public void initialize(int input){
    this.updateStatus(input);
    if (startRestartPin == 3){
      originalOutput  = 0;
      safetyOutput    = 0;
    }
  }

  //At "regular execution"
  public void execute(int input){
    this.updateData(input);
    this.sendData();
  }

  public void updateStatus(int input){
    startRestartPin = input;
  }

  public void updateData(int input){
    originalOutput = input;
    safetyOutput   = input;

    //Random to simulate disturbance in environment or static in signal
    Random rand = new Random();
    int n = rand.nextInt(10) + 1;
    if (n>8){ //disturbance or static occured on first data-read
      originalOutput += (n-8)*5;
    }
    if (n<5){ //disturbance or static occured on second data-read
      if (n<3){
        safetyOutput += n*5;
      } else {
        safetyOutput += (n-2)*-5;
      }
    }
  }

  public int[] sendData(){
    if (startRestartPin == 0) {
      int[2] output = {originalOutput, safetyOutput};
    }
  }
}
