package org.bon26.engine

abstract class Scene {
    open fun onCreate() {}
    open fun onUpdate(deltaTime: Double) {}
    open fun onDestroy() {}
    open fun onPause() {}
    open fun onResume() {}
}

class SceneManager(private val engine: Engine) {
    private val scenes = mutableMapOf<String, Scene>()
    private var currentScene: Scene? = null
    private var nextScene: Scene? = null
    private var isTransitioning = false

    fun registerScene(name: String, scene: Scene) {
        scenes[name] = scene
    }

    fun switchToScene(name: String, transition: Boolean = false) {
        val scene = scenes[name] ?: throw IllegalArgumentException("Scene '$name' not found")

        if (transition) {
            // Плавный переход между сценами
            nextScene = scene
            isTransitioning = true
            currentScene?.onPause()
        } else {
            // Мгновенная смена сцены
            currentScene?.onDestroy()
            currentScene = scene
            scene.onCreate()
        }
    }

    fun update(deltaTime: Double) {
        if (isTransitioning) {
            TODO("добавить плавный переход ээ наверное хз, может быть полезло")
            completeTransition()
        }

        currentScene?.onUpdate(deltaTime)
    }

    private fun completeTransition() {
        currentScene?.onDestroy()
        currentScene = nextScene
        nextScene = null
        isTransitioning = false
        currentScene?.onResume()
    }

    fun getCurrentScene(): Scene? = currentScene

    fun cleanup() {
        currentScene?.onDestroy()
        scenes.clear()
    }
}