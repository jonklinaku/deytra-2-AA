package com.company;

import java.util.Arrays;

class Main
{
    public static boolean isSubsetExist(int[] S, int n, int a, int b, int c, int[] arr)
    {
        if (a == 0 && b == 0 && c == 0) {
            return true;
        }

        if (n < 0) {
            return false;
        }

        boolean A = false;
        if (a - S[n] >= 0)
        {
            arr[n] = 1;
            A = isSubsetExist(S, n - 1, a - S[n], b, c, arr);
        }

        boolean B = false;
        if (!A && (b - S[n] >= 0))
        {
            arr[n] = 2;
            B = isSubsetExist(S, n - 1, a, b - S[n], c, arr);
        }

        boolean C = false;
        if ((!A && !B) && (c - S[n] >= 0))
        {
            arr[n] = 3;
            C = isSubsetExist(S, n - 1, a, b, c - S[n], arr);
        }

        return A || B || C;
    }

    public static void partition(int[] S)
    {
        int sum = Arrays.stream(S).sum();

        int[] A = new int[S.length];

        boolean result = (S.length >= 3) &&
                (sum % 3) == 0 &&
                isSubsetExist(S, S.length - 1, sum/3, sum/3, sum/3, A);

        if (!result)
        {
            System.out.print("3-Partition of set is not possible");
            return;
        }

        for (int i = 0; i < 3; i++)
        {
            System.out.print("Partition of set " + (char)(65+i) + " is {");
            String ans = "";
            for (int j = 0; j < S.length; j++)
            {
                if (A[j] == i + 1) {
                    ans+=S[j] +",";
                }
            }

            System.out.print(ans.substring(0,ans.length()-1)+"}"+System.lineSeparator());
        }
        System.out.print("With the sum being: "+ sum/3);
    }

    public static void main(String[] args)
    {
        int[] S = { 7, 3, 2, 1, 5, 4, 8 };
       //int[] S = { 8, 3, 2, 2, 5, 4, 9 };
        //int[] S = { 7, 3, 2, 1, 5, 4, 8,7, 3, 2, 1, 5, 4, 8,7, 3, 2, 1, 5, 4, 8,7, 3, 2, 1, 5, 4, 8 };
        partition(S);
    }
}
