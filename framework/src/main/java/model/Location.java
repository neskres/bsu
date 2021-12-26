package model;

public class Location {
    private String fromLocation;
    private String toLocation;
    private String pickupLocation;

    public Location(String fromLocation, String toLocation, String pickupLocation) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.pickupLocation = pickupLocation;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public String getPickupLocation() { return pickupLocation; }
}
