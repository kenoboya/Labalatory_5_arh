import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static String directlyCodeConvertToReverseCode(String code) {
            code = code.replace("0", "one");
            code = code.replace("1", "0");
            code = code.replace("one", "1");
        return code;
    }
    public static String incrementBinary(String binaryNumber) {
        int n = binaryNumber.length();
        char[] resultArray = binaryNumber.toCharArray();

        for (int i = n - 1; i >= 0; i--) {
            if (resultArray[i] == '0') {
                resultArray[i] = '1';
                break; // Виходимо з циклу після додавання одиниці
            } else {
                resultArray[i] = '0';
            }
        }

        // Якщо всі розряди були 1, додаємо новий розряд 1 спереду
        if (resultArray[0] == '0') {
            return new String(resultArray);
        } else {
            return "1" + new String(resultArray);
        }
    }


    public static String directToComplement(String directCode) {
        int length = directCode.length();
        char[] complementCode = new char[length];

        // Проходимо по кожному біту з початку рядка до кінця
        for (int i = 0; i < length; i++) {
            char currentBit = directCode.charAt(i);

            // Якщо поточний біт 1, то переключаємо його на 0, і навпаки
            complementCode[i] = (currentBit == '1') ? '0' : '1';
        }

        // Повертаємо рядок доповняльного коду
        return new String(complementCode);
    }

    public static void shiftRight(int[] arr) {
        int last = arr[arr.length - 1];

        for (int i = arr.length - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }

        arr[0] = 0;
    }

    public static String addBinary(int[] a, String b) {
        StringBuilder result = new StringBuilder();
        int carry = 0;

        int i = a.length - 1;
        int j = b.length() - 1;

        while (i >= 0 || j >= 0) {
            int sum = carry;

            if (i >= 0) {
                sum += a[i];
                i--;
            }

            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }
            result.insert(0, sum % 2);
            carry = sum / 2;
        }

        if (carry != 0) {
            result.insert(0, carry);
        }
        return result.toString();
    }
    public static void multiplyNumberInAdditionalCode (int A, int B, String znak) {
        int gflf;
        String binaryA, binaryB, aPryamoy, bDopovnyalnoy, aDopovnyalnoy;
        int[] all = new int[8];
        int firstDot = 2;
        ArrayList<Integer> aPryamiy = new ArrayList<>();
        ArrayList<Integer> bDopovnyalniy = new ArrayList<>();
        ArrayList<Integer> aDopovnyalniy = new ArrayList<>();
        ArrayList<Integer> alll = new ArrayList<>();

        for (int i = 0; i < 6; i++){
            alll.add(i, 0);
        }

        binaryA = Integer.toBinaryString(Math.abs(A));
        binaryB = Integer.toBinaryString(Math.abs(B));

        aPryamoy = Integer.toBinaryString(Math.abs(A));
        aDopovnyalnoy = incrementBinary(directToComplement(Integer.toBinaryString(Math.abs(A))));
        bDopovnyalnoy = Integer.toBinaryString(Math.abs(B));
        String bbb = bDopovnyalnoy;

        int[] bDop = new int[4];

        for (int a = 0; a < 4; a++){
            bDop[a] = bDopovnyalnoy.charAt(a) - '0';
        }
        System.out.println("СМ\t\t\t РгB");
        for (int k = 0; k < 4; k++) {

            if (k == 0) {
                alll.set(0, 1);
                alll.set(1, 1);
            }


            // add alll and aDopovnyalnoy
            if (bDopovnyalnoy.charAt(3) == '1') {
                int[] a = new int[4];

                for (int h = 0; h < 4; h++) {
                    a[h] = alll.get(h + 2);
                }

                String result = addBinary(a, aDopovnyalnoy);

                if (result.length() > 4) {
                    for (int h = 0; h < 4; h++) {
                        alll.set(h + 2, result.charAt(h + 1) - '0');
                    }
                } else {
                    for (int h = 0; h < 4; h++) {
                        alll.set(h + 2, result.charAt(h) - '0');
                    }
                }
                gflf = 3;
            }
            shiftRight(bDop);
            bDop[0] = alll.get(alll.size() - 1);

            for (int q = 0; q < 4; q++) {
                alll.set(alll.size() - 1 - q, alll.get(alll.size() - 2 - q));
            }


            bDopovnyalnoy = "";
            for (int a = 0; a < 4; a++) {
                bDopovnyalnoy += String.valueOf(bDop[a]);
            }

            for (int i = 0; i < alll.size(); i++) {
                if (i == 2) {
                    System.out.print(",");
                }
                System.out.print(alll.get(i));
            }

            System.out.print("\t\t" + bDopovnyalnoy);

            System.out.println();
        }

        int[] ar = new int[8];

        for (int i = 0; i < 4; i++){
            ar[i] = alll.get(2 + i);
        }

        for (int i = 4; i < 8; i++) {
            ar[i] = bDopovnyalnoy.charAt(i - 4) - '0';
        }

        int[] newAr = new int[8];

        for (int i = 0; i < 8; i++) {
            if (ar[i] == 1) {
                newAr[i] = 0;
            } else {
                newAr[i] = 1;
            }
        }
        String res = addBinary(newAr, "1");
        System.out.println();
        System.out.println("Доповняльному коді: " + znak + res);
        String reverse = "";
        if(!(A > 0 && B > 0)) {
            System.out.print("Оберненому коді: " + znak);
            for(var el: newAr) {
                System.out.print(el);
                reverse+=el;
            }
        }
        System.out.println("\nПрямому коді: " + znak + directlyCodeConvertToReverseCode(reverse));
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int A, B;
        System.out.print("Введіть число А: ");
        A = scanner.nextInt();
        System.out.print("Введіть число В: ");
        B = scanner.nextInt();
        if((A > 0 && B < 0) || (A < 0 && B > 0)) {
            multiplyNumberInAdditionalCode(A,B,"11,");
        }
        else if(A < 0 && B < 0){
            multiplyNumberInAdditionalCode(A,B,"00,");
        }
        else {
            boolean pm = false;
            int b1 = 0, b2 = 0;

            if (A < 0)
                b1 = 1;
            if (B < 0)
                b2 = 1;

            if (A < 0) {
                A *= -1;
                pm = !pm;
            }
            if (B < 0) {
                B *= -1;
                pm = !pm;
            }

            System.out.println("\nДоповнялький код = двійковий код");
            System.out.println("A = " + A + " = " + b1 + "." + Integer.toBinaryString(A));

            System.out.println("B = " + B + " = " + b2 + "." + Integer.toBinaryString(B));

            int CM = 0;
            int RV = 0;
            int Q = B;
            int N = 4;

            System.out.println("\nКрок " + 1 + ":");
            System.out.println("CM = " + String.format("%04d", Integer.parseInt(Integer.toBinaryString(CM))) +
                    " RB = " + String.format("%04d", Integer.parseInt(Integer.toBinaryString(B & 0b1111))));

            for (int i = 0; i < N; i++) {
                if (i > 0) {
                    System.out.println("\nКрок " + (i + 1) + ":");
                    System.out.println("CM = " + String.format("%04d", Integer.parseInt(Integer.toBinaryString(CM))) +
                            " RB = " + String.format("%04d", Integer.parseInt(Integer.toBinaryString(RV & 0b1111))));
                }

                if ((Q & 1) == 1) {
                    CM += A;
                }

                Q >>= 1;
                RV >>= 1;
                RV += (CM << i);
                CM >>= 1;

                System.out.println("\n-------> 1");
                System.out.println("CM = " + String.format("%04d", Integer.parseInt(Integer.toBinaryString(CM))) +
                        " RB = " + String.format("%04d", Integer.parseInt(Integer.toBinaryString(RV & 0b1111))));
            }

            System.out.println("\nРезультат:");
            System.out.println("CM = " + String.format("%04d", Integer.parseInt(Integer.toBinaryString(CM))) +
                    " RB = " + String.format("%04d", Integer.parseInt(Integer.toBinaryString(RV & 0b1111))));
            System.out.print("\nC = 00,");
            System.out.print(String.format("%04d", Integer.parseInt(Integer.toBinaryString(CM)))+ String.format("%04d", Integer.parseInt(Integer.toBinaryString(RV & 0b1111))));
        }
    }

}