# Документация к библиотеке Bon26 Engine

## Обзор

Bon26 Engine — это простая игровая библиотека на Kotlin, предоставляющая основные компоненты для создания 2D-игр, включая графику, обработку ввода, звук, систему частиц, генерацию шума и управление игровыми объектами.

## Основные компоненты

### Класс: Engine

Основной класс движка, предоставляющий доступ ко всем компонентам.

#### Свойства:
- `Graphics: SimpleGraphics` - система отрисовки
- `Input: Input` - система обработки ввода
- `Audio: AudioEngine` - аудио система
- `Camera: Camera` - управление камерой
- `Timer: Timer` - таймер
- `SceneManager: SceneManager` - управление сценами
- `Particles: ParticleSystem` - система частиц

#### Методы:

- `initialize(width: Int, height: Int, title: String = "Unnamed")`
  Инициализирует движок, создает окно заданного размера с указанным заголовком.

- `createObject(elementId: Int, hitbox: Hitbox, tag: String = ""): GameObject`
  Создает игровой объект с указанным ID, хитбоксом и тегом.

- `getObjectAtPoint(x: Int, y: Int): GameObject?`
  Возвращает объект в указанной точке или null, если объекта нет.

- `getIntersections(elementId: Int): List<GameObject>`
  Возвращает список объектов, пересекающихся с объектом указанного ID.

- `GameLoop(updateCallback: (Double) -> Unit)`
  Запускает основной игровой цикл с callback-функцией обновления.

- `removeMarkedObjects()`
  Удаляет объекты, помеченные для удаления.

- `cleanup()`
  Освобождает ресурсы движка.

### Класс: SimpleGraphics

Предоставляет функциональность для отрисовки графики.

#### Методы:

- `createWindow(width: Int, height: Int, title: String)`
  Создает окно для отрисовки.

- `setWindowTitle(title: String)`
  Устанавливает заголовок окна.

- `setWindowIcon(imagePath: String)`
  Устанавливает иконку окна.

- `drawRichText(text: String, x: Int, y: Int, fontName: String, fontSize: Int, color: Color, style: Int, zIndex: Int): Int`
  Отрисовывает текстовый элемент и возвращает его ID.

- `drawImage(imagePath: String, x: Int, y: Int, filter: ImageFilter, scaleX: Double, scaleY: Double, rotation: Double, zIndex: Int): Int`
  Отрисовывает изображение и возвращает его ID.

- `drawRect(x: Int, y: Int, width: Int, height: Int, outlineColor: Color, isFilled: Boolean, fillColor: Color, strokeWidth: Float, zIndex: Int): Int`
  Отрисовывает прямоугольник и возвращает его ID.

- `removeElement(id: Int): Boolean`
  Удаляет элемент по ID.

- `updateElementPosition(id: Int, x: Int, y: Int): Boolean`
  Обновляет позицию элемента.

- `updateElementScale(id: Int, scaleX: Double, scaleY: Double): Boolean`
  Обновляет масштаб элемента.

- `updateElementRotation(id: Int, rotation: Double): Boolean`
  Обновляет поворот элемента.

- `updateElementColor(id: Int, color: Color): Boolean`
  Обновляет цвет элемента.

- `updateElement(id: Int, x: Int?, y: Int?, scaleX: Double?, scaleY: Double?, rotation: Double?, zIndex: Int?): Boolean`
  Обновляет параметры элемента.

- `getElementIds(type: ElementType): List<Int>`
  Возвращает список ID элементов указанного типа.

- `getElementInfo(id: Int): ElementInfo?`
  Возвращает информацию об элементе.

- `getElementZIndex(id: Int): Int?`
  Возвращает Z-индекс элемента.

- `update()`
  Обновляет экран.

- `clear(color: Color)`
  Очищает экран указанным цветом.

- `clearAllElements()`
  Удаляет все элементы.

- `setCamera(camera: Camera)`
  Устанавливает камеру для отрисовки.

- `waitForClose()`
  Ожидает закрытия окна.

### Класс: Input

Обрабатывает пользовательский ввод (клавиатура и мышь) с использованием enum'ов для кнопок.

#### Перечисления:
- `MouseButton` - кнопки мыши (LEFT, MIDDLE, RIGHT, BACK, FORWARD)
- `Key` - клавиши клавиатуры (все стандартные клавиши)

#### Методы:

- `setupInput(component: JComponent)`
  Настраивает обработку ввода для компонента.

- `update(deltaTime: Double)`
  Обновляет состояния ввода.

- `isKeyPressed(key: Key): Boolean`
  Проверяет, нажата ли клавиша.

