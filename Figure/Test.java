interface obiekt1{
    public abstract double Pole(double a);
    public abstract double Obwod(double a);
}

interface obiekt2{
    public abstract double Pole(double a,double b);
    public abstract double Obwod(double a,double b);
}

class Figura{
    public enum Figura1 implements obiekt1{
        KWADRAT{
            public double bok1;
            public double Pole(double a) {return a*a;}
            public double Obwod(double a) {return 4*a;}
        },
        KOLO{
            public double bok1;
            public double Pole(double a) {return Math.PI*a*a;}
            public double Obwod(double a) {return 2*a*Math.PI;}
        },
        SZESCIOKAT{
            public double bok1;
            public double Pole(double a) {
                return (a*a*3*Math.sqrt(3))/2;
            }
            public double Obwod(double a) {
                return 6*a;
            }
        },
        PIECIOKAT{
            public double bok1;
            public double Pole(double a) {
                return a*a*Math.sqrt(25+10*Math.sqrt(5))/4;
            }
            public double Obwod(double a) {
                return 5*a;
            }
        }
    }
    public enum Figura2 implements obiekt2{
        PROSTOKAT{
            public double bok1,bok2;
            public double Pole(double a,double b) {
                return a*b;
            }
            public double Obwod(double a,double b) {
                return 2*a+2*b;
            }
        },
        ROMB{
            public double bok1,bok2;
            public double Pole(double a,double b) {
		b=Math.toRadians(b);
                return a*a*Math.sin(b);
            }
            public double Obwod(double a,double b) {
                return 4*a;
            }
        }
    }
  
}


public class Test {


    public static int klasyfikujCzworokat(double a,double b,double c,double d,double e){
        if(a==b&&b==c&&c==d&&e==90) return 0;//kwadrat
        if((a==c&&b==d&&e==90)||(a==b&&c==d&&e==90)||(a==d&&b==c&&e==90)) return 1;//prostoat
        if(a==b&&b==c&&c==d&&e>0&&e<180) return 2;//romb
        return 3;
    }

    public static double znajdzDrugiBokProstokata(double a,double b,double c){
        if(a!=b) return b;
        return c;
    }


    public static void main(String[] args){

  
        if(args.length<2) {
            System.out.println("Blad musisz podac wiecej argumentow");
            return;
        }

        
        double dobreParametry[]=new double[args.length-1];
        for(int i=1;i<args.length;i++){
            try{
                dobreParametry[i-1]=Double.parseDouble(args[i]);
                if(dobreParametry[i-1]<=0){
                    System.out.println("Uwaga argumnet "+args[i]+" jest ujemny wiec figura nie zostanie utworzona");
                }
            }
            catch (NumberFormatException e){
                System.out.println("Uwaga argumnet "+args[i]+" nie jest liczba wiec figura nie zostanie utworzona");
             
                dobreParametry[i-1]=0;
            }
        }

        for(int i=0,j=0;i<args[0].length();i++){
            switch(args[0].charAt(i)){

                case 'c':
                    if(j+5<=dobreParametry.length) {
                        switch (klasyfikujCzworokat(dobreParametry[j], dobreParametry[j + 1], dobreParametry[j + 2], dobreParametry[j + 3], dobreParametry[j + 4])) {
                            case 0:
                               
                                if(dobreParametry[j]>0) {
                                    System.out.println("pole kwadratu : " + Figura.Figura1.KWADRAT.Pole(dobreParametry[j]));
                                    System.out.println("obwod kwadratu : " + Figura.Figura1.KWADRAT.Obwod(dobreParametry[j]));
                                }
                                else{
                                    System.out.println("Pole kwadratu nie zostanie wygenerowane");
                                }
                                break;
                            case 1:
                                
                                System.out.println("pole prostokata : " + Figura.Figura2.PROSTOKAT.Pole(dobreParametry[j],znajdzDrugiBokProstokata(dobreParametry[j],dobreParametry[j+1],dobreParametry[j+2])));//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                                System.out.println("obwod prostokata : " + Figura.Figura2.PROSTOKAT.Obwod(dobreParametry[j],znajdzDrugiBokProstokata(dobreParametry[j],dobreParametry[j+1],dobreParametry[j+2])));
                                break;
                            case 2:
                                
                                System.out.println("pole romba wynosi " + Figura.Figura2.ROMB.Pole(dobreParametry[j],dobreParametry[j+4]));
                                System.out.println("obwod romba wynosi " + Figura.Figura2.ROMB.Obwod(dobreParametry[j],dobreParametry[j+4]));;
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
                    if(j+1<=dobreParametry.length) {
                        if(dobreParametry[j]>0) {
                           
                            System.out.println("pole kola : " +Figura.Figura1.KOLO.Pole(dobreParametry[j]));
                            System.out.println("obwod kola : " + Figura.Figura1.KOLO.Obwod(dobreParametry[j]));
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
                    if(j+1<=dobreParametry.length) {
                        if(dobreParametry[j]>0) {
                            
                            System.out.println("pole pieciokata : " + Figura.Figura1.PIECIOKAT.Pole(dobreParametry[j]));
                            System.out.println("obwod pieciokata : " + Figura.Figura1.PIECIOKAT.Obwod(dobreParametry[j]));
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
                    if(j+1<=dobreParametry.length) {
                        if(dobreParametry[j]>0) {
                           
                            System.out.println("pole szesciokata : " + Figura.Figura1.SZESCIOKAT.Pole(dobreParametry[j]));
                            System.out.println("obwod szesciokata : " + Figura.Figura1.SZESCIOKAT.Obwod(dobreParametry[j]));
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

}
