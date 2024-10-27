public class ComplexMatrix {
    private ComplexNumber[][] matrix;
    private int rows;
    private int cols;

    public ComplexMatrix(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Matrix dimensions must be positive integers.");
        }
        this.rows = rows;
        this.cols = cols;
        matrix = new ComplexNumber[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = new ComplexNumber(0, 0);
            }
        }
    }

    public void setElement(int row, int col, ComplexNumber value) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IndexOutOfBoundsException("Matrix index out of bounds.");
        }
        matrix[row][col] = value;
    }

    public ComplexNumber getElement(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IndexOutOfBoundsException("Matrix index out of bounds.");
        }
        return matrix[row][col];
    }

    public ComplexMatrix add(ComplexMatrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Matrix dimensions must match for addition.");
        }
        ComplexMatrix result = new ComplexMatrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.setElement(i, j, this.getElement(i, j).add(other.getElement(i, j)));
            }
        }
        return result;
    }

    public ComplexMatrix subtract(ComplexMatrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Matrix dimensions must match for subtraction.");
        }
        ComplexMatrix result = new ComplexMatrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.setElement(i, j, this.getElement(i, j).subtract(other.getElement(i, j)));
            }
        }
        return result;
    }

    public ComplexMatrix multiply(ComplexMatrix other) {
        if (this.cols != other.rows) {
            throw new IllegalArgumentException("Matrix dimensions are not suitable for multiplication.");
        }
        ComplexMatrix result = new ComplexMatrix(this.rows, other.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                ComplexNumber sum = new ComplexNumber(0, 0);
                for (int k = 0; k < this.cols; k++) {
                    sum = sum.add(this.getElement(i, k).multiply(other.getElement(k, j)));
                }
                result.setElement(i, j, sum);
            }
        }
        return result;
    }

    public ComplexMatrix transpose() {
        ComplexMatrix result = new ComplexMatrix(cols, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.setElement(j, i, this.getElement(i, j));
            }
        }
        return result;
    }

    public ComplexNumber determinant() {
        if (rows != cols) {
            throw new IllegalArgumentException("Matrix must be square to calculate determinant.");
        }
        return calculateDeterminant(matrix, rows);
    }

    private ComplexNumber calculateDeterminant(ComplexNumber[][] matrix, int n) {
        if (n == 1) {
            return matrix[0][0];
        }
        if (n == 2) {
            return matrix[0][0].multiply(matrix[1][1]).subtract(matrix[0][1].multiply(matrix[1][0]));
        }

        ComplexNumber det = new ComplexNumber(0, 0);
        for (int i = 0; i < n; i++) {
            ComplexNumber[][] subMatrix = new ComplexNumber[n - 1][n - 1];
            for (int j = 1; j < n; j++) {
                for (int k = 0, col = 0; k < n; k++) {
                    if (k == i) continue;
                    subMatrix[j - 1][col++] = matrix[j][k];
                }
            }
            det = det.add(matrix[0][i].multiply(calculateDeterminant(subMatrix, n - 1))
                    .multiply(new ComplexNumber(Math.pow(-1, i), 0)));
        }
        return det;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ComplexNumber[] row : matrix) {
            for (ComplexNumber element : row) {
                sb.append(element).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
