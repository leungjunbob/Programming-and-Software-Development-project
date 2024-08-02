/**
 * This is the bike class, it maintains the variables that is related to this particular bike. Some setter functions to
 * change its private variables, and some getter function to get variables' value.
 * @author Junbo Liang, junbol@student.unimelb.edu.au, 1019905.
 *
 */
public class Car {
    private String rengId;
    private String model;
    private String colour;
    private int hour = -1;
    private int minute = -1;
    private boolean parking = false;
    private int totalHour = 0;
    private int totalNumberOfHit = 0;
    private float totalParkingFee = 0;
    private int currentRow = 1;
    private int currentColumn = 0;
    // Initialized the class variables.

    public Car(String rengId, String model, String colour, int hour, int minute, boolean parking) {
        this.rengId = rengId;
        this.model = model;
        this.colour = colour;
        this.hour = hour;
        this.minute = minute;
        this.parking = parking;
    }

    public Car() {
    }

    public void addNumberOfHit(int num){
        this.totalNumberOfHit += num;
    }

    public void setCurrentRow(String sign) {
        if (sign.equals("+")){
            this.currentRow += 1;
        } else{
            this.currentRow -= 1;
        }
    }

    public void setCurrentColumn(String sign) {
        if (sign.equals("+")){
            this.currentColumn += 1;
        } else{
            this.currentColumn -= 1;
        }
    }
    // Edit the current row or current column of the car,
    // pass in the sign related to + 1(move right/down) and - 1(move left/up)

    public void setCurrentPosition(int[] position) {
        this.currentRow = position[0];
        this.currentColumn = position[1];
        // If car did an invalid movement, use this function return it to the position before.
    }

    public int[] getCurrentPosition(){
        return new int[] {this.currentRow, this.currentColumn};
    }

    public float getTotalParkingFee() {
        this.totalParkingFee = totalHour * 4 + totalNumberOfHit * 20;
        return this.totalParkingFee;
    }

    public String getRengId() {
        return rengId;
    }

    public void setTotalHour(int[] time) {
        this.totalHour = time[0] - this.hour + ((time[1] - this.minute >0) ? 1 : 0);
        // Calculate the total parking hour, when checkout minute - checkin minute > 0 add 1 to total hour, otherwise 0.
    }

    public int getTotalHour() {
        return totalHour;
    }

    public int getTotalNumberOfHit() {
        return totalNumberOfHit;
    }

    public boolean isParking() {
        return parking;
        // return boolean value determine whether bike checked in or not.
    }

    public int getHour() {
        return this.hour;
    }

}
