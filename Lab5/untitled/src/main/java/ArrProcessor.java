public class ArrProcessor implements ArrayProcessor {
    @Override
    public double getMin(double[] array) {
        if (array.length == 0)
            return 0;
        double result = array[0];
        for (int i = 2; i < array.length; i += 2)
            if (result > array[i])
                result = array[i];
        return result;
    }

    @Override
    public double getSumOfSegment(double[][] array) {
        double result = 0;
        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array[i].length; j++)
                if (j >= i && j <= array.length - i - 1 && Math.abs(array[i][j]) < 100)
                    result += array[i][j];
        return result;
    }
}
