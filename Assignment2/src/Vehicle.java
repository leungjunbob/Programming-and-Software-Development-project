/**
 * This is the vehicle class, it maintains the variables that are related to each instance. Some setter method to
 * change its private variables, and some getter function to get variables' value.
 *
 * @author Junbo Liang, junbol@student.unimelb.edu.au, 1019905.
 */
public abstract class Vehicle {
    private String vehicleType;
    private String rengId;
    private String model;
    private String colour;
    private Date entryDate;
    private Date exitDate;
    private int[] position = {1, 0};
    private int totalHour = 0;
    private int totalOvernightParking = 0;
    private int totalNumberOfHit = 0;
    private double parkingFee = 0;

    /**
     * This abstract method return symbol of vehicle according to different type of vehicle.
     * @return A symbol of vehicle, one of {"C", "B", "M", "T"}.
     */
    public abstract String getSymbol();
    // This abstract method return symbol of vehicle according to different type of vehicle.

    /**
     * This abstract will calculate the parking fee for each type of class by given the time of exit.
     * @param timeOfExit Exit datetime of Date Class
     * @return A double of parking fee.
     */
    public abstract double calculateParkingFee(Date timeOfExit);
    // This abstract will calculate the parking fee for each type of class by given the time of exit.

    /**
     * A constructor.
     * @param vehicleType Type of vehicle.
     * @param rengId Registration ID.
     * @param model Model of vehicle.
     * @param colour Colour of vehicle.
     * @param entryDate Entry Datetime.
     */
    public Vehicle(String vehicleType, String rengId, String model, String colour, Date entryDate) {
        this.vehicleType = vehicleType;
        this.rengId = rengId;
        this.model = model;
        this.colour = colour;
        this.entryDate = entryDate;
    }

    /**
     * This method increase the value of number of hit by the inputted parameter.
     * @param number Number of hits.
     */
    public void addNumberOfHit(int number) {
        this.totalNumberOfHit += number;
        // This method increase the value of number of hit by the inputted parameter.
    }

    /**
     * This method change the current position of vehicle.
     * @param position 2D array of position({x, y}).
     */
    public void setPosition(int[] position) {
        this.position = position;
        // This method change the current position of vehicle.
    }

    /**
     * This method will add the total parking fee for this vehicle.
     * @param parkingFee Parking fee.
     */
    public void setParkingFee(double parkingFee) {
        this.parkingFee = parkingFee;
        // This method will add the total parking fee for this vehicle.
    }

    /**
     * This method will add the exit date for this vehicle.
     * @param exitDate Exit datetime
     */
    public void setExitDate(Date exitDate) {
        this.exitDate = exitDate;
        // This method will add the exit date for this vehicle.
    }

    /**
     * This method calculates the total parking hour by given the exit date.
     * @param exit Exit datetime.
     */
    public void calculateTotalHour(Date exit) {
        final int minuteInHour = 60;
        final int hourInDay = 24;
        this.totalOvernightParking = exit.getDay() - entryDate.getDay();
        int hour = exit.getHour() - entryDate.getHour();
        int minute = exit.getMinute() - entryDate.getMinute();
        this.totalHour = hour + (minute % minuteInHour == 0 ? 0 : 1);
        if (this.totalHour < 0) {
            this.totalHour += hourInDay;
            this.totalOvernightParking -= 1;
            // If the hour value is in negative, need to add 24 hours and reduce one overnight parking.
        }
        // This method calculates the total parking hour by given the exit date.
    }

    /**
     * This method return the position of vehicle current location in a new int array.
     * @return A new int array({x, y}).
     */
    public int[] getPosition() {
        return new int[] {this.position[0], this.position[1]};
        // This method return the position of vehicle current location in an array form, and it stores
        // in different place in memory from private variable of position of vehicle.
    }

    /**
     * This method return the entry date.
     * @return Entry datetime.
     */
    public Date getEntryDate() {
        return this.entryDate;
        // This method return the entry date.
    }

    /**
     * This method return the parking fee of this vehicle.
     * @return Parking fee.
     */
    public double getParkingFee() {
        return parkingFee;
        // This method return the parking fee of this vehicle.
    }

    /**
     * Edit the current row of the vehicle.
     * @param sign "+": + 1(move right/down) and other: - 1(move left/up)
     */
    public void setCurrentRow(String sign) {
        if (sign.equals("+")) {
            this.position[0] += 1;
        } else {
            this.position[0] -= 1;
        }
    }
    /**
     * Edit the current column of the vehicle.
     * @param sign "+": + 1(move right/down) and other: - 1(move left/up)
     */
    public void setCurrentColumn(String sign) {
        if (sign.equals("+")) {
            this.position[1] += 1;
        } else {
            this.position[1] -= 1;
        }
    }

    /**
     * A getter to get registration ID.
     * @return registration ID.
     */
    public String getRengId() {
        return rengId;
    }

    /**
     * A getter to get Vehicle type.
     * @return Vehicle type.
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * A getter to get exit Datetime.
     * @return Exit Datetime.
     */
    public Date getExitDate() {
        return exitDate;
    }
    /**
     * A getter to get total parking hour.
     * @return Total parking hour.
     */
    public int getTotalHour() {
        return totalHour;
    }
    /**
     * A getter to get total overnight parking time.
     * @return Overnight parking time.
     */
    public int getTotalOvernightParking() {
        return totalOvernightParking;
    }

    /**
     * A getter to get total number of hit.
     * @return Total number of hit.
     */
    public int getTotalNumberOfHit() {
        return totalNumberOfHit;
    }
}