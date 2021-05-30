# n11Project
DOCKER NEDIR?

Docker, uygulamaların farklı ortamlarda çalıştırılması sürecinde kullanılan bir platformdur. Uygulamamızı derler, ölçeklendirir, paketler ve dağıtmamızı sağlar. Docker içerisinde barındırdığı container yapısını ve Docker Hub üzerinden indirilen image’ları kullanarak ürünü farklı işletim sistemi, versiyon ve environment’larla birbirinden izole şekilde çalıştırır; yeterli kaynak ve gerekli yapılandırmalar ile birlikte kullanıldığında, yapısal problemleri ortadan kaldırarak uygulamalarımızı stabil bir şekilde çalışabilecek hale getirir. Docker, Windows, Mac ve Linux üzerine kurulabilir. Java, C#, Python, Php, Grovy, Ruby gibi pek çok dili destekler.

SELENIUM GRID NEDIR?

Selenyum Grid Selenium-Grid testlerimizi paralel olarak farklı ortamlarda, farklı tarayıcılarda ve farklı sayılarda koşmamıza imkân tanıyan bir sunucudur. Yani aynı anda farklı tarayıcılarda ve işletim sistemlerinde birden fazla test çalıştırabiliriz. Kullanmamız için sebeplerimiz; Ölçeklendirerek farklı işletim sistemlerinin farklı versiyonları olan tarayıcılarda koşabilir. Zaman Tasarrufu sağlar. Sunucuyu, ortamı çok hızlı bir şekilde ayağa kaldırır. Testleri paralel olarak çalıştırmak için birden fazla makine kullanarak testleri koşması hızlandırılabilir.

Selenium-Grid Hub ve Node’dan oluşur. Sunucu üzerinde sadece bir Hub ve birden fazla Node olabilir. Hub: Testin yürütülmesi gereken, istenilen tarayıcıyı, tarayıcı versiyonu ve işletim sistemi hakkında ki bilgileri alır ve testleri bunları sağlayan destekleyen makineye yani node’a yönlendirir. Bir örnekle Hub’ı bir müdür ve Nodeları çalışan olarak düşünelim. Yapılacak bir iş olduğunu ve müdürün bu iş için uygun olan çalışanı bulup elindeki bu işi yönlendirdiğini düşünelim. Hub ve Node arasındaki ilişki bu ilişkiye benzer.Bir işçi(Node) seçildikten sonra Selenium test komutları önce Hub(müdür)’a ve Hub da atanmış olan Node makineye yönlendirir. Node tarayıcıyı başlatır ve aldığı Selenium test komutlarını koşar.


Docker ile lokal makinede Selenium Grid ortamı kurulması ve projenin çalıştırılması;

•	CMD üzerinden “docker network create grid” ile bir network oluşturulur. Böylece container'lar birbirleri ile iletişim kurabilecekler.

•	“docker run -d -p 4444:4444 --net grid  --name selenium-hub selenium/hub:3.11.0-dysprosium” ile bir hub oluşturulur.

•	“docker ps” komutu ile containerların durumuna bakarak hub’ın ayağa kalktığını kontrol edebiliriz.

•	http://localhost:4444/grid/console ile tarayıcı üzerinden de kontrolünü sağlayabiliriz.

•	“docker run -d --net grid -e HUB_HOST=selenium-hub -v /dev/shm:/dev/shm selenium/node-chrome:3.11.0-dysprosium”  ile node oluşturulur. Docker deamon bu komut ile birlikte container’ı başlatıp id’sini ekrana basmalı.

•	“docker ps” ile hub ve node’larımızın durumlarına bakılabilir. “docker network inspect grid” ile de eklediğimiz container’ları görebiliriz.

•	Bu adımlar sonrasında “Docker” ile lokal makinede “Selenium Grid” ortamı kurulmuş olmaktadır. 

•	Proje GitHub’dan clone edilir ve çalıştırılır.
