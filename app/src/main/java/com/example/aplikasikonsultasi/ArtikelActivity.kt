    package com.example.aplikasikonsultasi

    import android.content.Intent
    import android.net.Uri
    import android.os.Bundle
    import android.view.View
    import androidx.appcompat.app.AppCompatActivity
    import androidx.cardview.widget.CardView


    class ArtikelActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
             setContentView(R.layout.activity_artikel)

            // Setup click listeners for each CardView
            findViewById<CardView>(R.id.cardViewArtikel1).setOnClickListener {
                openUrl("https://www.alodokter.com/tidak-hanya-sehat-badan-kesehatan-mental-juga-penting-dijaga")
            }

            findViewById<CardView>(R.id.cardViewArtikel2).setOnClickListener {
                openUrl("https://www.halodoc.com/kesehatan/kesehatan-mental")
            }

            findViewById<CardView>(R.id.cardViewArtikel3).setOnClickListener {
                openUrl("https://www.halodoc.com/artikel/9-cara-sederhana-menjaga-kesehatan-mental")
            }

            findViewById<CardView>(R.id.cardViewArtikel4).setOnClickListener {
                openUrl("https://health.kompas.com/read/2022/01/03/110000468/mengenal-arti-kesehatan-mental-dan-cara-menjaganya")
            }

            findViewById<CardView>(R.id.cardViewArtikel5).setOnClickListener {
                openUrl("https://www.halodoc.com/artikel/pentingnya-jaga-kesehatan-mental-sejak-dini")
            }

            findViewById<CardView>(R.id.cardViewArtikel6).setOnClickListener {
                openUrl("https://tirto.id/mengenal-gangguan-kesehatan-mental-dan-solusinya-gkpF")
            }

            findViewById<CardView>(R.id.cardViewArtikel7).setOnClickListener {
                openUrl("https://www.alodokter.com/ketahui-cara-mengatasi-gangguan-kecemasan")
            }

            findViewById<CardView>(R.id.cardViewArtikel8).setOnClickListener {
                openUrl("https://www.psychologytoday.com/intl/blog/digital-mental-health/202304/empowering-mental-health-through-inclusive-technology")
            }

            findViewById<CardView>(R.id.cardViewArtikel9).setOnClickListener {
                openUrl("https://www.apa.org/monitor/2023/01/trends-improving-youth-mental-health")
            }

            findViewById<CardView>(R.id.cardViewArtikel10).setOnClickListener {
                openUrl("https://journals.plos.org/plosone/article?id=10.1371/journal.pone.0273579")
            }
        }

        private fun openUrl(url: String) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }