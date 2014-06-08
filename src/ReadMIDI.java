import java.io.File;
import java.util.Vector;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;


public class ReadMIDI {
	
	private static ReadMIDI instancia;
    public static final int NOTE_ON = 0x90;
   
    private ReadMIDI(){
    	
    }
    
    public static ReadMIDI getInstance(){
    	if (instancia == null){
    		instancia = new ReadMIDI();
    	}
    	return instancia;
    }
    
    public Vector<Integer> getNotes(String filename, int track) throws Exception
    {
    	Vector<Integer> v = new Vector<Integer>();
    	Sequence sequence = MidiSystem.getSequence(new File(filename));
    	Track t = sequence.getTracks()[track];
        for (int i=0; i < t.size(); i++) { 
                MidiEvent event = t.get(i);
               MidiMessage message = event.getMessage();
                if (message instanceof ShortMessage) {
                    ShortMessage sm = (ShortMessage) message;
                    if (sm.getCommand() == NOTE_ON) {
                        v.add((sm.getData1()));
                    }
                }               	
        }

		return v;
    }

    /*
	public static void main(String[] args) throws Exception {
		Vector<Integer> notes = getNotes("bigbang.mid", 3);
		System.out.println("The Big Bang Theory Notes:");
		for (Integer note : notes) {
			System.out.println(note);
		}
	}
	*/

}
