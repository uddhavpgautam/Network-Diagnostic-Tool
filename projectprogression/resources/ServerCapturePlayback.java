
/**
 * *********************************************************************
 * File: ServerCapturePlayback.java
 * *********************************************************************
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;

public class ServerCapturePlayback {

    boolean stopCapture = false;
    ByteArrayOutputStream byteArrayOutputStream;
    AudioFormat audioFormat1 = null;
    TargetDataLine targetDataLine;
    AudioInputStream audioInputStream;
    SourceDataLine sourceDataLine;
    private PrintWriter outVoice = null;
    InputStream inVoice;
    byte audioData[];

    public ServerCapturePlayback() //constructor
    {
    }

    public ServerCapturePlayback(ChatServer chatServer) //constructor
    {
        try {
            audioFormat1 = getAudioFormat();
            inVoice = chatServer.theConnection.getInputStream();
            outVoice = new PrintWriter(new OutputStreamWriter(chatServer.theConnection.getOutputStream()));
        } catch (IOException ioe) {
            System.out.println("error in input" + ioe);
        }
    }//end constructor

    public PrintWriter getVoiceOutput() {
        return outVoice;
    }

    public void sendVoiceOutput() {
        outVoice.println(byteArrayOutputStream.toString());
        outVoice.flush();
    }

    //This method captures audio input from a microphone and saves it in
    // a ByteArrayOutputStream object.
    public void captureAudio() {
        try {
            /*if (audioFormat != null)
             {
             audioFormat = null;
             }*/

            //Get everything set up for capture
            //AudioFormat audioFormat = getAudioFormat();
            DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat1);
            targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
            targetDataLine.open(audioFormat1);
            targetDataLine.start();
            //Create a thread to capture the
            // microphone data and start it
            // running. It will run until
            // the Stop button is clicked.
            Thread captureThread = new Thread(new CaptureThread());
            captureThread.start();
        } catch (Exception e) {
            System.out.println("error in capture" + e);
        }//end catch
    }//end captureAudio method

//This method plays back the audio data that has been saved in the ByteArrayOutputStream
    public void playAudio() {
        try {
            //Get everything set up for playback.
            //Get the previously-saved data into a byte array object.

            /*	if (audioFormat != null)
             {
             audioFormat = null;
             }*/
            InputStream byteArrayInputStream = inVoice;
            //	AudioFormat audioFormat = getAudioFormat();
            audioInputStream = new AudioInputStream(byteArrayInputStream, audioFormat1, 50000);
            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat1);
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            sourceDataLine.open(audioFormat1);
            sourceDataLine.start();
            //Create a thread to play back the data and start it running.
            // It will run until all the data has been played back.
            Thread playThread = new Thread(new PlayThread());
            playThread.start();
        } catch (Exception e) {
            System.out.println("error in playback" + e);
        }//end catch
    }//end playAudio

//This method creates and returns an AudioFormat object for a given set of format parameters. If these
// parameters don�t work well for you, try some of the other allowable parameter values, which
// are shown in comments following  the declarations.
    private AudioFormat getAudioFormat() {
        float sampleRate = 8000.0F;
        //8000,11025,16000,22050,44100
        int sampleSizeInBits = 16;
        //8,16
        int channels = 1;
        //1,2
        boolean signed = true;
        //true,false
        boolean bigEndian = false;
        //true,false
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }//end getAudioFormat

//===================================//
//Inner class to capture data from microphone
    class CaptureThread extends Thread {
        //An arbitrary-size temporary holding buffer

        byte tempBuffer[] = new byte[10000];

        public void run() {
            byteArrayOutputStream = new ByteArrayOutputStream();
            stopCapture = false;
            try {
                //Loop until stopCapture is set by another thread that
                // services the Stop button.
                while (!stopCapture) {
                    //Read data from the internal
                    // buffer of the data line.
                    int cnt = targetDataLine.read(tempBuffer, 0, tempBuffer.length);
                    if (cnt > 0) {
                        //Save data in output stream object.
                        byteArrayOutputStream.write(tempBuffer, 0, cnt);

                    }//end if
                }//end while
                byteArrayOutputStream.close();
            } catch (Exception e) {

                System.out.println(e);
            }//end catch
        }//end run
    }//end inner class CaptureThread
//===================================//

//Inner class to play back the data
// that was saved.
    class PlayThread extends Thread {

        byte tempBuffer[] = new byte[10000];

        public void run() {
            try {
                int cnt;
                //Keep looping until the input
                // read method returns -1 for
                // empty stream.
                while ((cnt = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1) {
                    if (cnt > 0) {
                        //Write data to the internal buffer of the data line
                        // where it will be delivered to the speaker.
                        sourceDataLine.write(tempBuffer, 0, cnt);
                    }//end if
                }//end while
                //Block and wait for internal
                // buffer of the data line to
                // empty.
                sourceDataLine.drain();
                sourceDataLine.close();
            } catch (Exception e) {
                System.out.println(e);
            }//end catch
        }//end run
    }//end inner class PlayThread
//===================================//
}//end outer class CapturePlayback.java
