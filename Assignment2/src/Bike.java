/**
 * This is the bike class extends from vehicle, it has the same private variable as described in vehicle class and
 * method. It also has the unique method of return symbol and return parking fee.
 *
 * @author Junbo Liang, junbol@student.unimelb.edu.au, 1019905.
 */
public class Bike extends Vehicle{
    /**
     * A constructor, pass the information to Vehicle Class.
     * @param vehicleType Type of vehicle.
     * @param rengId Registration ID.
     * @param model Model of vehicle.
     * @param colour Colour of vehicle.
     * @param entryDate Entry Datetime.
     */
    public Bike(String vehicleType, String rengId, String model, String colour, Date entryDate) {
        super(vehicleType, rengId, model, colour, entryDate);
        // Use super to pass the value back the constructor of vehicle class.
    }
    /**
     * Override method of getSymbol method in Vehicle Class.
     * @return A String "C".
     */
    @Override
    public String getSymbol() {
        return "B";
    }
    /**
     * Override method of calculating the total parking fee.
     * @param timeOfExit Exit datetime of Date Class.
     * @return Total parking fee.
     */
    @Override
    public double calculateParkingFee(Date timeOfExit) {
        final int hourParkingFee = 2;
        final int overnightFee = 5;
        calculateTotalHour(timeOfExit);
        double fee = hourParkingFee * getTotalHour() + overnightFee * getTotalOvernightParking();
        setParkingFee(fee);
        return fee;
    }
}