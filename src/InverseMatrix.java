import java.util.*;

public class InverseMatrix {

    public static double[][] TMatrix(double[][] Matrix){
    double [][] T_Matrix = new double[Matrix.length][Matrix.length];
        for(int i =0; i<Matrix.length; i++){
            for(int j = 0; j<Matrix.length ; j++){
                T_Matrix[i][j]=Matrix[j][i];
            }
        }
        return T_Matrix;
    }
    public static double Det(double[][] Matrix){
        if(Matrix.length ==2){
            return Matrix[0][0]*Matrix[1][1]-Matrix[0][1]*Matrix[1][0];
        }
        double det= 0;
        for(int i = 0; i<Matrix.length; i++){
                det += Math.pow(-1,i)*Matrix[0][i] * Det(subMatrix(Matrix,0,i));
            }
        return det;
    }
    public static double[][] Cofactor(double[][] TMatrix){
        double [][] CMatrix = new double[TMatrix.length][TMatrix.length];
        if(TMatrix.length==2){
            CMatrix[0][1] = -TMatrix[0][1];
            CMatrix[1][0] = -TMatrix[1][0];

        }
        for(int i = 0; i<TMatrix.length; i++){
            for(int j =0 ; j< TMatrix.length; j++){
            CMatrix[i][j] = Math.pow(-1,i+j) * Det(subMatrix(TMatrix,i,j));
            }
        }return CMatrix;
    }


    public static double[][] subMatrix(double[][] Matrix,int row, int col){
        double[][] subMatrix = new double[Matrix.length-1][Matrix.length-1];
       for(int i = 0 ; i<Matrix.length; i++){
           for(int j=0; j<Matrix.length; j++){
               if(i == row || j==col){
                   continue;
               }
               if(i<row&&j<col){
                   subMatrix[i][j]=Matrix[i][j];
               }
               else if(i>row&&j<col){
                   subMatrix[i-1][j]=Matrix[i][j];
               }
               else if(i<row&&j>col){
                   subMatrix[i][j-1]=Matrix[i][j];
               }
               else if(i>row && j>col) {
                   subMatrix[i - 1][j - 1] = Matrix[i][j];
               }
           }
       }
       return subMatrix;
    }
    public static double[][] IMatrix(double[][] Matrix){
        double[][] IMatrix = new double[Matrix.length][Matrix.length];
        double[][] TMatrix = TMatrix(Matrix);
        double[][] CMatrix = Cofactor(TMatrix);
        double det = Det(Matrix);
        for(int i = 0; i<CMatrix.length; i++){
            for(int j = 0;  j <CMatrix.length; j++){
                IMatrix[i][j]=1/det * CMatrix[i][j];
            }
        }
        return IMatrix;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("몇 차 행렬을 만드시겠습니까? (2 or 3)");
        int dem = sc.nextInt();
    double[][] Matrix = new double[dem][dem];
        for(int i =0; i<Matrix.length; i++){
            for (int j =0 ;j<Matrix.length; j++){
                System.out.println(i+1+"행"+j+1+"열에 입력할 값? :");
                Matrix[i][j] = sc.nextDouble();
            }
        }
        System.out.println("입력한 행렬값 :");
        for(int i =0; i< Matrix.length; i++){
            System.out.println(Arrays.toString(Matrix[i]));
        }
        System.out.println("전치행렬 값");
        for(int i =0; i< Matrix.length; i++){
            System.out.println(Arrays.toString(TMatrix(Matrix)[i]));
        }
        System.out.println("여인자 값");
        for(int i = 0 ; i<Matrix.length; i++){
            System.out.println(Arrays.toString(Cofactor(TMatrix(Matrix))[i]));
        }
        System.out.println("역행렬 값");
        for(int i = 0 ; i< Matrix.length; i++){
            System.out.println(Arrays.toString(IMatrix(Matrix)[i]));
        }


    }
}
