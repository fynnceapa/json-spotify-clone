# GlobalWaves - etapa 2

**Stan Andrei-Razvan 323CA**\
**Am implementat folosind solutia oficiala de la etapa 1.**

Pe langa pachetele deja existente am adaugat si urmatoarele:
- `page` - pentru sistemul de pagini
- `user.artist` - unde am pus clasele pentru artisti (Artist, Event, Merch)
- `user.host` - unde am pus clasele pentru hosti (Host, Announcement)

## Sistemul de pagini
  Am implementat folosind design pattern-ul **Visitor**.\
Am creat clasa `BasicPage` pe care toate celelalte pagini o mostenesc.\
Toate paginile implementeaze interfata `Visitable` si au metoda `accept(Visitor)`.\
Clasa `PageVisitor` implementeaza interfata `Visitor`si are metodele de Visit pentru fiecare tip de pagina.\
Am considerat ca cel mai simplu ar fi ca metodele de Visit sa returneze un string cu continutul paginii, \
am suprascris metoda `toString()` pentru a afisa continutul paginii.\
Fiecare User normal are un atribut `currentPage` de tip `BasicPage` care este pagina pe care se afla user-ul la acel moment.\
Cum a cerut si cerinta, singurele moduri prin care un user poate schimba pagina sunt prin comanda **changePage** sau prin **selectarea**
unui artist/host.

## Artisti & Hosti
Am creat clasa `Artist` care mosteneste clasa `LibraryEntry`(pentru a putea fi cautat cu search asa cum functioneza scheletul).\
Aceasta contine campuri pentru date de user (nume, oras, vasrta) cat si pentru **albume**, **evenimente** si **merch**.\
Clasele `Event` si `Merch` mostenesc clasa au campuri si metode specifice.\
  
Am creat clasa `Host` care mosteneste clasa `LibraryEntry`.\
Idem Artist, contine campuri pentru date de user (nume, oras, vasrta) cat si pentru **anunturi**.

Atat Artist cat si Host au metodele pentru rezolvarea cerintelor.\

In clasa **Admin** am adaugat cate un array pentru fiecare tip de user, astfel ca metode pentru verificarea ca un user exista\
(`checkUsername`) trece prin fiecare array de useri si returneaza **ture** daca a gasit un user cu numele specificat.\
Pentru determinarea tipului de user mai intai se verifica exista sa (in general), iar mai apoi se cauta in fiecare array.\
De exemplu, daca un user exista dar nu se afla in array-ul de artisti este clar ca nu este artist.\
Am folosit asta in general la comenzile care necesita un anumit tip de user.

## Albume
Am creat in pachetul `audio.Collections` clasa `Album` care mosteneste clasa `AudioCollection`.\
A fost nevoie de metoda `addSong` ca sa pot face conversia de la array-ul de **SongInput** primit din comanda la unul de **Song**.

Mare parte din logica pentru albume este in clasa **Admin**(unde am adaugat si un array de albume).\
Un album este adaugat atat in array-ul de albume al artistului cat si in array-ul din clasa Admin.\
Melodiile din fiecare album sunt adaugate si in clasa Admin.\
Pentru comenzile `showAlbums` si `showPodcasts` am facut in **utils** clasele `PodcastOut` si `AlbumOut` ca sa faca output-ul mai\
simplu.

## Alte modificari aduse scheletului
Am modificat `search` pentru a putea cauta artisti, hosti si albume.\
Am adaugat campuri noi in `CommandInput`.\
Am modificat clasele pentru player pentru a putea folosi functionalitatile unui playlist si pentru albume.\
Am adaugat un camp in `user` pentru a retine daca user-ul este logat sau nu si inca unul pentru pagina curenta.\
Am adaugat comenzi noi pentru statistici.\

## Probleme intampinate
Nu am intampinat probleme in implementare, dar mi-as fi dorit sa pot folosi mai multe design pattern-uri.\
De exemplu as fi vurt sa implementez tipurile de useri folosind design pattern-ul **Visitor**.\
