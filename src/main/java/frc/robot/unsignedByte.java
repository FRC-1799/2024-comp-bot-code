package frc.robot;

public class unsignedByte {
   private int value;
    public unsignedByte(byte val){
        value=(int)(val & 0x7F) + ((int) val & 0x80);
    }

    public unsignedByte(int val){
        if (val > 255 || val<0){
            throw(new Error("unsigned byte was initalized with value over 255 or a value under 0"));
        }
        value =  val;
    }

    public int get(){
        return value;
    }
    public byte getAsByte(){
        
        return (byte) value;
    }
}
