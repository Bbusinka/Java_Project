

import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;

public class Symulator {

    SecureRandom secureRandom = new SecureRandom(); //wykorzystanie randomu SecureRandom

    int n; //ilość urn

    boolean flag_Bn; //pierwsza kolizja

    int Bn; //moment, w którym pierwszy raz w urnie pojawiają się 2 kuli

    int Un; // ilość pustych urn

    int count_empty; //ilość nie pustych urn

    int count_fill; //ilość nie pustych urn

    int Ln;  //maksymalna liczba kul w urnie po wrzuceniu n kul

    int Cn; //minimalna liczba rzutów, po której w każdej z urn jest co najmniej jedna kula

    int Dn; //minimalna liczba rzutów, po której w każdej z urn są co najmniej dwie kule

    int DnCn; //liczba rzutów od momentu Cn

    int p;  //losowa pozycja urn

    int test;  //liczba prób

    int[] urny;


    public void Dodanie_kul() {
        FileWriter nFile = null;
        try {
            nFile = new FileWriter("wyniki.txt");
            nFile.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int m=1000;
        for (n = 1000; n <= 100000; n += 1000) {
                for (int k = 1; k <= 50; k++) {
                flag_Bn = false;
                urny = new int[n];
                Ln = 0;
                count_fill = n;
                count_empty = n;
                Cn = 0;
                Dn = 0;
                test = 0;
                while (count_fill > 0){
                    test++;
                    p = secureRandom.nextInt(n);
                    urny[p] = urny[p] + 1;
                    if (urny[p] > 1 && !flag_Bn){
                        Bn = test;
                        System.out.println("Bn: " + test);
                        flag_Bn=true;
                    }

                    if (test <= n) {
                        if (urny[p] > Ln) Ln = urny[p];
                        if (test == n) {
                            Un = countEmpty(urny, n);
                            System.out.println("Un dla "+ n + " : "+Un);
                            System.out.println("Ln: "+Ln);
                        }
                    }
                    if (urny[p] == 1) {
                        count_empty--;
                        if (count_empty == 0) {
                            Cn = test;
                        }
                    }

                    if (urny[p] == 2) {
                        count_fill--;
                        if (count_fill == 0) {
                            Dn = test;
                        }
                    }
                }
                DnCn = Dn - Cn;
                System.out.println("Cn: "+Cn);
                System.out.println("Dn: "+Dn);
                System.out.println("Dn - Cn: "+DnCn+"\n");
                System.out.println("-----------------------------------\n");
                try {
                    assert nFile != null;
                    nFile.append(String.valueOf(m)).append("\t").append(String.valueOf(Bn)).append("\t").append(String.valueOf(Un)).
                            append("\t").append(String.valueOf(Ln)).
                            append("\t").append(String.valueOf(Cn)).
                            append("\t").append(String.valueOf(Dn)).
                            append("\t").append(String.valueOf(DnCn)).append("\n");
                } catch (IOException e) {
                    e.printStackTrace(); }

            }
            m=m+1000;
        }
        try {
            nFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int countEmpty(int[] urny, int length) {
        int count = 0;
        for (int p = 0; p < length; p++) {
            if (urny[p] == 0) count++;
        }
        return count;
    }

}
