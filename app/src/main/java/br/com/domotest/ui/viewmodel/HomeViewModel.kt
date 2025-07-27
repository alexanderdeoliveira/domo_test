package br.com.domotest.ui.viewmodel

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager
import android.view.WindowMetrics
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.domotest.domain.GetTodoListUseCase
import br.com.domotest.domain.GetTodoUseCase
import br.com.domotest.domain.SaveTodoUseCase
import br.com.domotest.extensions.networkIsConnected
import br.com.domotest.model.RGBColor
import br.com.domotest.model.TodoModel
import br.com.domotest.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

const val TEXT_CHANGE_DELAY_IN_MILLIS = 4000L

class HomeViewModel(
    private val applicationContext: Context,
    private val getTodoListUseCase: GetTodoListUseCase,
    private val saveTodoUseCase: SaveTodoUseCase,
    private val getTodoUseCase: GetTodoUseCase
): BaseViewModel() {

    private var animator: AnimatorSet? = null

    private val _currentTodo = MutableLiveData<TodoModel>()
    val currentTodo: LiveData<TodoModel> = _currentTodo

    private val _textViewColor = MutableLiveData<RGBColor>()
    val textViewColor: LiveData<RGBColor> = _textViewColor

    private val _todoList = MutableLiveData<List<TodoModel>>()
    val todoList: LiveData<List<TodoModel>> = _todoList

    private val _mockEnabled = MutableLiveData<Boolean>()

    private var currentTodoPosition: Int = 0

    fun getTodoList() {
        launch {
            if (applicationContext.networkIsConnected()) {
                showLoading()
                val todoList = getTodoListUseCase()
                hideLoading()
                _todoList.postValue(todoList)
            }
        }
    }

    fun startTextViewAnimation(
        bottomNavHeight: Int,
        textView: TextView
    ) {
        val (screenWidth, screenHeight) = getScreenMetrics(applicationContext)
        val maxX: Int = screenWidth - textView.width
        val maxY: Int = (screenHeight - bottomNavHeight) - textView.height

        val animatorX: ObjectAnimator = ObjectAnimator.ofFloat(
            textView,
            "translationX",
            0f,
            maxX.toFloat()
        ).apply {
            setDuration(3000)
            repeatMode = ObjectAnimator.REVERSE
            repeatCount = ObjectAnimator.INFINITE
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}

                override fun onAnimationEnd(animation: Animator) {}

                override fun onAnimationCancel(animation: Animator) {}

                override fun onAnimationRepeat(animation: Animator) {
                    changeTextColor()
                }
            })
        }

        val animatorY: ObjectAnimator = ObjectAnimator.ofFloat(
            textView,
            "translationY",
            0f,
            maxY.toFloat()
        ).apply {
            setDuration(4000)
            repeatMode = ObjectAnimator.REVERSE
            repeatCount = ObjectAnimator.INFINITE
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}

                override fun onAnimationEnd(animation: Animator) {}

                override fun onAnimationCancel(animation: Animator) {}

                override fun onAnimationRepeat(animation: Animator) {
                    changeTextColor()
                }
            })
        }

        animator = AnimatorSet().apply {
            playTogether(animatorX, animatorY)
            start()
        }
        startTextChangeTimer()
    }

    private fun getScreenMetrics(context: Context): Pair<Int, Int> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val windowMetrics: WindowMetrics = windowManager.currentWindowMetrics
            val bounds: Rect = windowMetrics.bounds
            Pair(bounds.width(), bounds.height())
        } else {
            val displayMetrics = DisplayMetrics()
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            Pair(displayMetrics.widthPixels, displayMetrics.heightPixels)
        }
    }

    private fun changeTextColor() {
        val red = Random.nextInt(256)
        val green = Random.nextInt(256)
        val blue = Random.nextInt(256)

        _textViewColor.postValue(
            RGBColor(
                red,
                green,
                blue
            )
        )
    }

    private fun startTextChangeTimer() {
        launch {
            while(true) {
                delay(TEXT_CHANGE_DELAY_IN_MILLIS)
                changeText()
            }
        }
    }

    fun enableMock(enable: Boolean) {
        _mockEnabled.postValue(enable)
    }

    fun changeText() {
        launch {
            if (_mockEnabled.value == true) {
                getLocalTodo()?.let {
                    _currentTodo.postValue(it)
                }
            } else {
                todoList.value?.let {
                    val todo = if (it.size > currentTodoPosition) {
                        it[currentTodoPosition]
                    } else {
                        currentTodoPosition = 0
                        it.first()
                    }
                    _currentTodo.postValue(todo)
                }
            }
        }
    }

    private suspend fun getLocalTodo(): TodoModel? {
        return getTodoUseCase(true, currentTodoPosition)
    }

    fun saveTodo(todoModel: TodoModel) {
        launch {
            saveTodoUseCase(todoModel)
        }
    }

    fun pauseAnimation() {
        animator?.pause()
    }

    fun resumeAnimation() {
        animator?.resume()
    }

    fun cancelAnimation() {
        animator?.cancel()
    }
}