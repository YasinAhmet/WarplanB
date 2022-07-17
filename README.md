Kodu kendi oyunlarınızda kullanabilir, değiştirebilir, çalıştırabilir ve commercial olmamak suretiyle herhangi bir türlü yararlanabilirsiniz. Tüm kodlar şahsıma aittir, 7-8 aylık bir süreç içerisinde yazılmıştır. Herhangi bir oyun motoru vesaire kullanılmamış olmakla birlikte Java'da yazılmıştır.

![alt text](https://github.com/YasinAhmet/WarplanB/blob/9638f0d1dc9676ebd90fd8d1e4edf52f190d1e5b/unknown.png?raw=true)


//ESKI KISIM
Nasıl oynanır:

Haritalar ve birlikler:
Data kısmındaki Hexagons.txt ve Divisions.txt dosyalarının içeriğini değiştirerek farklı haritalar yapabilirsiniz. Birlikler divisions.txt dosyasından okunur.
Her birliğin şuna benzer bir satırı vardır:

side:first,men:100,attack:10,defence:5,morale:A,row:0,col:0;

2 taraf vardır, birisi "first" yani Almanlar, ikincisi ise "second" yani Ruslar. Her birliğin kendi statları bulunur, bunları buradan istediğiniz gibi ayarlayabilrisiniz.
Row ve col birliğin haritadaki konumunu belirtir. Bu dosyanın en sonunda da bu bulunmalıdır:

saveside:second;

Bu kod da oyunun kaydedildiği tarafı belirtir.


Hexagons.txt dosyasında da harita oluşturulur. Bu dosyanın içeriği şuna benzer:

(1. Satır)kkkggggcgk
(2. Satır)kkgggggcck
(3. Satır)ccccsgggkk
(4. Satır)ssssssgggg
(5. Satır)ssssssssss

S(Sea) denizi, K(?) şehiri, G(Grassland) çayırı, C(?) ormanlığı temsil eder. Yukardan aşağı col değeri, soldan sağa ise row değeri kullanılır. Örneğin;
Row'u 1 olan, col'u 2 olan bir birlik 2. satırın 3. altıgeninde çıkar(row/col+1 olarak, yani 0 değeri en soldur).

Her altıgen tipinin savunma değeri vardır. Şehirin 15, ormanlığın 10, çayırın 5, denizin ise 0'dır. Denizde birlik yürütülemez.

Oynanış:
Oyunun teknik olarak bilinmesi gereken konuları bu kadardır. Oyunu oynarken haritadan sol tık ile altıgen seçebilir, sağda çıkan panelden de aynı şekilde sol tık ile birlik seçebilirsiniz.
Seçilen birlikler sağ tık ile haritada yürütülebilir, yürütülen yerde düşman birlik varsa ona saldırır. Hareket etmek 5 hareket puanı, saldırmak 10 hareket puanı alır.

Sadece saveside'da yazan tarafın birlikleri oynanabilir. Şu an oyunda yapay zeka yoktur.
