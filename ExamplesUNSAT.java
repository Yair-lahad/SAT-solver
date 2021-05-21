
public class ExamplesUNSAT {
	
	public static void main(String[] args) {
		UNSATExample1();
		UNSATExample2();
	}
	
	public static void UNSATExample1() {
		int nVars = 3 ;
		SATSolver.init(nVars);
		
		int[] clause = {1} ;
		SATSolver.addClause(clause);
	
		int[][] clauses = {{-1,-2}, {2,3}, {-1,-3}} ;
		SATSolver.addClauses(clauses);
		
		boolean[] assignment = SATSolver.getSolution() ;
		if (assignment.length == nVars+1)
			System.out.println("SAT");
		else
			System.out.println("UNSAT");
		
		// output:
		// -------
		// SAT
	}
	
	public static void UNSATExample2() {
		System.out.println("CNF :");
		System.out.println("-----");
		
		System.out.println("{{1,2,3},");
		System.out.println(" {1,-2},");
		System.out.println(" {5,-9,-3,2},");
		System.out.println(" {-8,-3},");
		System.out.println(" {3,8},");
		System.out.println(" {-1,2},");
		System.out.println(" {3,-8,1,-9,7},") ;
		System.out.println(" {1},");
		System.out.println(" {9,-4,7,2},");
		System.out.println(" {5,6},");
		System.out.println(" {-2}}");
		
		
		System.out.println();
		
		SATSolver.init(10);
		
		SATSolver.addClause(new int[] {1,2,3});
		SATSolver.addClause(new int[] {1,-2});
		SATSolver.addClause(new int[] {5,-9,-3,2});
		SATSolver.addClause(new int[] {-8,-3});
		SATSolver.addClause(new int[] {3,8});
		SATSolver.addClause(new int[] {-1,2});
		
				
		int[] clause = {3,-8,1,-9,7} ;
		SATSolver.addClause(clause);
		
		int[][] clauses = {{1}, {9,-4,7,2}, {5,6}, {-2}};
		SATSolver.addClauses(clauses);
		
		System.out.println("Solution :");
		System.out.println("----------");
		
		boolean[] assignment = SATSolver.getSolution() ;
		printAssignment(assignment);
		
		System.out.println();
		
		// possible output:
		// ----------------
		// CNF :
		// -----
		// {{1,2,3},
		//  {1,-2},
		//  {5,-9,-3,2},
		//  {-8,-3},
		//  {3,8},
		//  {3,-8,1,-9,7},
		//  {1},
		//  {9,-4,7,2},
		//  {5,6}}
		// 
		// Solution :
		// ----------
		// x0 := false 
		// x1 := true 
		// x2 := false 
		// x3 := true 
		// x4 := false 
		// x5 := true 
		// x6 := false 
		// x7 := false 
		// x8 := false 
		// x9 := false 
		// x10 := false 
	}
	
	public static void printAssignment(boolean[] assignment) {
		if (assignment.length == 0) {
			System.out.println("UNSAT"); 
		} else {
			for(int i=1; i<assignment.length; ++i)
				System.out.println("x" + i + " := " + assignment[i] + " ") ;
		}
	}

}
