import java.lang.reflect.Array;

public class Result {
    public static void main(String[] args) {
        String[][] valid = {
                {"1", "0", "1", "0"},
                {"1", "0", "1", "0"},
                {"1", "0", "1", "0"},
                {"1", "0", "1", "0"}
        };
        String[][] invalid1 = {
                {"1", "0", "1", "0"},
                {"1", "0", "1", "0"},
                {"1", "0", "1", "0"}
        };

        String[][] invalid2 = {
                {"1", "0", "1", "0"},
                {"1", "0", "1", "0"},
                {"1", "F", "1", "0"},
                {"1", "0", "1", "0"}
        };

        try {
            summ(valid);
            summ(invalid1);
            summ(invalid2);

        } catch (MyArraySizeException  | MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    public static int summ(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4) throw new MyArraySizeException("Неверное количество строк");
        for (String[] rowOfStrings : array) {
            if (rowOfStrings.length != 4) {
                throw new MyArraySizeException(String.format("Неверное количество столбцов"));
            }
        }
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                String str = array[i][j];
                try{
                    int number = Integer.parseInt(str);
                    result += number;
                }
                catch (NumberFormatException e) {

                    throw new MyArrayDataException(i, j);
                }
            }
        }
        System.out.println(result);
        return result;
}

}


class MyArraySizeException extends Exception {
    public MyArraySizeException(String message) {
        super(message);
    }
}

class MyArrayDataException extends Exception {
    public MyArrayDataException(int i, int j) {
        super("Error at (" + i + ", " + j + ")");
        ;
    }
}