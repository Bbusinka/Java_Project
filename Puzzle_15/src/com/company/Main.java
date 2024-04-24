package com.company;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        PuzzleToSolve p = new PuzzleToSolve();
        p.shuffle(25);
        System.out.println("Potasowana plansza:");
        p.print();

        List<PuzzleToSolve> solution;


        System.out.println("Rozwiązywanie z IDS");
        solution = p.IDSSolve();
        p.showSolution(solution);


        System.out.println("Rozwiązywanie z A*");
        solution = p.aStarSolve();
        p.showSolution(solution);
    }

}
