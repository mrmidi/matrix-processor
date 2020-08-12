package processor;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Menu {

    public static Scanner scanner = new Scanner(System.in);
    public static double[][] matrixOne;
    public static double[][] matrixTwo;
    public static double[][] matrixResult;
    public static DecimalFormat df = new DecimalFormat("#.##");



    public static void mainMenu() {
        while (true) {
            System.out.println("1. Add matrices\n" +
                    "2. Multiply matrix to a constant\n" +
                    "3. Multiply matrices\n" +
                    "4. Transpose matrix\n" +
                    "5. Calculate a determinant\n" +
                    "6. Inverse matrix\n" +
                    "0. Exit");
            System.out.print("Your choice: > ");
            int choose = Integer.parseInt(scanner.nextLine());
            runCmd(choose);
        }
    }

    public static void transposeMenu() {
        System.out.println("1. Main diagonal\n" +
                "2. Side diagonal\n" +
                "3. Vertical line\n" +
                "4. Horizontal line\n");
        System.out.print("Your choice: > ");
        int choose = Integer.parseInt(scanner.nextLine());
        runTranspose(choose);
    }

    private static void runTranspose(int choose) {
        switch (choose) {
            case 1:
                addOne();
                matrixResult = Operations.transposeMain(matrixOne);
                System.out.println("The result is:");
                Operations.printMatrix(matrixResult);
                System.out.println();
                break;
            case 2:
                addOne();
                matrixResult = Operations.transposeSide(matrixOne);
                System.out.println("The result is:");
                Operations.printMatrix(matrixResult);
                System.out.println();
                break;
            case 3:
                addOne();
                matrixResult = Operations.transposeVertical(matrixOne);
                System.out.println("The result is:");
                Operations.printMatrix(matrixResult);
                System.out.println();
                break;
            case 4:
                addOne();
                matrixResult = Operations.transposeHorizontal(matrixOne);
                System.out.println("The result is:");
                Operations.printMatrix(matrixResult);
                System.out.println();
                break;

            default:
                break;
        }
    }

    private static void runCmd(int choose) {
        switch (choose) {
            case 1:
                Matrix matrix = new Matrix("Enter size of first matrix: > ");
                Matrix matrix2 = new Matrix("Enter size of second matrix: > ");
                matrix.plus(matrix2);
                System.out.println("The addition result is: ");
                matrix.printMatrix();
                break;
            case 2:
//                System.out.println("Multiplies two matrices to a constant");
                matrix = new Matrix("Enter size of matrix: > ");
                System.out.print("Enter scalar multiplier > ");
                double m = Double.parseDouble(scanner.nextLine());
                matrix.multiplyScalar(m);
                System.out.println("The scalar multiplication result is: ");
                matrix.printMatrix();
                System.out.println();
                break;
            case 3:
//                System.out.println("Multiplies two matrices");
                addTwo();
                matrixResult = Operations.multiplyMatrix(matrixOne, matrixTwo);
                System.out.println("The multiplication of two matrices result is: ");
                Operations.printMatrix(matrixResult);
                System.out.println();
                break;
            case 4:
                transposeMenu();
                break;
            case 5:
                matrix = new Matrix("Enter size of matrix: > ");
                System.out.println("The result is: ");
                if (matrix.isSquare()) {
                    System.out.println(Operations.fmt(matrix.getDet()));
                } else {
                    System.out.println("This matrix isn't square!\n");
                }
                break;
            case 6:
                matrix = new Matrix("Enter size of matrix: > ");
                double[][] tmp = matrix.getCofactorMatrix();
                Matrix adjoint = new Matrix(tmp);
                double det = matrix.getDet();
                adjoint.transpose();
                adjoint.multiplyScalar(1/det);
                System.out.println("The result is:");
                adjoint.printFormattedMatrix();
                break;

            case 7:
                matrix = new Matrix("Enter size of matrix: > ");
                matrix.transpose();
                matrix.printMatrix();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Unknown command");
                break;
        }

    }

    private static void addTwo() {
        System.out.print("Enter size of first matrix: > ");
        int[] rowsAndColumns = (Operations.splitStringToIntegers(scanner.nextLine()));
        System.out.println("Enter first matrix:");
        matrixOne = Operations.addM(rowsAndColumns[0], rowsAndColumns[1]);
        System.out.print("Enter size of second matrix: > ");
        rowsAndColumns = (Operations.splitStringToIntegers(scanner.nextLine()));
        System.out.println("Enter second matrix:");
        matrixTwo = Operations.addM(rowsAndColumns[0], rowsAndColumns[1]);
    }

    private static void addOne() {
        System.out.print("Enter size of matrix: > ");
        int[] rowsAndColumns = (Operations.splitStringToIntegers(scanner.nextLine()));
        System.out.println("Enter matrix:");
        matrixOne = Operations.addM(rowsAndColumns[0], rowsAndColumns[1]);
    }




}

