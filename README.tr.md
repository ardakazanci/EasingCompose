# Easing

[Türkçe](README.tr.md) | [English](README.en.md)

Easing, Jetpack Compose için tasarlanmış sade, şık ve ölçeklenebilir bir motion library fikridir.
Amacı yalnızca easing fonksiyonlarını bir araya getirmek değil; hareketin ekranda nasıl hissettirdiğini anlaşılır, canlı ve üretime yakın bir demo deneyimiyle göstermektir.

## Mimari

Bu proje üç parçalı bir mimari üzerine kurulur:

- `:easing-core`: Android ya da Compose bilmeyen saf Kotlin katmanı. Easing tanımları, matematik fonksiyonları, metadata ve curve sample üretimi burada yaşar.
- `:easing-compose`: Core tanımlarını Jetpack Compose `Easing` değerlerine dönüştüren ince adapter katmanı.
- `:app`: Kullanıcının easing seçebildiği, eğriyi görebildiği, animasyonu deneyebildiği ve Compose kullanım örneğini okuyabildiği demo uygulaması.

Library kodunun UI framework'üne bağımlı olmamasını istiyoruz.
Bu yüzden matematik ve katalog bilgisi içeride, Compose ise dış katmanda kalır.
Bu ayrım projeyi test etmeyi kolaylaştırır, yayınlanabilir bir library haline getirir ve ileride Kotlin Multiplatform gibi hedeflere kapı bırakır.

## Demo Deneyimi

Demo app bir tanıtım sayfası değil, doğrudan kullanılabilir küçük bir motion lab olarak düşünülür.
İlk ekranda kullanıcı şunları görebilir:

- easing ailesi ve easing seçimi
- canlı curve grafiği
- position, scale ve alpha preview
- duration slider
- run, reverse ve reset kontrolleri
- Compose code snippet

Arayüzün hedefi: az konuşan, hızlı anlatan, dokunduğunda ne yaptığını hemen hissettiren temiz bir deneyim.

## Önizleme

<video src="docs/media/easing.mp4" controls muted loop playsinline width="720"></video>

[Demo videosunu izle](docs/media/easing.mp4)

## Kullanım

```kotlin
val value by animateFloatAsState(
    targetValue = if (expanded) 1f else 0f,
    animationSpec = tween(
        durationMillis = 700,
        easing = ComposeEasings.EaseOutExpo
    ),
    label = "easeOutExpo"
)
```

## Uygulama Notu

Easing, yaygın animasyon zamanlama denklemlerini Kotlin'de doğrudan uygular ve bunları küçük bir Jetpack Compose adapter katmanı üzerinden sunar.

## Geliştirme

Core testlerini çalıştırmak ve debug demo app'i build etmek için:

```sh
./gradlew :easing-core:test :app:testDebugUnitTest :app:assembleDebug
```

## Lisans

Apache License, Version 2.0 ile lisanslanmıştır.
Detaylar için [LICENSE.md](LICENSE.md).
