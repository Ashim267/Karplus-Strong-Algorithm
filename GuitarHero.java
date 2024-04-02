// File name: GuitarHero.java
// Author: Ashim Chand
// userid: chanda
// Email: ashimchand@vanderbilt.edu
// Class: CS2201
// Honor Statement: Will not use unfair means
// Assignment Number: 6
// Description: Queue Implimentation
// Last Changed: 3/26/2024
//

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;


public class GuitarHero {

    public static final double CONCERT_A = 440.0;
    public static final int NUM_STRINGS = 37;
    public static final double STEP = (double)1/((double)GuitarString.SAMPLE_RATE);
    public static final int END_OF_SONG = -1;


    public static void main(String[] args) throws FileNotFoundException {

        // create the array of guitar strings (3 octaves)
        GuitarString[] strings = new GuitarString[NUM_STRINGS];
        createStrings(strings);

        //open data file
        Scanner keyboard = new Scanner(System.in);
        Scanner infile = openInputFile(keyboard);
        PrintStream outfile = openOutputFile(keyboard);

        //prime the output file
        String firstLine  = "; Sample Rate " + GuitarString.SAMPLE_RATE;
        String secondLine = "; Channels 1";
        outfile.println(firstLine);
        outfile.println(secondLine);

        System.out.println("Reading the input file and generating a .dat file for sox");

        // TODO
        // your task is to read & process all the data in the input stream "infile",
        // and create & write the correct data to the output stream "outfile".
        // That work should be done here before the files are closed.
        // Define and use helper methods as needed for good style.


        double nextTime = infile.hasNextDouble() ? infile.nextDouble() : Double.MAX_VALUE;
        int nIndex = infile.hasNextInt() ? infile.nextInt() : END_OF_SONG;

        double current = 0.0;
        while (nIndex != END_OF_SONG || current <= nextTime) {
            if (current >= nextTime) {
                if (nIndex != END_OF_SONG) {
                    strings[nIndex].pluck();
                }

                if (infile.hasNextDouble()) {
                    nextTime = infile.nextDouble();
                    nIndex = infile.hasNextInt() ? infile.nextInt() : END_OF_SONG;
                } else {
                    nextTime = Double.MAX_VALUE;
                }
            }

            double sample = 0.0;
            for (GuitarString string : strings) {
                sample += string.sample();
                string.tic();
            }
            outfile.println(sample);

            current += STEP;

        }

        // close the files
        infile.close();
        outfile.close();

        System.out.println("Done.");
    }



    // TODO
    // If you want to create your own helper methods (highly recommended),
    // define them here.


    /**
     * createStrings -- create the guitar string objects
     * post: array of GuitarString objects have been created and initialized
     * @param strings
     */
    private static void createStrings(GuitarString[] strings) {
        for (int i = 0; i < strings.length; i++) {
            double factor = Math.pow(2.0, (i - 24) / 12.0);
            strings[i] = new GuitarString(CONCERT_A * factor);
        }
    }


    /**
     * openInputFile
     * pre: user is prepared to enter file name at the keyboard
     * post: file have been opened
     * @param keyboard -- opened Scanner object
     * @return Scanner object opened on input file
     * @throws FileNotFoundException
     */
    private static Scanner openInputFile (Scanner keyboard) throws FileNotFoundException {

        // open input data file
        String inFileName;
        System.out.print("Enter the name of the input file: ");
        inFileName = keyboard.nextLine();
        File f = new File(inFileName);
        while (!f.canRead()) {
            System.out.println("File not found. Try again.");
            System.out.print("Enter the name of the input file: ");
            inFileName = keyboard.nextLine();
            f = new File(inFileName);
        }
        Scanner infile = new Scanner(f);
        return infile;
    }


    /**
     * openOutputFile
     * pre: user is prepared to enter file name at the keyboard
     * post: file have been opened
     * @param keyboard -- opened Scanner object
     * @return PrintStream object opened on output file
     * @throws FileNotFoundException
     */
    private static PrintStream openOutputFile (Scanner keyboard) throws FileNotFoundException {

        // open output data file
        String outFileName;
        System.out.print("Enter the name of the output file: ");
        outFileName = keyboard.nextLine();
        File f2 = new File(outFileName);
        PrintStream outfile = new PrintStream(f2);
        return outfile;
    }

}
