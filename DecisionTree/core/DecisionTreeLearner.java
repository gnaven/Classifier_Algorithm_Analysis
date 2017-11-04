package dt.core;

import java.awt.image.ImageObserver;
import java.util.*;

/**
 * Created by naven on 4/14/2017.
 */
public class DecisionTreeLearner extends AbstractDecisionTreeLearner {
    /**
     * Construct and return a new DecisionTreeLearner for the given Problem.
     *
     * @param problem
     */
    public DecisionTreeLearner(Problem problem) {
        super(problem);
    }

    @Override
    protected DecisionTree learn(Set<Example> examples, List<Variable> attributes, Set<Example> parent_examples) {
        DecisionTree tree;
        if (examples.isEmpty()){
            return PV(parent_examples);
        }else if(ClassificationAll(examples)){
            String val = "";
            for (Example ex : examples){
                val = ex.outputValue;
            }
            return (new DecisionTree(val));
        }
        else if (attributes.isEmpty()){
            return PV(examples);
        }else {
            Variable ImpAttribute= mostImportantVariable(attributes,examples);
            tree = new DecisionTree(ImpAttribute);
            for (String vk : ImpAttribute.domain){
                Set<Example> subExample= examplesWithValueForAttribute(examples, ImpAttribute,vk);
               // List<Variable> attribRemoved= remove(attributes,ImpAttribute);
                attributes.remove(ImpAttribute);
                DecisionTree subtree = learn(subExample,attributes,examples);
                attributes.add(ImpAttribute);
                tree.children.add(subtree);
            }
        }

        return tree;
    }

    public boolean ClassificationAll(Set<Example> examples){
        String store = " ";

        for (Example example : examples){
            store = example.getOutputValue();
        }

        for (Example example: examples) {
            if (!(example.getOutputValue().equals(store))) {
                return false;
            }
        }

        return true;
    }
    // returns the leaf node for the tree with the plurality value
    public DecisionTree PV (Set<Example>examples){
        DecisionTree tree = new DecisionTree(pluralityValue(examples));
        return tree;
    }

    @Override
    protected String pluralityValue(Set<Example> examples){
        HashMap<String, Integer> Plu = new HashMap<String,Integer>();
        for (Example e : examples){
            if (Plu.keySet().contains(e.outputValue)){
                Plu.put(e.outputValue, Plu.get(e.outputValue)+1);
            }else {
                Plu.put(e.outputValue,1);
            }
        }
        int Store = 0;
        String storeVal= "";
        for (String value: Plu.keySet()){
            if (Plu.get(value)> Store){
                Store= Plu.get(value);
                storeVal= value;
            }
        }
        return storeVal;
    }
    public List<Variable> remove (List<Variable> attributes, Variable atrRemove){
        for (int i = 0 ; i<attributes.size();i ++){
            if (attributes.get(i).equals(atrRemove)){
                attributes.remove(atrRemove);
            }
        }
        return attributes;
    }
    @Override
    protected String uniqueOutputValue(Set<Example> examples) {


        return null;
    }

    @Override
    protected Set<Example> examplesWithValueForAttribute(Set<Example> examples, Variable a, String vk) {

        HashSet <Example> e = new HashSet<Example>();

        for(Example example: examples){
            if (example.getInputValue(a).equals(vk)){
                e.add(example);
            }
        }


        return e;
    }

    @Override
    protected int countExamplesWithValueForAttribute(Set<Example> examples, Variable a, String vk) {
        return 0;
    }

    @Override
    protected int countExamplesWithValueForOutput(Set<Example> examples, String vk) {

        return 0;
    }


}
