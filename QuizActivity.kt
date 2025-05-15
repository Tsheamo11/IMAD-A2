package com.yourpackage.flashcardapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.imadassignment2.R

class QuizActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var feedbackTextView: TextView

    private var currentQuestionIndex = 0
    private var score = 0

    // Define your five questions here
    private val questions = listOf(
        Question(1, "The capital of France is Paris.", true),
        Question(2, "The Earth is flat.", false),
        Question(3, "Water boils at 100 degrees Celsius at sea level.", true),
        Question(4, "Humans have gills.", false),
        Question(5, "The sun is a planet.", false)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // Initialize UI elements
        questionTextView = findViewById(R.id.questionTextView)
        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        nextButton = findViewById(R.id.nextButton)
        feedbackTextView = findViewById(R.id.feedbackTextView)

        // Load the first question when the activity starts
        loadQuestion()

        // Set listeners for True, False, and Next buttons
        trueButton.setOnClickListener {
            checkAnswer(true) // Check answer when "True" is clicked
        }

        falseButton.setOnClickListener {
            checkAnswer(false) // Check answer when "False" is clicked
        }

        nextButton.setOnClickListener {
            currentQuestionIndex++ // Move to the next question
            if (currentQuestionIndex < questions.size) {
                loadQuestion() // Load the next question if available
            } else {
                // All questions answered, navigate to ScoreActivity
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("SCORE", score) // Pass the final score
                startActivity(intent)
                finish() // Close QuizActivity
            }
        }
    }

    // Displays the current question and resets UI for a new question
    private fun loadQuestion() {
        feedbackTextView.text = "" // Clear previous feedback
        feedbackTextView.setTextColor(Color.DKGRAY) // Reset feedback text color
        trueButton.isEnabled = true // Enable answer buttons
        falseButton.isEnabled = true
        nextButton.isEnabled = false // Disable next button until an answer is selected

        val currentQuestion = questions[currentQuestionIndex]
        questionTextView.text = currentQuestion.questionText // Set the question text
    }

    // Checks the user's answer against the correct answer
    @SuppressLint("SetTextI18n")
    private fun checkAnswer(userAnswer: Boolean) {
        val currentQuestion = questions[currentQuestionIndex]
        val isCorrect = (userAnswer == currentQuestion.correctAnswer)

        if (isCorrect) {
            score++ // Increment score if correct
            feedbackTextView.text = "Correct!" // Display correct feedback
            feedbackTextView.setTextColor(Color.GREEN)
        } else {
            feedbackTextView.text = "Incorrect!" // Display incorrect feedback
            feedbackTextView.setTextColor(Color.RED)
        }

        trueButton.isEnabled = false // Disable answer buttons after selection
        falseButton.isEnabled = false
        nextButton.isEnabled = true // Enable the next button
    }
}

// Data class to hold question information
data class Question(
    val id: Int,
    val questionText: String,
    val correctAnswer: Boolean // True for "True", False for "False"

