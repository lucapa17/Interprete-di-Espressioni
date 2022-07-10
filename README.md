# Interprete di Espressioni

Questa repository contiene un inteprete di programmi per un linguaggio LISP-like contenente definizioni di variabili, istruzioni di input/output, scelte condizionali e cicli, limitato alle sole operazioni tra variabili di tipo intero.
L’interprete deve eseguire le sequenti operazioni:
• Leggere un file, chiamato file sorgente nel seguito, in cui è contenuto il programma da interpretare; lasintassi del programma è definita da una grammatica context free non ambigua (riportata di seguito).
• Eseguire il programma contenuto nel file sorgente.
• Richiedere a console l’eventuale inserimento di dati previsto dalle istruzioni di input e visualizzare su
console il risultato di espressioni previsto dalle istruzioni di output.


## App Feature & Design

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

