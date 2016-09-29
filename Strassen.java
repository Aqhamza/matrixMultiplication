/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strassen;

/**
 *
 * @author Abdul Qadir
 */
import java.util.Scanner;
 /****/
/** Class Strassen **/
public class Strassen
{
    /** Function to multiply of matrices **/
    public int[][] mul(int[][] A, int[][] B)
    {       
        int x = A.length;
        int[][] res = new int[x][x];
        
        if (x == 1)
            res[0][0] = A[0][0] * B[0][0];
        else
        {
            int[][] A11 = new int[x/2][x/2];
            int[][] A12 = new int[x/2][x/2];
            int[][] A21 = new int[x/2][x/2];
            int[][] A22 = new int[x/2][x/2];
            int[][] B11 = new int[x/2][x/2];
            int[][] B12 = new int[x/2][x/2];
            int[][] B21 = new int[x/2][x/2];
            int[][] B22 = new int[x/2][x/2];
 
            /** Dividing matrix A into 4 halves **/
            div(A, A11, 0 , 0);
            div(A, A12, 0 , x/2);
            div(A, A21, x/2, 0);
            div(A, A22, x/2, x/2);
            /** Dividing matrix B into 4 halves **/
            div(B, B11, 0 , 0);
            div(B, B12, 0 , x/2);
            div(B, B21, x/2, 0);
            div(B, B22, x/2, x/2);
 
            /** 
              Strassen Algorithm
              P1 = (A11 + A22)(B11 + B22)
              p2 = (A21 + A22) B11
              P3 = A11 (B12 - B22)
              P4 = A22 (B21 - B11)
              P5 = (A11 + A12) B22
              P6 = (A21 - A11) (B11 + B12)
              P7 = (A12 - A22) (B21 + B22)
            **/
 
            int [][] P1 =  mul(add(A11, A22), add(B11, B22));
            int [][] P2 = mul(add(A21, A22), B11);
            int [][] P3 = mul(A11, sub(B12, B22));
            int [][] P4 = mul(A22, sub(B21, B11));
            int [][] P5 = mul(add(A11, A12), B22);
            int [][] P6 = mul(sub(A21, A11), add(B11, B12));
            int [][] P7 = mul(sub(A12, A22), add(B21, B22));
 
            /**
              C11 = P1 + P4 - P5 + P7
              C12 = P3 + P5
              C21 = P2 + P4
              C22 = P1 - P2 + P3 + P6
            **/
            int [][] C11 = add(sub(add(P1, P4), P5), P7);
            int [][] C12 = add(P3, P5);
            int [][] C21 = add(P2, P4);
            int [][] C22 = add(sub(add(P1, P3), P2), P6);
 
            /** join 4 halves into one result matrix **/
            join(C11, res, 0 , 0);
            join(C12, res, 0 , x/2);
            join(C21, res, x/2, 0);
            join(C22, res, x/2, x/2);
        }
        /** return result **/    
        return res;
    }
    /** Function to subtraction of two matrices **/
    public int[][] sub(int[][] A, int[][] B)
    {
        int y = A.length;
        int[][] C = new int[y][y];
        for (int i = 0; i < y; i++)
            for (int j = 0; j < y; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }
    /** Function to addition of two matrices **/
    public int[][] add(int[][] A, int[][] B)
    {
        int z = A.length;
        int[][] C = new int[z][z];
        for (int i = 0; i < z; i++)
            for (int j = 0; j < z; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }
    /** Function to divide main matrix into sub matrices **/
    public void div(int[][] P, int[][] C, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                C[i1][j1] = P[i2][j2];
    }
    /** Function to join main matrices into sub matrix **/
    public void join(int[][] C, int[][] P, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                P[i2][j2] = C[i1][j1];
    }    
    /** Main function **/
    public static void main (String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Strassen Matrix Multiplication Algorithm\n");
        /** Make an object of Strassen class **/
        Strassen s = new Strassen();
       /** Enter Dimension of Matrices**/
        System.out.println("Enter Order For Matrices :");
        int N = scan.nextInt();
        /** Taking Values for two 2d Matrices **/
        System.out.println("Enter Matrix 1 of Order "+N+"\n");
        int[][] A = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                A[i][j] = scan.nextInt();
 
        System.out.println("Enter Matrix 2 of Order "+N+"\n");
        int[][] B = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                B[i][j] = scan.nextInt();
 
        int[][] C = s.mul(A, B);
        /** Multiplying Matrices**/
        System.out.println("\nProduct of Matrices A and  B : ");
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                System.out.print(C[i][j] +" ");
            System.out.println();
        }
 
    }
}
