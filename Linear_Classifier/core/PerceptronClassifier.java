package lc.core;

import math.util.VectorOps;

import java.util.List;

/**
 * Created by naven on 4/21/2017.
 */
public class PerceptronClassifier extends LinearClassifier{
    public PerceptronClassifier(double[] weights) {
        super(weights);
    }
    public PerceptronClassifier(int ninputs) {
        super(ninputs);

    }
    @Override
    public void trainingReport(List<Example> examples, int stepnum, int nsteps){

    }
    public void print( double []w){
        for (double x: w){
            System.out.print(x +", ");
        }
    }

    @Override
    public void update(double[] x, double y, double alpha) {
//        for (int i=0; i < x.length; i++){
//            System.out.println("X " + x[i] + " W " + weights[i]);
//        }

        for (int i =0; i<x.length; i++){

            weights[i]=weights[i]+alpha*(y-threshold(VectorOps.dot(x,weights)))*x[i];
           // System.out.println("afterwards "+y+" & "+threshold(VectorOps.dot(x,weights))+" $ "+ x[i]);
        }
    }

    @Override
    public double threshold(double z) {
        // z = w.x
       //System.out.println("z "+ z);
        if (z>=0){
            return  1.0;

        }else{
            return  0.0;
        }
         //return 1/(1+Math.exp(-z));
    }

}

