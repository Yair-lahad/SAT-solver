
public class ExamplesFlights {

	// 4 cities satisfiable
	public static int[][] getExampleSatN4() {
		int[][] flights = 
			{{2, 3},
			 {2, 3},
			 {0, 1, 3},
			 {0, 1, 2}};
		
		return flights;
	}
	
	// 4 cities unsatisfiable
	public static int[][] getExampleUnsatN4() {
		int[][] flights = 
			{{2},
			 {2, 3},
			 {0, 1, 3},
			 {1, 2}};
		
		return flights;
	}
	
	
	// 10 cities unsatisfiable
	public static int[][] getExampleUnsatN10() {
		int[][] flights = 
			{{2, 3, 5},
			 {3, 4, 6},
			 {0, 4, 7},
			 {0, 1, 8},
			 {1, 2, 9},
			 {0, 6, 9},
			 {1, 5, 7},
			 {2, 6, 8},
			 {3, 7, 9},
			 {4, 5, 8}};
		
		return flights;
	}
	
	// 16 cities satisfiable
	public static int[][] getExampleSatN16() {
		int[][] flights = {
				{1, 2, 3},
				{0, 2, 3},
				{0, 1, 4},
				{0, 1, 5},
				{2, 5, 6},
				{3, 4, 7},
				{4, 7, 8},
				{5, 6, 9},
				{6, 9, 10},
				{7, 8, 11},
				{8, 11, 12},
				{9, 10, 13},
				{10, 14, 15},
				{11, 14, 15},
				{12, 13, 15},
				{12, 13, 14}};
		return flights;
	}

	public static int[][] getExampleSatN4_ohad_sat1() {
		int[][] flights =
				{{1,3},
						{2,0},
						{3,1},
						{0,2}};

		return flights;
	}

	public static int[][] getExampleSatN4_ohad_sat2() {
		int[][] flights =
				{{1,3,2},
						{2,0,3},
						{3,1,0},
						{0,2,1}};

		return flights;
	}
	
}
