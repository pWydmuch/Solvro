# Solvro city 

Projekt ten jest realizacją [zadania rekrutacyjnego](https://github.com/Solvro/rekrutacja#backend)
do koła naukowego Solvro działającego na Politechnice Wrocławskiej

Jego zadaniem było napisanie backendu do aplikacji imitującej działanie aplikacji JakDojade, a
konkretniej, obliczenie najkrótszej możliwej drogi między dwoma przystankami, oraz podanie przystanków pośredniczących.

Aplikacja umożliwia również rejestracje i logowanie użytkowników.

### Żeby uruchomić tę aplikację  trzeba najpierw sklonować to repozytorium
Najłatwiej to osiągnąć korzystając z poniższej komendy      

``` git clone https://github.com/pWydmuch/Solvro.git ```

Kiedy już to zrobisz, przejdź do folderu w którym znajduje się plik pom.xml i skorzystaj z poniższej instrukcji

``` sh run-app.sh ```

Mimo, że 'sh' jest typową komendą Linuxa, możesz również powyższy skrypt odpalić na Windowsie korzystając np. z git-basha

Gdyby z jakichś powodów apliacja nie działała poprzez dockera, można ją odpalić bez konteneryzacji, używająć poniższej instrukcji
 
``` ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev -DskipTests ```

By wygenerować nowe miasto, skorzystaj z poniższej komendy (muszisz mieć zainstalowanego pythona)

``` sh change-city.sh ```

### Użyte technologie

* Spring Boot
  * MVC
  * Security
  * Data  
* JPA/Hibernate
* PostgreSQL
* Jackson
* Docker
* Docker Compose
* Swagger
* Maven

### Opis aplikacji

Do zaimplementowania wyszukiwania najkrótszej ścieżki użyłem algorytmu Dijkstry w wersji dla grafu nieskierowanego

Aplikacja w celu zapewnienia bezpieczeństwa wykorzystuje protokół HTTPS używając certyfikatu, którego sam podpisałem, więc może powodować to różne ostrzeżenia np od przeglądarki.
Chcąc przetestować aplikację w Postmanie należy w opcjach wyłączyć sprawdzanie certyfikatu SSL, a używając cURL-a dodać do zapytania flagę -k.

Dostęp do endpointów /stop i /path  odbywa się na podstawie tokena JWT, otrzymanego po pomyślnym zalogowaniu. Token przekazuje się w nagłówku 'Authorization' w schamacie 'Bearer Token'  

Dokumentacja API z wykorzysatniem Swaggera jest dostępna pod endpointem /swagger-ui.html

W celu zapewnienia niezależności od środowiska uruchomieniowego wykorzystałem Dockera.
Projekt odpala się na dwóch kontenerach, jeden z aplikacją właściwą, a drugi z serwerem PostgreSQL.
By móc umożliwić komunikacje między tymi dwoma kontenerami użyłem Docker Compose'a. Natrafiłem jednak na problem, spowodowany tym, że
aplikacja chciała się połączyć z bazą danych, gdy jeszcze ona wstawała, co powodowło błąd.
By rozwiązać ten problem użyłem skryptu 'wait-for-it.sh', który nie pozwala odpalić się aplikacji, jeśli baza danych jeszcze nie jest gotowa.

By umożliwić połączenie aplikacji z frontendem udostępniłem endpointy dla wszystkich originów, czego oczywiście nie powinno się robić w prawdziwych aplikacjach, ale zwiększa przenaszalności aplikacji.
 
### Co chiałbym jeszcze zrobić

* Napisać testy
* Zmienić architekturę na bardziej zgodną z DDD, np heksagonalną
* Sprawić by dane, w kontenerze z Postgresem po wyłączeniu kontenera nie usuwały się
* Wdrożyć w chmurę, np Google Cloud
* Zastosować Continous Integration, np. Travisa 
