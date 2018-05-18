-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: 27 Mai 2017 la 18:21
-- Versiune server: 5.6.35-log
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `costinso_ip`
--
CREATE DATABASE IF NOT EXISTS `costinso_ip` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `costinso_ip`;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `Actori`
--

CREATE TABLE `Actori` (
  `id` int(11) NOT NULL,
  `nume` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prenume` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `poza` varchar(140) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Salvarea datelor din tabel `Actori`
--

INSERT INTO `Actori` (`id`, `nume`, `prenume`, `poza`) VALUES
(1, 'Bejenariu', 'Catrinel', ''),
(2, 'Costin', 'Gabi', ''),
(3, 'Logigan', 'Vlad', ''),
(4, 'Pavel', 'Vlad', ''),
(9, 'Zaharia', 'Valentina', ''),
(10, 'Negrutu', 'Silvana', ''),
(11, 'Teleoaca', 'Mihaela', ''),
(12, 'Badescu', 'Gratiela', 'asdasd'),
(13, 'Lupu', 'Dan', 'asdadfd'),
(14, 'Fifea', 'Alex', 'jytf');

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `Comentarii`
--

CREATE TABLE `Comentarii` (
  `id` int(11) NOT NULL,
  `id_spectacol` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `text` text NOT NULL,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Salvarea datelor din tabel `Comentarii`
--

INSERT INTO `Comentarii` (`id`, `id_spectacol`, `id_user`, `text`, `data`) VALUES
(1, 3, 1, 'frumos spectacol', '2017-05-16 21:00:00'),
(2, 3, 2, 'nu mi-a placut', '2017-05-18 21:00:00'),
(3, 3, 3, 'a fost asa si asa', '2017-05-08 21:00:00'),
(4, 3, 4, 'superb!', '2017-04-30 21:00:00');

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `Critici`
--

CREATE TABLE `Critici` (
  `id` int(11) NOT NULL,
  `id_spectacol` int(11) NOT NULL,
  `nume_critic` varchar(80) COLLATE utf8mb4_unicode_ci NOT NULL,
  `text` text COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Salvarea datelor din tabel `Critici`
--

INSERT INTO `Critici` (`id`, `id_spectacol`, `nume_critic`, `text`) VALUES
(1, 1, ' Cristina MODREANU', 'iHamlet, produs de Asociația Punctart în colaborare cu Teatrul Excelsior, regizat de Catinca Drăgănescu, pe un text de Elise Wilk, a fost cel mai bun spectacol pentru adolescenți pe care l-am văzut de mult timp. Rescrierea poveștii lui Hamlet cu elemente multimedia este un pas inteligent și necesar înspre recîștigarea unui public fără de care viitorul teatrului este incert. '),
(2, 1, 'Ziarul Metropolis, A fi sau a nu fi adolescent ', 'Cum altfel, dacă nu asfixiat de propriile neliniști, și-a dus acest tânăr puținele zile în care a fost pe pământ? În final, rămas complet fără aer, a trecut de la a face experimente pe cadavre la a fi el însuși unul.\r\nDeznădejdea asta maladivă, fatală, n-ar trebui să cuprindă niciun om, dar cu atât mai puțin unul care mai are, teoretic, atâtea alte mici deznădejdi de cunoscut – o restanță, un metrou ratat, o insomnie, un prieten în minus. Ești tânăr, te amăgești că ești etern și din visarea asta se presupune că ar trebui să te trezească primul fir de păr alb, nu moartea.\r\nUneori îmi imaginez că sunt mort se tot repetă și pe scena Teatrului Excelsior. Ce e cu tinerii și cu macabrul? iHamlet, spectacol de teatru multimedia centrat pe un personaj, H, un băiat dezamăgit de propria viață, creionează un posibil răspuns. Născută dintr-un teribilism specific adolescenței, atitudinea relaxată în fața sfârșitului ultim cere, de fapt, destulă compătimire din partea celorlalți. E prea devreme pentru a nu mai avea nimic de pierdut, de câștigat, de iubit.\r\niHamlet vorbeşte despre o realitate adolescentină de care cu toții am auzit, însă acest lucru nu o face mai uşor de suportat. Situarea într-un cerc închis, unde nu se mai vede, ca soluție, decât moartea, e cauzată de o dramă familială. Aici intervine, subtil, paralela shakespeariană. După divorțul părinților lui, dialogurile zgomotoase pe care H le are cu mama sa, pe care refuză să o vadă cu un alt bărbat, amintesc de furia vindicativă a lui Hamlet, cel originar. Ceva e putred în tinerețea lui H, doar aşa se poate explica imaginea vizuală pe care el însuşi o proiectează în minte ─ creierii împrăştiați pe pereți ai străinului din viața mamei lui.\r\nFragmentarea subită a familiei creează retrospective ale trecutului, momente ideale, cu mers la plajă și cu soare. În zadar se încearcă însă ancorarea în aceste amintiri tot mai șterse, viața nu se lasă impresionată de fericirile de scurtă durată. Relația conflictuală dintre fiu și mamă, lipsa unei relații reale între fiu și tatăl permanent ocupat ─ teme dintotdeauna valide, în multe cazuri, sub diverse forme ─ dau un personaj profund paralizat de singurătate. Iubirea, cea care l-ar putea salva, devine, în cele din urmă, o joacă, un motiv de plictiseală, o superficialitate pe care vârsta o poate scuza.\r\nUniversul interior al lui H e parte dintr-un spațiu mai larg, în care se întâmplă diverse ─ două camioane de suc se ciocnesc pe o autostradă, o femeie, în China, naște singurul copil la care are dreptul, o alta primește, pentru prima oară în viața ei, o palmă; pe fundalul acestei treceri prin viața lumii, Planeta Albastră se rotește pe un ecran invizibil, cucerind sala. Luând ca reper acest simbol al unui întreg măcinat de probleme și de întâmplări, angoasele lui H nu-l fac pe adolescent cu nimic mai special față de restul omenirii. Decepțiile lui îl pot oricând doborî, dar la fel de mult îl pot și ridica. Asemeni shakespearianului Hamlet, închis într-o coajă de nucă, ceea ce, raportat la lume, ar putea fi chiar propria viață, H s-ar putea crede, într-adevăr, regele nemărginirii, dacă nu ar fi însă visele urâte. Dacă n-ar fi mama și intrusul. Dacă n-ar fi tatăl care uită de ziua lui de naștere. Din fericire, povestea lui H lasă un final deschis, ceea ce e cu atât mai bine cu cât în primele rânduri ale sălii stau numai adolescenți. Poate cei mai descurajați dintre aceștia, dacă vor fi, se vor mulțumi cu șansa pe care dramaturgul o dă personajului, și deci, implicit, și lor.'),
(3, 2, 'Oana Stoica, Dilema Veche', '„Nimeni nu pleacă de bine.“ Aceasta este una dintre replicile-cheie din noul spectacol de la Centrul Replika al Catincăi Drăgănescu, Rovegan. Un demers artistic pornit de la cartea Lilianei Nechita, Cireşe amare, care mărturiseşte experienţa personală a plecării la muncă în străinătate, şi continuat cu o investigaţie asupra fenomenului migraţiei din Estul european în Occident. Documentarea s-a făcut în judeţul Vaslui, una dintre zonele cele mai sărace din România, unde o bună parte din populaţie a luat drumul străinătăţii în căutarea resurselor pentru supravieţuire.\r\n\r\nFenomenul migraţiei forţei de muncă – între două şi trei milioane de români lucrează în afara graniţelor, cifra exactă este greu de aflat în lipsa unor statistici oficiale – a suscitat interes în zona performativă, mai multe spectacole abordînd problema copiilor rămaşi acasă fără părinţi. Consecinţele plecării părinţilor asupra copiilor sînt majore: abandon şcolar, abuzuri psihice şi fizice, infracţionalitate etc. Perspectiva de acasă asupra migraţiei este tristă, familiile se destramă, iar banii cîştigaţi nu mai aduc nici un beneficiu, dimpotrivă, se înregistrează un efect de bumerang (plecarea este motivată de asigurarea unui viitor pentru copii, dar absenţa părinţilor produce efectul contrariu). Ignorate o perioadă, consecinţele migraţiei au răbufnit în ultima vreme şi au ajuns în dezbaterea publică. Desigur, nu au existat, deocamdată, şi urmări practice ale acestei conştientizări a situaţiei celor rămaşi acasă, iar despre perspectiva celor plecaţi încă nu se vorbeşte.\r\n\r\nCeea ce determină cartea Lilianei Nechita – care cuprinde scrisori către cei de acasă – este conştientizarea celeilalte viziuni, a migranţilor, a celor aflaţi între două lumi. Munca în străinătate poate însemna un succes personal, dar de multe ori ea este conectată cu umilinţe, cu servicii sub nivelul educaţiei sau cu eşuarea în zona promiscuă. Est-europenii au devenit, după căderea comunismului, noii sclavi economici ai Occidentului. Apare aici o perspectivă din interior asupra capitalismului haotic (original, la fel ca democraţia) pe care îl experimentează Estul Europei de un sfert de secol şi care generează dislocări de forţă de muncă dintr-un spaţiu în altul, cu efecte negative asupra vieţii profesionale şi personale.\r\n\r\nCatinca Drăgănescu ficţionalizează cazurile unor femei din Vaslui care lucrează ca îngrijitoare pentru bătrîni în Italia, în timp ce acasă rămîn copiii care nu reuşesc să păstreze familia unită. Rama pe care o pune regizoarea este varianta contemporană a unui basm, Capra cu trei iezi. Spectacolul se dezvoltă sub forma unei corale în care poveştile, muzica live şi proiecţiile se îmbină performativ, derulînd în scene scurte filmul unui abandon, de sine şi de alţii, motivat de sărăcie (o altă replică torturantă: „Sîntem săraci şi aici, dar măcar nu ne ştie nimeni“). Ficţiunea teatrală este urmată, la final, de realitatea filmată. Catinca Drăgănescu evită melodrama, refuză să emoţioneze prin victimizare, preferă această formă a basmului un pic comic, un pic magic, care stopează efuziunile sentimentale, dar care are un impact cu atît mai puternic asupra publicului.\r\n\r\nUmilinţele, munca grea, remunerată sub nivelul pieţei, depresia duc la o formă acută de dezrădăcinare. Migrantul îşi pierde identitatea în exil, rămîne suspendat între lumea de acasă, căreia nu îi mai aparţine, şi lumea nouă, în care nu se integrează complet şi unde este tratat ca cetăţean de categoria a doua. El are nevoie de mască, sub care să ascundă suferinţa şi acasă, şi în exil. Sub masca asta însă el moare pe dinăuntru.\r\n\r\nCatinca Drăgănescu a reuşit o distribuţie excepţională în Rovegan, cu trei actriţe care realizează un tur de forţă, combinînd modalităţi diferite de joc, de la scene dramatice la naraţiuni corale sau muzică live. De asemenea, ele fac o creaţie lingvistică, folosind dialectul moldovenesc întîi pentru „colorarea“ naraţiunii şi identificarea personajelor, apoi ca pe o formă de refugiu personal. Percepută ca o „detenţie“ economică (pentru că este forţată de circumstanţe exterioare – sărăcia), munca departe de casă determină depresii puternice. Intimitatea este de regulă redusă, la fel ca timpul liber. Prin urmare, contactul cu familia sau timpul pentru sine sînt scurte, astfel încît identitatea migrantului se rezumă la cea de muncitor. În spectacol, dialectul moldovenesc capătă o funcţie identitară, care permite personajelor păstrarea parţială a identităţii.\r\n\r\nRolurile sînt multiple, povestea principală generează cîteva personaje cărora li se adaugă pe parcurs altele. Sînt, astfel, caractere constante, pe care actriţele le interpretează de-a lungul întregului spectacol, şi caractere episodice. Formula dramaturgică dinamică este excelent susţinută actoriceşte.\r\n\r\nMihaela Teleoacă, actriţă a Teatrului de Comedie, este formidabilă în rolul Caprei, dar şi în celelalte apariţii secundare. Cu o uluitoare flexibilitate interpretativă, actriţa dovedeşte o excelentă înţelegere a personajelor şi a formulei de spectacol. Remarcabil este modul în care ea menţine echilibrul între dramă şi emoţie, reuşind să evite melodrama şi păstrînd, în acelaşi timp, tot tragismul personajului. Cred că este una dintre cele mai bune interpretări ale Mihaelei Teleoacă. Silvana Negruţiu, cu forţa şi umorul ei, şi Valentina Zaharia, fragilă şi ironică, sînt familiarizate cu stilul Catincăi Drăgănescu în spectacolele căreia au mai jucat (cel mai recent este Ibsen Incorporated la Teatrul de Comedie). Ambele actriţe funcţionează impecabil în spectacol.\r\n\r\nPentru cine are contact cu această Românie, tăcută şi ignorată, care se destramă încet, Rovegan este un spectacol cutremurător. Pentru ceilalţi, poate fi o revelaţie. De văzut la Centrul Replika. '),
(4, 2, 'Alina Epîngeac, Yorick.ro', 'Deschidem televizorul sau laptopul şi avem impresia că ştim. Ce anume? De toate. De la reţete miraculoase la adevărul despre aselenizare. Suntem bine informaţi. Şi avem „o viziune amplă asupra lumii în care trăim”. Numai că odată cu fluxul de informaţii venit via ecranul smartphone-ului lumea în care trăim s-a micşorat atât cât să încapă în spatele acelui ecran. Ridicăm ochii din lumina hipnotică a gadgetului nostru din ce în ce mai rar şi cu cât suntem mai conectaţi la socialul virtual, cu atât ne sălbăticim în viaţa reală; sau ce a mai rămas din ea. Realitatea altora este ştire pentru noi. Şi credem fără excepţie că viaţa noastră este numai bună pentu un scenariu de film de artă. Drame mici şi mari care se petrec în câţiva metri pătraţi, suprapuse geometric în blocuri din ce în ce mai înalte, care se consumă în spatele uşilor trântite în miez de noapte. Dar nu te impresionează cu nimic nici gemetele bătrânei care trage să moară de partea cealaltă a peretelui de rigips, nici frânturile de ceartă conjugală care se petrece fix deasupra patului în care dormi. Viaţa aşa cum e nu e prea interesantă în comparaţie cu breaking news-ul care clipoceşte pe ecran. Fugim cât putem de realitate; poate de frică sau, poate, datorită epuizării premature a unor minţi mereu ocupate.\r\n\r\nÎntr-o astfel de lume prea puţin sensibilă, deprimată şi egoistă, o poveste despre mame plecate la muncă în Italia în căutarea speranţei pentru copiii lăsaţi acasă este doar încă o ştire banală. Suntem tentaţi să schimbăm repede canalul pentu că „le avem noi pe-ale noastre”. La teatru, însă, suntem ceva mai permeabili la emoţie. E locul în care căutăm „senzaţii tari” pentru suflet şi acceptăm să dăm jos armura de nepăsare. Spectacolul „Rovegan” scris şi regizat de Catinca Drăgănescu la Centrul de Teatru Educaţional Replika face exact acest lucru: transformă o ştire lacrimogenă într-un act artistic subtil, inteligent, emoţionant, care te convinge să priveşti în ochi un fenomen de la care de prea multe ori îţi întorci privirea.\r\nDacă ai fi invitat, tu om mare şi serios, la un spectacol remixat pornind de la basmul „Capra cu trei iezi” te-ai duce? Puţini curioşi, poate. Dacă ai fi chemat la un spectacol despre migraţia disperată spre occident datorată sărăciei, te-ai duce? Şi mai puţin probabil. Catinca Drăgănescu a combinat, însă, atât de inteligent şi de motivat artistic şi social aceste două teme aparent divergente, încât produsul final este un spectacol consistent atât din punct de vedere estetic, dar şi cu un mesaj puternic, articulat, deloc ostentativ sau redundant. Este comic-amar ca povestea caprei plecate în căutarea hranei să se perpetueze astăzi sub forma realităţii mamelor plecate la muncă în Italia sau Spania. Şi exact acest gust îl are şi spectacolul „Rovegan”.\r\n\r\nÎntr-o manieră de joc brechtiană, în care cele trei actriţe devin pe rând personaje şi naratori, metaforele şi alegoria conduc această altoire originală şi spectaculoasă. Râzi când e de râs şi plângi când e de plâns. Dar deloc stupid şi deloc patetic. Râsul e inteligent şi lacrima lucidă. Nu se speculează sentimente şi nu se apasă manipulator pe butoane care să emoţioneze la comandă. Structura dramatică a acestui text este matematic gândită pentru a crea un tobogan de stări, pe care aluneci în ritmuri bine controlate. Scene puternice, „testimoniale”, sunt urmate de pastile concentrate de haz. Nu ai timp să devii o gospodină care consumă un pachet de şerveţele în faţa telenovelei preferate şi nici să râzi zgomotos ca într-un pub la glume deocheate. Ai tot timpul sentimentul de adevăr, de sinceritate, îţi repeţi în minte „aşa e, dom’ne” şi faci asocieri între lecţia despre caracteristicile caprei şi trăsăturile naţiei noastre. Şi totul pare foarte uşor. Foarte firesc. Iar atunci când un spectacol reuşeşte să fie simplu, atunci înseamnă că e foarte bun.\r\nCele trei actriţe sunt una singură. Acesta e sentimentul clar – de comunicare şi ascultare reciprocă. Deşi sunt puţine momente de interacţiune, efectul de distanţare fiind obţinut printr-o interpretare la persoana a treia în care sunt inserate scene interpretate la persoana I, felul în care Mihaela Teleoacă, Silvana Negruţiu şi Valentina Zaharia preiau şi duc mai departe povestea este un exemplu frumos de dialog artistic. Intră şi ies din personaje, îşi spun fiecare monologul cu atâta căldură, sinceritate şi emoţie netrucată, au grijă de parteneri, dozează precis intensitatea fiecărui moment, au umor şi auto-ironie, controlează perfect tuşele groase ale personajelor caricaturale, cântă a capella şi, cu doar câteva elemente de costum şi recuzită, te fac să vezi exact, te conduc pri poveste şi nu îţi dau voie să te rătăceşti printre paralelele cu basmul citat. Capra plecată la drum cu un rucsac în spate în care duce puterea de a îndura, capra tânără, curioasă şi sprinţară, căpriţa cuminte rămasă singură acasă, capra cea mare bolnavă şi abandonată pe un pat de spital care se simte povară, tipologii perfect asumate şi translatate în acest univers teatral în care semnul şi convenţia au forţă de sugestie.\r\n\r\n„Rovegan” este un spectacol care te păcăleşte isteţ să asculţi, tu, om mare, poveşti pentru copii şi să iei în serios un fenomen de care îţi e mai comod să faci abstracţie. Te smulge pur şi simplu din ecranul luminos cu poveşti pe care le scrollezi din obişnuinţă şi te convinge cu frumosul să te uiţi la lumea tristă şi plină de umor din jurul tău. Aplauzi performanţa artistică şi rămâi cu gustul unei vinovăţii difuze în faţa unei poveşti de viaţă cât se poate de reală, spusă în limbaj de basm.'),
(5, 2, 'Irina Radu, Yorick.ro', 'A fost odată ca niciodată, că de n-ar fi nu s-ar povesti… Acesta ar putea fi începutul unui basm cu final fericit. La fel cum foarte bine ar putea deveni începutul unei minunate povești despre adevăr și umanitate, despre toleranță și reciprocitate. Un basm cu morală simplă în care binele învinge orice obstacol. Însă, tot atât de bine, acesta ar putea fi începutul unui basm despre cum noi, oamenii, nu suntem neapărat întotdeauna eroi și despre cum nu înțelegem care (ne) sunt, cu adevărat, granițele între a vrea și a putea. Finalul, de data aceasta, rămâne deschis, absolut interpretabil.\r\n\r\nFenomenul migrației nu mai reprezintă de multă vreme nici o utopie și nici un subiect neștiut. Ci dimpotrivă, atât la nivel european, cât și punctual, raportându-ne la situația României în ecuație, migrația a devenit unul dintre cele mai dure procese de modificare a realităților economice, sociale, culturale, religioase. S-a preschimbat agresiv într-un indicator al diversităților de cauze și efecte. Prin urmare, trebuie tratat ca atare. Ce ne demonstrează însă Catinca Drăgănescu prin spectacolul Rovegan, producție a Asociației Arena și a Teatrului Educațional Replika este tocmai faptul că problema migrației se poate încadra foarte bine și în tiparele unui basm. Alegoria se poate naște la intersecția dintre capitalismul ieșit din matcă, migrația masivă a forței de muncă spre Occident și celebra poveste a Caprei cu trei iezi, adaptată acum pentru prezentul nostru, cel de toate zilele. În urmă cu doi ani, Catinca, împreună cu întreaga echipă a spectacolului, realiza o documentare  serioasă în mai multe localități din județul Vaslui, „polul sărăciei în Uniunea Europeană”. Scopul? Identificarea efectelor și dislocărilor produse în plan personal și social atât de cei care acceptă compromisul de pleca peste hotare, în speranța obținerii unui venit mai bun, cât să îi poată susține și întreține pe cei de acasă, precum și asupra celora rămași în urmă, datori, la rândul lor, să reziste.\r\nRovegan este un basm spus pe trei voci, mai precis un storytelling colectiv despre povestea unor mame nevoite să plece la muncă în Italia. Fărâmele de viață ale familiilor interogate de-a lungul cercetării, sunt ficționalizate acum și asumate de Valentina Zaharia, Mihaela Teleoacă și Silvana Negruțiu, ale căror interpretări se întrepătrund într-atât de unitar, încât emoția, comicul și empatia devin triadă permanentă. Subiectul n-ar fi neapărat unul nou – capra mamă e nevoită să plece în căutare de hrană pentru iezii săi, pe care îi instruiește să aștepte cuminți și să nu cadă în capcana dușmanului-lup. Extrapolând, spectacolul ne aduce în prim-plan parcursul Mioarei, hotărârea de a pleca pentru a lucra ca batandă pe meleaguri străine și repercusiunile pe care decizia le produce asupra familiei rămase în România. O dezbinare lentă, dar sigură. O derivă greu de controlat, un declanșator al abandonului școlar, al instaurării depresiei, al luptei împotriva sau a acceptării abuzurilor fizice și pierderea identității.\r\n\r\nCireșe amare, cartea Lilianei Nechita, devine o altă sursă importantă de inspirație în realizarea acestui spectacol. Scrisorile pe care autoarea avea să le trimită de-a lungul timpului acasă, familiei, prezintă întocmai realitatea covârșitoare și anduranța pe care trebuie să o dovedească fiecare migrantă în fața umilinței, a lipsei de respect și a condițiilor de trai impuse chiar și în Occidentul tuturor posibilităților. Mihaela Teleoacă interpretează o femeie care poartă pe umeri o povară imensă – aceea a sacrificiului în fața vieții și a celor dragi. Și reușește duios, cu ochi blânzi și cu o smerenie permanentă să îndure tot greul, amărăciunea și dorul nesfârșit. Reușește, totuși, dincolo de tot bulgărele de încercări să își găsească forța (și pe cea artistică deopotrivă) de a depăși și șantajul angajatorilor, și singurătatea și alienarea. Știe că trebuie. Acel trebuie cantonat în iluzia fericirii, a siguranței financiare, a unei ierarhii sociale. Trebuie aduce ziua de mâine și devine motivație și mecanism. Un crez repetat zilnic, în gând, printre străini, pe parcursul orelor nesfârșite de muncă și chin, în pauzele scurte de oftat și trimis scrisori către casă, în nopțile cu lungi insomnii. Trebuie să ocrotești, să iubești, să produci bani-bani-bani.\r\nValentina Zaharia surprinde prin claritatea asumării mijloacelor de joc. Interpretarea sa cuprinde transformări multiple, încât personajul său se încumetă spre un parcurs evolutiv serios, însă extrem de susținut și studiat. Ușor naivă, ușor fragilă, ușor ironică, Valentina știe să puncteze trecerile de la disperarea și neputința unei mame aflate în fața prea multor greutăți, la nonșalanța asumării unei noi dolce vita, precum și gustul înfrângerii și procesele degradării personale. Mezina familiei, Silvana Negruțiu, completează prin naivitatea, comicul și speranța permanentă a unui mâine mai bun. E puiul ce studiază din greu și crede cu tărie în gândul că statutul, deja dobândit, de olimpică o să îi fie de folos în viitor. Ghidul de supraviețuire în societate trebuie mai întâi învățat teoretic și ulterior aplicat. Păcat însă că realitatea naște uneori hăuri cu mult prea mari, iar sensibilitatea unui copil se poate preschimba într-o fracțiune de secundă fie în înstrăinare și anxietate, fie în eșec și vulnerabilitate în fața viciului.\r\n\r\nFicțiunea teatrală e completată de muzică live și proiecții ale faptelor reale. Mărturiile câtorva dintre familiile intervievate în perioada de documentare pentru proiect rulează în finalul spectacolului, ca o completare a valorii maximale a emoției. Rovegan șterge granițe, prejudecăți, clișee, idei preconcepute despre cum trebuie abordat fenomenul migrației și al sărăciei luciei și ne ajută să ne convingem încă o dată că nimeni nu pleacă de bine.'),
(6, 2, 'Andreea Verde', 'Vorbim de o seara geroasa in care temperatura se dusese lejer la -10 grade celsius. Pun pariu ca atunci cand batea vantul se simtea ca la -16. Ma duceam spre Replika sa vad spectacolul Rovegan despre care stiam urmatoarele:\r\n\r\nInspirat de cartea Lilianei Nechita \"Cirese amare\" si realizat in urma unei ample documentarii desfasurate anul trecut in localitati din judetul Vaslui, ROVEGAN exploreaza fenomenul migratiei motivate de saracie dinspre Europa de Est spre Occident.\r\n\r\nUrmand traseul unui basm contemporan, spectacolul fictionalizeaza experienta unor mame nevoite sa plece la munca in Italia si interogheaza asupra efectelor unei astfel de dislocari in plan personal si social.\r\n\r\nROVEGAN ia forma unui storytelling colectiv. Este un spectacol care imbina sunetul cu cuvantul si fictionalul cu documentarul pentru a crea spectatorului o incursiune in maruntaiele sistemului capitalist prin intermediul acestui cor al mamelor de pretutindeni.\r\n\r\n...si recunosc ca nu aveam mare tragere de inima. Adica era sambata, mi-era frig si intrarea era libera pe baza de rezervare. Si stiti cum suntem noi, cand ceva e gratis parca nu il bagam asa tare in seama. MARE GRESEALA, mi-e rusine acum. Pentru ca spectacolul asta este in categoria alora NEAPARAT DE VAZUT. Te poarta prin mai multe stari si te duce departe, in cel mai duios mod posibil, vorbind despre niste lucruri documentate indeaproape si extrem de triste.\r\n\r\nSala a fost FULL, oricum, ca sa stiti in cazul in care vreti sa veniti fara sa fi facut o rezervare. De asemenea, sunteti incurajati sa oferiti o donatie simbolica daca v-a placut ceea ce ati vazut.\r\n\r\nSe dau 3 scaune goale si o borna cu \"Bine ati venit in judetul Vaslui\". Avem 3 personaje feminine care-s prezentate drept capre si iezi din bine-cunoscutul basm 3 iezi cucuieti.\r\nCe urmeaza dupa aceasta alegorie este un maraton in care emotiile zboara dintr-un colt intr-altul al scenei. Am vazut, la propriu, lacrimi cazand pe jos.\r\n\r\nPovestea este simpla: mama pleaca in Italia pentru a face rost de bani si a-si sustine familia de la tara. Combinatiile nefericite o aduc in cele mai proaste contexte, dar ea continua sa lupte, sa faca bani-bani-bani pe care sa ii trimita acasa.\r\n\r\nIn paralel fata de acasa isi lasa copilul cu sora mai mica si porneste spre Milano - orasul tuturor posibilitatilor unde nimereste langa un domn batran care o scoate din mizerie. Mezina, ramasa acasa sa studieze, se confrunta cu presiunile din toate partile. Toata lumea incearca sa o faca sa realizeze ca INVATATURA nu iti aduce nimic inapoi si nu tine de foame.\r\n\r\nUn tur de forte cantat, dansat, jucat, transformat in poveste in care CAPRA devine eroul central.\r\nTrebuie sa vedeti aceasta punere in scena pentru ca veti vedea mai multe perspective si moduri de \"a pune problema\". Suntem intr-un montaigne-rousse care nu are buton de oprire? SUNTEM!\r\n\r\nIar video-ul proiectat la final, filmat in Negresti acasa la mai multe familii in care unul sau mai multi membrii sunt plecati sa munceasca in Italia, vine sa topeasca orice inima de gheata. E inuman, o sa spuneti. Si, cu toate astea, extrem de des intalnit.'),
(7, 3, 'Rucsandra Pop', 'https://hydrasociety.com/category/talk-to-the-bomb/');

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `Joaca`
--

CREATE TABLE `Joaca` (
  `id_spectacol` int(11) NOT NULL,
  `id_actor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Salvarea datelor din tabel `Joaca`
--

INSERT INTO `Joaca` (`id_spectacol`, `id_actor`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(2, 9),
(2, 10),
(2, 11),
(3, 12),
(3, 13),
(3, 14);

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `PozeSpectacol`
--

CREATE TABLE `PozeSpectacol` (
  `id` int(11) NOT NULL,
  `id_spectacol` int(11) NOT NULL,
  `locatiepoza` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Salvarea datelor din tabel `PozeSpectacol`
--

INSERT INTO `PozeSpectacol` (`id`, `id_spectacol`, `locatiepoza`) VALUES
(1, 1, 'http://ip.costinsobaru.ro/media/1/poster-phone.jpg'),
(2, 2, 'http://ip.costinsobaru.ro/media/2/poster-phone.jpg'),
(3, 3, 'http://ip.costinsobaru.ro/media/3/poster-phone.jpg');

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `Review`
--

CREATE TABLE `Review` (
  `id_user` int(11) NOT NULL,
  `id_spectacol` int(11) NOT NULL,
  `nota` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Salvarea datelor din tabel `Review`
--

INSERT INTO `Review` (`id_user`, `id_spectacol`, `nota`) VALUES
(1, 2, 4.5),
(1, 3, 5),
(2, 3, 4),
(4, 3, 3);

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `SeTine`
--

CREATE TABLE `SeTine` (
  `id_teatru` int(11) NOT NULL,
  `id_spectacol` int(11) NOT NULL,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Salvarea datelor din tabel `SeTine`
--

INSERT INTO `SeTine` (`id_teatru`, `id_spectacol`, `data`) VALUES
(1, 1, '2017-04-08 11:28:40'),
(1, 1, '2017-04-08 21:00:00'),
(1, 1, '2017-04-10 19:17:45'),
(1, 2, '2017-04-08 12:30:53'),
(1, 2, '2017-04-10 17:58:19'),
(2, 2, '2017-02-17 17:00:00'),
(2, 3, '2017-04-11 05:40:17');

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `Spectacol`
--

CREATE TABLE `Spectacol` (
  `id` int(11) NOT NULL,
  `nume` varchar(64) NOT NULL,
  `descriere` text NOT NULL,
  `trailer` varchar(64) NOT NULL,
  `poster` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Salvarea datelor din tabel `Spectacol`
--

INSERT INTO `Spectacol` (`id`, `nume`, `descriere`, `trailer`, `poster`) VALUES
(1, 'iHamlet', 'H., un adolescent aparent obişnuit, este uimit şi dezamăgit de lumea din jur. Atunci când închide ochii, lumea lui se transformă într-o reţea fabuloasă de gânduri şi imagini pe care le modelează la infinit,până într-o zi, când tot ceea ce părea sigur se scufundă sub o avalanşă de întrebări. O avalanșă de emoții, sunete, gânduri sparte la infinit, care îi aduc pe Hamlet, Ofelia, Laertes, Rosencrantz, Gertrude, Claudius, într-un univers multimedia fabulos cu proiecții holografice pentru a construi un Hamlet personal, un Hamlet al acestei generaţii, un Hamlet de azi.\r\n\r\niHamlet este un proiect cultural dedicat tinerilor, bazat pe un text nou, inspirat mai degrabă din personajul Hamlet decât de subiectul piesei lui William Shakespeare. Împreună cu Teatrul Excelsior, Punctart a creat o experienţă teatrală ilustrată ca un eseu audiovizual despre inadaptare şi refuzul de a se conforma realităţii, dar şi despre conflictul dintre generaţii.\r\n', 'https://www.youtube.com/watch?v=dQYuxEWgVKI', 'http://ip.costinsobaru.ro/media/1/poster-phone.jpg'),
(2, 'Rovegan', 'Inspirat de cartea Lilianei Nechita \"Cireșe amare\" şi realizat ȋn urma unei ample documentării desfăşurate anul trecut ȋn localităţi din judeţul Vaslui, ROVEGAN explorează fenomenul migraţiei motivate de sărăcie dinspre Europa de Est spre Occident.\r\n\r\nUrmând traseul unui basm contemporan, spectacolul ficţionalizează experienţa unor mame nevoite să plece la muncă ȋn Italia şi interoghează asupra efectelor unei astfel de dislocări ȋn plan personal şi social.\r\n\r\nROVEGAN ia forma unui storytelling colectiv. Este un spectacol care ȋmbină sunetul cu cuvântul şi ficţionalul cu documentarul pentru a crea spectatorului o incursiune ȋn măruntaiele sistemului capitalist prin intermediul acestui cor al mamelor de pretutindeni.\r\n\r\nGO WEST. platformă de teatru și dezbatere iși propune să atragă atenția opiniei publice asupra fenomenului migrației și a impactului acestuia asupra societății.\r\n\r\n\r\n\"Pentru cine are contact cu această Românie, tăcută şi ignorată, care se destramă încet, ROVEGAN este un spectacol cutremurător. Pentru ceilalţi, poate fi o revelaţie.\r\nCatinca Draganescu a reuşit o distribuţie excepţională în ROVEGAN, cu trei actriţe care realizează un tur de forţă, combinînd modalităţi diferite de joc, de la scene dramatice la naraţiuni corale sau muzică live.”\r\nOana Stoica, DILEMA veche\r\n', '', 'http://ip.costinsobaru.ro/media/2/poster-phone.jpg'),
(3, 'Talk to the Bomb', 'Vama Veche. Vara lui 2001. Destinele lui Sașa, Alex și Voinicul se intersectează în umbra\r\n\r\namenințătoare a unui 11 septembrie care încă nu s-a întâmplat. \r\n\r\nRevelion 2002. Un Revelion ratat. Iubirile se surpă. Lumile se prăbușesc sub greutatea proiecțiilor.\r\n\r\nPână în vară. În vară, când reîncepe tot. \r\n\r\n”Ce văd acum la televizor mi se pare urmarea firească a faptului că ne-am întâlnit. Send.”', 'https://www.youtube.com/watch?v=FgL2oy8ZjPY', 'http://ip.costinsobaru.ro/media/3/poster-phone.jpg');

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `Teatru`
--

CREATE TABLE `Teatru` (
  `id` int(11) NOT NULL,
  `nume` varchar(64) NOT NULL,
  `descriere` text NOT NULL,
  `locatie` varchar(128) NOT NULL,
  `gps_x` float NOT NULL,
  `gps_y` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Salvarea datelor din tabel `Teatru`
--

INSERT INTO `Teatru` (`id`, `nume`, `descriere`, `locatie`, `gps_x`, `gps_y`) VALUES
(1, 'Excelsior', 'De un sfert de veac teatrul nostru este împreună cu cel mai fidel public – copii şi adolescenţi din această mare urbe. Excelsior a fost înfiinţat la 1 aprilie 1990, actul de naştere fiindu-i semnat de ministrul Culturii de atunci, cărturarul Andrei Pleşu. „...Ştiu bine că nu meritul meu a fost atunci când s-a înfiinţat teatrul. A fost, în primul rând, meritul maestrului Ion Lucian şi a fost meritul împrejurărilor. Eram ministrul Culturii într-un moment în care nimeni nu se ocupa de cultură şi am făcut ce am vrut...”, spunea Andrei Pleşu pe data de 1 aprilie 2015, la cea de-a 25-a aniversare a Teatrului Excelsior.', 'Strada Academiei 28, București 010016', 44.4378, 26.0987),
(2, 'Logos', 'Teatru', 'Strada Biserica Amzei, Nr 5-7', 24.4566, 44.5566);

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `Useri`
--

CREATE TABLE `Useri` (
  `id` int(11) NOT NULL,
  `nume` varchar(64) NOT NULL,
  `prenume` varchar(64) NOT NULL,
  `email` varchar(64) NOT NULL,
  `parola` varchar(128) NOT NULL,
  `userRole` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Salvarea datelor din tabel `Useri`
--

INSERT INTO `Useri` (`id`, `nume`, `prenume`, `email`, `parola`, `userRole`) VALUES
(1, 'Sobaru', 'Costin', 'costin96@gmail.com', 'parola', 0),
(2, 'Stroe', 'Geanina', 'geanina.mariana17@gmail.com', 'geanina', 1),
(3, 'Copaci', 'Diana', 'copaci.diana@yahoo.com', 'diana', 1),
(4, 'Nazare', 'Emanuel', 'emanuelnazare@yahoo.com', 'emanuel', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Actori`
--
ALTER TABLE `Actori`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Comentarii`
--
ALTER TABLE `Comentarii`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_spectacol` (`id_spectacol`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `Critici`
--
ALTER TABLE `Critici`
  ADD PRIMARY KEY (`id`),
  ADD KEY `critici_fk_id_spectacol` (`id_spectacol`);

--
-- Indexes for table `Joaca`
--
ALTER TABLE `Joaca`
  ADD PRIMARY KEY (`id_spectacol`,`id_actor`),
  ADD KEY `id_actor` (`id_actor`);

--
-- Indexes for table `PozeSpectacol`
--
ALTER TABLE `PozeSpectacol`
  ADD PRIMARY KEY (`id`),
  ADD KEY `poze_fk_id_spectacol` (`id_spectacol`);

--
-- Indexes for table `Review`
--
ALTER TABLE `Review`
  ADD PRIMARY KEY (`id_user`,`id_spectacol`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_spectacol` (`id_spectacol`);

--
-- Indexes for table `SeTine`
--
ALTER TABLE `SeTine`
  ADD PRIMARY KEY (`id_teatru`,`id_spectacol`,`data`),
  ADD KEY `id_teatru` (`id_teatru`),
  ADD KEY `id_spectacol` (`id_spectacol`);

--
-- Indexes for table `Spectacol`
--
ALTER TABLE `Spectacol`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Teatru`
--
ALTER TABLE `Teatru`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Useri`
--
ALTER TABLE `Useri`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Actori`
--
ALTER TABLE `Actori`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `Comentarii`
--
ALTER TABLE `Comentarii`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `Critici`
--
ALTER TABLE `Critici`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `PozeSpectacol`
--
ALTER TABLE `PozeSpectacol`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `Spectacol`
--
ALTER TABLE `Spectacol`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `Teatru`
--
ALTER TABLE `Teatru`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `Useri`
--
ALTER TABLE `Useri`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Restrictii pentru tabele sterse
--

--
-- Restrictii pentru tabele `Comentarii`
--
ALTER TABLE `Comentarii`
  ADD CONSTRAINT `comentarii_fk_id_spectacol` FOREIGN KEY (`id_spectacol`) REFERENCES `Spectacol` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `comentarii_fk_id_useri` FOREIGN KEY (`id_user`) REFERENCES `Useri` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Restrictii pentru tabele `Critici`
--
ALTER TABLE `Critici`
  ADD CONSTRAINT `critici_fk_id_spectacol` FOREIGN KEY (`id_spectacol`) REFERENCES `Spectacol` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Restrictii pentru tabele `Joaca`
--
ALTER TABLE `Joaca`
  ADD CONSTRAINT `Joaca_ibfk_2` FOREIGN KEY (`id_actor`) REFERENCES `Actori` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `joaca_fk_id_spectacol` FOREIGN KEY (`id_spectacol`) REFERENCES `Spectacol` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Restrictii pentru tabele `PozeSpectacol`
--
ALTER TABLE `PozeSpectacol`
  ADD CONSTRAINT `poze_fk_id_spectacol` FOREIGN KEY (`id_spectacol`) REFERENCES `Spectacol` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Restrictii pentru tabele `Review`
--
ALTER TABLE `Review`
  ADD CONSTRAINT `review_fk_id_spectacol` FOREIGN KEY (`id_spectacol`) REFERENCES `Spectacol` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `review_fk_id_useri` FOREIGN KEY (`id_user`) REFERENCES `Useri` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Restrictii pentru tabele `SeTine`
--
ALTER TABLE `SeTine`
  ADD CONSTRAINT `setine_fk_id_spectacol` FOREIGN KEY (`id_spectacol`) REFERENCES `Spectacol` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `setine_fk_id_teatru` FOREIGN KEY (`id_teatru`) REFERENCES `Teatru` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
