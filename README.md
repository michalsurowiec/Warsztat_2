# Szkoła programowania

Jest to warsztat z 2 modułu CodersLab. Założeniem zadania jest utrwalenie wiedzy z MySQL oraz napisanie samodzielnie jednego dużego projektu który w całości jest obsługiwany z poziomu konsoli.
Projekt ten według swoich założeń miał pozwalać adminom na przydzielanie użytkownikom zadań z treścią do realizacji dla użytkowników. Użytkownicy natomiast ze swojej strony mają dostęp do przydzielonych im zadań (i żadnych innych) i mogą wstawić rozwiązanie swojego zadania, a w razie potrzeby je zmienić. Admini mogą przeglądać te rozwiązania i je komentować oraz dawać oceny.

## Realizacja
Projekt został podzielony na 2 strony: jedna dla użytkownika, a druga dla admina. Każda ze stron otrzymuje swój zestaw narzędzi potrzebnych do realizowania obowiązków. Dodatkowo, dla użytkownika wprowadziłem konieczność logowania się, która jest realizowana z wykorzystaniem JBCrypt.
Żeby z nich korzystać, należy przejść do paczki main, gdzie do dyspozycji są 2 klasy służące jako centra:
 - AdminHub - jest to program dla Admina, skąd może przejść do różnych modułów
 - Main - program dla Użytkownika, który najpierw rewiduje jego dane logowania, a jeżeli są poprawne to dopuszcza do centrum, skąd może przejść do swoich programów

### AdminHub
Admin w swoim programie ma do dyspozycji centrum, z którego może przejść do 4 modułów:
 - Zarządzanie użytkownikami
 - Zarządzanie grupami użytkowników
 - Zarządzanie zadaniami
 - Zarządzanie rozwiązaniami
 
Każdy z wyżej wymienionych modułów pozwala na realizowanie operacji CRUD wobec każdego z klas.

Użytkownicy nie mogą się sami rejestrować, musi to być zrealizowane za pośrednictwem adminem, gdzie też nadawane jest im hasło, które następnie zostanie im przekazane aby mogli się logować. W klasie użytkowników znajdują się takie informacje jak mail, hasło, nazwa i opis umiejętności.

Grupy użytkowników w tym projekcie nie są wykorzystywane w żaden ważniejszy sposób.

Zadania są wyłącznie opisowe, tzn. obejmują tylko tytuł oraz opis.

Rozwiązania są najważniejsze dla tego projektu. W swojej klasie zawierają id zadania i id użytkownika, dzięki czemu można określić, jakie zadania zostały przydzielone jakiemu użytkownikowi. Dodatkowo, mają datę utworzenia i edycji, dostarczająć informacji kiedy zadanie zostało przydzielone użytkownikowi, a kiedy użytkownik dodał do niego rozwiązanie. Po dodaniu rozwiązania admin jest w stanie je zobaczyć i dać mu ocenę w skali od 2,0 do 5,0 oraz napisać odpowiedni komentarz.

### Main
Użytkownik po przejściu przez logowanie ma aktualnie do dyspozycji tylko moduł dodawania rozwiązań do zadań. W tym module można:
 - zobaczyć wszystkie przydzielone zadania
 - dodać rozwiązania do zadań, które nie mają rozwiązania
 - edytować dodane rozwiązania
 
 ## Podsumowanie projektu
 Udało się zrealizować projekt zgodnie z wytycznymi. W trakcie jego realizacji poza utrwaleniem wiedzy o MySQL poznałem lepiej wykorzystanie switch-case, a także nieco lepiej zrozumiałem funkcjonowanie exception i try-catch, co może okazać się przydatne w następnych projektach.
