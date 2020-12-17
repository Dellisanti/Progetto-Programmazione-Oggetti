# Sunrise and Sunset(Spring boot rest API OpenWeather)
Repository creata per prova d'esame gennaio 2021 per il corso di programmazione ad oggetti.

# Autori
Dellisanti Francesco
De Ritis Riccardo

L'applicazione utilizza l'API Current Weather Data di OpenWeather per accedere ai dati di alba e tramonto di una certa città.
I dati vengono poi salvati per creare uno storico. Su di esso vengono fatte delle analisi riguardati la variazione di orario di alba e tramonto della città scelta dall'utente.

#Rotte Applicazione

Keys:
Città = città scelta dall'utente
Story = storico della città

1.
Get	localhost:8080/città

Fornisce all'utente orario di alba e tramonto della giornata odierna della città cercata.

2.
Get	localhost:8080/archive?città/{period}

Fornisce all'utente lo storico dei dati di alba e tramonto della città indicata
L'utente dovrà sostituire {period} con:
	il valore numerico del periodo dello storico che si vuole visualizzare

3.
Get	localhost:8080/stats/{type}/{period}
Fornisce all'utente statistiche riguardanti la variazione d'orario di alba e tramonto relative alla città cercata.
L'utente dovrà sostituire {tipe} con:
	sunrise -> per conoscere le statistiche dell'alba
	Sunset -> per conoscere le statistiche del tramonto
	all -> per conoscere le statistiche sia di alba che tramonto 
L'utente dovrà sostituire {period} con:
	il valore numerico del periodo dello storico che si vuole visualizzare

#Possibile implementazione del progetto:
Un possibile caso di utilizzo della nostra applicazione potrebbe essere l'utilizzo urbano che prevede l'accensione e lo spegnimento dell'illuminazione pubblica in base a orario di alba e tramonto. Così da rendere automatico e più efficiente l'illuminazione pubblica ed evitando sprechi corrente.




