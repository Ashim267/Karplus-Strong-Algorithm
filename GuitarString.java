// File name: GuitarString.java
// Author: Ashim Chand
// userid: chanda
// Email: ashimchand@vanderbilt.edu
// Class: CS2201
// Honor Statement: Will not use unfair means
// Assignment Number: 6
// Description: Gitar String Implimentation
// Last Changed: 3/26/2024

import java.util.Random;

public class GuitarString {

    private DblQueue buff;
    private int tics;
    private double frequency;
    // Do not change or delete these two public constants
    public static final int SAMPLE_RATE = 44100;
    public static final double DECAY_FACTOR = 0.996;


    // TODO
    // Your task is to completely implement a GuitarString class.
    // This class will model/simulate a vibrating guitar string of a 
    // given frequency. It uses a ring buffer of double values to
    // implement the Karplus-Strong algorithm. Make sure all public
    // methods are as specified in the project spec, otherwise our
    // grading code will not work with it and you will be penalized.

    public GuitarString(double frequency) {
        if (frequency <= 0) {
            throw new IllegalArgumentException("Frequency -ve");
        }
        this.frequency = frequency;
        int capacity = (int) Math.round(SAMPLE_RATE / frequency);
        this.buff = new DblQueue();
        for (int i = 0; i < capacity; i++) {
            buff.enqueue(0.0);
        }
        this.tics = 0;
    }

    public void pluck() {
        java.util.Random rand = new java.util.Random();
        for (int i = 0; i < buff.size(); i++) {
            buff.dequeue();
            buff.enqueue(rand.nextDouble() - 0.5);
        }
    }

    public void tic() {
        double first = buff.front();
        buff.dequeue();
        double second = buff.front();
        double avg = DECAY_FACTOR * (first + second) / 2.0;
        buff.enqueue(avg);
        tics++;
    }

    public double sample() {
        return buff.front();
    }

    public int getTime() {
        return tics;
    }

    public double getFrequency() {
        return frequency;
    }

    public GuitarString clone() {
        GuitarString cloned = new GuitarString(this.frequency);

        for (int i = 0; i < cloned.buff.size(); i++) {
            cloned.buff.enqueue(0);
        }
        cloned.tics = this.tics;
        return cloned;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        GuitarString that = (GuitarString) obj;

        return Double.compare(that.frequency, frequency) == 0 && this.tics == that.tics;
    }

}
