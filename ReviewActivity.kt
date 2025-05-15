package com.yourpackage.flashcardapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.imadassignment2.R

class ReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        val reviewTextView: TextView = findViewById(R.id.reviewTextView)

        // TODO: For a proper review, you would pass detailed question and answer
        // data from the QuizActivity to the ScoreActivity, and then to this activity.
        // This is a placeholder for demonstration.
        val dummyReviewText = """
            Review of Questions:

            1. The capital of France is Paris. (Correct: True)
            2. The Earth is flat. (Correct: False)
            3. Water boils at 100 degrees Celsius at sea level. (Correct: True)
            4. Humans have gills. (Correct: False)
            5. The sun is a planet. (Correct: False)
        """.trimIndent()

        reviewTextView.text = dummyReviewText
    }

