import java.util.Arrays;


//2 Пользователь вводит сперва число n - размер будущего массива
//Программа сделает массив длиной n, а затем прочитает n вещественных чисел и запомнит их в массив
//Вычислить сумму элементов, среднее арифметическое и МЕДИАНУ.


public class ArrayGetValues {

    public static void main(String[] args){
        ArrayGetValues arr = new ArrayGetValues(new int[]{17, 14, 17, 15, 21, 31, 12, 16, 22});
        System.out.println("Сумма значений массива: "+ arr.sum+ " | Медиана массива: "+ arr.median +" | Среднее арифметическое значений массива: "+ arr.arrayMean);
    }

    int[] mainArray;
    int sum, median;
    double arrayMean;


    public ArrayGetValues(int[] inputArr){
        mainArray = inputArr;
        arraySort(mainArray);
        sum = arraySum(mainArray);
        arrayMean = (double) sum / mainArray.length;
        int medianPos = mainArray.length/2;
        if (mainArray.length%2 == 1)
            medianPos++;

        median = inputArr[medianPos];

    }

    private int arraySum(int[] arr){
        int sum =0;
        for (int i=0;i<arr.length;i++)
            sum += arr[i];
        return sum;
    }

    private void arraySort(int[] arr){
        if (arr.length>1){
            int [] left = new int[arr.length/2];
            int [] right = new int[arr.length - left.length];
            for (int i=0; i<left.length;i++)
                left[i] = arr[i];
            for (int j= left.length,i=0;i< right.length;i++)
                right[i] = arr[i+j];
            arraySort(left);
            arraySort(right);
            int [] tempArr = arrayMerge(left,right);
            for (int i=0;i< tempArr.length;i++)
                arr[i]=tempArr[i];
        }
    }

    private static int[] arrayMerge(int[] a, int[] b){
        int[] outp = new int[a.length+b.length];
        int counter = 0, i =0, j=0;
        while (i<a.length && j<b.length) {
            if (a[i] <= b[j]) {
                outp[counter] = a[i];
                i++;
            } else {
                outp[counter] = b[j];
                j++;
            }
            counter++;
        }
        while (i<a.length){
            outp[counter] = a[i];
            i++;
            counter++;
        }
        while (j<b.length){
            outp[counter] = b[j];
            j++;
            counter++;
        }

        return outp;
    }


}
