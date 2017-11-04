package dt.core;

import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * A DecisionTree is a recursive, dynamic tree structure.
 * There are two types of DecisionTree: either its label
 * is a Variable, in which case it is an internal node
 * and can have children in the order of its Variable's
 * values, or its label is a String, in which case it
 * is a leaf (result) node.
 */
public class DecisionTree {
	
	Variable variable;
	List<DecisionTree> children;

	/**
	 * Construct and return a new non-leaf DecisionTree
	 * labeled with the given Variable.
	 */
	public DecisionTree(Variable variable) {
		this.variable = variable;
		this.children = new ArrayList<DecisionTree>();
	}

	String value;
	
	/**
	 * Construct and return a new leaf DecisionTree
	 * labeled with the given String value.
	 */
	public DecisionTree(String value) {
		this.value = value;
	}
	
	/**
	 * Dump this DecisionTree to stdout.
	 */
	public void dump() {
		dump(0);
	}
	
	protected void dump(int depth) {
		indent(depth);
		if (variable != null) {
			System.out.println(variable);
			for (int i=0; i < variable.domain.size(); i++) {
				String value = variable.domain.get(i);
				DecisionTree child = children.get(i);
				indent(depth+2);
				System.out.println(value + ":");
				child.dump(depth+4);
			}
		} else {
			System.out.println(value);
		}
	}
	
	protected void indent(int n) {
		for (int i=0; i < n; i++) {
			System.out.print(' ');
		}
	}
	
	/**
	 * Return the value computed by this DecisionTree for the given Example.
	 */
	public String eval(Example example) {

		if (this.value != null) {
			// We are a leaf
			return this.value;
		} else {

			// Example has value vk for this variable
			Set<Variable> ke = example.inputValues.keySet();
			//System.out.println("out "+ example.inputValues);
			String vk="";
			for (Variable v : ke){
				if (this.variable.toString().equals(v.toString())){
					vk = example.getInputValue(v);
				}
			}

			//String vk = example.getInputValue(this.variable);
			//System.out.println(vk+ " vk ");
			// Find child for that value in our children in domain order
			for (int i=0; i < variable.domain.size(); i++) {
				String value = variable.domain.get(i);
				if (value.equals(vk)) {
					return this.children.get(i).eval(example);
				}
			}
			// Error
			return null;
		}
	}
	
	/**
	 * Run this DecisionTree on the given Examples and print results and
	 * summary statistics.
	 */
	public void test(Set<Example> examples) {
		int ntested = 0;
		int ncorrect = 0;
		for (Example e : examples) {
			String result = this.eval(e);
			//System.out.println(e );

			//System.out.println(e + "\t" + result);

			ntested += 1;
			String output= e.getOutputValue();
			output= output.replaceAll("\\r", "");
			System.out.println("e"+ e.toString().replaceAll("\\r", "") + " res "+ result.toString());
			if (result.equals(e.getOutputValue())) {
				ncorrect += 1;
			}
		}
		double pct = (double)ncorrect / ntested * 100;
		System.out.format("correct: %d/%d (%.2f)%%", ncorrect, ntested, pct);
	}

}
