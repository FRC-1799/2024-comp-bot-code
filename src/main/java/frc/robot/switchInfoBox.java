package frc.robot;

public class switchInfoBox {
    public final int ID;
    public final int readPort;
    public final int statusPort;
    public final String name;
    public final boolean defaultValue;
    public switchInfoBox(int ID, int readPort, int statusPort, boolean defaultValue, String name){
        this.ID=ID;
        this.readPort=readPort;
        this.statusPort=statusPort;
        this.name=name;
        this.defaultValue=defaultValue;
    }
}
