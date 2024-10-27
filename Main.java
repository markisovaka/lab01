public class Main {
    public static void main(String[] args) {
        try {
            ComplexMatrix matrix1 = new ComplexMatrix(2, 2);
            matrix1.setElement(0, 0, new ComplexNumber(2, 3));
            matrix1.setElement(0, 1, new ComplexNumber(4, -1));
            matrix1.setElement(1, 0, new ComplexNumber(0, -2));
            matrix1.setElement(1, 1, new ComplexNumber(1, 1));

            ComplexMatrix matrix2 = new ComplexMatrix(2, 2);
            matrix2.setElement(0, 0, new ComplexNumber(3, -1));
            matrix2.setElement(0, 1, new ComplexNumber(-2, 2));
            matrix2.setElement(1, 0, new ComplexNumber(1, -1));
            matrix2.setElement(1, 1, new ComplexNumber(2, 3));

            System.out.println("Matrix 1:\n" + matrix1);
            System.out.println("Matrix 2:\n" + matrix2);

            ComplexMatrix sumMatrix = matrix1.add(matrix2);
            System.out.println("Matrix 1 + Matrix 2:\n" + sumMatrix);

            ComplexMatrix productMatrix = matrix1.multiply(matrix2);
            System.out.println("Matrix 1 * Matrix 2:\n" + productMatrix);

            ComplexMatrix transposedMatrix = matrix1.transpose();
            System.out.println("Transposed Matrix 1:\n" + transposedMatrix);

            ComplexNumber detMatrix1 = matrix1.determinant();
            System.out.println("Determinant of Matrix 1: " + detMatrix1);
        } catch (IllegalArgumentException | ArithmeticException | IndexOutOfBoundsException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
