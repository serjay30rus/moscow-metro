public class MetroStation {
    private String stationName;              // Name of metro station
    private String lineName;                  // Name of metro line

    public MetroStation(String stationNames, String lineName) {
        this.stationName = stationNames;
        this.lineName = lineName;
    }

    public MetroStation() {
    }

    public String getStationName() {
        return stationName;
    }

    public String getLineName() {
        return lineName;
    }


    @Override
    public String toString() {
        return "\nStation name:\t" + getStationName() + "\tline name:\t" + getLineName();
    }
}
