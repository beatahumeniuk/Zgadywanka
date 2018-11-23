# Zgadywanka

Popularna gra - zgadywanka, polegająca na gadywaniu haseł.

Gra posiada dwa widoki:
1. Wybieranie kategorii:
Wybór jednej z pięciu kategorii - zawiera 
 - nagłówek,
 - 5 buttonów, każdy z nazwą kategorii
2. Gra:
Przebieg gry - zawiera 
 - wynik, 
 - nazwę wybranej kategorii,
 - przycisk, wyświetlający podpowiedź
 - hasło (początkowo w formacie "--(..)" pozniej znaki "-" są zastępowane literami)
 - zestaw przycisków z literami 
 - przycisk do zmiany kategorii 
 - liczbę punktów, jakie można dostać za dane hasło
 - przycisk generujacy nowe hasło
 
 Przebieg gry:
 1. Wybieramy kategorię 
 2. Przechodzimy do ekranu gry
  2.1. Z pliku words.json jest wczytywana lista wyrazów
  2.2. Z listy zostaje wylosowany jeden wyraz i po przekonwertowaniu wyswietlony na ekranie
  2.3. Po wybraniu jednego z przycisków reprezentującego daną literę
    2.3.1. Jeżeli odgadywany wyraz zawiera daną literę znak "-" bedący w miejscu tej litery jest zastepowany nią
    2.3.1. W przeciwnym wypadku punkty, które można dostać za dane hasło zmniejszają się o 1
  2.4. Po wybraniu przycisku ze znakiem zapytania
    2.4.1. Wyświetla nam się podpowiedź do danego hasła
    2.4.2 Punkty, które można dostać za dane hasło zmniejszają się o połowę
  2.5.  Po wybraniu przycisku "ZMIEŃ KATEGORIĘ" 
    2.5.1. Wracamy do widoku z wyborem kategorii
    2.5.2. Wynik się zeruje
  2.6.  Po wybraniu przycisku "NASTĘPNE HASŁO"
   2.5.1.  Z listy zostaje wylosowany kolejny wyraz i po przekonwertowaniu wyswietlony na ekranie
    2.5.2. Wynik się zeruje
