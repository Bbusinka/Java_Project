interface obiekt{
        public abstract double Pole();
        public abstract double Obwod();
    }

    abstract class Figura implements obiekt{ }

    abstract class Czworokat extends Figura{
        public double bok1,bok2,bok3,bok4,kat;
    }

    class Kwadrat extends Czworokat{
        Kwadrat(double a, double b, double c, double d ,double e) {
            bok1=a;bok2=b;bok3=c;bok4=d;kat=e;
        }

        public double Pole() {
            return bok1*bok1;
        }

        public double Obwod() {
            return 4*bok1;
        }
    }

    class Prostokat extends Czworokat{
        Prostokat(double a, double b, double c, double d ,double e) {
            bok1=a;bok2=b;bok3=c;bok4=d;kat=e;
        }

        public double Pole() {
            return bok1*bok2;
        }

        public double Obwod() {
            return 2*bok1+2*bok2;
        }
    }

   class Romb extends Czworokat{
        Romb(double a, double b, double c, double d ,double e) {
            bok1=a;bok2=b;bok3=c;bok4=d;kat=e;
        }

        public double Pole() {
            kat=Math.toRadians(kat);
            return bok1*bok1*Math.sin(kat);
        }

        public double Obwod() {
            return 4*bok1;
        }
    }

    class Kolo extends Figura{
        public double promien;

        Kolo(double a) {
            if(a>0) promien=a;
            else {
                System.out.println("Blad parametru w szeciokacie");
                a=0;
            }
        }

        public double Pole() {
            return promien*promien*Math.PI;
        }

        public double Obwod() {
            return 2*Math.PI*promien;
        }
    }

   class Pieciokat extends Figura {
        public double bok;

        Pieciokat(double a) {
            if(a>0) bok=a;
            else {
                System.out.println("Blad parametru w pieciokacie");
                a=0;
            }
        }

        public double Pole() {
            return bok*bok*Math.sqrt(25+10*Math.sqrt(5))/4;
        }

        public double Obwod() {
            return 5*bok;
        }
    }

    class Szesciokat extends Figura {
        public double bok;

        Szesciokat(double a) {
            if(a>0) bok=a;
            else {
                System.out.println("Blad parametru w szeciokacie");
                a=0;
            }
        }

        public double Pole() {
            return (bok*bok*3*Math.sqrt(3))/2;
        }

        public double Obwod() {
            return 6*bok;
        }
    }


        

public class Main {

        public static void main(String[] args){

            if(args.length<2) {
                System.out.println("Blad musisz podac wiecej argumentow");
                return;
            }

            Figura figury[]=new Figura[args[0].length()];

           
            double parametry[]=new double[args.length-1];
            for(int i=1;i<args.length;i++){
                try{
                    parametry[i-1]=Double.parseDouble(args[i]);
                    if(parametry[i-1]<=0){
                        System.out.println("Uwaga argumnet "+args[i]+" jest ujemny wiec figura nie zostanie utworzona");
                    }
                }
                catch (NumberFormatException e){
                    System.out.println("Uwaga argumnet "+args[i]+" nie jest liczba wiec figura nie zostanie utworzona");
                  
                    parametry[i-1]=0;
                }
            }

            for(int i=0,j=0;i<args[0].length();i++){
                switch(args[0].charAt(i)){

                    case 'c':
                        if(j+5<=parametry.length) {
                            switch (klasCzworokat(parametry[j], parametry[j + 1], parametry[j + 2], parametry[j + 3], parametry[j + 4])) {
                                case 0:
                                    figury[i] = new Kwadrat(parametry[j], parametry[j + 1], parametry[j + 2], parametry[j + 3], parametry[j + 4]);
                                    System.out.println("pole kwadratu : " + figury[i].Pole());
                                    System.out.println("obwod kwadratu : " + figury[i].Obwod());
                                    break;
                                case 1:
                                    figury[i] = new Prostokat(parametry[j], parametry[j + 1], parametry[j + 2], parametry[j + 3], parametry[j + 4]);
                                    System.out.println("pole prostokata : " + figury[i].Pole());
                                    System.out.println("obwod prostokata : " + figury[i].Obwod());
                                    break;
                                case 2:
                                    figury[i] = new Romb(parametry[j], parametry[j + 1], parametry[j + 2], parametry[j + 3], parametry[j + 4]);
                                    System.out.println("pole romba wynosi " + figury[i].Pole());
                                    System.out.println("obwod romba wynosi " + figury[i].Obwod());
                                    break;
                                default:
                                    System.out.println("Blad generowania, zle argumenty w czworokacie");
                                    break;
                            }
                            j += 5;
                        }
                        else {
                            System.out.println("Blad generowania za malo argumentow");
                            return;
                        }
                        break;

                    case 'o':
                        if(j+1<=parametry.length) {
                            if(parametry[j]>0) {
                                figury[i] = new Kolo(parametry[j]);
                                System.out.println("pole kola : " + figury[i].Pole());
                                System.out.println("obwod kola : " + figury[i].Obwod());
                            }
                            else {
                                System.out.println("Blad generowania ujemy lub inny nieprawidlowy argument");
                            }
                            j++;
                        }
                        else {
                            System.out.println("Blad generowania za malo argumentow");
                            return;
                        }
                        break;

                    case 'p':
                        if(j+1<=parametry.length) {
                            if(parametry[j]>0) {
                                figury[i] = new Pieciokat(parametry[j]);
                                System.out.println("pole pieciokata : " + figury[i].Pole());
                                System.out.println("obwod pieciokata : " + figury[i].Obwod());
                            }
                            else {
                                System.out.println("Blad generowania ujemy lub inny nieprawidlowy argument");
                            }
                            j++;
                        }
                        else {
                            System.out.println("Blad generowania za malo argumentow");
                            return;
                        }
                        break;

                    case 's':
                        if(j+1<=parametry.length) {
                            if(parametry[j]>0) {
                                figury[i] = new Szesciokat(parametry[j]);
                                System.out.println("pole szesciokata : " + figury[i].Pole());
                                System.out.println("obwod szesciokata : " + figury[i].Obwod());
                            }
                            else {
                                System.out.println("Blad generowania ujemy lub inny nieprawidlowy argument");
                            }
                            j++;
                        }
                        else {
                            System.out.println("Blad generowania za malo argumentow");
                            return;
                        }
                        break;

                    default:
                        System.out.println("Blad, podano zla litere okreslajaca figure");
                        break;
                }

            }
        }

	public static int klasCzworokat(double a,double b,double c,double d,double e){
            if(a==b&&b==c&&c==d&&e==90) return 0;//kwadrat
            if((a==c&&b==d&&e==90)||(a==b&&c==d&&e==90)||(a==d&&b==c&&e==90)) return 1;//prostocat
            if(a==b&&b==c&&c==d&&e>0&&e<180) return 2;//romb
            return 3;
        }

}
