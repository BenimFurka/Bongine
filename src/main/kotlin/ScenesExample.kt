package org.bon26.game

import org.bon26.engine.Engine
import org.bon26.engine.GameObject
import org.bon26.engine.Key
import org.bon26.engine.Scene
import org.bon26.engine.MouseButton
import org.bon26.engine.RectHitbox
import java.awt.Color

val engine = Engine()
// Инициализация в main
fun main() {
    engine.initialize(800, 600, "My Game")

    // Регистрируем сцены
    engine.SceneManager.registerScene("menu", MainMenuScene())
    engine.SceneManager.registerScene("game", GameScene())

    // Запускаем начальную сцену
    engine.SceneManager.switchToScene("menu")

    engine.GameLoop { deltaTime ->
        // Общая логика, если нужна
    }
}

// Главное меню
private class MainMenuScene : Scene() {
    private var startButtonId: Int = -1

    override fun onCreate() {
        // Создаем элементы интерфейса
        startButtonId = engine.Graphics.drawRect(100, 100, 200, 50,
            Color.BLUE, true, Color.BLUE, zIndex = 10)

        engine.Graphics.drawRichText("Start Game", 150, 130,
            color = Color.WHITE, fontSize = 20, zIndex = 11)

        engine.createObject(startButtonId, RectHitbox(100, 100, 200, 50), "startButtonId")
    }

    override fun onUpdate(deltaTime: Double) {
        // Проверяем клик по кнопке
        if (engine.Input.isMouseButtonJustPressed(MouseButton.LEFT)) {
            val obj = engine.getObjectAtPoint(engine.Input.mouseX, engine.Input.mouseY)
            if (obj?.elementId == startButtonId) {
                engine.SceneManager.switchToScene("game")
            }
        }
    }

    override fun onDestroy() {
        // Очищаем ресурсы
        engine.Graphics.clearAllElements()
    }
}



// Игровая сцена
private class GameScene : Scene() {
    private var playerId: Int = -1
    private var player: GameObject? = null

    override fun onCreate() {
        // Создаем игрока
        playerId = engine.Graphics.drawRect(100, 100, 50, 50,
            Color.RED, true, Color.RED, zIndex = 5)
        val playerHitbox = RectHitbox(100, 100, 50, 50)

        player = engine.createObject(playerId, playerHitbox, "player")

    }

    override fun onUpdate(deltaTime: Double) {
        // Логика игры
        var playerX = player!!.x
        var playerY = player!!.y

        if (engine.Input.isKeyPressed(Key.W)) playerY -= 5
        else if (engine.Input.isKeyPressed(Key.S)) playerY += 5
        if (engine.Input.isKeyPressed(Key.A)) playerX -= 5
        else if (engine.Input.isKeyPressed(Key.D)) playerX += 5

        engine.Graphics.updateElementPosition(playerId, playerX, playerY)
        engine.gameObjectManager.updateHitboxPosition(playerId, playerX, playerY)
    }

    override fun onDestroy() {
        engine.Graphics.clearAllElements()
    }
}