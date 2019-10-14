package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    // The current _word
    private val _word = MutableLiveData<String>()

    val word:LiveData<String>
    get() = _word
    // The current _score
    private val _score = MutableLiveData<Int>()

    val score:LiveData<Int>
    get() = _score

    // The list of words - the front of the list is the next _word to guess
    private lateinit var wordList: MutableList<String>

    private val _eventGameFinish = MutableLiveData<Boolean>()

    fun onGameFinish() {
        _eventGameFinish.value = true
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    init {
        Log.i("GameViewModel", "GameViewModel created!!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel Destroyed!!")
        _word.value = ""
        _score.value = 0
    }

    /**
     * Resets the list of words and randomizes the order
     */
    public fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    /** Methods for buttons presses **/

    public fun onSkip() {
        if (!wordList.isEmpty()) {
            _score.value = _score.value?.minus(1)
        }
        nextWord()
    }

    public fun onCorrect() {
        if (!wordList.isEmpty()) {
            _score.value = _score.value?.plus(1)
        }
        nextWord()
    }

    /**
     * Moves to the next _word in the list
     */
    public fun nextWord() {
        if (wordList.isEmpty()) {
            onGameFinish()
        } else {
            //Select and remove a _word from the list
            _word.value = wordList.removeAt(0)
        }
    }
}