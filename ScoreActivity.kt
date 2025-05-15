package com.yourpackage.flashcardapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.imadassignment2.R

class ScoreActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        // Retrieve the score passed from QuizActivity
        val score = intent.getIntExtra("SCORE", 0)
        val totalQuestions = 5 // Total number of questions

        val scoreTextView: TextView = findViewById(R.id.scoreTextView)
        val feedbackMessageTextView: TextView = findViewById(R.id.feedbackMessageTextView)
        val reviewButton: Button = findViewById(R.id.reviewButton)
        val exitButton: Button = findViewById(R.id.exitButton)

        // 1. Display the total number of correct answers
        scoreTextView.text = "Your Score: $score/$totalQuestions"

        // 1. Provide personalized feedback based on the score
        val feedbackMessage = when (score) {
            totalQuestions -> "Excellent! You got all questions correct!"
            in (totalQuestions / 2 + 1) until totalQuestions -> "Good job! You know your stuff."
            in 1..(totalQuestions / 2) -> "Keep practicing! You're getting there."
            else -> "Don't give up! Review the flashcards and try again."
        }
        feedbackMessageTextView.text = feedbackMessage

        // 2. Offer a "Review" button
        reviewButton.setOnClickListener {
            // Starts the ReviewActivity (you'll need to implement this if you want a detailed review)
            val intent = Intent(this, ReviewActivity::class.java)
            startActivity(intent)
        }

        // 3. An "Exit" button must terminate the app
        exitButton.setOnClickListener {
            finishAffinity() // Closes all activities in the current task, effectively exiting the app
        }
    }

