
/* 
 I, Yair lahad (205493018), assert that the work I submitted is entirely my own.
 I have not received any part from any other student in the class, nor did I give parts of it for use to others.
 I realize that if my work is found to contain code that is not originally my own, a
 formal case will be opened against me with the BGU disciplinary committee.
*/

// Last Update: 24/11/19

public class Assignment2 {

	
	/*--------------------------------------------------------
	   Part a: instance representation & Solution verification 
	  -------------------------------------------------------
	 */

    // Task 1
    public static boolean hasFlight(int[][] flights, int i, int j) {
        for (int flight = 0; flight < flights[i].length; flight++) {
            if (flights[i][flight] == j) {
                return true;
            }
        }
        return false;
    }

    // Task 2
    public static boolean isLegalInstance(int[][] flights) {
        boolean flag = false; // flag will be raised if flight number i in flights[i]
        if (flights != null) {  //condition A begins
            if (flights.length > 1) {
                int n = flights.length;
                for (int i = 0; i < n; i++) {
                    flag = false;
                    if (flights[i] == null) return false; // condition B2
                    for (int j = 0; j < flights[i].length; j++) {
                        if (flights[flights[i][j]] == null)
                            return false; // condition B2 after calling hasFlight(flight, i,j)
                        if (flights[i][j] >= n | flights[i][j] < 0) return false; //condition B1
                        // condition C : !(A=B) biconditional to (A&!B)or(!A&B)
                        if (hasFlight(flights, i, flights[i][j]))
                            if (!(hasFlight(flights, flights[i][j], i))) return false;
                        // condition C other way
                        if (!(hasFlight(flights, i, flights[i][j])))
                            if ((hasFlight(flights, flights[i][j], i))) return false;
                        if (flights[i][j] == i) flag = true;//condition D begin
                    }
                    if (flag) return false; //condition D end
                }
            }
        } else
            return false; // condition A end
        return true; // returns true if all conditions works
    }

    // Task 3
    public static boolean isSolution(int[][] flights, int[] tour) {
        if (tour.length != flights.length)
            throw new IllegalArgumentException("IllegalArgumentException");
        for (int i = 0; i < tour.length; i = i + 1) {
            if (tour[i] >= flights.length | tour[i] < 0)
                throw new IllegalArgumentException("IllegalArgumentException");
        }
        // condtion A
        int[] checkCity = new int[tour.length];
        for (int i = 0; i < tour.length; i = i + 1) {
            checkCity[tour[i]]++;
            if (checkCity[tour[i]] != 1)
                return false;
        }
        //condition B
        for (int i = 0; i < tour.length; i = i + 1) {
            if (i < tour.length - 1) {
                if (!(hasFlight(flights, tour[i], tour[i + 1]))) {
                    return false;
                }
            }
            if (i == tour.length - 1) {
                if (!(hasFlight(flights, tour[i], tour[0]))) {
                    return false;
                }
            }
        }
        return true;
    }
	
	/*------------------------------------------------------
	  Part b: Express the problem as a CNF formula, solve 
	  it with a SAT solver, and decode the solution
	  from the satisfying assignment
	  -----------------------------------------------------
	 */

    // Task 4
    public static int[][] atLeastOne(int[] vars) {
        int[][] cnf = new int[1][vars.length];
        for (int i = 0; i < vars.length; i++) {
            cnf[0][i] = vars[i];
        }
        return cnf;
    }