- `isKeyJustPressed(key: Key): Boolean`
  Проверяет, была ли клавиша только что нажата.

- `isKeyJustReleased(key: Key): Boolean`
  Проверяет, была ли клавиша только что отпущена.

- `getKeyHoldTime(key: Key): Double`
  Возвращает время удержания клавиши.

- `isKeyHoldInterval(key: Key, interval: Double, reset: Boolean = true): Boolean`
  Проверяет интервальное нажатие клавиши.

- `isMouseButtonPressed(button: MouseButton): Boolean`
  Проверяет, нажата ли кнопка мыши.

- `isMouseButtonJustPressed(button: MouseButton): Boolean`
  Проверяет, была ли кнопка мыши только что нажата.

- `isMouseButtonJustReleased(button: MouseButton): Boolean`
  Проверяет, была ли кнопка мыши только что отпущена.

- `getMouseButtonHoldTime(button: MouseButton): Double`
  Возвращает время удержания кнопки мыши.

- `isMouseButtonHoldInterval(button: MouseButton, interval: Double, reset: Boolean = true): Boolean`
  Проверяет интервальное нажатие кнопки мыши.

#### Свойства:

- `mouseX: Int` - текущая X-координата мыши
- `mouseY: Int` - текущая Y-координата мыши

### Класс: AudioEngine

Управляет воспроизведением звуков.

#### Методы:

- `loadSound(filePath: String): AudioClip?`
  Загружает звуковой файл.

- `stopAll()`
  Останавливает все звуки.

- `cleanup()`
  Освобождает ресурсы аудио системы.

### Класс: AudioClip

Представляет звуковой клип.

#### Методы:

- `play()`
  Воспроизводит звук.

- `pause()`
  Приостанавливает воспроизведение.

- `stop()`
  Останавливает воспроизведение.

- `setVolume(volume: Float)`
  Устанавливает громкость (0.0 - 1.0).

- `setSpeed(speed: Float)`
  Устанавливает скорость воспроизведения (0.5 - 2.0).

- `applyEcho()`
  Применяет эффект эха.

### Класс: Camera

Управление камерой с поддержкой плавного следования и ограничений.

#### Свойства:
- `x: Float`, `y: Float` - позиция камеры
- `zoom: Float` - масштаб
- `camTarget: GameObject?` - цель камеры
- `cameraSmoothness: Float` - плавность движения
- `cameraBounds: RectHitbox?` - границы камеры
- `isTargetinCenter: Boolean?` - центрирование на цели
- `isCameraSmooth: Boolean?` - плавное движение

#### Методы:
- `setCameraTarget(obj: GameObject)`
  Устанавливает цель для камеры.

- `setCameraBounds(x: Int, y: Int, width: Int, height: Int)`
  Устанавливает границы для камеры.

- `setCameraPosition(x: Float, y: Float)`
  Устанавливает позицию камеры.

- `worldToScreen(worldX: Float, worldY: Float): Point`
  Преобразует мировые координаты в экранные.

- `screenToWorld(screenX: Int, screenY: Int): Point2D.Float`
  Преобразует экранные координаты в мировые.

- `changeZoom(newZoom: Float, centerX: Int, centerY: Int)`
  Изменяет масштаб с центром в указанной точке.

### Класс: GameObjectManager

Управляет игровыми объектами.

#### Методы:

- `addGameObject(elementId: Int, hitbox: Hitbox, tag: String = ""): GameObject`
  Добавляет игровой объект.

- `removeGameObject(elementId: Int)`
  Удаляет игровой объект.

- `removeMarkedObjects()`
  Удаляет объекты, помеченные для удаления.

- `getGameObject(elementId: Int): GameObject?`
  Возвращает объект по ID.

- `getObjectAtPoint(x: Int, y: Int): GameObject?`
  Возвращает объект в указанной точке.

- `getIntersections(elementId: Int): List<GameObject>`
  Возвращает пересекающиеся объекты.

- `updateHitboxPosition(elementId: Int, x: Int, y: Int)`
  Обновляет позицию хитбокса объекта.

### Класс: GameObject

Представляет игровой объект.

#### Свойства:

- `elementId: Int` - ID объекта
- `hitbox: Hitbox` - хитбокс объекта
- `tag: String` - тег объекта
- `isEnabled: Boolean` - флаг активности объекта
- `isMarkedForDeletion: Boolean` - флаг для удаления
- `x: Int` - X-координата объекта (из хитбокса)
- `y: Int` - Y-координата объекта (из хитбокса)
- `width: Int` - ширина объекта (из хитбокса)
- `height: Int` - высота объекта (из хитбокса)

#### Методы:

