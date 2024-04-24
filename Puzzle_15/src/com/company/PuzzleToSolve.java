package com.company;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class PuzzleToSolve {

    public final static int DIMS=4;
    private final int[][] puzzle_array;
    private final int display_of_puzzle;
    private Position Space_Position;


    private static class Position {
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }

    // Konstruktor do tworzenia nowej układanki
    public PuzzleToSolve() {
        // Tworzymy tablicę do przechowywania liczb w układance
        puzzle_array = new int[DIMS][DIMS];
        int cnt=1;
        // Wypełniamy tablicy liczbami od 1 do DIMS^2
        for(int i=0; i<DIMS; i++) {
            for(int j=0; j<DIMS; j++) {
                puzzle_array[i][j]=cnt;
                cnt++;
            }
        }
        // Ustawiamy liczbę znaków, aby wyświetlić liczby w układance
        display_of_puzzle=Integer.toString(cnt).length();

        // Ustawiamy pozycję początkową przestrzeni, która w tym przypadku jest oznaczona liczbą 0
        Space_Position = new Position(DIMS-1,DIMS-1);
        puzzle_array[Space_Position.x][Space_Position.y]=0;
    }

    // Stała reprezentująca rozwiązany stan układanki
    public final static PuzzleToSolve SOLVED=new PuzzleToSolve();

    // Konstruktor do tworzenia kopii rozwiązanej układanki
    public PuzzleToSolve(PuzzleToSolve toClone) {
        // Wywołamy domyślny konstruktor, aby utworzyć nową łamigłówkę
        this();
        // Kopiujemy wartości z układanki toClone do bieżącej układanki
        for(Position p: allPositions()) {
            puzzle_array[p.x][p.y] = toClone.number(p);
        }
        // Ustawiamy pustą pozycję jak w układance toClone
        Space_Position = toClone.getSpace_Position();
    }


    //zwraca pozycje do wszystkich elementów
    public List<Position> allPositions() {
        ArrayList<Position> out = new ArrayList<Position>();
        for(int i=0; i<DIMS; i++) {
            for(int j=0; j<DIMS; j++) {
                out.add(new Position(i,j));
            }
        }
        return out;
    }


    public int number(Position p) {
        return puzzle_array[p.x][p.y];
    }


    public Position getSpace_Position() {
        return Space_Position;
    }


    public Position whereIs(int x) {
        for(Position p: allPositions()) {
            if( number(p) == x ) {
                return p;
            }
        }
        return null;
    }


    // Sprawdzamy, czy ruch do podanej pozycji jest prawidłowy
    public boolean isValidMove(Position p) {
        // Sprawdzamy, czy współrzędna x jest między 0 a DIMS, w przeciwnym razie ruch jest nieprawidłowy
        if( ( p.x < 0) || (p.x >= DIMS) ) {
            return false;
        }
        // Sprawdzamy, czy współrzędna y jest między 0 a DIMS, w przeciwnym razie ruch jest nieprawidłowy
        if( ( p.y < 0) || (p.y >= DIMS) ) {
            return false;
        }
        // Obliczamy różnicę między współrzędnymi położenia ruchu i położenia pustego elementu, aby sprawdzić, czy sąsiadują ze sobą
        int dx = Space_Position.x - p.x;
        int dy = Space_Position.y - p.y;
        // Sprawdzamy, czy różnica w x lub y jest równa 1, w przeciwnym razie ruch jest niemożliwy.
        // Sprawdzamy, czy różnica w x i y nie jest równa 0, w przeciwnym razie pozycje są na tej samej linii i ruch jest również niemożliwy
        if( (Math.abs(dx) + Math.abs(dy) != 1 ) || (dx*dy != 0) ) {
            return false;
        }
        // Jeśli wszystkie testy zakończą się pomyślnie, ruch jest możliwy.
        return true;
    }




    //zwracamy wszystkie poprawne ruchy
    public List<Position> allValidMoves() {
        ArrayList<Position> out = new ArrayList<Position>();
        // Iteracja po wszystkich możliwych wartościach przesunięcia (dx,dy) względem pozycji w przestrzeni
        for(int dx=-1; dx<2; dx++) {
            for(int dy=-1; dy<2; dy++) {
                // Tworzymy nową pozycję dla bieżącego przesunięcia
                Position tp = new Position(Space_Position.x + dx, Space_Position.y + dy);
                // Jeśli nowa pozycja jest prawidłowym ruchem, dodajemy ją do listy możliwych ruchów
                if( isValidMove(tp) ) {
                    out.add(tp);
                }
            }
        }
        return out;
    }


    public void move(Position p) {
        if( !isValidMove(p) ) {
            throw new RuntimeException("Nieprawidłowy ruch");
        }
        assert puzzle_array[Space_Position.x][Space_Position.y]==0;
        puzzle_array[Space_Position.x][Space_Position.y] = puzzle_array[p.x][p.y];
        puzzle_array[p.x][p.y]=0;
        Space_Position = p;
    }

    // tworzy nową instancję obiektu PuzzleToSolve i wykonuje na niej ruch w pozycji p
    public PuzzleToSolve moveClone(Position p) {
        PuzzleToSolve out = new PuzzleToSolve(this);
        out.move(p);
        return out;
    }

    // zwraca listę wszystkich układanek, na których wykonano prawidłowy ruch
    public List<PuzzleToSolve> allAdjacentPuzzles() {
        ArrayList<PuzzleToSolve> out = new ArrayList<PuzzleToSolve>();
        for( Position move: allValidMoves() ) {
            out.add( moveClone(move) );
        }
        return out;
    }

    // zwraca liczbę niezgodności z docelowym stanem
    public int numberMisplacedPositions() {
        int wrong=0;
        for(int i=0; i<DIMS; i++) {
            for(int j=0; j<DIMS; j++) {
                if( (puzzle_array[i][j] >0) && ( puzzle_array[i][j] != SOLVED.puzzle_array[i][j] ) ){
                    wrong++;
                }
            }
        }
        return wrong;
    }

    // sprawdza, czy układanka została rozwiązana
    public boolean isSolved() {
        return numberMisplacedPositions() == 0;
    }

    // funkcja tasowania:
    // ile razy tasuje układankę
    public void shuffle(int howmany) {
        for(int i=0; i<howmany; i++) {
            List<Position> possible = allValidMoves();
            int which;
            which = (int) (Math.random() * (possible.size()));
            Position move = possible.get(which);
            this.move(move);
        }
    }



    // Funkcja wyświetlania
    public void print() {
        System.out.println("-----------------");
        for(int i=0; i<DIMS; i++) {
            System.out.print("| ");
            for(int j=0; j<DIMS; j++) {
                int n = puzzle_array[i][j];
                String s;
                if( n>0) {
                    s = Integer.toString(n);
                } else {
                    s = "";
                }
                while( s.length() < display_of_puzzle ) {
                    s += " ";
                }
                System.out.print(s + "| ");
            }
            System.out.print("\n");
        }
        System.out.print("-----------------\n\n");
    }


    //Rozwiązanie
    public void showSolution(List<PuzzleToSolve> solution) {
        if (solution != null ) {
            System.out.printf("Rozwiązanie pomyślne z %d ruchami:\n", solution.size()-1);
            for( PuzzleToSolve sp: solution) {
                sp.print();
            }
        }
    }



    // Metoda obliczania odległości Manhattanu między obecną układanką a rozwiązaną
    public int manhattanDistance() {
        int sum=0;
        for(Position p: allPositions()) {
            int val = number(p);
            if( val > 0 ) {
                Position correct = SOLVED.whereIs(val); // Pobieramy pozycję właściwej komórki z odpowiednią wartością
                sum += Math.abs( correct.x = p.x ); // Dodajemy różnicę między współrzędnymi bieżącej i prawidłowej komórki w poziomie
                sum += Math.abs( correct.y = p.y ); //Dodajemy różnicę między współrzędnymi bieżącej i prawidłowej komórki w pionie
            }
        }
        return sum; // Zwrócamy sumę różnic we współrzędnych wszystkich komórek
    }


    public int estimateError() {
        return this.manhattanDistance(); // Po prostu zwraca odległość Manhattanu
    }

    // Zastąpiamy metodę equals() w celu porównania dwóch obiektów PuzzleToSolve
    @Override
    public boolean equals(Object o) {
        if(o instanceof PuzzleToSolve) {
            for(Position p: allPositions()) {
                if( this.number(p) != ((PuzzleToSolve) o).number(p)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    // Metoda hashCode() musi zostać nadpisana w każdej klasie, która nadpisuje metodę equals()
    @Override
    public int hashCode() {
        int out=0;
        for(Position p: allPositions()) {
            out= (out*DIMS*DIMS) + this.number(p); // Przypisz wagę do każdej komórki i pomnóż przez DIMS^2, aby uzyskać unikalną wartość
        }
        return out; // Zwrócamy unikalną wartość skrótu
    }


    //Iterative Deepening Search Algorithm
    public List<PuzzleToSolve> IDSSolve() {
        // Tworzymy tabele do przechowywania informacji o układankach i jej poprzednikach, a także o głębokości
        HashMap<PuzzleToSolve,PuzzleToSolve> predecessor = new HashMap<PuzzleToSolve,PuzzleToSolve>();
        HashMap<PuzzleToSolve,Integer> depth = new HashMap<PuzzleToSolve,Integer>();
        // Tworzenie kolejki do odwiedzenia układanki
        Queue<PuzzleToSolve> toVisit = new LinkedList<PuzzleToSolve>();

        // Dodanie oryginalnej układanki do tabel i kolejki
        predecessor.put(this, null);
        depth.put(this,0);
        toVisit.add(this);
        int cnt=0;

        // Iteracyjnie zwiększamy maksymalną głębokość
        while( toVisit.size() > 0) {
            // Otrzymujemy kolejną układankę do odwiedzenia z kolejki
            PuzzleToSolve candidate_puzzle = toVisit.remove();
            cnt++;

            // Sprawdzamy, czy aktualna układanka jest rozwiązaniem
            if( candidate_puzzle.isSolved() ) {
                // Wyświetlamy informacji o znalezionym rozwiązaniu
                System.out.printf("Rozważane tablice %d, ", cnt);
                System.out.printf("Na głębokości %d\n", depth.get(candidate_puzzle));
                LinkedList<PuzzleToSolve> solution = new LinkedList<PuzzleToSolve>();
                PuzzleToSolve backtrace_puzzle=candidate_puzzle;
                while( backtrace_puzzle != null ) {
                    solution.addFirst(backtrace_puzzle);
                    backtrace_puzzle = predecessor.get(backtrace_puzzle);
                }
                return solution;
            }

            // Dodanie wszystkich sąsiadujących układanek do kolejki
            for(PuzzleToSolve fp: candidate_puzzle.allAdjacentPuzzles()) {
                if( !predecessor.containsKey(fp) ) {
                    predecessor.put(fp,candidate_puzzle);
                    depth.put(fp, depth.get(candidate_puzzle)+1);
                    toVisit.add(fp);
                    // Wyświetlamy informacje o aktualnej zagadce, jeśli na danej głębokości nie znaleziono jeszcze rozwiązania
                    System.out.printf("Możliwa ścieżka dla Głębokości %d\n", depth.get(candidate_puzzle));
                    fp.print();
                    System.out.println("Cel nie został znaleziony na tej głębokości");
                    System.out.println("_________________________________________\n\n");
                }
            }
        }

        // Jeśli nie udało się znaleźć rozwiązania na maksymalnej głębokości
        return null;

    }



    //A* Algorithm
    public List<PuzzleToSolve> aStarSolve() {
        // Tworzymy tabele do przechowywania informacji o zagadce i jej poprzednikach, głębokości i funkcji heurystycznej
        HashMap<PuzzleToSolve, PuzzleToSolve> predecessor = new HashMap<PuzzleToSolve, PuzzleToSolve>();
        HashMap<PuzzleToSolve, Integer> depth = new HashMap<PuzzleToSolve, Integer>();
        final HashMap<PuzzleToSolve, Integer> heuristic = new HashMap<PuzzleToSolve, Integer>();

        // Tworzymy komparator do użycia w kolejce priorytetowej
        Comparator<PuzzleToSolve> comparator = new Comparator<PuzzleToSolve>() {
            @Override
            public int compare(PuzzleToSolve a, PuzzleToSolve b) {
                // Zwrócamy różnicę między funkcją heurystyczną a i b
                return heuristic.get(a) - heuristic.get(b);
            }
        };
        // Tworzymy kolejkę priorytetową o początkowej pojemności 1000 i komparator, aby dodać układankę do kolejki
        PriorityQueue<PuzzleToSolve> toVisit = new PriorityQueue<PuzzleToSolve>(1000, comparator);

        // Dodanie początkowej układanki do tabel i kolejki
        predecessor.put(this, null);
        depth.put(this, 0);
        heuristic.put(this, this.estimateError());
        toVisit.add(this);
        // Licznik do zliczania liczby rozważanych układanek
        int cnt = 0;

        // Dopóki kolejka nie jest pusta
        while (toVisit.size() > 0) {
            // Pobieramy układankę z najmniejszą funkcją heurystyczną z kolejki
            PuzzleToSolve candidate_puzzle = toVisit.remove();
            cnt++;

            // Jeśli bieżąca układanka została rozwiązana, zwrócamy sekwencję układanek prowadzących do rozwiązania
            if (candidate_puzzle.isSolved()) {
                System.out.printf("Rozważane tablice %d\n", cnt);
                LinkedList<PuzzleToSolve> solution = new LinkedList<PuzzleToSolve>();
                PuzzleToSolve backtrace_puzzle = candidate_puzzle;
                while (backtrace_puzzle != null) {
                    solution.addFirst(backtrace_puzzle);
                    backtrace_puzzle = predecessor.get(backtrace_puzzle);
                }
                return solution;
            }

            for (PuzzleToSolve fp : candidate_puzzle.allAdjacentPuzzles()) {
                // Jeśli sąsiadująca układanka nie została jeszcze rozpatrzona
                if (!predecessor.containsKey(fp)) {
                    // Dodajemy sąsiednią układankę do tabel i kolejki priorytetowej
                    predecessor.put(fp, candidate_puzzle);
                    depth.put(fp, depth.get(candidate_puzzle) + 1);
                    int estimate = fp.estimateError();
                    heuristic.put(fp, depth.get(candidate_puzzle) + 1 + estimate);
                    toVisit.add(fp);
                }
            }
        }
        return null;
    }

}