    // Task 5
    public static int[][] atMostOne(int[] vars) {
        int n = vars.length;
        int len = n * (n - 1) / 2;
        int[][] cnf = new int[len][2];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int[] temp = {-vars[i], -vars[j]};
                cnf[index] = temp;
                index++;
            }
        }
        return cnf;
    }

    // Task 6
    public static int[][] append(int[][] arr1, int[][] arr2) {
        int[][] combine = new int[arr1.length + arr2.length][];

        for (int i = 0; i < arr1.length; i++) {
            combine[i] = new int[arr1[i].length]; // each spot will have the length of arr1[i].length
            for (int j = 0; j < arr1[i].length; j++)//adding first array
                combine[i][j] = arr1[i][j];
        }
        int index = 0;
        for (int i = arr1.length; i < combine.length; i++) {
            combine[i] = new int[arr2[index].length];
            for (int j = 0; j < arr2[index].length; j++)//adding second array
                combine[i][j] = arr2[index][j];
            index++;
        }
        return combine;
    }

    // Task 7
    public static int[][] exactlyOne(int[] vars) {
        return append(atLeastOne(vars), atMostOne(vars));
    }

    // Task 8
    public static int[][] diff(int[] I1, int[] I2) {
        int[][] diff = new int[I1.length][2];
        for (int i = 0; i < I1.length; i++) { //choose only one between I1[i] and I2[i]
            diff[i][0] = -I1[i];
            diff[i][1] = -I2[i];
        }

        return diff;
    }

    // Task 9
    public static int[][] createVarsMap(int n) {
        int number = 1;
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = number; // creating a map with x1...xN
                number++;
            }
        }
        return map;
    }

    // Task 10
    public static int[][] declareInts(int[][] map) {
        int[][] cnf = new int[0][0];
        for (int i = 0; i < map.length; i++) {
            int[][] temp = exactlyOne(map[i]);
            cnf = append(cnf, temp);
        }
        return cnf;
    }

    // Task 11
    public static int[][] allDiff(int[][] map) {
        int[][] cnf = new int[0][0];
        for (int i = 0; i < map.length - 1; i++) {
            for (int j = i + 1; j < map.length; j++) {
                int[][] temp = diff(map[i], map[j]);
                cnf = append(cnf, temp);
            }
        }
        return cnf;
    }

    // Task 12
    public static int[][] allStepsAreLegal(int[][] flights, int[][] map) {
        int[][] cnf = new int[0][0];
        int n = flights.length;
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        if (!hasFlight(flights, j, k)) {
                            int[][] temp = {{-map[i][j], -map[0][k]}}; // appending not flight in last place or not flight in first place
                            cnf = append(cnf, temp);
                        }
                    }
                }
            } else {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        if (k != j && !hasFlight(flights, j, k)) {
                            int[][] temp = {{-map[i][j], -map[i + 1][k]}}; //appending not flight or not next flight in next line
                            cnf = append(cnf, temp);
                        }
                    }
                }
            }

        }
        return cnf;
    }

    // Task 13
    public static void encode(int[][] flights, int[][] map) {
        if (!isLegalInstance(flights))
            throw new IllegalArgumentException("IllegalArgumentException");
        if (map.length != flights.length)
            throw new IllegalArgumentException("IllegalArgumentException");
        SATSolver.init(map.length * map.length);
        int[][] cnf = new int[0][0];
        int[][] clause = declareInts(map);
        cnf = append(cnf, clause);
        int[][] clause1 = allDiff(map);
        cnf = append(cnf, clause1);
        int[][] clause2 = allStepsAreLegal(flights, map);
        cnf = append(cnf, clause2);
        SATSolver.addClauses(cnf);

    }

    // Task 14
    public static int[] decode(boolean[] assignment, int[][] map) {
        if (assignment.length != ((map.length * map.length) + 1))
            throw new IllegalArgumentException("IllegalArgumentException");
        int[] sol = new int[map.length];
        int location = 1;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (assignment[location])
                    sol[i] = j;
                location++;
            }
        }
        return sol;
    }

    // Task 15
    public static int[] solve(int[][] flights) {
        if (!isLegalInstance(flights))
            throw new IllegalArgumentException("IllegalArgumentException");
        int[][] map = createVarsMap(flights.length); // creating a map
        SATSolver.init(map.length * map.length); //initializing cnf to solver
        encode(flights, map);
        boolean[] assignment = SATSolver.getSolution(); // activating the solver
        if (assignment == null)
            throw new IllegalArgumentException("IllegalArgumentException");
        if (assignment.length == 0) return null;
        int[] tour = decode(assignment, map);
        if (isSolution(flights, tour))
            return tour;
        else
            throw new IllegalArgumentException("IllegalArgumentException: solution is Illegal");
    }

    // Task 16
    public static boolean solve2(int[][] flights, int s, int t) {
        return false;
    }
}