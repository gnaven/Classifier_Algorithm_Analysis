package dt.example;

import dt.core.*;

import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * Created by naven on 4/18/2017.
 */
public class Iris extends Problem{

    public Iris(){
        super();
        this.inputs.add(new Variable("SepalLength", new Domain("MS","S","L","ML")));
        this.inputs.add(new Variable("SepalWidth", new Domain("MS","S","L","ML")));
        this.inputs.add(new Variable("PetalLength", new Domain("MS","S","L","ML")));
        this.inputs.add(new Variable("PetalWidth", new Domain("MS","S","L","ML")));

        this.output= new Variable("class", new Domain("Iris-setosa", "Iris-versicolour", "Iris-virginica"));
    }

    public static void main(String[] argv) throws IOException {
        Problem problem = new Iris();
        problem.dump();
        Set<Example> examples = problem.readExamplesFromCSVFile(new File(argv[0]));
        for (Example e : examples) {
            System.out.println(e);
        }
        DecisionTree tree = new DecisionTreeLearner(problem).learn(examples);
        tree.dump();
        Problem problem1 = new Iris();

        Set<Example> examplestest = problem1.readExamplesFromCSVFile(new File(argv[1]));
        tree.test(examplestest);

    }


    }
