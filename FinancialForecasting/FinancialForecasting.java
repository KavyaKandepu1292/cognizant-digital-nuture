public class FinancialForecasting {

    // RECURSIVE approach — O(n)
    public static double futureValueRecursive(double presentValue,
                                               double growthRate,
                                               int years) {
        // Base case
        if (years == 0) return presentValue;

        // Recursive case: grow by one year then recurse
        return futureValueRecursive(
            presentValue * (1 + growthRate),
            growthRate,
            years - 1
        );
    }

    // MEMOIZED recursive — O(n) but no repeated calls
    static double[] memo = new double[1000];

    public static double futureValueMemo(double presentValue,
                                          double growthRate,
                                          int years) {
        if (years == 0) return presentValue;
        if (memo[years] != 0) return memo[years];  // cached!

        memo[years] = futureValueMemo(presentValue, growthRate, years - 1)
                      * (1 + growthRate);
        return memo[years];
    }

    // ITERATIVE approach — O(n) cleaner
    public static double futureValueIterative(double presentValue,
                                               double growthRate,
                                               int years) {
        double value = presentValue;
        for (int i = 0; i < years; i++)
            value *= (1 + growthRate);
        return value;
    }

    public static void main(String[] args) {

        System.out.println("=== Financial Forecasting Tool ===\n");

        double presentValue = 100000.00;  // Rs. 1 Lakh
        double growthRate   = 0.10;       // 10% per year

        System.out.println("Present Value : Rs. " + presentValue);
        System.out.println("Growth Rate   : " + (growthRate * 100) + "%");

        System.out.println("\n--- Forecast using Recursive Approach ---");
        for (int y = 1; y <= 5; y++) {
            double fv = futureValueRecursive(presentValue, growthRate, y);
            System.out.printf("Year %d : Rs. %.2f%n", y, fv);
        }

        System.out.println("\n--- Forecast using Iterative Approach ---");
        for (int y = 1; y <= 5; y++) {
            double fv = futureValueIterative(presentValue, growthRate, y);
            System.out.printf("Year %d : Rs. %.2f%n", y, fv);
        }

        System.out.println("\n--- Forecast for 10 years ---");
        double fv10 = futureValueRecursive(presentValue, growthRate, 10);
        System.out.printf("Year 10 : Rs. %.2f%n", fv10);

        // ANALYSIS
        System.out.println("\n--- Time Complexity Analysis ---");
        System.out.println("Recursive          O(n)  one call per year");
        System.out.println("Memoized Recursive O(n)  no repeated calls");
        System.out.println("Iterative          O(n)  most efficient!");
        System.out.println("Optimization: Use iterative or memoization");
        System.out.println("to avoid stack overflow for large year values.");
    }
}