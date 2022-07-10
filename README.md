# Interprete di Espressioni

Questa repository contiene un inteprete di programmi per un linguaggio LISP-like contenente definizioni di variabili, istruzioni di input/output, scelte condizionali e cicli, limitato alle sole operazioni tra variabili di tipo intero.
L’interprete deve eseguire le sequenti operazioni:
* Leggere un file in cui è contenuto il programma da interpretare; la sintassi del programma è definita da una grammatica context free non ambigua.
* Eseguire il programma contenuto nel file sorgente.
* Richiedere a console l’eventuale inserimento di dati previsto dalle istruzioni di input e visualizzare su console il risultato di espressioni previsto dalle istruzioni di output.

* see documentation [here](2021_PAA_HomeworkSpecifiche.pdf)


## Formato di input

Nella seguente descrizione di grammatica libera da contesto scriviamo la produzione N → α | β come abbreviazione delle produzioni: N→α e N→β
Utilizziamo i caratteri corsivi per definire simboli non terminali, ed i caratteri normali per definire simboli terminali. Il simbolo  denota la stringa vuota.
Le parole chiave di questo semplice linguaggio sono SET, PRINT, INPUT, IF, WHILE per le istruzioni
(statement); ADD, SUB, MUL, DIV per gli operatori aritmetici; LT, GT, EQ per gli operatori relazionali;
AND, OR, NOT per gli operatori booleani, TRUE e FALSE per le relative costanti. Ad esempio, un possibile
programma in input `e il seguente:
(BLOCK
(INPUT n)
(SET result 1)
(WHILE (GT n 0)
(BLOCK
(SET result (MUL result n))
(SET n (SUB n 1)))
)
(PRINT result))
Non vi sono assunzioni circa il numero di istruzioni contenute in una particolare riga, mentre spazi, tabulazioni e righe vuote non fanno parte della sintassi del programma, ma devono essere considerati come “spazio
vuoto” e ignorati. Il programma precedente potrebbe anche essere scritto correttamente in questo modo:

From a (very very) high level point of view, the app will have :

* A0: *Splash Activity* to check preliminary information (e.g., user logged in or not)
* A1: *Login|Register Activity*
* A2: *Calendar Activity* to show user events
* A3: *Add|Modify Event* to add or modify current events
* S0: *Weather Server* to notify the weather of incoming events
* BR0: *Boot Broadcast Receiver* to detect if the device is booted and start the service

![High Level App Design](./figs/high_level_app_design.jpg)

## Slides

* [Github, Kotlin, and Android Studio](https://docs.google.com/presentation/d/1MwHVyf7rIHVpm-Nmp7eHX55iqBsKyXo18eYfnNr-h-I/edit?usp=sharing)
* Android NDK - Intro

## External useful resources

* [Android First Kotlin App](https://developer.android.com/codelabs/build-your-first-android-app-kotlin#0)
* [Mobisec 2020](https://mobisec.reyammer.io/slides)
* [Groupie library for complex RecyclerView layouts](https://github.com/lisawray/groupie)
* [Awesom Android UI](https://github.com/wasabeef/awesome-android-ui)

Questa repository contiene un inteprete di programmi per un linguaggio LISP-like contenente definizioni di variabili, istruzioni di input/output, scelte condizionali e cicli, limitato alle sole operazioni tra variabili di tipo intero. L’interprete esegue le sequenti operazioni:
• Leggere un file in cui è contenuto il programma da interpretare; la sintassi del programma è definita da una grammatica context free non ambigua.
• Eseguire il programma contenuto nel file sorgente.
• Richiedere a console l’eventuale inserimento di dati previsto dalle istruzioni di input e visualizzare su
console il risultato di espressioni previsto dalle istruzioni di output.

Leggere il file pdf per le specifiche complete del progetto.

In seguito  il diagramma UML corrispondente al progetto completo dell’interprete

![uml](https://user-images.githubusercontent.com/91558676/178147693-6566683a-020b-4352-a128-a2d6372c8414.jpg)

