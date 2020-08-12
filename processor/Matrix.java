package processor;


public class Matrix extends Operations{

    public double[][] matrix;

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }


//    public Matrix(int rows, int columns) {
//        this.matrix = new double[rows][columns];
//        for (int i = 0; i < rows; i++) {
//            System.out.printf("> ");
//            double[] row = splitStringToDouble(scanner.nextLine());
//            this.matrix[i] = row;
//        }
//    }


    public void addMatrix(int rows, int columns) {
        this.matrix = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            System.out.print("> ");
            double[] row = splitStringToDouble(scanner.nextLine());
            this.matrix[i] = row;
        }
    }

    public Matrix(String string) {
        System.out.print(string);
        int[] rowsAndColumns = (Operations.splitStringToIntegers(scanner.nextLine()));
        System.out.println("Enter matrix:");
        addMatrix(rowsAndColumns[0], rowsAndColumns[1]);
    }

    public void printMatrix() {
        for (double[] doubles : this.matrix) {
            for (double aDouble : doubles) {
                System.out.print(Operations.fmt(aDouble) + " ");
            }
            System.out.print("\n");
        }
        System.out.println();
    }

    public void printFormattedMatrix() {
        for (double[] doubles : this.matrix) {
            for (double aDouble : doubles) {
                String formatted = df.format(aDouble);
                if (formatted.equals("-0")) {
                    formatted = "0";
                }
                System.out.printf("%5s ", formatted);
            }
            System.out.println("\n");
        }
        System.out.println();
    }


    public int getColumns() {
        return this.matrix[0].length;
    }



    public int getRows() {
        return this.matrix.length;
    }


    public void multiplyScalar(double multiplier) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                setVal(i, j, getVal(i, j) * multiplier);
            }
        }
    }

    public void plus(Matrix second) {
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                double result = this.getVal(i, j) + second.getVal(i, j);
                this.setVal(i, j, result);
            }
        }
    }

    public double[][] getCofactorMatrix() {
        double[][] mat = this.matrix;
        double[][] result = new double[this.getColumns()][this.getRows()];
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                double[][] tmp = deleteRowAndColumn(mat, i, j);
                int pow = (j+1 + i+1);
                result[i][j] = Math.pow(-1, pow) * getDet(tmp);
            }
        }
        return result;
    }

    public double getVal(int i, int j) {
        return this.matrix[i][j];
    }

    public void setVal(int i, int j, double val) {
        this.matrix[i][j] = val;
    }

    public double getDet() {
        double[][] mat = this.matrix;
        return getDet(mat);
    }



    private double getDet(double[][] mat) {
        if (mat.length == 1) {
            return mat[0][0];
        }
        if (matrix.length == 2) {
            return get2x2Det(mat);
        }
        double result = 0.0;
        for (int i = 0; i < mat.length; i++) {
            double x = Math.pow(-1, i) * mat[0][i] * getDet(deleteRowAndColumn(mat, 0, i));
                result += x;

        }
        return result;
    }

    private double get2x2Det(double[][] mat) {
        return (mat[0][0] * mat[1][1]) - (mat[0][1] * mat[1][0]);
    }

    private double[][] deleteRowAndColumn(double[][] mat, int row, int col) {
        int tmprow = 0;
        double[][] result = new double[mat.length - 1][mat.length - 1];
        for (int i = 0; i < mat.length; i++) {
            if (i != row) {
                int tmpcol = 0;
                for (int j = 0; j < mat.length; j++) {
                    if (j != col) {
                        result[tmprow][tmpcol] = mat[i][j];
                        tmpcol++;
                    }

                }
                tmprow++;
            }
        }
        return result;
    }

    public void transpose() {
        double[][] tmp = new double[this.getColumns()][this.getRows()];
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getRows(); j++) {
                tmp[j][i] = this.matrix[i][j];
            }
        }
        this.matrix = tmp;
    }

    public Boolean isSquare() {
        return this.getColumns() == this.getRows();
    }





}
