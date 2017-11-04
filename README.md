# Classifier_Algorithm_Analysis
Gazi Mahir Ahmed Naven 

Please compile the program as you would compile packages.

When testing the Decision Tree Learning Algorithm. It is in dt package. Main methods are in the examples package

The command line arguments should be: 
argv[0] = filepath for learning data from text file  
argv[1] = filepath for testing dataset 
For Iris 
To compile
C:\Users\naven\Desktop\UofR\CSC_242\project-4\src>javac dt/examples/Iris.java
to Run
C:\Users\naven\Desktop\UofR\CSC_242\project-4\src>java dt.example.Iris filepath\iris.data.discrete.txt filepath\iris.data.discrete.txt

To use Will Wait please use the WillWaitProblem class instead of the Iris class
The Iris and the Willwait datasets are in the examples package in the dt package

Output: Proportion of the training and testing datasets. 

If you want to test Iris data set, run the Iris class in the examples package. 
For the WillWaitProblem data set, run the WillWaitProblem class in the examples package. 

When testing the linear classifier.  

lc package -> example -> PerceptronClassifierTest

The command line should take in arguments in the following order.
argv[0] = filename 
argv[1] = number of steps
argv[2] = alpha  

Compile in the similar way for the lc package.

(note: 

if you want to use constant alpha - it would be best if : 
argv[1] = number of steps = 1000 
argv[2] = alpha = 10 

if you want to use declining alpha: 

set: argv[2] = 0 (instead of alpha value )
   
It will display a GUI with the graph. For better results, please test it few times since the weight varies, the result may have different outlooks.  
