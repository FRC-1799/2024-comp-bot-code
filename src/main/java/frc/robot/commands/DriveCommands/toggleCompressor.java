package frc.robot.commands.DriveCommands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Pnumatics;

public class toggleCompressor extends InstantCommand{
    Pnumatics compressor;
    public toggleCompressor(Pnumatics compressor){
        this.compressor=compressor;
      }

    @Override
    public void execute(){
        compressor.toggleCompressor();
    }
}
