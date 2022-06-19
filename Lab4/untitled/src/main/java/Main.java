public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.printMinAndMax(0.1, 1, 0.001);
    }

    public void printMinAndMax(double a, double b, double d) {
        double[] output = calculateYs(a, b, d);
        System.out.println("Results: ");
        for (int i = 0; i < output.length; i++)
            System.out.printf("bx =%.4f y = %.4f\n", a + i * d, output[i]);
        int min = findMin(output);
        int max = findMax(output);
        System.out.printf("Min: bx =%.4f y = %.4f\n", a + min * d, output[min]);
        System.out.printf("Max: bx =%.4f y = %.4f\n", a + max * d, output[max]);
    }

    public double calculateY(double bx) {
        return bx > 0.45 ? bx + Math.log10(bx) : bx - Math.tan(bx);
    }

    public int calculateStepsCount(double a, double b, double d) {
        return ((int) ((b - a) / d) + 1);
    }

    public double[] calculateYs(double a, double b, double d) {
        double[] result = new double[calculateStepsCount(a, b, d)];
        for (int i = 0; i < result.length; i++)
            result[i] = calculateY(a + i * d);
        return result;
    }

    public int findMax(double[] arr) {
        if (arr.length == 0)
            return -1;
        int result = 0;
        for (int i = 1; i < arr.length; i++)
            if (arr[result] < arr[i])
                result = i;
        return result;
    }

    public int findMin(double[] arr) {
        if (arr.length == 0)
            return -1;
        int result = 0;
        for (int i = 1; i < arr.length; i++)
            if (arr[result] > arr[i])
                result = i;
        return result;
    }

    public double sum(double[] arr) {
        double result = 0;
        for (int i = 0; i < arr.length; i++)
            result += arr[i];
        return result;
    }

    public double average(double[] arr) {
        double result = 0;
        for (int i = 0; i < arr.length; i++)
            result += arr[i];
        result /= arr.length;
        return result;
    }
}
