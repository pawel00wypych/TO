    Należy zaimplementować system symulujący grę liczbową.
    
    Gram ma losować k liczb z puli n kolejnych liczb (oba parametry mają być liczbami całkowitymi większymi bądź równymi 1, 
    losowanie jest bez powtórzeń, oba oba parametrami mają być parametrami konstruktora, to znaczy wszystkie losowania danej
    gry mają stałe wartości tych parametrów.)
    
    Metoda draw() ma symulować losowanie i ma zwrócić kolekcję wylosowanych liczb.
    
    Klasa ma mieć dodatkowo metody: getMin() (zwracającą najmniejszą wylosowaną liczbę), getMax() (zwracającą największą wylosowaną liczbę),
    getHistogram() (zwracająca kolekcję reprezentującą liczbę wystąpień poszczególnych liczb z puli podczas wszystkich losowań) oraz getNumberOfDraws() 
    (zwracająca liczbę dotychczas wykonanych losowań).
    
    Należy przetestować wszystkie metody testami jednostkowymi (zarówno ich pozytywne jak i negatywne scenariusze jak próba stworzenia klasy mającą losować
    negatywną liczbę liczb).
    
    Zadanie należy oddać jako plik pdf zawierający kod klasy i testów oraz zrzut ekrany pokazujący wykonane testy.
    
    (Podpowiedź) Dodaj metodę która będzie inicjalizowała generator liczb losowych określoną wartością, żeby być w stanie użyć klasy losującej w teście 
    (tzn. aby przy każdym uruchomieniu zwracała te same liczby losowe).
