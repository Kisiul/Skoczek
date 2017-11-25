# Skoczek
Proze projekt 1

    Jest wstepne menu glowne, watki sa w miare ogarniete i powstala klasa logika, w ktorej powinny byc wykonywane wszystkie obliczenia dot. postaci. 

	Oprocz tego zrobilem dokumentacje javadoc-owa, i skomentowalem co sie dalo. na linuxie uruchamiam javadoca poleceniem: "javadoc *.java -d ../Dokumentacja". Wazna jest ta flaga -d, bo ona wskazuje miejsce docelowe plikow dokumentacji. 
Aby ja obejrzec, nalezy odpalic w przegladarce index.html.


	
	watki wygladaja nastepujaca:

		main--+---------<tu sie nic nie dzieje>------------------------------------------>  
		      |										^
		      menu_glowne--+----<oczekiwanie na skonczenie rozgrywki>-------------------+
				   |							       ^
				   Plansza/Panel_gry-----<wyswietlanie ekranu>-----------------+ 
					|			^ ^ ^			     ^	    
					Logika----<reakcja na klawisze, odswiezanie ekranu>--+  


Synchronizacje, lub komunikacje miedzy watkami zapewnia klasa status, ktora MUSI byc tylko jedna. Wszystkie jej metody sa "synchronized", co oznacza, ze nie moga byc wywolane jednoczesnie przez dwa watki, tylko po kolei (to sie dzieje automatycznie, jesli dobrze rozumiem).

Pzdr,
KD
