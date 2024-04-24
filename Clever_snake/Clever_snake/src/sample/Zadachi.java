package sample;

import java.util.ArrayList;
import java.util.HashMap;

public class Zadachi {
    public HashMap <String,Boolean>Riv1(){
        HashMap <String,Boolean> hashMap =new HashMap<>();
        hashMap.put("2+2=4",true);
        hashMap.put("7*8=40",false);
        hashMap.put("Земля має форму трикутника",false);
        hashMap.put("В 10 дециментах 11 міліметрів",false);
        hashMap.put("У павука чотири лапки",false);
        hashMap.put("В одному кілограмі 1000 грамів",true);
        hashMap.put("5*5=25",true);
        hashMap.put("4*2=8",true);
        hashMap.put("145-18=127",true);
        hashMap.put("145+18=163",true);


        return hashMap;
    }
    public HashMap <String,Boolean>Riv2(){
        HashMap <String,Boolean> hashMap =new HashMap<>();
        hashMap.put("Два в квадраті дорівнюе 4",true);
        hashMap.put("1458/145 =10,05",true);
        hashMap.put("Екватор проходить через Антарктиду",false);
        hashMap.put("Піраміда хіопса Стоіть в Кривому Розі",false);
        hashMap.put("Найвиці гори в світі це картати",false);
        hashMap.put("132*176=23232",true);
        hashMap.put("Столиця України ще Київ",true);
        hashMap.put("Киів стоиця Украіни",true);
        hashMap.put("Програма Скреч виконавець алгоритму",true);
        hashMap.put("Скло роблять з піску",true);

        return hashMap;
    }
    public HashMap <String,Boolean>Riv3(){
        HashMap <String,Boolean> hashMap =new HashMap<>();
        hashMap.put("Java об’єктно - орієнтована мова програмування",true);
        hashMap.put(" public static void main(String[] args) {\n" +
                "        launch(args);\n" +
                "    } - Java",true);
        hashMap.put(" public static void main(String[] args) {\n" +
                "        launch(args);\n" +
                "    } - C#",false);
        hashMap.put("Дітей приносять аїсти",false);
        hashMap.put("Маріанська впадена це озеро",false);
        hashMap.put("         <font>\n" +
                "            <Font name=\"Comic Sans MS\" size=\"14.0\" />\n" +
                "         </font>- XML розмітка",true);
        hashMap.put("Арктангенс 10 дорівнює 6",false);
        hashMap.put("Планета земля насправді не зовсім кругла, а схожа на картоплю",true);
        hashMap.put("В Австралії живуть кролики",true);
        hashMap.put("Н2О - формула води",true);

        return hashMap;
    }
}
