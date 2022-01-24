import java.util.Arrays;

public class ThreadHomework {
    public static void main(String[] args) {
        firstMethod();
        secondMethod();
    }

    public static void firstMethod() {
        int size = 10_000_000;
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }
        long startTime = System.currentTimeMillis();
        calculate(arr);
        System.out.println("One thread time: " + (System.currentTimeMillis() - startTime) + " ms.");
//        System.out.println(Arrays.toString(arr));
    }

    public static void secondMethod() {
        int size = 10_000_000;
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }
        long startTime = System.currentTimeMillis();
        splitAndMerge(arr);
        System.out.println("Two thread time: " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    private static void splitAndMerge(float[] initialArray) {
//        System.out.println(Arrays.toString(initialArray));
        final int halfSize = initialArray.length / 2;
        float[] leftHalf = new float[halfSize];
        float[] rightHalf = new float[halfSize];
        System.arraycopy(initialArray, 0, leftHalf, 0, halfSize);
        System.arraycopy(initialArray, halfSize, rightHalf, 0, halfSize);
//        System.out.println(Arrays.toString(leftHalf));
//        System.out.println(Arrays.toString(rightHalf));
        Thread thread1 = new Thread(() -> {
            calculate(leftHalf);
            ;
        });
        Thread thread2 = new Thread(() -> {
            calculate(rightHalf);
            ;
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        float[] mergedArray = new float[initialArray.length];
        System.arraycopy(leftHalf, 0, mergedArray, 0, halfSize);
        System.arraycopy(rightHalf, 0, mergedArray, halfSize, halfSize);
//        System.out.println(Arrays.toString(mergedArray));
    }

    private static void calculate(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}

