package com.example.aplikasikonsultasi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TesPsikologiActivity : AppCompatActivity() {

    private val questions = listOf(
        "1. Apakah dalam sebulan belakangan ini Anda sering menderita sakit kepala?",
        "2. Apakah dalam sebulan belakangan ini nafsu makan Anda turun?",
        "3. Apakah dalam sebulan belakangan ini Anda sulit tidur?",
        "4. Apakah dalam sebulan belakangan ini Anda sering ketakutan?",
        "5. Apakah dalam sebulan belakangan ini Anda merasa gugup tegang atau khawatir?",
        "6. Apakah dalam sebulan belakangan ini tangan Anda sering gemetar?",
        "7. Apakah dalam sebulan belakangan ini pencernaan Anda terganggu?",
        "8. Apakah dalam sebulan belakangan ini Anda sulit berpikir jernih?",
        "9. Apakah dalam sebulan belakangan ini Anda merasa tidak bahagia?",
        "10. Apakah dalam sebulan belakangan ini Anda menjadi sering menangis?",
        "11. Apakah dalam sebulan belakangan ini Anda kurang menikmati kegiatan Anda seharihari?",
        "12. Apakah dalam sebulan belakangan ini Anda sulit mengambil keputusan?",
        "13. Apakah dalam sebulan belakangan ini pekerjaan rutin Anda terganggu?",
        "14. Apakah dalam sebulan belakangan ini Anda tidak mampu melakukan hal-hal yang bermanfaat?",
        "15. Apakah dalam sebulan belakangan ini Anda kehilangan minat terhadap berbagai hal?",
        "16. Apakah dalam sebulan belakangan ini Anda merasa diri Anda tidak berharga?",
        "17. Apakah dalam sebulan belakangan ini dalam benak Anda ada pikiran untuk mengakhiri hidup?",
        "18. Apakah dalam sebulan belakangan ini Anda merasa lelah berkepanjangan?",
        "19. Apakah dalam sebulan belakangan ini lambung Anda terasa tidak nyaman?",
        "20. Apakah dalam sebulan belakangan ini Anda gampang capek?"
    )

    private var currentQuestionIndex = 0
    private var yesCount = 0

    private lateinit var tvQuestion: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioYes: RadioButton
    private lateinit var radioNo: RadioButton
    private lateinit var btnNext: Button
    private lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tes_psikologi)

        tvQuestion = findViewById(R.id.tvQuestion)
        radioGroup = findViewById(R.id.radioGroup)
        radioYes = findViewById(R.id.radioYes)
        radioNo = findViewById(R.id.radioNo)
        btnNext = findViewById(R.id.btnNext)
        btnSubmit = findViewById(R.id.btnSubmit)

        displayQuestion()

        radioGroup.setOnCheckedChangeListener { _, _ ->
            // Aktifkan tombol Next hanya jika jawaban dipilih
            btnNext.isEnabled = radioGroup.checkedRadioButtonId != -1
        }

        btnNext.setOnClickListener {
            // Mengecek jawaban
            if (radioYes.isChecked) {
                yesCount++
            }

            // Reset pilihan jawaban
            radioGroup.clearCheck()

            // Menampilkan pertanyaan berikutnya
            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                displayQuestion()
            } else {
                // Semua pertanyaan selesai, tampilkan tombol submit
                btnNext.visibility = View.GONE
                btnSubmit.visibility = View.VISIBLE
                btnSubmit.isEnabled = true
            }
        }

        btnSubmit.setOnClickListener {
            // Logika menghitung hasil tes
            val intent = if (yesCount > 7) {
                // Tampilkan hasil tes dengan saran konsultasi
                Intent(this, HasilTesActivity::class.java)
            } else {
                // Tampilkan hasil tes baik-baik saja
                Intent(this, HasilBaikActivity::class.java)
            }
            // Mengirim nilai jawaban "Ya" ke activity hasil
            intent.putExtra("NILAI_YA", yesCount)
            startActivity(intent)
        }
    }

    private fun displayQuestion() {
        tvQuestion.text = questions[currentQuestionIndex]
    }
}
