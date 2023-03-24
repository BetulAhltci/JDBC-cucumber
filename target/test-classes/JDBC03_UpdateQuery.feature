Feature:JDBC baglantisi uzerinden update sorgusu yapma

  #Bir yönetici olarak DataBase üzerinden customer_addresses tablosundaki
# istenen Customer'in adress bilgisini güncelleyebilmeli ve güncellenen
# adresi customer_addresses tablosunda oldugunu dogrulayabilmeliyim
@address
  Scenario Outline:

    * Database baglantisi kurulur
    * "<id>" degeri verilen customerin "<addressi>" guncellenir
    * custumer addresss tablosundaki "address" bilgileri istenir
    * customerin "<addressi>" guncelledigi dogrulanir
    * Data baglantisi kapatilir

    Examples:
      |id|addressi|
      |1|Kadikoy|
