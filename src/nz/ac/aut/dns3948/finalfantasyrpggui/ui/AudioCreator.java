/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nz.ac.aut.dns3948.finalfantasyrpggui.ui;

import java.io.File;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

/**
 * Class AudioCreator
 * A class that plays and stops music in the game
 * @author dns3948
 */
public class AudioCreator {
    private String gameMusic;
    Sequencer musicPlayer;
    
    /**
     * Constructor for class AudioCreator
     * @param musicName name of the midi file
     */
    public AudioCreator(String musicName){
        gameMusic = musicName;
    }

    /**
     * Create and play the midi file
     */
    public void createMusic(){     
        try {
            Sequence musicData = MidiSystem.getSequence(new File("Music Files/"
            + gameMusic +".mid"));
            musicPlayer = MidiSystem.getSequencer();
            musicPlayer.open();
            musicPlayer.setSequence(musicData);
            musicPlayer.setLoopStartPoint(4000);
            musicPlayer.start();
            musicPlayer.setLoopCount(3);
        }
        catch (IOException ex){
            //Show Exception's been caught
            //System.out.println("Caught exception: " + ex);
        }
        catch (MidiUnavailableException ex){
            //Show Exception's been caught
            //System.out.println("Caught exception: " + ex);
        }
        catch (InvalidMidiDataException ex){
            //Show Exception's been caught
            //System.out.println("Caught exception: " + ex);
        }
       
    }

    /**
     * Closes the midi file
     */
    public void closeMusic(){
        musicPlayer.close();
    }


}
