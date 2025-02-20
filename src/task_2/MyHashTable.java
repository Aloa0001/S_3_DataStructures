package task_2;

import java.util.Arrays;

public class MyHashTable {

    private int[] array;
    private double maxLoadFactor;
    private double numberOfElements;

    public MyHashTable() {
        array = new int[10];
        Arrays.fill(array, 0);
        maxLoadFactor = 0.75;
    }

    public MyHashTable(int size) {
        array = new int[size];
        Arrays.fill(array, 0);
        maxLoadFactor = 0.5;
    }


    /**********************************/

    /**
     * LINEAR PROBING IMPLEMENTATION
     */
    public void addLinear(int value) {
        if (loadFactor() > maxLoadFactor) {
            resize();
        }
        int i = 0;
        int index = hashFunctionLinear(value, i);
        while (array[index] != 0) {
            index = hashFunctionLinear(value, ++i);
            if (i > 3) {
                resize();
            }
        }
        array[index] = value;
        numberOfElements++;
    }

    private int hashFunctionLinear(int key, int i) {
        return (key + collisionResolutionLinearPolicy(i)) % 10;
    }

    private void resize() {
        int[] arr = Arrays.copyOf(array, array.length);
        array = Arrays.copyOf(array, array.length * 2);
        Arrays.fill(array, 0);
        reHash(arr);
    }

    private void reHash(int[] arr) {
        System.out.println(Arrays.toString(arr));
        for (int value : arr) {
            int j = 0;
            int newIndex = hashFunctionLinear(value, j);
            while (array[newIndex] != 0) {
                newIndex = hashFunctionLinear(value, ++j);
            }
            array[newIndex] = value;
        }
    }
    /*******************************************/


    /**
     * QUADRATIC PROBING IMPLEMENTATION
     */
    public void addQuadratic(int value) {
        if (loadFactor() > maxLoadFactor) {
            resizeQuadratic();
        }
        int i = 0;
        int index = hashFunctionQuadratic(value, i);
        while (array[index] != 0) {
            index = hashFunctionQuadratic(value, ++i);
            if (i > 3) {
                resizeQuadratic();
            }
        }
        array[index] = value;
        numberOfElements++;
    }

    private int hashFunctionQuadratic(int key, int i) {
        return (key + collisionResolutionQuadraticPolicy(i)) % 10;
    }

    private void resizeQuadratic() {
        int[] arr = Arrays.copyOf(array,array.length+1);
        array = Arrays.copyOf(array, array.length * 2);
        Arrays.fill(array, 0);
        reHashQuadratic(arr);
    }

    private void reHashQuadratic(int[] arr) {
        for (int value : arr) {
            int j = 0;
            int newIndex = hashFunctionQuadratic(value, j);
            while (array[newIndex] != 0) {
                newIndex = hashFunctionLinear(value, ++j);
            }
            array[newIndex] = value;
        }
    }
    /*******************************************/

    /**
     * FUNCTION 2 LINEAR IMPLEMENTATION
     */
    public void addLinearH2(int value) {
        if (loadFactor() > maxLoadFactor) {
            resizeH2();
        }
        int i = 0;
        int index = hashF2Linear(value, i);
        while (array[index] != 0) {
            index = hashF2Linear(value, ++i);
            if (i > 3) {
                resizeH2();
            }
        }
        array[index] = value;
        numberOfElements++;
    }

    private int hashF2Linear(int key, int i) {
        return 7 - (key + collisionResolutionLinearPolicy(i)) % 7;
    }
    private void resizeH2() {
        int[] arr = Arrays.copyOf(array, array.length);
        array = Arrays.copyOf(array, array.length * 2);
        Arrays.fill(array, 0);
        reHashH2(arr);
    }

    private void reHashH2(int[] arr) {
        for (int value : arr) {
            int j = 0;
            int newIndex = hashF2Linear(value, j);
            while (array[newIndex] != 0) {
                newIndex = hashFunctionLinear(value, ++j);
            }
            array[newIndex] = value;
        }
    }
    /********************************************/

    /**
     * FUNCTION 2 QUADRATIC IMPLEMENTATION
     */
    public void addQuadraticH2(int value) {
        if (loadFactor() > maxLoadFactor) {
            resizeQuadratic();
        }
        int i = 0;
        int index = hashF2Quadratic(value, i);
        while (array[index] != 0) {
            index = hashF2Quadratic(value, ++i);
            if (i > 3) {
                resizeQuadraticH2();
            }
        }
        array[index] = value;
        numberOfElements++;
    }

    private int hashF2Quadratic(int key, int i) {
        return 7 - (key + collisionResolutionQuadraticPolicy(i)) % 7;
    }
    private void resizeQuadraticH2() {
        int[] arr = Arrays.copyOf(array, array.length);
        array = Arrays.copyOf(array, array.length * 2);
        Arrays.fill(array, 0);
        reHashQuadraticH2(arr);
    }

    private void reHashQuadraticH2(int[] arr) {
        for (int value : arr) {
            int j = 0;
            int newIndex = hashF2Quadratic(value, j);
            while (array[newIndex] != 0) {
                newIndex = hashFunctionLinear(value, ++j);
            }
            array[newIndex] = value;
        }
    }
    /********************************************/


    /**
     * COLLISION RESOLUTION POLICIES
     */
    private int collisionResolutionLinearPolicy(int i) {
        return i;
    }

    private int collisionResolutionQuadraticPolicy(int i) {
        return (int) Math.pow(i, 2);
    }

    private double loadFactor() {
        return numberOfElements/array.length;
    }

    /**
     * TEST DRIVER
     *
     * @param args .
     */
    public static void main(String[] args) {
        MyHashTable hashLinearProbing = new MyHashTable();
        MyHashTable hashQuadraticProbing = new MyHashTable();
        MyHashTable hashLinearProbingF2 = new MyHashTable();
        MyHashTable f2Rehash = new MyHashTable(10);
        MyHashTable hashQuadraticProbingF2 = new MyHashTable();
        int[] arr = {4371, 1323, 6173, 4199, 4344, 9679, 1989};

        for (int x : arr) {
            hashLinearProbing.addLinear(x);
            hashQuadraticProbing.addQuadratic(x);
            hashLinearProbingF2.addLinearH2(x);
            hashQuadraticProbingF2.addQuadraticH2(x);
            f2Rehash.addLinearH2(x);
        }

        /** TEST TASK 2 */
        System.out.println("\nTEST FOR INITIAL INPUT :           "+ Arrays.toString(arr));
        System.out.println("\nLINEAR PROBING hashFunction1 :     "+Arrays.toString(hashLinearProbing.array));
        System.out.println("\nQUADRATIC PROBING hashFunction1 :  "+Arrays.toString(hashQuadraticProbing.array));
        System.out.println("\n\n*** Hash Function h2(x) = 7 − (x mod 7) *** ");
        System.out.println("\nLINEAR PROBING hashFunction2 :     "+Arrays.toString(hashLinearProbingF2.array));
        System.out.println("\nLINEAR PROBING hashFunction2 resized+rehashed:\n"
                +"                                  "+Arrays.toString(f2Rehash.array)+
                "\n\nThe resize+rehash was triggered by the 6th element, using a load factor of 0.5 ");
        System.out.println("\nQUADRATIC PROBING hashFunction2   "+Arrays.toString(hashQuadraticProbingF2.array));
    }
}
