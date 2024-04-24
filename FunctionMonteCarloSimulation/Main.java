//Autor: Valeriia Loichyk
//Album: 269399
import java.util.Scanner;

public class Main {
   public static double x;
   public static double y ;
   public static int count=0;
    public static void main(String[] args) {
        System.out.println("Podaj numer funkcji:");
        int f = ChekInt();
        switch (f){
            case 1:{ int a = 0;
                double b = 8;
                int M = 2;
                for(int n=50; n<=5000; n=n+50)
                {
                    for(int k=0; k<50; k++)
                    {
                        for (int i=0; i<n; i++){
                            x = Math.random() * (b-a) +a;
                            y =Math.random() * M;

                            if (y <= Math.cbrt(x))
                                count++;
                        }
                        double wynik=count*(b-a)*M/(double)n;
                        System.out.print(wynik+";");
                        count=0;
                    }
                    System.out.println();
                } break;}
            case 2:{ int a = 0;
                double b = Math.PI;
                int M = 1;
                for(int n=50; n<=5000; n=n+50)
                {
                    for(int k=0; k<50; k++)
                    {
                        for (int i=0; i<n; i++){
                            x = Math.random() * (b-a) +a;
                            y =Math.random() * M;

                            if (y <= Math.sin(x))
                                count++;
                        }
                        double wynik=count*(b-a)*M/(double)n;
                        System.out.print(wynik+";");
                        count=0;
                    }
                    System.out.println();
                } break;}
            case 3:{ int a = 0;
                double b = 1;
                int M = 8;
                for(int n=50; n<=5000; n=n+50)
                {
                    for(int k=0; k<50; k++)
                    {
                        for (int i=0; i<n; i++){
                            x = Math.random() * (b-a) +a;
                            y =Math.random() * M;
                            if (y <= 4*x*Math.pow(1-x,3.0))
                                count++;
                        }
                        double wynik=count*(b-a)*M/(double)n;
                        System.out.print(wynik+";");
                        count=0;
                    }
                    System.out.println();
                } break;}
            case 4:{ int a = 0;
                double b = 2;
                int M = 2;
                for(int n=50; n<=5000; n=n+50)
                {
                    for(int k=0; k<50; k++)
                    {
                        for (int i=0; i<n; i++){
                            x = Math.random() * (b-a) +a;
                            y =Math.random() * M;

                            if (Math.sqrt(Math.pow((x-1),2)+Math.pow((y-1),2))<=1)
                                count++;
                        }
                        double wynik=count*(b-a)*M/(double)n;
                        System.out.print(wynik+";");
                        count=0;
                    }
                    System.out.println();
                } break;}
        }

    }
    public static int ChekInt(){
        int c;
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) { c=sc.nextInt(); if(c<=0 || c>4){
            System.out.println("Liczba jest nieprawidłowa\n" + "Wprowadź ponownie: ");
            c=ChekInt();
        }}
        else {System.out.print("Nieprawidłowe dane!\n" + "Wprowadź ponownie: "); c=ChekInt();}
        return c;
    }
}
