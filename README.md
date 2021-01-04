# Sunrise and Sunset(Spring boot rest API OpenWeather)
Repository creata per prova d'esame gennaio 2021 per il corso di programmazione ad oggetti.

## Applicazione
L'applicazione utilizza l'API Current Weather Data di OpenWeather per accedere ai dati di alba e tramonto di una certa città.
I dati vengono poi salvati per creare uno storico. Su di esso vengono fatte delle analisi riguardati la variazione di orario di alba e tramonto della città scelta dall'utente.

## Come utilizzare

to do

## Rotte applicazione

* **Keys:**
	* Città = città scelta dall'utente
	* Story = storico della città

1.
Tipo | Path |
---- | ---- |
Get | localhost:8080/città |

Fornisce all'utente orario di alba e tramonto della giornata odierna della città cercata.

2.
Tipo | Path |
---- | ---- |
Get | localhost:8080/archive?città/{period} |

Fornisce all'utente lo storico dei dati di alba e tramonto della città indicata
L'utente dovrà sostituire **{period}** con:

  * il valore numerico del periodo dello storico che si vuole visualizzare

3.
Tipo | Path |
---- | ---- |
Get | localhost:8080/stats/{type}/{period} |

Fornisce all'utente statistiche riguardanti la variazione d'orario di alba e tramonto relative alla città cercata.
L'utente dovrà sostituire **{type}** con:

  * sunrise -> per conoscere le statistiche dell'alba
  * Sunset -> per conoscere le statistiche del tramonto
  * all -> per conoscere le statistiche sia di alba che tramonto 
	
L'utente dovrà sostituire **{period}** con:

  * il valore numerico del periodo dello storico che si vuole visualizzare
	
## UML
* Casi d'uso:
![Untitled Document](https://user-images.githubusercontent.com/71764245/102500681-dd6d9200-407c-11eb-9096-57e0271d8aaa.png)

* Classi:

![Schermata 2021-01-03 alle 15 54 20 PM](https://user-images.githubusercontent.com/71764245/103481573-30b14580-4ddc-11eb-87eb-38940a39a11a.png)

![Schermata 2021-01-03 alle 15 54 35 PM](https://user-images.githubusercontent.com/71764245/103481574-327b0900-4ddc-11eb-9176-d187faf12480.png)

![Schermata 2021-01-03 alle 15 54 48 PM](https://user-images.githubusercontent.com/71764245/103481575-34dd6300-4ddc-11eb-991c-608fe2530cf5.png)

![Schermata 2021-01-03 alle 15 55 02 PM](https://user-images.githubusercontent.com/71764245/103481577-360e9000-4ddc-11eb-863b-78dcb91e438f.png)

![Schermata 2021-01-03 alle 15 55 15 PM](https://user-images.githubusercontent.com/71764245/103481580-37d85380-4ddc-11eb-91fe-0fa04943c33f.png)

![Schermata 2021-01-03 alle 15 55 30 PM](https://user-images.githubusercontent.com/71764245/103481584-3b6bda80-4ddc-11eb-961b-1cc695c6dc0b.png)



* Sequenze:

![Schermata 2021-01-02 alle 22 37 28 PM](https://user-images.githubusercontent.com/71764245/103481491-a5d04b00-4ddb-11eb-8152-0717b34a1f68.png)

![Schermata 2021-01-03 alle 15 25 51 PM](https://user-images.githubusercontent.com/71764245/103481492-a8cb3b80-4ddb-11eb-83e6-5ada68cc25de.png)

![Schermata 2021-01-03 alle 15 35 44 PM](https://user-images.githubusercontent.com/71764245/103481495-ab2d9580-4ddb-11eb-8e64-706a119acaad.png)

![Schermata 2021-01-03 alle 15 42 46 PM](https://user-images.githubusercontent.com/71764245/103481497-acf75900-4ddb-11eb-8895-1bd595072662.png)

![Schermata 2021-01-03 alle 15 49 25 PM](https://user-images.githubusercontent.com/71764245/103481499-ae288600-4ddb-11eb-84b7-839d35fee117.png)

## Possibile implementazione del progetto:
Un possibile caso di utilizzo della nostra applicazione potrebbe essere l'utilizzo urbano che prevede l'accensione e lo spegnimento dell'illuminazione pubblica in base a orario di alba e tramonto. Così da rendere automatico e più efficiente l'illuminazione pubblica ed evitando sprechi corrente.

## Autori
Dellisanti Francesco

De Ritis Riccardo