- `containsPoint(x: Int, y: Int): Boolean`
  Проверяет, содержит ли объект точку.

- `intersects(other: GameObject): Boolean`
  Проверяет пересечение с другим объектом.

### Класс: ParticleSystem

Система частиц для создания визуальных эффектов.

#### Методы:

- `emit(x: Float, y: Float, count: Int, minSize: Int, maxSize: Int, minLifetime: Double, maxLifetime: Double, minVelocityX: Float, maxVelocityX: Float, minVelocityY: Float, maxVelocityY: Float, color: Color, zIndex: Int)`
  Создает частицы в указанной позиции.

- `update(deltaTime: Double)`
  Обновляет состояние частиц.

- `clear()`
  Очищает все частицы.

### Класс: Noise

Генерация шума для процедурного контента.

#### Типы шума:
- `NoiseType.VALUE` - Value noise
- `NoiseType.SIMPLEX` - Simplex noise

#### Методы:

- `getNoise(x: Double, y: Double): Double`
  Возвращает значение шума в точке.

- `fBm(x: Double, y: Double, octaves: Int, lacunarity: Double, gain: Double): Double`
  Возвращает значение fractional Brownian motion.

### Класс: SceneManager

Управление сценами игры.

#### Методы:

- `registerScene(name: String, scene: Scene)`
  Регистрирует сцену.

- `switchToScene(name: String, transition: Boolean = false)`
  Переключает на указанную сцену.

- `update(deltaTime: Double)`
  Обновляет текущую сцену.

- `getCurrentScene(): Scene?`
  Возвращает текущую сцену.

- `cleanup()`
  Очищает все сцены.

### Абстрактный класс: Scene

Базовый класс для создания сцен.

#### Методы:

- `onCreate()` - вызывается при создании сцены
- `onUpdate(deltaTime: Double)` - вызывается при обновлении сцены
- `onDestroy()` - вызывается при уничтожении сцены
- `onPause()` - вызывается при паузе сцены
- `onResume()` - вызывается при возобновлении сцены

### Класс: Timer

Таймер для измерения времени.

#### Методы:

- `start()` - запускает таймер
- `pause()` - приостанавливает таймер
- `resume()` - возобновляет таймер
- `getElapsedMillis(): Double` - возвращает прошедшее время в миллисекундах

### Объект: SafetyUtils

Утилиты для безопасной работы с коллекциями.

#### Методы:

- `getSafe(list: List<T>, index: Int): T?` - безопасное получение элемента списка
- `getSafe(map: Map<K, V>, key: K): V?` - безопасное получение элемента map
- `removeSafe(list: MutableList<*>, index: Int): Boolean` - безопасное удаление элемента

## Структуры данных

- `Hitbox` - абстрактный класс для хитбоксов
- `RectHitbox` - прямоугольный хитбокс
- `ElementType` - перечисление типов элементов (TEXT, IMAGE, RECT)
- `ElementInfo` - информация об элементе
- `ImageFilter` - фильтры изображений (NONE, ANTIALIASING)

## Пример использования

```kotlin
fun main() {
    val engine = Engine()
    engine.initialize(800, 600, "My Game")
    
    // Создание объекта
    val hitbox = RectHitbox(100, 100, 50, 50)
    val player = engine.createObject(1, hitbox, "player")
    
    // Настройка камеры
    engine.Camera.setCameraTarget(player)
    engine.Camera.isTargetinCenter = true
    engine.Camera.isCameraSmooth = true
    engine.Camera.cameraSmoothness = 10f
    
    // Игровой цикл
    engine.GameLoop { deltaTime ->
        // Обработка ввода
        if (engine.Input.isKeyPressed(Key.SPACE)) {
            // Действие при нажатии пробела
        }
        
        if (engine.Input.isMouseButtonJustPressed(MouseButton.LEFT)) {
            // Создание частиц при клике
            engine.Particles.emit(
                engine.Input.mouseX.toFloat(), 
                engine.Input.mouseY.toFloat(),
                count = 20,
                color = Color.RED,
                zIndex = 1000
            )
        }
        
        // Отрисовка
        engine.Graphics.clear(Color.WHITE)
        engine.Graphics.drawRect(player.x, player.y, 50, 50, Color.RED, true)
        
        // Обновление
        engine.update()
    }
    
    engine.cleanup()
}
```

---

ДОКУМЕНТАЦИЯ МОЖЕТ ИМЕТЬ НЕТОЧНОСТИ. ДЛЯ БОЛЬШЕГО ПОНИМАНИЯ СЛЕДУЕТ ОБРАТИТЬСЯ К ИСХОДНОМУ КОДУ И ДРУГИМ ПРИМЕРАМ!
