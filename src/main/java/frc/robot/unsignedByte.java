package frc.robot;

public class unsignedByte {
   private int value;
    public unsignedByte(byte val){
        value=val+128;
    }

    public unsignedByte(int val){
        if (val > 255){
            throw(new Error("unsigned byte was initalized with value over 255"));
        }
        value =  val;
    }

    public int get(){
        return value+=128;
    }
    public byte getAsByte(){
        //returns the value as a signed byte
        return (byte) (value-128);
    }
}
