package processor;


import java.util.Arrays;

public class Operations extends Menu {


    public static int[] splitStringToIntegers(String data) {
        return Arrays.stream(data.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    public static double[] splitStringToDouble(String data) {
        return Arrays.stream(data.split(" ")).mapToDouble(Double::parseDouble).toArray();
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] doubles : matrix) {
            for (double aDouble : doubles) {
                System.out.print(fmt(aDouble) + " ");
            }
            System.out.print("\n");
        }
    }

    public static double[][] multiplyMatrix(double[][] matrixOne, double[][] matrixTwo) {
        double[][] result = new double[matrixOne.length][matrixTwo[0].length];
        for (int row = 0; row < matrixOne.length; row++) {
            for (int col = 0; col < matrixTwo[0].length; col++) {
                result[row][col] = multiplyMatrixCell(matrixOne, matrixTwo, row, col);
            }
        }
        return result;
    }

    private static double multiplyMatrixCell(double[][] matrixOne, double[][] matrixTwo, int row, int col) {
        double result = 0;
        for (int i = 0; i < matrixTwo.length; i++) {
            result += matrixOne[row][i] * matrixTwo[i][col];
        }
        return result;
    }

    public static double[][] transposeMain(double[][] matrixOne) {
        double[][] result = new double[matrixOne[0].length][matrixOne.length];
        for (int i = 0; i < matrixOne.length; i++) {
            for (int j = 0; j < matrixOne[0].length; j++) {
                result[j][i] = matrixOne[i][j];
            }
        }
        return result;
    }

    public static double[][] transposeSide(double[][] matrixOne) {
        double[][] result = new double[matrixOne[0].length][matrixOne.length];
        int invertedColumn = result[0].length - 1;
        int invertedRow = result.length - 1;
        //rows loop
        for (int i = 0; i < matrixOne.length; i++) {
            //columns loop
            for (int j = 0; j < result[0].length; j++) {
                result[invertedRow - j][invertedColumn - i] = matrixOne[i][j];
            }
            System.out.println("inverted column = " + invertedColumn);
        }
        return result;
    }

    public static double[][] transposeVertical(double[][] matrixOne){
        double[][] result = new double[matrixOne.length][matrixOne[0].length];
        int column = matrixOne[0].length - 1;

        for (int i = 0; i < matrixOne.length; i++) {
            for (int j = 0; j < matrixOne[0].length; j++) {
                result[i][column - j] = matrixOne[i][j];
            }
        }
        return result;
    }

    public static double[][] transposeHorizontal(double[][] matrixOne){
        double[][] result = new double[matrixOne.length][matrixOne[0].length];
        int row = matrixOne.length - 1;

        for (int i = 0; i < matrixOne.length; i++) {
            System.arraycopy(matrixOne[i], 0, result[row - i], 0, matrixOne[0].length);
        }
        return result;
    }


    public static String fmt(double d)
    {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);

    }

    public static double[][] addM(int rows, int columns) {
        double[][] arr = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            System.out.print("> ");
            double[] row = splitStringToDouble(scanner.nextLine());
            arr[i] = row;
        }

        return arr;
    }


}

