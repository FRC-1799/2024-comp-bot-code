package frc.robot;

public class unsignedByte {
   private byte value;
    public unsignedByte(byte val){
        value=val;
    }

    public unsignedByte(int val){
        if (val > 255){
            throw(new Error("unsigned byte was initalized with value over 255"));
        }
        value = (byte) val;
    }

    public int get(){
        return value+=128;
    }
}
