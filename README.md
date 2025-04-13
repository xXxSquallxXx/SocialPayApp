SocialPayApp — это мобильное приложение на Android, разработанное на Kotlin для удобного совершения донатов через Систему быстрых платежей (СБП) с использованием QR-кодов. Пользователь может сканировать QR-код с видео на другом устройстве, вводить сумму пожертвования и отправлять платёж через API СБП, а также просматривать историю транзакций в HistoryScreen, где данные отображаются с помощью LazyColumn для эффективной прокрутки списка. Приложение создано с применением современных технологий: Jetpack Compose для декларативного UI, CameraX и ML Kit Barcode Scanning для работы с камерой и сканирования QR-кодов, Room для локального хранения транзакций, Retrofit для сетевых запросов, Dagger 2 для внедрения зависимостей, а также Coroutines и StateFlow для асинхронного управления состоянием. Навигация реализована через NavHost, обеспечивая плавные переходы между экранами Home, Scan, Payment и History.
