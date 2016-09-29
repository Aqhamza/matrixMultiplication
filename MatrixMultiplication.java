/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrixmultiplication;

/**
 *
 * @author Abdul Qadir
 */
import java.util.Scanner;
 
class MatrixMultiplication
{
   public static void main(String args[])
   {
       /**Declaring Variables**/
      int m, n, p, q, sum = 0, x, y, z;
 
      Scanner scan = new Scanner(System.in);
      /**Accepting Order for First Matrices**/
      System.out.println("Enter Number of Rows:");
      m = scan.nextInt();
      System.out.println("Enter Number of Coloumn:");
      n = scan.nextInt();
 
      int first[][] = new int[m][n];
     /**Filling Matrices**/
      System.out.println("Fill the First Matrix");
 
      for ( x = 0 ; x < m ; x++ )
         for ( y = 0 ; y < n ; y++ )
            first[x][y] = scan.nextInt();
        /**Accepting Order for Second Matrices**/
     System.out.println("Enter Number of Rows:");
      p = scan.nextInt();
      System.out.println("Enter Number of Coloumn:");
      q = scan.nextInt();
 
      if ( n != p )
         System.out.println("Erro!!!! WRONG ORDER");
      else
      {
         int second[][] = new int[p][q];
         int multiply[][] = new int[m][q];
         /**Filling Matrices**/
         System.out.println("Fill the Second Matrix");
 
         for ( x = 0 ; x < p ; x++ )
            for ( y = 0 ; y < q ; y++ )
               second[x][y] = scan.nextInt();
        /**Multiplying Matrices**/
         for ( x = 0 ; x < m ; x++ )
         {
            for ( y = 0 ; y < q ; y++ )
            {   
               for ( z = 0 ; z < p ; z++ )
               {
                  sum = sum + first[x][z]*second[z][y];
               }
 
               multiply[x][y] = sum;
               sum = 0;
            }
         }
 
         System.out.println("Result of Matrix Multiplication....\n");
         /**Printing Result**/
         for ( x = 0 ; x < m ; x++ )
         {
            for ( y = 0 ; y < q ; y++ )
               System.out.print(multiply[x][y]+"\t");
 
            System.out.print("\n");
         }
      }
   }
}
