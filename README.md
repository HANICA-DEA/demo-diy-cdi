# Introductie
Deze Demo geeft een beeld van hoe Dependency Injection zelf te implementeren is en is deel van de DEA Course aan de 
Hogeschool Arnhem/Nijmegen. 

De manier waarop JavaEE/JakartaEE zelf instanties maakt van klassen geannoteerd met @Path of hoe
Dependency Injection werkt, is voor veel Junior Software Developers magisch.

Deze Demo moet die magie weghalen door te laten zien hoe het werkt.

# Uitleg
Dit is geen Java/JakartaEE, maar een gewone console-applicatie. Er wordt dan ook niet gebruik gemaakt van Java/JakartaEE
annotaties of een applicatie-container. Het project bevat een Resource en een Service laag en moet opgestart
worden via een `main()`-methode uit de klasse `DiyCDIRunner`. Daarna volgen de volgende stappen:

* De `main()`-methode zoekt naar Klassen die geannoteerd zijn met `@DiyPath` en maakt daar instanties van.
* Binnen deze instanties wordt gezocht naar methoden die geannoteerd zijn met `DiyInject`. 
* Van de gevonden methodes wordt het type van de parameter bepaald, waarna gezocht wordt naar een klasse van dat type.
* Van deze klasse wordt een instantie gemaakt, die vervolgens gebruikt wordt om the injecteren.
* Nu wordt er gezocht naar methoden die geannoteerd zijn met `@DiyGET`. Deze worden aangeroepen.
