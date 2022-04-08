package algorythm.Tools;

public class RotateArray {

    static int[][] rotate90(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] rotate = new int[m][n];

        for (int i = 0; i < rotate.length; i++) {
            for (int j = 0; j < rotate[i].length; j++) {
                rotate[i][j] = arr[n - 1 - j][i];
            }
        }

        return rotate;
    }

    static int[][] rotate180(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] rotate = new int[n][m];

        for (int i = 0; i < rotate.length; i++) {
            for (int j = 0; j < rotate[i].length; j++) {
                rotate[i][j] = arr[n - 1 - i][m - 1 - j];
            }
        }

        return rotate;
    }

    static int[][] rotate270(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] rotate = new int[m][n];

        for (int i = 0; i < rotate.length; i++) {
            for (int j = 0; j < rotate[i].length; j++) {
                rotate[i][j] = arr[j][m - 1 - i];
            }
        }

        return rotate;
    }

    static int[][] rotate(int[][] arr, int degree) {
        int[][] rotate;
        int n = arr.length;
        int m = arr[0].length;

        switch (degree) {
            case 90:
            case 270:
                rotate = new int[m][n];
                break;
            case 180:
                rotate = new int[n][m];
                break;
            default:
                throw new IllegalArgumentException();
        }

        for (int i = 0; i < rotate.length; i++) {
            for (int j = 0; j < rotate[i].length; j++) {
                switch (degree) {
                    case 90:
                        rotate[i][j] = arr[n - 1 - j][i];
                        break;
                    case 180:
                        rotate[i][j] = arr[n - 1 - i][m - 1 - j];
                        break;
                    case 270:
                        rotate[i][j] = arr[j][m - 1 - i];
                        break;
                }
            }
        }

        return rotate;
    }
}
